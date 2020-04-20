<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>用户管理</title>
    <#include "/include/resource.ftl" />
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
                <div class="breadcrumbs" id="breadcrumbs">
                    <script type="text/javascript">
                        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                    </script>

                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home home-icon"></i>
                            <a href="#">系统设置</a>
                        </li>
                        <li class="active">服务通用配置管理</li>
                    </ul><!-- .breadcrumb -->

                    <div class="nav-search" id="nav-search">
                        <span class="input-icon">
                            <input type="text" placeholder="Search ..." class="nav-search-input" id="userSearch" autocomplete="off" />
                            <i class="icon-search nav-search-icon"></i>
                        </span>
                    </div><!-- #nav-search -->
                </div>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            服务通用配置管理
                            <small>
                                <i class="icon-double-angle-right"></i>
                                <label id = "interfaceNameDiv">系统内通用配置</label>
                            </small>
                        </h1>
                    </div><!-- /.page-header -->

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="table-responsive">
                                <span class="col-sm-12" style="margin-bottom: 10px;">
                                    <a class="btn btn-link" target="_blank" href="${base}/v1/test/config/add_page?catelogIndex=3-8">
                                        <i class="icon-plus-sign bigger-220 green"></i>
                                    </a>
                                    <label class="pull-right inline"  title="开启时可显示未启用的规则" data-rel="tooltip" >
                                        <small class="muted">显示未启用:</small>

                                        <input id="showDeletedBtn" type="checkbox" class="ace ace-switch ace-switch-5" value="0" />
                                        <span class="lbl"></span>
                                    </label>
                                </span><!-- /span -->

                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>配置项</th>
                                            <th>配置内容</th>
                                            <th>描述</th>
                                            <th>操作</th>
                                        </tr>
                                    </thead>

                                    <tbody  id= "ruleListBody">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                </div>

            <#include "/include/righttool-sidebar.ftl" />

<script type="text/javascript">
    $(document).ready(function(){
        refreshPage();
    });

    document.addEventListener('visibilitychange',function(){ //浏览器切换事件
        if(document.visibilityState!='hidden') { //状态判断
            refreshPage();
        }
    });

    function refreshPage(){
        var request = {
            url : "${base}/v1/test/config/list.json",
            handlerFunc : handlerInterface,
            isASync : true,
            failTitle :"获取信息失败:"
        };

        commonAjaxRequest(request);
    }

    var cacheResult;
    var template_HTML = '<tr class="ruleLine">' +
            '<td>{id}</td>' +
            '<td>{configKey}</td>' +
            '<td><a href="#">{configValue}</a></td>' +
            '<td>{description}</td>' +
            '<td>' +
                '<input type="hidden" id = "id" value="{id}"/> ' +
                '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">' +
                    '<a class="green" target="_blank" href="${base}/v1/test/config/add_page?catelogIndex=3-8&id={id}"><i class="icon-pencil bigger-130"></i></a>' +
                '</div>' +
            '</td></tr>';
    function handlerInterface(resu) {
        var resultList = resu.resultList;
        cacheResult = resultList;
        cacheResult.reverse();
        showItems(cacheResult);
    };

    function showItems(resultList){
        if (resultList != null && resultList.length > 0) {
            $("#ruleListBody").html("");
            var search = $("#userSearch").val();
            for (var resultIdx in resultList) {
                var result = resultList[resultIdx];

                if(!isStringEmpty(search)){
                    if(result.configKey.indexOf(search) == -1 && result.configKey.indexOf(search) == -1){
                        continue;
                    }
                }

                var html = template_HTML;
                html = html.replace(new RegExp("{id}", "gm"), result.id);
                html = html.replace(new RegExp("{configKey}", "gm"), result.configKey);
                html = html.replace(new RegExp("{configValue}", "gm"), result.configValue);
                html = html.replace(new RegExp("{description}", "gm"), result.description == null?"":result.description);

                $("#ruleListBody").append(html);
            }
        }
    }

    function handleDefaultResult() {
        refreshPage();
    }

    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();

        $("#userSearch").change(function(){
            showItems(cacheResult);
        });
    });
</script>
    </div>
</body>
</html>