<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>新增Mock规则</title>
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

        .profile-info-value>span+span:before {
            content: "";
        }
    </style>

    <link href="${base}/static/css/json/base.css" rel="stylesheet">
    <link href="${base}/static/css/json/jquery.numberedtextarea.css" rel="stylesheet">

    <script src="${base}/static/js/json/hm.js"></script>
    <script src="${base}/static/js/json/jquery.message.js"></script>
    <script src="${base}/static/js/json/jquery.json.js"></script>
    <script src="${base}/static/js/json/json2.js"></script>
    <script src="${base}/static/js/json/jsonlint.js"></script>
    <script src="${base}/static/js/json/jquery.numberedtextarea.js"></script>
    <script src="${base}/static/js/param.js"></script>
    <script src="${base}/static/js/catelog.js"></script>
    <script src="${base}/static/assets/js/fuelux/fuelux.tree.min.js"></script>
    <script src="${base}/static/assets/js/fuelux/data/fuelux.tree-sampledata.js"></script>

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
                                                <!-- 接口参数 -->
                                                <div class="timeline-item clearfix">
                                                    <div class="timeline-info">
                                                        <img alt="Susan't Avatar" src="${base}/static/assets/avatars/avatar1.png" />
                                                        <span class="label label-info label-sm">16:22</span>
                                                    </div>

                                                    <div class="panel panel-default">
                                                        <div id="faq-0-1">
                                                            <div id="accordion" class="accordion-style2">
                                                                <div class="group">
                                                                    <h3 class="accordion-header">Mock规则配置填写</h3>

                                                                    <div>
                                                                        <div class="col-xs-12">
                                                                            <form class="form-horizontal">
                                                                                <input type="hidden" id = "ruleId" />

                                                                                <!-- mock类型 -->
                                                                                <div class="form-group">
                                                                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="discountType">Mock类型:</label>
                                                                                    <div class="col-xs-12 col-sm-3">
                                                                                        <select class="width-80 chosen-select" id="mockType" data-placeholder="选择mock类型...">
                                                                                            <option value="0">&nbsp;</option>
                                                                                        </select>
                                                                                    </div><!-- /span -->
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="remark">规则描述:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-5" type="text" id="remark" placeholder="输入规则描述..." />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="ruleType">规则类型:</label>

                                                                                    <div class="col-xs-12 col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="hidden" name="ruleType" id="ruleType" value="VALUE_MATCH" />
                                                                                            <button type="button" value="VALUE_MATCH" style="border-radius: 8px;" class="btn btn-sm qing_rule_type btn-primary">值匹配</button>
                                                                                            <button type="button" value="NUMBER_RANGE" style="border-radius: 8px;" class="btn btn-sm qing_rule_type">数值范围匹配</button>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group ruleType_input ruleType_VALUE_MATCH">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="ruleTypeValue">匹配值:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-5" type="text" id="ruleTypeValue" placeholder="输入匹配值..." />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group ruleType_input ruleType_NUMBER_RANGE hide">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="ruleStartValue">值范围-下限:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-5" type="text" id="ruleStartValue" placeholder="输入下限.." />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group ruleType_input ruleType_NUMBER_RANGE hide">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="ruleEndValue">值范围-上限:</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input class="col-xs-5" type="text" id="ruleEndValue" placeholder="输入上限..." />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="noMock">走原代码逻辑：</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <label>
                                                                                                <input id="noMock" class="ace ace-switch ace-switch-6" type="checkbox" value="0" />
                                                                                                <span class="lbl"></span>
                                                                                            </label>
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" style="text-align: right" for="resp">返回值：</label>

                                                                                    <div class="col-sm-9">
                                                                                        <textarea id="resp" style="height: 250px" class="autosize-transition form-control"></textarea>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="ruleOrderNum">优先级：</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="text" class="input-mini" id="ruleOrderNum" />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="className">是否默认：</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <label>
                                                                                                <input id="isDefault" class="ace ace-switch ace-switch-6" type="checkbox" value="0" />
                                                                                                <span class="lbl"></span>
                                                                                            </label>
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="form-group">
                                                                                    <label class="col-sm-3 control-label no-padding-right" for="delayMs">延迟返回毫秒数：</label>

                                                                                    <div class="col-sm-9">
                                                                                        <div class="clearfix">
                                                                                            <input type="text" class="input-mini" id="delayMs" />
                                                                                        </div>

                                                                                        <div class="space-2"></div>
                                                                                    </div>
                                                                                </div>

                                                                            </form>

                                                                            <div class="hr hr-dotted"></div>
                                                                            <div class="hr hr-dotted"></div>

                                                                            <div class="clearfix form-actions">
                                                                                <div class="col-md-offset-3 col-md-9">
                                                                                    <button class="btn btn-info" style="border-radius: 8px" type="button" id = "saveBtn">
                                                                                        <i class="icon-ok bigger-110"></i>
                                                                                        Submit
                                                                                    </button>

                                                                                    &nbsp; &nbsp; &nbsp;
                                                                                    <button class="btn" type="reset" style="border-radius: 8px" id = "resetBtn">
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
                                            </div>
                                        </div><!-- /.timeline-items -->
                                    </div><!-- /.timeline-container -->
                                </div>
                            </div>
                        </div>

                        <br style="clear:both;">
                    </main>
                </div>
            </div>
            <#include "/include/righttool-sidebar.ftl" />
            <#--<#include "/test/datetime.ftl" />-->
        </div>

        <script type="text/javascript">
            $('textarea').numberedtextarea();
            $('#ruleOrderNum').ace_spinner({value:0,min:0,max:1000000000,step:1, on_sides: true, icon_up:'icon-plus smaller-75', icon_down:'icon-minus smaller-75', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});
            $('#delayMs').ace_spinner({value:0,min:0,max:1000000000,step:1, on_sides: true, icon_up:'icon-plus smaller-75', icon_down:'icon-minus smaller-75', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});

            $(document).ready(function(){
            <#if ((id)!0) gt 0>
                $("#ruleId").val(${bean.id});
                refreshMockType("${bean.mockType}");
                ruleTypeChange("${bean.ruleType}");
                revertValueInput("${bean.ruleType}", '${bean.ruleValue}');
                $("#ruleOrderNum").val(${bean.ruleOrderNum});
                $("#resp").val('${bean.resp}');
                $("#remark").val("${bean.remark}");
                $("#delayMs").val(${bean.delayMs});
                $("#ruleOrderNum").val(${bean.ruleOrderNum});
                var idDefault = "${bean.default?c}" ;
                if(idDefault == "true"){
                    $("#isDefault").attr("checked", "checked");
                    $("#isDefault").val(1);
                }else{
                    $("#isDefault").removeAttr("checked");
                    $("#isDefault").val(0);
                }
                if("${bean.notMock?c}" == "true"){
                    $("#noMock").val(1);
                    $("#noMock").attr("checked", "checked");
                }
            <#else>
                refreshMockType("${mockType!''}");
            </#if>
            });

            function refreshMockType(defaultMockType){
                var request = {
                    url : "${base}/v1/mock/type/list.json",
                    data : null,
                    handlerFunc : handlerMockTypeResult,
                    isASync : true,
                    failTitle :"获取信息失败:",
                    otherData:{"defaultMockType":defaultMockType}

                };

                commonAjaxRequest(request);
            }

            function revertValueInput(ruleType, ruleValue){
                ruleValue = JSON.parse(ruleValue);
                if(ruleType == 'VALUE_MATCH'){
                    $("#ruleTypeValue").val(ruleValue.value);
                }else if(ruleType == 'NUMBER_RANGE'){
                    $("#ruleStartValue").val(ruleValue.start);
                    $("#ruleEndValue").val(ruleValue.end);
                }
            }

            function handlerMockTypeResult(resu, otherData) {
                var resultList = resu.resultList;
                if (resu.resultList != null && resu.resultList.length > 0) {
                    var options = [];
                    var optionIdx = 0;
                    for (var resultIdx in resultList) {
                        var result = resultList[resultIdx];

                        var option = new Object();
                        option.key = result.mockType;
                        option.value = result.mockName;
                        options[optionIdx++] = option;
                    }

                    updateOptions("mockType", options, otherData.defaultMockType);
                }
            };

            $(".qing_rule_type").click(function(){
                ruleTypeChange($(this).val());
            });

            function ruleTypeChange(ruleType){
                $(".qing_rule_type").removeClass("btn-primary");
                $("#ruleType").val(ruleType);
                $(".qing_rule_type[value='" + ruleType + "']").addClass("btn-primary");

                $(".ruleType_input").addClass("hide");
                $(".ruleType_" + ruleType).removeClass("hide");
            }

            $("#isDefault").click(function(){
                var isDefault = $("#isDefault").val();
                if(isDefault == 0){
                    $("#isDefault").val(1);
                }else{
                    $("#isDefault").val(0);
                }
            });

            $("#noMock").click(function(){
                var noMock = $("#noMock").val();
                if(noMock == 0){
                    $("#noMock").val(1);
                }else{
                    $("#noMock").val(0);
                }
            });

            $("#saveBtn").click(function(){
                var remark = $("#remark").val();
                if(isStringEmpty(remark)){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "请输入规则描述",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }

                var ruleValue= {};
                var ruleValueType = $("#ruleType").val();
                if(ruleValueType == 'VALUE_MATCH'){
                    var ruleTypeValue = $("#ruleTypeValue").val();
                    if(isStringEmpty(ruleTypeValue)){
                        $.gritter.add({
                            title : '参数错误:',
                            text : "匹配值不能为空",
                            class_name : 'gritter-error gritter-center'
                        });
                        return;
                    }

                    ruleValue.value = ruleTypeValue;
                }else if(ruleValueType == 'NUMBER_RANGE'){
                    var ruleStartValue = $("#ruleStartValue").val();
                    var ruleEndValue = $("#ruleEndValue").val();
                    if(isStringEmpty(ruleStartValue) && isStringEmpty(ruleEndValue)){
                        $.gritter.add({
                            title : '参数错误:',
                            text : "值范围上限和下限不能同时为空",
                            class_name : 'gritter-error gritter-center'
                        });
                        return;
                    }

                    ruleValue.start = new Number(ruleStartValue);
                    ruleValue.end = new Number(ruleEndValue);
                }

                var resp = $("#resp").val();
                if($("#noMock").val() == "0" && isStringEmpty(resp)){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "返回值不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }
                var ruleOrderNum = $("#ruleOrderNum").val();
                if(isStringEmpty(ruleOrderNum)){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "优先级不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }

                var delayMs = $("#delayMs").val();
                if(isEmpty(delayMs)){
                    $.gritter.add({
                        title : '参数错误:',
                        text : "延迟返回毫秒数不能为空",
                        class_name : 'gritter-error gritter-center'
                    });
                    return;
                }

                var data = {
                    id : $("#ruleId").val(),
                    mockType : $("#mockType").val(),
                    remark : remark,
                    ruleType : $("#ruleType").val(),
                    ruleValue : JSON.stringify(ruleValue),
                    notMock : $("#noMock").val() == "1",
                    resp : resp,
                    ruleOrderNum : ruleOrderNum,
                    default: $("#isDefault").val() == "1",
                    delayMs:delayMs,
                    deleted : 0,
                    isNeedRecordOrder:false
                };

                var request = {
                    url : "${base}/v1/mock/rule/add.json",
                    data : data,
                    handlerFunc : handlerSave,
                    isASync : true,
                    failTitle :"保存出错:"
                };

                commonAjaxRequest(request);
            });

            function handlerSave(resu){
                $.gritter.add({
                    title : '提示:',
                    text : '添加成功',
                    class_name : 'gritter-info gritter-center'
                });
            }

            jQuery(function($) {
                $(".chosen-select").chosen();
            });
        </script>
    </div>
</body>
</html>