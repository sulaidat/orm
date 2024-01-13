package org.proorm.dialect;

import org.proorm.mapping.ORMapping;

import java.util.List;

public interface ISQLDialect {

    String sqlForDelete(String table, List<String> idColumns);

    String sqlForDeleteWhere(String table, String where);

    String sqlForInsert(String table, List<String> columns);

    String sqlForUpdate(String table, List<String> idColumns, List<String> columns);

    String sqlForSelect(String sql, Integer limit, Integer offset);

    String sqlForSelectWhere(List<String> columns, String table, String where);

    String sqlForSelect(List<String> columns, String table, String where, String groupBy, String having);

    String sqlForCreateTable(ORMapping mapping);

    String sqlForDropTable(ORMapping mapping);

    SetNullParameterMethod getSetNullParameterMethod();
}
