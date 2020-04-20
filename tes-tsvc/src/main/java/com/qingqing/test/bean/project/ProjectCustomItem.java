package com.qingqing.test.bean.project;

/**
 * Created by zhujianxing on 2019/1/31.
 */
public class ProjectCustomItem {
  private ProjectCustomConfigType itemType;
  private String customJson;

  public ProjectCustomItem(){}

  public ProjectCustomItem(String customJson, ProjectCustomConfigType itemType) {
    this.customJson = customJson;
    this.itemType = itemType;
  }

  public ProjectCustomConfigType getItemType() {
    return itemType;
  }

  public void setItemType(ProjectCustomConfigType itemType) {
    this.itemType = itemType;
  }

  public String getCustomJson() {
    return customJson;
  }

  public void setCustomJson(String customJson) {
    this.customJson = customJson;
  }
}
