package org.jminiorm.crud;

import org.jminiorm.exception.DBException;
import org.jminiorm.query.generic.IGenericSelectQuery;
import org.jminiorm.query.orm.IORMSelectQuery;

public interface ISelectQuery {
    /**
     * Begins a JPA select query.
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws DBException
     */
    <T> IORMSelectQuery<T> select(Class<T> clazz);

    /**
     * Returns the instance of the JPA annotated class T of given id.
     *
     * @param clazz
     * @param id
     * @param <T>
     * @return
     * @throws DBException
     */
    <T> T select(Class<T> clazz, Object id) throws DBException;

    /**
     * Begins a generic select query.
     *
     * @param sql    The whole SQL, except limit and offset.
     * @param params
     * @return
     */
    IGenericSelectQuery select(String sql, Object... params);
}
