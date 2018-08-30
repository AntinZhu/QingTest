package com.qingqing.test.dao.data;

import com.qingqing.test.domain.pay.ThirdPayBrief;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThirdPayBriefMapper {

	ThirdPayBrief findByQingQingTradeNo(String qingqingTradeNo);

	List<ThirdPayBrief> selectListByOrderIdAndOrderType(@Param("relatedOrderType") Integer orderType, @Param("relatedOrderId") Long orderId);
}