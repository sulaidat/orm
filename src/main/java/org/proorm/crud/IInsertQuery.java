package org.proorm.crud;

import org.proorm.exception.DBException;
import org.proorm.query.generic.IGenericInsertQuery;
import org.proorm.query.orm.IORMInsertQuery;

import java.util.Collection;

public interface IInsertQuery {

    <T> IORMInsertQuery<T> insert(Class<T> clazz);

    <T> void insert(T obj) throws DBException;

    <T> void insert(Collection<T> objs) throws DBException;

    IGenericInsertQuery insert(String table) throws DBException;
}
