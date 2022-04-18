package org.shiloh.mapper;

import org.apache.ibatis.annotations.Param;
import org.shiloh.entity.User;

import java.util.List;

/**
 * 批量插入Mapper
 *
 * @author shiloh
 * @date 2022/4/18 10:59
 */
public interface BatchInsertMapper {
    /**
     * 批量插入用户
     *
     * @param users 用户列表
     * @author shiloh
     * @date 2022/4/18 11:00
     */
    void batchInsert(@Param("users") List<User> users);
}
