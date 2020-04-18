package com.qingqing.test.bean.project;

import java.util.Map;

/**
 * Created by zhujianxing on 2019/1/31.
 */
public enum ProjectCustomConfigType {
  ALL("所有类型"),
  MYSQL_DB("数据库配置"),
  REDIS_SELF("服务自用Redis"),
  REDIS_USER_INFO_DP("userInfoDp"),
  ;

  private final String name;

  ProjectCustomConfigType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
