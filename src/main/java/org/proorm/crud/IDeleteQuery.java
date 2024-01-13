package org.proorm.crud;

import org.proorm.exception.DBException;
import org.proorm.query.generic.IGenericDeleteQuery;
import org.proorm.query.orm.IORMDeleteQuery;

import java.util.Collection;

public interface IDeleteQuery {
    <T> IORMDeleteQuery<T> delete(Class<T> clazz);

    <T> void delete(Class<T> clazz, Object id) throws DBException;

    <T> void delete(T obj) throws DBException;

    <T> void delete(Collection<T> objs) throws DBException;

    IGenericDeleteQuery delete(String table) throws DBException;
}
