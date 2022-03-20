package org.shiloh.mapper;

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
}
