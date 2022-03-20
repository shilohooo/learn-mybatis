package org.shiloh.test;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

/**
 * 测试用例：从XML文件中构建 {@link org.apache.ibatis.session.SqlSessionFactory}
 *
 * @author shiloh
 * @date 2022/3/19 12:23
 */
public class SqlSessionFactoryTests {

    /**
     * 测试从XML文件中构建 {@link org.apache.ibatis.session.SqlSessionFactory}
     *
     * @author shiloh
     * @date 2022/3/19 12:24
     */
    @Test
    public void buildSqlSessionFactoryFromXml() throws Exception {
        // 指定配置文件在classpath下的位置
        final String resource = "mybatis-config.xml";
        // 加载配置文件
        final InputStream inputStream = Resources.getResourceAsStream(resource);
        // 实例化SqlSessionFactoryBuilder，调用build(InputStream)方法获取SqlSessionFactory
        final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        Assert.assertNotNull(sqlSessionFactory);
    }

    /**
     * 使用Java代码配置的方式构建 {@link SqlSessionFactory}
     *
     * @author shiloh
     * @date 2022/3/20 11:31
     */
    @Test
    public void buildSqlSessionFactoryWithJavaConfig() throws Exception {
        // 读取Jdbc数据库链接配置
        final InputStream inputStream = Resources.getResourceAsStream("jdbc.properties");
        final Properties properties = new Properties();
        properties.load(inputStream);
        // 构建数据源
        final PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver(properties.getProperty("jdbc.driver"));
        dataSource.setUrl(properties.getProperty("jdbc.url"));
        dataSource.setUsername(properties.getProperty("jdbc.username"));
        dataSource.setPassword("jdbc.password");
        // 实例化事务工厂
        final JdbcTransactionFactory jdbcTransactionFactory = new JdbcTransactionFactory();
        // 创建环境
        final Environment environment = new Environment("Dev", jdbcTransactionFactory, dataSource);
        // 创建配置对象
        final Configuration configuration = new Configuration(environment);
        // 获取sql session factory
        final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(configuration);
        Assert.assertNotNull(sqlSessionFactory);
    }
}
