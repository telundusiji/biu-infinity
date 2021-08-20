package site.teamo.biu.infinity.fweb.common.entity.sql.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.teamo.biu.infinity.fweb.common.util.SqlEngineType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SqlBO {
    private String sql;
    private SqlEngineType type;
}
