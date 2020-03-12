function orderCourseList(base, qingqingOrderId, coursePriceType){
    var result = false;
    var data = {
        data : qingqingOrderId
    };

    var otherData = {
        coursePriceType : coursePriceType
    };

    var request = {
        url : base + "/v1/order_course/order_course_list.json",
        data : data,
        handlerFunc : handlerSubOrderDetail,
        isASync : false,
        failTitle :"获取课程信息失败：",
        env : $("#env").val(),
        otherData : otherData,
        guid : $("#guid").val()
    };

    return commonAjaxRequest(request);
}

function handlerSubOrderDetail(resu, otherData){
    var s = $("#orderCourseRow").html();
    s = s.replace("</tbody>","");
    s = s.replace("<tbody>","");

    var coursePriceType = otherData.coursePriceType;

    var orderCourseText = '';
    for(idx in resu.orderCourseV1List){
        var orderCourse = resu.orderCourseV1List[idx];
        var orderCourseHtml = s.replace(new RegExp("{orderCourseId}","gm"), orderCourse.id);
        orderCourseHtml = orderCourseHtml.replace("{startCourseTime}", formatDate(orderCourse.startCourseTime));
        orderCourseHtml = orderCourseHtml.replace("{endCourseTime}", formatDate(orderCourse.endCourseTime));
        orderCourseHtml = orderCourseHtml.replace("{realPrice}", orderCourse.realPrice);
        orderCourseHtml = orderCourseHtml.replace("{realPriceWithoutTax}", orderCourse.realPriceWithoutTax);
        orderCourseHtml = orderCourseHtml.replace("{orderStatus}", orderStatus(orderCourse, coursePriceType));
        orderCourseHtml = orderCourseHtml.replace(new RegExp("{groupOrderCourseId}","gm"), orderCourse.groupOrderCourseId);
        orderCourseText += orderCourseHtml;
    }
    $("#orderCourseBody").html(orderCourseText);
}

function orderStatus(orderCourse, coursePriceType){
    var freeze = orderCourse.freeze;
    var isFreeze = !(freeze == null || freeze < 4);

    var status = orderCourse.status;
    switch(status){
        case "not_accpeted":
        case "teacher_accepted":
            var coursePriceType = $("#coursePriceType").val();
            var orderStatus = $("#orderStatus").text();
            if("已支付" == orderStatus && coursePriceType > 1){
                return "已支付待成团";
            }

            return "待支付";
        case 102:
            return "等待老师确认";
        case "student_payed":
            return "已支付待上课";
        case "class_started":
            if(!isFreeze){
                return "上课中";
            }else{
                return "冻结中";
            }
        case "class_ended":
            return "已下课";
        case "finished":
        case "system_payed":
            if(isFreeze){
                return "已赔付";
            }else {
                return "已结课";
            }
        case "canceled":
            return "已取消";
        default:
            return "未知";
    }
}

function startClass(base, groupOrderCourseId, teacherId){
    var qingqingGroupOrderId = encode(groupOrderCourseId);
    var data = {
        url : '/svc/api/pi/v4/live_order_course/start_class.json',
        param : '{"qingqing_group_order_course_id":"' + qingqingGroupOrderId + '", "device_identification":{"platformType":"ios", "imei":"1", "idfa":"2"}}',
        userId : teacherId,
        userType : 'teacher'
    }

    var request = {
        url : base + "/v1/common/pi.json",
        data : data,
        handlerFunc : handlerCommonOrderOps,
        isASync : false,
        failTitle :"点击上课失败：",
        env : $("#env").val(),
        otherData : null,
        guid : $("#guid").val()
    };

    commonAjaxRequest(request);
};

function finishClass(base, orderCourseId, studentId){
    var qingqingOrderCourseId = encode(orderCourseId);
    var data = {
        url :"/svc/api/pt/v4/order_course/action/finish.json",
        param : '{"qingqing_order_course_id":"' + qingqingOrderCourseId + '", "device_identification":{"platformType":"ios", "imei":"1", "idfa":"2"}}',
        userId : studentId,
        userType : "student"
    };

    var request = {
        url : base + "/v1/common/pt.json",
        data : data,
        handlerFunc : handlerCommonOrderOps,
        isASync : false,
        failTitle :"结课失败：",
        env : $("#env").val(),
        otherData : null,
        guid : $("#guid").val()
    };

    commonAjaxRequest(request);
};

