<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Hello World!</title>
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
                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="teacherIdIpt">请求人ID:</label>

                                    <div class="col-xs-12 col-sm-9">
                                        <div class="clearfix">
                                            <input type="hidden" name="requestUserId" id="requestUserId" value="22367" class="col-xs-12 col-sm-3" />
                                            <span class="editable editable-click editable-unsaved" id="requestUserIdDiv" style="display: inline-block; background-color: rgba(0, 0, 0, 0);">22367</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="hr hr-dotted"></div>
                                <div class="hr hr-dotted"></div>

                                <!-- PAGE CONTENT BEGINS -->
                                <#include "/include/param.ftl" />

                                <div class="clearfix form-actions">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button class="btn btn-info" type="button" id = "submitBtn">
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

                                <div class="hr hr-dotted"></div>
                                <form class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="sourceChannel">切换支付场景</label>
                                        <div class="col-xs-12 col-sm-8">
                                            <select class="width-80 chosen-select" id="sourceChannel" data-placeholder="选择支付场景...">
                                                <option value="1" se>APP内</option>
                                                <option value="2">H5浏览器内</option>
                                                <option value="3">H5微信内</option>
                                                <option value="4">H5和教育</option>
                                                <option value="5">pc端</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="payType">选择支付方式</label>
                                        <div class="col-xs-12 col-sm-8">
                                            <select class="width-80 chosen-select" id="payType" data-placeholder="选择支付方式...">
                                                <option value="">&nbsp;</option>
                                            </select>
                                            <span class="input-group-btn">
                                                                            <button type="button" class="btn btn-purple btn-xs" id="addPayTypeBtn">
                                                                                新增支付路径
                                                                                <i class="icon-search icon-on-right bigger-110"></i>
                                                                            </button>
                                                                        </span>
                                        </div>
                                    </div>
                                    <div class="form-group hide" id="stageChooseDiv">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="stageConfigId">选择分期数</label>
                                        <div class="col-xs-4 col-sm-4">
                                            <div>
                                                <select class="width-80 form-control" id="stageConfigId" multiple="multiple">
                                                    <option value="AL">￥567 x 3期（手续费￥81）</option>
                                                    <option value="AK">￥567 x 3期（手续费￥81）</option>
                                                    <option value="AZ">￥567 x 3期（手续费￥81）</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="orderAmount">订单金额</label>
                                        <div class="col-xs-12 col-sm-3">
                                            <div class="alert alert-danger center">
                                                <strong>
                                                    <span id="orderAmountTxt"></span>
                                                </strong>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="balanceAmount">钱包余额</label>
                                        <div class="col-xs-12 col-sm-3">
                                            <div class="alert alert-danger center">
                                                <strong>
                                                    <span id="balanceAmountTxt"></span>
                                                </strong>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" style="text-align: right;" for="balanceAmount">第三方支付路径:</label>
                                        <div class="col-xs-12 col-sm-6">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>支付方式</th>
                                                        <th>关联记录</th>
                                                        <th>TradeId</th>
                                                        <th class="hidden-480">Status</th>
                                                        <th>操作</th>
                                                    </tr>
                                                    </thead>

                                                    <tbody id="payWayList">
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            <#include "/include/righttool-sidebar.ftl" />

        <script type="text/javascript">
            $('#submitBtn').click(updatePayWayList);

            function updatePayWayList(){
                var data = generateJsonParam("#paramListDiv input");

                commonAjaxRequest("${base}/v1/pay/pay_infos_2.json", data, handlerPayWayList, true, "获取第三方支付路径出错:", $("#env").val());
            }

            var syncPayWayList;
            function handlerPayWayList(resu){
                if(resu.payBriefList == null){
                    $.gritter.add({
                        title : '第三方支付路径:',
                        text : '未找到第三方支付路径',
                        class_name : 'gritter-info gritter-center'
                    });
                    return;
                }
                $("#payWayList").html(thirdPayWayList(resu.payBriefList));

                if(syncPayWayList == null){
                    syncPayWayList = setInterval(updatePayWayList, 5000);
                }
                return true;
            }

            $(document).off("click", '.mockPayBtn').on('click', '.mockPayBtn',function(){
                var qinqqingTradeNo = $(this).parent().parent().prev("td").prev("td").prev("td").text().trim();
                var data = {
                    data : qinqqingTradeNo
                };

                commonAjaxRequest("${base}/v1/pay/mock_third_pay.json", data, handlerMockThirdNotify, true, "模拟第三方支付成功通知失败:", $("#env").val());


                if(syncPayWayList == null){
                    syncPayWayList = setInterval(updatePayWayList, 5000);
                }
            });

            function handlerMockThirdNotify(resu){
                $.gritter.add({
                    title : '模拟第三方支付成功通知成功:',
                    text : '模拟第三方支付成功通知成功',
                    class_name : 'gritter-info gritter-center'
                });
            }

            $(document).ready(function(){
                var data = {
                    data : 1
                };
                commonAjaxRequest("${base}/v1/test/interface.json", data, handlerInterface, true, "获取接口信息失败:");
            });

            $(".env").click(function(){
                $(".env.btn-primary").removeClass("btn-primary");
                $(this).addClass("btn-primary");
                $("#env").val($(this).val());
            });

            function handlerInterface(resu){
//                activeCatelog(resu.interfaceInfo.inter.catelogIndex);
                var params = JSON.parse(resu.interfaceInfo.inter.paramDetail);

                if(params != ""){
                    interfaceParam = params;
                    var paramHtmls = genHtml("", params, null);
                    $("#paramListDiv").html(paramHtmls);

                    initHtml("", params);
                    $("#paramDiv").removeClass("hide");
                }
                $("#interfaceNameDiv").text(resu.interfaceInfo.inter.interfaceName);
            }

            jQuery(function($) {
                $(".chosen-select").chosen();
                $('[data-rel=tooltip]').tooltip();
            });
        </script>
    </div>
</body>
</html>