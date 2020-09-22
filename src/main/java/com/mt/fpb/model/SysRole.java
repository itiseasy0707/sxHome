package com.mt.fpb.model;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

public class SysRole {
    private Integer id;

    private String roleName;

    private Date addTime;

    private Integer roleType;

    private Integer status;

    private String remark;

    private Integer roleSort;

    @Transient
    private List<SysMenu> rules;

    public List<SysMenu> getRules() {
        return rules;
    }

    public void setRules(List<SysMenu> rules) {
        this.rules = rules;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }
}