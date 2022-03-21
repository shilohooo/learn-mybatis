package org.shiloh.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * {@link org.apache.ibatis.session.SqlSession}相关测试用例
 *
 * @author shiloh
 * @date 2022/3/20 12:17
 */
public class SqlSessionTests {
    /**
     * 从{@link org.apache.ibatis.session.SqlSessionFactory}中获取{@link org.apache.ibatis.session.SqlSession}
     * <p>
     * 每个线程都应该有它自己的 {@link SqlSession} 实例。{@link SqlSession} 的实例不是线程安全的，因此是不能被共享的，
     * <p>
     * 所以它的最佳的作用域是请求或方法作用域。 绝对不能将 {@link SqlSession} 实例的引用放在一个类的静态域，
     * <p>
     * 甚至一个类的实例变量也不行。 也绝不能将 {@link SqlSession} 实例的引用放在任何类型的托管作用域中，
     * <p>
     * 比如 Servlet 框架中的 HttpSession。 如果你现在正在使用一种 Web 框架，考虑将 SqlSession 放在一个和 HTTP 请求相似的作用域中。
     * <p>
     * 换句话说，每次收到 HTTP 请求，就可以打开一个 SqlSession，返回一个响应后，就关闭它。
     * <p>
     * 这个关闭操作很重要，为了确保每次都能执行关闭操作，你应该把这个关闭操作放到 finally 块中。
     * <p>
     * 可以使用try-with-resources来操作。
     *
     * @author shiloh
     * @date 2022/3/20 12:18
     */
    @Test
    public void getSqlSessionFromSqlSessionFactory() throws Exception {
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        Assert.assertNotNull(sqlSession);
    }
}
