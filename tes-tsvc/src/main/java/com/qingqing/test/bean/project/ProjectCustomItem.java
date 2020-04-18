package com.qingqing.test.bean.project;

/**
 * Created by zhujianxing on 2019/1/31.
 */
public class ProjectCustomItem {
  private ProjectCustomConfigType projectCustomConfigType;
  private String customJson;

  public ProjectCustomItem(){}

  public ProjectCustomItem(String customJson, ProjectCustomConfigType projectCustomConfigType) {
    this.customJson = customJson;
    this.projectCustomConfigType = projectCustomConfigType;
  }

  public ProjectCustomConfigType getProjectCustomConfigType() {
    return projectCustomConfigType;
  }

  public void setProjectCustomConfigType(ProjectCustomConfigType projectCustomConfigType) {
    this.projectCustomConfigType = projectCustomConfigType;
  }

  public String getCustomJson() {
    return customJson;
  }

  public void setCustomJson(String customJson) {
    this.customJson = customJson;
  }
}
