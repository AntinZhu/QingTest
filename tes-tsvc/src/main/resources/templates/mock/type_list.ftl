<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Mock类型集合</title>
    <#include "/include/resource.ftl" />
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
                <div class="breadcrumbs" id="breadcrumbs">
                    <script type="text/javascript">
                        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                    </script>

                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home home-icon"></i>
                            <a href="#">系统设置</a>
                        </li>
                        <li class="active">mock配置</li>
                        <li class="active">mock类型</li>
                    </ul><!-- .breadcrumb -->

                    <div class="nav-search" id="nav-search">
                        <span class="input-icon">
                            <input type="text" placeholder="Search ..." class="nav-search-input" id="task_search" autocomplete="off" value="${search!""}" />
                            <i class="icon-search nav-search-icon"></i>
                        </span>
                    </div><!-- #nav-search -->
                </div>

                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            Mock类型列表
                            <small>
                                <i class="icon-double-angle-right"></i>
                                <label id = "interfaceNameDiv">目前支持的Mock类型列表</label>
                            </small>
                        </h1>
                    </div><!-- /.page-header -->

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th>mock_type</th>
                                            <th>名称</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                            </tr>
                                    </thead>

                                    <tbody  id= "typeListBody">

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

    function refreshPage(){
        var request = {
            url : "${base}/v1/mock/type/list.json",
            data : null,
            handlerFunc : handlerInterface,
            isASync : true,
            failTitle :"获取信息失败:"
        };

        commonAjaxRequest(request);
    }

    var template_HTML = '<tr><td><a href="#">{mockType}</a></td><td>{mockName}</td><td class="hidden-480">{status}</td><td><input type="hidden" id = "id" value="{id}"/> <input type="hidden" id = "mockType" value="{mockType}"/> <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons"><a class="green" href="#"><i class="icon-pencil bigger-130"></i></a><a class="red" href="#"><i class="icon-trash bigger-130 _delete"></i></a></div></td></tr>';
    function handlerInterface(resu) {
        var resultList = resu.resultList;
        if (resu.resultList != null && resu.resultList.length > 0) {
            for (var resultIdx in resultList) {
                var result = resultList[resultIdx];

                var html = template_HTML;
                html = html.replace(new RegExp("{id}", "gm"), result.id);
                html = html.replace(new RegExp("{mockType}", "gm"), result.mockType);
                html = html.replace(new RegExp("{mockName}", "gm"), result.mockName);
                html = html.replace(new RegExp("{status}", "gm"), result.isDeleted ? "已删除" : "正常");

                $("#typeListBody").append(html);
            }
        }
    };

    $("._delete").click(function(){
        var id = $(this).parent().parent().parent().children("input[id='id']")

        var request = {
            url : "${base}/v1/mock/type/delete.json",
            data : {"data":id},
            handlerFunc : handlerDeleteSucc,
            isASync : true,
            failTitle :"删除Mock类型失败:"
        };

        commonAjaxRequest(request);
    });

    function handlerDeleteSucc(resu, data){
        $.gritter.add({
            title : '提示:',
            text : "删除成功",
            class_name : 'gritter-info gritter-center'
        });
    }

    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();
    });
</script>
    </div>
</body>
</html>