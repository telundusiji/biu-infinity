package site.teamo.biu.infinity.fweb.common.entity.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeNodeVO {
    private String title;
    private String type;
    private List<TreeNodeVO> children;
}
