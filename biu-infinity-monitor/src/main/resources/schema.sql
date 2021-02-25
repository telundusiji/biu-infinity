CREATE TABLE IF NOT EXISTS hdfs_summary
(
    id                    BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
    total                 BIGINT COMMENT '总存储量 单位：B',
    dfs_used              BIGINT COMMENT 'dfs_used 单位：B',
    percent_used          DOUBLE COMMENT 'percent_used',
    dfs_free              BIGINT COMMENT 'dfs_free',
    non_dfs_used          BIGINT COMMENT 'non_dfs_used',
    total_blocks          BIGINT COMMENT 'total_blocks',
    total_files           BIGINT COMMENT 'total_files',
    missing_blocks        BIGINT COMMENT 'missing_blocks',
    live_data_node_nums   INT COMMENT 'live_data_node_nums',
    dead_data_node_nums   INT COMMENT 'dead_data_node_nums',
    volume_failures_total INT COMMENT 'volume_failures_total',
    create_time           DATETIME COMMENT 'create_time',
    PRIMARY KEY (id)
) COMMENT = 'HDFS概览 ';

CREATE TABLE IF NOT EXISTS yarn_summary
(
    id                          BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
    live_node_manager_nums      INT COMMENT 'live_node_manager_nums',
    dead_node_manager_nums      INT COMMENT 'dead_node_manager_nums',
    unhealthy_node_manager_nums INT COMMENT 'unhealthy_node_manager_nums',
    submitted_apps              INT COMMENT 'submitted_apps',
    running_apps                INT COMMENT 'running_apps',
    pending_apps                INT COMMENT 'pending_apps',
    completed_apps              INT COMMENT 'completed_apps',
    killed_apps                 INT COMMENT 'killed_apps',
    failed_apps                 INT COMMENT 'failed_apps',
    allocated_mem               BIGINT COMMENT 'allocated_mem',
    allocated_cores             INT COMMENT 'allocated_cores',
    allocated_containers        INT COMMENT 'allocated_containers',
    available_mem               BIGINT COMMENT 'available_mem',
    available_cores             INT COMMENT 'available_cores',
    pending_mem                 BIGINT COMMENT 'pending_mem',
    pending_cores               INT COMMENT 'pending_cores',
    pending_containers          INT COMMENT 'pending_containers',
    reserved_mem                BIGINT COMMENT 'reserved_mem',
    reserved_cores              INT COMMENT 'reserved_cores',
    reserved_containers         INT COMMENT 'reserved_containers',
    create_time                 DATETIME COMMENT 'create_time',
    PRIMARY KEY (id)
) COMMENT = 'YARN概览 ';

CREATE TABLE IF NOT EXISTS queue_metrics
(
    id                   BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
    queue_name           VARCHAR(128) COMMENT 'queue_name',
    app_pending          INT COMMENT 'app_pending',
    app_running          INT COMMENT 'app_running',
    allocated_mb         INT COMMENT 'allocated_mb',
    available_mb         INT COMMENT 'available_mb',
    reserved_mb          INT COMMENT 'reserved_mb',
    pending_mb           INT COMMENT 'pending_mb',
    allocated_containers INT COMMENT 'allocated_containers',
    pending_containers   INT COMMENT 'pending_containers',
    active_users         INT COMMENT 'active_users',
    metrics_time         INT COMMENT 'metrics_time',
    create_time          DATETIME COMMENT 'create_time',
    PRIMARY KEY (id)
) COMMENT = '队列信息概览 ';;