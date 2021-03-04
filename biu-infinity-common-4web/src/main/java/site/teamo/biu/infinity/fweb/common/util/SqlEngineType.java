package site.teamo.biu.infinity.fweb.common.util;

import org.springframework.jdbc.core.JdbcTemplate;
import site.teamo.biu.infinity.fweb.common.util.ApplicationContextUtil;

public enum SqlEngineType {
    HIVE,
    SPARK,
    FLINK;

    public BiuDataSource dataSource() {
        switch (name()) {
            case "HIVE":
                return ApplicationContextUtil.getContext().getBean("hiveDataSource", BiuDataSource.class);
            case "SPARK":
                return ApplicationContextUtil.getContext().getBean("sparkDataSource", BiuDataSource.class);
            case "FLINK":
                return ApplicationContextUtil.getContext().getBean("flinkDataSource", BiuDataSource.class);
        }
        return null;
    }
}
