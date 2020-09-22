package com.mt.fpb.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class User {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 微信名称
     */
    @Column(name = "wx_name")
    private String wxName;

    /**
     * 省份证号码
     */
    @Column(name = "sfz_number")
    private String sfzNumber;

    /**
     * 身份证名称
     */
    @Column(name = "sfz_name")
    private String sfzName;

    /**
     * 性别 
     */
    private String sex;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 积分数
     */
    private String jifen;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 类型
     */
    private String type;

    /**
     * 状态
     */
    private String status;

    /**
     * 微信头像元数据
     */
    @Column(name = "wx_img")
    private String wxImg;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取微信名称
     *
     * @return wx_name - 微信名称
     */
    public String getWxName() {
        return wxName;
    }

    /**
     * 设置微信名称
     *
     * @param wxName 微信名称
     */
    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    /**
     * 获取省份证号码
     *
     * @return sfz_number - 省份证号码
     */
    public String getSfzNumber() {
        return sfzNumber;
    }

    /**
     * 设置省份证号码
     *
     * @param sfzNumber 省份证号码
     */
    public void setSfzNumber(String sfzNumber) {
        this.sfzNumber = sfzNumber;
    }

    /**
     * 获取身份证名称
     *
     * @return sfz_name - 身份证名称
     */
    public String getSfzName() {
        return sfzName;
    }

    /**
     * 设置身份证名称
     *
     * @param sfzName 身份证名称
     */
    public void setSfzName(String sfzName) {
        this.sfzName = sfzName;
    }

    /**
     * 获取性别 
     *
     * @return sex - 性别 
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别 
     *
     * @param sex 性别 
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取电话号码
     *
     * @return phone - 电话号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话号码
     *
     * @param phone 电话号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取积分数
     *
     * @return jifen - 积分数
     */
    public String getJifen() {
        return jifen;
    }

    /**
     * 设置积分数
     *
     * @param jifen 积分数
     */
    public void setJifen(String jifen) {
        this.jifen = jifen;
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取微信头像元数据
     *
     * @return wx_img - 微信头像元数据
     */
    public String getWxImg() {
        return wxImg;
    }

    /**
     * 设置微信头像元数据
     *
     * @param wxImg 微信头像元数据
     */
    public void setWxImg(String wxImg) {
        this.wxImg = wxImg;
    }
}