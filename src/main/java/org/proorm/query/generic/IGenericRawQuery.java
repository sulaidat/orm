package org.proorm.query.generic;

import org.proorm.exception.DBException;

public interface IGenericRawQuery extends IGenericQuery {

    IGenericRawQuery sql(String sql, Object... params);

    void execute() throws DBException;

}
