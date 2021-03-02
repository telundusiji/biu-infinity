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
public class SchemaVO {
    private int total;
    private List<Field> fields;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Field {
        private String name;
        private String type;
    }
}
