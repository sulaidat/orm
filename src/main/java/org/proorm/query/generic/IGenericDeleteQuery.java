package org.proorm.query.generic;

import org.proorm.exception.DBException;

import java.util.List;

public interface IGenericDeleteQuery extends IGenericQuery {
    IGenericDeleteQuery schema(String schema);

    IGenericDeleteQuery table(String table);

    IGenericDeleteQuery idColumn(String idColumn);

    IGenericDeleteQuery idColumns(String... idColumns);

    IGenericDeleteQuery addOne(Object... id);

    IGenericDeleteQuery addMany(List<Object> ids);

    IGenericDeleteQuery where(String sql, Object... params);

    void execute() throws DBException;

}
