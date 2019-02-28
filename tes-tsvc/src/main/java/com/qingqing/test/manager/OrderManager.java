package com.qingqing.test.manager;

import com.qingqing.api.proto.v1.BankProto.GetSupportBanksResponse;
import com.qingqing.api.proto.v1.OrderCommonEnum.DiscountType;
import com.qingqing.api.proto.v1.OrderCommonEnum.OrderCourseChargeType;
import com.qingqing.api.proto.v1.OrderCommonEnum.OrderCreateType;
import com.qingqing.api.proto.v1.OrderCommonEnum.OrderSiteType;
import com.qingqing.api.proto.v1.OrderCommonEnum.OrderType;
import com.qingqing.api.proto.v1.Pay;
import com.qingqing.api.proto.v1.Pay.GeneralOrderPaymentSummaryV2Response;
import com.qingqing.api.proto.v1.Pay.GeneralOrderPaymentSummaryV3Request;
import com.qingqing.api.proto.v1.Pay.OrderPayType;
import com.qingqing.api.proto.v1.Pay.PayGeneralOrderSubmitRequest;
import com.qingqing.api.proto.v1.Pay.PayResult;
import com.qingqing.api.proto.v1.TeacherProto;
import com.qingqing.api.proto.v1.Time.TimeParam;
import com.qingqing.api.proto.v1.app.AppCommon.DeviceIdentification;
import com.qingqing.api.proto.v1.app.AppCommon.PlatformType;
import com.qingqing.api.proto.v1.app.AppCommon.SourceChannel;
import com.qingqing.api.proto.v1.order.Order.AddGroupOrderRequestV2;
import com.qingqing.api.proto.v1.order.Order.ApiStrengthenPackageInfoForOrder;
import com.qingqing.api.proto.v1.order.Order.JoinGroupOrderRequest;
import com.qingqing.api.proto.v1.order.Order.OrderModeUnit;
import com.qingqing.api.proto.v1.order.Order.StudentAddGroupOrderResponse;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.common.util.OrderIdEncoder;
import com.qingqing.common.util.TimeUtil;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.bean.order.AddOrderResultBean;
import com.qingqing.test.bean.order.CoursePriceType;
import com.qingqing.test.bean.order.StudentAddOrderBean;
import com.qingqing.test.bean.order.TeacherInfoForOrderBean;
import com.qingqing.test.bean.order.response.OrderCourseListResponse;
import com.qingqing.test.bean.pay.PayBriefListResponse;
import com.qingqing.test.bean.pay.PayType;
import com.qingqing.test.bean.pay.PrePayBean;
import com.qingqing.test.bean.pay.ThirdPayBriefBean;
import com.qingqing.test.bean.pay.request.PayRequestBean;
import com.qingqing.test.bean.pay.request.PayRequestBeanV2;
import com.qingqing.test.bean.pay.request.PrePayRequestBean;
import com.qingqing.test.bean.pay.request.PrePayRequestBeanV2;
import com.qingqing.test.client.ApiPiClient;
import com.qingqing.test.client.PtClient;
import com.qingqing.test.controller.converter.BaseConverter;
import com.qingqing.test.controller.converter.OrderConverter;
import com.qingqing.test.controller.converter.PayConverter;
import com.qingqing.test.domain.order.OrderCourseV1;
import com.qingqing.test.domain.pay.ThirdPayBrief;
import com.qingqing.test.service.order.OrderCourseService;
import com.qingqing.test.service.pay.StudentBalanceService;
import com.qingqing.test.service.pay.ThirdPayBriefService;
import com.qingqing.test.service.user.StudentService;
import com.qingqing.test.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@Component
public class OrderManager {
    private static final Logger logger = LoggerFactory.getLogger(OrderManager.class);

