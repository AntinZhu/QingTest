<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Hello World!</title>
<#include "/include/resource.ftl" />
    <link href="${base}/static/css/json/base.css" rel="stylesheet">
    <link href="${base}/static/css/json/jquery.numberedtextarea.css" rel="stylesheet">

    <script src="${base}/static/js/json/jquery.message.js"></script>
    <script src="${base}/static/js/json/jquery.json.js"></script>
    <script src="${base}/static/js/json/json2.js"></script>
    <script src="${base}/static/js/json/jsonlint.js"></script>
    <script src="${base}/static/js/json/jquery.numberedtextarea.js"></script>

    <style>
        .center {
            text-align:center;
        }
        .center [class*="col-"] {
            margin-top:2px;
            margin-bottom:2px;
            padding-top:4px;
            padding-bottom:4px;

            position:relative;
            text-overflow:ellipsis;
        }
        .center [class*="col-"]  span{
            position:relative;
            z-index:2;

            display: inline-block;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;

            width: 100%;
        }
        .center [class*="col-"]:before {
            position:absolute;
            top:0; bottom:0;
            left:2px; right:2px;
            content:"";
            display:block;
            border:1px solid #DDD;
            z-index: 1;
        }

        .center [class*="col-"]:hover:before {
            background-color:#FCE6A6;
            border-color:#EFD27A;
        }
    </style>


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

                            <div class="center">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <span>.col-xs-12</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-1">
                                        <span>.col-xs-1</span>
                                    </div>

                                    <div class="col-xs-11">
                                        <span>.col-xs-11</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 col-sm-2">
                                        <span>.col-xs-6.col-sm-2</span>
                                    </div>

                                    <div class="col-xs-6 col-sm-10">
                                        <span>.col-xs-6.col-sm-10</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-12 col-lg-6">
                                        <span>.col-xs-12.col-lg-6</span>
                                    </div>

                                    <div class="col-xs-12 col-lg-6">
                                        <span>.col-xs-12.col-lg-6</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-4">
                                        <span>.col-xs-4</span>
                                    </div>

                                    <div class="col-xs-8">
                                        <span>.col-xs-8</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-5">
                                        <span>.col-xs-5</span>
                                    </div>

                                    <div class="col-xs-7">
                                        <span>.col-xs-7</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6">
                                        <span>.col-xs-6</span>
                                    </div>

                                    <div class="col-xs-6">
                                        <span>.col-xs-6</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-7">
                                        <span>.col-xs-7</span>
                                    </div>

                                    <div class="col-xs-5">
                                        <span>.col-xs-5</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-8">
                                        <span>.col-xs-8</span>
                                    </div>

                                    <div class="col-xs-4">
                                        <span>.col-xs-4</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-9">
                                        <span>.col-xs-9</span>
                                    </div>

                                    <div class="col-xs-3">
                                        <span>.col-xs-3</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-10">
                                        <span>.col-xs-10</span>
                                    </div>

                                    <div class="col-xs-2">
                                        <span>.col-xs-2</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-11">
                                        <span>.col-xs-11</span>
                                    </div>

                                    <div class="col-xs-1">
                                        <span>.col-xs-1</span>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-12">
                                        <span>.col-xs-12</span>
                                    </div>
                                </div>
                            </div>

                            <!-- PAGE CONTENT ENDS -->
                        </div><!-- /.col -->
                    </div><!-- /.row -->
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