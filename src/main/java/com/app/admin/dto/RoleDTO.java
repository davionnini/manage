package com.app.admin.dto;

import java.util.List;

public class RoleDTO {

    long id;

    String roleName;

    String desc;

    List<Integer> funcIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Integer> getFuncIds() {
        return funcIds;
    }

    public void setFuncIds(List<Integer> funcIds) {
        this.funcIds = funcIds;
    }
}
