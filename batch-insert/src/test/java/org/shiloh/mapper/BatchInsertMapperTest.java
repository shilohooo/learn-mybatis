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

import static org.junit.Assert.*;

/**
 * 批量插入测试用例
 *
 * @author shiloh
 * @date 2022/4/18 11:08
 */
public class BatchInsertMapperTest {

    /**
     * 测试批量插入用户信息
     *
     * @author shiloh
     * @date 2022/4/18 11:08
     */
    @Test
    public void testBatchInsert() throws IOException {
        final List<User> users = List.of(
                new User(null, "batch1", "123456", 1, "batch1@qq.com", 1L, null),
                new User(null, "batch2", "123456", 1, "batch2@qq.com", 1L, null),
                new User(null, "batch3", "123456", 2, "batch3@qq.com", 1L, null),
                new User(null, "batch4", "123456", 2, "batch4@qq.com", 3L, null),
                new User(null, "batch5", "123456", 1, "batch5@qq.com", 3L, null)
        );
        // 加载mybatis配置文件
        final InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 实例化SqlSessionFactory
        final SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        // 从工厂中获取SqlSession
        try (final SqlSession sqlSession = factory.openSession()) {
            // 从Session中获取Mapper
            final BatchInsertMapper mapper = sqlSession.getMapper(BatchInsertMapper.class);
            // 执行SQL
            mapper.batchInsert(users);
            // 提交事务
            sqlSession.commit();
        }

    }
}