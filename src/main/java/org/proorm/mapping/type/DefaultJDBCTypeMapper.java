package org.proorm.mapping.type;

import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DefaultJDBCTypeMapper implements IJDBCTypeMapper {

    @Override
    public Class<?> getJavaType(int jdbcType) {
        switch (jdbcType) {
        case Types.BIGINT:
            return Long.class;
        case Types.INTEGER:
            return Integer.class;
        case Types.SMALLINT:
        case Types.TINYINT:
            return Short.class;
        case Types.TIMESTAMP:
        case Types.TIMESTAMP_WITH_TIMEZONE:
            return LocalDateTime.class;
        case Types.DATE:
            return LocalDate.class;
        default:
            return null;
        }
    }

}
