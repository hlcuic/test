package com.hello.config;

import java.io.IOException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

//@Configuration
public class ConfigClass implements EnvironmentAware {

	private Environment environment;

	@Bean(name = "helloDataSource")
	public BasicDataSource createBasicDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty("driverClassName"));
		dataSource.setUrl(environment.getProperty("url"));
		dataSource.setUsername(environment.getProperty("username1"));
		dataSource.setPassword(environment.getProperty("password"));
		return dataSource;
	}

	@Bean(name = "helloSqlSessionFactoryBean")
	public SqlSessionFactory createSqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(createBasicDataSource());
		sqlSessionFactoryBean.setMapperLocations(getResource());
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public MapperScannerConfigurer createMapperScannerConfigurer() throws Exception {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.hello.dao");
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("helloSqlSessionFactoryBean");
		return mapperScannerConfigurer;
	}

	private Resource[] getResource() throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		String mapLocation = environment.getProperty("mapLocation");
		return resolver.getResources(mapLocation);
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
