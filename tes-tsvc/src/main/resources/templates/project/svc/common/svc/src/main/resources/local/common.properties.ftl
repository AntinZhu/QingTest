<#if (use_selfRedis!0) gt 0 >
redis.${svcName}.sentinel.url=172.22.12.12:6398,172.22.12.13:6393,172.22.12.14:6420
redis.${svcName}.sentinel.master.name=sentinel-172.22.12.12-6397
</#if>

<#if (use_userinfodp!0) gt 0 >
redis.userinfodpsvc.cluster.url=172.22.12.14:6394,172.22.12.14:6396,172.22.12.14:6398
</#if>
