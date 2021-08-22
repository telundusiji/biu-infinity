package site.teamo.biu.infinity.connection.es7;

import lombok.Data;
import lombok.experimental.Accessors;
import site.teamo.biu.infinity.connection.sdk.core.Resource;

import java.util.List;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/22 12:28
 * @Description:
 */
@Data
@Accessors(chain = true)
public class EsResource implements Resource {

    private String name;
    private List<String> address;

    @Override
    public String name() {
        return this.name;
    }
}
