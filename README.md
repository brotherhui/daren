# flare
### 1.聚合服务
> flare-service-boot

### 2.API网关
> flare-api-gateway

### 3.微服务
- 广告
> flare-advertisement-api<br>
> flare-advertisement-data<br>
> flare-advertisement-domain<br>
> flare-advertisement-service<br>

- 身份
> flare-identity-api<br>
> flare-identity-data<br>
> flare-identity-domain<br>
> flare-identity-service<br>

- 订单
> flare-order-api<br>
> flare-order-data<br>
> flare-order-domain<br>
> flare-order-service<br>

- 支付
> flare-payment-api<br>
> flare-payment-data<br>
> flare-payment-domain<br>
> flare-payment-service<br>

- 声誉
> flare-reputation-api<br>
> flare-reputation-data<br>
> flare-reputation-domain<br>
> flare-reputation-service<br>

- 标签
> flare-tag-api<br>
> flare-tag-data<br>
> flare-tag-domain<br>
> flare-tag-service<br>

### 4.构建方式
> flare-parent工程下执行 mvn clean install 或 mvn clean package

### 5.运行方式
- 分别启动 flare-service-boot 和 flare-api-gateway
> 无权限验证以get\post\put\delete方式访问<br>
> http://localhost:50000/api/{function}<br>
> 
> 有权限验证以get\post\put\delete方式访问<br>
> http://localhost:50000/auth/api/{function}<br>

### 6.包说明
- flare-parent
> 所有工程的父工程<br>
- flare-dependencies
> 所有jar依赖都定义在这个包下<br>
- flare-common
> 包存放整个后端服务所使用到的通用类，例如抽象服务接口、消息模型、接口返回结果等<br>
- flare-util
> 封装后端服务所使用到的工具类<br>
- flare-domain
> 封装实体的抽象基类<br>
