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
     * <p>
     * {@link SqlSessionFactoryBuilder}的主要作用是构建{@link SqlSessionFactory}，当构建完成后便可以舍弃掉
     * {@link SqlSessionFactoryBuilder}，因此官方建议将{@link SqlSessionFactoryBuilder}设置为局部变量即可。
     * <p>
     * 以下是官方对{@link SqlSessionFactoryBuilder}作用域和生命周期的说明：
     * <p>
     * 这个类可以被实例化、使用和丢弃，一旦创建了 SqlSessionFactory，就不再需要它了。
     * <p>
     * 因此 SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域（也就是局部方法变量）。
     * <p>
     * 你可以重用 SqlSessionFactoryBuilder 来创建多个 SqlSessionFactory 实例，
     * <p>
     * 但最好还是不要一直保留着它，以保证所有的 XML 解析资源可以被释放给更重要的事情。
     * <p>
     * {@link SqlSessionFactory}是 MyBatis应用的核心，可以从中获取{@link org.apache.ibatis.session.SqlSession}的实例，
     * {@link org.apache.ibatis.session.SqlSession}提供了在数据库执行SQL命令所需要的所有方法，
     * 可以通过{@link org.apache.ibatis.session.SqlSession}来执行已映射的SQL语句。
     * <p>
     * 对于{@link SqlSessionFactory}，每个 MyBatis 应用应该只创建一次，且仅存在唯一实例，此处建议使用单例模式实现。
     * <p>
     * 官方对于{@link SqlSessionFactory}作用域和生命周期的说明如下：
     * <p>
     * {@link SqlSessionFactory}一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。
     * <p>
     * 使用 {@link SqlSessionFactory} 的最佳实践是在应用运行期间不要重复创建多次，
     * <p>
     * 多次重建 {@link SqlSessionFactory} 被视为一种代码“坏习惯”。因此 {@link SqlSessionFactory} 的最佳作用域是应用作用域。
     * <p>
     * 有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。
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
