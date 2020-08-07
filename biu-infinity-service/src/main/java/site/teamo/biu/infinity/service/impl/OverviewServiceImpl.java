package site.teamo.biu.infinity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import site.teamo.biu.infinity.common.constant.InfinityConstant;
import site.teamo.biu.infinity.common.exception.InfinityException;
import site.teamo.biu.infinity.common.util.BiuHttpClient;
import site.teamo.biu.infinity.dao.HdfsSummaryMapper;
import site.teamo.biu.infinity.dao.QueueMetricsMapper;
import site.teamo.biu.infinity.dao.YarnSummaryMapper;
import site.teamo.biu.infinity.entity.HdfsSummary;
import site.teamo.biu.infinity.entity.QueueMetrics;
import site.teamo.biu.infinity.entity.YarnSummary;
import site.teamo.biu.infinity.service.OverviewService;

import java.io.IOException;
import java.util.*;

/**
 * @author 爱做梦的锤子
 * @create 2020/8/6
 */
@Service
public class OverviewServiceImpl implements OverviewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverviewServiceImpl.class);

    @Autowired
    private YarnSummaryMapper yarnSummaryMapper;

    @Autowired
    private HdfsSummaryMapper hdfsSummaryMapper;

    @Autowired
    private QueueMetricsMapper queueMetricsMapper;


    @Value("${infinity.hadoop.nn.uri}")
    private String nnUriStr;
    @Value("${infinity.hadoop.rm.uri}")
    private String rmUriStr;

    @Override
    public void refreshHdfsSummary() {
        try {
            HdfsSummary hdfsSummary = realTimeHdfsSummary();
            if (hdfsSummary == null) {
                return;
            }
            hdfsSummaryMapper.insert(hdfsSummary);
        } catch (Exception e) {
            LOGGER.error("统计HDFS信息错误", e);
        }
    }

    @Override
    public void refreshYarnSummary() {
        try {
            YarnSummary yarnSummary = realTimeYarnSummary();
            if (yarnSummary == null) {
                return;
            }
            yarnSummaryMapper.insert(yarnSummary);
        } catch (Exception e) {
            LOGGER.error("统计yarn信息错误", e);
        }
    }

    @Override
    public void refreshQueueMetrics() {
        List<QueueMetrics> queueMetricsList = realTimeQueueMetrics();
        if (queueMetricsList == null || queueMetricsList.size() == 0) {
            return;
        }
        queueMetricsMapper.insertList(queueMetricsList);
    }

    @Override
    public HdfsSummary realTimeHdfsSummary() {
        List<String> nameNodeUris = Arrays.asList(nnUriStr.split(";"));
        if (nameNodeUris.isEmpty()) {
            return null;
        }
        try {
            String activeNNUri = getActiveNNUri(nameNodeUris);

            String nameNodeInfoUrl = String.format(InfinityConstant.JmxKey.JMX_SERVER_URL_FORMAT, activeNNUri, InfinityConstant.JmxKey.NAME_NODE_INFO);
            JSONObject nameNodeInfo = JSON.parseObject(BiuHttpClient.getClient().get(nameNodeInfoUrl)).getJSONArray("beans").getJSONObject(0);

            String fsNameSystemStateUrl = String.format(InfinityConstant.JmxKey.JMX_SERVER_URL_FORMAT, activeNNUri, InfinityConstant.JmxKey.FS_NAME_SYSTEM_STATE);
            JSONObject fsNameSystemState = JSON.parseObject(BiuHttpClient.getClient().get(fsNameSystemStateUrl)).getJSONArray("beans").getJSONObject(0);

            HdfsSummary hdfsSummary = HdfsSummary.builder()
                    .total(nameNodeInfo.getLong("Total"))
                    .dfsUsed(nameNodeInfo.getLong("Used"))
                    .percentUsed(nameNodeInfo.getDouble("PercentUsed"))
                    .dfsFree(nameNodeInfo.getLong("Free"))
                    .nonDfsUsed(nameNodeInfo.getLong("NonDfsUsedSpace"))
                    .totalBlocks(nameNodeInfo.getLong("TotalBlocks"))
                    .totalFiles(nameNodeInfo.getLong("TotalFiles"))
                    .missingBlocks(nameNodeInfo.getLong("NumberOfMissingBlocks"))
                    .liveDataNodeNums(fsNameSystemState.getInteger("NumLiveDataNodes"))
                    .deadDataNodeNums(fsNameSystemState.getInteger("NumDeadDataNodes"))
                    .volumeFailuresTotal(fsNameSystemState.getInteger("VolumeFailuresTotal"))
                    .createTime(new Date())
                    .build();
            return hdfsSummary;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public YarnSummary realTimeYarnSummary() {
        List<String> rmUris = Arrays.asList(rmUriStr.split(";"));
        if (rmUris.isEmpty()) {
            return null;
        }
        try {
            String clusterMetricsUrl = String.format(InfinityConstant.JmxKey.JMX_SERVER_URL_FORMAT, getActiveRMUri(rmUris), InfinityConstant.JmxKey.CLUSTER_METRICS);
            JSONObject yarnMetrics = JSON.parseObject(BiuHttpClient.getClient().get(clusterMetricsUrl)).getJSONArray("beans").getJSONObject(0);

            String queueMetricsUrl = String.format(InfinityConstant.JmxKey.JMX_SERVER_URL_FORMAT, getActiveRMUri(rmUris), InfinityConstant.JmxKey.QUEUE_METRICS);
            JSONObject queueMetrics = JSON.parseObject(BiuHttpClient.getClient().get(queueMetricsUrl)).getJSONArray("beans").getJSONObject(0);

            YarnSummary yarnSummary = YarnSummary.builder()
                    .liveNodeManagerNums(yarnMetrics.getInteger("NumActiveNMs"))
                    .deadNodeManagerNums(yarnMetrics.getInteger("NumLostNMs"))
                    .unhealthyNodeManagerNums(yarnMetrics.getInteger("NumUnhealthyNMs"))
                    .submittedApps(queueMetrics.getInteger("AppsSubmitted"))
                    .runningApps(queueMetrics.getInteger("AppsRunning"))
                    .pendingApps(queueMetrics.getInteger("AppsPending"))
                    .completedApps(queueMetrics.getInteger("AppsCompleted"))
                    .killedApps(queueMetrics.getInteger("AppsKilled"))
                    .failedApps(queueMetrics.getInteger("AppsFailed"))
                    .allocatedMem(queueMetrics.getLong("AllocatedMB"))
                    .allocatedCores(queueMetrics.getInteger("AllocatedVCores"))
                    .allocatedContainers(queueMetrics.getInteger("AllocatedContainers"))
                    .availableMem(queueMetrics.getLong("AvailableMB"))
                    .availableCores(queueMetrics.getInteger("AvailableVCores"))
                    .pendingMem(queueMetrics.getLong("PendingMB"))
                    .pendingCores(queueMetrics.getInteger("PendingVCores"))
                    .pendingContainers(queueMetrics.getInteger("PendingContainers"))
                    .reservedMem(queueMetrics.getLong("ReservedMB"))
                    .reservedCores(queueMetrics.getInteger("ReservedVCores"))
                    .reservedContainers(queueMetrics.getInteger("ReservedContainers"))
                    .createTime(new Date())
                    .build();
            return yarnSummary;
        } catch (Exception e) {
            LOGGER.error("统计yarn信息错误", e);
            return null;
        }
    }

    @Override
    public List<QueueMetrics> realTimeQueueMetrics() {
        List<QueueMetrics> queueMetricsList = new ArrayList<>();
        List<String> rmUris = Arrays.asList(rmUriStr.split(";"));
        if (rmUris.isEmpty()) {
            return queueMetricsList;
        }
        try {
            String queueMetricsUrl = String.format(InfinityConstant.JmxKey.JMX_SERVER_URL_FORMAT, getActiveRMUri(rmUris), InfinityConstant.JmxKey.QUEUE_METRICS_ALL);
            JSONArray queueMetricsJsonArray = JSON.parseObject(BiuHttpClient.getClient().get(queueMetricsUrl)).getJSONArray("beans");
            if (queueMetricsJsonArray == null) {
                return queueMetricsList;
            }

            for (int i = 0; i < queueMetricsJsonArray.size(); i++) {
                JSONObject queueMetricsJsonObject = queueMetricsJsonArray.getJSONObject(i);

                QueueMetrics queueMetrics = QueueMetrics.builder()
                        .appPending(queueMetricsJsonObject.getInteger("AppsPending"))
                        .appRunning(queueMetricsJsonObject.getInteger("AppsRunning"))
                        .activeUsers(queueMetricsJsonObject.getInteger("ActiveUsers"))
                        .allocatedContainers(queueMetricsJsonObject.getInteger("AllocatedContainers"))
                        .allocatedMb(queueMetricsJsonObject.getInteger("AllocatedMB"))
                        .availableMb(queueMetricsJsonObject.getInteger("AvailableMB"))
                        .reservedMb(queueMetricsJsonObject.getInteger("ReservedMB"))
                        .pendingContainers(queueMetricsJsonObject.getInteger("PendingContainers"))
                        .pendingMb(queueMetricsJsonObject.getInteger("PendingMB"))
                        .metricsTime(Integer.valueOf(String.valueOf(System.currentTimeMillis() / 1000)))
                        .queueName(queueMetricsJsonObject.getString("tag.Queue"))
                        .createTime(new Date())
                        .build();
                queueMetricsList.add(queueMetrics);
            }
            return queueMetricsList;
        } catch (Exception e) {
            LOGGER.error("实时yarn队列信息获取失败", e);
            return queueMetricsList;
        }
    }


    /**
     * 获取active的NameNode uri
     *
     * @param nnUris NameNode的uri列表
     * @return 活跃节点的NameNode uri
     * @throws IOException
     */
    private String getActiveNNUri(List<String> nnUris) {
        String activeUri = nnUris.stream()
                .filter(x -> {
                    String fsNameSystemUrl = String.format(InfinityConstant.JmxKey.JMX_SERVER_URL_FORMAT, x, InfinityConstant.JmxKey.FS_NAME_SYSTEM);
                    JSONObject hdfsMetrics = null;
                    try {
                        hdfsMetrics = JSON.parseObject(BiuHttpClient.getClient().get(fsNameSystemUrl));
                    } catch (IOException e) {
                        LOGGER.warn("request NN uri failed:{}", fsNameSystemUrl);
                        return false;
                    }

                    JSONArray beans = hdfsMetrics.getJSONArray("beans");
                    if (beans == null || beans.size() == 0) {
                        return false;
                    }

                    String haState = beans.getJSONObject(0).getString("tag.HAState");
                    return "active".equals(haState);
                })
                .findFirst()
                .orElseThrow(() -> new InfinityException("no active NN uri"));
        return activeUri;
    }

    /**
     * 获取active的ResourceManager uri
     *
     * @param rmUris ResourceManager的uri列表
     * @return 活跃节点的ResourceManager uri
     * @throws IOException
     */
    private String getActiveRMUri(List<String> rmUris) {

        String activeUri = rmUris.stream()
                .filter(x -> {
                    String fsNameSystemUrl = String.format(InfinityConstant.JmxKey.JMX_SERVER_URL_FORMAT, x, InfinityConstant.JmxKey.CLUSTER_METRICS);
                    JSONObject yarnMetrics = null;
                    try {
                        yarnMetrics = JSON.parseObject(BiuHttpClient.getClient().get(fsNameSystemUrl));
                    } catch (IOException e) {
                        LOGGER.warn("request NN uri failed:{}", fsNameSystemUrl);
                        return false;
                    }

                    JSONArray beans = yarnMetrics.getJSONArray("beans");
                    if (beans == null || beans.size() == 0) {
                        return false;
                    }

                    String clusterMetrics = beans.getJSONObject(0).getString("tag.ClusterMetrics");
                    return "ResourceManager".equals(clusterMetrics);
                })
                .findFirst()
                .orElseThrow(() -> new InfinityException("no active RM uri"));
        return activeUri;
    }
}
