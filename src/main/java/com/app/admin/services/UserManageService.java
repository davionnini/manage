package com.app.admin.services;

import com.app.admin.dto.CommonDTO;
import com.app.admin.dto.ModifyUserDTO;
import com.app.admin.dto.UserDTO;
import com.app.admin.dto.UserInfoDTO;
import com.app.admin.model.Func.Func;
import com.app.admin.model.User.User;

import java.util.List;


public interface UserManageService {


    Boolean authUser(String token);

    String getTokenField(String jwsString, String field);

    String isLogin(UserDTO user);

    Boolean userRegister(UserDTO user);

    Boolean updateUserInfo(ModifyUserDTO user);

    List<User> userList(CommonDTO commonDTO);

    List<Func> funcList(String token);

    User getUserById(Long userId);

    List<User> getUserByName(UserInfoDTO username);

    Boolean deleteByUserId(Long userId);
}
