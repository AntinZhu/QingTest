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

                            <!-- 开关类 -->
                            <div class="col-xs-12 col-sm-6 widget-container-span qing_resize">
                                <div class="widget-box">
                                    <div class="widget-header header-color-blue">
                                        <h5 class="bigger lighter">
                                            <i class="icon-table"></i>
                                            开关类
                                        </h5>

                                        <div class="widget-toolbar widget-toolbar-light no-border">
                                            <select id="simple-colorpicker-1" class="hide">
                                                <option selected="" data-class="blue" value="#307ECC">#307ECC</option>
                                                <option data-class="blue2" value="#5090C1">#5090C1</option>
                                                <option data-class="blue3" value="#6379AA">#6379AA</option>
                                                <option data-class="green" value="#82AF6F">#82AF6F</option>
                                                <option data-class="green2" value="#2E8965">#2E8965</option>
                                                <option data-class="green3" value="#5FBC47">#5FBC47</option>
                                                <option data-class="red" value="#E2755F">#E2755F</option>
                                                <option data-class="red2" value="#E04141">#E04141</option>
                                                <option data-class="red3" value="#D15B47">#D15B47</option>
                                                <option data-class="orange" value="#FFC657">#FFC657</option>
                                                <option data-class="purple" value="#7E6EB0">#7E6EB0</option>
                                                <option data-class="pink" value="#CE6F9E">#CE6F9E</option>
                                                <option data-class="dark" value="#404040">#404040</option>
                                                <option data-class="grey" value="#848484">#848484</option>
                                                <option data-class="default" value="#EEE">#EEE</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main no-padding">
                                            <table class="table table-striped table-bordered table-hover">
                                                <thead class="thin-border-bottom">
                                                <tr>
                                                    <th>
                                                        <i class="icon-user"></i>
                                                        配置项
                                                    </th>

                                                    <th>
                                                        config_key
                                                    </th>

                                                    <th>
                                                        <i>@</i>
                                                        开关状态
                                                    </th>
                                                </tr>
                                                </thead>

                                                <tbody id="qing_config_switch_div">
                                                    <tr>
                                                        <td class="">银行卡校验开关</td>
                                                        <td class="">api_user_bank_card_validate_enable_switch_key</td>

                                                        <td>
                                                            <label class="pull-left inline qing_config_switch api_user_bank_card_validate_enable_switch_key"  title="开启会调本地接口" data-rel="tooltip" >
                                                                <input type="checkbox" id="is_selective_teacher" class="ace ace-switch ace-switch-5" value="0" />
                                                                <span class="lbl"></span>
                                                            </label>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- 严选课程承诺金 -->
                            <div class="col-xs-6 col-sm-3 widget-container-span qing_resize">
                                <div class="widget-box">
                                    <div class="widget-header">
                                        <h5 class="smaller">严选课程承诺金开关及比例</h5>
                                        <div class="hr hr-dotted"></div>
                                        <h5 class="smaller">(api_selective_order_promise_amount_config)</h5>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main padding-6">
                                            <input id="editBtnSwitch" type="hidden" value="1" />
                                            <input type="hidden" id="api_selective_order_promise_amount_config" />
                                            <input type="hidden" id="api_selective_order_promise_amount_config_notify" />
                                            <div id="api_selective_order_promise_amount_config_div">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- 老师授课时间设置 -->
                            <div class="col-xs-6 col-sm-3 widget-container-span qing_resize">
                                <div class="widget-box">
                                    <div class="widget-header">
                                        <h5 class="smaller">老师授课时间设置限制</h5>
                                        <div class="hr hr-dotted"></div>
                                        <h5 class="smaller">(api_teacher_time_config)</h5>
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main padding-6">
                                            <input type="hidden" id="api_teacher_time_config" />
                                            <input type="hidden" id="api_teacher_time_config_notify" />
                                            <div id="api_teacher_time_config_div">

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
    var switchTr = '<tr><td class="">{configName}</td><td class="">{configKey}</td> <td> <label class="pull-left inline {configKey} qing_config_switch"  title="开启会调本地接口" data-rel="tooltip" ><input type="checkbox" id="{configKey}" class="ace ace-switch ace-switch-5" value="{configValue}"  {selected}/><span class="lbl"></span></label></td> </tr>';
    var switchConfigs = [
        {configKey:'api_bi_month_data_remuneration_config', name: "小轻评级v2-月数据显示开关", defaultValue:true},
        {configKey:'api_bi_day_data_remuneration_config', name: "小轻评级v2-日数据显示开关", defaultValue:true},
        {configKey:'api_no_teaching_certificate_for_new_relation_enable_switch', name: "允许无教师资格证新关系下单或支付", defaultValue:true},
        {configKey:'api_sex_limit_for_new_relation_enable_switch', name: "允许（男老师女学生）新关系下单或支付", defaultValue:true},
        {configKey:'api_jd_pay_installment_enable_key', name: "京东白条显示开关", defaultValue:false},
        {configKey:'api_user_bank_card_validate_enable_switch_key', name: "银行卡校验开关", defaultValue:true},
    ];

    var api_selective_order_promise_amount_config_param_info;
    var api_teacher_time_config_param_info;
    var resizeChanged = false;

    function initDefault(){
        var fullSwitchTrs = "";
        // 初始化默认属性
        var defaultIdx = 0;
        while(defaultIdx < switchConfigs.length){
            var defaultConfig = switchConfigs[defaultIdx];
            var itemSwitchTr = switchTr;
            itemSwitchTr = itemSwitchTr.replace(new RegExp("{configName}","gm"), defaultConfig.name);
            itemSwitchTr = itemSwitchTr.replace(new RegExp("{configKey}","gm"), defaultConfig.configKey);
            itemSwitchTr = itemSwitchTr.replace(new RegExp("{configValue}","gm"), defaultConfig.defaultValue);
            if(defaultConfig.defaultValue){
                itemSwitchTr = itemSwitchTr.replace(new RegExp("{selected}","gm"), 'checked="checked"');
            }else{
                itemSwitchTr = itemSwitchTr.replace(new RegExp("{selected}","gm"), '');
            }
            fullSwitchTrs += itemSwitchTr;

            defaultIdx++;
        }
        $("#qing_config_switch_div").html(fullSwitchTrs);

        api_selective_order_promise_amount_config_param_info = init_api_selective_order_promise_amount_config('{"isEnable":false,"deductRate":0}');
        api_teacher_time_config_param_info = init_api_teacher_time_config('{"teachingHours":6, "weekendTeachingHours":2}');
    }

    function init_api_teacher_time_config(configValue){
        $("#api_teacher_time_config").val(configValue);
        var paramsTemplate = '[{"key":"teachingHours","name":"总时长","defaultValue":{"name":{teachingHoursDefaultValue},"value":{teachingHoursDefaultValue}}},{"key":"weekendTeachingHours","name":"周末时长","defaultValue":{"name":{weekendTeachingHoursDefaultValue},"value":{weekendTeachingHoursDefaultValue}}}]';
        var params = paramsTemplate;

        var configObj = JSON.parse(configValue);
        params = params.replace(new RegExp("{teachingHoursDefaultValue}","gm"), configObj.teachingHours);
        params = params.replace(new RegExp("{weekendTeachingHoursDefaultValue}","gm"), configObj.weekendTeachingHours);

        return showParam({paramData:params, htmlDiv:"api_teacher_time_config_div", "valueChangedNotifyId":"api_teacher_time_config_notify", "isEditStatus":false});
    }

    //输入框的值改变时触发
    $(document).on("change", "#api_selective_order_promise_amount_config_notify",function(e){
        var result = generateJsonParam("#api_selective_order_promise_amount_config_div input", api_selective_order_promise_amount_config_param_info);

        setConfig("api_selective_order_promise_amount_config", JSON.stringify(result));
    });

    //输入框的值改变时触发
    $(document).on("change", "#api_teacher_time_config_notify",function(e){
        var result = generateJsonParam("#api_teacher_time_config_div input", api_teacher_time_config_param_info);

        setConfig("api_teacher_time_config", JSON.stringify(result));
    });

    window.onresize = function(){
        resizeChanged = qingResize(resizeChanged);
    }

    function setConfig(configKey, configValue){
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

        commonAjaxRequest("${base}/v1/common/pi.json", data, emptyM, true, "设置通用配置失败:", $("#env").val(), null, "test-api-config");
    }

    function emptyM(){

    }

    function init_api_selective_order_promise_amount_config(configValue){
        $("#api_selective_order_promise_amount_config").val(configValue);
        var paramsTemplate = '[{"key":"isEnable","name":"是否开启","selectable":[{"name":"否","value":false},{"name":"是","value":true}],"class":"switch_editable","defaultValue":{enableDefaultValue}},{"key":"deductRate","name":"比例","defaultValue":{"name":{rateDefaultValue},"value":{rateDefaultValue}}}]';
        var params = paramsTemplate;

        var configObj = JSON.parse(configValue);
        if(configObj.isEnable){
            params = params.replace(new RegExp("{enableDefaultValue}","gm"), '{"name":"是","value":true}');
        }else{
            params = params.replace(new RegExp("{enableDefaultValue}","gm"), '{"name":"否","value":false}');
        }
        if(configObj.deductRate != null){
            params = params.replace(new RegExp("{rateDefaultValue}","gm"), configObj.deductRate);
        }else{
            params = params.replace(new RegExp("{rateDefaultValue}","gm"), 0);
        }

        return showParam({paramData:params, htmlDiv:"api_selective_order_promise_amount_config_div", "valueChangedNotifyId":"api_selective_order_promise_amount_config_notify", "isEditStatus":false});
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

        commonAjaxRequest("${base}/v1/common/pi.json", data, initResult, true, "获取通用配置信息:", $("#env").val(), null, $("#guid").val());
    }

    function initResult(resu){
        for( var idx in resu.resultList){
            var config = resu.resultList[idx];

            var eleInput = $("input[id='" + config.key + "']");
            if(eleInput.length == 0){
                continue;
            }
            var ele = $(".qing_config_switch." + config.key).children("input").first();
            if(ele.length > 0){
                $(ele).val(config.value);
                if(config.value == "true"){
                    $(ele).attr("checked", "checked");
                }else{
                    $(ele).removeAttr("checked");
                }
            }

            if("api_selective_order_promise_amount_config" == config.key){
                api_selective_order_promise_amount_config_param_info = init_api_selective_order_promise_amount_config(config.value);
            }
            if("api_teacher_time_config" == config.key){
                api_teacher_time_config_param_info = init_api_teacher_time_config(config.value);
            }
        }
    }

    $(document).on("click", ".qing_config_switch", function(){
        var inputEle = $(this).children("input").first();
        var switchValue = $(inputEle).val();
        var newValue = (switchValue != "true") ;
        $(inputEle).val(newValue);

        setConfig($(inputEle).attr("id"), newValue);
    });

    $(".use_live_tool").click(function(){
        var newValue = $(this).val();
        $(".use_live_tool.btn-primary").removeClass("btn-primary");
        $(this).addClass("btn-primary");
        $("#use_live_tool").val(newValue);
    });

    $(".teacher_limited_site_types").click(function() {
        if($(this).attr("sel") == 0){
            $(this).attr("sel", 1);
            $(this).addClass("btn-primary");
        }else{
            $(this).attr("sel", 0);
            $(this).removeClass("btn-primary");
        }
        var finalValue = 0;
        var eles = $(".teacher_limited_site_types.btn-primary");
        if(eles){
            var idx = 0;
            while(idx < eles.length){
                finalValue = finalValue + new Number($(eles[idx++]).val());
            }
        }
    });

    $('#teacher_max_teachable_student_count').change(function(){
    });

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

                commonAjaxRequest("${base}/v1/common/wx_notify.json?content=" + encodeURI(JSON.stringify(content)), null, handlerParamSave, true, "反馈出错:");
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

    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();
        $('#teacher_max_teachable_student_count').ace_spinner({value:0,min:0,max:1000,step:1, on_sides: true, icon_up:'icon-plus smaller-75', icon_down:'icon-minus smaller-75', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});
    });
</script>
    </div>
</body>
</html>