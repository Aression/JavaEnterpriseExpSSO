# JavaEnterpriseExpSSO
 Java企业及应用实验一,单点登录系统

## 说明
- 本项目实现了基于OAuth2.0协议的SSO（Single Sign On）单点登陆系统,采用授权码模式(Authorization Code)实现
- 子系统功能:
    - 简单起见, 只设置两个很小的功能
    - A8081子系统负责: 输入一个数A, 返回其二进制字符串Str.
    - B8082子系统负责: 输入一个数A, 返回其十六进制字符串Str.

## 预计实现路径
- 页面的展示: springboot+thymeleaf实现
- 登陆验证逻辑: OAuth2.0协议. 服务器(Server)承担验证服务器和服务供给方两个角色, 客户端(Client)需要向Server申请到登录态后才能执行其功能.
- 服务器(Server)使用restful架构实现,用于传输token和提供服务.