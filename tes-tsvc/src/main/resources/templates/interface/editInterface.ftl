<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Hello World!</title>
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

    <script src="${base}/static/js/json/hm.js"></script>
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
                                                                    <h3 class="accordion-header">接口参数填写</h3>

                                                                    <div>
                                                                        <div class="col-xs-12">
                                                                            <form class="form-horizontal">
                                                                                <input type="hidden" id = "interfaceId" />
                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="catelogName">目录显示名称:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-3" type="text" id="catelogName" placeholder="目录显示名称..." />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <#if ((interfaceBean.inter.id)!0) gt 0>
                                                                                    <div class="form-group">
                                                                                        <label class="col-sm-3 control-label no-padding-right" for="interfaceName">现在所属目录:</label>

                                                                                        <div class="col-sm-9">
                                                                                            <div class="clearfix">
                                                                                                <div class="breadcrumbs" id="breadcrumbs">
                                                                                                    <script type="text/javascript">
                                                                                                        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                                                                                                    </script>

                                                                                                    <ul class="breadcrumb">
                                                                                                        <#list interfaceBean.parentCatelogList? reverse as cate>
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
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="interfaceName">接口名称:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-5" type="text" id="interfaceName" placeholder="输入接口名称..." />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="interfaceUrl">接口地址:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-10" type="text" id="interfaceUrl" placeholder="输入接口地址..." />
                                                                                            <div class="col-xs-9 label label-lg label-light arrowed-in arrowed-right qing_input_tip" style="color: #333">
                                                                                                需要以服务上下文开始，.json结尾，举例：<b class="red">/svc</b>/api/pt/v1/teacher/get_open_class_completion_status<b class="red">.json</b>
                                                                                            </div>
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="interfaceType">接口类型:</label>

                                                                                    <div class="col-xs-12 col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="hidden" name="interfaceType" id="interfaceType" value="PT" />
                                                                                            <button type="button" value="PT" style="border-radius: 8px;" class="btn btn-sm qing_interfaceType btn-primary">&thinsp;&thinsp;&thinsp;&thinsp;PT&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;</button>
                                                                                            <button type="button" value="PI" style="border-radius: 8px;" class="btn btn-sm qing_interfaceType">&thinsp;&thinsp;&thinsp;&thinsp;PI&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;</button>
                                                                                            <button type="button" value="PB" style="border-radius: 8px;" class="btn btn-sm qing_interfaceType">&thinsp;&thinsp;&thinsp;&thinsp;PB&thinsp;&thinsp;&thinsp;&thinsp;&thinsp;</button>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="env">接口请求人用户类型:</label>

                                                                                    <div class="col-xs-12 col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="hidden" name="requestUserType" id="requestUserType" value="student" />
                                                                                            <button type="button" value="student" style="border-radius: 8px;" class="btn btn-sm  qing_requestUserType btn-primary">&thinsp;&thinsp;学生&thinsp;&thinsp;</button>
                                                                                            <button type="button" value="teacher" style="border-radius: 8px;" class="btn btn-sm  qing_requestUserType">&thinsp;&thinsp;老师&thinsp;&thinsp;</button>
                                                                                            <button type="button" value="ta" style="border-radius: 8px;" class="btn btn-sm qing_requestUserType">&thinsp;&thinsp;助教&thinsp;&thinsp;</button>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="className">是否需要参数：</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <label>
                                                                                                <input id="hasParam" class="ace ace-switch ace-switch-6" type="checkbox" value="1" checked="checked" />
                                                                                                <span class="lbl"></span>
                                                                                            </label>
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group" id="classNameDiv">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="className">请求参数类名：</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="hidden" id="paramDetail" />
                                                                                            <input class="col-xs-10" type="text" id="className" placeholder="输入类名..." />
                                                                                            <button type="button" id="generateParam" style="border-radius: 8px;margin-left: 5px" class="btn btn-sm">生成参数</button>
                                                                                            <div class="col-xs-9 label label-lg label-light arrowed-in arrowed-right qing_input_tip" style="color: #333;">
                                                                                                内部类使用$,举例:com.qingqing.api.proto.v1.Pay<b class="red">$</b>GeneralOrderPaymentSummaryV2Response
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </form>

                                                                            <div class="hr hr-dotted"></div>
                                                                            <div class="hr hr-dotted"></div>

                                                                            <#include "/include/paramDetail.ftl" />

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
            //输入框的值改变时触发
            $(document).on("change", "#paramDetail",function(e){
                $("#paramDetail").val(generateEditParam("#paramListDiv input"));
            });

