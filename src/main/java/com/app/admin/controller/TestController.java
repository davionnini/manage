package com.app.admin.controller;

import com.app.admin.dao.RoleMapper;
import com.app.admin.dao.UserMapper;
import com.app.admin.model.Role.Role;
import com.app.admin.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/test")
@RestController
public class TestController {
    @Autowired
    JwtTokenUtil jwt;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/test")
    public List<Role> test()
    {
        System.out.println(userMapper.getById(4).toString());
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> collect = new ArrayList<>();

        list1.add(1);
        list1.add(3);
        list1.add(5);
        list1.add(7);

        list2.add(1);
        list2.add(3);
        list2.add(5);
        list2.add(7);


        //交集
        collect.clear();
        collect.addAll(list1);
        collect.retainAll(list2);
        System.out.println("交集"+ collect.toString());
        //并集
        collect.clear();
        collect.addAll(list1);
        collect.addAll(list2);
        System.out.println("并集"+ collect.toString());
        //补集
        collect.clear();
        collect.addAll(list1);
        collect.removeAll(list2);
        System.out.println("1补集合"+collect.toString());

        collect.clear();
        collect.addAll(list2);
        collect.removeAll(list1);
        System.out.println("2补集合"+collect.toString());

        return roleMapper.getAllWithFunc();
    }
}
