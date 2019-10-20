<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Mock规则集合</title>
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
                        <li class="active">mock规则</li>
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
                            Mock规则列表
                            <small>
                                <i class="icon-double-angle-right"></i>
                                <label id = "interfaceNameDiv">目前支持的Mock规则列表</label>
                            </small>
                        </h1>
                    </div><!-- /.page-header -->

                    <div class="row">
                        <div class="col-xs-12">
                            <div class="table-responsive">
                                <input type="hidden" id="mockType" />
                                <span class="col-sm-12" style="margin-bottom: 10px;">
                                    <a class="btn btn-link" target="_blank" href="${base}/v1/mock/rule/edit?catelogIndex=3-8-1&mockType=${mockType}">
                                        <i class="icon-plus-sign bigger-240 green"></i>
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
                                            <th>mock_type</th>
                                            <th>规则描述</th>
                                            <th>规则类型</th>
                                            <th>规则</th>
                                            <th>规则优先级</th>
                                            <th>延迟毫秒数</th>
                                            <th>返回值</th>
                                            <th>是否是默认</th>
                                            <th>是否启用</th>
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

    function refreshPage(){
        var request = {
            url : "${base}/v1/mock/rule/list.json",
            data : {"data":'${mockType}'},
            handlerFunc : handlerInterface,
            isASync : true,
            failTitle :"获取信息失败:"
        };

        commonAjaxRequest(request);
    }

    var template_HTML = '<tr class="ruleLine {ruleClass}">' +
            '<td><a href="#">{mockType}</a></td>' +
            '<td>{remark}</td>' +
            '<td>{ruleType}</td>' +
            '<td>{ruleValue}</td>' +
            '<td>{ruleOrderNum}</td>' +
            '<td>{delayMs}</td>' +
            '<td>{resp}</td>' +
            '<td><label><input id="{id}" class="ace ace-switch ace-switch-6 default_rule" type="checkbox" value="{isDefault}" {default_checked} />    <span class="lbl"></span>    </label></td>' +
            '<td class="hidden-480"><label><input id="{id}" class="ace ace-switch ace-switch-6 enable_rule" type="checkbox" value="{isEnable}" {enable_checked} />    <span class="lbl"></span>    </label></td>' +
            '<td>' +
                '<input type="hidden" id = "id" value="{id}"/> ' +
                '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">' +
                    '<a class="green" target="_blank" href="${base}/v1/mock/rule/edit?catelogIndex=3-8-1&id={id}"><i class="icon-pencil bigger-130"></i></a>' +
                '</div>' +
            '</td></tr>';
    function handlerInterface(resu) {
        var resultList = resu.resultList;
        if (resu.resultList != null && resu.resultList.length > 0) {
            $("#ruleListBody").html("");
            var showDeleted = $("#showDeletedBtn").val() == "1";
            for (var resultIdx in resultList) {
                var result = resultList[resultIdx];

                var html = template_HTML;
                html = html.replace(new RegExp("{id}", "gm"), result.id);
                html = html.replace(new RegExp("{mockType}", "gm"), result.mockType);
                html = html.replace(new RegExp("{remark}", "gm"), result.remark);
                html = html.replace(new RegExp("{ruleType}", "gm"), ruleTypeDesc(result.ruleType));
                html = html.replace(new RegExp("{ruleValue}", "gm"), ruleValueDesc(result.ruleType, result.ruleValue));
                html = html.replace(new RegExp("{ruleOrderNum}", "gm"), result.ruleOrderNum);
                html = html.replace(new RegExp("{delayMs}", "gm"), result.delayMs);
                html = html.replace(new RegExp("{resp}", "gm"), result.resp);
                html = html.replace(new RegExp("{isDefault}", "gm"), result.default ? "1" : "0");
                var defaultChecked = "";
                if(result.default){
                    defaultChecked = 'checked="checked"';
                }
                html = html.replace(new RegExp("{default_checked}", "gm"), defaultChecked);
                if(result.deleted){
                    if(showDeleted){
                        html = html.replace(new RegExp("{ruleClass}", "gm"), "rule_deleted");
                    }else{
                        html = html.replace(new RegExp("{ruleClass}", "gm"), "hide rule_deleted");
                    }
                    html = html.replace(new RegExp("{enable_checked}", "gm"),  '');
                    html = html.replace(new RegExp("{isEnable}", "gm"), "0");
                }else{
                    html = html.replace(new RegExp("{ruleClass}", "gm"), "");
                    html = html.replace(new RegExp("{enable_checked}", "gm"),  'checked="checked"');
                    html = html.replace(new RegExp("{isEnable}", "gm"), "1");
                }

                $("#ruleListBody").append(html);
            }
        }
    };

    function ruleTypeDesc(ruleType){
        if(ruleType == "VALUE_MATCH"){
            return "值匹配";
        }else if(ruleType == "NUMBER_RANGE"){
            return "数值范围匹配";
        }

        return "";
    }

    function ruleValueDesc(ruleType, ruleValue){
        ruleValue = JSON.parse(ruleValue);
        if(ruleType == "VALUE_MATCH"){
            return "值为：" + ruleValue.value;
        }else if(ruleType == "NUMBER_RANGE"){
            var desc = "";
            if(ruleValue.start != null){
                desc += "大于等于" + ruleValue.start;
            }
            if(ruleValue.end != null){
                if(ruleValue.start != null){
                    desc += "&&";
                }
                desc += "小于" + ruleValue.end;
            }
            return desc;
        }

        return "";
    }

    $(document).on("click", '.default_rule', function(){
        var id = $(this).attr("id");
        var defaultValue;

        var isDefault = $(this).val();
        if(isDefault == 0){
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
            url : "${base}/v1/mock/rule/set_default.json",
            data : data,
            handlerFunc : handleDefaultResult,
            isASync : true,
            failTitle :"保存出错:"
        };

        commonAjaxRequest(request);
    });

    function handleDefaultResult(){
        refreshPage();
    }

    $(document).on("click", '.enable_rule', function(){
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
            url : "${base}/v1/mock/rule/set_deleted.json",
            data : data,
            handlerFunc : handleDefaultResult,
            isASync : true,
            failTitle :"保存出错:"
        };

        commonAjaxRequest(request);
    });

    $("#showDeletedBtn").click(function(){
        var nowValue = $(this).val();
        if(nowValue == 0){
            $(this).val(1);

            $(".rule_deleted").removeClass("hide");
        }else{
            $(this).val(0);
            $(".rule_deleted").addClass("hide");
        }
    });

    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();
    });
</script>
    </div>
</body>
</html>