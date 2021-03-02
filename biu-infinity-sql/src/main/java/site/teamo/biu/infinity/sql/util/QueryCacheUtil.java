package site.teamo.biu.infinity.sql.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import site.teamo.biu.infinity.common.util.Tuple2;
import site.teamo.biu.infinity.common.util.Tuple3;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class QueryCacheUtil {

    private static final String SCHEMA_KEY_SUFFIX = "_schema";

    @Autowired
    private RedisTemplate redisTemplate;

    public Tuple2<Tuple3<Integer, Integer, Long>, Tuple2<List<String>, List<List<String>>>> getPage(String key, int pageNo, int pageSize) {
        if (pageNo < 1) {
            throw new IndexOutOfBoundsException("Page number must start from 1");
        }
        if (pageSize < 1) {
            throw new IndexOutOfBoundsException("Page size must start from 1");
        }
        long start = (pageNo - 1) * pageSize;
        long end = start + pageSize;
        List<List<String>> list = redisTemplate.opsForList().range(key, start, end);
        if (list == null) {
            return null;
        }
        Long size = redisTemplate.opsForList().size(key);
        if (size == null) {
            return null;
        }
        List<String> schema = JSON.parseArray((String) redisTemplate.opsForValue().get(key + SCHEMA_KEY_SUFFIX), String.class);
        if (schema == null) {
            return null;
        }
        return Tuple2.of(Tuple3.of(pageNo, pageSize, size), Tuple2.of(schema, list));
    }

    public void cache(String key, List<List<String>> list, List<String> schema) {
        Long cacheNumber = redisTemplate.opsForList().rightPushAll(key, list);
        redisTemplate.expire(key, 5, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(key + SCHEMA_KEY_SUFFIX, JSON.toJSONString(schema), 5, TimeUnit.MINUTES);
        log.info("Redis cache data number[{}] for key[{}]", cacheNumber, key);
    }

}
