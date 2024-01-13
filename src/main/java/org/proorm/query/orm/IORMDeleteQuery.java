package org.proorm.query.orm;

import org.proorm.exception.DBException;

import java.util.Collection;

public interface IORMDeleteQuery<T> extends IORMQuery<T> {

    @Override
    IORMDeleteQuery<T> forClass(Class<T> clazz);

    IORMDeleteQuery<T> id(Object... id);

    IORMDeleteQuery<T> addOne(T obj);

    IORMDeleteQuery<T> addMany(Collection<T> objs);

    IORMDeleteQuery<T> where(String where, Object... params);

    void execute() throws DBException;

}
