package com.app.admin.controller;

public class String {
    public void testClassLoader(){
        System.out.println("自定义Long类被"+ String.class.getClassLoader()+"加载了");
    }
    public static void main(java.lang.String[] args) {
        System.out.println("String");
    }
}

