# 前言

使用IDEA自带的maven时，若不配置镜像，下载lib包的速度会很慢。因此，在不另外在本地安装maven的情况下，直接使用IDEA自带的maven就可以，下面是配置镜像方法

# 配置镜像方法

1. 找到自带maven目录中的settings.xml，我的是D:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2018.1.5\plugins\maven\lib\maven3\conf\settings.xml
2. 在setting.xml中<mirrors></mirrors>中添加内容

```
<mirror>
    <id>alimaven</id>
    <name>aliyun maven</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    <mirrorOf>central</mirrorOf>       
</mirror>
```