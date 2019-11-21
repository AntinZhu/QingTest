<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>俺是404</title>
    <#include "/include/resource.ftl" />
    <link href="${base}/static/css/json/base.css" rel="stylesheet">
    <link href="${base}/static/css/json/jquery.numberedtextarea.css" rel="stylesheet">

    <script src="${base}/static/js/json/jquery.message.js"></script>
    <script src="${base}/static/js/json/jquery.json.js"></script>
    <script src="${base}/static/js/json/json2.js"></script>
    <script src="${base}/static/js/json/jsonlint.js"></script>
    <script src="${base}/static/js/json/jquery.numberedtextarea.js"></script>

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
                    <div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->

                            <div class="error-container">
                                <div class="well">
                                    <h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="icon-sitemap"></i>
												404
											</span>
                                        Page Not Found
                                    </h1>

                                    <hr />
                                    <h3 class="lighter smaller">时空错乱，让你穿越至此，放下键盘和鼠标，放空自己，闻一闻这自由的味道！该下班了，少年！！！！！</h3>

                                    <div>
                                        <div class="space"></div>
                                        <h4 class="smaller">看看下面是不是你要找的:</h4>

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
                </div><!-- /.page-content -->
            </div><!-- /.main-container -->

        <#include "/include/righttool-sidebar.ftl" />
        </div>
</body>
</body>
</html>