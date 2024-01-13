package org.proorm.query.orm;

import org.proorm.query.IQuery;

public interface IORMQuery<T> extends IQuery {

    IORMQuery<T> forClass(Class<T> clazz);

}
