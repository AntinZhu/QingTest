package com.qingqing.test.bean.order;

import java.util.List;

/**
 * Created by zhujianxing on 2019/2/22.
 */
public class ServicePackage {
    private boolean isForce;
    private List<ServicePackageInfo> servicePackageInfoList;

    public boolean isForce() {
        return isForce;
    }

    public void setForce(boolean force) {
        isForce = force;
    }

    public List<ServicePackageInfo> getServicePackageInfoList() {
        return servicePackageInfoList;
    }

    public void setServicePackageInfoList(List<ServicePackageInfo> servicePackageInfoList) {
        this.servicePackageInfoList = servicePackageInfoList;
    }
}
