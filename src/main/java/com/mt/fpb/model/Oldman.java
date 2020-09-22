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
 * @since 2020-09-22 10:59:10
 */
@Table(name = "`oldman`")
public class Oldman implements Serializable {
    private static final long serialVersionUID = 399218296605847047L;

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
     * 家属姓名
     */
    @Column(name = "`ol_relation_name`")
    private String olRelationName;

    /**
     * 家属电话号码
     */
    @Column(name = "`ol_relation_tel`")
    private String olRelationTel;

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

    public String getOlRelationName() {
        return olRelationName;
    }

    public void setOlRelationName(String olRelationName) {
        this.olRelationName = olRelationName;
    }

    public String getOlRelationTel() {
        return olRelationTel;
    }

    public void setOlRelationTel(String olRelationTel) {
        this.olRelationTel = olRelationTel;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}