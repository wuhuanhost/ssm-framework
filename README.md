<!--
 * @Author: Dreamer
 * @Site: https://www.geekfanfan.com
 * @Date: 2020-11-19 08:51:27
 * @Email: wuhuanhost@163.com
 * @LastEditors: Dreamer
 * @LastEditTime: 2021-08-10 10:41:55
-->

#ssm-framework

基于 maven 整合 spring,springboot,MyBatis 等框架，实现快速 JAVA WEB API 开发。

> 原`基于maven整合spring，springMVC，MyBatis框架，实现快速javaweb开发`使用新分支[ssm](https://github.com/wuhuanhost/ssm-framework/tree/ssm)

## 项目结构说明

```
.
│  mvnw
│  mvnw.cmd
│  pom.xml
│  README.md
│
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─geekfanfan
    │  │          └─think
    │  │              │  ThinkApplication.java(启动类)
    │  │              │
    │  │              ├─config(配置类)
    │  │              │     RedisConfig.java
    │  │              │
    │  │              ├─constants(常量类)
    │  │              │      WeChatConstants.java
    │  │              │
    │  │              ├─mapper
    │  │              │      UserMapper.java
    │  │              │
    │  │              ├─entity(mybatis实体类)
    │  │              │      User.java
    │  │              │
    │  │              │
    │  │              ├─service(业务类)
    │  │              │  │  UserService.java
    │  │              │  │
    │  │              │  └─impl（业务类实现类）
    │  │              │       UserServiceImpl.java
    │  │              │
    │  │              │
    │  │              ├─utils(工具类)
    │  │              │  │  RedisUtil.java
    │  │              │  │
    │  │              │  └─wechat
    │  │              │          SHA1.java
    │  │              │
    │  │              │
    │  │              ├─vo(视图包装，视图包装对象View Object用于封装客户端请求的数据，防止部分数据泄露如：管理员ID，保证数据安全，不破坏 原有的实体类结构)
    │  │              │      UserVO.java
    │  │              │
    │  │              │
    │  │              └─controller(控制器类)
    │  │                      HelloController.java
		│  │                      HomeController.java
    │  │
    │  └─resources
    │      │  application.yml
    │      │  log4j2-spring.xml
    │      │
    │      ├─mappers
    │      │      User.xml
    │      │
    │      ├─static
    │      └─templates
    └─test
        └─java
            └─com

```

## 常用命令

### 启动项目

```shell
./mvnw spring-boot:run
```

**访问地址**

> http://localhost:8888

**文档地址**

> http://localhost:8888/doc.html

**Durid 监控地址**

> http://localhost:8888/druid/index.html
> 用户名：root
> 密码：root
