package com.qingqing.test.bean.order;

/**
 * Created by zhujianxing on 2019/2/22.
 */
public class ServicePackageInfo {
    private Long id;
    private Integer timeLength;
    private Double price;
    private boolean isRecommend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(Integer timeLength) {
        this.timeLength = timeLength;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isRecommend() {
        return isRecommend;
    }

    public void setRecommend(boolean recommend) {
        isRecommend = recommend;
    }
}
