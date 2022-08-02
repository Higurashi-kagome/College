#!/bin/bash
# 安装好 jdk、MySQL（配置好表数据）、Redis、node、解压 nacos、配置防火墙、配置阿里云安全组
# nacos
sh /usr/local/nacos/nacos-1.4.3/bin/shutdown.sh
/usr/local/nacos/nacos-1.4.3/bin/startup.sh -m standalone
# redis
systemctl stop redis
systemctl start redis
# 后端接口
nohup java -jar /usr/local/college/artifacts-8-2/api_gateway_jar/api-gateway.jar &
nohup java -jar /usr/local/college/artifacts-8-2/service_order_jar/service-order.jar &
nohup java -jar /usr/local/college/artifacts-8-2/service_acl_jar/service-acl.jar &
nohup java -jar /usr/local/college/artifacts-8-2/service_oss_jar/service-oss.jar &
nohup java -jar /usr/local/college/artifacts-8-2/service_cms_jar/service-cms.jar &
nohup java -jar /usr/local/college/artifacts-8-2/service_statistics_jar/service-statistics.jar &
nohup java -jar /usr/local/college/artifacts-8-2/service_edu_jar/service-edu.jar &
nohup java -jar /usr/local/college/artifacts-8-2/service_ucenter_jar/service-ucenter.jar &
nohup java -jar /usr/local/college/artifacts-8-2/service_msm_jar/service-msm.jar &
nohup java -jar /usr/local/college/artifacts-8-2/service_vod_jar/service-vod.jar &
# 后台管理系统
# 前台页面