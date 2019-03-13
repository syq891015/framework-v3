Spring Boot 后台管理框架
#### 模块依赖
|模块 |依赖于 |备注 |数据源 |WEB程序 |后台管理程序
|--- |--- |--- |--- |--- |---
|framework-common |framework-parent |公共常量、工具类 |√ |√ |√
|framework-datasource | framework-common |Mybatis、Redis数据源相关 |√ |× |√
|framework-logging |framework-datasource framework-web framework-shiro | 用户日志注解、管理页面 |× |× |√
|framework-scheduleJob |framework-logging framework-datasource framework-web framework-shiro |定时任务及日志管理 |× |× |√
|framework-shiro |framework-datasource |shiro配置及相关工具类 |× |× |√
|framework-web |framework-common |Web配置、上传文件配置、异常统一处理及Web相关工具类 |× |√ |√
|framework-authority |framework-datasource framework-web framework-logging framework-shiro |后台管理程序（RBAC） |× |× |√

#### 备注
###### 1. 需将程序启动入口类，如Application放在com.myland包下

###### 2. 引入framework-shiro需要实现用户校验领域，并@Component