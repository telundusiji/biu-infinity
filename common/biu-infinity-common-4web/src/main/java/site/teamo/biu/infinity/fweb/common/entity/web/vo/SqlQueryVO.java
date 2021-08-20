package site.teamo.biu.infinity.fweb.common.entity.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SqlQueryVO {
    private long count;
    private int pageSize;
    private int pageNo;
    private List<String> schema;
    private List<Map<String, String>> list;
}
