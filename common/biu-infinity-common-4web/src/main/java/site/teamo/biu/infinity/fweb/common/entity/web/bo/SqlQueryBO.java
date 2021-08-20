package site.teamo.biu.infinity.fweb.common.entity.web.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.teamo.biu.infinity.fweb.common.util.SqlEngineType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SqlQueryBO {
    private int pageNo;
    private int pageSize;
    private SqlEngineType type;
    private String sql;
}
