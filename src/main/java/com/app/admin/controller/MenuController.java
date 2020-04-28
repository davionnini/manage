package com.app.admin.controller;

import com.app.admin.dto.CommonDTO;
import com.app.admin.dto.ModifyUserDTO;
import com.app.admin.model.Func.Func;
import com.app.admin.services.PowerManageService;
import com.app.admin.services.UserManageService;
import com.app.admin.vo.MenuListVo;
import com.app.standard.common.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取个人菜单配置
 */
@RequestMapping("/menu")
@RestController
public class MenuController {

    @Autowired
    UserManageService userManageService;

    @Autowired
    PowerManageService powerManageService;
    @RequestMapping("/list")
    public ReturnCode menu(@RequestBody CommonDTO commonDTO)
    {

        List<Func> list = userManageService.funcList(commonDTO.getToken());

        List<MenuListVo> menulist = new ArrayList<>();
        MenuListVo menu = new MenuListVo();
        menu.setMenuId(1);
        menu.setMenuName("systemManage");
        menu.setMenuDesc("系统管理");
        menu.setLevel("root");
        menu.setChildren(list);
        menulist.add(menu);

        return ReturnCode.success(menulist);
    }



}
