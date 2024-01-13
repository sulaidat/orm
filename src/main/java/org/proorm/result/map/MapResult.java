package org.proorm.result.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.proorm.queryTarget.IQueryTarget;
import org.proorm.result.AbstractResult;

public class MapResult<V> extends AbstractResult<Map<String,V>> implements IMapResult<V> {

    private Class<V> type;

    public MapResult(IQueryTarget queryTarget, String sql, List<Object> params, Class<V> type) {
        super(queryTarget, sql, params);
        this.type = type;
    }

    @Override
    protected Map<String,V>
    castRow(Map<String,Object> row) {
        return (Map)row;
    }

    @Override
    protected Map<String,Class<?>> typeMappings() {
        Map<String,Class<?>> mappings = new HashMap<>();
        if (type != Object.class) mappings.put(null, type);
        return mappings;
    }
}
