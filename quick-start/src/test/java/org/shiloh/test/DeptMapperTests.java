package org.shiloh.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.shiloh.entity.Dept;
import org.shiloh.entity.User;
import org.shiloh.mapper.DeptMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * {@link org.shiloh.mapper.DeptMapper} 测试用例
 *
 * @author shiloh
 * @date 2022/4/10 13:49
 */
public class DeptMapperTests {
    /**
     * 测试根据ID查询部门信息, 以及该部门下的所有用户
     *
     * @author shiloh
     * @date 2022/4/10 13:50
     */
    @Test
    public void testFindByIdJoinUsers() throws IOException {
        // 加载mybatis配置文件
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 实例化SqlSessionFactory
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        // 从工厂中获取SqlSession
        try (final SqlSession sqlSession = factory.openSession()) {
            // 从Session中获取Mapper
            final DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
            // 获取部门信息
            final Dept dept = deptMapper.findByIdJoinUser(1L);
            Assert.assertNotNull(dept);
            System.out.println(dept);
            final List<User> users = dept.getUsers();
            Assert.assertFalse(users.isEmpty());
            users.forEach(System.out::println);
        }
    }
}
