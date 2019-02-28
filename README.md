Spring Boot 后台管理框架
#### 模块依赖
|模块 |依赖于
|--- |---
|framework-common |framework-parent
|framework-datasource | framework-common
|framework-logging |framework-datasource framework-web framework-shiro
|framework-scheduleJob |framework-logging framework-datasource framework-web framework-shiro
|framework-shiro |framework-datasource
|framework-web |framework-common
|framework-authority |framework-datasource framework-web framework-logging framework-shiro