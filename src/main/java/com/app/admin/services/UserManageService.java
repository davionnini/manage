package com.app.admin.services;

import com.app.admin.dto.ModifyUserDTO;
import com.app.admin.dto.UserDTO;
import com.app.admin.model.Func.FuncModel;
import com.app.admin.model.User.User;

import java.util.List;


public interface UserManageService {


    Boolean authUser(String token);

    String getTokenField(String jwsString, String field);

    String isLogin(UserDTO user);

    Boolean userRegister(UserDTO user);

    Boolean updateUserInfo(ModifyUserDTO user);

    List<User> userList();

    List<FuncModel> funcList(String token);
}
