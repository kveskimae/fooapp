package com.foo.app;

import java.io.IOException;
import java.util.List;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.foo.app.controller.ControllerPackageMarker;

@Configuration
@ComponentScan(basePackageClasses={ControllerPackageMarker.class})
@Import({JpaConfig.class})
public class RestApiConfig extends WebMvcConfigurationSupport {
	
    @Autowired
    private ApplicationContext context;

	@Bean
    public DozerBeanMapperFactoryBean mapper() throws IOException {
        DozerBeanMapperFactoryBean mapper = new DozerBeanMapperFactoryBean();
        Resource[] mappingFiles = ((ResourcePatternResolver) context).getResources("classpath*:dozer/**/*.dzr.xml");
        mapper.setMappingFiles(mappingFiles);
        return mapper;
    }
	
	@Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
        addDefaultHttpMessageConverters(converters) ;
    }

    @Bean
    MappingJackson2HttpMessageConverter converter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // ISO-8601
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new
                MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(mapper);
        return mappingJackson2HttpMessageConverter;
    }
	
}