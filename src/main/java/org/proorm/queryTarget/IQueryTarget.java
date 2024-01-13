package org.proorm.queryTarget;

import org.proorm.crud.*;
import org.proorm.exception.DBException;

import java.sql.Connection;

public interface IQueryTarget extends ISelectQuery, IInsertQuery, IUpdateQuery, ITableQuery, IDeleteQuery {

    Connection getConnection() throws DBException;

    void releaseConnection(Connection con) throws DBException;

    IDatabaseConfig getConfig();

}
