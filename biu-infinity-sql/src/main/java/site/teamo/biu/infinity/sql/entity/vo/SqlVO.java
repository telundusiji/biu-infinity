package site.teamo.biu.infinity.sql.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SqlVO {
    private long total;
    private int pageNo;
    private int pageSize;
    private List<String> schema;
    private List<List<String>> list;
}
