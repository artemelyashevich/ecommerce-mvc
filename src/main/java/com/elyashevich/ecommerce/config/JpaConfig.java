package com.elyashevich.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableJpaRepositories(basePackages = "com.elyashevich.ecommerce.repository")
@ComponentScan(basePackages = "com.elyashevich.ecommerce")
@PropertySources({
        @PropertySource("classpath:db.properties"),
        @PropertySource("classpath:hibernate.properties")
})
public class JpaConfig {

    @Value("${datasource.driver_class_name:com.mysql.cj.jdbc.Driver}")
    private String driverClassName;
    @Value("${datasource.url:jdbc:mysql:// localhost:3306/ ecommerce}")
    private String url;
    @Value("${datasource.username:admin}")
    private String username;
    @Value("${datasource.password:admin}")
    private String password;
    @Value("${hibernate.dialect:org.hibernate.dialect.MySQL8Dialect}")
    private String dialect;
    @Value("${hibernate.show_sql:true}")
    private String showSql;
    @Value("${hibernate_hbm2ddl_auto:true}")
    private String ddlAuto;

    @Bean
    public DataSource dataSource() {
        var dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        var factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(this.dataSource());
        factoryBean.setPackagesToScan("com.elyashevich.ecommerce");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(this.jpaProperties());
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Properties jpaProperties() {
        var properties = new Properties();
        properties.setProperty("hibernate.dialect", this.dialect);
        properties.setProperty("hibernate.show_sql", this.showSql);
        properties.setProperty("hibernate.hbm2ddl.auto", this.ddlAuto);
        return properties;
    }
}
