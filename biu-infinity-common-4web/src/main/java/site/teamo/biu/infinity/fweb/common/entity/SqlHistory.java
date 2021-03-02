package site.teamo.biu.infinity.fweb.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sql_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SqlHistory {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * sql内容
     */
    @Column(name = "sql_content")
    private String sqlContent;

    /**
     * sql的md5转换字符
     */
    @Column(name = "sql_md5")
    private String sqlMd5;

    /**
     * sql的执行状态
     */
    @Column(name = "status")
    private String status;

    /**
     * 执行开始时间
     */
    @Column(name = "start_time")
    private Long startTime;

    /**
     * 执行时间
     */
    @Column(name = "cost_time")
    private Long costTime;

    /**
     * 执行引擎(HIVE,SPARK,FLINK)
     */
    @Column(name = "query_engine")
    private String queryEngine;

    /**
     * 记录创建时间
     */
    @Column(name = "create_time")
    private Long createTime;
}