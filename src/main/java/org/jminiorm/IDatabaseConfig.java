package org.jminiorm;

import org.jminiorm.dialect.ISQLDialect;
import org.jminiorm.executor.IStatementExecutor;
import org.jminiorm.mapping.provider.IORMappingProvider;
import org.jminiorm.mapping.type.IJDBCTypeMapper;

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
