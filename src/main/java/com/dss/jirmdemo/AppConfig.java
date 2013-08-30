package com.dss.jirmdemo;

import co.jirm.spring.SpringJirmFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public DriverManagerDataSource dataSource() {
        final DriverManagerDataSource driverManagerDataSource =
                new DriverManagerDataSource(env.getRequiredProperty("jdbc.url"),
                                            env.getRequiredProperty("jdbc.username"),
                                            env.getRequiredProperty("jdbc.password"));
        driverManagerDataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        return driverManagerDataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SpringJirmFactory jirmFactory() {
        return new SpringJirmFactory(dataSource());
    }

}
