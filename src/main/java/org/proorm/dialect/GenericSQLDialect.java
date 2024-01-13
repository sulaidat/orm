package org.proorm.dialect;

import org.proorm.attributeconverter.AttributeConverterUtils;
import org.proorm.mapping.ColumnMapping;
import org.proorm.mapping.ORMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class GenericSQLDialect implements ISQLDialect {

    @Override
    public final String sqlForDelete(String table, List<String> idColumns) {
        return sqlForDeleteIdEscaped(identifier(table), identifiers(idColumns));
    }

    protected String sqlForDeleteIdEscaped(String table, List<String> idColumns) {
        return "DELETE FROM " + table + "\n" +
                "WHERE " + idColumns.stream().map(c -> c + " = ?").collect(Collectors.joining(" AND "));
    }

    public final String sqlForDeleteWhere(String table, String where) {
        return sqlForDeleteWhereIdEscaped(identifier(table), where);
    }

    protected String sqlForDeleteWhereIdEscaped(String table, String where) {
        return "DELETE FROM " + table + "\n" +
                "WHERE " + where;
    }

    @Override
    public final String sqlForInsert(String table, List<String> columns) {
        return sqlForInsertIdEscaped(identifier(table), identifiers(columns));
    }

    protected String sqlForInsertIdEscaped(String table, List<String> columns) {
        return "INSERT INTO " + table + " (" + String.join(", ", columns) + ")\n" +
                "VALUES (" + String.join(", ", questionMarks(columns.size())) + ")";
    }

    @Override
    public final String sqlForUpdate(String table, List<String> idColumns, List<String> columns) {
        return sqlForUpdateIdEscaped(identifier(table),
                identifiers(idColumns),
                identifiers(columns));
    }

    protected String sqlForUpdateIdEscaped(String table, List<String> idColumns, List<String> columns) {
        return "UPDATE " + table + "\n" +
                "SET " + String.join(" = ?, ", columns) + " = ?\n" +
                "WHERE " + idColumns.stream().map(c -> c + " = ?").collect(Collectors.joining(" AND "));
    }

    @Override
    public String sqlForSelect(String sql, Integer limit, Integer offset) {
        if (limit!= null)
            sql = sql + " LIMIT " + limit + " ";
        if (offset != null)
            sql = sql + " OFFSET " + offset + " ";
        return sql;
    }

    @Override
    public final String sqlForSelectWhere(List<String> columns, String table, String where) {
        return sqlForSelectIdEscaped(identifiers(columns), identifier(table), where);
    }
    @Override
    public final String sqlForSelect(List<String> columns, String table, String where, String groupBy, String having) {
        return sqlForSelectIdEscaped(identifiers(columns), identifier(table), where, groupBy, having);
    }

    protected String sqlForSelectIdEscaped(List<String> columns, String table, String where) {
        return "SELECT " + String.join(", ", columns) + "\n" +
                "FROM " + table + "\n" +
                (where == null ? "" : ("WHERE " + where + "\n"));
    }

    protected String sqlForSelectIdEscaped(List<String> columns, String table, String where, String groupBy, String having) {
        if(having!=null && groupBy==null){
            return sqlForSelectIdEscaped(columns,table,where);
        }
        return "SELECT " + String.join(", ", columns) + "\n" +
                "FROM " + table + "\n" +
                (where == null ? "" : ("WHERE " + where + "\n")) +
                (groupBy == null ? "" : ("GROUP BY " + groupBy + "\n")) +
                (having == null ? "" : ("HAVING " + having + "\n"));

    }

    @Override
    public String sqlForDropTable(ORMapping mapping) {
        return "DROP TABLE IF EXISTS " + identifier(mapping.getSchema(), mapping.getTableName());
    }

    @Override
    public String sqlForCreateTable(ORMapping mapping) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(identifier(mapping.getSchema(), mapping.getTableName())).append(" (");
        List<String> columns = new ArrayList<>();
        for (ColumnMapping columnMapping : mapping.getColumnMappings()) {
            columns.add(sqlForColumnDefinition(columnMapping));
        }
        sb.append(String.join(", ", columns));
        if (mapping.getColumnMappings().stream().anyMatch(ColumnMapping::isId)) {
            sb.append(", ");
            sb.append(sqlForPrimaryKey(mapping, mapping.getIdColumnMappings()));
        }
        sb.append(")");
        return sb.toString();
    }

    protected String sqlForColumnDefinition(ColumnMapping columnMapping) {
        StringBuilder sb = new StringBuilder();
        sb.append(identifier(columnMapping.getName())).append(" ");
        if (columnMapping.getColumnDefinition() != null)
            sb.append(columnMapping.getColumnDefinition());
        else {
            sb.append(sqlForColumnType(columnMapping)).append(" ");
            if (!columnMapping.isNullable() || columnMapping.isId())
                sb.append("NOT NULL ");
            else
                sb.append("NULL ");
            if (columnMapping.isGenerated())
                sb.append(sqlForAutoIncrement());
        }
        return sb.toString();
    }

    protected String sqlForColumnType(ColumnMapping columnMapping) {
        Class<?> javaType;
        if (columnMapping.getConverter() != null) {
            javaType = AttributeConverterUtils.getConverterDatabaseType(columnMapping.getConverter());
        } else
            javaType = columnMapping.getPropertyDescriptor().getPropertyType();
        Integer length = columnMapping.getLength();
        Integer scale = columnMapping.getScale();
        Integer precision = columnMapping.getPrecision();
        boolean generated = columnMapping.isGenerated();
        return sqlForColumnType(javaType, length, scale, precision, generated);
    }

    protected String sqlForColumnType(Class<?> javaType, Integer length, Integer scale, Integer precision,
                                      boolean generated) {
        if ((javaType == Short.class) || (javaType == short.class))
            return "SMALLINT";
        if ((javaType == Integer.class) || (javaType == int.class))
            return "INTEGER";
        if ((javaType == Long.class) || (javaType == long.class))
            return "BIGINT";
        if ((javaType == Float.class) || (javaType == float.class))
            return "REAL";
        if ((javaType == Double.class) || (javaType == double.class))
            return "DOUBLE";
        if ((javaType == Boolean.class) || (javaType == boolean.class))
            return "BOOLEAN";
        if (javaType == byte[].class)
            return "BINARY";
        if (javaType == String.class) {
            if (length == null)
                return "TEXT";
            else
                return "VARCHAR(" + length + ")";
        }
        if (javaType == Date.class || javaType == LocalDateTime.class)
            return "TIMESTAMP";
        if (javaType == LocalDate.class)
            return "DATE";
        if (javaType == BigDecimal.class)
            return "NUMERIC(" + precision + "," + scale + ")";
        throw new RuntimeException("No SQL type defined in dialect for java type " + javaType.getName());
    }

    protected String sqlForAutoIncrement() {
        return "AUTO_INCREMENT";
    }

    protected String sqlForPrimaryKey(ORMapping mapping, List<ColumnMapping> idColumnMappings) {
        return "CONSTRAINT " + identifier(mapping.getTableName() + "_pk") + " PRIMARY KEY ("
                + String.join(", ", identifiers(idColumnMappings.stream().map(ColumnMapping::getName).collect(Collectors.toList()))) + ")";
    }

    /**
     * Produced a list of question marks.
     */
    protected List<String> questionMarks(int number) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < number; i++)
            res.add("?");
        return res;
    }

    /**
     * Escapes and quotes the identifiers.
     */
    protected List<String> identifiers(List<String> identifiers) {
        List<String> result = new ArrayList<>();
        for (String id : identifiers) {
            result.add(identifier(id));
        }
        return result;
    }

    /**
     * Escapes and quotes the table identifier.
     */
    protected String identifier(String schema, String identifier) {
        return schemaPrefix(quoteIdentifier(schema)) + quoteIdentifier(identifier);
    }

    protected String schemaPrefix(String schema) {
        return (schema == null || schema.isEmpty()) ? "" : schema + ".";
    }

    /**
     * Escapes and quotes the identifier.
     */
    protected String identifier(String identifier, boolean acceptNone) {
        if (acceptNone && (identifier == null || identifier.isEmpty())) return identifier;
        return quoteIdentifier(identifier);
    }

    /**
     * Escapes and quotes the identifier.
     */
    protected String identifier(String identifier) {
        return identifier(identifier, false);
    }

    /**
     * Quotes the identifier. Does nothing by default, so that you can leave identifiers unquoted in your statements as
     * well without having case-sensitivity problems with some databases.
     */
    protected String quoteIdentifier(String identifier) {
        return identifier;
    }

    @Override
    public SetNullParameterMethod getSetNullParameterMethod() {
        return SetNullParameterMethod.SETOBJECT;
    }

}
