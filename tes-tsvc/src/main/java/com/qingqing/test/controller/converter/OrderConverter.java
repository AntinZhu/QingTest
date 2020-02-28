package com.qingqing.test.controller.converter;

import com.qingqing.api.proto.api_course_class_hour.ApisvcCourseClassHour;
import com.qingqing.api.proto.order.ClassOrderProto.JoinClassOrderResponse;
import com.qingqing.api.proto.order.ClassOrderProto.TeacherDetailForLiveClassOrderResponse;
import com.qingqing.api.proto.v1.ApiServicePackageProto.ApiServicePackageInfo;
import com.qingqing.api.proto.v1.ApiServicePackageProto.ApiServicePackageInfoForOrder;
import com.qingqing.api.proto.v1.GradeCourseProto;
import com.qingqing.api.proto.v1.GradeCourseProto.TeacherCoursePrice;
import com.qingqing.api.proto.v1.OrderCommonEnum.GroupSubOrderStatus;
import com.qingqing.api.proto.v1.TeacherProto;
import com.qingqing.api.proto.v1.coursecontentpackage.CourseContentPackageProto.CourseContentPackageForOrder;
import com.qingqing.api.proto.v1.coursecontentpackage.CourseContentPackageProto.CourseContentPackagePriceForOrder;
import com.qingqing.api.proto.v1.coursepackage.CoursePackageProto.CoursePackageUnit;
import com.qingqing.api.proto.v1.coursepackage.CoursePackageProto.CoursePackageUnits;
import com.qingqing.api.proto.v1.order.Order.StudentAddGroupOrderResponse;
import com.qingqing.api.proto.v1.strengthen.ApiStrengthenProto.ApiStrengthenInfo;
import com.qingqing.api.proto.v1.strengthen.ApiStrengthenProto.ApiTeacherGradeAndSupportStrengthenInfo;
import com.qingqing.common.auth.domain.UserType;
import com.qingqing.common.util.OrderIdEncoder;
import com.qingqing.common.util.UserIdEncoder;
import com.qingqing.common.util.converter.lang.DoubleCompareUtil;
import com.qingqing.test.bean.base.BaseResponse;
import com.qingqing.test.bean.base.KeyAndValue;
import com.qingqing.test.bean.order.*;
import com.qingqing.test.bean.order.CoursePrice.SiteTypeAndPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderConverter {

    private static final Logger logger = LoggerFactory.getLogger(OrderConverter.class);

    public static TeacherInfoForOrderBean converterToInfoBean(Long teacherId, TeacherDetailForLiveClassOrderResponse response){
        // 获取所有支持的年级，科目，上门方式
        Map<String, String> courseMap = new HashMap<String, String>();
        List<CourseOrderBean> courseOrderBeanList = new ArrayList<CourseOrderBean>();

        for (TeacherCoursePrice teacherCoursePrice : response.getCoursePricesList()) {
            CoursePriceType coursePriceType = CoursePriceType.valueOf(teacherCoursePrice.getPriceType());
            if(!CoursePriceType.class_course.equals(coursePriceType)){
                continue;
            }

            List<CoursePrice> coursePriceList = new ArrayList<>(teacherCoursePrice.getPriceInfosCount());
            for (GradeCourseProto.GradeCoursePriceInfoV2 courseInfo :  teacherCoursePrice.getPriceInfosList()){
                KeyAndValue grade = new KeyAndValue(String.valueOf(courseInfo.getGradeCourse().getGradeId()), courseInfo.getGradeCourse().getGradeName());
                Integer courseId = courseInfo.getGradeCourse().getCourseId();
                courseMap.put(String.valueOf(courseId), courseInfo.getGradeCourse().getCourseName());

                List<SiteTypeAndPrice> siteTypeAndPriceList = new ArrayList<>(4);
//                if(DoubleCompareUtil.gtZero(courseInfo.getPriceInfo().getPriceToStudentHome())){
//                    siteTypeAndPriceList.add(new SiteTypeAndPrice(OrderSiteType.student_home, courseInfo.getPriceInfo().getPriceToStudentHome()));
//                }
//                if(DoubleCompareUtil.gtZero(courseInfo.getPriceInfo().getPriceToTeacherHome())){
//                    siteTypeAndPriceList.add(new SiteTypeAndPrice(OrderSiteType.teacher_home, courseInfo.getPriceInfo().getPriceToTeacherHome()));
//                }
                if(DoubleCompareUtil.gtZero(courseInfo.getPriceInfo().getPriceForLiving())){
                    siteTypeAndPriceList.add(new SiteTypeAndPrice(OrderSiteType.live, courseInfo.getPriceInfo().getPriceForLiving()));
                }else{
                    continue;
                }

                coursePriceList.add(new CoursePrice(grade, siteTypeAndPriceList));
            }

            courseOrderBeanList.add(new CourseOrderBean(coursePriceType, coursePriceList));
        }

        TeacherInfoForOrderBean bean = new TeacherInfoForOrderBean();
        bean.setResponse(BaseResponse.SUCC_RESP);
        bean.setSupportCourseList(BaseConverter.convertToKeyAndValue(courseMap));
        bean.setCourseOrderList(courseOrderBeanList);
        bean.setQingqingTeacherId(UserIdEncoder.encodeUser(UserType.teacher, teacherId));
        bean.setCoursePackageList(Collections.<CoursePackage>emptyList());
        bean.setCourseContentPackageList(Collections.<CourseContentPackage>emptyList());
        bean.setStrengthenPackageList(Collections.<StrengthenPackage>emptyList());

        return bean;
    }

    public static TeacherInfoForOrderBean converterToInfoBean(TeacherProto.TeacherDetailForStudentToOrderResponse response){
//        logger.info("detail_for_order:" + JsonUtil.format(JsonFormat.printToString(response)));
        TeacherInfoForOrderBean bean = new TeacherInfoForOrderBean();
        bean.setResponse(BaseConverter.convertBaseResponse(response.getResponse()));
        if(!BaseResponse.SUCC_CODE.equals(bean.getResponse().getError_code())){
            return bean;
        }

        // 获取所有支持的年级，科目，上门方式
        Map<String, String> courseMap = new HashMap<String, String>();

        List<CourseOrderBean> courseOrderBeanList = new ArrayList<CourseOrderBean>(response.getCoursePricesCount());
        // 价格信息
        for(GradeCourseProto.TeacherCoursePrice priceInfo : response.getCoursePricesList()){
            CoursePriceType coursePriceType = CoursePriceType.valueOf(priceInfo.getPriceType());

            List<CoursePrice> coursePriceList = new ArrayList<>(priceInfo.getPriceInfosCount());
            for (GradeCourseProto.GradeCoursePriceInfoV2 courseInfo :  priceInfo.getPriceInfosList()){
                KeyAndValue grade = new KeyAndValue(String.valueOf(courseInfo.getGradeCourse().getGradeId()), courseInfo.getGradeCourse().getGradeName());
                Integer courseId = courseInfo.getGradeCourse().getCourseId();
                courseMap.put(String.valueOf(courseId), courseInfo.getGradeCourse().getCourseName());

                List<SiteTypeAndPrice> siteTypeAndPriceList = new ArrayList<>(4);
                if(DoubleCompareUtil.gtZero(courseInfo.getPriceInfo().getPriceToStudentHome())){
                    siteTypeAndPriceList.add(new SiteTypeAndPrice(OrderSiteType.student_home, courseInfo.getPriceInfo().getPriceToStudentHome()));
                }
                if(DoubleCompareUtil.gtZero(courseInfo.getPriceInfo().getPriceToTeacherHome())){
                    siteTypeAndPriceList.add(new SiteTypeAndPrice(OrderSiteType.teacher_home, courseInfo.getPriceInfo().getPriceToTeacherHome()));
                }
                if(DoubleCompareUtil.gtZero(courseInfo.getPriceInfo().getPriceForLiving())){
                    siteTypeAndPriceList.add(new SiteTypeAndPrice(OrderSiteType.live, courseInfo.getPriceInfo().getPriceForLiving()));
                }

                coursePriceList.add(new CoursePrice(grade, siteTypeAndPriceList));
            }

            courseOrderBeanList.add(new CourseOrderBean(coursePriceType, coursePriceList));
        }

        // 优惠包
        List<CoursePackage> coursePackageList = new ArrayList<>(response.getCoursePackageUnitsCount());
        for (CoursePackageUnits coursePackageUnits : response.getCoursePackageUnitsList()) {
            coursePackageList.add(toCoursePackage(coursePackageUnits));
        }

        // 内容包
        List<CourseContentPackage> courseContentPackageList = new ArrayList<>(response.getContentPackageUnitsCount());
        for (CourseContentPackageForOrder courseContentPackageForOrder : response.getContentPackageUnitsList()) {
            courseContentPackageList.add(toCourseContentPackage(courseContentPackageForOrder));
        }

        // 各年级支持的巩固包类型
        List<StrengthenPackage> strengthenPackageList = new ArrayList<>(response.getGradeStrengthenInfosCount());
        for(ApiTeacherGradeAndSupportStrengthenInfo strengthenInfo : response.getGradeStrengthenInfosList()){
            List<StrengthenInfo> strengthenInfoList = new ArrayList<>(strengthenInfo.getStrengthenInfosCount());
            for(ApiStrengthenInfo apiStrengthenInfo : strengthenInfo.getStrengthenInfosList()){
                StrengthenInfo info = new StrengthenInfo();
                info.setStrengthenType(apiStrengthenInfo.getStrengthenType());
                info.setNormalTimes(apiStrengthenInfo.getNormalTimes());
                info.setStrengthenTimes(apiStrengthenInfo.getStrengthenTimes());

                strengthenInfoList.add(info);
            }

            StrengthenPackage strengthenPackage = new StrengthenPackage();
            strengthenPackage.setGradeId(strengthenInfo.getGradeId());
            strengthenPackage.setStrengthenInfos(strengthenInfoList);

            strengthenPackageList.add(strengthenPackage);
        }

        if(response.hasServicePackageInfo()){
            ApiServicePackageInfoForOrder servicePackageInfoForOrder = response.getServicePackageInfo();
            Long recommendId = servicePackageInfoForOrder.getRecommendServicePackage().getServicePackageId();

            List<ServicePackageInfo> servicePackageInfoList = new ArrayList<>(servicePackageInfoForOrder.getServicePackageInfosCount());
            for(ApiServicePackageInfo apiServicePackageInfo : servicePackageInfoForOrder.getServicePackageInfosList()){
                ServicePackageInfo info = new ServicePackageInfo();
                info.setId(apiServicePackageInfo.getServicePackageId());
                info.setPrice(apiServicePackageInfo.getPrice());
                info.setTimeLength(apiServicePackageInfo.getEffectiveTimeLength());
                info.setRecommend(apiServicePackageInfo.getServicePackageId() == recommendId);

                servicePackageInfoList.add(info);
            }

            ServicePackage servicePackage = new ServicePackage();
            servicePackage.setForce(servicePackageInfoForOrder.getIsForce());
            servicePackage.setServicePackageInfoList(servicePackageInfoList);
            bean.setServicePackage(servicePackage);
        }

        // 课时包
        List<ClassHourPackage> classHourPackageList = new ArrayList<>();
        for(ApisvcCourseClassHour.ApisvcCourseClassHourPackageInfo classHourPackageInfo  : response.getClassHourInfo().getClassHourPackagesList()){
            ClassHourPackage classPackage = new ClassHourPackage();
            classPackage.setPackageId(classHourPackageInfo.getPackageId());
            classPackage.setName(classHourPackageInfo.getName());
            classPackage.setFeeClassHour(classHourPackageInfo.getFeeClassHour());
            classPackage.setFreeClassHour(classHourPackageInfo.getFreeClassHour());
            classHourPackageList.add(classPackage);
        }

        bean.setSupportCourseList(BaseConverter.convertToKeyAndValue(courseMap));
        bean.setCourseOrderList(courseOrderBeanList);
        bean.setQingqingTeacherId(response.getTeacherInfo().getQingqingUserId());
        bean.setCoursePackageList(coursePackageList);
        bean.setCourseContentPackageList(courseContentPackageList);
        bean.setStrengthenPackageList(strengthenPackageList);
        bean.setClassHourPackageList(classHourPackageList);

        return bean;
    }

    public static AddOrderResultBean convertAddOrderResult(StudentAddGroupOrderResponse response){
        AddOrderResultBean bean = new AddOrderResultBean();
        bean.setResponse(BaseConverter.convertBaseResponse(response.getResponse()));
        if(!BaseResponse.SUCC_CODE.equals(bean.getResponse().getError_code())){
            return bean;
        }

        bean.setQingqingGroupOrderId(response.getQingqingGroupOrderId());
        bean.setQingqingOrderId(response.getQingqingGroupSubOrderId());

        bean.setOrderId(String.valueOf(OrderIdEncoder.decodeQingqingOrderId(response.getQingqingGroupSubOrderId())));
        bean.setGroupOrderId(String.valueOf(OrderIdEncoder.decodeQingqingOrderId(response.getQingqingGroupOrderId())));
        bean.setOrderBriefStatus(convertOrderBriefStatus(response.getGroupSubOrderStatus()));

        return bean;
    }

    public static AddOrderResultBean convertAddOrderResult(JoinClassOrderResponse response){
        AddOrderResultBean bean = new AddOrderResultBean();
        bean.setResponse(BaseConverter.convertBaseResponse(response.getResponse()));
        if(!BaseResponse.SUCC_CODE.equals(bean.getResponse().getError_code())){
            return bean;
        }

        bean.setQingqingGroupOrderId(response.getQingqingGroupOrderId());
        bean.setQingqingOrderId(response.getQingqingGroupSubOrderId());

        bean.setOrderId(String.valueOf(OrderIdEncoder.decodeQingqingOrderId(response.getQingqingGroupSubOrderId())));
        bean.setGroupOrderId(String.valueOf(OrderIdEncoder.decodeQingqingOrderId(response.getQingqingGroupOrderId())));
        bean.setOrderBriefStatus(convertOrderBriefStatus(response.getGroupSubOrderStatus()));

        return bean;
    }

    private static CourseContentPackage toCourseContentPackage(CourseContentPackageForOrder courseContentPackageForOrder){
        List<CourseOrderBean> courseOrderBeanList = new ArrayList<>(courseContentPackageForOrder.getPriceCount());
        for (CourseContentPackagePriceForOrder courseContentPackagePriceForOrder : courseContentPackageForOrder.getPriceList()) {
            courseOrderBeanList.add(toCourseOrderBean(courseContentPackagePriceForOrder));
        }

        CourseContentPackage courseContentPackage = new CourseContentPackage();
        courseContentPackage.setPackageId(courseContentPackageForOrder.getContentPackageRelationId());
        courseContentPackage.setPackageName(courseContentPackageForOrder.getName());
        courseContentPackage.setClassCount(courseContentPackageForOrder.getClassCount());
        courseContentPackage.setClassHour(courseContentPackageForOrder.getClassHour());
        courseContentPackage.setCourseOrderBeanList(courseOrderBeanList);

        return courseContentPackage;
    }

    private static CourseOrderBean toCourseOrderBean(CourseContentPackagePriceForOrder price){
        CoursePriceType coursePriceType = CoursePriceType.valueOf(price.getCoursePriceType());

        List<CoursePrice> coursePriceList = new ArrayList<>(price.getGradeCoursePriceCount());
        for (GradeCourseProto.GradeCoursePriceInfoV2 courseInfo : price.getGradeCoursePriceList()){
            KeyAndValue grade = new KeyAndValue(String.valueOf(courseInfo.getGradeCourse().getGradeId()), courseInfo.getGradeCourse().getGradeName());

            List<SiteTypeAndPrice> siteTypeAndPriceList = new ArrayList<>(4);
            if(DoubleCompareUtil.gtZero(courseInfo.getPriceInfo().getPriceToStudentHome())){
                siteTypeAndPriceList.add(new SiteTypeAndPrice(OrderSiteType.student_home, courseInfo.getPriceInfo().getPriceToStudentHome()));
            }
            if(DoubleCompareUtil.gtZero(courseInfo.getPriceInfo().getPriceToTeacherHome())){
                siteTypeAndPriceList.add(new SiteTypeAndPrice(OrderSiteType.teacher_home, courseInfo.getPriceInfo().getPriceToTeacherHome()));
            }
            if(DoubleCompareUtil.gtZero(courseInfo.getPriceInfo().getPriceForLiving())){
                siteTypeAndPriceList.add(new SiteTypeAndPrice(OrderSiteType.live, courseInfo.getPriceInfo().getPriceForLiving()));
            }

            coursePriceList.add(new CoursePrice(grade, siteTypeAndPriceList));
        }

        return new CourseOrderBean(coursePriceType, coursePriceList);
    }

    private static CoursePackage toCoursePackage(CoursePackageUnits coursePackageUnits){
        String packageName = coursePackageUnits.getName();
        CoursePackageUnit coursePackageUnit = coursePackageUnits.getPackageUnits(0);

        CoursePackage coursePackage = new CoursePackage();
        coursePackage.setPackageId(coursePackageUnits.getPackageId());
        coursePackage.setPackageName(packageName);
        coursePackage.setCourseTimes(coursePackageUnit.getChargeCourseCount() + coursePackageUnit.getFreeCountCount());

        return coursePackage;
    }

    private static String convertOrderBriefStatus(GroupSubOrderStatus briefOrderInfoStatus){
        switch (briefOrderInfoStatus){
            case cancel_group_user_order_status:
                return "已取消";
            case confirm_pending_group_user_order_status:
                return "待老师确认";
            case make_up_group_user_order_status:
                return "已支付";
            case wait_to_make_up_group_user_order_status:
                return "待成团";
            case wait_to_pay_group_user_order_status:
                return "待支付";
            default:
                return "未知状态";
        }
    }
}
