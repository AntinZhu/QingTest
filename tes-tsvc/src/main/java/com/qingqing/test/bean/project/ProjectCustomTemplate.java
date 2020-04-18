package com.qingqing.test.bean.project;

/**
 * Created by zhujianxing on 2019/1/31.
 */
public class ProjectCustomTemplate {
  private String templateDir;
  private String templateFile;
  private String destDir;

  public ProjectCustomTemplate(){}

  public ProjectCustomTemplate(String templateDir, String templateFile, String destDir) {
    this.templateDir = templateDir;
    this.templateFile = templateFile;
    this.destDir = destDir;
  }

  public String getTemplateDir() {
    return templateDir;
  }

  public void setTemplateDir(String templateDir) {
    this.templateDir = templateDir;
  }

  public String getTemplateFile() {
    return templateFile;
  }

  public void setTemplateFile(String templateFile) {
    this.templateFile = templateFile;
  }

  public String getDestDir() {
    return destDir;
  }

  public void setDestDir(String destDir) {
    this.destDir = destDir;
  }
}
