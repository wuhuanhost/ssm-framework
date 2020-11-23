# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.0/maven-plugin/reference/html/#build-image)


## 备注

### 启动

`mvnw spring-boot:run`

## 说明

### 使用注解日志

通过Lombok使用@Slf4j注解的时候，log对象会提示没有定义，所以需要在编辑器或IDE中安装lombok插件

### swagger2自定义状态码

`new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)`


### 解决swagger异常AbstractSerializableParameter 

```yml
logging:
  level:
    io.swagger.models.parameters.AbstractSerializableParameter: error
```

参考：

* https://blog.csdn.net/z_k_h/article/details/81875828
* https://blog.csdn.net/qq_35735865/article/details/86711871
 

### 文档地址

http://192.168.1.123:8888/swagger-ui.html

### Druid监控页面地址

http://127.0.0.1:8888/druid

> 用户名：root
> 密 码：root