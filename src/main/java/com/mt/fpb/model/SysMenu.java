package com.mt.fpb.model;

import javax.persistence.Transient;
import java.util.Date;

public class SysMenu {
    private Integer id;

    private String menuName;

    private Integer pid;

    private String path;

    private String icon;

    private Date addTime;

    private Integer menuType;

    private Integer isShow;

    private Integer menuStatus;

    private String remark;

    private String alias;

    private String rule;

    private String method;
    @Transient
    private Integer RoleId;

    public Integer getRoleId() {
        return RoleId;
    }

    public void setRoleId(Integer roleId) {
        RoleId = roleId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(Integer menuStatus) {
        this.menuStatus = menuStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", pid=" + pid +
                ", path='" + path + '\'' +
                ", icon='" + icon + '\'' +
                ", addTime=" + addTime +
                ", menuType=" + menuType +
                ", isShow=" + isShow +
                ", menuStatus=" + menuStatus +
                ", remark='" + remark + '\'' +
                ", alias='" + alias + '\'' +
                ", rule='" + rule + '\'' +
                ", method='" + method + '\'' +
                ", RoleId=" + RoleId +
                '}';
    }
}