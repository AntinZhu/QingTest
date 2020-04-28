<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>创建新服务</title>
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
                                                <!-- 接口参数 -->
                                                <div class="timeline-item clearfix">
                                                    <div class="timeline-info">
                                                        <img alt="Susan't Avatar" src="${base}/static/assets/avatars/avatar1.png" />
                                                        <span class="label label-info label-sm">16:22</span>
                                                    </div>

                                                    <div class="panel panel-default">
                                                        <div id="faq-0-1">
                                                            <div id="xxxx" class="accordion-style2">
                                                                <div class="group">
                                                                    <h3 class="accordion-header">用户参数填写</h3>

                                                                    <div>
                                                                        <div class="col-xs-12">
                                                                            <!-- 必备信息 -->
                                                                            <form id="createProjectForm" class="form-horizontal" method="post" target="_blank" action="${base}/v1/project/create">
                                                                                <input type="hidden" id="custom" name = "custom" />
                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="svcName">服务名:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-3" type="text" style="width: 40%;" name="svcName" id="svcName" placeholder="请使用驼峰风格，方便解析:teacherSvc..." />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="poolCode">服务池code:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-3" type="text" style="width: 40%;" name="poolCode" id="poolCode" placeholder="poolCode : ps-teacher-svc..." />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="basePackage">包名:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-3" type="text" style="width: 40%;" name="basePackage" id="basePackage" placeholder="basePackage : com.qingqing.api.teacher..." />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="createUser">创建人:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-3" type="text" style="width: 40%;" name="createUser" id="createUser" placeholder="createUser..." />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>
                                                                            </form>

                                                                            <div class="hr hr-dotted"></div>
                                                                            <div class="hr hr-dotted"></div>
                                                                            <!-- 按钮 -->
                                                                            <div class="clearfix form-actions">
                                                                                <div class="col-md-offset-3 col-md-9">
                                                                                    <button class="btn btn-info" type="button" id = "saveBtn">
                                                                                        <i class="icon-ok bigger-110"></i>
                                                                                        Submit
                                                                                    </button>

                                                                                    &nbsp; &nbsp; &nbsp;
                                                                                    <button class="btn" type="reset" id = "resetBtn">
                                                                                        <i class="icon-undo bigger-110"></i>
                                                                                        Reset
                                                                                    </button>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div><!-- #accordion -->
                                                        </div>
                                                    </div>
                                                </div>

                                                <!-- 接口参数 -->
                                                <div class="timeline-item clearfix">
                                                    <div class="timeline-info">
                                                        <i class="timeline-indicator icon-star btn btn-warning no-hover green"></i>
                                                    </div>

                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                            <div href="#faq-0-1"  class="accordion-toggle">
                                                                <h1>
                                                                    可选配置和组件
                                                                    <small>
                                                                        <i class="icon-double-angle-right"></i>
                                                                        <label id = "interfaceNameDiv">选择你会使用到的配置</label>
                                                                    </small>
                                                                </h1>
                                                            </div>
                                                        </div>

                                                        <div id="faq-0-1">
                                                            <div id="accordion" class="accordion-style2">
                                                                <div class="col-md-6">
                                                                    <div class="group">
                                                                        <h3 id="param-step-1" class="accordion-header">DB相关</h3>
                                                                        <div>
                                                                            <div class="col-md-12" style="padding:0;position:relative;height:100%;">
                                                                                <div style="min-height: 300px;">
                                                                                    <form class="form-horizontal">
                                                                                        <div class="form-group">
                                                                                            <label class="col-sm-3 control-label no-padding-right" for="selfDbName">服务自用数据库名:</label>

                                                                                            <div class="col-sm-9">
                                                                                                <div class="clearfix">
                                                                                                    <input class="col-xs-6" type="text" id="selfDbName" placeholder="对应数据库名称..." />
                                                                                                </div>

                                                                                                <div class="space-2"></div>
                                                                                            </div>
                                                                                        </div>

                                                                                        <div class="form-group">
                                                                                            <label class="col-sm-3 control-label no-padding-right" for="useMasterDbName">其他Master数据库名</label>

                                                                                            <div class="col-sm-9">
                                                                                                <div class="clearfix">
                                                                                                    <select multiple="" class="width-80 chosen-select tag-input-style" id="useMasterDbName" data-placeholder="选择已有数据库...">
                                                                                                        <option value="qq_data">qq_data</option>
                                                                                                        <option value="qq_user">qq_user</option>
                                                                                                        <option value="qq_passport">qq_passport</option>
                                                                                                    </select>
                                                                                                </div>

                                                                                                <div class="space-2"></div>
                                                                                            </div>
                                                                                        </div>

                                                                                        <div class="form-group">
                                                                                            <label class="col-sm-3 control-label no-padding-right" for="useSlaveDbName">其他Slave数据库名</label>

                                                                                            <div class="col-sm-9">
                                                                                                <div class="clearfix">
                                                                                                    <select multiple="" class="width-80 chosen-select tag-input-style" id="useSlaveDbName" data-placeholder="选择已有数据库...">
                                                                                                        <option value="qq_data">qq_data</option>
                                                                                                        <option value="qq_user">qq_user</option>
                                                                                                        <option value="qq_passport">qq_passport</option>
                                                                                                    </select>
                                                                                                </div>

                                                                                                <div class="space-2"></div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </form>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-md-6">
                                                                    <div class="col-xs-12 col-sm-12 widget-container-span">
                                                                        <div class="widget-box">
                                                                            <div class="widget-header header-color-blue">
                                                                                <h5 class="bigger lighter">
                                                                                    <i class="icon-table"></i>
                                                                                    可选项(TODO-功能配置待丰富)
                                                                                </h5>
                                                                            </div>

                                                                            <div class="widget-body">
                                                                                <div class="widget-main no-padding">
                                                                                    <table class="table table-striped table-bordered table-hover">
                                                                                        <thead class="thin-border-bottom">
                                                                                        <tr>
                                                                                            <th>
                                                                                                <i class="icon-user"></i>
                                                                                                功能描述
                                                                                            </th>
                                                                                            <th class="hidden-480">是否开启</th>
                                                                                            <th>
                                                                                                <i class="icon-user"></i>
                                                                                                功能描述
                                                                                            </th>
                                                                                            <th class="hidden-480">是否开启</th>
                                                                                        </tr>
                                                                                        </thead>

                                                                                        <tbody>
                                                                                        <tr>
                                                                                            <td class="">服务自用Redis</td>
                                                                                            <td>
                                                                                                <label class="pull-left inline"  title="信了你的鬼" data-rel="tooltip" >
                                                                                                    <input id="REDIS_SELF" type="checkbox" class="ace ace-switch ace-switch-6 qing_custom_switch" value="0" />
                                                                                                    <span class="lbl"></span>
                                                                                                </label>
                                                                                            </td>

                                                                                            <td class=""></td>
                                                                                            <td>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td class="">使用UserInfoDp</td>
                                                                                            <td>
                                                                                                <label class="pull-left inline"  title="信了你的鬼" data-rel="tooltip" >
                                                                                                    <input id="REDIS_USER_INFO_DP" type="checkbox" class="ace ace-switch ace-switch-6 qing_custom_switch" value="0" />
                                                                                                    <span class="lbl"></span>
                                                                                                </label>
                                                                                            </td>

                                                                                            <td class="">Mock功能配置</td>
                                                                                            <td>
                                                                                                <label class="pull-left inline"  title="信了你的鬼" data-rel="tooltip" >
                                                                                                    <input id="COMMON_MOCK" type="checkbox" class="ace ace-switch ace-switch-6 qing_custom_switch" value="0" />
                                                                                                    <span class="lbl"></span>
                                                                                                </label>
                                                                                            </td>
                                                                                        </tr>
                                                                                        </tbody>
                                                                                    </table>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div><!-- /span -->

                                                                </div>

                                                            </div><!-- #accordion -->
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
            $(document).ready(function(){
            });

            $(document).on("change", ".qing_custom_switch", function(){
                if($(this).val() == 0){
                    $(this).val(1);
                }else{
                    $(this).val(0);
                }
            });

            $("#saveBtn").click(function () {
                var svcName = $("#svcName").val();
                if(isStringEmpty(svcName)){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "服务名不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }
                var poolCode = $("#poolCode").val();
                if(isStringEmpty(poolCode)){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "服务池Code不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }

                var basePackage = $("#basePackage").val();
                if(isStringEmpty(basePackage)){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "包名不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }

                var customArr = generateCustom();
                $("#custom").val(JSON.stringify(customArr));

                $("#createProjectForm").submit();

                <#--var data = {-->
                    <#--"svcName":svcName,-->
                    <#--"poolCode": poolCode,-->
                    <#--"basePackage": basePackage,-->
                    <#--"createUser":$("#createUser").val()-->
                <#--};-->

                <#--var request = {-->
                    <#--url : "${base}/v1/project/create.json",-->
                    <#--data : data,-->
                    <#--handlerFunc : handlerSave,-->
                    <#--isASync : true,-->
                    <#--failTitle :"保存失败:"-->
                <#--};-->

                <#--commonAjaxRequest(request);-->
            });

            function generateCustom(){
                var customArr = [];

                if($("#selfDbName").val() != ""){
                    var custom = {};
                    custom.itemType = "MYSQL_DB";
                    custom.customJson = '{"dbName":"' + $("#selfDbName").val() + '"}';

                    customArr.push(custom);
                }
                if($("#useMasterDbName").val() != null){
                    var useDbsArr = $("#useMasterDbName").val();
                    for(var idx in useDbsArr){
                        var useDb = useDbsArr[idx];
                        var custom = {};
                        custom.itemType = "MYSQL_DB";
                        custom.customJson = '{"dbName":"' + useDb + '"}';

                        customArr.push(custom);
                    }
                }

                if($("#useSlaveDbName").val() != null){
                    var useDbsArr = $("#useSlaveDbName").val();
                    for(var idx in useDbsArr){
                        var useDb = useDbsArr[idx];
                        var custom = {};
                        custom.itemType = "MYSQL_DB";
                        custom.customJson = '{"dbName":"' + useDb + '","type":"slave"}';

                        customArr.push(custom);
                    }
                }

                var customSwitchItems = $(".qing_custom_switch");
                var itemIdx = 0;
                while(itemIdx < customSwitchItems.length){
                    var item = customSwitchItems[itemIdx];
                    if($(item).val() == 1){
                        var custom = {};
                        custom.itemType = $(item).attr("id");

                        customArr.push(custom);
                    }

                    itemIdx++;
                }

                return customArr;
            }

            function handlerSave(resu){
                $.gritter.add({
                    title : '提示:',
                    text : '添加成功',
                    class_name : 'gritter-info gritter-center'
                });
            }

            jQuery(function($) {
                $(".chosen-select").chosen();

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