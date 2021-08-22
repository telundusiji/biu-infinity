package site.teamo.biu.infinity.connection.es7;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import site.teamo.biu.infinity.connection.sdk.core.MetadataOperator;
import site.teamo.biu.infinity.connection.sdk.core.ResourceConnection;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/22 12:30
 * @Description:
 */
public class EsConnection extends ResourceConnection<EsResource> {

    private RestHighLevelClient client;

    public EsConnection(EsResource resource) {
        super(resource);
        HttpHost[] httpHosts = resource.getAddress().stream()
                .map(address -> new HttpHost(address))
                .toArray(num -> new HttpHost[num]);
        this.client = new RestHighLevelClient(RestClient.builder(httpHosts));
    }

    @Override
    public MetadataOperator getMetadataOperator() {
        return new EsMetadataOperator(client, resource);
    }

    @Override
    public void close() throws Exception {
        client.close();
    }
}
