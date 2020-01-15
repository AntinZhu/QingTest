<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title id = "qing_title">Hello World!</title>
    <#include "/include/resource.ftl" />

    <style>
        .spinner-preview {
            width:100px;
            height:100px;
            text-align:center;
            margin-top:60px;
        }

        .dropdown-preview {
            margin:0 5px;
            display:inline-block;
        }
        .dropdown-preview  > .dropdown-menu {
            display: block;
            position: static;
            margin-bottom: 5px;
        }

        .json_key{ color: #92278f;font-weight:bold;}
        .json_null{color: #f1592a;font-weight:bold;}
        .json_string{ color: #3ab54a;font-weight:bold;}
        .json_number{ color: #25aae2;font-weight:bold;}
        .json_boolean{ color: #f98280;font-weight:bold;}
        .json_link{ color: #61D2D6;font-weight:bold;}
        .json_array_brackets{}

        .next-line{
            display:block;
        }
    </style>

    <link href="${base}/static/css/json/base.css" rel="stylesheet">
    <link href="${base}/static/css/json/jquery.numberedtextarea.css" rel="stylesheet">

    <script src="${base}/static/js/json/clipboard.min.js"></script>
    <script src="${base}/static/js/json/jquery.message.js"></script>
    <script src="${base}/static/js/json/jquery.json.js"></script>
    <script src="${base}/static/js/json/json2.js"></script>
    <script src="${base}/static/js/json/jsonlint.js"></script>
    <script src="${base}/static/js/json/jquery.numberedtextarea.js"></script>
    <script src="${base}/static/js/param.js"></script>
    <script src="${base}/static/js/paramResult.js"></script>

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
                    <main class="row-fluid" style="height:100%;min-height:550px;">
                        <div id="timeline-1">
                            <div class="row">
                                <div class="col-xs-12 col-sm-10 col-sm-offset-1">
                                    <div class="timeline-container">
                                        <div class="timeline-label">
													<span class="label label-primary arrowed-in-right label-lg">
														<b>Today</b>
													</span>
                                        </div>

                                        <div class="timeline-items">
                                            <div id="faq-list-1" class="panel-group accordion-style1 accordion-style2">
                                                <!-- 请求的接口地址 -->
                                                <div class="timeline-item clearfix">
                                                    <div class="timeline-info">
                                                        <a id="editInterface" href="#" target="_blank">
                                                            <i class="timeline-indicator icon-beaker btn btn-default no-hover"></i>
                                                        </a>
                                                    </div>

                                                    <div class="widget-box transparent">
                                                        <div class="widget-header hidden"></div>

                                                        <div class="widget-body">
                                                            <input type="hidden" id="guid" >
                                                            <a target="_blank" title="点击链接可查看调用日志" data-rel="tooltip" id = "logUrl" href="">
                                                                <div class="widget-main" id="interfaceUrl"> Took the final exam. Phew! </div>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>

                                                <!-- 请求的接口地址 -->
                                                <div class="timeline-item clearfix qing_catelog_hide" id="shareUrlDiv">
                                                    <div class="timeline-info">
                                                        <i class="timeline-indicator icon-beaker btn btn-default no-hover"></i>
                                                    </div>

                                                    <div class="widget-box transparent">
                                                        <div class="widget-header hidden"></div>

                                                        <div class="widget-body">
                                                            <div class="widget-main">
                                                                <div id="shareUrl" style="float: left">Took the final exam. Phew!</div>
                                                                <button class="btn btn-link btn-sm copy" data-clipboard-target="#shareUrl">
                                                                    <i class="icon-share-alt biggr-100"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <!-- 接口参数 -->
                                                <div class="timeline-item clearfix">
                                                    <div class="timeline-info">
                                                        <img alt="Susan't Avatar" src="${base}/static/assets/avatars/avatar1.png" />
                                                        <span class="label label-info label-sm" id="qingTime">16:22</span>
                                                    </div>

                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                            <div href="#faq-0-1"  class="accordion-toggle">
                                                                <h1>
                                                                    接口测试
                                                                    <small>
                                                                        <i class="icon-double-angle-right"></i>
                                                                        <label id = "interfaceNameDiv">Common form elements and layouts</label>
                                                                    </small>
                                                                </h1>
                                                            </div>
                                                        </div>

                                                        <div id="faq-0-1">
                                                            <div id="accordion" class="accordion-style2">
                                                                <div class="group">
                                                                    <h3 id="param-step-1" class="accordion-header">接口参数选择</h3>

                                                                    <div>
                                                                        <div class="col-xs-12">
                                                                            <div class="form-group qing_catelog_hide">
                                                                                <label class="col-sm-3 control-label no-padding-right" for="requestUrl">地址:</label>

                                                                                <div class="col-sm-9">
                                                                                    <div class="clearfix">
                                                                                        <input class="col-xs-10" type="text" id="requestUrl" placeholder="修改地址..." />
                                                                                    </div>

                                                                                    <div class="space-2"></div>
                                                                                </div>
                                                                            </div>

                                                                            <#include "/include/param.ftl" />

                                                                            <div class="clearfix form-actions">
                                                                                <div class="col-md-offset-3 col-md-9">
                                                                                    <button class="btn btn-info" style="border-radius: 8px" type="button" id = "teacherIdBtn">
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
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="group">
                                                                <h3  id="param-step-2" class="accordion-header">请求参数</h3>
                                                                <div>
                                                                    <div class="col-md-12" style="padding:0;position:relative;height:100%;">
                                                                        <div style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                                            <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                                            <div class="ro" id="json-request" style="padding:0px 25px;white-space: pre-line;">
                                                                                <span data-type="object"><i style="cursor:pointer;" class="fa icon-minus" onclick="hide(this)"></i>{<br><br>}</span></div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                                <div class="group">
                                                                    <h3 id="param-step-3" class="accordion-header">返回值</h3>

                                                                    <div>
                                                                        <div class="col-md-12" style="padding:0;position:relative;height:100%;">
                                                                            <div id="json-response-div" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                                                <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                                                <div class="ro" id="json-response" style="padding:0px 25px;white-space: pre-line;">
                                                                                    <span data-type="object"><i style="cursor:pointer;" class="fa icon-minus" onclick="hide(this)"></i>{<br><br>}</span></div>
                                                                            </div>

                                                                            <div id="dataResultDiv" class="hide">
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div><!-- #accordion -->
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--接口的相关配置 -->
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
                                                                <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                                    <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                                    <div class="ro" id="json-interface" style="padding:0px 25px;white-space: pre-line;">
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
                                                                <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                                    <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                                    <div class="ro" id="json-interface-detail" style="padding:0px 25px;white-space: pre-line;">
                                                                        <span data-type="object"><i style="cursor:pointer;" class="fa icon-minus" onclick="hide(this)"></i>{<br><br>}</span></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div><!-- /.timeline-items -->
                                    </div><!-- /.timeline-container -->
                                </div>
                            </div>
                        </div>

                        <br style="clear:both;">
                    </main>
                </div>
            </div>
            <#include "/include/righttool-sidebar.ftl" />
        </div>

        <script type="text/javascript">
            $('textarea').numberedtextarea();
            var headerHtmlTemplate = '<label>\
                    <input name="form-field-checkbox" id="header_enable_{idx}" value="{header_enable_value}" class="ace ace-checkbox-2 qing_header_enable" type="checkbox" {header_checked}>\
                    <span class="lbl">\
                    <input type="text" id="header_key_{idx}" value="{header_key_value}" >\
                    =\
                    <input type="text" id="header_value_{idx}" value="{header_value_value}" >\
                    <a class="blue addHeaderBtn" href="###"><i class="icon-plus bigger-130"></i></a>\
                    </span>\
                    </label><br />';

            var paramInfo;
            var isCross = ${cross!1};
            var catelogIdx;
            $(document).ready(function(){
                refreshPage();

                $("#editInterface").attr("href", "http://" + location.host + "${base}/v1/test/interface/edit?catelogIndex=3-1&id=${interfaceId}");
            });

            function refreshPage(){
                $("#qing_local_switch_div").removeClass("hide");

                var data = {
                    data : ${interfaceId}
                };
                var request = {
                    url : "${base}/v1/test/interface.json",
                    data : data,
                    handlerFunc : handlerInterface,
                    isASync : true,
                    failTitle :"获取接口信息失败:"
                };

                commonAjaxRequest(request);
            }

            var logUrl = "http://172.22.12.14:5601/app/logtrail#/?q=env_type:%20%22{env}%22%20%26%26%20guid:%20%22{guid}%22&t=Now&i=rsyslog-app*&_g=()&h={server}";
            function refreshInterfaceUrl(){
                var env = $("#env").val();
                var guid = generateGuid();

                var url = interfaceUrlPrefix.replace("{env}", env);
                url = url.replace("{guid}", guid);

                var logTargetUrl = logUrl.replace("{env}", env);
                logTargetUrl = logTargetUrl.replace("{guid}", guid);
                logTargetUrl = logTargetUrl.replace("{server}", getServer(interfaceBean.interfaceUrl))

                $("#interfaceUrl").text(url);
                $("#guid").val(guid);
                $("#logUrl").attr("href", logTargetUrl);
            }

            function getServer(url){
                if(url.indexOf("/") == 0){
                    url = url.substr(1, url.length);
                }

                url = url.substr(0, url.indexOf("/"));

                return url;
            }

            var gatewayUrl = "http://gateway.{env}.idc.cedu.cn";
            var interfaceUrlPrefix;
            var paramExamples;
            var interfaceBean;
            var userTypeArr = [];
            $.each(["student", "teacher", "assistant"] , function(k, v){
                userTypeArr.push({id: v, text: v});
            });
            function handlerInterface(resu){
                jsonShow(resu, "json-interface");
                interfaceUrlPrefix = gatewayUrl + resu.interfaceInfo.inter.interfaceUrl + "?guid={guid}";
                $("#interfaceId").val(resu.interfaceInfo.inter.id);
                interfaceBean = resu.interfaceInfo.inter;
                $("#interfaceNameDiv").text(resu.interfaceInfo.inter.interfaceName);
                $("#qing_title").text(resu.interfaceInfo.inter.interfaceName);
                if(resu.interfaceInfo.inter.interfaceType == "PT" || resu.interfaceInfo.inter.interfaceType == "PI"){
                    $("#requestUserIdDev").removeClass("hide");
                    $("#requestUserTypeDiv").text(resu.interfaceInfo.inter.requestUserType);
                    $("#requestUserType").val(resu.interfaceInfo.inter.requestUserType);

                    $('#requestUserTypeDiv').editable({
                        type: 'select2',
                        value : resu.interfaceInfo.inter.requestUserType,
                        source: userTypeArr,
                        success: function(response, newValue) {
                            $(this).prev("input").val(newValue);
                        }
                    });
                }

                $("#requestUrl").val(resu.interfaceInfo.inter.interfaceUrl);

                if(!isStringEmpty(resu.interfaceInfo.inter.requestHeaders)){
                    var headers = JSON.parse(resu.interfaceInfo.inter.requestHeaders);
                    var headerIdx = 0;
                    var headerLength = headers.length;
                    var allHeaderHtml = "";
                    while(headerIdx < headerLength){
                        var header = headers[headerIdx];

                        var headerHtml = headerHtmlTemplate.replace(new RegExp("{idx}","gm"), headerIdx + 1);
                        headerHtml = headerHtml.replace(new RegExp("{header_checked}","gm"), header.enable? 'checked="checked"':'');
                        headerHtml = headerHtml.replace(new RegExp("{header_enable_value}","gm"), header.enable? 1:0);
                        headerHtml = headerHtml.replace(new RegExp("{header_key_value}","gm"), header.key);
                        headerHtml = headerHtml.replace(new RegExp("{header_value_value}","gm"), header.value);

                        headerIdx++;
                        allHeaderHtml += headerHtml;
                    }
                    $("#requestHeaders").attr("_idx", headerLength);
                    $("#requestHeaders").html(allHeaderHtml);
                    $("#requestHeadersDiv").removeClass("hide");
                }

                $("#selfParamSwitch").val(${full!0});

                if(resu.interfaceInfo.inter.paramDetail != null && resu.interfaceInfo.inter.paramDetail != ""){
                    jsonShow(resu.interfaceInfo.inter.paramDetail, "json-interface-detail");
                    var paramDetail = fillDefaultValue(JSON.parse(resu.interfaceInfo.inter.paramDetail));
                    paramInfo = showParam({paramData:paramDetail});
                }

                if(resu.interfaceInfo.catelog){
                    catelogIdx = resu.interfaceInfo.catelog.cacheCatelogIndex;
                }

                if('${userType!""}' != ""){
                    $("#requestUserTypeDiv").text('${userType!"ta"}');
                    $("#requestUserType").val('${userType!"ta"}');
                }

                paramExamples = resu.interfaceInfo.paramList;
                initParamChoose(paramExamples, ${paramExampleId});
                if(${paramExampleId} == 0){
                    fillFullParam();
                }

                var env = '${env!"dev"}';
                $("#env").val(env);
                $(".env.btn-primary").removeClass("btn-primary");
                $(".env[value='" + env + "']").addClass("btn-primary");

                refreshInterfaceUrl();

                if(${inv!0} == 1){
                    invoke();
                }
            }

            function fillDefaultValue(paramArr){
                var defaultObj = new Object(${defaultObj!"{}"});
               fillDefaultValueWithDefault(paramArr, defaultObj);

                return JSON.stringify(paramArr);
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
                    if(paramExampleId == -1 && data.default){
                        paramEx = data;
                        paramExampleId = data.id;
                    }else if(data.id == paramExampleId){
                        paramEx = data;
                    }

                    options[optionIdx++] = option;
                }
                updateOptions("paramChoose", options, paramExampleId);
                if(paramEx != null){
                    $("#requestUserId").val(paramEx.requestUserId);
                    $("#requestUserIdDiv").text(paramEx.requestUserId);
                    if(paramEx.paramDetail != "[]"){
                        paramInfo = showParam({paramData:paramEx.paramDetail});
                    }
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
                    $("#selfParamSwitch").attr("checked", "checked");
                    showFull();
                }
            }

            function fillFullParam(){
                var param = generateJsonParam("#paramListDiv input", paramInfo);
                $("#fullParam").text(JSON.stringify(param));
            }

            $(document).off("click", '.addInputBtn').on('click', '.addInputBtn',cloneInput);
            $(document).off("click", '.delInputBtn').on('click', '.delInputBtn',removeInput);
            $(document).off("click", '.addHeaderBtn').on('click', '.addHeaderBtn',function () {
                var newHeaderHtml = headerHtmlTemplate;
                var idx = 1 + new Number($("#requestHeaders").attr("_idx"));
                $("#requestHeaders").attr("_idx", idx);

                newHeaderHtml = newHeaderHtml.replace(new RegExp("{idx}","gm"), idx);
                newHeaderHtml = newHeaderHtml.replace(new RegExp("{header_checked}","gm"), 'checked="checked"');
                newHeaderHtml = newHeaderHtml.replace(new RegExp("{header_enable_value}","gm"), 1);
                newHeaderHtml = newHeaderHtml.replace(new RegExp("{header_key_value}","gm"), "");
                newHeaderHtml = newHeaderHtml.replace(new RegExp("{header_value_value}","gm"), "");

                $("#requestHeaders").append(newHeaderHtml);
            });
            $(document).off("click", '.qing_header_enable').on('click', '.qing_header_enable',function () {
                var nowValue = $(this).val();
                if(nowValue == 1){
                    $(this).val(0);
                }else{
                    $(this).val(1);
                }
            });

            function invoke () {
                refreshInterfaceUrl();
                var param;
                try{
                    param  = getParam();
                }catch(err){
                    $.gritter.add({
                        title : '参数提醒:',
                        text : '参数格式错误，请检查json格式',
                        class_name : 'gritter-error gritter-center'
                    });
                    var jsonParam = "";
                    if($("#selfParamSwitch").val() == 1){
                        jsonParam = $("#fullParam").text();
                    }
                    jsonShow(jsonParam, "json-request");
                    return;
                }

                jsonShow(param, "json-request");
                jsonShow("[]", "json-response");

                var isLocalDebug = $("#isLocalDebug").val();
                if(isLocalDebug != 1){
                    invokeServer(param);
                }else{
                    invokeLocal(param);
                }

                $("#param-step-3").trigger("click");
            }

            function getParam(){
                var param;
                if($("#selfParamSwitch").val() == 1){
                    param = JSON.parse($("#fullParam").text());
                }else{
                    param = generateJsonParam("#paramListDiv input", paramInfo);
                }

                if(JSON.stringify(param) == "{}"){
                    return null;
                }else{
                    return param;
                }
            }

            function getHeaders(){
                var headers = [];

                var headerLen = new Number($("#requestHeaders").attr("_idx"));
                var headerIdx = 1;
                while(headerIdx <= headerLen){
                    var enable = $("#header_enable_" + headerIdx);
                    if($(enable).val() == 1){
                        var header = {};
                        header.key =  $("#header_key_" + headerIdx).val();
                        header.value =  $("#header_value_" + headerIdx).val();

                        headers.push(header);
                    }
                    headerIdx++;
                }

                return headers;
            }

            function invokeServer(param){
                var data = {
                    requestUrl : $("#requestUrl").val(),
                    interfaceId : $("#interfaceId").val(),
                    requestUserId : $("#requestUserId").val(),
                    requestUserType : $("#requestUserType").val(),
                    param : param == null? "":JSON.stringify(param),
                    headers : getHeaders()
                };

                var request = {
                    url : "${base}/v1/test/interface/invoke.json",
                    data : data,
                    handlerFunc : handlerInvokeResult,
                    isASync : true,
                    failTitle :"接口调用异常:",
                    env :$("#env").val(),
                    guid : $("#guid").val()
                };

                commonAjaxRequest(request);
            }

            function invokeLocal(param){
                if(interfaceBean.interfaceType == "PT" || interfaceBean.interfaceType == "PI"){
                    var user = {
                        user_id :  new Number($("#requestUserId").val()),
                        user_type  : $("#requestUserType").val()
                    };

                    var othData = {
                        param : param
                    };

                    var request = {
                        url : "${base}/v1/test/user/token.json",
                        data : user,
                        handlerFunc : handlerFilterFillInvoke,
                        isASync : true,
                        failTitle :"接口调用异常:",
                        env :$("#env").val(),
                        guid : $("#guid").val(),
                        otherData : othData
                    };

                    commonAjaxRequest(request);
                }else{
                    var headers= {};
                    fillHeaders(headers);
                    handlerLocalInvoke(param, headers);
                }
            }

            function handlerFilterFillInvoke(data, otherData){
                var param = otherData.param;
                var token = data.resultList;
                var headers = {
                    si : token.session,
                    tk : token.token,
                    Timestamp : token.timestamp,
                    Authkey : token.authkey,
                    qingqing_debug_mode : 'true',
                    QingqingUser : token.qingqingUserId
                };
                fillHeaders(headers);

                handlerLocalInvoke(param, headers);
            }

            function fillHeaders(headers){
                var assignHeaders = getHeaders();
                var idx = 0;
                while(idx < assignHeaders.length){
                    headers[assignHeaders[idx].key] = assignHeaders[idx].value;
                    idx++;
                }
            }

            function handlerLocalInvoke(param, headers){
                var localPort = $("#localDebugPort").val();
                var url = "http://127.0.0.1:" + localPort + $("#requestUrl").val() + "?guid=" + $("#guid").val();

                if(isCross == 1){
                    var data = {
                        url : url,
                        headers : headers,
                        params : param == null? "":JSON.stringify(param),
                        requestType : interfaceBean.requestType
                    };

                    var request = {
                        url : "http://127.0.0.1:8009/app/cross",
                        data : data,
                        handlerFunc : handlerLocalInvokeResult,
                        isASync : true,
                        failTitle :"接口调用异常:",
                        env :$("#env").val(),
                        guid : $("#guid").val()
                    };

                    commonAjaxRequest(request);
                }else{
                    var request = {
                        url : "http://127.0.0.1:" + localPort + interfaceBean.interfaceUrl,
                        data : param,
                        handlerFunc : handlerLocalInvokeResult,
                        isASync : true,
                        failTitle :"接口调用异常:",
                        env :$("#env").val(),
                        guid : $("#guid").val(),
                        headers :headers
                    };

                    commonAjaxRequest(request);
                }
            }

            function handlerInvokeResult(resu){
            <#if ((dataResultFunction)!'') == ''>
                jsonShow(resu.data, "json-response");
                afterInv(resu.data);
            <#else>
                jsonShow(resu.data, "json-response");
                $("#json-response-div").addClass("hide");
                $("#dataResultDiv").removeClass("hide");
                ${dataResultFunction}(resu)
            </#if>
            };

            function handlerLocalInvokeResult(resu){
                jsonShow(resu, "json-response");

                afterInv(resu);
            };

            $("#qing_param_copy").click(function(){
                // 复制到粘贴板
                copyToClipboard(location.href);
            });

            function afterInv(responseData){
                var goToNextPage = ${goToNextPage!0};
                responseData = JSON.parse(responseData)
                if(responseData.response.error_code == 0){
                    if(goToNextPage == "1" && interfaceBean.nextPageUrl != null && interfaceBean.nextPageUrl != ""){
                        var naxtPageUrl = interfaceBean.nextPageUrl;
                        naxtPageUrl = naxtPageUrl.replace("{env}", $("#env").val());
                        naxtPageUrl = naxtPageUrl.replace("{userId}", new Number($("#requestUserId").val()));
                        naxtPageUrl = naxtPageUrl.replace("{userType}", $("#requestUserType").val());
                        naxtPageUrl = qingReplaceStringWithObj(naxtPageUrl, responseData);

                        <#--window.loca("${base}" + naxtPageUrl);-->

                        window.location.href= "${base}" + naxtPageUrl;
                    }
                }
            }

            $("#paramChoose").change(function(){
                var id = $(this).val();
                if(id != 0){
                    for(idx in paramExamples){
                        var paramEx = paramExamples[idx];
                        if(paramEx.id == id){
                            if(paramEx.paramDetail != "[]"){
                                paramInfo = showParam({paramData:paramEx.paramDetail});
                            }else{
                                $("#paramDiv").addClass("hide");
                            }
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
                    if(interfaceBean.paramDetail){
                        paramInfo = showParam({paramData:interfaceBean.paramDetail});
                    }else{
                        $("#paramDiv").addClass("hide");
                    }
                    $(".param-ops").addClass("hide");
                }

                refreshShareUrl();
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
                    failTitle :"参数设置默认出错:"
                };

                commonAjaxRequest(request);
            });

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
                    failTitle :"参数删除出错:"
                };

                commonAjaxRequest(request);
            });

            function refreshShareUrl(){
                if($("#paramChoose").val() > 0){
                    $("#shareUrl").text("http://" + location.host + location.pathname + "?inv=1&id=${interfaceId}&paramId=" + $("#paramChoose").val() + "&catelogIndex=" + catelogIdx + "&env=" + $("#env").val());
                }
            }

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
                            interfaceId : $("#interfaceId").val(),
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
                            failTitle :"参数样例保存出错:"
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

            $(".env").click(function(){
                $(".env.btn-primary").removeClass("btn-primary");
                $(this).addClass("btn-primary");
                $("#env").val($(this).val());

                refreshInterfaceUrl();
                refreshShareUrl();
            });

            $('#teacherIdBtn').click(invoke);

            $('.copy').click(function(){
                //$.msg("成功复制到粘贴板","color:#00D69C;");
                // $(this).tooltip('toggle')
                //       .attr('data-original-title', "复制成功！")
                //       .tooltip('fixTitle')
                //       .tooltip('toggle');
            });
            var clipboard = new Clipboard('.copy');

            jQuery(function($) {
                setInterval(refreshTime, 1000);

                function refreshTime(){
                    var now = new Date();
                    $("#qingTime").text(now.format("HH:mm"));
                }

                //editables on first profile page
                $.fn.editable.defaults.mode = 'inline';
                $.fn.editableform.loading = "<div class='editableform-loading'><i class='light-blue icon-2x icon-spinner icon-spin'></i></div>";
                $.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="icon-ok icon-white"></i></button>'+
                        '<button type="button" class="btn editable-cancel"><i class="icon-remove"></i></button>';

                $("#requestUserIdDiv").editable({
                    type: 'text',
                    name: 'username',
                    success: function(response, newValue) {
                        $(this).prev("input").val(newValue);
                    }
                });

                //editables
                $('#username').editable({
                    type: 'text',
                    name: 'username'
                });

                $('.date_editable').editable({
                    type: 'date',
                    format: 'yyyy-mm-dd',
                    viewformat: 'dd/mm/yyyy',
                    datepicker: {
                        weekStart: 1
                    }
                });

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

                $(".chosen-select").chosen();
                $("#requestUserType_chosen").css("width", "100px");

                $('[data-rel=tooltip]').tooltip();
            });
        </script>
    </div>
</body>
</html>