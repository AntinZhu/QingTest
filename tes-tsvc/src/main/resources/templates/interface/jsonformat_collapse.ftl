<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Hello World!</title>
    <#include "/include/resource.ftl" />

    <style>
        .spinner-preview {
            width:100px;
            height:100px;
            text-align:center;
            margin-top:60px;
        }

        .dropdown-preview {
            margin:0 5px;
            display:inline-block;
        }
        .dropdown-preview  > .dropdown-menu {
            display: block;
            position: static;
            margin-bottom: 5px;
        }

        .json_key{ color: #92278f;font-weight:bold;}
        .json_null{color: #f1592a;font-weight:bold;}
        .json_string{ color: #3ab54a;font-weight:bold;}
        .json_number{ color: #25aae2;font-weight:bold;}
        .json_boolean{ color: #f98280;font-weight:bold;}
        .json_link{ color: #61D2D6;font-weight:bold;}
        .json_array_brackets{}

        .next-line{
            display:block;
        }
    </style>

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
                <div id="timeline-1">
                    <div class="row">
                        <div class="col-xs-12 col-sm-10 col-sm-offset-1">
                            <div class="timeline-container">
                                <div class="timeline-label">
													<span class="label label-primary arrowed-in-right label-lg">
														<b>Today</b>
													</span>
                                </div>

                                <div class="timeline-items">
                                    <div id="faq-list-1" class="panel-group accordion-style1 accordion-style2">
                                    <!-- 请求的接口地址 -->
                                    <div class="timeline-item clearfix">
                                        <div class="timeline-info">
                                            <i class="timeline-indicator icon-beaker btn btn-default no-hover"></i>
                                        </div>

                                        <div class="widget-box transparent">
                                            <div class="widget-header hidden"></div>

                                            <div class="widget-body">
                                                <div class="widget-main" id="interfaceUrl"> Took the final exam. Phew! </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- 接口参数 -->
                                    <div class="timeline-item clearfix">
                                        <div class="timeline-info">
                                            <img alt="Susan't Avatar" src="${base}/static/assets/avatars/avatar1.png" />
                                            <span class="label label-info label-sm">16:22</span>
                                        </div>

                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <a href="#faq-0-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle">
                                                        <i class="icon-chevron-left pull-right" data-icon-hide="icon-chevron-down" data-icon-show="icon-chevron-left"></i>
                                                        <h1>
                                                            接口测试
                                                            <small>
                                                                <i class="icon-double-angle-right"></i>
                                                                <label id = "interfaceNameDiv">Common form elements and layouts</label>
                                                            </small>
                                                        </h1>
                                                    </a>
                                                </div>

                                                <div class="panel-collapse" id="faq-0-1">
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

                                                            <div class="form-group hide" id="requestUserIdDev">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="teacherIdIpt">请求人ID:</label>

                                                                <div class="col-xs-12 col-sm-9">
                                                                    <div class="clearfix">
                                                                        <input type="hidden" name="requestUserId" id="requestUserId" value="22367" class="col-xs-12 col-sm-3" />
                                                                        <span class="editable editable-click editable-unsaved" id="requestUserIdDiv" style="display: inline-block; background-color: rgba(0, 0, 0, 0);">22367</span>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <div class="hr hr-dotted"></div>

                                                            <div class="form-group hiden" id="paramDiv">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="param3">请求参数:</label>

                                                                <div class="col-xs-12 col-sm-9" id = "paramListDiv">
                                                                    <div class="profile-user-info profile-user-info-striped" >
                                                                        <div class="profile-info-row">
                                                                            <div class="profile-info-name"> Username </div>

                                                                            <div class="profile-info-value">
                                                                                <span class="editable" id="username">alexdoe</span>
                                                                            </div>
                                                                        </div>
                                                                        <div class="profile-info-row">
                                                                            <div class="profile-info-name"> Username </div>

                                                                            <div class="profile-info-value">
                                                                                <div class="profile-user-info profile-user-info-striped">
                                                                                    <div class="profile-info-row">
                                                                                        <div class="profile-info-name"> 名称 </div>

                                                                                        <div class="profile-info-value">
                                                                                            <span class="editable editable-click editable-unsaved" id="user_name" style="display: inline-block; background-color: rgba(0, 0, 0, 0);">张</span>
                                                                                        </div>
                                                                                    </div>

                                                                                    <div class="profile-info-row">
                                                                                        <div class="profile-info-name"> 用户信息 </div>

                                                                                        <div class="profile-info-value">
                                                                                            <span class="editable editable-click" id="user" style="display: inline-block;">undefined</span>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <div class="hr hr-dotted"></div>
                                                        </form>

                                                        <div class="clearfix form-actions">
                                                            <div class="col-md-offset-3 col-md-9">
                                                                <button class="btn btn-info" type="button" id = "teacherIdBtn">
                                                                    <i class="icon-ok bigger-110"></i>
                                                                    Submit
                                                                </button>

                                                                &nbsp; &nbsp; &nbsp;
                                                                <button class="btn" type="reset">
                                                                    <i class="icon-undo bigger-110"></i>
                                                                    Reset
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
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
                                                    <div class="col-md-6" style="padding:0;position:relative;height:100%;">
                                                        <div class="page-header">
                                                            <h1>
                                                                接口配置
                                                                <small>
                                                                    <i class="icon-double-angle-right"></i>
                                                                    <label>也就是看看</label>
                                                                </small>
                                                            </h1>
                                                        </div><!-- /.page-header -->
                                                        <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                            <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                            <div class="ro" id="json-interface" style="padding:0px 25px;white-space: pre-line;">
                                                                <span data-type="object"><i style="cursor:pointer;" class="fa fa-minus-square-o" onclick="hide(this)"></i>{<br><br>}</span></div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6" style="padding:0;position:relative;height:100%;">
                                                        <div class="page-header">
                                                            <h1>
                                                                接口参数配置
                                                                <small>
                                                                    <i class="icon-double-angle-right"></i>
                                                                    <label>如果此处的json格式不对，说明配置有问题</label>
                                                                </small>
                                                            </h1>
                                                        </div><!-- /.page-header -->
                                                        <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                            <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                            <div class="ro" id="json-interface-detail" style="padding:0px 25px;white-space: pre-line;">
                                                                <span data-type="object"><i style="cursor:pointer;" class="fa fa-minus-square-o" onclick="hide(this)"></i>{<br><br>}</span></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                    </div>
                                    <!-- 接口请求的参数和结果 -->
                                    <div class="timeline-item clearfix">
                                        <div class="timeline-info">
                                            <i class="timeline-indicator icon-star btn btn-warning no-hover green"></i>
                                        </div>
                                        <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <a href="#faq-2-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
                                                        <i class="icon-chevron-left pull-right" data-icon-hide="icon-chevron-down" data-icon-show="icon-chevron-left"></i>

                                                        <i class="icon-user bigger-130"></i>
                                                        &nbsp;请求参数和返回
                                                    </a>
                                                </div>

                                                <div class="panel-collapse collapse" id="faq-2-1">
                                                    <div class="col-md-6" style="padding:0;position:relative;height:100%;">
                                                        <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                            <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                            <div class="ro" id="json-request" style="padding:0px 25px;white-space: pre-line;">
                                                                <span data-type="object"><i style="cursor:pointer;" class="fa fa-minus-square-o" onclick="hide(this)"></i>{<br><br>}</span></div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6" style="padding:0;position:relative;height:100%;">
                                                        <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                                            <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                                            <div class="ro" id="json-response" style="padding:0px 25px;white-space: pre-line;">
                                                                <span data-type="object"><i style="cursor:pointer;" class="fa fa-minus-square-o" onclick="hide(this)"></i>{<br><br>}</span></div>
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
                </div>

                <br style="clear:both;">

                <div class="hide">
                    <div id="table_editable">
                        <div class="profile-user-info profile-user-info-striped" id = "{id}">
                            {paramList}
                        </div>
                    </div>

                    <div id = "sub_editable">
                        <div class="profile-info-row" alt="{alt}">
                            <div class="profile-info-name"> {name} </div>

                            <div class="profile-info-value">
                                <div class="spinner-buttons input-group-btn hide delInputDiv {key}" style="display: inline-block;">
                                    <button class="btn spinner-down btn-xs btn-danger delInputBtn" type="button">
                                        <i class="icon-minus smaller-75"></i>
                                    </button>
                                </div>
                                <div class="spinner-buttons input-group-btn hide addInputDiv {key}" style="display: inline-block;margin-left: 25px">
                                    <button class="btn spinner-up btn-xs btn-success addInputBtn" type="button">
                                        <i class="icon-plus smaller-75"></i>
                                    </button>
                                </div>
                                {paramList}
                            </div>
                        </div>
                    </div>

                    <div id = "input_editable">
                        <div class="profile-info-row" alt="{alt}">
                            <div class="profile-info-name"> {name} </div>

                            <div class="profile-info-value">
                                <div class="spinner-buttons input-group-btn hide delInputDiv {key}" style="display: inline-block;margin-right: 25px;">
                                    <button class="btn spinner-down btn-xs btn-danger delInputBtn" type="button">
                                        <i class="icon-minus smaller-75"></i>
                                    </button>
                                </div>
                                <div class="spinner-buttons input-group-btn hide addInputDiv {key}" style="display: inline-block;">
                                    <button class="btn spinner-up btn-xs btn-success addInputBtn"  type="button">
                                        <i class="icon-plus smaller-75"></i>
                                    </button>
                                </div>
                                <input type="hidden" name="{key}" alt="{alt}" value="{defaultValue}"/>
                                <span class="editable input_label {key}_label">{defaultName}</span>
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
            $('textarea').numberedtextarea();
            var current_json = '';
            var current_json_str = '';
            var xml_flag = false;
            var zip_flag = false;
            var shown_flag = false;
            var compress_flag = false;
            $('.tip').tooltip();
            function init(){
                xml_flag = false;
                zip_flag = false;
                shown_flag = false;
                compress_flag = false;
                renderLine();
            }

            function jsonShow(content, id){
                var result = '';
                if(Object.prototype.toString.call(content) != "[object String]"){
                    content = JSON.stringify(content);
                }
                if (content!='') {
                    try{
                        current_json = jsonlint.parse(content);
                        current_json_str = JSON.stringify(current_json);
                        result = new JSONFormat(content,4).toString();
                    }catch(e){
                        result = '<span style="color: #f1592a;font-weight:bold;">' + e + '</span>';
                        current_json_str = result;
                    }

                    $('#' + id).html(result);
                }else{
                    $('#' + id).html('');
                }
            }

            function renderLine(){
                var line_num = $('#json-target').height()/20;
                $('#line-num').html("");
                var line_num_html = "";
                for (var i = 1; i < line_num+1; i++) {
                    line_num_html += "<div>"+i+"<div>";
                }
                $('#line-num').html(line_num_html);
            }
            $('.clear').click(function(){
                $('#json-target').html('');
            });
            (function($){
                $.fn.innerText = function(msg) {
                    if (msg) {
                        if (document.body.innerText) {
                            for (var i in this) {
                                this[i].innerText = msg;
                            }
                        } else {
                            for (var i in this) {
                                this[i].innerHTML.replace(/&amp;lt;br&amp;gt;/gi,"n").replace(/(&amp;lt;([^&amp;gt;]+)&amp;gt;)/gi, "");
                            }
                        }
                        return this;
                    } else {
                        if (document.body.innerText) {
                            return this[0].innerText;
                        } else {
                            return this[0].innerHTML.replace(/&amp;lt;br&amp;gt;/gi,"n").replace(/(&amp;lt;([^&amp;gt;]+)&amp;gt;)/gi, "");
                        }
                    }
                };
            })(jQuery);

            $(document).ready(function(){
                var data = {
                    data : 10
                };
                commonAjaxRequest("${base}/v1/test/interface.json", data, handlerInterface, true, "获取接口信息失败:");
            });

            function refreshInterfaceUrl(){
                var env = $("#env").val();
                $("#interfaceUrl").text(interfaceUrlPrefix.replace("{env}", env));
            }

            var interfaceParam;
            var interfaceUrlPrefix = "http://gateway.{env}.idc.cedu.cn";
            function handlerInterface(resu){
                jsonShow(resu, "json-interface");
                jsonShow(resu.interfaceInfo.inter.paramDetail, "json-interface-detail");
                interfaceUrlPrefix += resu.interfaceInfo.inter.interfaceUrl;
                $("#interfaceId").val(resu.interfaceInfo.inter.id);
                $("#interfaceNameDiv").text(resu.interfaceInfo.inter.interfaceName);
                if(resu.interfaceInfo.inter.interfaceType == "PT"){
                    $("#requestUserIdDev").removeClass("hide");
                    var requestLabel = $("#requestUserIdDev").find("label");
                    switch (resu.interfaceInfo.inter.requestUserType){
                        case "teacher":
                            requestLabel.text(requestLabel.text() + "(老师)");
                            break;
                        case "student":
                            requestLabel.text(requestLabel.text() + "(学生)");
                            break;
                    }
                }

                var params = JSON.parse(resu.interfaceInfo.inter.paramDetail);

                if(params != ""){
                    interfaceParam = params;
                    var paramHtmls = genHtml("", params, "0");
                    $("#paramListDiv").html(paramHtmls);

                    initHtml("", params);
                    $("#paramDiv").removeClass("hide");
                }

                refreshInterfaceUrl();
            }

            $(document).off("click", '.addInputBtn').on('click', '.addInputBtn',cloneInput);
            $(document).off("click", '.delInputBtn').on('click', '.delInputBtn',removeInput);
//            $(document).on('click', '.accordion-toggle', function(event) {
//                event.stopPropagation();
//                var $this = $(this);
//                var parent = $this.data('parent');
//                var actives = $(parent).find('.open');
//                for(var idx = 0; idx < actives.length; idx++){
//                    $(actives[idx]).collapse('hide');
//                }
////                // From bootstrap itself
////                if (actives && actives.length) {
////                    hasData = actives.data('collapse');
////                    //if (hasData && hasData.transitioning) return;
////                    actives.collapse('hide');
////                }
////                var target = $this.attr('data-target') || (href = $this.attr('href')) && href.replace(/.*(?=#[^\s]+$)/, ''); //strip for ie7
////                $(target).collapse('toggle');
//            });

            function cloneInput(){
                var inputClone = $(this).parent().parent().clone();
                inputClone.children("div .addInputDiv").remove();
                var newAltParent = $(this).parent().parent().parent().attr("alt");
                var newAltParent = incAlt(newAltParent);
                var firstAltParent = firstAlt(newAltParent);

                $(this).parent().parent().parent().attr("alt", newAltParent);
                inputClone.find("input").each(function(index,element){
                    var nowAlt = $(element).attr("alt");
                    var newAlt= nowAlt.replace(firstAltParent, newAltParent);
                    $(element).attr("alt", newAlt);
                });
                $(this).parent().parent().parent().append(inputClone);

                editableInit();
            }

            function incAlt(alt){
                var newAlt = "";

                var splitArr = alt.split("-");
                for(var idx in splitArr){
                    var altItem = splitArr[idx];
                    if(idx == splitArr.length - 1){
                        altItem = new Number(altItem) + 1;
                    }
                    newAlt += newAlt ==""? altItem : "-" + altItem;
                }

                return newAlt;
            }

            function firstAlt(alt){
                var newAlt = "";

                var splitArr = alt.split("-");
                for(var idx in splitArr){
                    var altItem = splitArr[idx];
                    if(idx == splitArr.length - 1){
                        altItem = 0;
                    }
                    newAlt += newAlt ==""? altItem : "-" + altItem;
                }

                return newAlt;
            }

            function removeInput(){
                $(this).parent().parent().remove();
            }

            var paramInfo = new Object();
            function initHtml(parentKey, params){
                if(parentKey != ''){
                    parentKey += "-";
                }

                for(var paramIdx in params) {
                    var param = params[paramIdx];
                    var isMulti = Array.isArray(param);
                    if(isMulti){
                        param = param[0];
                    }

                    var paramKey = parentKey + param.key;
                    paramInfo[paramKey] = new Object();
                    paramInfo[paramKey]["isMulti"] = isMulti;

                    if(param.detail != null){
                        initHtml(paramKey, param.detail);
                    }else{
                        var defaultName = "";
                        var defaultValue = "";
                        if(param.defaultValue != null){
                            if(param.defaultValue.name != null){
                                defaultName = param.defaultValue.name;
                                defaultValue =  param.defaultValue.value;
                            }else{
                                defaultName = param.defaultValue;
                                defaultValue = param.defaultValue;
                            }
                        }

                        if(param.selectable != null){
                            var options = [];
                            for(var secIdx in param.selectable){
                                var sec = param.selectable[secIdx];
                                options.push({id: sec.value, text: sec.name});
                            }

                            paramInfo[paramKey]["options"] = options;
                            paramInfo[paramKey]["selectable"] = true;
                        }else{
                            paramInfo[paramKey]["selectable"] = false;
                        }

                        paramInfo[paramKey]["type"] = Object.prototype.toString.call(defaultValue);
                    }
                }

                editableInit();
            }

            function editableInit(){
                for(var prop in paramInfo){
                    if(paramInfo[prop]["selectable"]){
                        var options = paramInfo[prop]["options"];
                        $('.' + prop + "_label").editable({
                            type: 'select2',
                            value : options[0].text,
                            source: options,
                            success: function(response, newValue) {
                                $(this).prev("input").val(newValue);
                            }
                        });
                    }else{
                        $('.' + prop + '_label').editable({
                            type: 'text',
                            success: function(response, newValue) {
                                $(this).prev("input").val(newValue);
                            }
                        });
                    }

                    if(paramInfo[prop]["isMulti"]){
                        $("div ." + prop).removeClass("hide");
                        $("div ." + prop).removeClass("hide");
                        $("." + prop + "_label").css("display", "block");
                    }
                }
            }

            function genHtml(parentKey, params, paramAlt){
                if(parentKey != ''){
                    parentKey += "-";
                }

                var paramHtmls = "";
                for(var paramIdx in params){
                    var param = params[paramIdx];
                    var paramHtml = "";
                    var isArray = Array.isArray(param);
                    if(isArray){
                        param = param[0];
                    }
                    if(param.detail != null){
                        var subTableHtml = genHtml(parentKey + param.key, param.detail, paramAlt + "-0");

                        var subHtml = $("#sub_editable").html();
                        paramHtml = subHtml.replace(new RegExp("{key}","gm"), parentKey + param.key);
                        paramHtml = paramHtml.replace(new RegExp("{name}","gm"), param.name);
                        paramHtml = paramHtml.replace(new RegExp("{paramList}","gm"), subTableHtml);
                        paramHtml = paramHtml.replace(new RegExp("{alt}","gm"), paramAlt);
                    }else{
                        paramHtml = initInput(parentKey, param, paramAlt);
                    }
                    paramHtmls += paramHtml;
                }

                var tableHtml = $("#table_editable").html();
                tableHtml = tableHtml.replace(new RegExp("{id}","gm"), parentKey);
                tableHtml = tableHtml.replace(new RegExp("{paramList}","gm"), paramHtmls);

                return tableHtml;
            }

            function initInput(paramKey, param, paramAlt){
                var html = $("#input_editable").html();
                var paramHtml = html.replace(new RegExp("{name}","gm"), param.name);
                paramHtml = paramHtml.replace(new RegExp("{key}","gm"), paramKey + param.key);
                paramHtml = paramHtml.replace(new RegExp("{alt}","gm"), paramAlt);
                var defaultName = "";
                var defaultValue = "";
                if(param.defaultValue != null){
                    if(param.defaultValue.name != null){
                        defaultName = param.defaultValue.name;
                        defaultValue =  param.defaultValue.value;
                    }else{
                        defaultName = param.defaultValue;
                        defaultValue = param.defaultValue;
                    }
                }

                paramHtml = paramHtml.replace(new RegExp("{defaultName}","gm"), defaultName);
                paramHtml = paramHtml.replace(new RegExp("{defaultValue}","gm"), defaultValue);

                return paramHtml;
            }

            jQuery(function($) {

                $(".env").click(function(){
                    $(".env.btn-primary").removeClass("btn-primary");
                    $(this).addClass("btn-primary");
                    $("#env").val($(this).val());

                    refreshInterfaceUrl();
                });

                $('#teacherIdBtn').click(function () {
                    var param = new Object();
                    fromIdxInfo = new Object();
                    destIdxInfo = new Object();

                    $("#paramListDiv input").each(function(key,value){
//                        alert(key + "->" + value.name + "->" + value.value);
                        var paramNameArr = value.name.split("-");
                        var altArr = value.alt.split("-");

                        formatParam(param, "", paramNameArr, altArr, 0, value);
                    });
                    jsonShow(param, "json-request");
                    var data = {
                        interfaceId : $("#interfaceId").val(),
                        requestUserId : $("#requestUserId").val(),
                        param : JSON.stringify(param)
                    };
                    commonAjaxRequest("${base}/v1/test/interface/invoke.json", data, handlerTeacherInfo, true, "接口调用异常：:", $("#env").val());
                    fromIdxInfo = null;
                    destIdxInfo = null;
                });

                var fromIdxInfo;
                var destIdxInfo;
                function formatParam(paramObj, paramName, paramNameArr, altArr, arrIdx, value){
                    var propName = paramNameArr[arrIdx];
                    paramName = paramName == ""? propName:paramName + "-" + propName;
                    if(paramInfo[paramName]["isMulti"]){ // 数组形式
                        if(paramObj[propName] == null){
                            paramObj[propName] = new Array();
                        }

                        var idx = altArr[arrIdx];
                        if(arrIdx == paramNameArr.length -1){
                            paramObj[propName].push(formatValue(value.name, value.value));
                            return;
                        }else{
                            var obj;
                            if(fromIdxInfo[paramName] != null && fromIdxInfo[paramName] == idx){
                                idx = destIdxInfo[paramName];
                            }else{
                                if(idx > 0){
                                    if(paramObj[propName][idx-1] == null){
                                        fromIdxInfo[paramName] = idx;
                                        while(paramObj[propName][idx-1] == null){
                                            idx --;
                                        }
                                        destIdxInfo[paramName] = idx;
                                    }
                                }
                            }

                            if(paramObj[propName][idx] == null){
                                obj = new Object();
                                paramObj[propName][idx] = obj;
                            }else{
                                obj = paramObj[propName][idx];
                            }

                            formatParam(obj, paramName, paramNameArr, altArr, arrIdx + 1, value);
                        }
                    }else{
                        if(arrIdx == paramNameArr.length -1){
                            paramObj[propName] = formatValue(value.name, value.value);
                            return;
                        }else{
                            if(paramObj[propName] == null){
                                paramObj[propName] = new Object();
                            }

                            formatParam(paramObj[propName], paramName, paramNameArr, altArr, arrIdx + 1, value);
                        }
                    }
                }

                function formatValue(paramKey, value){
                    if(paramInfo[paramKey]["type"] == "[object Number]"){
                        return new Number(value);
                    }

                    return  value;
                }

                function handlerTeacherInfo(resu){
                    jsonShow(resu.data, "json-response");
                };

                //editables on first profile page
                $.fn.editable.defaults.mode = 'inline';
                $.fn.editableform.loading = "<div class='editableform-loading'><i class='light-blue icon-2x icon-spinner icon-spin'></i></div>";
                $.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="icon-ok icon-white"></i></button>'+
                        '<button type="button" class="btn editable-cancel"><i class="icon-remove"></i></button>';

                $("#requestUserIdDiv").editable({
                    type: 'text',
                    name: 'username'
                });

                //editables
                $('#username').editable({
                    type: 'text',
                    name: 'username'
                });

                $('#signup').editable({
                    type: 'date',
                    format: 'yyyy-mm-dd',
                    viewformat: 'dd/mm/yyyy',
                    datepicker: {
                        weekStart: 1
                    }
                });
            });
        </script>
    </div>
</body>
</html>