//            $(document).on("change", "#paramListDiv :input:not(.qing_editable)",function(e){
//
//                return false;
//            });

            $(document).ready(function(){
            <#if ((interfaceBean.inter.id)!0) gt 0>
                $("#interfaceId").val(${interfaceBean.inter.id});
                $("#catelogName").val("${interfaceBean.catelog.catelogName}");
                $("#parentCatelogId").val("${interfaceBean.catelog.id}");
                $("#interfaceName").val("${interfaceBean.inter.interfaceName}");
                $("#interfaceUrl").val("${interfaceBean.inter.interfaceUrl}");
                var paramDetail ='${interfaceBean.inter.paramDetail!""}';
                $("#paramDetail").val(paramDetail);
                if(paramDetail != ""){
                    showParam({paramData:paramDetail, isEditStatus:true, valueChangedNotifyId:"paramDetail"});
                }else{
                    $("#hasParam").removeAttr("checked");
                    $("#hasParam").val(0);
                }

                var interfaceType = "${interfaceBean.inter.interfaceType}";
                $(".qing_interfaceType.btn-primary").removeClass("btn-primary");
                $(".qing_interfaceType[value=" + interfaceType + "]").addClass("btn-primary");
                $("#interfaceType").val(interfaceType);

                var requestUserType = "${interfaceBean.inter.requestUserType}";
                $(".qing_requestUserType.btn-primary").removeClass("btn-primary");
                $(".qing_requestUserType[value=" + requestUserType + "]").addClass("btn-primary");
                $("#requestUserType").val(requestUserType);
                $("#className").val("${(interfaceBean.inter.paramClassName)!''}");

                // TODO 父的cateId
                showParentCatelog("${base}/v1/test/catelog.json", "tree1", "parentCatelogId", ${interfaceBean.catelog.id});
                $(".qing_input_tip").addClass("hide");
            <#else>
                showParentCatelog("${base}/v1/test/catelog.json", "tree1", "parentCatelogId");
            </#if>
            });

            $("#generateParam").click(function () {
                var className = $('#className').val().trim();
                if(className == null || className == ""){
                    notice("参数有误", "请输入类名");
                    return;
                }

                var data = {
                    data : className
                };

                commonAjaxRequest("${base}/v1/test/interface/request/convert.json", data, handlerConvert, true, "解析出错:");
            });

            function handlerConvert(resu){
                jsonShow(resu, "json-interface");
                jsonShow(resu.data, "json-interface-detail");
                showParam({paramData:resu.data, isEditStatus:true,valueChangedNotifyId:"paramDetail"});
                $("#paramDetail").val(resu.data);
            }

            $("#resetBtn").click(function () {
                jsonShow(generateEditParam("#paramListDiv input"), "json-interface");
            });

            $(".qing_interfaceType").click(function(){
                $(".qing_interfaceType.btn-primary").removeClass("btn-primary");
                $(this).addClass("btn-primary");

                var interfaceType = $(this).val();
                $("#interfaceType").val(interfaceType);
                if(interfaceType == "PB"){
                    $(".qing_requestUserType.btn-primary").removeClass("btn-primary");
                    $("#requestUserType").val("");
                    $(".qing_requestUserType").attr("disabled", "disabled");
                }else{
                    $(".qing_requestUserType").removeAttr("disabled");
                    var requestUserTypes = $(".qing_requestUserType");
                    $(requestUserTypes[0]).addClass("btn-primary");
                    $("#requestUserType").val($(requestUserTypes[0]).val());
                }
            });

            $(".qing_requestUserType").click(function(){
                $(".qing_requestUserType.btn-primary").removeClass("btn-primary");
                $(this).addClass("btn-primary");
                $("#requestUserType").val($(this).val());
            });

            $("#hasParam").change(function () {
                var value = $(this).val();
                if(value == 1){
                    $(this).val(0);
                    $("#classNameDiv").addClass("hide");
                    $("#paramDiv").addClass("hide");
                }else{
                    $("#classNameDiv").removeClass("hide");
                    var paramDetail = $("#paramDetail").val();
                    if(paramDetail != null && paramDetail != ""){
                        $("#paramDiv").removeClass("hide");
                    }
                    $(this).val(1);
                }
            });

            $("#saveBtn").click(function () {
                var catelogName = $("#catelogName").val();
                if(catelogName == null || catelogName == ""){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "目录显示名称不能为空",
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

                var interfaceName = $("#interfaceName").val();
                if(interfaceName == null || interfaceName == ""){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "接口名称不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }
                var interfaceUrl = $("#interfaceUrl").val();
                if(interfaceUrl == null || interfaceUrl == ""){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "接口地址不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }
                var interfaceType = $("#interfaceType").val();
                if(interfaceType == null || interfaceType == ""){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "接口类型不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }
                var requestUserType = $("#requestUserType").val();
                if(interfaceType != "PB"){
                    if((requestUserType == null || requestUserType == "")){
                        $.gritter.add({
                            title : '参数错误:',
                            text : "请求人用户类型不能为空",
                            class_name : 'gritter-error gritter-center'
                        });
                        return;
                    }
                }else{
                    requestUserType = null;
                }


                var paramDetail = "";
                if($("#hasParam").val() == 1){
                    $("#paramDetail").val(generateEditParam("#paramListDiv input"));
                    paramDetail = $("#paramDetail").val();;
                    if(paramDetail == null || paramDetail == ""){
                        $.gritter.add({
                            title : '参数错误:',
                            text : "请求参数不能为空",
                            class_name : 'gritter-error gritter-center'
                        });
                        return;
                    }
                }

                var data = {
                    inter:{
                        id : $("#interfaceId").val(),
                        interfaceName : interfaceName,
                        interfaceUrl : interfaceUrl,
                        interfaceType : interfaceType,
                        requestType : 1,
                        requestUserType : requestUserType,
                        sortDescNum: 0,
                        catelogIndex:'0',
                        paramDetail : paramDetail,
                        deleted : 0,
                        paramClassName : $("#className").val()
                    },
                    catelogName : catelogName,
                    parentCatelogId : parentCatelogId
                };

                commonAjaxRequest("${base}/v1/test/interface/save.json", data, handlerSave, true, "解析出错:");
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