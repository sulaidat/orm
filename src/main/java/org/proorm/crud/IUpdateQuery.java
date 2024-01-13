package org.proorm.crud;

import org.proorm.exception.DBException;
import org.proorm.query.generic.IGenericUpdateQuery;
import org.proorm.query.orm.IORMUpdateQuery;

import java.util.Collection;

public interface IUpdateQuery {
    /**
 * Begins a JPA update query.
 *
 * @param clazz
 * @param <T>
 * @return
 * @throws DBException
 */
<T> IORMUpdateQuery<T> update(Class<T> clazz);

    /**
     * Updates an instance of JPA annotated class.
     *
     * @param obj
     * @throws DBException
     */
    <T> void update(T obj) throws DBException;

    /**
     * Updates instances of JPA annotated class.
     *
     * @param objs
     * @throws DBException
     */
    <T> void update(Collection<T> objs) throws DBException;

    /**
     * Begins a generic update query on the given table.
     *
     * @param table
     * @return
     * @throws DBException
     */
    IGenericUpdateQuery update(String table) throws DBException;
}
