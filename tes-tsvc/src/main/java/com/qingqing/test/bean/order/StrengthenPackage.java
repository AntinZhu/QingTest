package com.qingqing.test.bean.order;

import java.util.List;

/**
 * Created by zhujianxing on 2018/8/28.
 */
public class StrengthenPackage {
   private Integer gradeId;
   private List<StrengthenInfo> strengthenInfos;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public List<StrengthenInfo> getStrengthenInfos() {
        return strengthenInfos;
    }

    public void setStrengthenInfos(List<StrengthenInfo> strengthenInfos) {
        this.strengthenInfos = strengthenInfos;
    }
}