    @Autowired
    private ApiPiClient apiPiClient;
    @Autowired
    private PtClient ptClient;
    @Autowired
    private ThirdPayBriefService thirdPayBriefService;
    @Autowired
    private OrderCourseService orderCourseService;
    @Autowired
    private StudentBalanceService studentBalanceService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    public String test(){
        GetSupportBanksResponse response = apiPiClient.listBank();
        return String.valueOf(response.getBanksCount());
    }

    public TeacherInfoForOrderBean detailForOrder(Long studentId, Long teacherId){
        TeacherProto.SimpleQingQingTeacherIdRequest request = TeacherProto.SimpleQingQingTeacherIdRequest.newBuilder()
                .setQingqingTeacherId(userService.encodeUser(UserType.teacher, teacherId)).build();
        TeacherProto.TeacherDetailForStudentToOrderResponse resp = ptClient.detailForOrder(request, studentId);

        return OrderConverter.converterToInfoBean(resp);
    }

    public AddOrderResultBean addOrder(StudentAddOrderBean addOrderBean){
        AddGroupOrderRequestV2.Builder builder = AddGroupOrderRequestV2.newBuilder();
        builder.setQingqingTeacherId(addOrderBean.getQingqingTeacherId());

        String qingqingStudentId = userService.encodeUser(UserType.student, addOrderBean.getStudentId());
        builder.setLeaderQingqingUserId(qingqingStudentId);

        builder.addQingqingStudentIds(qingqingStudentId);
        builder.setCreateType(OrderCreateType.attention_oct);
        builder.setGradeId(addOrderBean.getGradeId());
        builder.setCourseId(addOrderBean.getCourseId());
        builder.setRemark("from api test server");
        builder.setChargeType(OrderCourseChargeType.formal_occt);
        if(addOrderBean.getPackageCourseId() != null){
            builder.setCoursePackageId(addOrderBean.getPackageCourseId());
            builder.setDiscountType(DiscountType.discount_package_discount_type);
        }else if(addOrderBean.getContentPackageId() != null){
            builder.setContentPackageRelationId(addOrderBean.getContentPackageId());
            builder.setDiscountType(DiscountType.content_package_discount_type);
        }

        CoursePriceType priceType = CoursePriceType.valueOf(addOrderBean.getCoursePriceType());
        builder.setPriceType(priceType.getPriceType());

        builder.addAllOrderModeUnits(getNormalOrderModeUnits(addOrderBean));
        if(addOrderBean.getStrengthenType() > 0){
            ApiStrengthenPackageInfoForOrder.Builder strengthenBuilder = ApiStrengthenPackageInfoForOrder.newBuilder();
            strengthenBuilder.setStrengthenPackageType(addOrderBean.getStrengthenType());
            strengthenBuilder.setStrengthenOrderModeUnit(getStrengthenModeUnits(addOrderBean));

            builder.setStrengthenPackageInfo(strengthenBuilder);
        }

        // 服务包
        if(addOrderBean.getServicePackageId() != null && addOrderBean.getServicePackageId() > 0){
            builder.setServicePackageId(addOrderBean.getServicePackageId());
        }

        StudentAddGroupOrderResponse response = ptClient.studentAddOrderV2(builder.build(), addOrderBean.getStudentId());

        return OrderConverter.convertAddOrderResult(response);
    }

    public PrePayBean getPrePayInfo(PrePayRequestBean requestBean){
        CoursePriceType coursePriceType = CoursePriceType.valueOf(requestBean.getCoursePriceType());
        GeneralOrderPaymentSummaryV3Request request = GeneralOrderPaymentSummaryV3Request.newBuilder()
                .setSourceChannel(SourceChannel.valueOf(requestBean.getSourceChannel()))
                .setQingqingOrderId(requestBean.getQingqingOrderId())
                .setOrderType(coursePriceType.getOrderType())
                .build();
        GeneralOrderPaymentSummaryV2Response response = ptClient.prePayForGeneralOrder(request, requestBean.getStudentId(), UserType.student);

        return PayConverter.convertToPrePayBean(response);
    }

