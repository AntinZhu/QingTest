<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>master-slave测试页面</title>
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
                            <div class="col-sm-6 qing_resize">
                                <div class="col-xs-12 col-sm-10 col-sm-offset-1">
                                    <div class="timeline-container">
                                        <div class="timeline-label">
													<span class="label label-primary arrowed-in-right label-lg">
														<b>Today</b>
													</span>
                                        </div>

                                        <div class="timeline-items">
                                            <!-- 新增 -->
                                            <div id="faq-list-1" class="panel-group accordion-style1 accordion-style2">
                                                <!-- 接口参数 -->
                                                <div class="timeline-item clearfix">
                                                    <div class="timeline-info">
                                                        <img alt="Susan't Avatar" src="${base}/static/assets/avatars/avatar1.png" />
                                                        <span class="label label-info label-sm qingTime">16:22</span>
                                                    </div>

                                                    <div class="panel panel-default">
                                                        <div id="faq-0-1">
                                                            <div id="accordion" class="accordion-style2">
                                                                <div class="group">
                                                                    <h3 class="accordion-header">新增</h3>

                                                                    <div>
                                                                        <div class="col-xs-12">
                                                                            <#assign paramListDiv = "addParamDiv" />
                                                                            <#assign paramDiv = "addShowDiv" />
                                                                            <#include "/include/paramDetail.ftl" />

                                                                            <div class="hr hr-dotted"></div>
                                                                            <div class="hr hr-dotted"></div>

                                                                            <div class="clearfix form-actions">
                                                                                <div class="col-md-offset-3 col-md-9">
                                                                                    <button class="btn btn-info" type="button" id = "addBtn">
                                                                                        <i class="icon-ok bigger-110"></i>
                                                                                        Submit
                                                                                    </button>

                                                                                    &nbsp; &nbsp; &nbsp;
                                                                                    <button class="btn" type="reset" id = "resetBtn">
                                                                                        <i class="icon-undo bigger-110"></i>
                                                                                        Reset
                                                                                    </button>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div><!-- #accordion -->
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--接口的相关配置 -->
                                                <div class="timeline-item clearfix">
                                                    <div class="timeline-info">
                                                        <i class="timeline-indicator icon-star btn btn-warning no-hover green"></i>
                                                    </div>
                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                            <a href="#faq-1-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
                                                                <i class="icon-chevron-left pull-right" data-icon-hide="icon-chevron-down" data-icon-show="icon-chevron-left"></i>

                                                                <i class="icon-user bigger-130"></i>
                                                                &nbsp;接口相关配置
                                                            </a>
                                                        </div>

                                                        <div class="panel-collapse collapse" id="faq-1-1">
                                                            <div class="col-md-12" style="padding:0;position:relative;height:100%;">
                                                                <div class="page-header">
                                                                    <h1>
                                                                        操作返回
                                                                        <small>
                                                                            <i class="icon-double-angle-right"></i>
                                                                            <label>也就是看看</label>
                                                                        </small>
                                                                    </h1>
                                                                </div><!-- /.page-header -->
                                                                <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                                    <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                                    <div class="ro" id="json-add-result" style="padding:0px 25px;white-space: pre-line;">
                                                                        <span data-type="object"><i style="cursor:pointer;" class="fa icon-minus" onclick="hide(this)"></i>{<br><br>}</span></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- 更新 -->
                                            <div id="faq-list-2" class="panel-group accordion-style1 accordion-style2">
                                                <!-- 接口参数 -->
                                                <div class="timeline-item clearfix">
                                                    <div class="timeline-info">
                                                        <img alt="Susan't Avatar" src="${base}/static/assets/avatars/avatar1.png" />
                                                        <span class="label label-info label-sm qingTime">16:22</span>
                                                    </div>

                                                    <div class="panel panel-default">
                                                        <div id="faq-0-1">
                                                            <div id="accordion" class="accordion-style2">
                                                                <div class="group">
                                                                    <h3 class="accordion-header">更新</h3>

                                                                    <div>
                                                                        <div class="col-xs-12">
                                                                        <#assign paramListDiv = "updateParamDiv" />
                                                                        <#assign paramDiv = "updateShowDiv" />
                                                                        <#include "/include/paramDetail.ftl" />

                                                                            <div class="hr hr-dotted"></div>
                                                                            <div class="hr hr-dotted"></div>

                                                                            <div class="clearfix form-actions">
                                                                                <div class="col-md-offset-3 col-md-9">
                                                                                    <button class="btn btn-info" type="button" id = "updateBtn">
                                                                                        <i class="icon-ok bigger-110"></i>
                                                                                        Submit
                                                                                    </button>

                                                                                    &nbsp; &nbsp; &nbsp;
                                                                                    <button class="btn" type="reset" id = "resetBtn">
                                                                                        <i class="icon-undo bigger-110"></i>
                                                                                        Reset
                                                                                    </button>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div><!-- #accordion -->
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--接口的相关配置 -->
                                                <div class="timeline-item clearfix">
                                                    <div class="timeline-info">
                                                        <i class="timeline-indicator icon-star btn btn-warning no-hover green"></i>
                                                    </div>
                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                            <a href="#faq-2-1" data-parent="#faq-list-2" data-toggle="collapse" class="accordion-toggle collapsed">
                                                                <i class="icon-chevron-left pull-right" data-icon-hide="icon-chevron-down" data-icon-show="icon-chevron-left"></i>

                                                                <i class="icon-user bigger-130"></i>
                                                                &nbsp;接口相关配置
                                                            </a>
                                                        </div>

                                                        <div class="panel-collapse collapse" id="faq-2-1">
                                                            <div class="col-md-12" style="padding:0;position:relative;height:100%;">
                                                                <div class="page-header">
                                                                    <h1>
                                                                        操作返回
                                                                        <small>
                                                                            <i class="icon-double-angle-right"></i>
                                                                            <label>也就是看看</label>
                                                                        </small>
                                                                    </h1>
                                                                </div><!-- /.page-header -->
                                                                <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                                    <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                                    <div class="ro" id="json-update-result" style="padding:0px 25px;white-space: pre-line;">
                                                                        <span data-type="object"><i style="cursor:pointer;" class="fa icon-minus" onclick="hide(this)"></i>{<br><br>}</span></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- 删除 -->
                                            <div id="faq-list-3" class="panel-group accordion-style1 accordion-style2">
                                                <!-- 接口参数 -->
                                                <div class="timeline-item clearfix">
                                                    <div class="timeline-info">
                                                        <img alt="Susan't Avatar" src="${base}/static/assets/avatars/avatar1.png" />
                                                        <span class="label label-info label-sm qingTime">16:22</span>
                                                    </div>

                                                    <div class="panel panel-default">
                                                        <div id="faq-0-1">
                                                            <div id="accordion" class="accordion-style2">
                                                                <div class="group">
                                                                    <h3 class="accordion-header">删除</h3>

                                                                    <div>
                                                                        <div class="col-xs-12">
                                                                        <#assign paramListDiv = "deleteParamDiv" />
                                                                        <#assign paramDiv = "deleteShowDiv" />
                                                                        <#include "/include/paramDetail.ftl" />

                                                                            <div class="hr hr-dotted"></div>
                                                                            <div class="hr hr-dotted"></div>

                                                                            <div class="clearfix form-actions">
                                                                                <div class="col-md-offset-3 col-md-9">
                                                                                    <button class="btn btn-info" type="button" id = "deleteBtn">
                                                                                        <i class="icon-ok bigger-110"></i>
                                                                                        Submit
                                                                                    </button>

                                                                                    &nbsp; &nbsp; &nbsp;
                                                                                    <button class="btn" type="reset" id = "resetBtn">
                                                                                        <i class="icon-undo bigger-110"></i>
                                                                                        Reset
                                                                                    </button>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div><!-- #accordion -->
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--接口的相关配置 -->
                                                <div class="timeline-item clearfix">
                                                    <div class="timeline-info">
                                                        <i class="timeline-indicator icon-star btn btn-warning no-hover green"></i>
                                                    </div>
                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                            <a href="#faq-3-1" data-parent="#faq-list-3" data-toggle="collapse" class="accordion-toggle collapsed">
                                                                <i class="icon-chevron-left pull-right" data-icon-hide="icon-chevron-down" data-icon-show="icon-chevron-left"></i>

                                                                <i class="icon-user bigger-130"></i>
                                                                &nbsp;接口相关配置
                                                            </a>
                                                        </div>

                                                        <div class="panel-collapse collapse" id="faq-3-1">
                                                            <div class="col-md-12" style="padding:0;position:relative;height:100%;">
                                                                <div class="page-header">
                                                                    <h1>
                                                                        操作返回
                                                                        <small>
                                                                            <i class="icon-double-angle-right"></i>
                                                                            <label>也就是看看</label>
                                                                        </small>
                                                                    </h1>
                                                                </div><!-- /.page-header -->
                                                                <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                                    <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                                    <div class="ro" id="json-delete-result" style="padding:0px 25px;white-space: pre-line;">
                                                                        <span data-type="object"><i style="cursor:pointer;" class="fa icon-minus" onclick="hide(this)"></i>{<br><br>}</span></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div><!-- /.timeline-items -->
                                    </div><!-- /.timeline-container -->
                                </div>
                            </div>
                            <div class="col-sm-6 qing_resize" style="padding:0;position:relative;height:100%;">
                                <div class="page-header">
                                    <h1>
                                        查询结果
                                        <small>
                                            <i class="icon-double-angle-right"></i>
                                            <label class="pull-left inline"  title="指定主库" data-rel="tooltip" >
                                                <input id="assignMaster" type="checkbox" class="ace ace-switch ace-switch-5" value="0" />
                                                <span class="lbl"></span>
                                            </label>
                                            <label>
                                                <button type="button" class="btn btn-purple btn-xs" id="refreshAll">
                                                    刷新
                                                    <i class="icon-search icon-on-right bigger-110"></i>
                                                </button>
                                            </label>
                                        </small>
                                    </h1>
                                </div><!-- /.page-header -->
                                <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                    <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                    <div class="ro" id="json-interface-detail" style="padding:0px 25px;white-space: pre-line;">
                                        <span data-type="object"><i style="cursor:pointer;" class="fa icon-minus" onclick="hide(this)"></i>{<br><br>}</span></div>
                                </div>
                            </div>
                        </div><!-- /.row -->
                    </div><!-- /.page-header -->
                </div>
                </div>

            <#include "/include/righttool-sidebar.ftl" />

