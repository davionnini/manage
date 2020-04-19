#目录结构
```
├── java 
│   └── com
│       └── app
│           ├── admin               ##项目文件夹
│           │   ├── AppApplication.java     ##入口文件
│           │   ├── config          ##全局配置类
│           │   ├── controller      ##控制器层
│           │   ├── dto             ##业务请求
│           │   ├── mapper          ##mybatis对应配置里mapper
│           │   ├── model           ##数据库model
│           │   ├── services        ##业务逻辑层
│           │   ├── utils           ##通用工具类
│           │   └── vo              ##业务返回
│           ├── demo                ##测试目录
│           └── standard            ##全局通用结构
│               └── common          ##项目返回结构和状态码
└── resources                       ##项目配置目录
    ├── application.properties      ##项目配置文件
    └── mybatis      ##mybatis      ##文件路径
        ├── config.xml              ##mybatis配置 
        └── mapper                  ##mybatis数据表配置
```
