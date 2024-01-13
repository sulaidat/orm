package org.proorm.query.orm;

import org.proorm.exception.DBException;

import java.util.Collection;

/**
 * Represents an update query for objects of a JPA annotated class.
 *
 * @param <T>
 */
public interface IORMUpdateQuery<T> extends IORMQuery<T> {

    IORMUpdateQuery<T> forClass(Class<T> clazz);

    IORMUpdateQuery<T> addOne(T obj);

    IORMUpdateQuery<T> addMany(Collection<T> objs);

    void execute() throws DBException;

}
