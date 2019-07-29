<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>三方赔付处理</title>
    <#include "/include/resource_uncompressed.ftl" />

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
                                                            <input type="hidden" id="guid" >
                                                            <a target="_blank" title="点击链接可查看调用日志" data-rel="tooltip" id = "logUrl" href="">
                                                                <div class="widget-main" id="interfaceUrl"> Took the final exam. Phew! </div>
                                                            </a>
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
                                                                    三方赔付处理页面
                                                                    <small>
                                                                        <i class="icon-double-angle-right"></i>
                                                                        <label id = "interfaceNameDiv">数据依赖赔付前置接口</label>
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
                                                                            <form class="form-horizontal" role="form">
                                                                                <input type="hidden" id="groupApplyId" />
                                                                                <input type="hidden" id="orderCourseId" />

                                                                                <#include "/include/env.ftl" />

                                                                                <div class="hr hr-dotted"></div>

                                                                                <div class="form-group">
                                                                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="isPayVoucherPPrice">是否赔付优惠:</label>

                                                                                    <div class="col-xs-12 col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <label class="pull-left inline"  title="" data-rel="tooltip" >
                                                                                                <input id="isPayVoucherPrice" type="checkbox" class="ace ace-switch ace-switch-5" value="0" />
                                                                                                <span class="lbl"></span>
                                                                                            </label>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="studentAmount">赔付家长金额:</label>

                                                                                    <div class="col-xs-12 col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="input"  id="studentAmount" value="0" class="col-xs-12 col-sm-3" />
                                                                                            <span class="input-group-btn">
                                                                            <button type="button" class="btn btn-purple btn-xs" id="mockFinishBtn">
                                                                                赔付前置
                                                                                <i class="icon-search icon-on-right bigger-110"></i>
                                                                            </button>
                                                                        </span>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="teacherAmount">赔付老师金额:</label>

                                                                                    <div class="col-xs-12 col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="input"  id="teacherAmount" value="0" class="col-xs-12 col-sm-3" />
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="assistantAmount">平台金额:</label>

                                                                                    <div class="col-xs-12 col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="input"  id="assistantAmount" value="0" class="col-xs-12 col-sm-3" />
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="remark">备注:</label>

                                                                                    <div class="col-xs-12 col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="input"  id="remark" value="我是备注" class="col-xs-12 col-sm-3" />
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="dependOnCourseNum">依赖课次:</label>

                                                                                    <div class="col-xs-12 col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="input"  id="dependOnCourseNum" value="0" class="col-xs-12 col-sm-3" />
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="taxAmount">税费:</label>

                                                                                    <div class="col-xs-12 col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="input"  id="taxAmount" value="0" class="col-xs-12 col-sm-3" />
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="hr hr-dotted"></div>
                                                                                <div class="hr hr-dotted"></div>
                                                                            </form>

                                                                            <div class="clearfix form-actions">
                                                                                <div class="col-md-offset-3 col-md-9">
                                                                                    <button class="btn btn-info" style="border-radius: 8px" type="button" id = "submitBtn">
                                                                                        <i class="icon-ok bigger-110"></i>
                                                                                        Submit
                                                                                    </button>

                                                                                    &nbsp; &nbsp; &nbsp;
                                                                                    <button class="btn" style="border-radius: 8px" type="reset" id="resetBtn">
                                                                                        <i class="icon-undo bigger-110"></i>
                                                                                        摆 设
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
                                                                                <span data-type="object"><i style="cursor:pointer;" class="fa icon-minus" onclick="hide(this)"></i>{<br><br>}</span></div>
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
                                                                                    <span data-type="object"><i style="cursor:pointer;" class="fa icon-minus" onclick="hide(this)"></i>{<br><br>}</span></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
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
            var logUrl = "http://172.22.12.14:5601/app/logtrail#/?q=env_type:%20%22{env}%22%20%26%26%20guid:%20%22{guid}%22&t=Now&i=rsyslog-app*&_g=()&h={server}";

            function refreshInterfaceUrl(){
                var env = $("#env").val();
                var guid = generateGuid();

                var interfaceUrlPrefix = "http://gateway.{env}.idc.cedu.cn";
                var interfaceUrl = "/svc/pi/v4/order_course/action/process_freeze.json"
                interfaceUrlPrefix += interfaceUrl + "?guid={guid}";
                var url = interfaceUrlPrefix.replace("{env}", env);
                url = url.replace("{guid}", guid);

                var logTargetUrl = logUrl.replace("{env}", env);
                logTargetUrl = logTargetUrl.replace("{guid}", guid);
                logTargetUrl = logTargetUrl.replace("{server}", getServer(interfaceUrl))

                $("#interfaceUrl").text(url);
                $("#guid").val(guid);
                $("#logUrl").attr("href", logTargetUrl);
            }

            $("#mockFinishBtn").click(preFreeze);

            function preFreeze(){
                var orderCourseId = $("#orderCourseId").val();
                var data = {
                    order_course_id : new Number(orderCourseId),
                    student_amount : new Number($("#studentAmount").val()),
                    is_deduct_reduce : ($("#isPayVoucherPrice").val() == 1)
                }

                var request = {
                    url : "${base}/v1/order_course/freeze_apply/mock.json",
                    data : data,
                    handlerFunc : handlerMockResult,
                    isASync : true,
                    failTitle :"赔付前置失败:",
                    env : $("#env").val(),
                    otherData : null,
                    guid : $("#guid").val()
                };

                commonAjaxRequest(request);
            }

            function handlerMockResult(resu){
                $("#teacherAmount").val(resu.teacher_amount);
                $("#assistantAmount").val(resu.company_amount);
                $("#taxAmount").val(resu.tax_amount);
                $("#dependOnCourseNum").val(resu.depend_on_course_num);
                $("#studentAmount").val(resu.student_amount);
            }

            $("#submitBtn").click(function(){
                var groupApplyId = $("#groupApplyId").val();
                var data = {
                    group_apply_id : new Number(groupApplyId),
                    student_amount : new Number($("#studentAmount").val()),
                    teacher_amount : new Number($("#teacherAmount").val()),
                    assistant_amount : new Number($("#assistantAmount").val()),
                    tax_amount : new Number($("#taxAmount").val()),
                    process_content : $("#remark").val(),
                    is_paid_voucher_price : ($("#isPayVoucherPrice").val() == "1"),
                    depend_on_course_num : new Number($("#dependOnCourseNum").val())
                }

                jsonShow(data, "json-request");
                var request = {
                    url : "${base}/v1/order_course/freeze_apply/process.json",
                    data : data,
                    handlerFunc : handlerProcessResult,
                    isASync : true,
                    failTitle :"赔付失败:",
                    env : $("#env").val(),
                    otherData : null,
                    guid : $("#guid").val()
                };

                commonAjaxRequest(request);
            });

            function handlerProcessResult(resu){
                jsonShow(resu, "json-response");
            }

            function getServer(url){
                if(url.indexOf("/") == 0){
                    url = url.substr(1, url.length);
                }

                url = url.substr(0, url.indexOf("/"));

                return url;
            }

            $(document).on("change", "#isPayVoucherPrice", function(){
                if($(this).val() == 0){
                    $(this).val(1);
                }else{
                    $(this).val(0);
                }
            });

                $(document).ready(function(){
                    var data = {
                        data : ${orderCourseId?c}
                    };

                    $("#orderCourseId").val(${orderCourseId?c});

                    var env = '${env}';
                    $("#env").val(env);
                    $(".env.btn-primary").removeClass("btn-primary");
                    $(".env[value='" + env + "']").addClass("btn-primary");

                    var request = {
                        url : "${base}/v1/order_course/freeze_apply/query_by_order_course_id.json",
                        data : data,
                        handlerFunc : handlerInterface,
                        isASync : true,
                        failTitle :"获取接口信息失败:",
                        env : $("#env").val(),
                        otherData : null,
                        guid : $("#guid").val()
                    };

                    commonAjaxRequest(request);
                });

                function handlerInterface(resu){
                    if(resu.resultList == null){
                        $.gritter.add({
                            title : "我是提示",
                            text : "没有找到该课程的赔付申请",
                            class_name : 'gritter-error gritter-center'
                        });
                        return;
                    }

                    $("#groupApplyId").val(resu.resultList.id);
                    refreshInterfaceUrl();

                    preFreeze();
                }

                jQuery(function($) {
                    $(".chosen-select").chosen();
                    $("#requestUserType_chosen").css("width", "100px");

                    $(".env").click(function(){
                        $(".env.btn-primary").removeClass("btn-primary");
                        $(this).addClass("btn-primary");
                        $("#env").val($(this).val());

                        refreshInterfaceUrl();
                    });

                    $('#username').editable({
                        type: 'text',
                        name: 'username'
                    });

                    $('[data-rel=tooltip]').tooltip();
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