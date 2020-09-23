package com.mt.fpb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sx_home")
public class SxHome {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 外键 用户表的外键
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 视频源数据
     */
    private String address;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取视频源数据
     *
     * @return address - 视频源数据
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置视频源数据
     *
     * @param address 视频源数据
     */
    public void setAddress(String address) {
        this.address = address;
    }
}