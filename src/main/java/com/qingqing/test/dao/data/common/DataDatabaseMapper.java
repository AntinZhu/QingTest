package com.qingqing.test.dao.data.common;

import org.apache.ibatis.annotations.Param;

public interface DataDatabaseMapper {

    int update(@Param("sql") String sql);
}