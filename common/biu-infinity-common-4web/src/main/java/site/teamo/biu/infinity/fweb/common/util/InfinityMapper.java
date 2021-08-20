package site.teamo.biu.infinity.fweb.common.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface InfinityMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
