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
