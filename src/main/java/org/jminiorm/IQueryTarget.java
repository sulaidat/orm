package org.jminiorm;

import org.jminiorm.crud.IInsertQuery;
import org.jminiorm.crud.ISelectQuery;
import org.jminiorm.crud.ITableQuery;
import org.jminiorm.crud.IUpdateQuery;
import org.jminiorm.exception.DBException;
import org.jminiorm.query.generic.IGenericDeleteQuery;
import org.jminiorm.query.generic.IGenericInsertQuery;
import org.jminiorm.query.generic.IGenericSelectQuery;
import org.jminiorm.query.generic.IGenericUpdateQuery;
import org.jminiorm.query.orm.IORMDeleteQuery;
import org.jminiorm.query.orm.IORMInsertQuery;
import org.jminiorm.query.orm.IORMSelectQuery;
import org.jminiorm.query.orm.IORMUpdateQuery;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IQueryTarget extends ISelectQuery, IInsertQuery, IUpdateQuery, ITableQuery {

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
