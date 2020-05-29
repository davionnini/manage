package com.app.admin.services.imp;

import com.app.admin.dto.CommonDTO;
import com.app.admin.dto.ModifyUserDTO;
import com.app.admin.dto.UserDTO;
import com.app.admin.dao.RoleMapper;
import com.app.admin.dao.RoleUserMapper;
import com.app.admin.dao.UserMapper;
import com.app.admin.dto.UserInfoDTO;
import com.app.admin.model.Func.Func;
import com.app.admin.model.Role.Role;
import com.app.admin.model.RoleUser.RoleUser;
import com.app.admin.model.User.User;
import com.app.admin.services.UserManageService;
import com.app.admin.utils.JwtTokenUtil;

import com.app.admin.utils.UniqueIdUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        RoleUser roleUser = new RoleUser();

        if(userMapper.getByName(userDTO.getUsername()) == null){

            //用户入库
            userModel.setPassword(userDTO.getPassword());
            userModel.setUserName(userDTO.getUsername());
            userModel.setUserId(UniqueIdUtil.nextId());

            userMapper.insert(userModel);

            roleUser.setUserId(userModel.getId());

            //权限入库
            long roleId = 2;
            roleUser.setRoleId(roleId);
            roleUserMapper.insert(roleUser);
        }

        return true;
    }

    /**
     * 修改用户个人信息
     * @param userDTO
     * @return
     */
    public Boolean updateUserInfo(ModifyUserDTO userDTO)
    {
        List<Long> increasedRoleIds = new ArrayList<>();
        List<Long> lossRoleIds = new ArrayList<>();
        User userModel = new User();

        long userId = userDTO.getUserId();


        //获取用户id
        userModel.setUserName(userDTO.getUsername());
        userModel.setPassword(userDTO.getPassword());
        userModel.setId(userDTO.getUserId());

        //更新用户信息
        userMapper.updateByPrimaryKey(userModel);

        //更新角色
        //查询添加角色id是否存在
        List<RoleUser> roles = roleUserMapper.getByUserId(userId);
        List<Long> roleIds = roles.stream().map(RoleUser::getRoleId).collect(toList());


        //新增的roleIds
        List<Long> addRoleIds = userDTO.getRoleIds();

        //转化
        addRoleIds = addRoleIds.parallelStream().collect(toList());
        roleIds = roleIds.parallelStream().collect(toList());


        //新增roleIds addRoleIds的补集
        increasedRoleIds.addAll(addRoleIds);
        increasedRoleIds.removeAll(roleIds);


        //删除的roleIds roleIds的补集
        lossRoleIds.addAll(roleIds);
        lossRoleIds.removeAll(addRoleIds);


        //权限入库
        RoleUser roleUserModel = new RoleUser();

        roleUserModel.setUserId(userId);
        for (Long roleId: increasedRoleIds) {
            roleUserModel.setRoleId(roleId);
            roleUserMapper.insert(roleUserModel);
        }

        for (Long roleId: lossRoleIds) {
            roleUserMapper.deleteByRoleIdAndUserId(roleId,userId);

        }

        return true;

    }

    /**
     * 用户列表
     * @return
     */
    public List<User> userList(CommonDTO commonDTO)
    {
        PageHelper.startPage(commonDTO.getPageNum(),commonDTO.getPageSize());
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


    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public User getUserById(Long userId)
    {
        return userMapper.getById(userId);
    }


    public Boolean deleteByUserId(Long userId)
    {
        userMapper.deleteById(userId);
        return true;
    }

    /**
     * 用户模糊查询
     * @param userInfoDTO
     * @return
     */
    public List<User> getUserByName(UserInfoDTO userInfoDTO)
    {
        User user = new User();
        user.setUserName(userInfoDTO.getUserName());
        PageHelper.startPage(userInfoDTO.getPageNum(),userInfoDTO.getPageSize());
        return userMapper.getByLikeName(user);
    }

}
