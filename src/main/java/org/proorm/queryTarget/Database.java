package org.proorm.queryTarget;

import org.proorm.exception.DBException;

import java.sql.Connection;
import java.sql.SQLException;

public class Database extends QueryTargetFactory implements IDatabase {

    private IDatabaseConfig config;

    public Database(IDatabaseConfig config) {
        this.config = config;
    }

    @Override
    public IDatabaseConfig getConfig() {
        return config;
    }

    @Override
    public Connection getConnection() throws DBException {
        try {
            return getConfig().getDataSource().getConnection();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void releaseConnection(Connection con) throws DBException {
        try {
            con.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

//    @Override
//    public ITransaction createTransaction() throws DBException {
//        return new Transaction(this);
//    }

}
