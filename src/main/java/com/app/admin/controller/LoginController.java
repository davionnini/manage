package com.app.admin.controller;

import com.app.admin.dto.ModifyUserDTO;
import com.app.admin.dto.UserDTO;
import com.app.admin.model.User.User;
import com.app.admin.services.UserManageService;
import com.app.standard.common.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理面板
 */
@RequestMapping("/user")
@RestController
public class LoginController {

    @Autowired
    private UserManageService auth;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ReturnCode login(@RequestBody UserDTO user)
    {
       String jws = auth.isLogin(user);
       if(jws == null){
           return ReturnCode.fail("用户不存在");
       }

        Map<String,String> token = new HashMap<>();
       token.put("token",jws);
       return ReturnCode.success(token);

    }

    @RequestMapping(value = "/logout")
    public ReturnCode logout()
    {
        return ReturnCode.success();
    }

    @RequestMapping(value="/register")
    public ReturnCode register(@RequestBody UserDTO user)
    {
        auth.userRegister(user);

        return ReturnCode.success();
    }

    @RequestMapping(value="/modify")
    public ReturnCode modifyPassword(@RequestBody ModifyUserDTO user)
    {
        auth.updateUserInfo(user);

        return ReturnCode.success();
    }

    @RequestMapping(value="/all")
    public ReturnCode getAll()
    {
        List<User> users = auth.userList();
        return ReturnCode.success(users);
    }
}
