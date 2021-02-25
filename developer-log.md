# Infinity开发计划

Infinity 基础开发包含两块

* 大数据平台的搭建
* web代码开发

### 开发日志



#### 2021.02.24

今日进度

* 大数据虚拟服务器安装

> 准备一台虚拟机，安装 CentOS 7.8操作系统，配置基础服务：IP与主机相关配置，自登陆免密等

* Infinity 历史项目代码结构调整

> 按照新的思路，对之前写的部分代码已经整个代码工程的结构进行调整。将web相关的代码放置在 infinity-web 模块下，不再对其使用多模块放置，保留 infinity-common 用于放置公共代码

#### 2021.02.25

今日进度

* 安装hadoop和hive两个组件

> 在虚拟主机上完成hadoop和hive的安装，使两个组件均正常运行

* 对代码结构调整

> 讲common模块分为common和common-4web，在common-4web模块中可以添加spring相关的工具代码和业务代码，以提高代码使用率

* 添加SpringCloud支持
* 添加Infinity-monitor服务

> Infinity-monitor服务用于进行服务的监控和统计，目前添加了对hdfs和yarn使用的监控和统计