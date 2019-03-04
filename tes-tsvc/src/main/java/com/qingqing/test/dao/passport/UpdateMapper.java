package com.qingqing.test.dao.passport;

import org.apache.ibatis.annotations.Param;

public interface UpdateMapper {

    void insert(@Param("sql") String sql);
}