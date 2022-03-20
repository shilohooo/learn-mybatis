package org.shiloh.entity;

import lombok.Data;

/**
 * 用户信息实体
 *
 * @author shiloh
 * @date 2022/3/20 12:00
 */
@Data
public class User {
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
}
