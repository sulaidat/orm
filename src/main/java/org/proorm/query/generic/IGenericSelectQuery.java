package org.proorm.query.generic;

import org.proorm.result.map.IMapResult;
import org.proorm.result.object.IObjectResult;

public interface IGenericSelectQuery extends IGenericQuery {

    IGenericSelectQuery sql(String sql, Object... params);

    IMapResult<Object> toMap();

    <T> IObjectResult<T> toObject(Class<T> clazz);

}
