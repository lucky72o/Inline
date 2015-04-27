package org.inline.config.root;


import org.inline.init.TestDataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 *
 * Integration testing specific configuration - creates a in-memory datasource,
 * sets hibernate on create drop mode and inserts some test data on the database.
 *
 * This allows to clone the project repository and start a running application with the command
 *
 * mvn clean install tomcat7:run-war -Dspring.profiles.active=test
 *
 * Access http://localhost:8080/ and login with test123 / Password2, in order to see some test data,
 * or create a new user.
 *
 */
@Configuration
@Profile("test")
@EnableTransactionManagement
public class TestConfiguration {

    @Bean(initMethod = "init")
    public TestDataInitializer initTestData() {
        return new TestDataInitializer();
    }

    @Bean(name = "datasource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(org.hsqldb.jdbcDriver.class.getName());
        dataSource.setUrl("jdbc:hsqldb:mem:mydb");
        dataSource.setUsername("sa");
        dataSource.setPassword("jdbc:hsqldb:mem:mydb");
        return dataSource;
    }

    @Bean
    @Autowired
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("org.inline.daos");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    final Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
//        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));

        hibernateProperties.setProperty("hibernate.show_sql", "true");

        return hibernateProperties;
    }

}
