package com.mt.fpb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "old_relation")
public class OldRelation {
    /**
     * 主键自增id
     */
    @Id
    private Integer id;

    /**
     * 亲属姓名
     */
    private String name;

    /**
     * 亲属电话号码
     */
    private String tel;

    /**
     * 身份证号
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 老人id
     */
    @Column(name = "oldman_id")
    private Integer oldmanId;

    /**
     * 获取主键自增id
     *
     * @return id - 主键自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键自增id
     *
     * @param id 主键自增id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取亲属姓名
     *
     * @return name - 亲属姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置亲属姓名
     *
     * @param name 亲属姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取亲属电话号码
     *
     * @return tel - 亲属电话号码
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置亲属电话号码
     *
     * @param tel 亲属电话号码
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取身份证号
     *
     * @return id_card - 身份证号
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置身份证号
     *
     * @param idCard 身份证号
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取老人id
     *
     * @return oldman_id - 老人id
     */
    public Integer getOldmanId() {
        return oldmanId;
    }

    /**
     * 设置老人id
     *
     * @param oldmanId 老人id
     */
    public void setOldmanId(Integer oldmanId) {
        this.oldmanId = oldmanId;
    }
}