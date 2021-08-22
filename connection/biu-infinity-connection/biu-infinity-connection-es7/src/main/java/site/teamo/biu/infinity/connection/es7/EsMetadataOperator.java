package site.teamo.biu.infinity.connection.es7;

import org.elasticsearch.client.RestHighLevelClient;
import site.teamo.biu.infinity.connection.sdk.bean.es.CreateRequest;
import site.teamo.biu.infinity.connection.sdk.bean.es.EsIndexInfo;
import site.teamo.biu.infinity.connection.sdk.bean.es.ExampleData;
import site.teamo.biu.infinity.connection.sdk.core.MetadataOperator;
import site.teamo.biu.infinity.connection.sdk.exception.UnsupportedOperationException;

import java.util.List;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/22 12:37
 * @Description:
 */
public class EsMetadataOperator implements MetadataOperator<
        String, CreateRequest, Object, Long, ExampleData, EsIndexInfo> {

    private RestHighLevelClient client;
    private EsResource resource;

    public EsMetadataOperator(RestHighLevelClient client, EsResource resource) {
        this.client = client;
        this.resource = resource;
    }

    @Override
    public boolean create(String s, CreateRequest createRequest) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean update(String s, Object o) {
        throw new UnsupportedOperationException("Es metadata operator not support to update");
    }

    @Override
    public List<EsIndexInfo> listAll() {
        return null;
    }

    @Override
    public List<EsIndexInfo> list(List<String> strings) {
        return null;
    }

    @Override
    public EsIndexInfo detail(String s) {
        return null;
    }

    @Override
    public boolean exist(String s) {
        return false;
    }

    @Override
    public ExampleData exampleData(String s, Long number) {
        return null;
    }
}
