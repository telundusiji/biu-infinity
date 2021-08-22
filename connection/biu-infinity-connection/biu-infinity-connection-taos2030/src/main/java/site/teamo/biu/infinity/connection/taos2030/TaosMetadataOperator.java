package site.teamo.biu.infinity.connection.taos2030;

import site.teamo.biu.infinity.connection.sdk.bean.taos.*;
import site.teamo.biu.infinity.connection.sdk.core.MetadataOperator;

import java.util.List;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/21 23:01
 * @Description:
 */
public class TaosMetadataOperator implements MetadataOperator<TableName, CreateRequest, UpdateRequest, Long, ExampleData, TaosTableInfo> {
    @Override
    public boolean create(TableName tableName, CreateRequest createRequest) {
        return false;
    }

    @Override
    public boolean delete(TableName tableName) {
        return false;
    }

    @Override
    public boolean update(TableName tableName, UpdateRequest updateRequest) {
        return false;
    }

    @Override
    public List<TaosTableInfo> listAll() {
        return null;
    }

    @Override
    public List<TaosTableInfo> list(List<TableName> tableNames) {
        return null;
    }

    @Override
    public TaosTableInfo detail(TableName tableName) {
        return null;
    }

    @Override
    public boolean exist(TableName tableName) {
        return false;
    }

    @Override
    public ExampleData exampleData(TableName tableName, Long number) {
        return null;
    }
}
