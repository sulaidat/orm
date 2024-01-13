package org.jminiorm.query.generic;

import org.jminiorm.IQueryTarget;
import org.jminiorm.query.AbstractQuery;
import org.jminiorm.result.map.IMapResult;
import org.jminiorm.result.map.MapResult;
import org.jminiorm.result.object.IObjectResult;
import org.jminiorm.result.object.ObjectResult;

import java.util.Arrays;
import java.util.List;

public class GenericSelectQuery extends AbstractQuery implements IGenericSelectQuery {

    private String sql;
    private List<Object> params;

    public GenericSelectQuery(IQueryTarget target) {
        super(target);
    }

    @Override
    public IGenericSelectQuery sql(String sql, Object... params) {
        this.sql = sql;
        this.params = Arrays.asList(params);
        return this;
    }

    @Override
    public IMapResult<Object> toMap() {
        return new MapResult<Object>(getQueryTarget(), getSQL(), params, Object.class);
    }

    @Override
    public <T> IObjectResult<T> toObject(Class<T> clazz) {
        return new ObjectResult<>(getQueryTarget(), getSQL(), params, clazz);
    }

    protected String getSQL() {
        return getQueryTarget().getConfig().getDialect().sqlForSelect(sql, null, null);
    }

}
