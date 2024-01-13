package org.proorm.crud;

import org.proorm.exception.DBException;
import org.proorm.query.generic.IGenericInsertQuery;
import org.proorm.query.orm.IORMInsertQuery;

import java.util.Collection;

public interface IInsertQuery {
    /**
     * Begins a JPA insert query.
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws DBException
     */
    <T> IORMInsertQuery<T> insert(Class<T> clazz);

    /**
     * Insert an instance of JPA annotated class into its table. Generated id is set if any.
     *
     * @param obj
     * @throws DBException
     */
    <T> void insert(T obj) throws DBException;

    /**
     * Insert instances of a JPA annotated class into their table. Generated ids are set if any.
     *
     * @param objs
     * @throws DBException
     */
    <T> void insert(Collection<T> objs) throws DBException;

    /**
     * Begins a generic insert query into the given table.
     *
     * @param table
     * @return
     * @throws DBException
     */
    IGenericInsertQuery insert(String table) throws DBException;
}
