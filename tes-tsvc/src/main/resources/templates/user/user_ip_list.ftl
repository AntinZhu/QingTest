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
                        <li class="active">用户管理</li>
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
                            系统设置
                            <small>
                                <i class="icon-double-angle-right"></i>
                                <label id = "interfaceNameDiv">已被允许访问的用户IP列表</label>
                            </small>
                        </h1>
                    </div><!-- /.page-header -->

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="table-responsive">
                                <span class="col-sm-12" style="margin-bottom: 10px;">
                                    <a class="btn btn-link" target="_blank" href="${base}/v1/user/edit?catelogIndex=3-8">
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
                                            <th>用户名</th>
                                            <th>用户IP</th>
                                            <th>状态</th>
                                            <th>黑名单</th>
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
            url : "${base}/v1/user/list.json",
            handlerFunc : handlerInterface,
            isASync : true,
            failTitle :"获取信息失败:"
        };

        commonAjaxRequest(request);
    }

    var cacheResult;
    var template_HTML = '<tr class="ruleLine {userClass}">' +
            '<td>{userName}</td>' +
            '<td><a href="#">{userIp}</a></td>' +
            '<td class="hidden-480"><label><input id="{id}" class="ace ace-switch ace-switch-6 enable_user" type="checkbox" value="{isEnable}" {enable_checked} />    <span class="lbl"></span>    </label></td>' +
            '<td class="hidden-480"><label><input userId="{id}" class="ace ace-switch ace-switch-6 black_user" type="checkbox" value="{isBlack}" {black_checked} />    <span class="lbl"></span>    </label></td>' +
            '<td>' +
                '<input type="hidden" id = "id" value="{id}"/> ' +
                '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">' +
                    '<a class="green" target="_blank" href="${base}/v1/user/edit?catelogIndex=3-8&id={id}"><i class="icon-pencil bigger-130"></i></a>' +
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
            var showDeleted = $("#showDeletedBtn").val() == "1";
            for (var resultIdx in resultList) {
                var result = resultList[resultIdx];

                if(!isStringEmpty(search)){
                    if(result.userName.indexOf(search) == -1 && result.userIp.indexOf(search) == -1){
                        continue;
                    }
                }

                var html = template_HTML;
                html = html.replace(new RegExp("{id}", "gm"), result.id);
                html = html.replace(new RegExp("{userIp}", "gm"), result.userIp);
                html = html.replace(new RegExp("{userName}", "gm"), result.userName);
                if(result.deleted){
                    if(showDeleted){
                        html = html.replace(new RegExp("{userClass}", "gm"), "user_deleted");
                    }else{
                        html = html.replace(new RegExp("{userClass}", "gm"), "hide user_deleted");
                    }
                    html = html.replace(new RegExp("{enable_checked}", "gm"),  '');
                    html = html.replace(new RegExp("{isEnable}", "gm"), "0");
                }else{
                    html = html.replace(new RegExp("{userClass}", "gm"), "");
                    html = html.replace(new RegExp("{enable_checked}", "gm"),  'checked="checked"');
                    html = html.replace(new RegExp("{isEnable}", "gm"), "1");
                }

                if(result.ipStatus == "enable"){
                    html = html.replace(new RegExp("{black_checked}", "gm"),  '');
                    html = html.replace(new RegExp("{isBlack}", "gm"), "0");
                }else{
                    html = html.replace(new RegExp("{black_checked}", "gm"),  'checked="checked"');
                    html = html.replace(new RegExp("{isBlack}", "gm"), "1");
                }

                $("#ruleListBody").append(html);
            }
        }
    }

    $(document).on("click", '.enable_user', function(){
        var id = $(this).attr("id");
        var defaultValue;

        var isEnable = $(this).val();
        if(isEnable == 0){
            defaultValue = false;
            $(this).val(1);
        }else{
            defaultValue = true;
            $(this).val(0);
        }

        var data = {
            id : id,
            bool : defaultValue
        };

        var request = {
            url : "${base}/v1/user/isDeleted/set.json",
            data : data,
            handlerFunc : handleDefaultResult,
            isASync : true,
            failTitle :"保存出错:"
        };

        commonAjaxRequest(request);
    });

    $(document).on("click", '.black_user', function(){
        var id = $(this).attr("userId");
        var defaultValue;

        var isEnable = $(this).val();
        if(isEnable == 0){
            defaultValue = true;
            $(this).val(1);
        }else{
            defaultValue = false;
            $(this).val(0);
        }

        var data = {
            id : id,
            bool : defaultValue
        };

        var request = {
            url : "${base}/v1/user/isBlack/set.json",
            data : data,
            handlerFunc : handleDefaultResult,
            isASync : true,
            failTitle :"保存出错:"
        };

        commonAjaxRequest(request);
    });

    function handleDefaultResult() {
        refreshPage();
    }

    $("#showDeletedBtn").click(function(){
        var nowValue = $(this).val();
        if(nowValue == 0){
            $(this).val(1);

            $(".user_deleted").removeClass("hide");
        }else{
            $(this).val(0);
            $(".user_deleted").addClass("hide");
        }
    });

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