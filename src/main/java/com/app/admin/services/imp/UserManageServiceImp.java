package com.app.admin.services.imp;

import com.app.admin.dto.ModifyUserDTO;
import com.app.admin.dto.UserDTO;
import com.app.admin.mapper.RoleMapper;
import com.app.admin.mapper.RoleUserMapper;
import com.app.admin.mapper.UserMapper;
import com.app.admin.model.Func.Func;
import com.app.admin.model.Role.Role;
import com.app.admin.model.RoleUser.RoleUser;
import com.app.admin.model.User.User;
import com.app.admin.services.UserManageService;
import com.app.admin.utils.JwtTokenUtil;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
public class UserManageServiceImp implements UserManageService {

    @Autowired
    private JwtTokenUtil jwtToken;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleUserMapper roleUserMapper;
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

        long userId = Integer.valueOf(getTokenField(userDTO.getToken(),"userId"));

        //更新用户信息
        if(userMapper.getById(userId) != null){
            userModel.setPassword(userDTO.getPassword());

            //获取用户id
            userModel.setId(userId);
            userMapper.updateByPrimaryKey(userModel);
        }

        //更新角色

        //查询添加角色id是否存在
        List<Role> roles = roleMapper.getByUserId(userId);
        List<Long> roleIds = roles.stream().map(Role::getId).collect(toList());


        //新增的roleIds
        List<Long> addRoleIds = userDTO.getRoleIds();

        //取并集入库
        addRoleIds = addRoleIds.parallelStream().collect(toList());
        roleIds = roleIds.parallelStream().collect(toList());


        //addRoleIds的补集
        addRoleIds.removeAll(roleIds);

//        //并集
//        addRoleIds.removeAll(roleIds);
//        addRoleIds.addAll(roleIds);

//        //交集
//        addRoleIds.retainAll(roleIds);

        //权限入库
        RoleUser roleUserModel = new RoleUser();
        roleUserModel.setUserId(userId);
        for (Long roleId: addRoleIds) {
            roleUserModel.setRoleId(roleId);
            roleUserMapper.insert(roleUserModel);
        }



        return true;

    }


    /**
     * 用户列表
     * @return
     */
    public List<User> userList()
    {
        PageHelper.startPage(2,10);
        return userMapper.getAll();
    }


    /**
     * 菜单
     * @param token
     * @return
     */
    public List<Func> funcList(String token)
    {
        long userId = Integer.valueOf(getTokenField(token,"userId"));
        return userMapper.funcList(userId);
    }


}
