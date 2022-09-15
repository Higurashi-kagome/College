#!/bin/bash
# 安装好 jdk、MySQL（配置好用户名密码、配置好表数据、运行）、Redis（运行）、Node.js、解压并运行 Nacos、配置防火墙、配置云安全组
# nacos
sh /usr/local/nacos/nacos-server-1.4.3/bin/shutdown.sh
/usr/local/nacos/nacos-server-1.4.3/bin/startup.sh -m standalone
# cat /usr/local/nacos/nacos-server-1.4.3/logs/start.out
# redis
systemctl stop redis
systemctl start redis
# MySQL
systemctl stop mysqld
systemctl start mysqld
# 后端接口
# -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms1024m -Xmx1024m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC
nohup java -jar -Xms10m -Xmx100m /usr/local/college/artifacts-8-2/api_gateway_jar/api-gateway.jar &
nohup java -jar -Xms10m -Xmx100m /usr/local/college/artifacts-8-2/service_order_jar/service-order.jar &
nohup java -jar -Xms10m -Xmx100m /usr/local/college/artifacts-8-2/service_acl_jar/service-acl.jar &
nohup java -jar -Xms10m -Xmx100m /usr/local/college/artifacts-8-2/service_oss_jar/service-oss.jar &
nohup java -jar -Xms10m -Xmx100m /usr/local/college/artifacts-8-2/service_cms_jar/service-cms.jar &
nohup java -jar -Xms10m -Xmx100m /usr/local/college/artifacts-8-2/service_statistics_jar/service-statistics.jar &
nohup java -jar -Xms10m -Xmx100m /usr/local/college/artifacts-8-2/service_edu_jar/service-edu.jar &
nohup java -jar -Xms10m -Xmx100m /usr/local/college/artifacts-8-2/service_ucenter_jar/service-ucenter.jar &
nohup java -jar -Xms10m -Xmx100m /usr/local/college/artifacts-8-2/service_msm_jar/service-msm.jar &
nohup java -jar -Xms10m -Xmx100m /usr/local/college/artifacts-8-2/service_vod_jar/service-vod.jar &
# 后台管理系统
# 前台页面