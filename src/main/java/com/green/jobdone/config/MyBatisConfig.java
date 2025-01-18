
package com.green.jobdone.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.green.jobdone.user") // Mapper 인터페이스 위치
@MapperScan(basePackages = "com.green.jobdone.mail")
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        // TypeHandler 등록
        factoryBean.setTypeHandlersPackage("com.green.jobdone.config.handler"); // TypeHandler 패키지 경로
        return factoryBean.getObject();
    }
}