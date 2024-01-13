package org.jminiorm.crud;

import org.jminiorm.exception.DBException;
import org.jminiorm.query.generic.IGenericDeleteQuery;
import org.jminiorm.query.orm.IORMDeleteQuery;

import java.util.Collection;

public interface IDeleteQuery {
    /**
     * Begins a JPA delete query.
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws DBException
     */
    <T> IORMDeleteQuery<T> delete(Class<T> clazz);

    /**
     * Deletes an instance of a JPA annotated class by given id.
     *
     * @param clazz
     * @param id
     * @param <T>
     * @throws DBException
     */
    <T> void delete(Class<T> clazz, Object id) throws DBException;

    /**
     * Deletes an instance of JPA annotated class.
     *
     * @param obj
     * @throws DBException
     */
    <T> void delete(T obj) throws DBException;

    /**
     * Deletes instances of JPA annotated class.
     *
     * @param objs
     * @throws DBException
     */
    <T> void delete(Collection<T> objs) throws DBException;

    /**
     * Begins a generic delete query on the given table.
     *
     * @param table
     * @return
     * @throws DBException
     */
    IGenericDeleteQuery delete(String table) throws DBException;
}
