<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>我来下课时包订单</title>
    <#include "/include/resource.ftl" />
    <link href="${base}/static/css/json/base.css" rel="stylesheet">
    <link href="${base}/static/css/json/jquery.numberedtextarea.css" rel="stylesheet">

    <script src="${base}/static/js/json/jquery.message.js"></script>
    <script src="${base}/static/js/json/jquery.json.js"></script>
    <script src="${base}/static/js/json/json2.js"></script>
    <script src="${base}/static/js/json/jsonlint.js"></script>
    <script src="${base}/static/js/json/jquery.numberedtextarea.js"></script>
    <script src="${base}/static/js/param.js"></script>

</head>
<body>
    <#include "/include/topbar.ftl" />
    <div class="main-container" id="main-container">
        <script type="text/javascript">
            try{ace.settings.check('main-container' , 'fixed')}catch(e){}
        </script>

        <div class="main-container-inner">
            <a class="menu-toggler" id="menu-toggler" href="#">
                <span class="menu-text"></span>
            </a>

            <#include "/include/sidebar.ftl" />

            <div class="main-content">
                <div class="breadcrumbs" id="breadcrumbs">
                    <script type="text/javascript">
                        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                    </script>

                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home home-icon"></i>
                            <a href="#">流程</a>
                        </li>
                        <li class="active">课时包下单</li>
                    </ul><!-- .breadcrumb -->

                    <div class="nav-search" id="nav-search">
                        <span class="input-icon">
                            <input type="text" placeholder="Search ..." class="nav-search-input" id="task_search" autocomplete="off" value="${search!""}" />
                            <i class="icon-search nav-search-icon"></i>
                        </span>
                    </div><!-- #nav-search -->
                </div>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            下单这件事
                            <small>
                                <i class="icon-double-angle-right"></i>
                                <label id = "interfaceNameDiv">弄啥类</label>
                            </small>
                        </h1>
                    </div><!-- /.page-header -->

                    <div class="row">
                        <div class="col-xs-12">
                            <h4 class="lighter">
                                <i class="icon-hand-right icon-animated-hand-pointer blue"></i>
                                <input type="hidden" id="guid" >
                                <a target="_blank" title="点击链接可查看调用日志" data-rel="tooltip" id = "logUrl" href="">
                                    <div class="widget-main" id="interfaceUrl"> Took the final exam. Phew! </div>
                                </a>
                            </h4>

                            <div class="hr hr-18 hr-double dotted"></div>

                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" style="text-align: right;" for="env">环境选择:</label>

                                    <div class="col-xs-12 col-sm-9">
                                        <div class="clearfix">
                                            <input type="hidden" name="env" id="env" value="dev" class="col-xs-12 col-sm-3" />
                                            <button type="button" value="dev" env="dev" style="border-radius: 8px" class="btn btn-primary env">开发环境</button>
                                            <button type="button" value="fws" env="fws" style="border-radius: 8px" class="btn env">接口测试环境</button>
                                            <button type="button" value="tst" env="tst" style="border-radius: 8px" class="btn env">测试环境</button>
                                        </div>
                                    </div>
                                </div>

                                <div class="hr hr-dotted"></div>
                                <div class="hr hr-dotted"></div>

                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="teacherIdIpt">请求人ID:</label>

                                    <div class="col-xs-12 col-sm-9">
                                        <div class="clearfix">
                                        <#if (userId!0) gt 0>
                                            <input type="hidden" name="requestUserId" id="requestUserId" value="${userId?c}" class="col-xs-12 col-sm-3" />
                                            <span class="editable editable-click editable-unsaved" id="requestUserIdDiv" style="display: inline-block; background-color: rgba(0, 0, 0, 0);">${userId?c}</span>
                                        <#else>
                                            <input type="hidden" name="requestUserId" id="requestUserId" value="22367" class="col-xs-12 col-sm-3" />
                                            <span class="editable editable-click editable-unsaved" id="requestUserIdDiv" style="display: inline-block; background-color: rgba(0, 0, 0, 0);">22367</span>
                                        </#if>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="requestUserType">请求人类型:</label>

                                    <div class="col-xs-12 col-sm-9">
                                        <div class="clearfix">
                                        <#if (userType!'') != ''>
                                            <input type="hidden" name="requestUserType" id="requestUserType" value="${userType}" class="col-xs-12 col-sm-3" />
                                            <span class="editable editable-click editable-unsaved" id="requestUserTypeDiv" style="display: inline-block; background-color: rgba(0, 0, 0, 0);">${userType}</span>
                                        <#else>
                                            <input type="hidden" name="requestUserType" id="requestUserType" value="student" class="col-xs-12 col-sm-3" />
                                            <span class="editable editable-click editable-unsaved" id="requestUserTypeDiv" style="display: inline-block; background-color: rgba(0, 0, 0, 0);">student</span>
                                        </#if>

                                        </div>
                                    </div>
                                </div>

                                <div class="hr hr-dotted"></div>
                                <div class="hr hr-dotted"></div>

                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" style="text-align: right;" for="env">学生ID:</label>

                                    <div class="col-xs-12 col-sm-9">
                                        <div class="clearfix">
                                            <input type="number" name="studentIdIpt" id="studentIdIpt" value="22367" class="col-xs-12 col-sm-1" />
                                            <input type="hidden" name="studentId" id = "studentId" />
                                            <span class="input-group-btn" class="col-xs-12 col-sm-2" >
                                                <button type="button" class="btn btn-purple btn-xs" id="studentIdBtn">
                                                    Search
                                                    <i class="icon-search icon-on-right bigger-110"></i>
                                                </button>
                                            </span>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group ">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right">年级:</label>

                                    <div class="col-xs-12 col-sm-2">
                                        <select class="width-80 chosen-select" id="gradeId" data-placeholder="选择年级...">
                                            <option value="1" selected="selected">一年级</option>
                                            <option value="2">二年级</option>
                                            <option value="3">三年级</option>
                                            <option value="4">四年级</option>
                                            <option value="5">五年级</option>
                                            <option value="6">六年级</option>
                                            <option value="7">初一</option>
                                            <option value="8">初二</option>
                                            <option value="9">初三</option>
                                            <option value="10">高三</option>
                                            <option value="11">高三</option>
                                            <option value="12">高三</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" style="text-align: right;" for="env">选择科目:</label>

                                    <div class="col-xs-12 col-sm-4">
                                        <div class="clearfix">
                                            <select class="width-80 chosen-select tag-input-style" id="courseId" data-placeholder="选择科目" multiple="multiple">
                                                <option value="1" selected="selected">语文</option>
                                                <option value="2">数学</option>
                                                <option value="3">英语</option>
                                                <option value="4">物理</option>
                                                <option value="5">化学</option>
                                                <option value="6">政治</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" style="text-align: right;" for="useValueVoucher">使用优惠券:</label>

                                    <div class="col-xs-12 col-sm-9">
                                        <label class="pull-left inline"  title="开启会总选择优惠券逻辑" data-rel="tooltip" >
                                            <input id="useValueVoucher" type="checkbox" class="ace ace-switch ace-switch-5 qing_enable" value="0" />
                                            <span class="lbl"></span>
                                        </label>
                                    </div>
                                </div>

                                <div class="hr hr-dotted"></div>
                                <div class="hr hr-dotted"></div>

                                <!-- 年级科目信息 -->
                                <div id="orderDetailSelector" class="hide">
                                    <!-- 自定义购买 -->
                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" style="text-align: right;" for="initOrder">自定义下单：</label>

                                        <div class="col-xs-12 col-sm-9">
                                            <div class="clearfix">
                                                <!-- 自定义下单 -->
                                                <div class="col-xs-6 col-sm-3 pricing-box">
                                                    <div class="widget-box">
                                                        <div class="widget-header header-color-blue">
                                                            <h5 class="bigger lighter">自定义课时购买</h5>
                                                        </div>

                                                        <div class="widget-body">
                                                            <div class="widget-main">
                                                                <ul class="list-unstyled spaced2">
                                                                    <li>
                                                                        <div class="form-group">
                                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="customUnitPrice">单价:</label>
                                                                            <div class="col-xs-12 col-sm-3">
                                                                                <span class="customUnitPrice">0</span>
                                                                            </div>
                                                                        </div>
                                                                    </li>

                                                                    <li>
                                                                        <div class="form-group">
                                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="customCourseHour">课时数:</label>
                                                                            <div class="col-xs-12 col-sm-3">
                                                                                <input type="number" id="customCourseHour" value="10" />
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                </ul>

                                                                <hr style="margin-top: 64px;" />
                                                                <div class="price">
                                                                    $<span id="customTotalPrice"></span>
                                                                    <small>/单</small>
                                                                </div>
                                                            </div>

                                                            <div>
                                                                <a href="#" class="btn btn-block btn-primary" id="customBuyBtn">
                                                                    <i class="icon-shopping-cart bigger-110"></i>
                                                                    <span>Buy</span>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <!-- 轻豆下单 -->
                                                <div class="col-xs-6 col-sm-3 pricing-box">
                                                    <div class="widget-box">
                                                        <div class="widget-header header-color-green">
                                                            <h5 class="bigger lighter">轻豆兑换购买</h5>
                                                        </div>

                                                        <div class="widget-body">
                                                            <div class="widget-main">
                                                                <ul class="list-unstyled spaced2">
                                                                    <li>
                                                                        <div class="form-group">
                                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="customUnitPrice">单价:</label>
                                                                            <div class="col-xs-12 col-sm-3">
                                                                                <span class="customUnitPrice">0</span>
                                                                            </div>
                                                                        </div>
                                                                    </li>

                                                                    <li>
                                                                        <div class="form-group">
                                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="scoreCourseHour">课时数:</label>
                                                                            <div class="col-xs-12 col-sm-3">
                                                                                <input type="number" id="scoreCourseHour" value="10" />
                                                                            </div>
                                                                        </div>
                                                                    </li>

                                                                    <li>
                                                                        <div class="form-group">
                                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="scoreCount">轻豆数:</label>
                                                                            <div class="col-xs-12 col-sm-3">
                                                                                <input type="number" id="scoreCount" value="100" />
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                </ul>

                                                                <hr />
                                                                <div class="price">
                                                                    $<span id="scoreTotalPrice"></span>
                                                                    <small>/单</small>
                                                                </div>
                                                            </div>

                                                            <div>
                                                                <a href="#" class="btn btn-block btn-success" id="scoreBuyBtn">
                                                                    <i class="icon-shopping-cart bigger-110"></i>
                                                                    <span>Buy</span>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="hr hr-dotted"></div>
                                    <div class="hr hr-dotted"></div>

                                    <div id="packageListDiv" class="hide">
                                        <!-- 课时包购买 -->
                                        <div class="form-group">
                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" style="text-align: right;" for="initOrder">课时包：</label>

                                            <div class="col-xs-12 col-sm-9">
                                                <div class="clearfix" id="packageList">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" style="text-align: right;" for="initOrder">接口返回数据：</label>

                                        <div class="col-xs-12 col-sm-9">
                                            <div class="timeline-item clearfix">
                                                <div class="timeline-info">
                                                    <i class="timeline-indicator icon-star btn btn-warning no-hover green"></i>
                                                </div>
                                                <div class="panel panel-default">
                                                    <div class="panel-heading">
                                                        <a href="#faq-1-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
                                                            <i class="icon-chevron-left pull-right" data-icon-hide="icon-chevron-down" data-icon-show="icon-chevron-left"></i>

                                                            <i class="icon-user bigger-130"></i>
                                                            &nbsp;接口相关配置
                                                        </a>
                                                    </div>

                                                    <div class="panel-collapse collapse" id="faq-1-1">
                                                        <div class="col-md-6" style="padding:0;position:relative;height:100%;">
                                                            <div class="page-header">
                                                                <h1>
                                                                    接口配置
                                                                    <small>
                                                                        <i class="icon-double-angle-right"></i>
                                                                        <label>也就是看看</label>
                                                                    </small>
                                                                </h1>
                                                            </div><!-- /.page-header -->
                                                            <div id="json-response-div" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                                <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                                <div class="ro" id="detailForOrderResult" style="padding:0px 25px;white-space: pre-line;">
                                                                    <span data-type="object"><i style="cursor:pointer;" class="fa icon-minus" onclick="hide(this)"></i>{<br><br>}</span></div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6" style="padding:0;position:relative;height:100%;">
                                                            <div class="page-header">
                                                                <h1>
                                                                    接口参数配置
                                                                    <small>
                                                                        <i class="icon-double-angle-right"></i>
                                                                        <label>如果此处的json格式不对，说明配置有问题</label>
                                                                    </small>
                                                                </h1>
                                                            </div><!-- /.page-header -->
                                                            <div id="json-response-div" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                                <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                                <div class="ro" id="departmentInfo" style="padding:0px 25px;white-space: pre-line;">
                                                                    <span data-type="object"><i style="cursor:pointer;" class="fa icon-minus" onclick="hide(this)"></i>{<br><br>}</span></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                </div>

            <div id="useValueVoucherDiv" class="hide" style="z-index: 7;
    position: fixed;
    top: 45%;
    left: 28%;
    background-color: #eeeeee;
    border-width: 10px;
    border-color: red;
    width: 400px;
    box-shadow: 5px 2px 8px #888888;
    border-radius: 20px;">
                <form class="form-horizontal" role="form" style="margin-top: 15px;">
                    <div class="form-group ">
                        <label class="control-label col-xs-12 col-sm-3 no-padding-right">选择优惠券:</label>

                        <div class="col-xs-12 col-sm-2">
                            <select class="width-80 chosen-select" id="valueVoucherInstanceId" data-placeholder="选择优惠券...">
                            </select>

                            <span class="input-group-btn">
                                <button type="button" class="btn btn-purple btn-xs" id="useValueVoucherBtn">
                                    确认
                                </button>

                                 <button type="button" class="btn btn-purple btn-xs" id="useValueVoucherCancelBtn">
                                    取消
                                </button>
                            </span>
                        </div>
                    </div>
                </form>
            </div>

            <#include "/include/righttool-sidebar.ftl" />

