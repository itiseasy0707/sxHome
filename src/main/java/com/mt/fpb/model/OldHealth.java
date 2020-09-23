package com.mt.fpb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "old_health")
public class OldHealth {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 血型
     */
    @Column(name = "blood_type")
    private String bloodType;

    /**
     * 血压
     */
    @Column(name = "blood_press")
    private String bloodPress;

    /**
     * 外键(老人表的主键)
     */
    @Column(name = "old_id")
    private Integer oldId;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取血型
     *
     * @return blood_type - 血型
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * 设置血型
     *
     * @param bloodType 血型
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * 获取血压
     *
     * @return blood_press - 血压
     */
    public String getBloodPress() {
        return bloodPress;
    }

    /**
     * 设置血压
     *
     * @param bloodPress 血压
     */
    public void setBloodPress(String bloodPress) {
        this.bloodPress = bloodPress;
    }

    public Integer getOldId() {
        return oldId;
    }

    public void setOldId(Integer oldId) {
        this.oldId = oldId;
    }
}