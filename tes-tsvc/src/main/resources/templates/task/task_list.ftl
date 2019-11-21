<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>任务列表</title>
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
                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            任务列表
                            <small>
                                <i class="icon-double-angle-right"></i>
                                <label id = "interfaceNameDiv">查询并操作用户进行中的任务</label>
                            </small>
                        </h1>
                    </div><!-- /.page-header -->

                    <div class="col-xs-12">

                        <#include "/include/param.ftl" />

                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" style="border-radius: 8px" type="button" id = "taskSearchBtn">
                                    <i class="icon-ok bigger-110"></i>
                                    Submit
                                </button>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" style="border-radius: 8px" type="reset" id="resetBtn">
                                    <i class="icon-undo bigger-110"></i>
                                    Save Example
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="row col-xs-12" id="taskList">
                    </div>
                </div>
                </div>

            <#include "/include/righttool-sidebar.ftl" />

<script type="text/javascript">
    $(document).ready(function(){
        var paramDetail = '[{"key":"user_type","name":"user_type","selectable":[{"name":"unknown_user_type","value":"unknown_user_type"},{"name":"student","value":"student"},{"name":"teacher","value":"teacher"},{"name":"ta","value":"ta"},{"name":"guest","value":"guest"},{"name":"admin","value":"admin"},{"name":"system","value":"system"},{"name":"expert_user_type","value":"expert_user_type"},{"name":"staff_user_type","value":"staff_user_type"},{"name":"crm_student","value":"crm_student"},{"name":"crm_teacher","value":"crm_teacher"},{"name":"wechat_app","value":"wechat_app"},{"name":"trm","value":"trm"},{"name":"promotion_user_type","value":"promotion_user_type"},{"name":"tmk_s","value":"tmk_s"},{"name":"copartner","value":"copartner"},{"name":"live_visitor","value":"live_visitor"},{"name":"robot","value":"robot"},{"name":"tmk_customer","value":"tmk_customer"}],"class":"select_editable","defaultValue":{"name":"teacher","value":"teacher"}},{"key":"user_id","name":"user_id","defaultValue":{"name":"3856","value":3856}},{"key":"page_no","name":"page_no","defaultValue":{"name":"1","value":1}},{"key":"page_count","name":"page_count","defaultValue":{"name":"50","value":50}},[{"key":"task_types","name":"task_types","defaultValue":[{"name":"1","value":1}]}],[{"key":"task_status","name":"task_status","selectable":[{"name":"unknown_task_status","value":"unknown_task_status"},{"name":"created_task_status","value":"created_task_status"},{"name":"done_task_status","value":"done_task_status"},{"name":"ignored_task_status","value":"ignored_task_status"},{"name":"expired_task_status","value":"expired_task_status"}],"class":"select_editable","defaultValue":[{"name":"created_task_status","value":"created_task_status"}]}],{"key":"task_order_type","name":"task_order_type","selectable":[{"name":"unknown_task_order_type","value":"unknown_task_order_type"},{"name":"start_time_task_type","value":"start_time_task_type"},{"name":"end_time_task_type","value":"end_time_task_type"},{"name":"handle_time_task_type","value":"handle_time_task_type"}],"class":"select_editable","defaultValue":{"name":"start_time_task_type","value":"start_time_task_type"}},{"key":"order_mode","name":"order_mode","selectable":[{"name":"unknown_order_mode","value":"unknown_order_mode"},{"name":"desc_order_mode","value":"desc_order_mode"},{"name":"asc_order_mode","value":"asc_order_mode"}],"class":"select_editable","defaultValue":{"name":"desc_order_mode","value":"desc_order_mode"}}]';
        paramInfo = showParam({paramData:paramDetail});
    });

    $(document).off("click", '.addInputBtn').on('click', '.addInputBtn',cloneInput);
    $(document).off("click", '.delInputBtn').on('click', '.delInputBtn',removeInput);

    var task_html = '<div class="col-xs-2 col-sm-3 qing_task hide" id="task_{idx}"><div class="widget-box"><div class="widget-header" style="text-align: center"><h4 class="smaller">{desc}</h4></div><div class="widget-body"><div class="widget-main" style="text-align: center;word-break: break-all;">{content}<br/><a href="{url}" target="_blank" ><button class="btn btn-xs btn-danger"><i class="icon-bolt bigger-110"></i>跳转<i class="icon-arrow-right icon-on-right"></i></button></a></div></div></div></div>';
   function taskShow(){
        var request = {
            url : "${base}/v1/test/interface/all",
            data : null,
            handlerFunc : saveData,
            isASync : false,
            failTitle :"查询接口信息出错:"
        };

        commonAjaxRequest(request);
    }

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