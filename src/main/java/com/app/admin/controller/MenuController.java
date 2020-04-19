package com.app.admin.controller;

import com.app.admin.dto.ModifyUserDTO;
import com.app.admin.model.Func.Func;
import com.app.admin.services.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 获取个人菜单配置
 */
@RequestMapping("/menu")
@RestController
public class MenuController {

    @Autowired
    UserManageService userManageService;

    @RequestMapping("/list")
    public List<Func> menu(@RequestBody ModifyUserDTO user)
    {
        return userManageService.funcList(user.getToken());
    }
}
