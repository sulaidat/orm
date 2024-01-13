package org.proorm.query.generic;

import org.proorm.exception.DBException;

import java.util.List;
import java.util.Map;

public interface IGenericInsertQuery extends IGenericQuery {

//    IGenericInsertQuery schema(String schema);

    IGenericInsertQuery table(String table);

    IGenericInsertQuery generatedId(String column);

    IGenericInsertQuery addOne(Map<String, Object> values);

    IGenericInsertQuery addMany(List<Map<String, Object>> values);

    void execute() throws DBException;

}
