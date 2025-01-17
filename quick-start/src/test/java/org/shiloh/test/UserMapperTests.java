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
     * <p>
     * 官方对映射器实例作用域和生命周期的说明：
     * <p>
     * 映射器是一些绑定映射语句的接口。映射器接口的实例是从 SqlSession 中获得的。
     * <p>
     * 虽然从技术层面上来讲，任何映射器实例的最大作用域与请求它们的 SqlSession 相同。
     * <p>
     * 但方法作用域才是映射器实例的最合适的作用域。
     * <p>
     * 也就是说，映射器实例应该在调用它们的方法中被获取，使用完毕之后即可丢弃。
     * <p>
     * 映射器实例并不需要被显式地关闭。尽管在整个请求作用域保留映射器实例不会有什么问题，
     * <p>
     * 但是你很快会发现，在这个作用域上管理太多像 SqlSession 的资源会让你忙不过来。
     * <p>
     * 因此，最好将映射器放在方法作用域内。
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

    /**
     * 测试根据ID获取用户信息
     *
     * @author shiloh
     * @date 2022/3/22 19:15
     */
    @Test
    public void testGetUserById() throws Exception {
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
            final User user = userMapper.findById(1L);
            Assert.assertNotNull(user);
            System.out.println(user);
        }
    }

    /**
     * 测试新增用户信息
     *
     * @author shiloh
     * @date 2022/3/23 15:27
     */
    @Test
    public void testAddUser() throws Exception {
        // 加载mybatis配置文件
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 实例化SqlSessionFactory
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        // 从工厂中获取SqlSession，并开启事务自动提交
        try (final SqlSession sqlSession = factory.openSession(true)) {
            // 从Session中获取Mapper
            final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 执行SQL
            final User user = new User(null, "Bruce Lee", "123456", 1, "bruce@qq.com", 1L, null);
            final int result = userMapper.insert(user);
            Assert.assertEquals(1, result);
        }
    }

    /**
     * 测试根据ID删除用户信息
     *
     * @author shiloh
     * @date 2022/3/23 15:32
     */
    @Test
    public void testDeleteUserById() throws Exception {
        // 加载mybatis配置文件
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 实例化SqlSessionFactory
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        // 从工厂中获取SqlSession，并开启事务自动提交
        try (final SqlSession sqlSession = factory.openSession(true)) {
            // 从Session中获取Mapper
            final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 执行SQL
            final int result = userMapper.deleteById(3L);
            Assert.assertEquals(1, result);
        }
    }

    /**
     * 测试更新用户信息
     *
     * @author shiloh
     * @date 2022/3/23 15:44
     */
    @Test
    public void testUpdateUser() throws Exception {
        // 加载mybatis配置文件
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 实例化SqlSessionFactory
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        // 从工厂中获取SqlSession，并开启事务自动提交
        try (final SqlSession sqlSession = factory.openSession(true)) {
            // 从Session中获取Mapper
            final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 执行SQL
            final User user = new User(1L, "老李头", "123456", 1, "laolitou@qq.com", 1L, null);
            final int result = userMapper.update(user);
            Assert.assertEquals(1, result);
        }
    }

    /**
     * 测试新增用户信息并获取自动生成的主键
     *
     * @author shiloh
     * @date 2022/3/23 15:27
     */
    @Test
    public void testAddUserAndGetPrimaryKey() throws Exception {
        // 加载mybatis配置文件
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 实例化SqlSessionFactory
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        // 从工厂中获取SqlSession，并开启事务自动提交
        try (final SqlSession sqlSession = factory.openSession(true)) {
            // 从Session中获取Mapper
            final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 执行SQL
            final User user = new User(null, "Mark123", "123456", 1, "mark123@qq.com", 1L, null);
            userMapper.insertAngGetPrimaryKey(user);
            System.out.println(user);
        }
    }

    /**
     * 测试根据ID查询用户信息, 以及用户所属部门
     *
     * @author shiloh
     * @date 2022/4/9 21:17
     */
    @Test
    public void testFindByIdJoinDept() throws Exception {
        // 加载mybatis配置文件
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 实例化SqlSessionFactory
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        // 从工厂中获取SqlSession，并开启事务自动提交
        try (final SqlSession sqlSession = factory.openSession(true)) {
            // 从Session中获取Mapper
            final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 执行SQL
            final User user = userMapper.findByIdJoinDept(1L);
            Assert.assertNotNull(user);
            System.out.println(user);
        }
    }

    /**
     * 测试根据部门ID查询该部门下的所有用户信息
     *
     * @author shiloh
     * @date 2022/4/13 20:46
     */
    @Test
    public void testFindAllByDeptId() throws IOException {
        // 加载mybatis配置文件
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 实例化SqlSessionFactory
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        // 从工厂中获取SqlSession，并开启事务自动提交
        try (final SqlSession sqlSession = factory.openSession(true)) {
            // 从Session中获取Mapper
            final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 执行SQL
            final List<User> users = userMapper.findAllByDeptId(1L);
            Assert.assertFalse(users.isEmpty());
            users.forEach(System.out::println);
        }
    }

    /**
     * 测试 MyBatis 一级缓存：
     * MyBatis 默认情况下，只启用了本地的会话缓存，仅对同一个会话中的数据进行缓存。
     *
     * @author shiloh
     * @date 2022/4/14 11:43
     */
    @Test
    public void testLevel1Cache() throws IOException {
        // 加载mybatis配置文件
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 实例化SqlSessionFactory
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        // 从工厂中获取SqlSession，并开启事务自动提交
        try (
                final SqlSession sqlSession = factory.openSession(true);
                final SqlSession sqlSession1 = factory.openSession(true)
        ) {
            // 从Session中获取Mapper
            final UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            final UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
            // 执行SQL
            User user = userMapper.findById(1L);
            // 重复获取只会执行一条 SQL，若使用不同会话来执行SQL，则一级缓存不会生效
            user = userMapper1.findById(1L);
            Assert.assertNotNull(user);
        }
    }
}