function applyFreeze(base, orderCourseId, studentId){
    var qingqingOrderCourseId = encode(orderCourseId);
    var data = {
        url :"/svc/api/pt/v2/order_course/action/student/apply_freeze.json",
        param : '{"qingqing_order_course_id":"' + qingqingOrderCourseId + '", "reason_type":"not_come_cf_course_reason_type", "extra_reason":"来吧，赔钱"}',
        userId : studentId,
        userType : "student"
    };

    var otherData = {
        orderCourseId : orderCourseId,
        base :base
    };

    var request = {
        url : base + "/v1/common/pt.json",
        data : data,
        handlerFunc : handlerApplyFreeze,
        isASync : false,
        failTitle :"申请三方赔付失败：",
        env : $("#env").val(),
        otherData : otherData,
        guid : $("#guid").val()
    };

    commonAjaxRequest(request);
};

function handlerApplyFreeze(resu, otherData){
    var orderCourseId = otherData.orderCourseId;

    window.open(otherData.base +"/v1/order_course/freeze_apply/page?orderCourseId=" + orderCourseId + "&env=" + $("#env").val());
}

function applyCancelOrderCourse(base, groupOrderCourseId, assistantId){
    var qingqingGroupOrderId = encode(groupOrderCourseId);
    var data = {
        url : "/svc/api/pi/v4/order_course/action/assistant/apply_cancel.json",
        param : '{"qingqing_group_order_course_ids":["' + qingqingGroupOrderId + '"], "reason_number":1, "extra_reason":"我想删课", "responsibility_type":"teacher_change_responsibility_type"}',
        userType : "ta",
        userId : assistantId
    };

    var otherData = {
        base :base
    };

    var request = {
        url : base + "/v1/common/pi.json",
        data : data,
        handlerFunc : handlerApplyCancel,
        isASync : false,
        failTitle :"申请删课失败:",
        env : $("#env").val(),
        otherData : otherData,
        guid : $("#guid").val()
    };

    commonAjaxRequest(request);
};

function handlerApplyCancel(resu, otherData) {
    $.gritter.add({
        title: '申请删课成功:',
        text: '申请删课成功',
        class_name: 'gritter-info gritter-center'
    });

    window.open(otherData.base + "/v1/order_course/cancel_apply/process?qingqingBatchApplyId=" + resu.data + "&env=" + $("#env").val());

    handlerCommonOrderOps();
}

function applyChange(base, groupOrderCourseId, orderId){
    window.open(base +"/v1/order_course/change_apply/page?groupOrderCourseId=" + groupOrderCourseId + "&env=" + $("#env").val() + "&orderId=" + orderId);
};

function applyChange2(base, groupOrderCourseId, orderId){
    window.open(base +"/v1/order_course/change_apply/apply_page?groupOrderCourseId=" + groupOrderCourseId + "&env=" + $("#env").val() + "&orderId=" + orderId);
};

function handlerApplyFreeze(resu, otherData){
    var orderCourseId = otherData.orderCourseId;

    window.open(otherData.base +"/v1/order_course/freeze_apply/page?orderCourseId=" + orderCourseId + "&env=" + $("#env").val());
}

function deleteClassOrderCourse(base, orderCourseId, assistantId){
    var data = {
        url : "/svc/api/pi/v5/order_course/action/live_class/delegate_process_delete.json",
        param : '{"order_course_ids":[' + orderCourseId + '], "reason_number":1, "extra_reason":"我想删课", "handle_comment":"没脾气"}',
        userType : "ta",
        userId : assistantId
    };

    var otherData = {
        base :base
    };

    var request = {
        url : base + "/v1/common/pi.json",
        data : data,
        handlerFunc : handlerApplyCancel,
        isASync : false,
        failTitle :"申请删课失败：",
        env : $("#env").val(),
        otherData : otherData,
        guid : $("#guid").val()
    };

    commonAjaxRequest(request);
}

function getOrderType(coursePriceType){
    switch (coursePriceType){
        case "1":
            return "general_order_type";
        case "2":
        case "3":
        case "4":
        case "5":
            return "group_order_type";
        case "103":
            return "live_class_order_type";
        case "104":
            return "class_hour_order_type";
    }

    return null;
}