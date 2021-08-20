package site.teamo.biu.infinity.fweb.common.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseChartVO<D> {
    private List<D> rows;
    private List<String> columns;

}
