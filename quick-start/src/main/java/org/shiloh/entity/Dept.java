package org.shiloh.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 部门实体
 *
 * @author shiloh
 * @date 2022/4/8 22:38
 */
@Data
@ToString(exclude = {"users"})
public class Dept {
    /**
     * ID
     */
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门下的用户列表
     */
    private List<User> users;
}
