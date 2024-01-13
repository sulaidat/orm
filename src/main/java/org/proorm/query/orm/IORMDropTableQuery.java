package org.proorm.query.orm;

import org.proorm.exception.DBException;

import java.sql.SQLException;

public interface IORMDropTableQuery<T> extends IORMQuery<T> {

    @Override
    IORMDropTableQuery<T> forClass(Class<T> clazz);

    void execute() throws DBException;

}