    public PrePayBean getPrePayInfo(PrePayRequestBeanV2 requestBean){
        GeneralOrderPaymentSummaryV3Request request = GeneralOrderPaymentSummaryV3Request.newBuilder()
                .setSourceChannel(SourceChannel.valueOf(requestBean.getSourceChannel()))
                .setQingqingOrderId(requestBean.getQingqingOrderId())
                .setOrderType(OrderType.valueOf(requestBean.getOrderType()))
                .build();
        GeneralOrderPaymentSummaryV2Response response = ptClient.prePayForGeneralOrder(request, requestBean.getUserId(), requestBean.getUserType());

        return PayConverter.convertToPrePayBean(response);
    }

    private OrderModeUnit getStrengthenModeUnits(StudentAddOrderBean addOrderBean) {
        Integer strengthenTimes = (addOrderBean.getCourseTimes() * addOrderBean.getStrengthenTimes()) / addOrderBean.getNormalTimes();
        Integer halfHourLength = 10 / 5;
        Integer start = 0;
        Date courseDate = TimeUtil.dayAfterNow(((addOrderBean.getClassHour() / 5) * addOrderBean.getCourseTimes())/ 28 + 1);

        OrderModeUnit.Builder builder6 = OrderModeUnit.newBuilder();
        TimeParam.Builder timeBuilder = TimeParam.newBuilder();
        for(int i = 0; i < strengthenTimes; i++){
            timeBuilder.setDate(TimeUtil.dateToString(courseDate, TimeUtil.DATE_TO_YEAR_MONTH_DAY));
            timeBuilder.setStartBlock(start);
            timeBuilder.setEndBlock(start + (halfHourLength -1));
            builder6.addTimeParams(timeBuilder.build());

            start = start + halfHourLength;
            if(start > 28){
                start = 0;
                courseDate = TimeUtil.dayAfter(courseDate, 1);
            }
        }
        builder6.setSiteType(OrderSiteType.live_ost);
        return builder6.build();
    }

    private List<OrderModeUnit> getNormalOrderModeUnits(StudentAddOrderBean addOrderBean) {
        Integer courseTimes = addOrderBean.getCourseTimes();
        Integer classHour = addOrderBean.getClassHour();
        Long studentAddressId = addOrderBean.getAddressId();
        Integer orderSiteTypeValue = addOrderBean.getOrderSiteType();
        com.qingqing.test.bean.order.OrderSiteType orderSiteType = com.qingqing.test.bean.order.OrderSiteType.valueOf(orderSiteTypeValue);

        List<OrderModeUnit> omus = new ArrayList<OrderModeUnit>();
        //普通下单
        {
            OrderModeUnit.Builder builder6 = OrderModeUnit.newBuilder();
            TimeParam.Builder timeBuilder = TimeParam.newBuilder();
            Date courseDate = TimeUtil.dayAfterNow(1);
            Integer halfHourLength = classHour / 5;
            Integer start = 0;
            for(int i = 0; i < courseTimes; i++){
                timeBuilder.setDate(TimeUtil.dateToString(courseDate, TimeUtil.DATE_TO_YEAR_MONTH_DAY));
                timeBuilder.setStartBlock(start);
                timeBuilder.setEndBlock(start + (halfHourLength -1));
                builder6.addTimeParams(timeBuilder.build());

                start = start + halfHourLength;
                if(start > 28){
                    start = 0;
                    courseDate = TimeUtil.dayAfter(courseDate, 1);
                }
            }

            builder6.setSiteType(orderSiteType.getSiteTypeProto());
            if(orderSiteType.getSiteTypeProto().equals(OrderSiteType.student_home_ost)){
                builder6.setAddressId(studentAddressId);
            }
            omus.add(builder6.build());
        }

        return omus;
    }

