package org.jminiorm.mapping.type;

public interface IJDBCTypeMapper {

    Class<?> getJavaType(int jdbcType);

}
