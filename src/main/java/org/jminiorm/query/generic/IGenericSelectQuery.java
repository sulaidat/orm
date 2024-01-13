package org.jminiorm.query.generic;

import org.jminiorm.result.map.IMapResult;
import org.jminiorm.result.object.IObjectResult;

/**
 * Represents a generic select query, that is, one that may return objects of any type (not necessarily a JPA annotated
 * class). Since the return type may be anything, the from and select clauses cannot be infered from JPA annotations and
 * the whole SQL statement must be specified.
 */
public interface IGenericSelectQuery extends IGenericQuery {

    /**
     * Sets the whole select statement and returns this.
     *
     * @param sql
     * @param params
     * @return this
     */
    IGenericSelectQuery sql(String sql, Object... params);

    /**
     * Returns the result set as Maps String Object.
     *
     * @return
     */
    IMapResult<Object> toMap();

    /**
     * Returns the result set as objects of type T.
     *
     * @return
     */
    <T> IObjectResult<T> toObject(Class<T> clazz);

}
