package com.mt.fpb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 老人表(Oldman)实体类
 *
 * @author makejava
 * @since 2020-09-22 15:23:58
 */
@Table(name = "`oldman`")
public class Oldman implements Serializable {
    private static final long serialVersionUID = -68595711992489679L;

    /**
     * 主键自增id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "`id`")
    private Integer id;

    /**
     * 老人姓名
     */
    @Column(name = "`ol_name`")
    private String olName;

    /**
     * 老人年龄
     */
    @Column(name = "`ol_age`")
    private Integer olAge;

    /**
     * 紧急联系人姓名
     */
    @Column(name = "`ol_emergency_name`")
    private String olEmergencyName;

    /**
     * 紧急联系人电话号码
     */
    @Column(name = "`ol_emergency_tel`")
    private String olEmergencyTel;

    /**
     * 紧急联系人身份证号
     */
    @Column(name = "`ol_emergency_idcard`")
    private String olEmergencyIdcard;

    /**
     * 用户id(未确认字段)
     */
    @Column(name = "`user_id`")
    private Integer userId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOlName() {
        return olName;
    }

    public void setOlName(String olName) {
        this.olName = olName;
    }

    public Integer getOlAge() {
        return olAge;
    }

    public void setOlAge(Integer olAge) {
        this.olAge = olAge;
    }

    public String getOlEmergencyName() {
        return olEmergencyName;
    }

    public void setOlEmergencyName(String olEmergencyName) {
        this.olEmergencyName = olEmergencyName;
    }

    public String getOlEmergencyTel() {
        return olEmergencyTel;
    }

    public void setOlEmergencyTel(String olEmergencyTel) {
        this.olEmergencyTel = olEmergencyTel;
    }

    public String getOlEmergencyIdcard() {
        return olEmergencyIdcard;
    }

    public void setOlEmergencyIdcard(String olEmergencyIdcard) {
        this.olEmergencyIdcard = olEmergencyIdcard;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}