    public SimpleResponse payForOrder(PayRequestBean payRequest) {
        Double orderAmount = payRequest.getOrderAmount();
        PayType payType = PayType.parseKey(payRequest.getPayType());
        CoursePriceType coursePriceType = CoursePriceType.valueOf(payRequest.getCoursePriceType());

        return payForOrder(payRequest.getQingqingOrderId(), coursePriceType.getOrderType().name(), orderAmount, payRequest.getStageConfigId(), payRequest.getStudentId(), "student", payType);
    }

    public SimpleResponse payForOrder(PayRequestBeanV2 payRequest) {
        PayType payType = PayType.valueOf(payRequest.getPayType());
        return payForOrder(payRequest.getQingqingOrderId(), payRequest.getOrderType(), payRequest.getOrderAmount(), payRequest.getStageConfigId(), payRequest.getUserId(), payRequest.getUserType(), payType);
    }

    public SimpleResponse payForOrder(String qingqingOrderId, String orderType, Double orderAmount, Long stageConfigId, Long userId, String userType,  PayType payType) {
        OrderPayType backupPayType = OrderPayType.alipay;
        Double balancePayAmount = orderAmount;
        if(!PayType.qingqing_balance.equals(payType)){
            backupPayType = payType.getOrderPayType();
            balancePayAmount = 0.0;
        }

        PayGeneralOrderSubmitRequest.Builder request = PayGeneralOrderSubmitRequest.newBuilder();
        request.setQingqingOrderId(qingqingOrderId)
                .setOrderPayType(OrderPayType.qingqing_balance)
                .setBackupOrderPayType(backupPayType)
                .setOrderType(OrderType.valueOf(orderType))
                .setMoney(String.valueOf(balancePayAmount));

        if(stageConfigId != null){
            request.setCmbInstallmentId(stageConfigId);
            request.setInstallmentParam(Pay.InstallmentRequestParam.newBuilder().setInstallmentId(stageConfigId));
            request.setBaiduPayUserInfo(Pay.BaiduPayUserInfo.newBuilder().setEmail("55555555@qq.com").setPhoneNumber("15121121059").setUserName("张三"));
            request.setLovehaimiPayUserInfo(Pay.LoveHaiMiPayUserInfo.newBuilder().setPhoneNumber("15121121059").setUserName("张三"));
        }

        PayResult payResult = ptClient.payForOrder(request.build(), userId, UserType.valueOf(userType));
        BaseResponse baseResponse = BaseConverter.convertBaseResponse(payResult.getResponse());
        switch (baseResponse.getError_code()){
            case 1001:
                baseResponse.setHint_message("钱包余额不足");
                break;
            case 1011:
                baseResponse.setError_code(0);
                break;
            case 1031:
                baseResponse.setHint_message("订单已经支付");
                break;
            case 1131:
                baseResponse.setHint_message("调用支付服务出错");
                break;
            default:
                break;
        }

        return new SimpleResponse(baseResponse);
    }

    public PayBriefListResponse getPayBriefInfo(Integer orderType, Long orderId){
        PayBriefListResponse result = new PayBriefListResponse();
        result.setResponse(BaseResponse.SUCC_RESP);

        List<ThirdPayBrief> payBriefs = thirdPayBriefService.selectListByOrderTypeAndOrderId(orderType, orderId);
        if(!payBriefs.isEmpty()){
            List<ThirdPayBriefBean> beanList = new ArrayList<>(payBriefs.size());
            for(ThirdPayBrief thirdPayBrief : payBriefs){
                ThirdPayBriefBean bean = new ThirdPayBriefBean();
                PayType payType = PayConverter.convertFromOrderPayTypeV3(thirdPayBrief.getThirdPaymentTypeV3());
                if(PayType.unknown.equals(payType)){
                    continue;
                }

                bean.setPayStatus(thirdPayBrief.getPayTime() == null? "未支付":"已支付");
                bean.setPayTypeKey(payType.getKey());
                bean.setPayTypeName(payType.getName());
                bean.setQingqingTradeNo(thirdPayBrief.getQingqingTradeNo());
                bean.setTradeId(thirdPayBrief.getTradeId());

                beanList.add(bean);
            }

            result.setPayBriefList(beanList);
        }

        return result;
    }

