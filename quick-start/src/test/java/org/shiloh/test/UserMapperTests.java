package org.shiloh.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.shiloh.entity.User;
import org.shiloh.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * {@link org.shiloh.mapper.UserMapper}相关测试用例
 *
 * @author shiloh
 * @date 2022/3/20 12:22
 */
public class UserMapperTests {
    /**
     * 测试获取所有用户信息
     *
     * @author shiloh
     * @date 2022/3/20 12:23
     */
    @Test
    public void testGetAllUsers() throws IOException {
        // 加载mybatis配置文件
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 实例化SqlSessionFactory
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        // 从工厂中获取SqlSession
        try (final SqlSession sqlSession = factory.openSession()) {
            // 从Session中获取Mapper
            final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 执行SQL
            final List<User> users = userMapper.findAll();
            Assert.assertFalse(users.isEmpty());
            users.forEach(System.out::println);
        }
    }
}
