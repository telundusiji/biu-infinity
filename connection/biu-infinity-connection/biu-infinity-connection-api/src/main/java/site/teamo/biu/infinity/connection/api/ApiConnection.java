package site.teamo.biu.infinity.connection.api;

import site.teamo.biu.infinity.connection.sdk.core.MetadataOperator;
import site.teamo.biu.infinity.connection.sdk.core.ResourceConnection;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/21 23:15
 * @Description:
 */
public class ApiConnection extends ResourceConnection<ApiResource> {

    protected ApiResource resource;

    public ApiConnection(ApiResource resource) {
        super(resource);
    }

    @Override
    public MetadataOperator getMetadataOperator() {
        return new ApiMetadataOperator(resource);
    }

    @Override
    public void close() throws Exception {

    }
}
