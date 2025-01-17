package org.shiloh.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.shiloh.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 动态 SQL 测试用例
 *
 * @author shiloh
 * @date 2022/4/14 22:00
 */
public class DynamicSqlMapperTests {

    /**
     * 测试根据部门ID和用户姓名模糊匹配查询用户列表
     *
     * @author shiloh
     * @date 2022/4/14 22:01
     */
    @Test
    public void testFindAllByDeptIdAndUsernameLike() throws IOException {
        // 加载mybatis配置文件
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 实例化SqlSessionFactory
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        // 从工厂中获取SqlSession，并开启事务自动提交
        try (final SqlSession sqlSession = factory.openSession(true)) {
            // 从Session中获取Mapper
            final DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
            // 执行 SQL
            final String fuzzyUsername = "老";
            final List<User> users = mapper.findAllByDeptIdAndUsernameLike(fuzzyUsername);
            Assert.assertFalse(users.isEmpty());
            users.forEach(System.out::println);
        }
    }

    /**
     * 测试使用动态更新语句更新用户信息
     *
     * @author shiloh
     * @date 2022/4/16 21:26
     */
    @Test
    public void testUpdateUserUsingDynamicSetSyntax() throws IOException {
        // 加载mybatis配置文件
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 实例化SqlSessionFactory
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        // 从工厂中获取SqlSession，并开启事务自动提交
        try (final SqlSession sqlSession = factory.openSession(true)) {
            // 从Session中获取Mapper
            final DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
            // 执行 SQL，仅更新姓名、性别和邮箱
            final User user = new User(1L, "老李头321", null, 1, "lxl@gmail.com", null, null);
            mapper.updateUser(user);
        }
    }

    /**
     * 测试查询 ID 在给定范围内的用户列表
     *
     * @author shiloh
     * @date 2022/4/17 11:37
     */
    @Test
    public void testFindAllByIdIn() throws IOException {
        // 加载mybatis配置文件
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 实例化SqlSessionFactory
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        // 从工厂中获取SqlSession，并开启事务自动提交
        try (final SqlSession sqlSession = factory.openSession(true)) {
            // 从Session中获取Mapper
            final DynamicSqlMapper mapper = sqlSession.getMapper(DynamicSqlMapper.class);
            // 执行 SQL
            final List<Long> ids = List.of(1L, 3L, 5L, 6L);
            final List<User> users = mapper.findAllByIdIn(ids);
            Assert.assertFalse(users.isEmpty());
            users.forEach(System.out::println);
        }
    }
}