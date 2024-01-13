package org.proorm.query.orm;

import org.proorm.exception.DBException;

import java.sql.SQLException;

/**
 * Represents a create table statement that creates the table for a JPA annotated class.
 */
public interface IORMDropTableQuery<T> extends IORMQuery<T> {

    @Override
    IORMDropTableQuery<T> forClass(Class<T> clazz);

    /**
     * Drops the table.
     *
     * @throws SQLException
     */
    void execute() throws DBException;

}
