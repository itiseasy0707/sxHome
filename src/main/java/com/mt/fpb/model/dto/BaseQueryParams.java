package com.mt.fpb.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * 基本查询参数
 * @author g
 * @date 2020-02-03 14:29
 */
@Data
public class BaseQueryParams {
    /** 页码 */
    private Integer page;
    /** 页容量 */
    private Integer pageSize;
    /** 名称 */
    private String name;
    /** 排序字段 */
    private String orderBy;
    /** 升序降序  ASC DESC */
    private String sorted;

    private Date time;

    private Integer id;

    private String openId;

    private String shopName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    private String areaName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSorted() {
        return sorted;
    }

    public void setSorted(String sorted) {
        this.sorted = sorted;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
