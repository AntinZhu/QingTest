package com.qingqing.test.dao.test.common;

import org.apache.ibatis.annotations.Param;

/**
 * Created by zhujianxing on 2019/5/28.
 */
public interface CommonMapper {

    void insert(@Param("sql") String sql);

    void delete(@Param("sql") String sql);
}
