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
     * 官方对映射器实例作用域和生命周期的说明：
     *
     * 映射器是一些绑定映射语句的接口。映射器接口的实例是从 SqlSession 中获得的。
     *
     * 虽然从技术层面上来讲，任何映射器实例的最大作用域与请求它们的 SqlSession 相同。
     *
     * 但方法作用域才是映射器实例的最合适的作用域。
     *
     * 也就是说，映射器实例应该在调用它们的方法中被获取，使用完毕之后即可丢弃。
     *
     * 映射器实例并不需要被显式地关闭。尽管在整个请求作用域保留映射器实例不会有什么问题，
     *
     * 但是你很快会发现，在这个作用域上管理太多像 SqlSession 的资源会让你忙不过来。
     *
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
}
