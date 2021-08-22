package site.teamo.biu.infinity.connection.api;

import lombok.Data;
import lombok.experimental.Accessors;
import site.teamo.biu.infinity.connection.sdk.core.Resource;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/21 23:15
 * @Description:
 */
@Data
@Accessors(chain = true)
public class ApiResource implements Resource{

    private String name;

    private String baseUrl;

    @Override
    public String name() {
        return this.name;
    }
}
