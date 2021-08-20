package site.teamo.biu.infinity.fweb.common.util;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.teamo.biu.infinity.common.exception.ErrorCode;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BiuJSONResult<T> {
    /**
     * 状态码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

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

    public static <E> BiuJSONResult<PageData<E>> ok(PageInfo<E> pageInfo) {
        return BiuJSONResult.PageData.<E>builder()
                .list(pageInfo.getList())
                .count(pageInfo.getTotal())
                .pageNo(pageInfo.getPageNum())
                .pageSize(pageInfo.getPageSize())
                .build().buildJSONResult();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PageData<E> {
        private long count;
        private int pageSize;
        private int pageNo;
        private List<E> list;
        private List<String> schema;

        public BiuJSONResult<PageData<E>> buildJSONResult() {
            return BiuJSONResult.<PageData<E>>builder()
                    .code(ErrorCode.OK)
                    .data(this)
                    .build();
        }
    }
}
