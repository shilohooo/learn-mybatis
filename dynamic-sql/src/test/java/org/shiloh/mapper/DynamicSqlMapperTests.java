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
}