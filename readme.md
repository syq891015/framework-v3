##1、

####1.1、framework-datasource引入framework-common

####1.2、framework-web引入framework-common

####1.3、framework-shiro引入framework-datasource

####1.4、framework-logging引入framework-datasource、framework-web、framework-shiro

####1.5、framework-authority引入framework-datasource、framework-web、framework-logging、framework-shiro

####1.6、framework-scheduleJob引入framework-datasource、framework-web、framework-logging、framework-shiro

##2、

####2.1、如果是WEB程序，则引入framework-web

####2.2、如果只需要数据源，则引入framework-datasource

####2.3、如果需要shiro，则引入framework-shiro

####2.4、如果需要logging，则引入framework-logging

####2.5、如果需要RBAC，则引入framework-authority

##3、需将程序启动入口类，如Application放在com.myland包下

##4、应引入：hibernate-validation schedule Element-UI

##5、引入framework-shiro需要实现用户校验领域，并且给securityManager设置realm

##6、创建前端项目步骤

####6.1、vue init webpack 项目名称
####6.2、npm install axios element-ui font-awesome js-cookie vue-i18n
####6.3、npm install node-sass sass-loader --save-dev
####6.4、config/index.js 8080 =》 9527
####6.5、dev.env.js BASE_API: '"http://localhost:8080"'
