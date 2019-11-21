<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>新增通用配置</title>
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

        .profile-info-value>span+span:before {
            content: "";
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
    <script src="${base}/static/js/catelog.js"></script>
    <script src="${base}/static/assets/js/fuelux/fuelux.tree.min.js"></script>
    <script src="${base}/static/assets/js/fuelux/data/fuelux.tree-sampledata.js"></script>

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
                                                            <div id="accordion" class="accordion-style2">
                                                                <div class="group">
                                                                    <h3 class="accordion-header">配置参数填写</h3>

                                                                    <div>
                                                                        <div class="col-xs-12">
                                                                            <form class="form-horizontal">
                                                                                <input type="hidden" id = "configId" />
                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="configName">配置名称:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-3" type="text" id="configName" placeholder="配置名称..." />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <#if ((configBean.config.id)!0) gt 0>
                                                                                    <div class="form-group">
                                                                                        <label class="col-sm-3 control-label no-padding-right" for="interfaceName">现在所属目录:</label>

                                                                                        <div class="col-sm-9">
                                                                                            <div class="clearfix">
                                                                                                <div class="breadcrumbs" id="breadcrumbs">
                                                                                                    <script type="text/javascript">
                                                                                                        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                                                                                                    </script>

                                                                                                    <ul class="breadcrumb">
                                                                                                        <#list configBean.parentCatelogList? reverse as cate>
                                                                                                            <li><a href="#">${cate.catelogName}</a></li>
                                                                                                        </#list>
                                                                                                    </ul><!-- .breadcrumb -->
                                                                                                </div>
                                                                                            </div>

                                                                                            <div class="space-2"></div>
                                                                                        </div>
                                                                                    </div>
                                                                                </#if>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="interfaceName">接口所属目录:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="hidden" id="parentCatelogId" >
                                                                                            <div id="tree1" class="tree"></div>
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="configKey">configKey:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-5" type="text" id="configKey" placeholder="输入configKey..." />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" style="text-align: right" for="fullData">默认值：</label>

                                                                                    <div class="col-sm-9">
                                                                                        <textarea id="defaultValue" style="height: 250px" class="autosize-transition form-control"></textarea>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" style="text-align: right" for="aa"></label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="col-sm-offset-5">
                                                                                            <button type="button" class="btn btn-grey btn-sm" id="paramTemplateConvertBtn">
                                                                                                <i class="icon-refresh"></i>
                                                                                            </button>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group qing_catelog_hide">
                                                                                    <label class="col-sm-3 control-label no-padding-right" style="text-align: right" for="fullData">结果：</label>

                                                                                    <div class="col-sm-9">
                                                                                        <textarea id="paramJsonTemplate" style="height: 250px" class="autosize-transition form-control"></textarea>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="hr hr-dotted"></div>

                                                                                <#include "/include/paramDetail.ftl" />

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" style="text-align: right" for="fullData"></label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix form-actions">
                                                                                            <div class="col-md-offset-3 col-md-9">
                                                                                                <button class="btn btn-info" style="border-radius: 8px" type="button" id = "saveBtn">
                                                                                                    <i class="icon-ok bigger-110"></i>
                                                                                                    Submit
                                                                                                </button>

                                                                                                &nbsp; &nbsp; &nbsp;
                                                                                                <button class="btn" type="reset" style="border-radius: 8px" id = "resetBtn">
                                                                                                    <i class="icon-undo bigger-110"></i>
                                                                                                    Reset
                                                                                                </button>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                               </form>
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
            <#--<#include "/test/datetime.ftl" />-->
        </div>

        <script type="text/javascript">
            $('textarea').numberedtextarea();

            $(document).off("click", '.addInputBtn').on('click', '.addInputBtn',cloneInput);
            $(document).off("click", '.delInputBtn').on('click', '.delInputBtn',removeInput);

            var paramInfo;
            $(document).on("change", "#paramJsonTemplate",function(e){
                setTimeout(refreshResult, 500);
            });

            function refreshResult(){
                var param = generateEditParam("#paramListDiv input", paramInfo);
                var jsonValue = JSON.stringify(generateJsonParam("#paramListDiv input", paramInfo));
                $("#paramJsonTemplate").val(param);
                $("#defaultValue").val(jsonValue);

                jsonShow(param, "json-interface-detail");
                jsonShow(jsonValue, "json-interface");
            }

            $("#paramTemplateConvertBtn").click(function(){
                var jsonObjValue = $("#defaultValue").val();
                if(jsonObjValue == ""){
                    $.gritter.add({
                        title : "参数错误",
                        text : "请输入Json串",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }

                jsonShow(jsonObjValue, "json-interface-detail");

                var jsonObj;
                try{
                    jsonObj = JSON.parse(jsonObjValue);
                }catch(e){
                    $.gritter.add({
                        title : "参数错误",
                        text : "Json串格式有误",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }

                var result = JSON.stringify(generateTemplate(jsonObj));
                jsonShow(result, "json-interface-detail");
                $("#paramJsonTemplate").text(result);

                paramInfo = showParam({paramData:result, isEditStatus:true,valueChangedNotifyId:"paramJsonTemplate"});
            });

            $(document).ready(function(){
            <#if ((configBean.config.id)!0) gt 0>
                var jsonTemplate = '${configBean.config.paramTemplate}';
                $("#configId").val(${configBean.config.id});
                $("#configName").val('${configBean.config.configName}');
                $("#configKey").val('${configBean.config.configKey}');
                $("#defaultValue").val('${configBean.config.defaultValue}');
                $("#paramJsonTemplate").val(jsonTemplate);
                $("#parentCatelogId").val("${configBean.catelog.id}");
                jsonShow(jsonTemplate, "json-interface-detail");
                paramInfo = showParam({paramData:jsonTemplate, isEditStatus:true,valueChangedNotifyId:"paramJsonTemplate"});

                // TODO 父的cateId
                showParentCatelog("${base}/v1/test/catelog.json", "tree1", "parentCatelogId", ${configBean.catelog.id});
                $(".qing_input_tip").addClass("hide");
            <#else>
                showParentCatelog("${base}/v1/test/catelog.json", "tree1", "parentCatelogId");
            </#if>
            });

            $("#saveBtn").click(function () {
                var configName = $("#configName").val();
                if(configName == null || configName == ""){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "配置名称不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }

                var parentCatelogId = $("#parentCatelogId").val();
                if(parentCatelogId == null || parentCatelogId == ""){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "所属目录不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }

                var configKey = $("#configKey").val();
                if(configKey == null || configKey == ""){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "configKey不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }
                var defaultValue = $("#defaultValue").val();
                if(defaultValue == null || defaultValue == ""){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "默认值不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }

                var paramJsonTemplate = $("#paramJsonTemplate").val();
                if(paramJsonTemplate == null || paramJsonTemplate == ""){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "结果不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }

                var data = {
                    config:{
                        id : $("#configId").val(),
                        configKey : configKey,
                        configName :configName,
                        paramTemplate : paramJsonTemplate,
                        defaultValue :defaultValue,
                        deleted : 0
                    },
                    parentCatelogId : parentCatelogId
                };

                var request = {
                    url : "${base}/v1/config/common/add.json",
                    data : data,
                    handlerFunc : handlerSave,
                    isASync : true,
                    failTitle :"保存出错:"
                };

                commonAjaxRequest(request);
            });

            function handlerSave(resu){
                $.gritter.add({
                    title : '提示:',
                    text : '添加成功',
                    class_name : 'gritter-info gritter-center'
                });
                refreshCatelog();
            }

            jQuery(function($) {

            });
        </script>
    </div>
</body>
</html>