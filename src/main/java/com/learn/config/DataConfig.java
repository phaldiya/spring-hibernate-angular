package com.learn.config;

import com.learn.domain.Person;
import com.learn.repository.CustomJpaRepositoryFactoryBean;
import com.learn.security.SpringSecurityAuditorAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.**.repository", repositoryFactoryBeanClass = CustomJpaRepositoryFactoryBean.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@PropertySources(value = { @PropertySource(value = "classpath:/properties/${spring.profiles.active}.properties", ignoreResourceNotFound = true) })
public class DataConfig {
    @Autowired
    private Environment environment;

    @Bean
    public AuditorAware<Person> auditorAware() {
        return new SpringSecurityAuditorAware();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.user"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.learn");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setJpaProperties(new Properties() {
            {
                setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
                setProperty("hibernate.default_schema", environment.getProperty("hibernate.default_schema"));
                setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
                setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
                setProperty("hibernate.ejb.naming_strategy", "com.learn.repository.CustomNamingStrategy");


                setProperty("hibernate.search.default.directory_provider", "filesystem");
                setProperty("hibernate.search.default.indexBase", environment.getProperty("hibernate.search.indexbase"));

                setProperty("hibernate.cache.use_second_level_cache", "true");
                setProperty("hibernate.cache.use_query_cache", "true");
                setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");

                setProperty("jadira.usertype.autoRegisterUserTypes", "true");
                setProperty("jadira.usertype.databaseZone", "America/Los_Angeles");
            }
        });

        String jdbcDriver = environment.getProperty("jdbc.driver");
        if (jdbcDriver != null && jdbcDriver.compareToIgnoreCase("org.h2.Driver") == 0 ) {
            factory.getJpaPropertyMap().put("hibernate.hbm2ddl.import_files", "/data/core.base.sql, data/base.sql, data/data.sql");
        }

        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }
}
