app.id=${svcName}
apollo.cacheDir=./src/main/resources/local
apollo.meta=http://local.apollo.idc.cedu.cn
apollo.bootstrap.enabled=true

#actuator的，暴露详细健康状态
management.endpoint.health.show-details=always
management.endpoint.readiness.show-details=always
management.endpoints.web.exposure.include=*

#actuator 访问路径替换 默认是/actuator 替换成/mgtact
management.endpoints.web.base-path=/mgtact

#这个配置是关闭metrics的数据推送。如果开启必须同时配置正确的推送地址，否则一直进行推送会推送失败而一直抛出异常。 如果接入了Micrometer 就不要配。
#Micrometer相关参考https://wiki.changingedu.com/pages/viewpage.action?pageId=200180496
management.metrics.export.open-falcon.enabled = false