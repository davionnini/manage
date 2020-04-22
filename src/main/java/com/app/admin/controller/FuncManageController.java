package com.app.admin.controller;

import com.app.admin.dto.FuncDTO;
import com.app.admin.model.Func.Func;
import com.app.admin.services.PowerManageService;
import com.app.admin.vo.MenuListVo;
import com.app.standard.common.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("/func")
@RestController
public class FuncManageController<K,V>{

    @Autowired
    private PowerManageService powerManageService;

    @RequestMapping("/list")
    public ReturnCode funcList()
    {
        List<Func> list = powerManageService.funcAll();

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

    @RequestMapping("/add")
    public ReturnCode addFunc(@RequestBody FuncDTO func)
    {
        powerManageService.addFunc(func);
        return ReturnCode.success();
    }
}
