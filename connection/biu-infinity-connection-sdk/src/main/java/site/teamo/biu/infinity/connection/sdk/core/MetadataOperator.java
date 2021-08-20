package site.teamo.biu.infinity.connection.sdk.core;

/**
 * @author haocongshun
 * @date 2021/8/20 16:13
 */

import java.util.List;

/**
 * 元数据操作器
 *
 * @param <NAME> 操作目标名称
 * @param <CR>   添加操作的请求
 * @param <UR>   更新操作请求
 * @param <ED>   实例数据泛型
 * @param <INFO> 操作结果信息
 */
public interface MetadataOperator<NAME, CR, UR, ED, INFO> {
    boolean create(NAME name, CR cr);

    boolean delete(NAME name);

    boolean update(NAME name, UR ur);

    List<INFO> listAll();

    List<INFO> list(List<NAME> nameList);

    INFO detail(NAME name);

    boolean exist(NAME name);

    ED exampleData(NAME name, int number);
}
