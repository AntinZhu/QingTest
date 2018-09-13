package com.qingqing.test.bean.order;

import com.qingqing.test.bean.base.KeyAndValue;

import java.util.List;

/**
 * Created by zhujianxing on 2018/2/7.
 */
public class CoursePrice {
    private KeyAndValue grade;
    private List<SiteTypeAndPrice> siteTypeAnsPriceList;

    public CoursePrice() {}

    public CoursePrice(KeyAndValue grade, List<SiteTypeAndPrice> siteTypeAnsPriceList) {
        this.grade = grade;
        this.siteTypeAnsPriceList = siteTypeAnsPriceList;
    }

    public KeyAndValue getGrade() {
        return grade;
    }

    public void setGrade(KeyAndValue grade) {
        this.grade = grade;
    }

    public List<SiteTypeAndPrice> getSiteTypeAnsPriceList() {
        return siteTypeAnsPriceList;
    }

    public void setSiteTypeAnsPriceList(List<SiteTypeAndPrice> siteTypeAnsPriceList) {
        this.siteTypeAnsPriceList = siteTypeAnsPriceList;
    }

    public static class SiteTypeAndPrice{
        private OrderSiteType siteType;
        private double price;

        public SiteTypeAndPrice(OrderSiteType siteType, double price) {
            this.siteType = siteType;
            this.price = price;
        }

        public OrderSiteType getSiteType() {
            return siteType;
        }

        public double getPrice() {
            return price;
        }
    }
}
