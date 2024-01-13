package org.jminiorm.query.orm;

import org.jminiorm.resultset.IObjectResultSet;

/**
 * Represents a select query that returns objects of a JPA annotated class. The select and from clauses are infered from
 * the JPA annotations.
 *
 * @param <T>
 */
public interface IORMSelectQuery<T> extends IORMQuery<T>, IObjectResultSet<T> {

    /**
     * Sets the target class
     * @param clazz
     * @return
     */
    @Override
    IORMSelectQuery<T> forClass(Class<T> clazz);

    /**
     * Sets the where clause and returns this.
     *
     * @param where
     * @param params
     * @return this
     */
    IORMSelectQuery<T> where(String where, Object... params);

    /**
     * Sets the group by clause and returns this.
     * @param groupBy
     * @param params
     * @return
     */
    IORMSelectQuery<T> groupBy(String groupBy, Object... params);

    /**
     * Sets the having clause and returns this.
     * @param having
     * @param params
     * @return
     */
    IORMSelectQuery<T> having(String having, Object... params);

    /**
     * Sets the id of the object to return.
     *
     * @param id
     * @return this
     */
    IORMSelectQuery<T> id(Object id);

    IORMSelectQuery<T> groupBy(String groupBy);

    IORMSelectQuery<T> having(String having);

}
