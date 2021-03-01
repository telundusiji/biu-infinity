DROP table if exists sql_history;
CREATE TABLE IF NOT EXISTS sql_history
(
    id           BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
    sql_content  TEXT COMMENT 'sql内容',
    status       VARCHAR(128) COMMENT 'Sql的执行状态',
    start_time   BIGINT COMMENT '执行开始时间',
    cost_time    BIGINT COMMENT '执行时间',
    query_engine VARCHAR(128) COMMENT '执行引擎(HIVE,SPARK,FLINK)',
    create_time  BIGINT COMMENT 'create_time',
    PRIMARY KEY (id)
) COMMENT = 'sql执行历史 ';