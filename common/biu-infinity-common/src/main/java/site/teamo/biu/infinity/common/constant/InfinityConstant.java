package site.teamo.biu.infinity.common.constant;

/**
 * 常量类
 *
 * @author 爱做梦的锤子
 * @create 2020/8/4
 */
public interface InfinityConstant {
    /**
     * 获取HDFS和YARN的Jmx数据的请求参数
     */
    interface JmxKey {
        String JMX_SERVER_URL_FORMAT = "http://%s/jmx?qry=%s";
        /**
         * 获取HDFS相关数据的Jmx请求参数
         */
        String NAME_NODE_INFO = "Hadoop:service=NameNode,name=NameNodeInfo";
        String NAME_NODE_STATUS = "Hadoop:service=NameNode,name=NameNodeStatus";
        String FS_NAME_SYSTEM = "Hadoop:service=NameNode,name=FSNamesystem";
        String FS_NAME_SYSTEM_STATE = "Hadoop:service=NameNode,name=FSNamesystemState";
        String BLOCK_STATS = "Hadoop:service=NameNode,name=BlockStats";

        /**
         * 获取YARN相关数据的Jmx请求参数
         */
        String CLUSTER_METRICS = "Hadoop:service=ResourceManager,name=ClusterMetrics";
        String QUEUE_METRICS = "Hadoop:service=ResourceManager,name=QueueMetrics,q0=root";
        String QUEUE_METRICS_ALL = "Hadoop:service=ResourceManager,name=QueueMetrics,*";

    }

    interface Expression {
        String CRON_REGULAR_EXPRESSION = "^\\s*($|#|\\w+\\s*=|(\\?|\\*|(?:[0-5]?\\d)(?:(?:-|\\/|\\,)(?:[0-5]?\\d))?(?:,(?:[0-5]?\\d)(?:(?:-|\\/|\\,)(?:[0-5]?\\d))?)*)\\s+(\\?|\\*|(?:[0-5]?\\d)(?:(?:-|\\/|\\,)(?:[0-5]?\\d))?(?:,(?:[0-5]?\\d)(?:(?:-|\\/|\\,)(?:[0-5]?\\d))?)*)\\s+(\\?|\\*|(?:[01]?\\d|2[0-3])(?:(?:-|\\/|\\,)(?:[01]?\\d|2[0-3]))?(?:,(?:[01]?\\d|2[0-3])(?:(?:-|\\/|\\,)(?:[01]?\\d|2[0-3]))?)*)\\s+(\\?|\\*|(?:0?[1-9]|[12]\\d|3[01])(?:(?:-|\\/|\\,)(?:0?[1-9]|[12]\\d|3[01]))?(?:,(?:0?[1-9]|[12]\\d|3[01])(?:(?:-|\\/|\\,)(?:0?[1-9]|[12]\\d|3[01]))?)*)\\s+(\\?|\\*|(?:[1-9]|1[012])(?:(?:-|\\/|\\,)(?:[1-9]|1[012]))?(?:L|W)?(?:,(?:[1-9]|1[012])(?:(?:-|\\/|\\,)(?:[1-9]|1[012]))?(?:L|W)?)*|\\?|\\*|(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)(?:(?:-)(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC))?(?:,(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)(?:(?:-)(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC))?)*)\\s+(\\?|\\*|(?:[0-6])(?:(?:-|\\/|\\,|#)(?:[0-6]))?(?:L)?(?:,(?:[0-6])(?:(?:-|\\/|\\,|#)(?:[0-6]))?(?:L)?)*|\\?|\\*|(?:MON|TUE|WED|THU|FRI|SAT|SUN)(?:(?:-)(?:MON|TUE|WED|THU|FRI|SAT|SUN))?(?:,(?:MON|TUE|WED|THU|FRI|SAT|SUN)(?:(?:-)(?:MON|TUE|WED|THU|FRI|SAT|SUN))?)*)(|\\s)+(\\?|\\*|(?:|\\d{4})(?:(?:-|\\/|\\,)(?:|\\d{4}))?(?:,(?:|\\d{4})(?:(?:-|\\/|\\,)(?:|\\d{4}))?)*))$";
    }
}
