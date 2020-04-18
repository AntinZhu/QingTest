<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>新增或编辑用户</title>
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
    <script src="${base}/static/assets/js/uncompressed/fuelux/fuelux.tree.js"></script>
    <script src="${base}/static/assets/js/uncompressed/fuelux/data/fuelux.tree-sampledata.js"></script>

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
                                                                    <h3 class="accordion-header">用户参数填写</h3>

                                                                    <div>
                                                                        <div class="col-xs-12">
                                                                            <form class="form-horizontal">
                                                                                <input type="hidden" id = "id" />
                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="userName">用户名:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-3" type="text" id="userName" placeholder="用户名..." />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="userIp">用户IP:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-3" type="text" id="userIp" placeholder="用户IP..." />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="ipStatus">黑名单:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <label class="pull-left inline"  title="禁用该IP" data-rel="tooltip" >
                                                                                                <input id="ipStatus" type="checkbox" class="ace ace-switch ace-switch-5" value="0" />
                                                                                                <span class="lbl"></span>
                                                                                            </label>
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>
                                                                            </form>

                                                                            <div class="hr hr-dotted"></div>
                                                                            <div class="hr hr-dotted"></div>

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
            <#if ((id)!0) gt 0>
                $("#id").val(${bean.id});
                $("#userName").val("${bean.userName}");
                $("#userIp").val("${bean.userIp}");
                <#if ((bean.ipStatus)!"enable") == "enable">
                    $("#ipStatus").val(0);
                <#else>
                    $("#ipStatus").val(1);
                    $("#ipStatus").attr("checked", "checked");
                </#if>
            <#else>
            </#if>
            });

            $("#ipStatus").click(function(){
                if($(this).val() == "0"){
                    $(this).val(1);
                }else{
                    $(this).val(0);
                }
            });

            $("#saveBtn").click(function () {
                var userName = $("#userName").val();
                if(isStringEmpty(userName)){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "用户名不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }
                var userIp = $("#userIp").val();
                if(isStringEmpty(userIp)){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "用户IP不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }

                var data = {
                    id : $("#id").val(),
                    userName : userName,
                    userIp : userIp,
                    deleted : false,
                    ipStatus: $("#ipStatus").val() == "1" ? "black":"enable"
                };

                var request = {
                    url : "${base}/v1/user/add.json",
                    data : data,
                    handlerFunc : handlerSave,
                    isASync : true,
                    failTitle :"保存失败:"
                };

                commonAjaxRequest(request);

            });

            function handlerSave(resu){
                $.gritter.add({
                    title : '提示:',
                    text : '添加成功',
                    class_name : 'gritter-info gritter-center'
                });
            }

            jQuery(function($) {
            });
        </script>
    </div>
</body>
</html>