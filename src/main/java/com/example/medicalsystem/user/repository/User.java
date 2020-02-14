package com.example.medicalsystem.user.repository;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型
     */
    private String userTypeId;

    private String token;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 性别(0:男  1:女)
     */
    private Integer gender;

    /**
     * email
     */
    private String email;

    /**
     * 工作单位
     */
    private String workUnit;

    /**
     * age
     */
    private Integer age;
    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 联系方式
     */
    private Long phoneNumber;

    /**
     * 职务
     */
    private String position;

    /**
     * 所在科室
     */
    private String department;

    /**
     * 创建时间（时间戳13位）
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

}
