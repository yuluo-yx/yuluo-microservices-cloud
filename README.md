# yuluo-microservice

![logo](./logo.png)

## 技术选型

| 服务功能           | 技术选型                                          | 主要作用                                         |
|--------------------|---------------------------------------------------|--------------------------------------------------|
| 服务发现与配置中心 | Spring Cloud Alibaba Nacos                        | 注册和发现服务，提供配置中心能力                 |
| 网关               | Spring Cloud GateWay                              | 微服务网关，为服务提供安全隔离。提供服务唯一入口 |
| RPC                | Spring Cloud Starter OpenFeign                    | 服务之间的 RP C调用                              |
| 数据存储           | MySQL/MongoDB                                     | 数据存储服务                                     |
| 缓存数据库         | Redis                                             | 提供缓存服务                                     |
| ORM框架            | Spring Data JPA/Mybatis-plus                      | 提供 ORM 映射，连接数据库                        |
| MVC框架            | Spring MVC                                        | 基于 MVC 设计模式的请求驱动类型的Web框架         |
| 服务保护           | Spring Cloud Alibaba Sentinel                     | 高可用流控防护组件                               |
| 前端               | Vite Vue3 TypeScript Pinia Element-plus SCSS Yarn | 构建前端项目                                     |
| 微服务框架         | Spring Cloud Alibaba/Spring Cloud                 | 微服务开发脚手架                                 |
| 分布式事务         | Spring Cloud Alibaba Seata                        | 提供分布式事务支持                               |
| 工具类             | Apache Commons-lang3/Hutool                       | 提供辅助工具类支持                               |
| 负载均衡           | Spring Cloud Loadbalancer                         | 客户端负载均衡器                                 |
| 链路追踪           | io.micrometer.tracing + Zipkin                    | 提供服务链路监控支持                             |
| 权限设计方案       | Spring Security / JWT                             | 提供用户登录验证和Token颁发                      |
| 日志记录功能       | 基于AOP实现，支持 excel 导出功能                  | 提供系统访问日志记录和关键操作记录               |
| 接口文档           | Knif4j                                            | 集成 Swagger，提供服务接口文档和接口请求测试     |
| 服务监控框架       | Spring Boot Admin                                 | 实时监控服务状态，可视化展示                     |
| 数据脱敏           | 对关键属性进行过滤，例如电话号码，邮箱等          | 保护后台数据安全                                 |
| 后端代码风格       | 基于 Alibaba 规范                                 | 代码更容易理解，可读性高                         |


## 基本功能
1. 用户登录与注册，用户邮箱验证登录
2. 日志记录，包括系统访问日志和系统操作日志
3. 服务监控，基于 Admin 监控
4. SQL日志跟踪
5. 数据脱敏功能，对敏感数据进行脱敏
6. 简易前端项目，可二次开发
7. 基于 Zipkin 链路监控
8. 接口文档

## 基础组件
|组件名| 下载地址                                          |
|---|-----------------------------------------------|
|Nacos 2.2.0| https://github.com/alibaba/nacos/releases     |
|Sentinel| https://github.com/alibaba/Sentinel/releases  |
|Zipkin| https://github.com/openzipkin/zipkin/releases |
|Seata| https://github.com/alibaba/Seata/releases     |

## 项目监控地址

| 模块                 | 地址                                                              |
|----------------------|-------------------------------------------------------------------|
| Spring Boot Admin    | http://localhost:8000                                             |
| Spring Boot EndPoint | 查看服务控制台 mappings 映射                                      |
| Nacos                | http://localhost:8848/nacos/                                      |
| Sentinel             | http://127.0.0.1:8080/#/dashboard/home                            |
| Zipkin               | http://localhost:9411/zipkin/                                     |
| Knif4j               | http://${spring.cloud.nacos.discovery.ip}:${server.port}/doc.html |
| Sql监控              | 整合 p6spy 输出完整 SQL 与执行时间监控                            |
| 前端项目             | http://127.0.0.1:9000/login.html                                  |
| Seata                | http://127.0.0.1:7091                                             |

## 代码结构
```markdwon
|--yuluo-microservices-parent
|--|--yuluo-microservices-auth              # 微服务权限模块
|--|--yuluo-microservices-common            # 微服务通用模块
|--|--|--yuluo-microservices-common-core    # 核心模块，工具类和依赖管理
|--|--|--yuluo-microservices-common-domain  # domain实体模块，存放实体类型
|--|--|--yuluo-microservices-common-mail    # 邮件模块，集成邮件功能
|--|--|--yuluo-microservices-common-log     # 日志模块，集成日志功能
|--|--|--yuluo-microservices-common-redis   # redis模块，集成redis功能
|--|--|--yuluo-microservices-common-TTL     # 线程间传递用户信息
|--|--yuluo-microservices-data              # 微服务数据模块
|--|--|--yuluo-microservices-data-jpa       # 基于MongoDB封装JPA数据接口
|--|--|--yuluo-microservices-data-mybatis   # 基于MyBatis-plus封装
|--|--yuluo-microservices-gateway           # 微服务网关
|--|--yuluo-microservices-docker            # 微服务部署模块，提供docker-compose部署方案
|--|--yuluo-microservices-frontend          # 微服务前端模块
|--|--yuluo-microservices-services          # 微服务模块
|--|--|--yuluo-module-system-api            # 系统调用API
|--|--|--yuluo-module-system-service        # 服务服务实现模块
|--|--yuluo-microservices-visual            # 微服务监控模块
```

## 项目启动基本步骤
1. 数据库配置
   运行根目录中的 sql 文件，将数据库文件导入到mysql中
2. 启动 Nacos 服务器 
   可选择建立 Nacos 命名空间，在修改 common.yml 中数据库和 Redis 配置之后，
   配置 mail 中的邮箱 pass，网上参考步骤，之后更新 nacos 的 groupId 和 tenant
   运行 script/nacos-quickstart.sh 一键导入 Nacos 配置文件(暂时不支持中文，谨慎使用)
3. 启动 Zipkin 链路监控服务
4. 进入 frontend 启动前端页面
   yarn 安装依赖
   yarn dev 运行
5. 可通过前端体验用户登录功能
6. 导入 postman.json 的接口配置文件到 postman 中
   发送对应请求，查看响应结果

## 优化方案

| 模块功能       | 优化方案                           |
|----------------|------------------------------------|
| RPC            | 使用 GRPC 替代 OpenFeign           |
| 链路监控       | 使用 Apache SkyWalking 替代 ZipKin |
| 单点登录       | 使用 Oauth2 加入单点登录功能       |
| 权限设计       | 设计用户权限                       |
| 文档           | 基于 VuePress 提供文档支持         |
| 动态数据源配置 |                                    |
