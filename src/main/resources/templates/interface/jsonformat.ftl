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
            <main class="row-fluid" style="height:85%;min-height:550px;">
                <div class="col-md-6" style="padding:0px;height:100%;">
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
                                    <div class="form-group hide" id="requestUserIdDev">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="teacherIdIpt">请求人ID:</label>

                                        <div class="col-xs-12 col-sm-9">
                                            <div class="clearfix">
                                                <input type="number" name="requestUserId" id="requestUserId" value="22367" class="col-xs-12 col-sm-3" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="hr hr-dotted"></div>

                                    <div id="paramEleDiv">

                                    </div>

                                    <div class="hr hr-dotted"></div>
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
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6" style="padding:0;position:relative;height:100%;">
                    <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                        <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                        <div class="ro" id="json-target" style="padding:0px 25px;white-space: pre-line;">
                            <span data-type="object"><i style="cursor:pointer;" class="fa fa-minus-square-o" onclick="hide(this)"></i>{<br><br>}</span></div>
                    </div>
                    <form id="form-save" method="POST"><input type="hidden" value="" id="txt-content" name="content"></form>
                </div>
                <br style="clear:both;">

                <div class="hide">
                    <div id="inputDiv">
                        <div class="form-group">
                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="{paramName}">{remark}:</label>

                            <div class="col-xs-12 col-sm-9">
                                <div class="clearfix">
                                    <input type="number" name="{paramName}" id="{paramName}" value="{defaultValue}" class="col-xs-12 col-sm-3" />
                                </div>
                            </div>
                        </div>

                        <div class="space-2"></div>
                    </div>
                </div>
            </main>
        </div>

        <script type="text/javascript">
            var interfaceData;

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
            $('#json-src').keyup(function(){
                init();
                var content = $.trim($(this).val());
                jsonShow(content);
            });

            function jsonShow(content){
                var result = '';
                content = JSON.stringify(content);
                if (content!='') {
                    try{
                        current_json = jsonlint.parse(content);
                        current_json_str = JSON.stringify(current_json);
                        result = new JSONFormat(content,4).toString();
                    }catch(e){
                        result = '<span style="color: #f1592a;font-weight:bold;">' + e + '</span>';
                        current_json_str = result;
                    }

                    $('#json-target').html(result);
                }else{
                    $('#json-target').html('');
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
                $('#json-src').val('');
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
            $('#json-src').keyup();

            $(document).ready(function(){
                var data = {
                    data : 9
                };
                commonAjaxRequest("${base}/v1/test/interface.json", data, handlerInterface, true, "获取接口信息失败:");
            });

            function handlerInterface(resu){
                interfaceData = resu.interfaceInfo;
                $("#interfaceId").val(resu.interfaceInfo.inter.id);
                jsonShow(resu);
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

                    var paramHtml = "";
                    for(var paramIdx in resu.interfaceInfo.params){
                        var param = resu.interfaceInfo.params[paramIdx];
                        switch (param.htmlEleType){
                            case "input":
                                paramHtml += initInput(param);
                                break;
                        }
                    }

                    $("#paramEleDiv").html(paramHtml);
                }
            }

            function initInput(param){
                var s = $("#inputDiv").html();
                var inputHtml = s.replace(new RegExp("{paramName}","gm"), param.paramName);
                inputHtml = inputHtml.replace(new RegExp("{remark}","gm"), param.remark);
                inputHtml = inputHtml.replace(new RegExp("{defaultValue}","gm"), param.defaultValue);

                return inputHtml;
            }

            jQuery(function($) {
                $('#teacherIdBtn').click(function () {
                    var paramList = new Array();
                    var idx = 0;
                    for(var paramIdx in interfaceData.params) {
                        var param = interfaceData.params[paramIdx];
                        var paramValue = $("#" + param.paramName).val();
                        if(paramValue == null && param.must){
                            return false;
                        }

                        if(paramValue != null){
                            var paramItem = new Object();
                            paramItem.paramId = param.id;
                            paramItem.paramValue = paramValue;
                            paramList[idx++] = paramItem;
                        }
                    }
                    var data = {
                        interfaceId : $("#interfaceId").val(),
                        requestUserId : $("#requestUserId").val(),
                        paramList : paramList
                    };
                    commonAjaxRequest("${base}/v1/test/interface/invoke.json", data, handlerTeacherInfo, true, "获取老师信息for订单异常:");
                });

                function handlerTeacherInfo(resu){
                    jsonShow(resu);
                };
            });
        </script>
    </div>
</body>
</html>