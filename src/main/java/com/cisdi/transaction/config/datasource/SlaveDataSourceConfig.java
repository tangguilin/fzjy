package com.cisdi.transaction.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
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

/**从数据源配置
 * @Author: cxh
 * @Description:
 * @Date: 2022/8/1 23:29
 */
@Configuration
@MapperScan(basePackages = "com.cisdi.transaction.mapper.slave",sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataSourceConfig {
    // 将这个对象放入Spring容器中
    @Bean(name = "slaveDataSource")
    // 表示这个数据源是默认数据源
   // @Primary
    // 读取application.properties中的配置参数映射成为一个对象
    // prefix表示参数的前缀
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource getDateSource1()
    {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "slaveSqlSessionFactory")
    // 表示这个数据源是默认数据源
    //@Primary
    // @Qualifier表示查找Spring容器中名字为oneDataSource的对象
    public SqlSessionFactory oneSqlSessionFactory(@Qualifier("slaveDataSource") DataSource datasource)
            throws Exception
    {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/slave/*.xml"));
        return bean.getObject();
    }

    @Bean("twoSqlSessionTemplate")
    // 表示这个数据源是默认数据源
   // @Primary
    public SqlSessionTemplate oneSqlSessionTemplate(
            @Qualifier("slaveSqlSessionFactory") SqlSessionFactory sessionFactory)
    {
        return new SqlSessionTemplate(sessionFactory);
    }
}
