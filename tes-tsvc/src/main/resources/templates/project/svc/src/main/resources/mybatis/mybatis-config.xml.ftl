<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <typeAliases>
        <typeAlias alias="UserType" type="com.qingqing.common.auth.domain.UserType" />
    </typeAliases>

    <typeHandlers>
        <typeHandler handler="com.qingqing.common.mybatis.handler.GenericEnumTypeHandler" javaType="com.qingqing.common.auth.domain.UserType"/>
    </typeHandlers>
</configuration>
