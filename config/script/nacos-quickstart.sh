#!/bin/sh
echo "Nacos auto config started"

authConfig=$(cat ../yuluo-microservices-auth.yml)
commonConfig=$(cat ../yuluo-microservices-common.yml)
gatewayConfig=$(cat ../yuluo-microservices-gateway.yml)
mailConfig=$(cat ../yuluo-microservices-mail.yml)
serviceConfig=$(cat ../yuluo-module-system-service.yml)

# 组 ID
groupId="DEFAULT_GROUP"
tenant="ab140c36-ded9-491f-a178-37dfae795b5a"
type="yaml"

curl -X POST "127.0.0.1:8848/nacos/v1/cs/configs" -d "dataId=yuluo-microservices-mail.yml&group=${groupId}&content=${mailConfig}&tenant=${tenant}&type=${type}"
printf "\n"
curl -X POST "127.0.0.1:8848/nacos/v1/cs/configs" -d "dataId=yuluo-module-system-service.yml&group=${groupId}&content=${serviceConfig}&tenant=${tenant}&type=${type}"
printf "\n"
curl -X POST "127.0.0.1:8848/nacos/v1/cs/configs" -d "dataId=yuluo-microservices-auth.yml&group=${groupId}&content=${authConfig}&tenant=${tenant}&type=${type}"
printf "\n"
curl -X POST "127.0.0.1:8848/nacos/v1/cs/configs" -d "dataId=yuluo-microservices-common.yml&group=${groupId}&content=${commonConfig}&tenant=${tenant}&type=${type}"
printf "\n"
curl -X POST "127.0.0.1:8848/nacos/v1/cs/configs" -d "dataId=yuluo-microservices-gateway.yml&group=${groupId}&content=${gatewayConfig}&tenant=${tenant}&type=${type}"
printf "\n"

echo "Nacos config pushed successfully finished"