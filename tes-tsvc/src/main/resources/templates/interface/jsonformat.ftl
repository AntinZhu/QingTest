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
                                                        <i class="timeline-indicator icon-beaker btn btn-default no-hover"></i>
                                                    </div>

                                                    <div class="widget-box transparent">
                                                        <div class="widget-header hidden"></div>

                                                        <div class="widget-body">
                                                            <div class="widget-main" id="interfaceUrl"> Took the final exam. Phew! </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- 接口参数 -->
                                                <div class="timeline-item clearfix">
                                                    <div class="timeline-info">
                                                        <img alt="Susan't Avatar" src="${base}/static/assets/avatars/avatar1.png" />
                                                        <span class="label label-info label-sm">16:22</span>
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
                                                                    <h3 class="accordion-header">接口参数选择</h3>

                                                                    <div>
                                                                        <div class="col-xs-12">
                                                                            <!-- PAGE CONTENT BEGINS -->
                                                                            <form class="form-horizontal" role="form">
                                                                                <input type="hidden" id="interfaceId" name="interfaceId" />
                                                                                <div class="form-group">
                                                                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="env">环境选择:</label>

                                                                                    <div class="col-xs-12 col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="hidden" name="env" id="env" value="dev" class="col-xs-12 col-sm-3" />
                                                                                            <button type="button" value="dev" class="btn env btn-primary">开发环境</button>
                                                                                            <button type="button" value="hjl" class="btn env">接口测试环境</button>
                                                                                            <button type="button" value="tst" class="btn env">测试环境</button>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="hr hr-dotted"></div>

                                                                                <div class="form-group hide" id="requestUserIdDev">
                                                                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="teacherIdIpt">请求人ID:</label>

                                                                                    <div class="col-xs-12 col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="hidden" name="requestUserId" id="requestUserId" value="22367" class="col-xs-12 col-sm-3" />
                                                                                            <span class="editable editable-click editable-unsaved" id="requestUserIdDiv" style="display: inline-block; background-color: rgba(0, 0, 0, 0);">22367</span>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="hr hr-dotted"></div>

                                                                                <div class="form-group hiden" id="paramDiv">
                                                                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="param3">请求参数:</label>

                                                                                    <div class="col-xs-12 col-sm-9" id = "paramListDiv">
                                                                                        <div class="profile-user-info profile-user-info-striped" >
                                                                                            <div class="profile-info-row">
                                                                                                <div class="profile-info-name"> Username </div>

                                                                                                <div class="profile-info-value">
                                                                                                    <span class="editable" id="username">alexdoe</span>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="profile-info-row">
                                                                                                <div class="profile-info-name"> Username </div>

                                                                                                <div class="profile-info-value">
                                                                                                    <div class="profile-user-info profile-user-info-striped">
                                                                                                        <div class="profile-info-row">
                                                                                                            <div class="profile-info-name"> 名称 </div>

                                                                                                            <div class="profile-info-value">
                                                                                                                <span class="editable editable-click editable-unsaved" id="user_name" style="display: inline-block; background-color: rgba(0, 0, 0, 0);">张</span>
                                                                                                            </div>
                                                                                                        </div>

                                                                                                        <div class="profile-info-row">
                                                                                                            <div class="profile-info-name"> 用户信息 </div>

                                                                                                            <div class="profile-info-value">
                                                                                                                <span class="editable editable-click" id="user" style="display: inline-block;">undefined</span>
                                                                                                            </div>
                                                                                                        </div>
                                                                                                    </div>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="hr hr-dotted"></div>
                                                                            </form>

                                                                            <div class="clearfix form-actions">
                                                                                <div class="col-md-offset-3 col-md-9">
                                                                                    <button class="btn btn-info" type="button" id = "teacherIdBtn">
                                                                                        <i class="icon-ok bigger-110"></i>
                                                                                        Submit
                                                                                    </button>

                                                                                    &nbsp; &nbsp; &nbsp;
                                                                                    <button class="btn" type="reset">
                                                                                        <i class="icon-undo bigger-110"></i>
                                                                                        Reset
                                                                                    </button>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>

                                                            <div class="group">
                                                                <h3 class="accordion-header">请求参数</h3>
                                                                <div>
                                                                    <div class="col-md-12" style="padding:0;position:relative;height:100%;">
                                                                        <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                                            <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                                            <div class="ro" id="json-request" style="padding:0px 25px;white-space: pre-line;">
                                                                                <span data-type="object"><i style="cursor:pointer;" class="fa fa-minus-square-o" onclick="hide(this)"></i>{<br><br>}</span></div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <div class="group">
                                                                    <h3 class="accordion-header">返回值</h3>

                                                                    <div>
                                                                        <div class="col-md-12" style="padding:0;position:relative;height:100%;">
                                                                            <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                                                <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                                                <div class="ro" id="json-response" style="padding:0px 25px;white-space: pre-line;">
                                                                                    <span data-type="object"><i style="cursor:pointer;" class="fa fa-minus-square-o" onclick="hide(this)"></i>{<br><br>}</span></div>
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
                                                                        <span data-type="object"><i style="cursor:pointer;" class="fa fa-minus-square-o" onclick="hide(this)"></i>{<br><br>}</span></div>
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
                                                                        <span data-type="object"><i style="cursor:pointer;" class="fa fa-minus-square-o" onclick="hide(this)"></i>{<br><br>}</span></div>
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
        <#include "/include/rightu-util.ftl" />
        </div>

        <script type="text/javascript">
            $('textarea').numberedtextarea();

            $(document).ready(function(){
                var data = {
                    data : ${interfaceId}
                };
                commonAjaxRequest("${base}/v1/test/interface.json", data, handlerInterface, true, "获取接口信息失败:");
            });

            function refreshInterfaceUrl(){
                var env = $("#env").val();
                $("#interfaceUrl").text(interfaceUrlPrefix.replace("{env}", env));
            }

            var interfaceParam;
            var interfaceUrlPrefix = "http://gateway.{env}.idc.cedu.cn";
            function handlerInterface(resu){
                activeCatelog(resu.interfaceInfo.inter.catelogIndex);
                jsonShow(resu, "json-interface");
                interfaceUrlPrefix += resu.interfaceInfo.inter.interfaceUrl;
                $("#interfaceId").val(resu.interfaceInfo.inter.id);
                $("#interfaceNameDiv").text(resu.interfaceInfo.inter.interfaceName);
                if(resu.interfaceInfo.inter.interfaceType == "PT"){
                    $("#requestUserIdDev").removeClass("hide");
                    var requestLabel = $("#requestUserIdDev").find("label");
                    switch (resu.interfaceInfo.inter.requestUserType){
                        case "teacher":
                            requestLabel.text(requestLabel.text() + "(老师)");
                            break;
                        case "student":
                            requestLabel.text(requestLabel.text() + "(学生)");
                            break;
                    }
                }

                if(resu.interfaceInfo.inter.paramDetail != null){
                    jsonShow(resu.interfaceInfo.inter.paramDetail, "json-interface-detail");
                    var params = JSON.parse(resu.interfaceInfo.inter.paramDetail);

                    if(params != ""){
                        interfaceParam = params;
                        var paramHtmls = genHtml("", params, "0");
                        $("#paramListDiv").html(paramHtmls);

                        initHtml("", params);
                        $("#paramDiv").removeClass("hide");
                    }
                }

                refreshInterfaceUrl();
            }

            $(document).off("click", '.addInputBtn').on('click', '.addInputBtn',cloneInput);
            $(document).off("click", '.delInputBtn').on('click', '.delInputBtn',removeInput);

            jQuery(function($) {
                $('#teacherIdBtn').click(function () {
                    var param = generateJsonParam("#paramListDiv input");
                    jsonShow(param, "json-request");
                    var data = {
                        interfaceId : $("#interfaceId").val(),
                        requestUserId : $("#requestUserId").val(),
                        param : JSON.stringify(param)
                    };
                    commonAjaxRequest("${base}/v1/test/interface/invoke.json", data, handlerTeacherInfo, true, "接口调用异常：:", $("#env").val());
                });

                function handlerTeacherInfo(resu){
                    jsonShow(resu.data, "json-response");
                };

                $(".env").click(function(){
                    $(".env.btn-primary").removeClass("btn-primary");
                    $(this).addClass("btn-primary");
                    $("#env").val($(this).val());

                    refreshInterfaceUrl();
                });

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
            });
        </script>
    </div>
</body>
</html>