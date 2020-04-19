package com.app.admin.vo;

import com.app.admin.model.Func.Func;

import java.util.List;

/**
 * 菜单列表
 */
public class MenuListVo {

    long menuId;

    String menuName;

    String menuDesc;

    String level;

    List<Func> children;

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Func> getChildren() {
        return children;
    }

    public void setChildren(List<Func> children) {
        this.children = children;
    }
}
