package com.devcom.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages= {"com.devcom.*"})
@PropertySource(value = { "classpath:application.properties" })
public class SpringConfiguration {

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
	    PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
	    configurer.setIgnoreUnresolvablePlaceholders(true);
	    configurer.setIgnoreResourceNotFound(true);
	    return configurer;
	}
}
