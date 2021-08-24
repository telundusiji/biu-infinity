package site.teamo.biu.infinity.fweb.common.util;

import com.github.pagehelper.PageInfo;
import site.teamo.biu.infinity.common.exception.ErrorCode;
import site.teamo.biu.infinity.fapi.common.BiuJSONResult;


public class BiuJSONResultUtil {

    public static BiuJSONResult ok() {
        return BiuJSONResult.builder()
                .code(ErrorCode.OK)
                .build();
    }

    public static BiuJSONResult ok(Object data) {
        return BiuJSONResult.builder()
                .code(ErrorCode.OK)
                .data(data)
                .build();
    }

    public static <T> BiuJSONResult<T> okType(T data) {
        return BiuJSONResult.<T>builder()
                .code(ErrorCode.OK)
                .data(data)
                .build();
    }

    public static BiuJSONResult error(int code, Object data) {
        return BiuJSONResult.builder()
                .code(code)
                .data(data)
                .build();
    }

    public static <E> BiuJSONResult<BiuJSONResult.PageData<E>> ok(PageInfo<E> pageInfo) {
        final BiuJSONResult.PageData<E> pageData = BiuJSONResult.PageData.<E>builder()
                .list(pageInfo.getList())
                .count(pageInfo.getTotal())
                .pageNo(pageInfo.getPageNum())
                .pageSize(pageInfo.getPageSize())
                .build();
        return new BiuJSONResult<BiuJSONResult.PageData<E>>()
                .setCode(ErrorCode.OK)
                .setData(pageData);
    }
}
