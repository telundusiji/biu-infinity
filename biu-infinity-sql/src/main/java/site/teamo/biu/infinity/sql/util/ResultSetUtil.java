package site.teamo.biu.infinity.sql.util;

import site.teamo.biu.infinity.common.util.Tuple2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetUtil {
    public static List<String> readSchema(ResultSet resultSet) throws SQLException {
        ArrayList<String> schema = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            schema.add(metaData.getColumnLabel(i));
        }
        return schema;
    }


    public static Tuple2<Integer, List<List<String>>> readData(ResultSet resultSet, int num) throws SQLException {
        List<List<String>> list = new ArrayList<>();
        return readData(resultSet, num, list);
    }

    public static Tuple2<Integer, List<List<String>>> readData(ResultSet resultSet, int num, List<List<String>> list) throws SQLException {
        int tempCounter = 0;
        while (resultSet.next() && tempCounter < num) {
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                row.add(resultSet.getString(i));
            }
            tempCounter++;
            list.add(row);
        }
        return Tuple2.of(tempCounter, list);
    }


    public static Tuple2<Integer, List<List<String>>> readData(ResultSet resultSet) throws SQLException {
        return readData(resultSet, Integer.MAX_VALUE);
    }

    public static Tuple2<Integer, List<List<String>>> readData(ResultSet resultSet, List<List<String>> list) throws SQLException {
        return readData(resultSet, Integer.MAX_VALUE, list);
    }
}
