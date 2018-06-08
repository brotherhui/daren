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
> 分别启动 flare-service-boot 和 flare-api-gateway<br>
> 以post方式访问http://localhost:50000/query/xxx<br>
> 以post方式访问http://localhost:50000/command/xxx<br>
> 以post方式访问http://localhost:50000/auth/query/xxx<br>
> 以post方式访问http://localhost:50000/auth/command/xxx<br>
