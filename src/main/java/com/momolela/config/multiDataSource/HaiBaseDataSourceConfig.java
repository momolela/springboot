package com.momolela.config.multiDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "com.momolela.mapper.haibase", sqlSessionTemplateRef = "haiBaseSqlSessionTemplate")
public class HaiBaseDataSourceConfig {

    @Bean("haiBaseDataSource")
    @ConfigurationProperties(prefix = "multi.datasource.haibase")
    public DataSource haiBaseDataSource() {
//        return DataSourceBuilder.create().build(); // 使用 springboot 默认的数据源
        return new DruidDataSource(); // 使用 DruidDataSource
    }

    @Bean("haiBaseSqlSessionFactory")
    public SqlSessionFactory haiBaseSqlSessionFactory(@Qualifier("haiBaseDataSource") DataSource haiBaseDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // 使配置信息加载到类中，再注入到SqlSessionFactoryBean
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true); // 驼峰模式
        sqlSessionFactoryBean.setConfiguration(configuration);

        sqlSessionFactoryBean.setDataSource(haiBaseDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/haibase/*"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("haiBaseTransactionManager")
    public DataSourceTransactionManager haiBaseTransactionManager(@Qualifier("haiBaseDataSource") DataSource haiBaseDataSource) {
        return new DataSourceTransactionManager(haiBaseDataSource);
    }

    @Bean("haiBaseSqlSessionTemplate")
    public SqlSessionTemplate haiBaseSqlSessionTemplate(@Qualifier("haiBaseSqlSessionFactory") SqlSessionFactory haiBaseSqlSessionFactory) {
        return new SqlSessionTemplate(haiBaseSqlSessionFactory);
    }

}
