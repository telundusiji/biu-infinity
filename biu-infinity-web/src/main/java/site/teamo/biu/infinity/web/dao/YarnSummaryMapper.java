package site.teamo.biu.infinity.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import site.teamo.biu.infinity.web.InfinityMapper;
import site.teamo.biu.infinity.web.entity.YarnSummary;

@Mapper
@Component
public interface YarnSummaryMapper extends InfinityMapper<YarnSummary> {
}