    public OrderCourseListResponse getOrderCourseListByOrderId(Long orderId){
        List<OrderCourseV1> orderCourseV1s = orderCourseService.selectByOrderId(orderId);

        OrderCourseListResponse result = new OrderCourseListResponse();
        result.setResponse(BaseResponse.SUCC_RESP);
        result.setOrderCourseV1List(orderCourseV1s);

        return result;
    }

    public void madeUpGroupOrder(Long groupOrderId, Long studentId){
        // 1018:满员 1020：状态异常 1025:停止生源供给 1019:已参团

        JoinGroupOrderRequest.Builder request = JoinGroupOrderRequest.newBuilder().setDeviceIdentification(getDeviceIdentification())
                .setQingqingGroupOrderId(OrderIdEncoder.encodeOrderId(groupOrderId));
        while(true){
            studentId++;
            if(!studentService.isUserExist(studentId)){
                continue;
            }

            request.setQingqingStudentId(userService.encodeUser(UserType.student, studentId));
            StudentAddGroupOrderResponse response = ptClient.joinGroup(request.build(), studentId);
            boolean isMadeUp = false;
            switch (response.getResponse().getErrorCode()){
                case 0:
                    String qingqingSubOrderId = response.getQingqingGroupSubOrderId();
                    payByBalance(qingqingSubOrderId, OrderType.group_order_type.getNumber(), studentId);
                    break;
                case 1025:
                case 1019:
                    // 该家长无法参团
                    break;
                case 1020:
                    isMadeUp = true;
                    break;
                case 1018: // 已满员
                    // 查询剩余未支付的子订单，使用钱包支付
                    isMadeUp = true;
                    break;
                default:
                    logger.error("unknown error when join group, resp:" + JsonUtil.format(response.getResponse()));
                    break;
            }

            if(isMadeUp){
                break;
            }
        }


    }

    public boolean payByBalance(String qingqingOrderId, Integer orderType, Long studentId){
        try{
            GeneralOrderPaymentSummaryV3Request prePayRequest = GeneralOrderPaymentSummaryV3Request.newBuilder()
                    .setSourceChannel(SourceChannel.app_source_channel)
                    .setQingqingOrderId(qingqingOrderId)
                    .setOrderType(OrderType.valueOf(orderType))
                    .build();
            GeneralOrderPaymentSummaryV2Response prePayResponse = ptClient.prePayForGeneralOrder(prePayRequest, studentId, UserType.student);

            studentBalanceService.addUserBalance(studentId, prePayResponse.getAllNeedExtraPay());

            PayGeneralOrderSubmitRequest.Builder request = PayGeneralOrderSubmitRequest.newBuilder();
            request.setQingqingOrderId(qingqingOrderId)
                    .setOrderPayType(OrderPayType.qingqing_balance)
                    .setBackupOrderPayType(OrderPayType.alipay)
                    .setOrderType(OrderType.valueOf(orderType))
                    .setMoney(String.valueOf(prePayResponse.getAllNeedExtraPay()));

            PayResult payResult = ptClient.payForOrder(request.build(), studentId, UserType.student);
            return payResult.getResponse().getErrorCode() == 0;
        }catch(Exception e){
            logger.error("pay by balance error, studentId:{} orderId:{} orderType:", studentId, OrderIdEncoder.decodeQingqingOrderId(qingqingOrderId), orderType, e);
            return false;
        }
    }

    private DeviceIdentification getDeviceIdentification(){
        return DeviceIdentification.newBuilder().setPlatformType(PlatformType.ios).setImei("1").setIdfa("2").build();
    }
}
