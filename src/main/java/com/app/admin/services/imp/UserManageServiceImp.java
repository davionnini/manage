package com.app.admin.services.imp;

import com.app.admin.dto.ModifyUserDTO;
import com.app.admin.dto.UserDTO;
import com.app.admin.mapper.UserMapper;
import com.app.admin.model.User.User;
import com.app.admin.services.UserManageService;
import com.app.admin.utils.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserManageServiceImp implements UserManageService {

    @Autowired
    private JwtTokenUtil jwtToken;


    @Autowired
    private UserMapper userMapper;

    /**
     * 设置token
     * @param claim
     * @return
     */
    private String setToken(Map<String,Object> claim){
        String jws = jwtToken.generateToken(claim);
        return jws;
    }

    /**
     * 验证用户token
     * @param token
     * @return
     */
    public Boolean authUser(String token)
    {
        return jwtToken.verifySign(token);
    }


    /**
     * 用户登录
     * @param user
     * @return
     */
    public String isLogin(UserDTO user){
        User one = userMapper.getByNameAndPassword(user.getUsername(),user.getPassword());

        if(one != null){
            Map<String,Object> info = new HashMap<>();
            info.put("userId",one.getId());
            info.put("username",one.getUserName());
            info.put("createdAt",new Date());
            return setToken(info);
        }

        return null;
    }

    /**
     * 获取token中的数据
     * @param jwsString
     * @param field
     * @return
     */
    public String getTokenField(String jwsString, String field)
    {
        return jwtToken.getBodyField(jwsString,field);
    }


    /**
     * 用户注册
     * @param userDTO
     * @return
     */
    public Boolean userRegister(UserDTO userDTO){

        User userModel = new User();

        if(userMapper.getByName(userDTO.getUsername()) == null){
            userModel.setPassword(userDTO.getPassword());
            userModel.setUserName(userDTO.getUsername());
            userModel.setUserId(System.currentTimeMillis());
            userMapper.insert(userModel);
        }

        return true;
    }

    /**
     * 修改用户信息
     * @param userDTO
     * @return
     */
    public Boolean updateUserInfo(ModifyUserDTO userDTO)
    {
        User userModel = new User();

        if(userMapper.getByName(userDTO.getUsername()) == null){
            userModel.setPassword(userDTO.getPassword());
            userModel.setUserName(userDTO.getUsername());

            //获取用户id
            long id = Integer.valueOf(getTokenField(userDTO.getToken(),"userId"));
            userModel.setId(id);
            userMapper.updateByPrimaryKey(userModel);
        }
        return true;

    }
}
