<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>合同流程</title>
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
                    <div class="page-content">
                        <div class="page-header">
                            <h1>
                                流程测试
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
                                <input type="hidden" id="contractId">

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
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="idCardNo">身份证号</label>
                                        <div class="col-xs-12 col-sm-3">
                                            <input type="number" id="idCardNo" value="360681199202144250" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="realName">实名</label>
                                        <div class="col-xs-12 col-sm-3">
                                            <input type="text" id="realName" value="张三" />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="createBtn"></label>
                                        <div class="col-xs-12 col-sm-3">
                                            <button type="button" class="btn btn-purple btn-xs" id="createBtn">
                                                创建合同
                                                <i class="icon-search icon-on-right bigger-110"></i>
                                            </button>
                                        </div>
                                    </div>

                                    <div class="hr hr-dotted"></div>

                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" style="text-align: right;" for="balanceAmount">合同列表:</label>
                                        <div class="col-xs-12 col-sm-9">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>合同ID</th>
                                                        <th>上上签合同ID</th>
                                                        <th>上上签用户账号</th>
                                                        <th>手机号</th>
                                                        <th>实名</th>
                                                        <th>身份证</th>
                                                        <th class="hidden-480">Status</th>
                                                        <th>回调模拟</th>
                                                        <th>查看合同</th>
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
                                                合同服务-补偿通知
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
            var logUrl = "http://172.22.12.14:5601/app/logtrail#/?q=env_type:%20%22{env}%22%20%26%26%20guid:%20%22{guid}%22&t=Now&i=rsyslog-app*&_g=()&h=contractsvc";
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

            function queryPreInfo(){
                var data = {
                    url :"/contractsvc/api/h5pt/v1/contract/sign_contract_pre.json",
                    param : '{"data":' + getContractId() + '}',
                    userType : $("#requestUserType").val(),
                    userId : $("#requestUserId").val()
                };

                var request = {
                    url : "${base}/v1/common/pt.json",
                    data : data,
                    handlerFunc : handleQueryContractResult,
                    isASync : false,
                    failTitle :"查询数据失败:",
                    env : $("#env").val(),
                    guid : $("#guid").val()
                };

                return commonAjaxRequest(request);
            }

            function handleQueryContractResult(resu){
                return resu;
            }

            $("#submitBtn").click(queryContractInfo);

            function queryContractInfo(){
                var data = {
                    url :"/orderservice/api/pi/v1/class_hour_order/student/order_detail.json",
                    param : '{"qingqing_common_order_id":"' + getParam("qingqing_common_order_id") + '", "order_type":"' + getParam("order_type") + '"}',
                    userType : $("#requestUserType").val(),
                    userId : $("#requestUserId").val()
                };

                var request = {
                    url : "${base}/v1/common/pi.json",
                    data : data,
                    handlerFunc : handleQueryContractId,
                    isASync : false,
                    failTitle :"查询数据失败:",
                    env : $("#env").val(),
                    guid : $("#guid").val()
                };

                commonAjaxRequest(request);

                var preInfo = queryPreInfo();
                if(preInfo!= null){
                    if(preInfo.signer_name != null){
                        $("#realName").val(preInfo.signer_name);
                    }

                    if(preInfo.signer_ID_card != null){
                        $("#idCardNo").val(preInfo.signer_ID_card);
                    }
                }
            }

            function handleQueryContractId(resu){
                if(resu.contract_id == null){
                    gritterError('查询失败', '未查询到该订单的合同ID');
                    return;
                }

                $("#contractId").val(resu.contract_id);
                refreshContractList();

                // 查询当前
                $("#resultShow").removeClass("hide");
            }

            $("#createBtn").click(function() {
                var realName = $("#realName").val();
                var idCardNo = $("#idCardNo").val();
                if(realName == ""){
                    $.gritter.add({
                        title : '提示:',
                        text : "请输入实名",
                        class_name : 'gritter-error gritter-center'
                    });
                    return false;
                }

                if(idCardNo == ""){
                    $.gritter.add({
                        title : '提示:',
                        text : "请输入身份证号",
                        class_name : 'gritter-error gritter-center'
                    });
                    return false;
                }

                applyLink(realName, idCardNo);

                refreshContractList();
            });

            function applyLink(realName, idCardNo){
                var data = {
                    url :"/contractsvc/api/h5pt/v1/contract/apply_sign_contract_link.json",
                    param : '{"contract_id":' + getContractId() + ', "signer_name":"' + realName + '", "signer_ID_card":"' + idCardNo + '","return_url":"https://www.baidu.com"}',
                    userType : $("#requestUserType").val(),
                    userId : $("#requestUserId").val()
                };

                var request = {
                    url : "${base}/v1/common/pt.json",
                    data : data,
                    handlerFunc : handleCreateContractId,
                    isASync : false,
                    failTitle :"查询数据失败:",
                    env : $("#env").val(),
                    guid : $("#guid").val()
                };

                return commonAjaxRequest(request);
            }

            function handleCreateContractId(resu){
                return resu.data;
            }

            function getContractId(){
                return new Number($("#contractId").val());
            }

            function refreshContractList(){
                var data = {
                    url :"/contractsvc/api/pi/v1/test/contract/ssq/contract_list.json",
                    param : '{"data":' + getContractId() + '}',
                    userType : $("#requestUserType").val(),
                    userId : $("#requestUserId").val()
                };

                var request = {
                    url : "${base}/v1/common/pi.json",
                    data : data,
                    handlerFunc : handleRefreshContractList,
                    isASync : false,
                    failTitle :"查询数据失败:",
                    env : $("#env").val(),
                    guid : $("#guid").val()
                };

                commonAjaxRequest(request);
            }

            function handleRefreshContractList(resu){
                $("#payWayList").html(contractList(resu.resultList));
            }

            function contractList(payBriefList){
                var trText = "";

                if(payBriefList != null){
                    var signedContractId = 0;
                    var firstIdx  = 0;
                    while(firstIdx < payBriefList.length){
                        var payBrief = payBriefList[firstIdx++];
                        if(payBrief.contractStatus == "signed" || payBrief.contractStatus == "lock"){
                            signedContractId = payBrief.id;
                            break;
                        }
                    }

                    var secondIdx  = 0;
                    while(secondIdx < payBriefList.length){
                        var payBrief = payBriefList[secondIdx++];

                        var linkTd = "";
                        if(signedContractId == payBrief.id){
                            var preInfo = queryPreInfo();
                            if(preInfo != null){
                                linkTd = "<td><a target='_blank' href='" + preInfo.contract_result_url + "' class='q_view_contract'>查看</a> </td>";
                            }else{
                                linkTd = "<td></td>";
                            }
                        }else{
                            if(signedContractId > 0){
                                linkTd = "<td></td>";
                            }else{
                                linkTd = "<td><a target='_blank' href='" + applyLink(payBrief.realName, payBrief.idCardNo) + "' class='q_view_contract'>查看</a> </td>";
                            }
                        }

                        trText = trText + "<tr>" +
                                "<td>" + payBrief.contractId + "</td>" +
                                "<td class=\"hidden-480\">" + payBrief.ssqContractId+ "</td>" +
                                "<td>" + payBrief.ssqAccountId + "</td>" +
                                "<td>" + payBrief.phoneNumber + "</td>" +
                                "<td>" + payBrief.realName + "</td>" +
                                "<td>" + payBrief.idCardNo + "</td>" +
                                "<td class=\"hidden-480\"><span class=\"label label-sm label-warning\">" + getSignStatus(payBrief.contractStatus) + "</span></td>" +
                                "<td><div class=\" btn-group\"><button type=\"button\" class=\"btn btn-xs btn-success mockNotifyBtn\"><i class=\"icon-ok bigger-120\"></i></button></div></td>" +
                                linkTd +
                                "</tr>";
                    }
                }

                return trText;
            }

            $(document).off("click", '.mockNotifyBtn').on('click', '.mockNotifyBtn',function(){
                var ssqContractId = $(this).parent().parent().prev("td").prev("td").prev("td").prev("td").prev("td").prev("td").text().trim();
                var ssqAccountId = $(this).parent().parent().prev("td").prev("td").prev("td").prev("td").prev("td").text().trim();
                var data = {
                    ssqContractId : ssqContractId,
                    ssqAccountId :ssqAccountId
                };

                var request = {
                    url : "${base}/v1/contract/mock_notify.json",
                    data : data,
                    handlerFunc : handlerMockThirdNotify,
                    isASync : false,
                    failTitle :"模拟回调通知失败:",
                    env : $("#env").val(),
                    guid : $("#guid").val()
                };

                commonAjaxRequest(request);


                refreshContractList();
            });

            function handlerMockThirdNotify(resu){
                $.gritter.add({
                    title : '结果:',
                    text : '模拟第三方回调成功',
                    class_name : 'gritter-info gritter-center'
                });
            }

            function getSignStatus(status){
                switch (status){
                    case "wait_to_sign":
                        return "待签署";
                    case "signed":
                        return "已签署";
                    case "repeal":
                        return "已作废";
                    case "lock":
                        return "已锁定";
                    default:
                        return status;
                }
            }

            $('#payNotifyBtn').click(payNotify);

            function payNotify(){
                var data = {
                    data : "/contractsvc/api/crontab/v1/active_notify"
                }

                var isLocalDebug = $("#isLocalDebug").val();
                var localPort = $("#localDebugPort").val();
                var request = {
                    url : "${base}/v1/common/crond_task.json",
                    data : data,
                    handlerFunc : emptyFunction,
                    isASync : false,
                    failTitle :"合同服务-补偿通知:",
                    env : $("#env").val(),
                    otherData : null,
                    guid : $("#guid").val()
                };

                commonAjaxRequest(request);
            }

            var paramInfo;

            function getParam(name){
                return $("input[name = '" + name + "']").val();
            }

            $(document).ready(function(){
                refreshPage();

                $("#qing_local_switch_div").removeClass("hide");
            });

            function refreshPage(){
                var data = {
                    data : 548
                };
                var request = {
                    url : "${base}/v1/test/interface.json",
                    data : data,
                    handlerFunc : handlerInterface,
                    isASync : true,
                    failTitle :"获取接口信息失败:",
                    env : $("#env").val(),
                    otherData : null,
                    guid : $("#guid").val()
                };

                commonAjaxRequest(request);
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
            var interfaceBean;
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
                    interfaceBean = resu.interfaceInfo.inter;

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
                }

                refreshInterfaceUrl();
                if(${inv!0} == 1){
                    queryContractInfo();
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
                    paramInfo = showParam({paramData:interfaceBean.paramDetail});
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
                        var paramDetail = generateEditParam("#paramListDiv input", paramInfo);
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

                        var request = {
                            url : "${base}/v1/test/interface/param/save.json",
                            data : data,
                            handlerFunc : handlerParamSave,
                            isASync : true,
                            failTitle :"参数样例保存出错:",
                            env : $("#env").val(),
                            otherData : null,
                            guid : $("#guid").val()
                        };

                        commonAjaxRequest(request);
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

                var request = {
                    url : "${base}/v1/test/interface/param/delete.json",
                    data : data,
                    handlerFunc : refreshPage,
                    isASync : true,
                    failTitle :"参数删除出错:",
                    env : $("#env").val(),
                    otherData : null,
                    guid : $("#guid").val()
                };

                commonAjaxRequest(request);
            });

            $("#param_default").click(function(){
                var paramId = $("#paramChoose").val();

                var data = {
                    data : new Number(paramId)
                };

                var request = {
                    url : "${base}/v1/test/interface/param/default/set.json",
                    data : data,
                    handlerFunc : notOps,
                    isASync : true,
                    failTitle :"参数设置默认出错:",
                    env : $("#env").val(),
                    otherData : null,
                    guid : $("#guid").val()
                };

                commonAjaxRequest(request);
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