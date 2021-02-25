package site.teamo.biu.infinity.web.common.constant;

/**
 *
 * 常量类
 *
 * @author 爱做梦的锤子
 * @create 2020/8/4
 */
public class InfinityConstant {

    private InfinityConstant(){}

    /**
     * 获取HDFS和YARN的Jmx数据的请求参数
     */
    public static class JmxKey{
        private JmxKey(){}

        public static final String JMX_SERVER_URL_FORMAT = "http://%s/jmx?qry=%s";
        /**
         * 获取HDFS相关数据的Jmx请求参数
         */
        public static final String NAME_NODE_INFO = "Hadoop:service=NameNode,name=NameNodeInfo";
        public static final String NAME_NODE_STATUS = "Hadoop:service=NameNode,name=NameNodeStatus";
        public static final String FS_NAME_SYSTEM = "Hadoop:service=NameNode,name=FSNamesystem";
        public static final String FS_NAME_SYSTEM_STATE = "Hadoop:service=NameNode,name=FSNamesystemState";
        public static final String BLOCK_STATS = "Hadoop:service=NameNode,name=BlockStats";

        /**
         * 获取YARN相关数据的Jmx请求参数
         */
        public static final String CLUSTER_METRICS = "Hadoop:service=ResourceManager,name=ClusterMetrics";
        public static final String QUEUE_METRICS = "Hadoop:service=ResourceManager,name=QueueMetrics,q0=root";
        public static final String QUEUE_METRICS_ALL = "Hadoop:service=ResourceManager,name=QueueMetrics,*";

    }

}
