package site.teamo.biu.infinity.fweb.common.entity.monitor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "queue_metrics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueMetrics {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * queue_name
     */
    @Column(name = "queue_name")
    private String queueName;

    /**
     * app_pending
     */
    @Column(name = "app_pending")
    private Integer appPending;

    /**
     * app_running
     */
    @Column(name = "app_running")
    private Integer appRunning;

    /**
     * allocated_mb
     */
    @Column(name = "allocated_mb")
    private Integer allocatedMb;

    /**
     * available_mb
     */
    @Column(name = "available_mb")
    private Integer availableMb;

    /**
     * reserved_mb
     */
    @Column(name = "reserved_mb")
    private Integer reservedMb;

    /**
     * pending_mb
     */
    @Column(name = "pending_mb")
    private Integer pendingMb;

    /**
     * allocated_containers
     */
    @Column(name = "allocated_containers")
    private Integer allocatedContainers;

    /**
     * pending_containers
     */
    @Column(name = "pending_containers")
    private Integer pendingContainers;

    /**
     * active_users
     */
    @Column(name = "active_users")
    private Integer activeUsers;

    /**
     * metrics_time
     */
    @Column(name = "metrics_time")
    private Integer metricsTime;

    /**
     * create_time
     */
    @Column(name = "create_time")
    private Date createTime;

}