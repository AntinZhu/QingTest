<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>查找接口</title>
    <#include "/include/resource.ftl" />
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
                        <h1>
                            接口查找
                            <small>
                                <i class="icon-double-angle-right"></i>
                                <label id = "interfaceNameDiv">快速查找到你想要的接口</label>
                            </small>
                        </h1>
                    </div><!-- /.page-header -->

                    <div class="col-xs-3 col-sm-3">
                        <div class="form-actions">
                            <div class="input-group">
                                <input id="qing_search" placeholder="输入你想查找的接口名称 ..." type="text" class="form-control" name="message" value="${search}" />
                                <span class="input-group-btn">
                                    <button class="btn btn-sm btn-info no-radius" type="button" id="interfaceSearch" />
                                </span>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-3 col-sm-3">
                    </div>

                    <div class="col-xs-3 col-sm-3">
                        <a href="${base}/v1/download/cross">下载本地调试辅助工具</a>
                    </div>

                    <div class="row col-xs-12" id="taskList">
                    </div>
                </div>
                </div>

            <#include "/include/righttool-sidebar.ftl" />

<script type="text/javascript">
    var allInterface;

    var task_html = '<div class="col-xs-2 col-sm-3 qing_task hide" id="task_{idx}"><div class="widget-box"><div class="widget-header" style="text-align: center"><h4 class="smaller">{desc}</h4></div><div class="widget-body"><div class="widget-main" style="text-align: center;word-break: break-all;">{content}<br/><a href="{url}" target="_blank" ><button class="btn btn-xs btn-danger"><i class="icon-bolt bigger-110"></i>跳转<i class="icon-arrow-right icon-on-right"></i></button></a></div></div></div></div>';
    $(document).ready(function(){
        var request = {
            url : "${base}/v1/test/interface/all",
            data : null,
            handlerFunc : saveData,
            isASync : false,
            failTitle :"查询接口信息出错:"
        };

        commonAjaxRequest(request);
    });

    function saveData(resu){
        allInterface = resu.resultList;
        for(var idx in allInterface){
            var task = allInterface[idx];
            var html = task_html;
            html = html.replace(new RegExp("{idx}","gm"), task.id);
            html = html.replace(new RegExp("{desc}","gm"), task.interfaceName);
            html = html.replace(new RegExp("{url}","gm"), "${base}/v1/test/json_format?cross=1&id=" + task.id + "&catelogIndex=" + task.catelogIndex);
            html = html.replace(new RegExp("{content}","gm"), task.interfaceUrl);

            try{
                $("#taskList").append(html);
            }catch(t){
                // ignore
            }
        }

        search();
    }

    function search(){
        var search = $("#qing_search").val();
        $(".qing_task").addClass("hide");

        if(search != ""){
            for(var idx in allInterface){
                var task = allInterface[idx];
                var id = "task_" + task.id;

                if(task.interfaceName.toLowerCase().indexOf(search) != -1 || task.interfaceUrl.toLowerCase().indexOf(search) != -1){
                    $("#" + id).removeClass("hide");
                }
            }
        }
    }

    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();

        $(".env").click(function(){
            $(".env.btn-primary").removeClass("btn-primary");
            $(this).addClass("btn-primary");
            $("#env").val($(this).val());
        });

        $("#qing_search").change(search);


    });
</script>
    </div>
</body>
</html>