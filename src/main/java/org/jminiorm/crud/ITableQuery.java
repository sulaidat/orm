package org.jminiorm.crud;

import org.jminiorm.exception.DBException;

import java.util.List;
import java.util.Map;

public interface ITableQuery {
    /**
     * Creates the table for the given JPA annotated class.
     *
     * @param clazz
     * @param <T>
     * @throws DBException
     */
    <T> void createTable(Class<T> clazz) throws DBException;

    /**
     * Drops the table for the given JPA annotated class.
     *
     * @param clazz
     * @param <T>
     * @throws DBException
     */
    <T> void dropTable(Class<T> clazz) throws DBException;

    /**
     * Executes an arbitrary SQL statement that returns nothing (UPDATE, DELETE, INSERT, CREATE TABLE, etc.) when other
     * provided methods are not sufficient.
     *
     * @param sql
     * @param params
     * @throws DBException
     */
    void sql(String sql, Object... params) throws DBException;

    /**
     * Executes the given SQL statement once for every set of parameters. Returns the generated keys if any.
     *
     * @param sql
     * @param params
     * @param generatedColumn
     * @return
     * @throws DBException
     */
    List<Long> executeUpdate(String sql, List<List<Object>> params, String generatedColumn) throws DBException;

    /**
     * Executes the given SQL statement with the given set of parameters and returns the rows. Column values for each
     * row are those returned by the JDBC driver as-is, unless a specific Java type is provided. In that case a
     * conversion will be attempted. If the conversion isn't supported by the driver, a DBException occurs. See {@link
     * java.sql.ResultSet#getObject(String, Class)}. The rows are returned as case-insensitive linked hash maps.
     *
     * @param sql
     * @param params
     * @param typeOverrides
     * @return
     * @throws DBException
     */
    List<Map<String, Object>> executeQuery(String sql, List<Object> params,
                                           Map<String, Class<?>> typeOverrides) throws DBException;

    /**
     * Returns a connection to the current database. Close, commit and rollback shouldn't be called on this connection.
     * Instead, call releaseConnection(con) when you're finished using it.
     *
     * @return
     * @throws DBException
     */
}
