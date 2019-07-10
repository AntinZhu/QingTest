<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>用户IP上传页面</title>
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
                            <div class="col-xs-12">
                                <!-- PAGE CONTENT BEGINS -->

                                <div class="error-container">
                                    <div class="well">
                                        <h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="icon-sitemap"></i>
												200
											</span>
                                        </h1>

                                        <hr />
                                        <h3 class="lighter smaller"> <b style="color: red;">${userName}</b>，你的IP已上传，欢迎来到API TEST</h3>

                                        <div>
                                            <div class="space"></div>
                                            <h4 class="smaller">你可以尝试以下功能：:</h4>

                                            <ul class="list-unstyled spaced inline bigger-110 margin-15">
                                                <li>
                                                    <i class="icon-hand-right blue"></i>
                                                    <a href="${base}/v1/utils/es/student-teacher?catelogIndex=4-5">Es 数据修改</a>
                                                </li>

                                                <li>
                                                    <i class="icon-hand-right blue"></i>
                                                    <a href="${base}/v1/order/student/add_order_page?catelogIndex=1-1">
                                                        快速下单
                                                    </a>
                                                </li>

                                                <li>
                                                    <i class="icon-hand-right blue"></i>
                                                    <a href="${base}/v1/common/haha?catelogIndex=2-0">
                                                        接口调用
                                                    </a>
                                                </li>

                                                <li>
                                                    <i class="icon-hand-right blue"></i>
                                                    <a href="${base}/v1/common/crond_task_page?catelogIndex=4-2">
                                                        定时任务调用
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>

                                        <hr />
                                        <div class="space"></div>

                                        <div class="center">
                                            <a href="#" class="btn btn-grey">
                                                <i class="icon-arrow-left"></i>
                                                Go Back
                                            </a>

                                            <a href="#" class="btn btn-primary">
                                                <i class="icon-dashboard"></i>
                                                Dashboard
                                            </a>
                                        </div>
                                    </div>
                                </div><!-- PAGE CONTENT ENDS -->
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.page-header -->
                </div>
                </div>

            <#include "/include/righttool-sidebar.ftl" />

<script type="text/javascript">
    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();

        $(document).ready(function(){
            var userIp = $("#qing_user_ip").val();
            var content = {
                msgtype : "markdown",
                markdown :{
                    content : "用户IP上传\n                >用户IP: <font color=\"comment\">" + userIp + "</font> \n                >用户名: <font color=\"comment\">${userName}</font> "
                }
            };

            commonAjaxRequest("${base}/v1/common/wx_notify.json?content=" + encodeURI(JSON.stringify(content)), null, handlerParamSave, true, "IP上传出错:");
        });

        function handlerParamSave(){
        }
    });
</script>
    </div>
</body>
</html>