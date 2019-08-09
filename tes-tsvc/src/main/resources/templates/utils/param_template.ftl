<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>参数模板生成器</title>
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
                        <h1>
                            参数模板生成器
                            <small>
                                <i class="icon-double-angle-right"></i>
                                <label id = "interfaceNameDiv">将Json转化为My 参数输入格式</label>
                            </small>
                        </h1>
                    </div><!-- /.page-header -->

                    <div class="row col-xs-12">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right" for="fullData">Json串：</label>

                            <div class="col-sm-9">
                                <textarea id="paramJsonObj" style="height: 250px" class="autosize-transition form-control"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right" for="aa"></label>

                            <div class="col-sm-9">
                                <div class="col-sm-offset-5">
                                    <button class="btn btn-grey btn-sm" id="paramTemplateConvertBtn">
                                        <i class="icon-refresh"></i>
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right" for="fullData">结果：</label>

                            <div class="col-sm-9">
                                <textarea id="paramJsonTemplate" style="height: 250px" class="autosize-transition form-control"></textarea>
                            </div>
                        </div>

                        <div class="hr hr-dotted"></div>

                        <#include "/include/paramDetail.ftl" />

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right" for="fullData">格式化查看：</label>

                            <div class="col-sm-9">
                                <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                                    <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                                    <div class="ro" id="json-interface-detail" style="padding:0px 25px;white-space: pre-line;">
                                        <span data-type="object"><i style="cursor:pointer;" class="fa icon-minus" onclick="hide(this)"></i>{<br><br>}</span></div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                </div>

            <#include "/include/righttool-sidebar.ftl" />
    </div>

<script type="text/javascript">
    var paramInfo;
    $(document).on("change", "#paramJsonTemplate",function(e){
        setTimeout(refreshResult, 500);
    });

    function refreshResult(){
        var param = generateEditParam("#paramListDiv input", paramInfo);
        $("#paramJsonTemplate").text(param);

        jsonShow(param, "json-interface-detail");
    }

    $("#paramTemplateConvertBtn").click(function(){
        var jsonObjValue = $("#paramJsonObj").val();
        if(jsonObjValue == ""){
            $.gritter.add({
                    title : "参数错误",
                    text : "请输入Json串",
                    class_name : 'gritter-error gritter-center'
                });
            return;
        }

        jsonShow(jsonObjValue, "json-interface-detail");

        var jsonObj;
        try{
            jsonObj = JSON.parse(jsonObjValue);
        }catch(e){
            $.gritter.add({
                title : "参数错误",
                text : "Json串格式有误",
                class_name : 'gritter-error gritter-center'
            });
            return;
        }

        var result = JSON.stringify(generateTemplate(jsonObj));
        jsonShow(result, "json-interface-detail");
        $("#paramJsonTemplate").text(result);

        paramInfo = showParam({paramData:result, isEditStatus:true,valueChangedNotifyId:"paramJsonTemplate"});
    });
</script>
</body>
</html>