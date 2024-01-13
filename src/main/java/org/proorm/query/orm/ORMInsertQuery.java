package org.proorm.query.orm;

import org.proorm.queryTarget.IQueryTarget;
import org.proorm.exception.DBException;
import org.proorm.mapping.ColumnMapping;

import java.util.*;

public class ORMInsertQuery<T> extends AbstractORMQuery<T> implements IORMInsertQuery<T> {

    private List<T> columnTypes = new ArrayList<>();

    public ORMInsertQuery(IQueryTarget target) {
        super(target);
    }

    @Override
    public IORMInsertQuery<T> forClass(Class<T> clazz) {
        return (IORMInsertQuery<T>) super.forClass(clazz);
    }

    @Override
    public ORMInsertQuery<T> addOne(T obj) {
        columnTypes.add(obj);
        return this;
    }

    @Override
    public ORMInsertQuery<T> addMany(Collection<T> objs) {
        this.columnTypes.addAll(objs);
        return this;
    }

    @Override
    public void execute() throws DBException {

        if (!columnTypes.isEmpty()) {
            // Table name:
            String tableName = getMapping().getTableName();

            // Columns to insert, excluding generated columns:
            List<ColumnMapping> relevantColumnMappings = new ArrayList<>();
            for (ColumnMapping columnMapping : getMapping().getColumnMappings()) {
                if (columnMapping.isInsertable() && !columnMapping.isGenerated()) {
                    relevantColumnMappings.add(columnMapping);
                }
            }

            // <column, value> pair for each row to insert:
            List<Map<String, Object>> rows = new ArrayList<>();
            for (T type : columnTypes) {
                Map<String, Object> row = new HashMap<>();
                for (ColumnMapping columnMapping : relevantColumnMappings) {
                    row.put(columnMapping.getName(), columnMapping.getValue(type));
                }
                rows.add(row);
            }

            // Insert:
            ColumnMapping idColumnMapping = getMapping().hasId() ? getMapping().getIdColumnMapping() : null;
            getQueryTarget()
                    .insert(tableName)
                    .generatedId(
                            idColumnMapping != null && idColumnMapping.isGenerated()
                                    ? idColumnMapping.getName()
                                    : null)
                    .addMany(rows)
                    .execute();

            // Assign generated ids if any :
            if (idColumnMapping != null && idColumnMapping.isGenerated()) {
                for (int i = 0; i < columnTypes.size(); i++) {
                    Long key = (Long) rows.get(i).get(idColumnMapping.getName());
                    if (idColumnMapping.getPropertyDescriptor().getPropertyType() == Long.class)
                        idColumnMapping.writeProperty(columnTypes.get(i), key);
                    else
                        idColumnMapping.writeProperty(columnTypes.get(i), key.intValue());
                }
            }
        }
    }

}
