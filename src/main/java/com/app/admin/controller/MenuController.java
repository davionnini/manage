package com.app.admin.controller;

import com.app.admin.model.Func.FuncModel;
import com.app.admin.services.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class MenuController {

    @Autowired
    UserManageService userManageService;

    @RequestMapping("menu/list")
    public List<FuncModel> menu(@RequestParam String token)
    {
        return userManageService.funcList(token);
    }
}
