package org.proorm.queryTarget;

import org.proorm.dialect.GenericSQLDialect;
import org.proorm.dialect.ISQLDialect;
import org.proorm.executor.DefaultStatementExecutor;
import org.proorm.executor.IStatementExecutor;
import org.proorm.mapping.provider.IORMappingProvider;
import org.proorm.mapping.provider.JPAORMFactory;
import org.proorm.mapping.type.DefaultJDBCTypeMapper;
import org.proorm.mapping.type.IJDBCTypeMapper;

import javax.sql.DataSource;

public class DatabaseConfig implements IDatabaseConfig {

    private DataSource dataSource;
    private ISQLDialect dialect;
    private IORMappingProvider mappingProvider;
    private IJDBCTypeMapper typeMapper;
    private IStatementExecutor executor;

    private static DatabaseConfig instance;

    private DatabaseConfig(){};

    public static DatabaseConfig getInstance(){
        if(instance!= null){
            instance = new DatabaseConfig();
        }
        return instance;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public IDatabaseConfig dataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    @Override
    public ISQLDialect getDialect() {
        return dialect;
    }

    @Override
    public IDatabaseConfig dialect(ISQLDialect dialect) {
        this.dialect = dialect;
        return this;
    }

    @Override
    public IORMappingProvider getORMappingProvider() {
        return mappingProvider;
    }

    @Override
    public IDatabaseConfig ORMappingProvider(IORMappingProvider mappingProvider) {
        this.mappingProvider = mappingProvider;
        return this;
    }

    @Override
    public IJDBCTypeMapper getJDBCTypeMapper() {
        return typeMapper;
    }

    @Override
    public IDatabaseConfig JDBCTypeMapper(IJDBCTypeMapper typeMapper) {
        this.typeMapper = typeMapper;
        return this;
    }

    @Override
    public IStatementExecutor getStatementExecutor() {
        return executor;
    }

    @Override
    public IDatabaseConfig statementExecutor(IStatementExecutor executor) {
        this.executor = executor;
        return this;
    }

    @Override
    public IDatabaseConfig build() {
        if (dataSource == null)
            throw new RuntimeException("No data source provided.");

        if (dialect == null)
            dialect = new GenericSQLDialect();

        if (mappingProvider == null)
            mappingProvider = new JPAORMFactory();

        if (typeMapper == null)
            typeMapper = new DefaultJDBCTypeMapper();

        if (executor == null)
            executor = new DefaultStatementExecutor();

        return this;
    }
}