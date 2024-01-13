package org.proorm.executor;

import java.util.List;
import java.util.Map;

import org.proorm.queryTarget.IQueryTarget;
import org.proorm.exception.DBException;

public interface IStatementExecutor {
    List<Long> executeUpdate(IQueryTarget target, String sql, List<List<Object>> params, String generatedColumn)
            throws DBException;

    List<Map<String,Object>> executeSelect(IQueryTarget target, String sql, List<Object> params,
                                           Map<String,Class<?>> typeOverrides) throws DBException;

}
