package org.proorm.query.generic;

import org.proorm.IQueryTarget;
import org.proorm.exception.DBException;
import org.proorm.query.AbstractQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericInsertQuery extends AbstractQuery implements IGenericInsertQuery {

    private String schema;
    private String table;
    private String generatedId;
    private List<Map<String,Object>> values = new ArrayList<>();

    public GenericInsertQuery(IQueryTarget target) {
        super(target);
    }

//    @Override
//    public IGenericInsertQuery schema(String schema) {
//        this.schema = schema;
//        return this;
//    }

    @Override
    public IGenericInsertQuery table(String table) {
        this.table = table;
        return this;
    }

    @Override
    public IGenericInsertQuery generatedId(String column) {
        generatedId = column;
        return this;
    }

    @Override
    public IGenericInsertQuery addOne(Map<String,Object> values) {
        this.values.add(values);
        return this;
    }

    @Override
    public IGenericInsertQuery addMany(List<Map<String,Object>> values) {
        this.values.addAll(values);
        return this;
    }

    @Override
    public void execute() throws DBException {
        if (values.isEmpty()) return;

        // Columns :
        List<String> columns = new ArrayList<>(values.get(0).keySet());

        // SQL :
        String sql = getQueryTarget().getConfig().getDialect().sqlForInsert(table, columns);

        // Parameters :
        List<List<Object>> params = new ArrayList<>();
        for (Map<String,Object> val : values) {
            List<Object> curParams = new ArrayList<>();
            for (String col : columns) {
                curParams.add(val.get(col));
            }
            params.add(curParams);
        }

        // Execute query :
        List<Long> generatedIds = getQueryTarget().executeUpdate(sql, params, generatedId);
        if (generatedId != null) {
            for (int i = 0; i < generatedIds.size(); i++) {
                values.get(i).put(generatedId, generatedIds.get(i));
            }
        }
    }
}
