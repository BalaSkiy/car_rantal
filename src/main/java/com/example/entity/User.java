package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ch
 * @since 2023-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户性别
     */
    private Boolean sex;

    /**
     * 用户年龄
     */
    private Integer age;

    private String job;

    /**
     * 角色
     */
    private Integer roleid;


}
