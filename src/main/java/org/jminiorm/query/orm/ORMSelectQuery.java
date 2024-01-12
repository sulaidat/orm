package org.jminiorm.query.orm;

import org.jminiorm.IQueryTarget;
import org.jminiorm.exception.DBException;
import org.jminiorm.exception.UnexpectedNumberOfItemsException;
import org.jminiorm.mapping.ColumnMapping;
import org.jminiorm.resultset.IObjectResultSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ORMSelectQuery<T> extends AbstractORMQuery<T> implements IORMSelectQuery<T> {

    private String where;
    private List<Object> params = new ArrayList<>();
    private String groupBy;
    private String having;

    public ORMSelectQuery(IQueryTarget target) {
        super(target);
    }

    @Override
    public IORMSelectQuery<T> forClass(Class<T> clazz) {
        return (IORMSelectQuery<T>) super.forClass(clazz);
    }

    @Override
    public IORMSelectQuery<T> where(String where, Object... params) {
        this.where = where;
        this.params = Arrays.asList(params);
        return this;
    }

    @Override
    public IORMSelectQuery<T> groupBy(String groupBy) {
        return this;
    }

    @Override
    public IORMSelectQuery<T> having(String having, Object... params) {
        return this;
    }

    @Override
    public IORMSelectQuery<T> id(Object id) {
        // Sets the parameters as the id :
        params = new ArrayList<>();
        params.add(id);

        // Sets the where clause for the id column :
        where = getMapping().getIdColumnMapping().getColumn() + " = ?";

        return this;
    }

    @Override
    public T one() throws UnexpectedNumberOfItemsException, DBException {
        IObjectResultSet<T> res = getResultSet();
        return res.one();
//        return getResultSet().one();
    }

    @Override
    public T first() throws DBException {
        return getResultSet().first();
    }

    @Override
    public List<T> list() throws DBException {
        return getResultSet().list();
    }

    /**
     * Returns the result set.
     *
     * @return
     */
    protected IObjectResultSet<T> getResultSet() {
        return getQueryTarget().select(buildSQL(), params.toArray()).toObject(getTargetClass());
    }

    /**
     * Builds the SQL select to get the rows from the database.
     *
     * @return
     */
    protected String buildSQL() {
        // The table to select from :
        String table = getMapping().getTable();

        // The columns to put in the select list :
        List<String> columns = new ArrayList<>();
        for (ColumnMapping columnMapping : getMapping().getColumnMappings()) {
            columns.add(columnMapping.getColumn());
        }

        // Generate sql :
        return getQueryTarget().getConfig().getDialect().sqlForSelect(getMapping().getSchema(), columns, table, where
                , null);
    }

}
