package org.proorm.mapping.type;

public interface IJDBCTypeMapper {

    Class<?> getJavaType(int jdbcType);

}
