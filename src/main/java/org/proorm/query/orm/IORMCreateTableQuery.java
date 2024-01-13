package org.proorm.query.orm;

import org.proorm.exception.DBException;

import java.sql.SQLException;

public interface IORMCreateTableQuery<T> extends IORMQuery<T> {

    @Override
    IORMCreateTableQuery<T> forClass(Class<T> clazz);

    void execute() throws DBException;

}
