package org.proorm.query.generic;

import org.proorm.exception.DBException;

import java.util.List;
import java.util.Map;

public interface IGenericUpdateQuery extends IGenericQuery {

    IGenericUpdateQuery schema(String schema);

    IGenericUpdateQuery table(String table);

    IGenericUpdateQuery idColumn(String idColumn);

    IGenericUpdateQuery idColumns(String... idColumns);

    IGenericUpdateQuery addOne(Map<String, Object> values);

    IGenericUpdateQuery addMany(List<Map<String, Object>> values);

    void execute() throws DBException;

}
