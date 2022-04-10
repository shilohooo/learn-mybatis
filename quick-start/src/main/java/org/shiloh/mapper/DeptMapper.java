package org.shiloh.mapper;

import org.apache.ibatis.annotations.Param;
import org.shiloh.entity.Dept;

/**
 * 部门 Mapper
 *
 * @author shiloh
 * @date 2022/4/8 22:41
 */
public interface DeptMapper {
    /**
     * 根据ID查询部门信息, 以及该部门下的所有用户
     *
     * @param id ID
     * @return 部门信息, 以及该部门下的所有用户
     * @author shiloh
     * @date 2022/4/10 13:36
     */
    Dept findByIdJoinUser(@Param("id") Long id);
}
