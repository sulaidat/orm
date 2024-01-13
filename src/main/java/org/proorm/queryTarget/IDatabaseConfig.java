package org.proorm.queryTarget;

import org.proorm.dialect.ISQLDialect;
import org.proorm.executor.IStatementExecutor;
import org.proorm.mapping.provider.IORMappingProvider;
import org.proorm.mapping.type.IJDBCTypeMapper;

import javax.sql.DataSource;

public interface IDatabaseConfig {

    DataSource getDataSource();

    IDatabaseConfig dataSource(DataSource dataSource);

    ISQLDialect getDialect();

    IDatabaseConfig dialect(ISQLDialect dialect);

    IORMappingProvider getORMappingProvider();

    IDatabaseConfig ORMappingProvider(IORMappingProvider mappingProvider);

    IJDBCTypeMapper getJDBCTypeMapper();

    IDatabaseConfig JDBCTypeMapper(IJDBCTypeMapper mapper);

    IStatementExecutor getStatementExecutor();

    IDatabaseConfig statementExecutor(IStatementExecutor executor);
    IDatabaseConfig build();

}
