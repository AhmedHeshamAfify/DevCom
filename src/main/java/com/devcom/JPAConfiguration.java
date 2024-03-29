package com.devcom;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.devcom" })
@PropertySource(value = { "classpath:application.properties" })
@EnableJpaRepositories("com.devcom.repositories")
public class JPAConfiguration {
	
	    @Autowired
	    private Environment environment;
	    
	    @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        
	        dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driverClassName"));
	        dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
	        dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
	        dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
	        return dataSource;
	    }
	    
	    @Bean
		   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		      em.setDataSource(dataSource());
		      em.setPackagesToScan(new String[] { "com.devcom.models" });
		 
		      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		      em.setJpaVendorAdapter(vendorAdapter);
		      em.setJpaProperties(additionalProperties());
		 
		      return em;
		   }
	
		   @Bean
		   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
		      JpaTransactionManager transactionManager = new JpaTransactionManager();
		      transactionManager.setEntityManagerFactory(emf);
		 
		      return transactionManager;
		   }
		 
		   @Bean
		   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		      return new PersistenceExceptionTranslationPostProcessor();
		   }
		 
		   Properties additionalProperties() {
		      Properties properties = new Properties();
		      properties.setProperty("hibernate.hbm2ddl.auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
		      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		      
//		      properties.setProperty("hibernate.search.jmx_enabled", "true");
		      properties.setProperty("hibernate.search.default.directory_provider", "filesystem");
//		      properties.setProperty("hibernate.search.generate_statistics", "true");
//		      properties.setProperty("hibernate.search.lucene_version", "LUCENE_CURRENT");
		      properties.setProperty("hibernate.search.default.indexBase", "./x-app/usr/lucene/indexes");
		      properties.setProperty("hibernate.search.Rules.directory_provider", "local-heap");
		      properties.setProperty("hibernate.search.Actions.directory_provider", "/com.acme.hibernate.CustomDirectoryProvider");
		      properties.setProperty("hibernate.search.indexing_strategy", "manual");
		      properties.setProperty("hibernate.search.default.indexmanager", "near-real-time");
//		      <property name="default.indexmanager">near-real-time</property>
//		      hibernate.search.indexing_strategy = manual

		      return properties;
		   }
}
