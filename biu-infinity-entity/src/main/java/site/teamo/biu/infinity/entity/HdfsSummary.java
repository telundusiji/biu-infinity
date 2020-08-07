package site.teamo.biu.infinity.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "hdfs_summary")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HdfsSummary {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 总存储量 单位：B
     */
    private Long total;

    /**
     * dfs_used 单位：B
     */
    @Column(name = "dfs_used")
    private Long dfsUsed;

    /**
     * percent_used
     */
    @Column(name = "percent_used")
    private Double percentUsed;

    /**
     * dfs_free
     */
    @Column(name = "dfs_free")
    private Long dfsFree;

    /**
     * non_dfs_used
     */
    @Column(name = "non_dfs_used")
    private Long nonDfsUsed;

    /**
     * total_blocks
     */
    @Column(name = "total_blocks")
    private Long totalBlocks;

    /**
     * total_files
     */
    @Column(name = "total_files")
    private Long totalFiles;

    /**
     * missing_blocks
     */
    @Column(name = "missing_blocks")
    private Long missingBlocks;

    /**
     * live_data_node_nums
     */
    @Column(name = "live_data_node_nums")
    private Integer liveDataNodeNums;

    /**
     * dead_data_node_nums
     */
    @Column(name = "dead_data_node_nums")
    private Integer deadDataNodeNums;

    /**
     * volume_failures_total
     */
    @Column(name = "volume_failures_total")
    private Integer volumeFailuresTotal;

    /**
     * create_time
     */
    @Column(name = "create_time")
    private Date createTime;

}