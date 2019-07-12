<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>收银台</title>
    <#include "/include/resource.ftl" />
    <link href="${base}/static/css/json/base.css" rel="stylesheet">
    <link href="${base}/static/css/json/jquery.numberedtextarea.css" rel="stylesheet">

    <script src="${base}/static/js/json/hm.js"></script>
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
                    <div class="page-content">
                        <div class="page-header">
                            <h1>
                                接口测试
                                <small>
                                    <i class="icon-double-angle-right"></i>
                                    <label id = "interfaceNameDiv">Common form elements and layouts</label>
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

                                <div class="hr hr-dotted"></div>
                                <div class="hr hr-dotted"></div>

                                <!-- PAGE CONTENT BEGINS -->
                                <#include "/include/param.ftl" />

                                <div class="clearfix form-actions">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button class="btn btn-info" style="border-radius: 8px" type="button" id = "submitBtn">
                                            <i class="icon-ok bigger-110"></i>
                                            Submit
                                        </button>

                                        &nbsp; &nbsp; &nbsp;
                                        <button class="btn" style="border-radius: 8px" type="reset" id="resetBtn">
                                            <i class="icon-undo bigger-110"></i>
                                            Save Example
                                        </button>
                                    </div>
                                </div>

                                <div class="hr hr-dotted"></div>
                                <form class="form-horizontal hide" id="resultShow">
                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="payType">选择支付方式</label>
                                        <div class="col-xs-12 col-sm-8">
                                            <select class="width-80 chosen-select" id="payType" data-placeholder="选择支付方式...">
                                                <option value="">&nbsp;</option>
                                            </select>
                                            <span class="input-group-btn">
                                                                            <button type="button" class="btn btn-purple btn-xs" id="addPayTypeBtn">
                                                                                新增支付路径
                                                                                <i class="icon-search icon-on-right bigger-110"></i>
                                                                            </button>
                                                                        </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="balancePayAmount">钱包支付金额</label>
                                        <div class="col-xs-12 col-sm-3">
                                            <input type="number" id="balancePayAmount" value="0" />
                                        </div>
                                    </div>
                                    <div class="form-group hide" id = "multiPayAmountDiv">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="multiPayAmount">分次支付金额</label>
                                        <div class="col-xs-12 col-sm-3">
                                            <input type="number" id="multiPayAmount" value="0" />
                                        </div>
                                    </div>
                                    <div class="form-group hide" id="stageChooseDiv">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="stageConfigId">选择分期数</label>
                                        <div class="col-xs-4 col-sm-4">
                                            <div>
                                                <select class="width-80 form-control" id="stageConfigId" multiple="multiple">
                                                    <option value="AL">￥567 x 3期（手续费￥81）</option>
                                                    <option value="AK">￥567 x 3期（手续费￥81）</option>
                                                    <option value="AZ">￥567 x 3期（手续费￥81）</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="orderAmount">订单金额</label>
                                        <div class="col-xs-12 col-sm-3">
                                            <div class="alert alert-danger center">
                                                <strong>
                                                    <span id="orderAmountTxt"></span>
                                                </strong>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="balanceAmount">钱包余额</label>
                                        <div class="col-xs-12 col-sm-3">
                                            <div class="alert alert-danger center">
                                                <strong>
                                                    <span id="balanceAmountTxt"></span>
                                                </strong>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" style="text-align: right;" for="balanceAmount">第三方支付路径:</label>
                                        <div class="col-xs-12 col-sm-6">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>支付方式</th>
                                                        <th>关联记录</th>
                                                        <th>TradeId</th>
                                                        <th class="hidden-480">Status</th>
                                                        <th>操作</th>
                                                    </tr>
                                                    </thead>

                                                    <tbody id="payWayList">
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="balanceAmount">操作：</label>
                                        <div class="col-xs-12 col-sm-3">
                                            <button class="btn btn-info" style="border-radius: 8px" type="button" id = "payNotifyBtn">
                                                支付服务-补偿通知
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            <#include "/include/righttool-sidebar.ftl" />

        <script type="text/javascript">
            var logUrl = "http://172.22.12.14:5601/app/logtrail#/?q=env_type:%20%22{env}%22%20%26%26%20guid:%20%22{guid}%22&t=Now&i=rsyslog-app*&_g=()&h=svc";
            function refreshInterfaceUrl(){
                var env = $("#env").val();
                var guid = generateGuid();

                var url = "查看日志（guid={guid} && env={env}）".replace("{env}", env);
                url = url.replace("{guid}", guid);

                var logTargetUrl = logUrl.replace("{env}", env);
                logTargetUrl = logTargetUrl.replace("{guid}", guid);

                $("#interfaceUrl").text(url);
                $("#guid").val(guid);
                $("#logUrl").attr("href", logTargetUrl);
            }

            var paramInfo;

            $('#payNotifyBtn').click(payNotify);

            function payNotify(){
                var data = {
                    data : "/paysvc/api/crontab/v1/auto_sync_third_pay_notify"
                }

                var isLocalDebug = $("#isLocalDebug").val();
                var localPort = $("#localDebugPort").val();
                commonAjaxRequest("${base}/v1/common/crond_task.json?is_local=" + isLocalDebug + "&local_port=" + localPort, data, emptyFunction, false, "支付服务-交易补偿通知:", $("#env").val(), null, $("#guid").val());
            }


            $('#submitBtn').click(prePayV2);

            function prePayV2(isAsync){
                var data = {
                    qingqingOrderId : getParam("qingqing_common_order_id"),
                    sourceChannel : getParam("source_channel"),
                    userId : getParam("requestUserId"),
                    orderType : getParam("order_type"),
                    userType :  $("#requestUserType").val()
                };

                var isLocalDebug = $("#isLocalDebug").val();
                var localPort = $("#localDebugPort").val();
                return commonAjaxRequest("${base}/v1/pay/pre_pay_v2.json?is_local=" + isLocalDebug + "&local_port=" + localPort, data, handlerPrePay, isAsync, "获取订单前置接口失败:", $("#env").val(), null, $("#guid").val());
            }

            function getParam(name){
                return $("input[name = '" + name + "']").val();
            }

            var installmentConfigs;
            function handlerPrePay(resu){
                // 更新支付方式下下拉框
                updateOptions("payType", resu.supportPayTypeList, "qingqing_balance");
                for(var idx in resu.supportPayTypeList){
                    var payTypeSelectable = resu.supportPayTypeList[idx];
                    if(payTypeSelectable.key == 'multiple_pay'){
                        $("#multiPayAmountDiv").removeClass("hide");
                        break;
                    }
                }


                $("#payType_chosen").css('width','200px');

                $("#orderAmountTxt").text(resu.needPayAmount);

                var balanceAmount = new Number(resu.balanceAmount)
                $("#balanceAmountTxt").text(balanceAmount.toLocaleString());

                installmentConfigs = resu.installmentConfigs;

                $("#resultShow").removeClass("hide");

                var payType = $("#payType").val();
                if(payType == "qingqing_balance"){
                    $("#balancePayAmount").val($("#orderAmountTxt").text());
                }
            }

            var multiOrderIds = [];
            var multiOrderIdx = 0;
            function updatePayWayList(){
                var data = generateJsonParam("#paramListDiv input", paramInfo);

                var isLocalDebug = $("#isLocalDebug").val();
                var localPort = $("#localDebugPort").val();
                commonAjaxRequest("${base}/v1/pay/pay_infos_2.json?is_local=" + isLocalDebug + "&local_port=" + localPort, data, handlerPayWayList, true, "获取第三方支付路径出错:", $("#env").val(), null, $("#guid").val());

//                for(var idx in multiOrderIds){
//                    var multiSubOrder = multiOrderIds[idx];
//                    var requestData = {
//                        order
//                    }
//                }
            }

            var syncPayWayList;
            function handlerPayWayList(resu){
                if(resu.payBriefList == null){
//                    $.gritter.add({
//                        title : '第三方支付路径:',
//                        text : '未找到第三方支付路径',
//                        class_name : 'gritter-info gritter-center'
//                    });
                    return;
                }
                $("#payWayList").html(thirdPayWayList(resu.payBriefList));

                if(syncPayWayList == null){
                    syncPayWayList = setInterval(updatePayWayList, 5000);
                }
                return true;
            }

            $('#addPayTypeBtn').click(function () {
                return pay();
            })

            var checkPayTimer;
            function pay(){
                var result = false;
                var payType = $("#payType").val();
                var stageConfigIds = $("#stageConfigId").val();
                var stageList = getStageInfo(payType);
                if(stageList != null && stageConfigIds == null){
                    $.gritter.add({
                        title : '字段未选择:',
                        text : '请选择分期方案',
                        class_name : 'gritter-error gritter-center'
                    });
                    return false;
                }

                var stageConfigId;
                if(stageConfigIds != null){
                    stageConfigId = stageConfigIds[0];
                }

                var data = {
                    qingqingOrderId : getParam("qingqing_common_order_id"),
                    orderType : getParam("order_type"),
                    orderAmount : $("#orderAmountTxt").text(),
                    userId : getParam("requestUserId"),
                    userType : $("#requestUserType").val(),
                    payType : payType,
                    stageConfigId : stageConfigId,
                    balancePayAmount : $("#balancePayAmount").val(),
                    multiPayAmount : $("#multiPayAmount").val(),
                    sourceChannel : getParam("source_channel"),
                };

                var isLocalDebug = $("#isLocalDebug").val();
                var localPort = $("#localDebugPort").val();
                result = commonAjaxRequest("${base}/v1/pay/ack_pay_v2.json?is_local=" + isLocalDebug + "&local_port=" + localPort, data, handlerPay, false, "新增支付路径失败:", $("#env").val(), null, $("#guid").val());
                return result;
            }

            function getStageInfo(payType){
                for(idx in installmentConfigs){
                    var installmentConfig = installmentConfigs[idx];
                    if(installmentConfig.payType == payType){
                        var stageConfigs = new Array();
                        var siteIdx = 0;
                        for(var itemIdx in installmentConfig.items){
                            var item = installmentConfig.items[itemIdx];
                            var stageConfig = new Object();
                            stageConfig.key = item.configId;
                            stageConfig.value = "￥" + item.stageAmount +  " x " + item.stageNum + "期（手续费￥" + item.serviceAmount + "）";
                            stageConfigs[siteIdx++] = stageConfig;
                        }
                        return stageConfigs;
                    }
                }

                return null;
            }

            function handlerPay(resu){
                // 更新支付方式下下拉框
                updatePayWayList();

                var payType = $("#payType").val();
                if(payType == 'qingqing_balance'){
                    checkPay();
                }else{
                    if(checkPayTimer == null){
                        checkPayTimer = setInterval(checkPay, 5000);
                    }
                }

                if(resu.multiple_pay_info != null && resu.multiple_pay_info.qingqing_multiple_pay_sub_order_id != null){
                    multiOrderIds[multiOrderIdx ++ ] = resu.multiple_pay_info.qingqing_multiple_pay_sub_order_id;
                }

                return true;
            }

            function checkPay(){
                var data = {
                    qingqingOrderId : getParam("qingqing_common_order_id"),
                    userId : getParam("requestUserId"),
                    userType :  $("#requestUserType").val(),
                    orderType : getParam("order_type")
                };

                var isLocalDebug = $("#isLocalDebug").val();
                var localPort = $("#localDebugPort").val();
                commonAjaxRequest("${base}/v1/pay/check_pay_v2.json?is_local=" + isLocalDebug + "&local_port=" + localPort, data, handlerCheckPay, false, "查询支付状态失败:", $("#env").val(), null, $("#guid").val());
            }

            function handlerCheckPay(data){
                if(data.is_pay_success){
                    $("#orderStatus").text("已支付");

                    $.gritter.add({
                        title : '支付状态变更',
                        text : '订单已支付成功',
                        class_name : 'gritter-info gritter-center'
                    });

                    if(checkPayTimer != null){
                        clearTimeout(checkPayTimer);
                        checkPayTimer = null;
                    }
                }
                return true;
            }

            $("#payType").change(function () {
                var payType = $("#payType").val();
                var stageList = getStageInfo(payType);
                if(stageList != null){
                    updateOptions("stageConfigId", stageList, stageList[0].key);
                    $("#stageChooseDiv").removeClass("hide");
                }else{
                    $("#stageChooseDiv").addClass("hide");
                }

                if(payType == "qingqing_balance"){
                    $("#balancePayAmount").val($("#orderAmountTxt").text());
                }else{
                    $("#balancePayAmount").val(0);
                }
            });

            $(document).off("click", '.mockPayBtn').on('click', '.mockPayBtn',function(){
                var qinqqingTradeNo = $(this).parent().parent().prev("td").prev("td").prev("td").text().trim();
                var data = {
                    data : qinqqingTradeNo
                };

                var isLocalDebug = $("#isLocalDebug").val();
                var localPort = $("#localDebugPort").val();
                commonAjaxRequest("${base}/v1/pay/mock_third_pay.json?is_local=" + isLocalDebug + "&local_port=" + localPort, data, handlerMockThirdNotify, true, "模拟第三方支付成功通知失败:", $("#env").val(), null, $("#guid").val());


                if(syncPayWayList == null){
                    syncPayWayList = setInterval(updatePayWayList, 5000);
                }
            });

            function handlerMockThirdNotify(resu){
                $.gritter.add({
                    title : '模拟第三方支付成功通知成功:',
                    text : '模拟第三方支付成功通知成功',
                    class_name : 'gritter-info gritter-center'
                });
            }

            $(document).ready(function(){
                refreshPage();
            });

            function refreshPage(){
                var data = {
                    data : 1
                };
                commonAjaxRequest("${base}/v1/test/interface.json", data, handlerInterface, true, "获取接口信息失败:");
            }

            $(".env").click(function(){
                $(".env.btn-primary").removeClass("btn-primary");
                $(this).addClass("btn-primary");
                $("#env").val($(this).val());

                refreshInterfaceUrl();
            });

            var userTypeArr = [];
            $.each(["student", "teacher", "assistant"] , function(k, v){
                userTypeArr.push({id: v, text: v});
            });
            function handlerInterface(resu){
                var params = JSON.parse(resu.interfaceInfo.inter.paramDetail);
                if(resu.interfaceInfo.inter.interfaceType == "PT" || resu.interfaceInfo.inter.interfaceType == "PI"){
                    $("#requestUserIdDev").removeClass("hide");
                    var userType = "${userType!''}";
                    if(userType == ''){
                        userType = resu.interfaceInfo.inter.requestUserType;
                    }
                    $("#requestUserTypeDiv").text(userType);
                    $("#requestUserType").val(userType);

                    $('#requestUserTypeDiv').editable({
                        type: 'select2',
                        value : resu.interfaceInfo.inter.requestUserType,
                        source: userTypeArr,
                        success: function(response, newValue) {
                            $(this).prev("input").val(newValue);
                        }
                    });

                    $("#selfParamSwitch").val(${full!0});

                    if(resu.interfaceInfo.inter.paramDetail != null && resu.interfaceInfo.inter.paramDetail != ""){
                        jsonShow(resu.interfaceInfo.inter.paramDetail, "json-interface-detail");
                        var paramDetail = fillDefaultValue(JSON.parse(resu.interfaceInfo.inter.paramDetail));
                        paramInfo = showParam({paramData:paramDetail});

                        paramExamples = resu.interfaceInfo.paramList;
                        initParamChoose(paramExamples, ${paramExampleId!0});
                        if(${paramExampleId!0} == 0){
                            fillFullParam();
                        }
                    }

                    var env = '${env!"dev"}';
                    $("#env").val(env);
                    $(".env.btn-primary").removeClass("btn-primary");
                    $(".env[value='" + env + "']").addClass("btn-primary");

                    refreshInterfaceUrl();

                    if(${inv!0} == 1){
                        prePayV2();
                    }
                }

                interfaceParam = params;
                $("#interfaceNameDiv").text(resu.interfaceInfo.inter.interfaceName);
            }

            function fillDefaultValue(paramArr){
                var defaultObj = new Object(${defaultObj!"{}"});
                for(var paramIdx in paramArr){
                    var param = paramArr[paramIdx];
                    for(var propName in defaultObj){
                        if(param.key == propName){
                            if(param.defaultValue.name != null){
                                param.defaultValue.name = defaultObj[propName];
                                param.defaultValue.value = defaultObj[propName];
                            }else{
                                param.defaultValue = defaultObj[propName];
                            }
                            break;
                        }
                    }
                }

                return JSON.stringify(paramArr);
            }

            function fillFullParam(){
                var param = generateJsonParam("#paramListDiv input", paramInfo);
                $("#fullParam").text(JSON.stringify(param));
            }

            function initParamChoose(paramChooses, paramExampleId){
                if(paramChooses.length == 0){
                    $("#paramChooseDiv").addClass("hide");
                    return;
                }

                var options = [];
                var optionIdx = 0;
                var paramEx;

                var defaultOption = new Object();
                defaultOption.key = 0;
                defaultOption.value = "咱不选";

                options[optionIdx++] = defaultOption;
                for(idx in paramChooses){
                    var data = paramChooses[idx];
                    var option = new Object();
                    option.key = data.id;
                    option.value = data.paramName + "(" + data.id + ")";
                    if(paramExampleId == 0 && data.default){
                        paramEx = data;
                        paramExampleId = data.id;
                    }else if(data.id == paramExampleId){
                        paramEx = data;
                    }

                    options[optionIdx++] = option;
                }
                updateOptions("paramChoose", options, paramExampleId);
                if(paramEx != null){
                    paramInfo = showParam({paramData:paramEx.paramDetail});
                    $("#requestUserId").val(paramEx.requestUserId);
                    $("#requestUserIdDiv").text(paramEx.requestUserId);
                }

                $("#paramChooseDiv").removeClass("hide");
                $("#paramChoose").trigger("chosen:updated");

                $("#paramChoose_chosen").css('width','200px');

                if($("#selfParamSwitch").val() == 0){
                    fillFullParam();
                }else{
                    if(paramEx != null){
                        $("#fullParam").text(paramEx.fullParam);
                    }
                    $("#selfParamSwitch").attr("checked", "checked")
                    showFull();
                }
            }

            $("#paramChoose").change(function(){
                var id = $(this).val();
                if(id != 0){
                    for(idx in paramExamples){
                        var paramEx = paramExamples[idx];
                        if(paramEx.id == id){
                            paramInfo = showParam({paramData:paramEx.paramDetail});
                            $("#requestUserId").val(paramEx.requestUserId);
                            $("#requestUserIdDiv").text(paramEx.requestUserId);

                            if(paramEx.fullParam == null || paramEx.fullParam == ""){
                                fillFullParam();
                            }else{
                                $("#fullParam").text(paramEx.fullParam);
                            }

                            $(".param-ops").removeClass("hide");
                            break;
                        }
                    }
                }else{
                    $(".param-ops").addClass("hide");
                }
            });

            $("#resetBtn").click(function() {
                bootbox.prompt("取个名字", function(result) {
                    if (result === null) {
                        $.gritter.add({
                            title : "参数示例",
                            text : "这个名字还是要有的",
                            class_name : 'gritter-error gritter-center'
                        });
                        return;
                    } else {
                        var paramDetail = generateEditParam("#paramListDiv input");
                        var fullParam;
                        if($("#selfParamSwitch").val() == 1){
                            fullParam = $("#fullParam").text();
                        }else{
                            fullParam = JSON.stringify(generateJsonParam("#paramListDiv input", paramInfo));
                        }
                        var data = {
                            id : $("#paramChoose").val(),
                            interfaceId : 1,
                            requestUserId : $("#requestUserId").val(),
                            paramDetail : paramDetail,
                            deleted : 0,
                            default : 0,
                            paramName : result,
                            fullParam : fullParam
                        };

                        commonAjaxRequest("${base}/v1/test/interface/param/save.json", data, handlerParamSave, true, "参数样例保存出错:");
                    }
                });
            });

            function handlerParamSave(resu){
                $.gritter.add({
                    title : "参数示例",
                    text : "保存成功",
                    class_name : 'gritter-info gritter-center'
                });

                refreshPage();
            }

            $("#param_del").click(function(){
                var paramId = $("#paramChoose").val();

                var data = {
                    data : new Number(paramId)
                };

                commonAjaxRequest("${base}/v1/test/interface/param/delete.json", data,  refreshPage, true, "参数删除出错:");
            });

            $("#param_default").click(function(){
                var paramId = $("#paramChoose").val();

                var data = {
                    data : new Number(paramId)
                };

                commonAjaxRequest("${base}/v1/test/interface/param/default/set.json", data, notOps, true, "参数设置默认出错:");
            });

            jQuery(function($) {
                $(".chosen-select").chosen();
                $('[data-rel=tooltip]').tooltip();

                $("#requestUserIdDiv").editable({
                    type: 'text',
                    name: 'username',
                    success: function(response, newValue) {
                        $(this).prev("input").val(newValue);
                    }
                });
            });
        </script>
    </div>
</body>
</html>