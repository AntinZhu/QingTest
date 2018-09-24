<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Hello World!</title>
    <#include "/include/resource.ftl" />
    <link href="${base}/static/css/json/base.css" rel="stylesheet">
    <link href="${base}/static/css/json/jquery.numberedtextarea.css" rel="stylesheet">
    <link href="${base}/static/css/json/font-awesome.min.css" rel="stylesheet">

    <script src="${base}/static/js/json/hm.js"></script>
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
            <main class="row-fluid" style="height:100%;min-height:550px;">
                <div class="col-md-12" style="padding:0px;height:100%;">
                    <div class="page-content">
                        <div class="page-header">
                            <h1>
                                接口测试
                                <small>
                                    <i class="icon-double-angle-right"></i>
                                    <label id = "interfaceNameDiv">Common form elements and layouts</label>
                                </small>
                            </h1>
                        </div><!-- /.page-header -->

                        <div class="row">
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

                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="balanceAmount">第三方支付路径</label>
                                        <div class="col-xs-12 col-sm-6">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>支付方式</th>
                                                        <th>关联记录</th>
                                                        <th class="hidden-480">Status</th>
                                                        <th>操作</th>
                                                    </tr>
                                                    </thead>

                                                    <tbody id="payWayList">
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="hr hr-dotted"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
            <div class="ace-settings-container" id="ace-settings-container">
                <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                    <i class="icon-cog bigger-150"></i>
                </div>

                <div class="ace-settings-box" id="ace-settings-box">
                    <div>
                        <div class="pull-left">
                            <select id="skin-colorpicker" class="hide">
                                <option data-skin="default" value="#438EB9">#438EB9</option>
                                <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                                <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                                <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                            </select>
                        </div>
                        <span>&nbsp; Choose Skin</span>
                    </div>

                    <div>
                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
                        <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
                    </div>

                    <div>
                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
                        <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
                    </div>

                    <div>
                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
                        <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
                    </div>

                    <div>
                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
                        <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
                    </div>

                    <div>
                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
                        <label class="lbl" for="ace-settings-add-container">
                            Inside
                            <b>.container</b>
                        </label>
                    </div>
                </div>
            </div><!-- /#ace-settings-container -->
        </div>

        <script type="text/javascript">
        </script>
    </div>
</body>
</html>