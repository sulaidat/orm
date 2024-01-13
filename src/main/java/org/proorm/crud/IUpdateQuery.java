package org.proorm.crud;

import org.proorm.exception.DBException;
import org.proorm.query.generic.IGenericUpdateQuery;
import org.proorm.query.orm.IORMUpdateQuery;

import java.util.Collection;

public interface IUpdateQuery {
    <T> IORMUpdateQuery<T> update(Class<T> clazz);

    <T> void update(T obj) throws DBException;

    <T> void update(Collection<T> objs) throws DBException;

    IGenericUpdateQuery update(String table) throws DBException;
}
