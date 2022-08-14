package com.cisdi.transaction.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Author: cxh
 * @Description:
 * @Date: 2022/8/7 23:28
 */
@Configuration
@MapperScan(basePackages = "com.cisdi.transaction.mapper.three",sqlSessionFactoryRef = "threeSqlSessionFactory")
public class ThreeDataSourceConfig {
    // 将这个对象放入Spring容器中
    @Bean(name = "threeDataSource")
    // 表示这个数据源是默认数据源
   // @Primary
    // 读取application.properties中的配置参数映射成为一个对象
    // prefix表示参数的前缀
    @ConfigurationProperties(prefix = "spring.datasource.db3")
    public DataSource getDateSource1()
    {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "threeSqlSessionFactory")
    // 表示这个数据源是默认数据源
   // @Primary
    // @Qualifier表示查找Spring容器中名字为oneDataSource的对象
    public SqlSessionFactory oneSqlSessionFactory(@Qualifier("threeDataSource") DataSource datasource)
            throws Exception
    {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/three/*.xml"));
        return bean.getObject();
    }

    @Bean("threeSqlSessionTemplate")
    // 表示这个数据源是默认数据源
    //@Primary
    public SqlSessionTemplate oneSqlSessionTemplate(
            @Qualifier("threeSqlSessionFactory") SqlSessionFactory sessionFactory)
    {
        return new SqlSessionTemplate(sessionFactory);
    }
}
