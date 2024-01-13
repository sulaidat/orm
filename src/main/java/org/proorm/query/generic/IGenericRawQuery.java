package org.proorm.query.generic;

import org.proorm.exception.DBException;

public interface IGenericRawQuery extends IGenericQuery {

    /**
     * Sets the raw SQL string.
     *
     * @param sql
     */
    IGenericRawQuery sql(String sql, Object... params);

    /**
     * Executes the query.
     *
     * @throws DBException
     */
    void execute() throws DBException;

}
