package org.shiloh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息实体
 *
 * @author shiloh
 * @date 2022/3/20 12:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -4096114430354729824L;

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 部门ID
     *
     * @see Dept#getId()
     */
    private Long deptId;

    /**
     * 所属部门
     */
    private Dept dept;
}
