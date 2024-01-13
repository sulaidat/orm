package org.proorm.query.orm;

import org.proorm.result.object.IObjectResult;

public interface IORMSelectQuery<T> extends IORMQuery<T>, IObjectResult<T> {

    @Override
    IORMSelectQuery<T> forClass(Class<T> clazz);

    IORMSelectQuery<T> where(String where, Object... params);

    IORMSelectQuery<T> groupBy(String groupBy, Object... params);

    IORMSelectQuery<T> having(String having, Object... params);

    IORMSelectQuery<T> id(Object id);

    IORMSelectQuery<T> groupBy(String groupBy);

    IORMSelectQuery<T> having(String having);

}
