package site.teamo.biu.infinity.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import site.teamo.biu.infinity.InfinityMapper;
import site.teamo.biu.infinity.entity.QueueMetrics;

@Mapper
@Component
public interface QueueMetricsMapper extends InfinityMapper<QueueMetrics> {
}