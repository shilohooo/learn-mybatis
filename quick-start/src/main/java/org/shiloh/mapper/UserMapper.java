package org.shiloh.mapper;

import org.apache.ibatis.annotations.Param;
import org.shiloh.entity.User;

import java.util.List;

/**
 * 用户信息Mapper
 *
 * @author shiloh
 * @date 2022/3/20 12:08
 */
public interface UserMapper {
    /**
     * 查询所有用户信息
     *
     * @return 用户信息列表
     * @author shiloh
     * @date 2022/3/20 12:38
     */
    List<User> findAll();

    /**
     * 根据ID查询用户信息
     *
     * @param id ID
     * @return 用户信息
     * @author shiloh
     * @date 2022/3/22 19:13
     */
    User findById(@Param("id") Long id);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 受影响行数
     * @author shiloh
     * @date 2022/3/23 15:22
     */
    int insert(User user);

    /**
     * 根据ID删除用户信息
     *
     * @param id ID
     * @return 受影响的行数
     * @author shiloh
     * @date 2022/3/23 15:30
     */
    int deleteById(Long id);

    /**
     * 根据ID更新用户信息
     *
     * @param user 用户信息
     * @return 受影响的行数
     * @author shiloh
     * @date 2022/3/23 15:33
     */
    int update(User user);

    /**
     * 新增用户信息并获取自动生成的主键
     *
     * @param user 用户信息
     * @author shiloh
     * @date 2022/3/23 15:59
     */
    void insertAngGetPrimaryKey(User user);
}
