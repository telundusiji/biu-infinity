package site.teamo.biu.infinity.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import site.teamo.biu.infinity.InfinityMapper;
import site.teamo.biu.infinity.entity.HdfsSummary;

@Mapper
@Component
public interface HdfsSummaryMapper extends InfinityMapper<HdfsSummary> {
}