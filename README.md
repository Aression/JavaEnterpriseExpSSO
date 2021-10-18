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
- 服务器(Server)用于传输token和提供服务.

## 目前的实现和使用:

在主域（localhost:8084）认证中心创建session全局对话（如果关闭浏览器则会失效，后续可以改进（存储在cookie或者local的存储中，在前端用ajex请求用token作为文件头）），在子模块中定义一个拦截器没有token或者session则会访问（认证模块）。如果一个子模块访问认证模块登录成功，则会创建令牌和session全局对话，重定向并将token返回原来的子模块，并进行token信息的验证，验证成功则会创建局部session，如果此时其他的子模块访问，则会访问认证模块直接获取session中的token，避免了重复登录的操作。

### 使用: 
- 启动mongodb, 确保有一个名为root, 密码123456的用户拥有管理员权限
- 启动三个springboot项目 app8081, app8082, app_server
- 进入localhost:8081/sysA/wel进入系统A
- 进入localhost:8082/sysB/wel进入系统B