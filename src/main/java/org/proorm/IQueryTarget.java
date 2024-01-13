package org.proorm;

import org.proorm.crud.*;
import org.proorm.exception.DBException;

import java.sql.Connection;

public interface IQueryTarget extends ISelectQuery, IInsertQuery, IUpdateQuery, ITableQuery, IDeleteQuery {

    Connection getConnection() throws DBException;

    /**
     * This should ALWAYS be called after a connection is no longer in use. For a database, this closes the connection
     * and returns it to the pool. For a transaction, does nothing (the connection is closed when the transaction is).
     *
     * @param con
     * @throws DBException
     */
    void releaseConnection(Connection con) throws DBException;

    /**
     * Returns the config.
     *
     * @return
     */
    IDatabaseConfig getConfig();

}
