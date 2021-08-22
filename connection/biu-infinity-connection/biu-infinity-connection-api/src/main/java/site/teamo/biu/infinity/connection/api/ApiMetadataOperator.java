package site.teamo.biu.infinity.connection.api;

import site.teamo.biu.infinity.connection.sdk.bean.api.CreateRequest;
import site.teamo.biu.infinity.connection.sdk.bean.api.ExampleData;
import site.teamo.biu.infinity.connection.sdk.bean.api.ExampleDataRequest;
import site.teamo.biu.infinity.connection.sdk.bean.api.UpdateRequest;
import site.teamo.biu.infinity.connection.sdk.core.MetadataOperator;

import java.util.List;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/22 00:01
 * @Description:
 */
public class ApiMetadataOperator implements MetadataOperator<String, CreateRequest, UpdateRequest, ExampleDataRequest, ExampleData, Object> {

    private ApiResource resource;

    public ApiMetadataOperator(ApiResource resource) {
        this.resource = resource;
    }


    @Override
    public boolean create(String s, CreateRequest createRequest) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        throw new UnsupportedOperationException("Api metadata operator not support delete");
    }

    @Override
    public boolean update(String s, UpdateRequest updateRequest) {
        return false;
    }

    @Override
    public List<Object> listAll() {
        throw new UnsupportedOperationException("Api metadata operator not support delete");
    }

    @Override
    public List<Object> list(List<String> strings) {
        throw new UnsupportedOperationException("Api metadata operator not support delete");
    }

    @Override
    public Object detail(String s) {
        throw new UnsupportedOperationException("Api metadata operator not support delete");
    }

    @Override
    public boolean exist(String s) {
        return false;
    }

    @Override
    public ExampleData exampleData(String s, ExampleDataRequest exampleDataRequest) {
        return null;
    }
}
