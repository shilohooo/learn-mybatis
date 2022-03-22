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
}
