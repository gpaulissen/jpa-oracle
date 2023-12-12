package com.example.todocrud.configuration;

// import org.springframework.stereotype.Component;

import java.sql.SQLException;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.support.ConnectionUsernameProvider;
import org.springframework.data.jdbc.support.oracle.ProxyDataSource;
import org.springframework.util.Assert;

import com.example.todocrud.provider.SecurityContextHolderUserProvider;
    
@Configuration
@ConfigurationProperties("oracle")
public class OracleConfiguration {
    @Autowired
    private ConnectionUsernameProvider contextProvider; // = new SecurityContextHolderUserProvider();

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String url;

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Bean
    OracleDataSource oracleDataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();

        Assert.notNull(username, "Username must NOT be null");
        Assert.notNull(password, "Password must NOT be null");
        Assert.notNull(url, "URL must NOT be null");

        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL(url);
        dataSource.setImplicitCachingEnabled(true);
        // dataSource.setFastConnectionFailoverEnabled(true);

        return dataSource;
    }
    
    @Bean
    @Primary
    DataSource dataSource() throws SQLException {
        Assert.notNull(contextProvider, "Context provider must NOT be null");
        
        return new ProxyDataSource(oracleDataSource(), contextProvider);
    }
}
