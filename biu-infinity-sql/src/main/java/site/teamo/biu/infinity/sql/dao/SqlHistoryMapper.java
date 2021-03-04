package site.teamo.biu.infinity.sql.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import site.teamo.biu.infinity.fweb.common.entity.sql.SqlHistory;
import site.teamo.biu.infinity.fweb.common.util.InfinityMapper;

@Component
@Mapper
public interface SqlHistoryMapper extends InfinityMapper<SqlHistory> {
}
