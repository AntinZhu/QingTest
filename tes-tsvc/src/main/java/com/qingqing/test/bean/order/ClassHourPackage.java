package com.qingqing.test.bean.order;

/**
 * Created by zhujianxing on 2019/2/20.
 */
public class ClassHourPackage {
    private Long packageId;
    private String name;
    private Integer feeClassHour;
    private Integer freeClassHour;

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFeeClassHour() {
        return feeClassHour;
    }

    public void setFeeClassHour(Integer feeClassHour) {
        this.feeClassHour = feeClassHour;
    }

    public Integer getFreeClassHour() {
        return freeClassHour;
    }

    public void setFreeClassHour(Integer freeClassHour) {
        this.freeClassHour = freeClassHour;
    }
}
