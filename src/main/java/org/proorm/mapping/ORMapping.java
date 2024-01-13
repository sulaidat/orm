package org.proorm.mapping;

import org.proorm.exception.DBException;

import java.util.ArrayList;
import java.util.List;
public class ORMapping {

    private Class<?> clazz;
    private String schema;
    private String tableName;
    private List<ColumnMapping> columnMappings;
    private CaseInsensitiveMap<ColumnMapping> columnMappingsIndexedByProperty;
    private CaseInsensitiveMap<ColumnMapping> columnMappingsIndexedByColumn;
    private List<ColumnMapping> idColumnMappings;
    private Boolean hasId;

    public ORMapping() {
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }


    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnMapping> getColumnMappings() {
        return columnMappings;
    }

    public void setColumnMappings(List<ColumnMapping> columnMappings) {
        this.columnMappings = columnMappings;
    }

    public ColumnMapping getColumnMappingByProperty(String property) {
        if (columnMappingsIndexedByProperty == null) {
            columnMappingsIndexedByProperty = createColumnMappingsIndexedByProperty();
        }
        return columnMappingsIndexedByProperty.get(property);
    }

    protected CaseInsensitiveMap<ColumnMapping> createColumnMappingsIndexedByProperty() {
        CaseInsensitiveMap<ColumnMapping> mappings = new CaseInsensitiveMap<>();
        for (ColumnMapping mapping : getColumnMappings()) {
            mappings.put(mapping.getPropertyDescriptor().getName(), mapping);
        }
        return mappings;
    }

    public ColumnMapping getColumnMappingByColumn(String column) {
        if (columnMappingsIndexedByColumn == null) {
            columnMappingsIndexedByColumn = createColumnMappingsIndexedByColumn();
        }
        return columnMappingsIndexedByColumn.get(column);
    }

    protected CaseInsensitiveMap<ColumnMapping> createColumnMappingsIndexedByColumn() {
        CaseInsensitiveMap<ColumnMapping> mappings = new CaseInsensitiveMap<>();
        for (ColumnMapping mapping : getColumnMappings()) {
            mappings.put(mapping.getName(), mapping);
        }
        return mappings;
    }

    public List<ColumnMapping> getIdColumnMappings() {
        if (idColumnMappings == null) {
            idColumnMappings = new ArrayList<>();
            for (ColumnMapping mapping : getColumnMappings()) {
                if (mapping.isId()) {
                    idColumnMappings.add( mapping);
                    break;
                }
            }
            if (idColumnMappings.isEmpty())
                throw new RuntimeException("No ID column defined in class " + getClazz().getName());
        }
        return idColumnMappings;
    }

    public ColumnMapping getIdColumnMapping() {
        if (getIdColumnMappings().size() > 1) throw new DBException("More than one ID column.");
        return getIdColumnMappings().get(0);
    }

    public Boolean hasId() {
        if (hasId == null) {
            hasId = getColumnMappings().stream().anyMatch(ColumnMapping::isId);
        }
        return hasId;
    }
}