<script type="text/javascript">
    var serIp = "${serIp}";

    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();
        var resizeChanged = false;
        window.onresize = function(){
            resizeChanged = qingResize(resizeChanged);
        }

        setInterval(refreshTime, 1000);

        function refreshTime(){
            var now = new Date();
            $(".qingTime").text(now.format("HH:mm"));
        }

        var addParamInfo;
        var updateParamInfo;
        var deleteParamInfo;
        $(document).ready(function(){
            var paramDetail = '[{"key":"inT","name":"事务内查询", "class":"switch_editable","defaultValue":{"name":false,"value":false}},{"key":"numberType","name":"值类型","defaultValue":{"name":100,"value":100}},{"key":"number","name":"值","defaultValue":{"name":2,"value":2}},{"key":"info","name":"描述","defaultValue":{"name":"我是描述-dev","value":"我是描述-dev"}}]';
            addParamInfo = showParam({paramData:paramDetail,htmlDiv:"addParamDiv", hideDiv:"addShowDiv"});

            paramDetail = '[{"key":"inT","name":"事务内更新", "class":"switch_editable","defaultValue":{"name":false,"value":false}},{"key":"id","name":"ID","defaultValue":{"name":259,"value":259}},{"key":"numberType","name":"值类型","defaultValue":{"name":100,"value":100}},{"key":"number","name":"值","defaultValue":{"name":2,"value":2}},{"key":"info","name":"描述","defaultValue":{"name":"我是描述-dev","value":"我是描述-dev"}}]';
            updateParamInfo = showParam({paramData:paramDetail,htmlDiv:"updateParamDiv", hideDiv:"updateShowDiv"});

            paramDetail = '[{"key":"inT","name":"事务内删除", "class":"switch_editable","defaultValue":{"name":false,"value":false}},{"key":"id","name":"ID","defaultValue":{"name":259,"value":259}},{"key":"numberType","name":"值类型","defaultValue":{"name":100,"value":100}}]';
            deleteParamInfo = showParam({paramData:paramDetail,htmlDiv:"deleteParamDiv", hideDiv:"deleteShowDiv"});

            selectAll();

            resizeChanged = qingResize(resizeChanged);
        });

        $("#refreshAll").click(function(){
            selectAll();
        });

        function selectAll(){
            var url = '/test/list_by_type_1?type=100';
            if($("#assignMaster").val() == "1"){
                url = '/test/list_by_type_2?type=100';
            }
            var data = {
                url : url,
                param : ''
            };

            var request = {
                url : "${base}/v1/common/pi.json?host=" + encodeURI(serIp + ":8084"),
                data : data,
                handlerFunc : handlerParamSave,
                isASync : false,
                failTitle :"查询失败："
            };

            commonAjaxRequest(request);
        }

        function handlerParamSave(resu){
            jsonShow(JSON.stringify(resu.resultList), "json-interface-detail");
        }

        $("#addBtn").click(function(){
            var param = generateJsonParam("#addParamDiv input", addParamInfo);

            var url = '/test/add_1';
            if(param.inT){
                url = '/test/add_2';
            }
            var data = {
                url : url,
                param : JSON.stringify(param)
            }

            var request = {
                url : "${base}/v1/common/pi.json?host=" + encodeURI(serIp + ":8084"),
                data : data,
                handlerFunc : handleAddResult,
                isASync : false,
                failTitle :"新增失败:"
            };

            commonAjaxRequest(request);
        });

        function handleAddResult(resu){
            jsonShow(JSON.stringify(resu.resultList), "json-add-result");
        }

        $("#updateBtn").click(function(){
            var param = generateJsonParam("#updateParamDiv input", updateParamInfo);

            var url = '/test/update_1';
            if(param.inT){
                url = '/test/update_2';
            }
            var data = {
                url : url,
                param : JSON.stringify(param)
            }

            var request = {
                url : "${base}/v1/common/pi.json?host=" + encodeURI(serIp + ":8084"),
                data : data,
                handlerFunc : handleUpdateResult,
                isASync : false,
                failTitle :"更新失败："
            };

            commonAjaxRequest(request);
        });

        $("#assignMaster").click(function(){
            var nowValue = $(this).val();
            if(nowValue == "1"){
                $(this).val(0);
            }else{
                $(this).val(1);
            }
        });

        function handleUpdateResult(resu){
            jsonShow(JSON.stringify(resu.resultList), "json-update-result");
        }

        $("#deleteBtn").click(function(){
            var param = generateJsonParam("#deleteParamDiv input", deleteParamInfo);

            var url = '/test/delete_1';
            if(param.inT){
                url = '/test/delete_2';
            }
            var data = {
                url : url,
                param : JSON.stringify(param)
            }

            var request = {
                url : "${base}/v1/common/pi.json?host=" + encodeURI(serIp + ":8084"),
                data : data,
                handlerFunc : handleDeleteResult,
                isASync : false,
                failTitle :"更新失败："
            };

            commonAjaxRequest(request);
        });

        function handleDeleteResult(resu){
            jsonShow(JSON.stringify(resu.resultList), "json-delete-result");
        }
    });
</script>
    </div>
</body>
</html>