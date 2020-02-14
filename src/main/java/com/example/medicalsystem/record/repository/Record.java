package com.example.medicalsystem.record.repository;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "record")
@Data
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 病例编号
     */
    private String recordNumber;

    /**
     * 所处医院
     */
    private String hospital;

    /**
     * token
     */
    private  String token;

    /**
     * 病人姓名
     */
    private String patientName;

    /**
     * 主诊医生
     */
    private String userName;

    /**
     * 病人性别（0：男，1：女）
     */
    private Integer gender;

    /**
     * 病人年龄
     */
    private Integer age;

    /**
     * 病人联系方式
     */
    private Long phoneNumber;

    /**
     * 所处科室
     */
    private String department;

    /**
     * 过敏史
     */
    private String allergyHistory;

    /**
     * 症状
     */
    private String symptom;

    /**
     * 以往病史
     */
    private String medicalHistory;

    /**
     * 初诊结果
     */
    private String initialDiagnosis;

    /**
     * 确诊结果
     */
    private String confirmedResult;

    /**
     * 处方
     */
    private String prescription;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;
}
