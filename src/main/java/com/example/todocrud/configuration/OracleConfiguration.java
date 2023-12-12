package com.example.todocrud.configuration;

// import org.springframework.stereotype.Component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.data.jdbc.support.ConnectionUsernameProvider;
import javax.sql.DataSource;
import java.sql.SQLException;
import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.data.jdbc.support.oracle.ProxyDataSource;

@Configuration
@ConfigurationProperties("spring")
class OracleConfiguration {
    @Autowired
    private ConnectionUsernameProvider contextProvider;
    private OracleDataSource dataSourceInternal;
    String username;
    String password;
    String url;
    @Bean
    public OracleDataSource oracleDataSource() throws SQLException {
        dataSourceInternal.setUser(username);
        dataSourceInternal.setPassword(password);
        dataSourceInternal.setURL(url);
        dataSourceInternal.setImplicitCachingEnabled(true);
        // dataSourceInternal.setFastConnectionFailoverEnabled(true);
        return dataSourceInternal;
    }
    
    @Bean
    @Primary
    public DataSource dataSource() throws SQLException {
        ProxyDataSource dataSource = new ProxyDataSource(dataSourceInternal, contextProvider);
        return dataSource;
    }
}
