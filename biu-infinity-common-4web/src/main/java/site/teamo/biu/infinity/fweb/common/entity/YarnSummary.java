package site.teamo.biu.infinity.fweb.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "yarn_summary")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YarnSummary {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * live_node_manager_nums
     */
    @Column(name = "live_node_manager_nums")
    private Integer liveNodeManagerNums;

    /**
     * dead_node_manager_nums
     */
    @Column(name = "dead_node_manager_nums")
    private Integer deadNodeManagerNums;

    /**
     * unhealthy_node_manager_nums
     */
    @Column(name = "unhealthy_node_manager_nums")
    private Integer unhealthyNodeManagerNums;

    /**
     * submitted_apps
     */
    @Column(name = "submitted_apps")
    private Integer submittedApps;

    /**
     * running_apps
     */
    @Column(name = "running_apps")
    private Integer runningApps;

    /**
     * pending_apps
     */
    @Column(name = "pending_apps")
    private Integer pendingApps;

    /**
     * completed_apps
     */
    @Column(name = "completed_apps")
    private Integer completedApps;

    /**
     * killed_apps
     */
    @Column(name = "killed_apps")
    private Integer killedApps;

    /**
     * failed_apps
     */
    @Column(name = "failed_apps")
    private Integer failedApps;

    /**
     * allocated_mem
     */
    @Column(name = "allocated_mem")
    private Long allocatedMem;

    /**
     * allocated_cores
     */
    @Column(name = "allocated_cores")
    private Integer allocatedCores;

    /**
     * allocated_containers
     */
    @Column(name = "allocated_containers")
    private Integer allocatedContainers;

    /**
     * available_mem
     */
    @Column(name = "available_mem")
    private Long availableMem;

    /**
     * available_cores
     */
    @Column(name = "available_cores")
    private Integer availableCores;

    /**
     * pending_mem
     */
    @Column(name = "pending_mem")
    private Long pendingMem;

    /**
     * pending_cores
     */
    @Column(name = "pending_cores")
    private Integer pendingCores;

    /**
     * pending_containers
     */
    @Column(name = "pending_containers")
    private Integer pendingContainers;

    /**
     * reserved_mem
     */
    @Column(name = "reserved_mem")
    private Long reservedMem;

    /**
     * reserved_cores
     */
    @Column(name = "reserved_cores")
    private Integer reservedCores;

    /**
     * reserved_containers
     */
    @Column(name = "reserved_containers")
    private Integer reservedContainers;

    /**
     * create_time
     */
    @Column(name = "create_time")
    private Date createTime;

}