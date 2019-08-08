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
                            <div class="col-xs-12 col-sm-6 qing_resize">
                                <div class="form-group">
                                    <label style="text-align: right;" class="control-label col-xs-12 col-sm-3 no-padding-right" for="teacherIdIpt">ConfigKey:</label>

                                    <div class="col-xs-12 col-sm-9">
                                        <div class="clearfix">
                                            <select class="width-100 chosen-select form-control" id="configKeyChoose">
                                                <option value="0">没得选</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" style="text-align: right" for="fullData">ConfigValue：</label>

                                    <div class="col-sm-9">
                                        <textarea id="configValueParam" style="height: 250px" class="autosize-transition form-control"></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" style="text-align: right" for="fullData"></label>

                                    <div class="col-sm-9">
                                        <button class="btn btn-info" type="button" id="searchBtn">
                                            <i class="icon-ok bigger-110"></i>
                                            更新
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12">
                                <!-- 开关类 -->
                                <div class="col-xs-12 col-sm-12 widget-container-span qing_config_switch qing_common_config hide">
                                <#if configKey == "qing_config_switch">
                                    <div class="widget-box">
                                <#else >
                                    <div class="widget-box collapsed">
                                </#if>
                                        <div class="widget-header header-color-blue">
                                            <h5 class="bigger lighter">
                                                <i class="icon-table"></i>
                                                开关类
                                            </h5>

                                            <div class="widget-toolbar no-border">
                                                <a href="#" data-action="collapse">
                                                <#if configKey == "qing_config_switch">
                                                    <i class="icon-chevron-up"></i>
                                                <#else >
                                                    <i class="icon-chevron-down"></i>
                                                </#if>
                                                </a>
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

                                <!-- 分期支付配置 -->
                                <div class="col-xs-6 col-sm-12 widget-container-span qing_common_config installment_pay_config_v2 hide">
                                <#if configKey == "installment_pay_config_v2">
                                    <div class="widget-box">
                                <#else >
                                    <div class="widget-box collapsed">
                                </#if>
                                        <div class="widget-header header-color-orange">
                                            <h5 class="smaller">分期支付方式配置(installment_pay_config_v2)</h5>

                                            <div class="widget-toolbar no-border">
                                                <a href="#" data-action="collapse">
                                                <#if configKey == "installment_pay_config_v2">
                                                    <i class="icon-chevron-up"></i>
                                                <#else >
                                                    <i class="icon-chevron-down"></i>
                                                </#if>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="widget-body">
                                            <div class="widget-main padding-6">
                                                <input type="hidden" id="installment_pay_config_v2" />
                                                <input type="hidden" id="installment_pay_config_v2_notify" />
                                                <div id="installment_pay_config_v2_div">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xs-12 col-sm-6 qing_resize">
                                <!-- 严选课程承诺金 -->
                                <div class="col-xs-6 col-sm-6 widget-container-span qing_common_config api_selective_order_promise_amount_config hide">
                                    <#if configKey == "api_selective_order_promise_amount_config">
                                        <div class="widget-box">
                                    <#else >
                                        <div class="widget-box collapsed">
                                    </#if>
                                        <div class="widget-header header-color-pink">
                                            <h5 class="smaller">严选课程承诺金开关及比例</h5>
                                            <div class="hr hr-dotted"></div>
                                            <h5 class="smaller">(api_selective_order_promise_amount_config)</h5>

                                            <div class="widget-toolbar no-border">
                                                <a href="#" data-action="collapse">
                                                <#if configKey == "api_selective_order_promise_amount_config">
                                                    <i class="icon-chevron-up"></i>
                                                <#else >
                                                    <i class="icon-chevron-down"></i>
                                                </#if>
                                                </a>
                                            </div>
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
                                <div class="col-xs-6 col-sm-6 widget-container-span qing_common_config api_teacher_time_config hide">
                                <#if configKey == "api_teacher_time_config">
                                    <div class="widget-box">
                                <#else >
                                    <div class="widget-box collapsed">
                                </#if>
                                        <div class="widget-header header-color-pink">
                                            <h5 class="smaller">老师授课时间设置限制</h5>
                                            <div class="hr hr-dotted"></div>
                                            <h5 class="smaller">(api_teacher_time_config)</h5>

                                            <div class="widget-toolbar no-border">
                                                <a href="#" data-action="collapse">
                                                <#if configKey == "api_teacher_time_config">
                                                    <i class="icon-chevron-up"></i>
                                                <#else >
                                                    <i class="icon-chevron-down"></i>
                                                </#if>
                                                </a>
                                            </div>
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

                                <!-- 月度排课任务设置 -->
                                <div class="col-xs-6 col-sm-12 widget-container-span qing_common_config api_moon_arrange_course_config hide">
                                <#if configKey == "api_moon_arrange_course_config">
                                    <div class="widget-box">
                                <#else >
                                    <div class="widget-box collapsed">
                                </#if>
                                        <div class="widget-header header-color-pink">
                                            <h5 class="smaller">月度排课任务设置(api_moon_arrange_course_config)</h5>

                                            <div class="widget-toolbar no-border">
                                                <a href="#" data-action="collapse">
                                                <#if configKey == "api_moon_arrange_course_config">
                                                    <i class="icon-chevron-up"></i>
                                                <#else >
                                                    <i class="icon-chevron-down"></i>
                                                </#if>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="widget-body">
                                            <div class="widget-main padding-6">
                                                <input type="hidden" id="api_moon_arrange_course_config" />
                                                <input type="hidden" id="api_moon_arrange_course_config_notify" />
                                                <div id="api_moon_arrange_course_config_div">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- 调课约束设置 -->
                                <div class="col-xs-6 col-sm-12 widget-container-span qing_common_config change_course_constraint_config hide">
                                <#if configKey == "change_course_constraint_config">
                                    <div class="widget-box">
                                <#else >
                                    <div class="widget-box collapsed">
                                </#if>
                                        <div class="widget-header header-color-pink">
                                            <h5 class="smaller">调课约束设置(change_course_constraint_config)</h5>

                                            <div class="widget-toolbar no-border">
                                                <a href="#" data-action="collapse">
                                                <#if configKey == "change_course_constraint_config">
                                                    <i class="icon-chevron-up"></i>
                                                <#else >
                                                    <i class="icon-chevron-down"></i>
                                                </#if>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="widget-body">
                                            <div class="widget-main padding-6">
                                                <input type="hidden" id="change_course_constraint_config" />
                                                <input type="hidden" id="change_course_constraint_config_notify" />
                                                <div id="change_course_constraint_config_div">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </div>

                                    <!-- 首课沟通设置 -->
                                    <div class="col-xs-6 col-sm-12 widget-container-span qing_common_config service_standard_config_v2 hide">
                                    <#if configKey == "service_standard_config_v2">
                                    <div class="widget-box">
                                    <#else >
                                    <div class="widget-box collapsed">
                                    </#if>
                                        <div class="widget-header header-color-pink">
                                            <h5 class="smaller">首课沟通配置(service_standard_config_v2)</h5>

                                            <div class="widget-toolbar no-border">
                                                <a href="#" data-action="collapse">
                                                <#if configKey == "service_standard_config_v2">
                                                    <i class="icon-chevron-up"></i>
                                                <#else >
                                                    <i class="icon-chevron-down"></i>
                                                </#if>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="widget-body">
                                            <div class="widget-main padding-6">
                                                <input type="hidden" id="service_standard_config_v2" />
                                                <input type="hidden" id="service_standard_config_v2_notify" />
                                                <div id="service_standard_config_v2_div">

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </div>

                                    <!-- 人脸认证设置 -->
                                    <div class="col-xs-6 col-sm-6 widget-container-span qing_common_config api_detect_auth_config hide">
                                    <#if configKey == "api_detect_auth_config">
                                    <div class="widget-box">
                                    <#else >
                                    <div class="widget-box collapsed">
                                    </#if>
                                        <div class="widget-header header-color-pink">
                                            <h5 class="smaller">人脸认证(api_detect_auth_config)</h5>

                                            <div class="widget-toolbar no-border">
                                                <a href="#" data-action="collapse">
                                                <#if configKey == "api_detect_auth_config">
                                                    <i class="icon-chevron-up"></i>
                                                <#else >
                                                    <i class="icon-chevron-down"></i>
                                                </#if>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="widget-body">
                                            <div class="widget-main padding-6">
                                                <input type="hidden" id="api_detect_auth_config" />
                                                <input type="hidden" id="api_detect_auth_config_notify" />
                                                <div id="api_detect_auth_config_div">

                                                </div>
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
    var installment_pay_config_v2_paramInfo;
    var api_moon_arrange_course_config_param_info;
    var change_course_constraint_config_param_info;
    var service_standard_config_v2_param_info;
    var api_detect_auth_config_param_info;

    var resizeChanged = false;

    function initDefault(){
        var allConfig;
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
        installment_pay_config_v2_paramInfo = init_installment_pay_config_v2("{}");
        api_moon_arrange_course_config_param_info = init_api_moon_arrange_course_config('{"isOpen":false,"startUpdateDay":3,"endUpdateDay":25,"expriedDay":20,"deduction":{"moreHalf":3.0,"lessHalf":5.0,"none":8.0}}');
        change_course_constraint_config_param_info = init_change_course_constraint_config('{"teacherChangeCourseConstraintConfig":{"freeChangeCourseNumPerMonth":1,"duringFineRate":10,"outsideFineRate":20,"compensateRate":80},"studentChangeCourseConstraintConfig":{"freeChangeCourseNumPerMonth":1,"duringFineRate":10,"outsideFineRate":20,"compensateRate":80},"cities":[1,2,3]}');

        service_standard_config_v2_param_info = init_service_standard_config_v2("{}");
        api_detect_auth_config_param_info = init_api_detect_auth_config("{}");

        <#if configKey != "">
            $(".qing_common_config.${configKey}").removeClass("hide");
            var resizeEles = $(".qing_resize");
            var eleIdx = 0;
            while(eleIdx < resizeEles.length){
                var ele = resizeEles[eleIdx];
                if($(ele).children(".qing_common_config").length == $(ele).children(".qing_common_config.hide").length){
                    $(ele).addClass("hide");
                }

                eleIdx++;
            }
        <#else>
            $(".qing_common_config").removeClass("hide");
        </#if>
    }

    function init_api_teacher_time_config(configValue){
        $("#api_teacher_time_config").val(configValue);
        var paramsTemplate = '[{"key":"teachingHours","name":"总时长","defaultValue":{"name":0,"value":0}},{"key":"weekendTeachingHours","name":"周末时长","defaultValue":{"name":0,"value":0}}]';

        return showParam({paramData:fillTemplate(paramsTemplate, configValue), htmlDiv:"api_teacher_time_config_div", "valueChangedNotifyId":"api_teacher_time_config_notify", "isEditStatus":false});
    }

    function init_installment_pay_config_v2(configValue){
        $("#installment_pay_config_v2").val(configValue);

        var paramsTemplate = '[{"key":"configMap", "name":"分期配置", "detail":[{"key":"9", "name":"招行分期", "detail":[{"key":"open", "name":"是否开启", "class":"switch_editable","defaultValue":{"name":false,"value":false}},{"key":"limitAmount", "name":"最低金额","defaultValue":{"name":0,"value":0}},{"key":"maxAmount", "name":"最大金额","defaultValue":{"name":"无限制","value":null}}]} ,{"key":"10", "name":"花呗分期", "detail":[{"key":"open", "name":"是否开启", "class":"switch_editable","defaultValue":{"name":false,"value":false}},{"key":"limitAmount", "name":"最低金额","defaultValue":{"name":0,"value":0}},{"key":"maxAmount", "name":"最大金额","defaultValue":{"name":"无限制","value":null}}]} ,{"key":"11", "name":"京东支付", "detail":[{"key":"open", "name":"是否开启", "class":"switch_editable","defaultValue":{"name":false,"value":false}},{"key":"limitAmount", "name":"最低金额","defaultValue":{"name":0,"value":0}},{"key":"maxAmount", "name":"最大金额","defaultValue":{"name":"无限制","value":null}}]} ,{"key":"12", "name":"百度支付", "detail":[{"key":"open", "name":"是否开启", "class":"switch_editable","defaultValue":{"name":false,"value":false}},{"key":"limitAmount", "name":"最低金额","defaultValue":{"name":0,"value":0}},{"key":"maxAmount", "name":"最大金额","defaultValue":{"name":"无限制","value":null}}]} ,{"key":"16", "name":"爱海米分期", "detail":[{"key":"open", "name":"是否开启", "class":"switch_editable","defaultValue":{"name":false,"value":false}},{"key":"limitAmount", "name":"最低金额","defaultValue":{"name":0,"value":0}},{"key":"maxAmount", "name":"最大金额","defaultValue":{"name":"无限制","value":null}}]}]}]';

        return showParam({paramData:fillTemplate(paramsTemplate, configValue), htmlDiv:"installment_pay_config_v2_div", "valueChangedNotifyId":"installment_pay_config_v2_notify", "isEditStatus":false});
    }

    function init_api_moon_arrange_course_config(configValue){
        $("#api_moon_arrange_course_config").val(configValue);
        var paramsTemplate = '[{"key":"isOpen","name":"是否生成任务", "class": "switch_editable","defaultValue":{"name":false,"value":false}},{"key":"startUpdateDay","name":"几号开始","defaultValue":{"name":0,"value":0}},{"key":"endUpdateDay","name":"几号结束","defaultValue":{"name":0,"value":0}},{"key":"deductionConfig","name":"扣费配置","detail":[{"key":"deductionIsOpen","name":"是否扣费", "class": "switch_editable","defaultValue":{"name":0,"value":0}},{"key":"deductionDay","name":"几号扣费","defaultValue":{"name":0,"value":0}},{"key":"endGenerateDay","name":"几号停止生成","defaultValue":{"name":0,"value":0}},{"key":"deduction","name":"扣费比例配置","detail":[{"key":"moreHalf","name":"一半以上","defaultValue":{"name":0,"value":0}},{"key":"lessHalf","name":"一半以下","defaultValue":{"name":0,"value":0}},{"key":"none","name":"未做任务","defaultValue":{"name":0,"value":0}}]}]}]';

        return showParam({paramData:fillTemplate(paramsTemplate, configValue), htmlDiv:"api_moon_arrange_course_config_div", "valueChangedNotifyId":"api_moon_arrange_course_config_notify", "isEditStatus":false});
    }

    function init_change_course_constraint_config(configValue){
        $("#change_course_constraint_config").val(configValue);
        var paramsTemplate = '[{"key":"teacherChangeCourseConstraintConfig","name":"老师端","detail":[{"key":"freeChangeCourseNumPerMonth","name":"freeChangeCourseNumPerMonth","defaultValue":{"name":1,"value":1}},{"key":"duringFineRate","name":"duringFineRate","defaultValue":{"name":10,"value":10}},{"key":"outsideFineRate","name":"outsideFineRate","defaultValue":{"name":20,"value":20}},{"key":"compensateRate","name":"compensateRate","defaultValue":{"name":80,"value":80}}]},{"key":"studentChangeCourseConstraintConfig","name":"学生端","detail":[{"key":"freeChangeCourseNumPerMonth","name":"freeChangeCourseNumPerMonth","defaultValue":{"name":1,"value":1}},{"key":"duringFineRate","name":"duringFineRate","defaultValue":{"name":10,"value":10}},{"key":"outsideFineRate","name":"outsideFineRate","defaultValue":{"name":20,"value":20}},{"key":"compensateRate","name":"compensateRate","defaultValue":{"name":80,"value":80}}]},[{"key":"cities","name":"支持城市", "defaultValue":[{"name":1,"value":1}]}]]';

        return showParam({paramData:fillTemplate(paramsTemplate, configValue), htmlDiv:"change_course_constraint_config_div", "valueChangedNotifyId":"change_course_constraint_config_notify", "isEditStatus":false});
    }

    function init_service_standard_config_v2(configValue){
        $("#service_standard_config_v2").val(configValue);
        var paramsTemplate = '[{"key":"enable","name":"enable", "class": "switch_editable","defaultValue":{"name":false,"value":false}},{"key":"deductAmount","name":"deductAmount","defaultValue":{"name":2,"value":2}},{"key":"startCourseTimeInterval","name":"startCourseTimeInterval","defaultValue":{"name":6,"value":6}},{"key":"liveClassPassingThreshold","name":"liveClassPassingThreshold","defaultValue":{"name":6,"value":6}},{"key":"live1v1PassingThreshold","name":"live1v1PassingThreshold","defaultValue":{"name":6,"value":6}},{"key":"offline1v1PassingThreshold","name":"offline1v1PassingThreshold","defaultValue":{"name":7,"value":7}},{"key":"offlineGroupPassingThreshold","name":"offlineGroupPassingThreshold","defaultValue":{"name":7,"value":7}},{"key":"validDays","name":"validDays","defaultValue":{"name":7,"value":7}},{"key":"firstCourseCommunication","name":"firstCourseCommunication","detail":[{"key":"enable","name":"enable", "class": "switch_editable","defaultValue":{"name":true,"value":true}},{"key":"deductAmount","name":"deductAmount","defaultValue":{"name":4,"value":4}}]},{"key":"firstCourseTraining","name":"firstCourseTraining","detail":[{"key":"enable","name":"enable", "class": "switch_editable","defaultValue":{"name":true,"value":true}},{"key":"deductAmount","name":"deductAmount","defaultValue":{"name":2,"value":2}},{"key":"createInterval","name":"createInterval","defaultValue":{"name":30,"value":30}}]},{"key":"firstCourseRecordOnlineLesson","name":"firstCourseRecordOnlineLesson","detail":[{"key":"enable","name":"enable", "class": "switch_editable","defaultValue":{"name":true,"value":true}}]},{"key":"firstCourseCommunicationV2","name":"firstCourseCommunicationV2","detail":[{"key":"enable", "class": "switch_editable","name":"enable","defaultValue":{"name":false,"value":false}},{"key":"deductAmount","name":"deductAmount","defaultValue":{"name":7,"value":7}},[{"key":"openCities","name":"openCities","defaultValue":[{"name":151,"value":151}]}]]}]';
        return showParam({paramData:fillTemplate(paramsTemplate, configValue), htmlDiv:"service_standard_config_v2_div", "valueChangedNotifyId":"service_standard_config_v2_notify", "isEditStatus":false});
    }

    function init_api_detect_auth_config(configValue){
        var key = "api_detect_auth_config";
        $("#" + key).val(configValue);
        var paramsTemplate = '[{"key":"isOpen", "class": "switch_editable","name":"开关","defaultValue":{"name":true,"value":true}},{"key":"maxDetectAuthNum","name":"次数上限","defaultValue":{"name":10,"value":10}}]';

        return showParam({paramData:fillTemplate(paramsTemplate, configValue), htmlDiv:key + "_div", "valueChangedNotifyId":key + "_notify", "isEditStatus":false});
    }


    function fillTemplate(paramsTemplate, configValue){
        var params = JSON.parse(paramsTemplate);
        var configObj = JSON.parse(configValue);

        fillDefaultValueWithDefault(params, configObj);

        return JSON.stringify(params);
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

    //输入框的值改变时触发
    $(document).on("change", "#installment_pay_config_v2_notify",function(e){
        var result = generateJsonParam("#installment_pay_config_v2_div input", installment_pay_config_v2_paramInfo);

        setConfig("installment_pay_config_v2", JSON.stringify(result));
    });

    //输入框的值改变时触发
    $(document).on("change", "#api_moon_arrange_course_config_notify",function(e){
        var result = generateJsonParam("#api_moon_arrange_course_config_div input", api_moon_arrange_course_config_param_info);

        setConfig("api_moon_arrange_course_config", JSON.stringify(result));
    });

    //输入框的值改变时触发
    $(document).on("change", "#change_course_constraint_config_notify",function(e){
        var result = generateJsonParam("#change_course_constraint_config_div input", change_course_constraint_config_param_info);

        setConfig("change_course_constraint_config", JSON.stringify(result));
    });

    //输入框的值改变时触发
    $(document).on("change", "#service_standard_config_v2_notify",function(e){
        var result = generateJsonParam("#service_standard_config_v2_div input", service_standard_config_v2_param_info);

        setConfig("service_standard_config_v2", JSON.stringify(result));
    });

    //输入框的值改变时触发
    $(document).on("change", "#api_detect_auth_config_notify",function(e){
        var result = generateJsonParam("#api_detect_auth_config_div input", api_detect_auth_config_param_info);

        setConfig("api_detect_auth_config", JSON.stringify(result));
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
        allConfig = resu.resultList;

        var selectConfigList = [];
        selectConfigList[0] = {key:"",value:"先不选"};
        for( var idx in resu.resultList){
            var config = resu.resultList[idx];
            selectConfigList[idx + 1] = {key:config.key, value:config.key};

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
            if("installment_pay_config_v2" == config.key){
                installment_pay_config_v2_paramInfo = init_installment_pay_config_v2(config.value);
            }
            if("api_moon_arrange_course_config" == config.key){
                api_moon_arrange_course_config_param_info = init_api_moon_arrange_course_config(config.value);
            }
            if("change_course_constraint_config" == config.key) {
                change_course_constraint_config_param_info = init_change_course_constraint_config(config.value);
            }
            if("service_standard_config_v2" == config.key) {
                service_standard_config_v2_param_info = init_service_standard_config_v2(config.value);
            }
            if("api_detect_auth_config" == config.key) {
                api_detect_auth_config_param_info = init_api_detect_auth_config(config.value);
            }

            $(".qing_param_edit").removeClass("hide");
        }

        updateOptions("configKeyChoose", selectConfigList, "");
    }

    $(document).on("click", ".qing_config_switch", function(){
        var inputEle = $(this).children("input").first();
        var switchValue = $(inputEle).val();
        var newValue = (switchValue != "true") ;
        $(inputEle).val(newValue);

        setConfig($(inputEle).attr("id"), newValue);
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

    $("#configKeyChoose").change(function(){
        var value = $(this).val();
        if(value == ""){
            $("#configValueParam").val("");
        }else{
            var idx = 0;
            var len = allConfig.length;
            while(idx < len){
                var config = allConfig[idx];
                if(config.key == value){
                    $("#configValueParam").val(config.value);
                    break;
                }
                idx++;
            }
        }
    });

    $("#searchBtn").click(function(){
        var configKey = $("#configKeyChoose").val();
        if(value == ""){
            return;
        }

        var configValue = $("#configValueParam").val();

        setConfig(configKey, configValue);
    });

    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();
        $('#teacher_max_teachable_student_count').ace_spinner({value:0,min:0,max:1000,step:1, on_sides: true, icon_up:'icon-plus smaller-75', icon_down:'icon-minus smaller-75', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});
    });
</script>
    </div>
</body>
</html>