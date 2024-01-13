package org.proorm.crud;

import org.proorm.exception.DBException;

import java.util.List;
import java.util.Map;

public interface ITableQuery {
    <T> void createTable(Class<T> clazz) throws DBException;

    <T> void dropTable(Class<T> clazz) throws DBException;

    void sql(String sql, Object... params) throws DBException;

    List<Long> executeUpdate(String sql, List<List<Object>> params, String generatedColumn) throws DBException;

    List<Map<String, Object>> executeQuery(String sql, List<Object> params,
                                           Map<String, Class<?>> typeOverrides) throws DBException;
}
