package org.proorm.result;

import org.proorm.IQueryTarget;
import org.proorm.exception.DBException;
import org.proorm.exception.UnexpectedNumberOfItemsException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractResult<T> implements IResult<T> {

    private IQueryTarget queryTarget;
    private String sql;
    private List<Object> params;

    public AbstractResult(IQueryTarget queryTarget, String sql, List<Object> params) {
        this.queryTarget = queryTarget;
        this.sql = sql;
        this.params = params;
    }

    @Override
    public T one() throws UnexpectedNumberOfItemsException, DBException {
        List<T> res = list();
        if (res.size() != 1) throw new UnexpectedNumberOfItemsException(res.size());
        else return res.get(0);
    }

    @Override
    public T first() throws DBException {
        List<T> res = list();
        if (res.isEmpty()) return null;
        else return res.get(0);
    }

    @Override
    public List<T> list() throws DBException {
        List<Map<String, Object>> rawResult = queryTarget.executeQuery(sql, params, typeMappings());

        Stream<Map<String, Object>> stream = rawResult.stream();
        Stream<T> stream2 = stream.map(this::castRow);
        List<T> res = stream2.collect(Collectors.toList());
        return res;
//        return rawResult.stream()
//                .map(this::castRow)
//                .collect(Collectors.toList());
    }

    protected IQueryTarget getQueryTarget() {
        return queryTarget;
    }

    /**
     * Cast the row into type T.
     *
     * @param row
     * @return
     */
    protected abstract T castRow(Map<String, Object> row);

    /**
     * Return the column name => Java type mappings to use when executing the query.
     *
     * @return
     */
    protected abstract Map<String, Class<?>> typeMappings();
}