<script type="text/javascript">
    var allEnv = ["dev","tst","fws"];
    $(document).ready(function(){
        refreshInterfaceUrl();

        initOrderParam();
    });

    $("#useValueVoucherBtn").click(function () {
        var valueVoucherInstanceId = $("#valueVoucherInstanceId").val();

        var valueRequestData = {"order": {"order_type":"class_hour_v2_order_type","qingqing_common_order_id":createOrderResultObj.qingqing_order_id}, "value_voucher_instance_ids": [new Number(valueVoucherInstanceId)]};
        var valueData = {
            url: "/orderservice/api/pt/v1/class_hour_order/student/use_value_voucher.json",
            param: JSON.stringify(valueRequestData),
            userType: "student",
            userId: new Number($("#studentId").val())
        };
        var useValueVoucherResult = getDataFromPt("${base}", valueData, $("#env").val(), $("#guid").val());

        if(useValueVoucherResult.response != null && useValueVoucherResult.response.error_code == 0) {
            $("#useValueVoucherDiv").addClass("hide");
            gotoPay(createOrderResultObj);
        }
    });

    $("#useValueVoucherCancelBtn").click(function () {
        $("#useValueVoucherDiv").addClass("hide");
        gotoPay(createOrderResultObj);
    });

    $(".env").click(function(){
        $(".env.btn-primary").removeClass("btn-primary");
        $(this).addClass("btn-primary");
        $("#env").val($(this).val());

        refreshInterfaceUrl();
        initOrderParam();
    });

    var logUrl = "http://172.22.12.14:5601/app/logtrail#/?q=env_type:%20%22{env}%22%20%26%26%20guid:%20%22{guid}%22&t=Now&i=rsyslog-app*&_g=()&h={server}";
    function refreshInterfaceUrl(){
        var env = $("#env").val();
        var guid = generateGuid();

        var url = "查看日志（guid={guid} && env={env}）".replace("{env}", env);
        url = url.replace("{guid}", guid);

        var logTargetUrl = logUrl.replace("{env}", env);
        logTargetUrl = logTargetUrl.replace("{guid}", guid);
        logTargetUrl = logTargetUrl.replace("{server}", "orderservice")

        $("#interfaceUrl").text(url);
        $("#guid").val(guid);
        $("#logUrl").attr("href", logTargetUrl);
    }

    $("#gradeId").change(function(){
        initOrderParam();
    });

    $("#customCourseHour").change(function(){
        var customPrice = detailForOrderObj.custom_course_class_hour_package_info.guide_price;
        $("#customTotalPrice").text(customPrice * $("#customCourseHour").val());
    });

    $("#scoreCourseHour").change(function(){
        var customPrice = detailForOrderObj.custom_course_class_hour_package_info.guide_price;
        $("#scoreTotalPrice").text(customPrice * $("#scoreCourseHour").val());
    });

    $('#studentIdBtn').click(initOrderParam);

    var colorArr = ["header-color-red3", "header-color-orange", "header-color-dark", "header-color-blue", "header-color-green", "header-color-grey"];
    var btnColorArr = ["btn-danger", "btn-warning", "btn-inverse", "btn-primary", "btn-success", "btn-grey"];
    var colorIdx = 0;
    var packageTemplate = '<div class="col-xs-6 col-sm-3 pricing-box"><div class="widget-box"><div class="widget-header {color}"><h5 class="bigger lighter">{package_name}({package_id}){tagName}</h5></div><div class="widget-body"><div class="widget-main"><ul class="list-unstyled spaced2"><li><div class="form-group"><label class="control-label col-xs-12 col-sm-3 no-padding-right" for="customUnitPrice">策略:</label><div class="col-xs-12 col-sm-9"><span>{strategy}</span></div></div></li><li><div class="form-group"><label class="control-label col-xs-12 col-sm-3 no-padding-right" for="customUnitPrice">价格:</label><div class="col-xs-12 col-sm-9"><span>{price}</span></div></div></li><li><div class="form-group"><label class="control-label col-xs-12 col-sm-3 no-padding-right" for="customUnitPrice">课时:</label><div class="col-xs-12 col-sm-9"><span>{hour}</span></div></div></li></ul><hr /><div class="price">$ {total_price}<small>/单</small></div></div><div><a href="#" class="btn btn-block {btnColor} buyPackageBtn" id="{package_id}"><i class="icon-shopping-cart bigger-110"></i><span>Buy</span></a></div></div></div></div>';
    var detailForOrderObj;
    var createOrderResultObj;
    function initOrderParam() {
        var studentId = $('#studentIdIpt').val();
        if(!validStudentId(studentId)){
            return;
        }

        $("#studentId").val(studentId);
        var gradeId = new Number($("#gradeId").val());

        var studentDepartmentInfo = getStudentDepartmentInfo(studentId);
        if(studentDepartmentInfo == null || studentDepartmentInfo.resultList == null){
            jsonShow("[]", "departmentInfo");
            $("#orderDetailSelector").addClass("hide");
            return;
        }

        jsonShow(JSON.stringify(studentDepartmentInfo), "departmentInfo");

        detailForOrderObj = getDetailForOrderByDepartmentInfo(studentDepartmentInfo, gradeId);
        if(detailForOrderObj != null && detailForOrderObj.custom_course_class_hour_package_info != null){
            // 初始化自定义购买
            var customPrice = detailForOrderObj.custom_course_class_hour_package_info.guide_price;
            $(".customUnitPrice").text(customPrice);
            $("#customTotalPrice").text(customPrice * $("#customCourseHour").val());

            // 初始化轻豆购买
            $("#scoreTotalPrice").text(customPrice * $("#scoreCourseHour").val());

            // 初始化课时包
            var fullPackageHtml = "";
            colorIdx = 0;
            if(detailForOrderObj.renew_order_package_strategy != null){
                var idx = 0;
                while(idx < detailForOrderObj.renew_order_package_strategy.length){
                    var strategy = detailForOrderObj.renew_order_package_strategy[idx++];
                    fullPackageHtml += buildStrategyHtml(strategy);
                }
            }

            if(detailForOrderObj.new_order_package_strategy != null){
                var idx = 0;
                while(idx < detailForOrderObj.new_order_package_strategy.length){
                    var strategy = detailForOrderObj.new_order_package_strategy[idx++];
                    fullPackageHtml += buildStrategyHtml(strategy);
                }
            }

            $("#packageList").html(fullPackageHtml);

            if(fullPackageHtml == ""){
                $("#packageListDiv").addClass("hide");
            }else{
                $("#packageListDiv").removeClass("hide");
            }

            jsonShow(JSON.stringify(detailForOrderObj), "detailForOrderResult");
            $("#orderDetailSelector").removeClass("hide");
        }else{
            $("#orderDetailSelector").addClass("hide");
            jsonShow("[]", "detailForOrderResult");
        }
    }

    function buildStrategyHtml(strategy){
        if(strategy.course_class_hour_package_info == null){
            return "";
        }

        var tagName = "";
        if(strategy.tag_info != null && strategy.tag_info.name != null){
            tagName = "-(" + strategy.tag_info.name + ")";
        }

        var result = "";
        var idx = 0;
        while(idx < strategy.course_class_hour_package_info.length){
            var package = strategy.course_class_hour_package_info[idx++];
            result += buildPackageHtml(strategy.strategy_name, package, tagName);
        }

        return result;
    }

    function buildPackageHtml(strategyName, packageData, tagName){
        var html = packageTemplate;
        html = html.replace(new RegExp("{color}","gm"), colorArr[colorIdx%colorArr.length]);
        html = html.replace(new RegExp("{btnColor}","gm"), btnColorArr[colorIdx%colorArr.length]);
        html = html.replace(new RegExp("{package_id}","gm"), packageData.package_id);
        html = html.replace(new RegExp("{package_name}","gm"), packageData.package_name);
        html = html.replace(new RegExp("{strategy}","gm"), strategyName);
        html = html.replace(new RegExp("{price}","gm"), packageData.package_price.sale_price + " = " + packageData.package_price.guide_price + " * " + ((packageData.package_price.discount_percent == null)? "1":packageData.package_price.discount_percent));
        html = html.replace(new RegExp("{hour}","gm"), "买" + packageData.class_hour_info.fee_class_hour + " 送 " + packageData.class_hour_info.free_class_hour + " （" + packageData.class_hour_info.class_hour_effect_days + "天)");
        html = html.replace(new RegExp("{total_price}","gm"), packageData.package_price.sale_price * packageData.class_hour_info.fee_class_hour);
        html = html.replace(new RegExp("{tagName}","gm"), tagName);

        colorIdx++;
        return html;
    }

    function getDetailForOrderByDepartmentInfo(studentDepartmentInfo, gradeId){
        var detailForOrderObj;
        if(studentDepartmentInfo.resultList.qingqing){
            detailForOrderObj = getDetailForOrder(studentDepartmentInfo.resultList.qingqing.department, gradeId);
        }

        if(detailForOrderObj != null && detailForOrderObj.custom_course_class_hour_package_info != null){
            detailForOrderObj.departmentId = studentDepartmentInfo.resultList.qingqing.department;
            detailForOrderObj.isQingQing = true;
            return detailForOrderObj;
        }

        if(studentDepartmentInfo.resultList.online){
            detailForOrderObj = getDetailForOrder(studentDepartmentInfo.resultList.online.department, gradeId);
        }

        if(detailForOrderObj != null && detailForOrderObj.custom_course_class_hour_package_info != null){
            detailForOrderObj.departmentId = studentDepartmentInfo.resultList.online.department;
            detailForOrderObj.isOnline = true;
            return detailForOrderObj;
        }

        return detailForOrderObj;
    }

    function changeDepartmentType(departmentId, type){
        var data = {
            url :"/strategysvc/api/pi/v1/class_hour_v2/department/add_department.json",
            param : '{"department_id":' + departmentId + ',"create_user_id":22367,"create_user_type":"student","business_department_type":"' + type + '"}',
            userType : $("#requestUserType").val(),
            userId : $("#requestUserId").val()
        };

        return getDataFromPi("${base}", data, $("#env").val(), $("#guid").val(), true);
    }

    $(document).off("click", '.buyPackageBtn').on('click', '.buyPackageBtn',function(){
        var gradeId = $("#gradeId").val();

        var courseIdselect = $("#courseId").val();
        if (courseIdselect == null || courseIdselect.length == 0) {
            $.gritter.add({
                title : '提示:',
                text : "请选择科目",
                class_name : 'gritter-error gritter-center'
            });

            return false;
        }
        var courseIds = courseIdselect.map(Number);

        var studentId = new Number($("#studentId").val());

        var param = {};
        param.student_id = studentId;
        param.grade_id = new Number(gradeId);
        param.course_id = courseIds;
        param.order_create_type = "general_order_service_order_create_type";
        param.package_id = new Number($(this).attr("id"));
        var package = getPackage(param.package_id);
        param.total_price = package.package_price.sale_price * package.class_hour_info.fee_class_hour;

        addOrder(param);
    });

    function getPackage(packageId) {
        if (detailForOrderObj.renew_order_package_strategy != null) {
            var idx = 0;
            while (idx < detailForOrderObj.renew_order_package_strategy.length) {
                var strategy = detailForOrderObj.renew_order_package_strategy[idx++];
                var packageIdx = 0;
                while (packageIdx < strategy.course_class_hour_package_info.length) {
                    var package = strategy.course_class_hour_package_info[packageIdx++];
                    if (packageId == package.package_id) {
                        return package;
                    }
                }
            }
        }

        if (detailForOrderObj.new_order_package_strategy != null) {
            var idx = 0;
            while (idx < detailForOrderObj.new_order_package_strategy.length) {
                var strategy = detailForOrderObj.new_order_package_strategy[idx++];
                var packageIdx = 0;
                while (packageIdx < strategy.course_class_hour_package_info.length) {
                    var package = strategy.course_class_hour_package_info[packageIdx++];
                    if (packageId == package.package_id) {
                        return package;
                    }
                }
            }
        }
    }

    $("#customBuyBtn").click(function(){
        var customHours = $("#customCourseHour").val();
        if(customHours == null || customHours < 0){
            $.gritter.add({
                title : '提示:',
                text : "购买课时数异常",
                class_name : 'gritter-error gritter-center'
            });
            return false;
        }

        var gradeId = $("#gradeId").val();

        var courseIdselect = $("#courseId").val();
        if (courseIdselect == null || courseIdselect.length == 0) {
            $.gritter.add({
                title : '提示:',
                text : "请选择科目",
                class_name : 'gritter-error gritter-center'
            });

            return false;
        }
        var courseIds = courseIdselect.map(Number);

        var studentId = new Number($("#studentId").val());

        var param = {};
        param.student_id = studentId;
        param.grade_id = new Number(gradeId);
        param.course_id = courseIds;
        param.order_create_type = "general_order_service_order_create_type";
        param.custom_add_order_info = {};
        param.custom_add_order_info.custom_class_hour_number = new Number(customHours);
        param.custom_add_order_info.custom_effect_days = 365;
        param.custom_add_order_info.class_hour_price_config_id = detailForOrderObj.custom_course_class_hour_package_info.class_hour_price_config_id;
        param.total_price =  param.custom_add_order_info.custom_class_hour_number * detailForOrderObj.custom_course_class_hour_package_info.guide_price;

        addOrder(param);
    });

    $("#scoreBuyBtn").click(function(){
        var customHours = $("#scoreCourseHour").val();
        if(customHours == null || customHours < 0){
            $.gritter.add({
                title : '提示:',
                text : "购买课时数异常",
                class_name : 'gritter-error gritter-center'
            });
            return false;
        }

        var scoreCount = $("#scoreCount").val();
        if(scoreCount == null || scoreCount < 0){
            $.gritter.add({
                title : '提示:',
                text : "使用轻豆数异常",
                class_name : 'gritter-error gritter-center'
            });
            return false;
        }

        var gradeId = $("#gradeId").val();

        var courseIdselect = $("#courseId").val();
        if (courseIdselect == null || courseIdselect.length == 0) {
            $.gritter.add({
                title : '提示:',
                text : "请选择科目",
                class_name : 'gritter-error gritter-center'
            });

            return false;
        }
        var courseIds = courseIdselect.map(Number);

        var studentId = new Number($("#studentId").val());

        var param = {};
        param.student_id = studentId;
        param.grade_id = new Number(gradeId);
        param.course_id = courseIds;
        param.order_create_type = "score_order_service_order_create_type";
        param.score_exchange_order = {};
        param.score_exchange_order.score_exchange_number = new Number(scoreCount);
        param.score_exchange_order.score_exchange_order_amount = customHours * detailForOrderObj.custom_course_class_hour_package_info.guide_price;
        param.score_exchange_order.custom_class_hour_number = new Number(customHours);
        param.score_exchange_order.custom_effect_days = 365;
        param.score_exchange_order.class_hour_price_config_id = detailForOrderObj.custom_course_class_hour_package_info.class_hour_price_config_id;
        param.uniq_create_order = {};
        param.uniq_create_order.uniq_ref_type = "score_exchange_orderservice_uniq_ref_type";
        param.uniq_create_order.uniq_ref_id = studentId + "-" + scoreCount + "-" + new Date().getTime();
        param.total_price =  param.score_exchange_order.custom_class_hour_number * detailForOrderObj.custom_course_class_hour_package_info.guide_price;

        addOrder(param);
    });

    function addOrder(param){
        // 将当前部门归属类型设置为我下单需要的类型
        changeDepartmentType(detailForOrderObj.departmentId, detailForOrderObj.isQingQing? "qingqing_business_department_type":"qingqing_online_business_department_type");

        var data = {
            url :"/orderservice/api/pi/v1/class_hour_order/add_order.json",
            param : JSON.stringify(param),
            userType : $("#requestUserType").val(),
            userId : $("#requestUserId").val()
        };

        var studentId = new Number($("#studentId").val());

        var createOrderResult = getDataFromPi("${base}", data, $("#env").val(), $("#guid").val());
        createOrderResultObj = createOrderResult;

        if(createOrderResult.response != null && createOrderResult.response.error_code == 0) {
            createOrderResultObj = createOrderResult;
            if ($("#useValueVoucher").val() == "1") {
                var valueRequestData = {"order_type": "class_hour_v2_order_type", "order_price": param.total_price};
                var valueData = {
                    url: "/svc/api/pt/v1/valuevouchers/list_for_adding_class_hour_v2_order.json",
                    param: JSON.stringify(valueRequestData),
                    userType: "student",
                    userId: param.student_id
                };
                var aaa = getDataFromPt("${base}", valueData, $("#env").val(), $("#guid").val());
                var stageConfigs = new Array();
                var siteIdx = 0;
                if (aaa.value_vouchers) {
                    for (var itemIdx in aaa.value_vouchers) {
                        var item = aaa.value_vouchers[itemIdx];
                        var stageConfig = new Object();
                        stageConfig.key = item.value_voucher_instance_id;
                        stageConfig.value = item.name + "(减" + item.reward_amount + ")" ;
                        stageConfigs[siteIdx++] = stageConfig;
                    }
                }

                var recommanderId = null;
                if (siteIdx > 0) {
                    if (aaa.recommend_value_vouchers && aaa.recommend_value_vouchers.length > 0) {
                        recommanderId = aaa.recommend_value_vouchers[0];
                    } else {
                        recommanderId = stageConfigs[0].key;
                    }
                }

                updateOptions("valueVoucherInstanceId", stageConfigs, recommanderId);

                $("#useValueVoucherDiv").removeClass("hide");
                return;
            } else {
                gotoPay(createOrderResult);
            }
        }
    }

    function gotoPay(createOrderResult) {
        if(createOrderResult.response != null && createOrderResult.response.error_code == 0){
            var naxtPageUrl = "${base}/v1/pay/mock_pay_notify?catelogIndex=1-4&paramId=-1&gnp=1&inv=1&env={env}&uid={userId}&uty=student&def=%7b%22qingqing_common_order_id%22%3a%22{qingqing_order_id}%22%2c%22order_type%22%3a%22class_hour_v2_order_type%22%7d";
            naxtPageUrl = naxtPageUrl.replace("{env}", $("#env").val());
            naxtPageUrl = naxtPageUrl.replace("{userId}", new Number($("#studentId").val()));
            naxtPageUrl = qingReplaceStringWithObj(naxtPageUrl, createOrderResult);

            window.open(naxtPageUrl);
        }
    }

    function getStudentDepartmentInfo(studentId){
        var data = {
            url :"/orderservice/api/pi/v1/test/class_hour_order/student/info.json",
            param : '{"data":' + studentId + '}',
            userType : $("#requestUserType").val(),
            userId : $("#requestUserId").val()
        };

        return getDataFromPi("${base}", data, $("#env").val(), $("#guid").val());
    }

    function getDetailForOrder(departmentId, gradeId){
        var data = {
            url :"/orderservice/api/pi/v1/class_hour_order/detail_for_order.json",
            param : '{"department_id":' + departmentId + ',"grade_id":' + gradeId + '}',
            userType : $("#requestUserType").val(),
            userId : $("#requestUserId").val()
        };

        return getDataFromPi("${base}", data, $("#env").val(), $("#guid").val());
    }

    function validStudentId(studentId){
        if(studentId == null || studentId < 1){
            $.gritter.add({
                title : '提示:',
                text : "学生ID非法",
                class_name : 'gritter-error gritter-center'
            });
            return false;
        }

        return true;
    };

    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();

        //jquery accordion
        $( "#accordion" ).accordion({
            collapsible: true ,
            heightStyle: "content",
            animate: 250,
            header: ".accordion-header"
        }).sortable({
            axis: "y",
            handle: ".accordion-header",
            stop: function( event, ui ) {
                // IE doesn't register the blur when sorting
                // so trigger focusout handlers to remove .ui-state-focus
                ui.item.children( ".accordion-header" ).triggerHandler( "focusout" );
            }
        });
    });
</script>
    </div>
</body>
</html>