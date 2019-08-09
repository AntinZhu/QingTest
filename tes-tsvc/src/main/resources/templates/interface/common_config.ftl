<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>通用配置修改</title>
    <#include "/include/resource.ftl" />
    <link href="${base}/static/css/json/base.css" rel="stylesheet">
    <link href="${base}/static/css/json/jquery.numberedtextarea.css" rel="stylesheet">

    <script src="${base}/static/js/json/hm.js"></script>
    <script src="${base}/static/js/json/jquery.message.js"></script>
    <script src="${base}/static/js/json/jquery.json.js"></script>
    <script src="${base}/static/js/json/json2.js"></script>
    <script src="${base}/static/js/json/jsonlint.js"></script>
    <script src="${base}/static/js/json/jquery.numberedtextarea.js"></script>
    <script src="${base}/static/assets/js/jquery.maskedinput.min.js"></script>
    <script src="${base}/static/assets/js/bootstrap-tag.min.js"></script>
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
                        <div class="row">
                            <div class="col-xs-12 label label-lg label-light arrowed-in arrowed-right qing_input_tip center" style="color: #333;text-align: left;">
                                如果有经常需要使用的开关配置，可联系<b class="red"><a  href="#" onclick="feedback()">管理员</a></b>加入该页面
                            </div>
                            <div class="hr hr-dotted"></div>
                            <div class="hr hr-dotted"></div>

                            <div class="col-xs-12">
                                <#include "/include/env.ftl" />
                            </div>
                            <div class="hr hr-dotted"></div>
                            <div class="hr hr-dotted"></div>
                            <div class="col-xs-12 col-sm-12">
                                <!-- 分期支付配置 -->
                                <div class="col-xs-6 col-sm-12 widget-container-span qing_common_config ${config.configKey}">
                                    <div class="widget-box">
                                        <div class="widget-header header-color-orange">
                                            <h5 class="smaller">${config.configName}(${config.configKey})</h5>

                                            <div class="widget-toolbar no-border">
                                                <a href="#" data-action="collapse">
                                                    <i class="icon-chevron-up"></i>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="widget-body">
                                            <div class="widget-main padding-6">
                                                <input id="editBtnSwitch" type="hidden" value="1" />
                                                <input type="hidden" id="${config.configKey}" />
                                                <input type="hidden" id="${config.configKey}_notify" />
                                                <div id="${config.configKey}_div">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- /.row -->
                    </div><!-- /.page-header -->
                </div>
                </div>

            <#include "/include/righttool-sidebar.ftl" />

<script type="text/javascript">
    var param_info;

    var resizeChanged = false;

    function initDefault(){
        param_info = init_config('${config.defaultValue}');
    }

    function init_config(configValue){
        $("#" + '${config.configKey}').val(configValue);
        var paramsTemplate = '${config.paramTemplate}';

        return showParam({paramData:fillTemplate(paramsTemplate, configValue), htmlDiv:"${config.configKey}_div", "valueChangedNotifyId":"${config.configKey}_notify", "isEditStatus":false});
    }



    function fillTemplate(paramsTemplate, configValue){
        var params = JSON.parse(paramsTemplate);
        var configObj = JSON.parse(configValue);

        fillDefaultValueWithDefault(params, configObj);

        return JSON.stringify(params);
    }

    //输入框的值改变时触发
    $(document).on("change", "#${config.configKey}_notify",function(e){
        var result = generateJsonParam("#${config.configKey}_div input", param_info);

        setConfig("${config.configKey}", JSON.stringify(result));
    });

    window.onresize = function(){
        resizeChanged = qingResize(resizeChanged);
    }

    function setConfig(configKey, configValue){
        if(configKey == "" || configKey == null){
            return;
        }

        var obj = new Object();
        obj.configKey = configKey;
        obj.configValue = configValue;
        obj.configScope = "common";
        obj.operateUserId = 1;
        obj.operateUserType = "system";

        var data = {
            url : "/svc/api/pi/v1/test/common/config/reset.json",
            param: JSON.stringify(obj),
            userId:22367,
            userType : 'student'
        }

        var request = {
            url : "${base}/v1/common/pi.json",
            data : data,
            handlerFunc : initConfig,
            isASync : true,
            failTitle :"设置通用配置失败:",
            guid : "test-api-config",
            env : $("#env").val()
        };

        commonAjaxRequest(request);
    }

    function emptyM(){
    }

    $(document).ready(function(){
        initAll();

        resizeChanged = qingResize(resizeChanged);
    });

    function initAll(){
        initDefault();

        initConfig();
    }

    function initConfig(){
        var data = {
            url : "/svc/api/pi/v1/test/common/config/list.json",
            param:"",
            userId:22367,
            userType : 'student'
        }

        var request = {
            url : "${base}/v1/common/pi.json",
            data : data,
            handlerFunc : initResult,
            isASync : true,
            failTitle :"获取通用配置信息:",
            guid : "test-api-config",
            env : $("#env").val()
        };

        commonAjaxRequest(request);
    }

    function initResult(resu){
        for( var idx in resu.resultList){
            var config = resu.resultList[idx];

            if("${config.configKey}" == config.key) {
                param_info = init_config(config.value);
            }

            idx++;
        }

        $(".qing_param_edit").removeClass("hide");
    }

    $(".env").click(function(){
        $(".env.btn-primary").removeClass("btn-primary");
        $(this).addClass("btn-primary");
        $("#env").val($(this).val());

        initAll();
    });

    function feedback(){
        bootbox.prompt("输入你想加入的通用配置的ConfigKey", function(result) {
            if (result === null) {
//                $.gritter.add({
//                    title : "参数示例",
//                    text : "不反馈了？",
//                    class_name : 'gritter-error gritter-center'
//                });
                return;
            } else {
                var userIp = $("#qing_ip").text();
                var content = {
                    msgtype : "markdown",
                    markdown :{
                        content : "有用户请求在通用配置页面中加入新配置\n                >用户: <font color=\"comment\">" + userIp + "</font> \n                >config_key: <font color=\"comment\">" + result + "</font>"
                    }
                };

                var request = {
                    url : "${base}/v1/common/wx_notify.json?content=" + encodeURI(JSON.stringify(content)),
                    data : null,
                    handlerFunc : handlerParamSave,
                    isASync : true,
                    failTitle :"反馈出错："
                };

                commonAjaxRequest(request);
            }
        });
    }

    function handlerParamSave(){
        $.gritter.add({
            title : '提示:',
            text : "申请成功",
            class_name : 'gritter-info gritter-center'
        });
    }

    $(document).off("click", '.addInputBtn').on('click', '.addInputBtn',cloneInput);
    $(document).off("click", '.delInputBtn').on('click', '.delInputBtn',removeInput);

    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();
    });
</script>
    </div>
</body>
</html>