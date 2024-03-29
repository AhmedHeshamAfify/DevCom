package com.devcom.configuration;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .apiInfo(metaData())
          .select()                               
          .apis(RequestHandlerSelectors.basePackage("com.devcom.controllers"))           
          .paths(PathSelectors.any())                          
          .build();                                           
    }
    
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
          .addResourceLocations("classpath:/META-INF/resources/");
     
        registry.addResourceHandler("/webjars/**")
          .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("DEVCOM REST API DOCUMENTATION")
                .description("API DOCUMENT FOR DEVELOPERS")
                .contact(new Contact("DEVCOM",
                        "https://www.devcom.com",
                        "devcom@gmail.com"))
                .license("MUM")
                .licenseUrl("https://github.com/JonkiPro/REST-Web-Services/blob/master/LICENSE")
                .version("1.0")
                .build();
    }
}