package org.shiloh.mapper;

import org.apache.ibatis.annotations.Param;
import org.shiloh.entity.User;

import java.util.List;

/**
 * 动态 SQL 示例 Mapper
 *
 * @author shiloh
 * @date 2022/4/14 21:40
 */
public interface DynamicSqlMapper {
    /**
     * 根据部门ID和用户姓名模糊匹配查询用户列表
     *
     * @param fuzzyUsername 用户姓名
     * @return 用户列表
     * @author shiloh
     * @date 2022/4/14 21:44
     */
    List<User> findAllByDeptIdAndUsernameLike(@Param("fuzzyUsername") String fuzzyUsername);

    /**
     * 更新用户信息
     *
     * @param user 待更新的用户信息
     * @author shiloh
     * @date 2022/4/16 21:16
     */
    void updateUser(User user);
}
