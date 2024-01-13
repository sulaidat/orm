package org.proorm.query.orm;

import org.proorm.exception.DBException;

import java.util.Collection;

public interface IORMInsertQuery<T> extends IORMQuery<T> {

    IORMInsertQuery<T> forClass(Class<T> clazz);

    IORMInsertQuery<T> addOne(T obj);

    IORMInsertQuery<T> addMany(Collection<T> objs);

    void execute() throws DBException;

}
