package org.proorm.query.orm;

import org.proorm.IQueryTarget;
import org.proorm.exception.DBException;

public class ORMCreateTableQuery<T> extends AbstractORMQuery<T> implements IORMCreateTableQuery<T> {

    public ORMCreateTableQuery(IQueryTarget target) {
        super(target);
    }

    @Override
    public IORMCreateTableQuery<T> forClass(Class<T> clazz) {
        return (IORMCreateTableQuery<T>) super.forClass(clazz);
    }

    @Override
    public void execute() throws DBException {
        String sql = getQueryTarget().getConfig().getDialect().sqlForCreateTable(getMapping());
        getQueryTarget().sql(sql);
    }
}
