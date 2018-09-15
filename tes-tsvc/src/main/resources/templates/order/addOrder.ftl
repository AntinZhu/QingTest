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
    </style>
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
                        下单接口
                        <#--<small>-->
                            <#--<i class="icon-double-angle-right"></i>-->
                            <#--and Validation-->
                        <#--</small>-->
                    </h1>
                </div><!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->

                        <h4 class="lighter">
                            <i class="icon-hand-right icon-animated-hand-pointer blue"></i>
                            <a href="#modal-wizard" data-toggle="modal" class="pink"> Wizard Inside a Modal Box </a>
                        </h4>

                        <div class="hr hr-18 hr-double dotted"></div>

                        <div class="row-fluid">
                            <div class="span12">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-blue widget-header-flat">
                                        <h4 class="lighter">订单主流程</h4>

                                        <#--<div class="widget-toolbar">-->
                                            <#--<label>-->
                                                <#--<small class="green">-->
                                                    <#--<b>Validation</b>-->
                                                <#--</small>-->

                                                <#--<input id="skip-validation" type="checkbox" class="ace ace-switch ace-switch-4" />-->
                                                <#--<span class="lbl"></span>-->
                                            <#--</label>-->
                                        <#--</div>-->
                                    </div>

                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div class="row-fluid wizard-actions">
                                                <button class="btn btn-prev">
                                                    <i class="icon-arrow-left"></i>
                                                    Prev
                                                </button>

                                                <button class="btn btn-success btn-next" data-last="Finish ">
                                                    Next
                                                    <i class="icon-arrow-right icon-on-right"></i>
                                                </button>
                                            </div>

                                            <hr />
                                            <div id="fuelux-wizard" class="row-fluid" data-target="#step-container">
                                                <ul class="wizard-steps">
                                                    <li data-target="#step1" class="active">
                                                        <span class="step">1</span>
                                                        <span class="title">下单</span>
                                                    </li>

                                                    <li data-target="#step2">
                                                        <span class="step">2</span>
                                                        <span class="title">支付</span>
                                                    </li>

                                                    <li data-target="#step3">
                                                        <span class="step">3</span>
                                                        <span class="title">课程</span>
                                                    </li>

                                                    <li data-target="#step4">
                                                        <span class="step">4</span>
                                                        <span class="title">Other Info</span>
                                                    </li>
                                                </ul>
                                            </div>

                                            <hr />
                                            <div class="step-content row-fluid position-relative" id="step-container">
                                                <div class="step-pane active" id="step1">
                                                    <h3 class="lighter block green">下单参数选择</h3>
                                                    <div class="hr hr-dotted"></div>
                                                    <form class="form-horizontal" id="validation-form" method="get">
                                                        <div class="form-group">
                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="teacherIdIpt">老师ID:</label>

                                                            <div class="col-xs-12 col-sm-9">
                                                                <div class="clearfix">
                                                                    <input type="number" name="teacherIdIpt" id="teacherIdIpt" value="2946" class="col-xs-12 col-sm-3" />
                                                                    <input type="hidden" name="qingqingTeacherId" id = "qingqingTeacherId" />
                                                                    <input type="hidden" name="teacherId" id = "teacherId" />
                                                                    <span class="input-group-btn">
																		<button type="button" class="btn btn-purple btn-xs" id="teacherIdBtn">
																			Search
																			<i class="icon-search icon-on-right bigger-110"></i>
																		</button>
																	</span>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="space-2"></div>

                                                        <div class="form-group">
                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="studentIdIpt">学生ID:</label>

                                                            <div class="col-xs-12 col-sm-9">
                                                                <div class="clearfix">
                                                                    <input type="number" name="studentIdIpt" id="studentIdIpt" value="22367" class="col-xs-12 col-sm-3" />
                                                                    <input type="hidden" name="studentId" id = "studentId" />
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="hr hr-dotted"></div>

                                                        <!-- 年级科目信息 -->
                                                        <div id="orderDetailSelector" class="hide">
                                                            <div class="form-group">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="discountType">优惠类型:</label>
                                                                <div class="col-xs-12 col-sm-3">
                                                                    <select class="width-80 chosen-select" id="discountType" data-placeholder="选择优惠类型...">
                                                                        <option value="0">&nbsp;</option>
                                                                    </select>
                                                                </div><!-- /span -->
                                                            </div>
                                                            <div class="form-group hide" id="coursePackageChooseDiv">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="coursePackageId">选择优惠包：</label>
                                                                <div class="col-xs-4 col-sm-4">
                                                                    <div>
                                                                        <select class="width-80 form-control" id="coursePackageId" multiple="multiple">
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-group hide" id="contentPackageChooseDiv">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="contentPackageId">选择内容包：</label>
                                                                <div class="col-xs-4 col-sm-4">
                                                                    <div>
                                                                        <select class="width-80 form-control" id="contentPackageId" multiple="multiple">
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="coursePriceType">订单类型:</label>
                                                                <div class="col-xs-12 col-sm-3">
                                                                    <select class="width-80 chosen-select" id="coursePriceType" data-placeholder="选择订单类型...">
                                                                        <option value="0">&nbsp;</option>
                                                                    </select>
                                                                </div><!-- /span -->
                                                            </div>
                                                            <div class="form-group ">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right">年级:</label>

                                                                <div class="col-xs-12 col-sm-3">
                                                                    <select class="width-80 chosen-select" id="gradeId" data-placeholder="选择年级...">
                                                                        <option value="0">&nbsp;</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="form-group ">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right">科目:</label>

                                                                <div class="col-xs-12 col-sm-3">
                                                                    <select class="width-80 chosen-select" id="courseId" data-placeholder="选择科目...">
                                                                        <option value="0">&nbsp;</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="form-group ">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right">上门方式:</label>

                                                                <div class="col-xs-12 col-sm-3">
                                                                    <select class="width-80 chosen-select" id="siteType" data-placeholder="选择上门方式...">
                                                                        <option value="0">&nbsp;</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="hr hr-dotted"></div>
                                                        </div>

                                                        <!-- 老师的相关信息 -->
                                                        <div class="form-group hide">
                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="startCourseTime">首次上课时间:</label>
                                                            <div class="input-group col-xs-12 col-sm-6">
                                                                <input class="form-control date-picker" id="id-date-picker-1" type="startCourseTime" data-date-format="yyyy-mm-dd" />
                                                                <span class="input-group-addon">
                                                                            <i class="icon-calendar bigger-110"></i>
                                                                        </span>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="courseTimes">课次:</label>
                                                            <div class="col-xs-12 col-sm-3">
                                                                <input type="number" name="courseTimes" id="courseTimes" value="4" />
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="classHour">课时:</label>
                                                            <div class="col-xs-12 col-sm-3">
                                                                <input type="number" name="classHour" id="classHour" disabled="disabled" value="20" />
                                                            </div>
                                                        </div>

                                                        <div class="hr hr-dotted"></div>

                                                        <!-- 学生相关的信息 -->
                                                        <div class="form-group hide" id="studentAddress">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right">学生可选地址:</label>

                                                            <div class="col-xs-12 col-sm-9" id="addressRadio">
                                                            </div>

                                                            <div class="hr hr-dotted"></div>
                                                        </div>
                                                    </form>
                                                </div>

                                                <div class="step-pane" id="step2">
                                                    <form class="form-horizontal" id="validation-form" method="get">
                                                        <div class="form-group">
                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right">相关信息</label>
                                                            <div class="col-xs-12 col-sm-6">
                                                                <div class="widget-box">
                                                                    <div class="widget-header header-color-blue">
                                                                        <h5 class="bigger lighter">
                                                                            <i class="icon-table"></i>
                                                                            下单接口返回信息
                                                                        </h5>

                                                                        <div class="widget-toolbar widget-toolbar-light no-border">
                                                                            <select id="simple-colorpicker-1" class="hide">
                                                                                <option selected="" data-class="blue" value="#307ECC">#307ECC</option>
                                                                                <option data-class="blue2" value="#5090C1">#5090C1</option>
                                                                                <option data-class="blue3" value="#6379AA">#6379AA</option>
                                                                                <option data-class="green" value="#82AF6F">#82AF6F</option>
                                                                                <option data-class="green2" value="#2E8965">#2E8965</option>
                                                                                <option data-class="green3" value="#5FBC47">#5FBC47</option>
                                                                                <option data-class="red" value="#E2755F">#E2755F</option>
                                                                                <option data-class="red2" value="#E04141">#E04141</option>
                                                                                <option data-class="red3" value="#D15B47">#D15B47</option>
                                                                                <option data-class="orange" value="#FFC657">#FFC657</option>
                                                                                <option data-class="purple" value="#7E6EB0">#7E6EB0</option>
                                                                                <option data-class="pink" value="#CE6F9E">#CE6F9E</option>
                                                                                <option data-class="dark" value="#404040">#404040</option>
                                                                                <option data-class="grey" value="#848484">#848484</option>
                                                                                <option data-class="default" value="#EEE">#EEE</option>
                                                                            </select>
                                                                        </div>
                                                                    </div>

                                                                    <div class="widget-body">
                                                                        <div class="widget-main no-padding">
                                                                            <table class="table table-striped table-bordered table-hover">
                                                                                <thead class="thin-border-bottom">
                                                                                <tr>
                                                                                    <th>名称</th>
                                                                                    <th>qingqingId</th>

                                                                                    <th class="hidden-480">值</th>
                                                                                </tr>
                                                                                </thead>

                                                                                <tbody>
                                                                                <tr>
                                                                                    <td>父订单ID</td>

                                                                                    <td id="qingqingGroupOrderId">alex@email.com</td>
                                                                                    <td id="groupOrderId">alex@email.com</td>
                                                                                </tr>

                                                                                <tr>
                                                                                    <td class="" >子订单ID</td>

                                                                                    <td id="qingqingOrderId">alex@email.com</td>
                                                                                    <td id="orderId">alex@email.com</td>
                                                                                </tr>

                                                                                <tr>
                                                                                    <td class="">订单状态</td>

                                                                                    <td id="orderStatus"></td>
                                                                                    <td>中文就不告诉你</td>
                                                                                </tr>

                                                                                <tr>
                                                                                    <td>订单类型</td>

                                                                                    <td id="orderTypeValue">alex@email.com</td>
                                                                                    <td></td>
                                                                                </tr>
                                                                                </tbody>
                                                                            </table>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <hr />
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
                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="balanceAmount">第三方支付路径</label>
                                                            <div class="col-xs-12 col-sm-6">
                                                                <div class="table-responsive">
                                                                    <table class="table table-striped table-bordered table-hover">
                                                                        <thead>
                                                                            <tr>
                                                                                <th>支付方式</th>
                                                                                <th>关联记录</th>
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

                                                <div class="step-pane" id="step3">
                                                    <div class="center">
                                                        <h3 class="blue lighter" id = "orderDetail">This is step 3</h3>
                                                        <div class="row">
                                                            <div class="col-xs-12">
                                                                <div class="table-responsive center">
                                                                    <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                                                                        <thead>
                                                                        <tr>
                                                                            <th class="center">
                                                                                <label>
                                                                                    <input type="checkbox" class="ace" />
                                                                                    <span class="lbl"></span>
                                                                                </label>
                                                                            </th>
                                                                            <th>父课程ID</th>
                                                                            <th>子课程ID</th>
                                                                            <th>
                                                                                <i class="icon-time bigger-110 hidden-480"></i>
                                                                                预计开始时间
                                                                            </th>
                                                                            <th>
                                                                                <i class="icon-time bigger-110 hidden-480"></i>
                                                                                预计结束时间
                                                                            </th>
                                                                            <th>价格(含税)</th>
                                                                            <th>价格(不含税)</th>
                                                                            <th class="hidden-480">状态</th>
                                                                            <th>上课 | 三方赔付 | 删课 | 结课</th>
                                                                        </tr>
                                                                        </thead>

                                                                        <tbody id = "orderCourseBody">

                                                                        </tbody>
                                                                    </table>
                                                                </div><!-- /.table-responsive -->
                                                            </div><!-- /span -->
                                                        </div><!-- /row -->
                                                    </div>
                                                </div>

                                                <div class="step-pane" id="step4">
                                                    <div class="center">
                                                        <h3 class="blue lighter">This is step 4</h3>
                                                    </div>
                                                </div>
                                            </div>
                                        </div><!-- /widget-main -->
                                    </div><!-- /widget-body -->
                                </div>
                            </div>
                        </div>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>


            <div class="hide">
                <div  id="payWayOperation">
                    <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                        <button type="button" class="btn btn-xs btn-success mockPayBtn">
                            <i class="icon-ok bigger-120"></i>
                        </button>
                    </div>
                </div>

                <div>
                    <table id="orderCourseRow">
                        <tr>
                            <td class="center">
                                <label>
                                    <input type="checkbox" value="{orderCourseId}" class="ace" />
                                    <span class="lbl"></span>
                                </label>
                            </td>
                            <td>{groupOrderCourseId}</td>
                            <td>{orderCourseId}</td>
                            <td>{startCourseTime}</td>
                            <td>{endCourseTime}</td>
                            <td>{realPrice}</td>
                            <td>{realPriceWithoutTax}</td>

                            <td class="hidden-480">
                                <span class="label label-sm label-warning">{orderStatus}</span>
                            </td>

                            <td class="center">
                                <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                    <button class="btn btn-xs btn-success start-class">
                                        <input type="hidden" value="{groupOrderCourseId}" />
                                        <i class="icon-ok bigger-120"></i>
                                    </button>

                                    <button class="btn btn-xs btn-info apply-freeze">
                                        <input type="hidden" value="{orderCourseId}" />
                                        <i class="icon-edit bigger-120"></i>
                                    </button>

                                    <button class="btn btn-xs btn-danger apply-cancel">
                                        <input type="hidden" value="{orderCourseId}" />
                                        <i class="icon-trash bigger-120"></i>
                                    </button>

                                    <button class="btn btn-xs btn-warning finish-class">
                                        <input type="hidden" value="{orderCourseId}" />
                                        <i class="icon-flag bigger-120"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
        </div>
</div><!-- /.main-container -->
</div>
<script type="text/javascript">
    jQuery(function($) {
        $('#simple-colorpicker-1').ace_colorpicker({pull_right:true}).on('change', function(){
            var color_class = $(this).find('option:selected').data('class');
            var new_class = 'widget-header';
            if(color_class != 'default')  new_class += ' header-color-'+color_class;
            $(this).closest('.widget-header').attr('class', new_class);
        });

        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();

        $(".select2").css('width','200px').select2({allowClear:true})
                .on('change', function(){
                    $(this).closest('form').validate().element($(this));
                });


        var $validation = false;
        $('#fuelux-wizard').ace_wizard().on('change' , function(e, info){
            if(info.step == 1) {
                $("#stageChooseDiv").addClass("hide");
                return addOrder();
            }else if(info.step == 2){
                var orderStatus = $("#orderStatus").text();
                if("已支付" != orderStatus){
                    $.gritter.add({
                        title : '操作提醒:',
                        text : '你需要再次页面完成支付，才能到下一步',
                        class_name : 'gritter-error gritter-center'
                    });
                    return false;
                }

                var coursePriceType = $("#coursePriceType").val();
                if(coursePriceType > 1){ // 朋友团
                    var data = {
                        data : $("#groupOrderId").text(),
                        studentId : $("#studentId").val()
                    }
                    result = commonAjaxRequest("${base}/v1/order/made_up.json", data, notOps, false, "成团失败:");
                }

                orderCourseList();
               return true;
            }
        }).on('finished', function(e) {
            bootbox.dialog({
                message: "Thank you! Your information was successfully saved!",
                buttons: {
                    "success" : {
                        "label" : "OK",
                        "className" : "btn-sm btn-primary"
                    }
                }
            });
        }).on('stepclick', function(e){
            return false;//prevent clicking on steps
        });

        function notOps(resu){

        }

        function addOrder(){
            var result = false;

            var packageCourseId = null;
            var contentPackageId = null;
            var discountType = $("#discountType").val();
            if(discountType == "2"){
                packageCourseId = $("#coursePackageId").val()[0];
            }else if(discountType == "3"){
                contentPackageId = $("#contentPackageId").val()[0];
            }

            var data = {
                studentId : $("#studentId").val(),
                qingqingTeacherId : $("#qingqingTeacherId").val(),
                gradeId : $("#gradeId").val(),
                courseId : $("#courseId").val(),
                orderSiteType : $("#siteType").val(),
                startCourseTime : $("#startCourseTime").val(),
                courseTimes : $("#courseTimes").val(),
                classHour : $("#classHour").val(),
                addressId : $('input[name="studentAddressId"]:checked').val(),
                coursePriceType : $("#coursePriceType").val(),
                packageCourseId : packageCourseId,
                contentPackageId :contentPackageId
            };

            result = commonAjaxRequest("${base}/v1/order/student/add_order.json", data, handlerAddOrder, false, "生成订单失败:");

            return result;
        }

        function orderCourseList(){
            var result = false;
            var data = {
                data : $("#qingqingOrderId").text()
            };

            result = commonAjaxRequest("${base}/v1/order_course/order_course_list.json", data, handlerSubOrderDetail, false, "");

            return result;
        }

        function handlerSubOrderDetail(resu){
            var s = $("#orderCourseRow").html();
            s = s.replace("</tbody>","");
            s = s.replace("<tbody>","");

            var orderCourseText = '';
            for(idx in resu.orderCourseV1List){
                var orderCourse = resu.orderCourseV1List[idx];
                var orderCourseHtml = s.replace(new RegExp("{orderCourseId}","gm"), orderCourse.id);
                orderCourseHtml = orderCourseHtml.replace("{startCourseTime}", formatDate(orderCourse.startCourseTime));
                orderCourseHtml = orderCourseHtml.replace("{endCourseTime}", formatDate(orderCourse.endCourseTime));
                orderCourseHtml = orderCourseHtml.replace("{realPrice}", orderCourse.realPrice);
                orderCourseHtml = orderCourseHtml.replace("{realPriceWithoutTax}", orderCourse.realPriceWithoutTax);
                orderCourseHtml = orderCourseHtml.replace("{orderStatus}", orderStatus(orderCourse.status, orderCourse.freeze));
                orderCourseHtml = orderCourseHtml.replace(new RegExp("{groupOrderCourseId}","gm"), orderCourse.groupOrderCourseId);
                orderCourseText += orderCourseHtml;
            }
            $("#orderCourseBody").html(orderCourseText);

            $(".start-class").click(function(){
                var groupOrderCourseId = $(this).find("input").val();
                var data = {
                    groupOrderCourseId : groupOrderCourseId,
                    teacherId : $("#teacherIdIpt").val()
                }

                commonAjaxRequest("${base}/v1/order_course/teacher/start_class.json", data, handlerCommonOrderOps, false, "点击上课失败：");
            });

            $(".apply-freeze").click(function(){
                var orderCourseId = $(this).find("input").val();
                var data = {
                    data : orderCourseId,
                    studentId : $("#studentId").val()
                }

                commonAjaxRequest("${base}/v1/order_course/student/apply_freeze.json", data, handlerCommonOrderOps, false, "申请三方赔付失败：");
            });

            $(".finish-class").click(function(){
                var orderCourseId = $(this).find("input").val();
                var data = {
                    data : orderCourseId,
                    studentId : $("#studentId").val()
                }

                commonAjaxRequest("${base}/v1/order_course/student/finish_class.json", data, handlerCommonOrderOps, false, "结课失败：");
            });

            $(".apply-cancel").click(function(){
                var orderCourseId = $(this).find("input").val();
                var data = {
                    data : orderCourseId,
                    studentId : $("#studentId").val()
                }

                commonAjaxRequest("${base}/v1/order_course/student/apply_cancel.json", data, handlerApplyCancel, false, "申请删课失败:");
            });
        }

        function handlerApplyCancel(resu){
            $.gritter.add({
                title : '申请删课成功:',
                text : '申请删课成功',
                class_name : 'gritter-info gritter-center'
            });
            orderCourseList();
        }

        function handlerCommonOrderOps(resu){
            orderCourseList();
        }

        function orderStatus(status, freeze){
            switch(status){
                case "not_accpeted":
                case "teacher_accepted":
                    var coursePriceType = $("#coursePriceType").val();
                    var orderStatus = $("#orderStatus").text();
                    if("已支付" == orderStatus && coursePriceType > 1){
                        return "已支付待成团";
                    }

                    return "待支付";
                case 102:
                    return "等待老师确认";
                case "student_payed":
                    return "已支付待上课";
                case "class_started":
                    if(freeze == null || freeze < 4){
                        return "上课中";
                    }else{
                        return "冻结中";
                    }
                case "class_ended":
                    return "已下课";
                case "finished":
                case "system_payed":
                    return "已结课";
                default:
                    return "未知";
            }
        }

        $('#addPayTypeBtn').click(function () {
            return pay();
        })

        var checkPayTimer;
        function pay(){
            var result = false;
            var payType = $("#payType").val();
            var stageConfigIds = $("#stageConfigId").val();
            var stageList = getStageInfo(payType);
            if(stageList != null && stageConfigIds == null){
                $.gritter.add({
                    title : '字段未选择:',
                    text : '请选择分期方案',
                    class_name : 'gritter-error gritter-center'
                });
                return false;
            }

            var stageConfigId;
            if(stageConfigIds != null){
                stageConfigId = stageConfigIds[0];
            }

            var data = {
                        qingqingOrderId : $("#qingqingOrderId").text(),
                        orderAmount : $("#orderAmountTxt").text(),
                        studentId : $("#studentId").val(),
                        coursePriceType : $("#coursePriceType").val(),
                        payType : payType,
                        stageConfigId : stageConfigId
                    };

            result = commonAjaxRequest("${base}/v1/pay/ack_pay.json", data, handlerPay, false, "新增支付路径失败:");
            return result;
        }

        function checkPay(){
            var data = {
                qingqingOrderId : $("#qingqingOrderId").text(),
                orderAmount : $("#orderAmountTxt").text(),
                studentId : $("#studentId").val(),
                coursePriceType : $("#coursePriceType").val(),
                payType : $("#payType").val()
            };

            commonAjaxRequest("${base}/v1/pay/check_pay.json", data, handlerCheckPay, false, "查询支付状态失败:");
        }

        function handlerCheckPay(data){
            if(data.is_pay_success){
                $("#orderStatus").text("已支付");

                $.gritter.add({
                    title : '支付状态变更',
                    text : '订单已支付成功',
                    class_name : 'gritter-info gritter-center'
                });

                if(checkPayTimer != null){
                    clearTimeout(checkPayTimer);
                    checkPayTimer = null;
                }
            }
            return true;
        }

        function handlerPay(resu){
            // 更新支付方式下下拉框
            updatePayWayList();

            var payType = $("#payType").val();
            if(payType == 'qingqing_balance'){
                checkPay();
            }else{
                if(checkPayTimer == null){
                    checkPayTimer = setInterval(checkPay, 5000);
                }
            }

            return true;
        }

        function updatePayWayList(){
            var data = {
                coursePriceType : $("#coursePriceType").val(),
                qingqingOrderId : $("#qingqingOrderId").text()
            };

            commonAjaxRequest("${base}/v1/pay/pay_infos.json", data, handlerPayWayList, true, "获取第三方支付路径出错:");
        }

        var syncPayWayList;
        function handlerPayWayList(resu){
            var trText = "";
            for(idx in resu.payBriefList){
                var payBrief = resu.payBriefList[idx];
                trText = trText + "<tr><td>" + payBrief.payTypeName + "</td><td class=\"hidden-480\">" + payBrief.qingqingTradeNo + "</td><td class=\"hidden-480\"><span class=\"label label-sm label-warning\">" + payBrief.payStatus + "</span></td><td>" + $("#payWayOperation").html() + "</td></tr>";
            }
            $("#payWayList").html(trText);
            $('.mockPayBtn').click(function(){
                var qinqqingTradeNo = $(this).parent().parent().prev("td").prev("td").text().trim();
                var data = {
                    data : qinqqingTradeNo
                };

                commonAjaxRequest("${base}/v1/pay/mock_third_pay.json", data, handlerMockThirdNotify, true, "模拟第三方支付成功通知失败:");


                if(syncPayWayList == null){
                    syncPayWayList = setInterval(updatePayWayList, 5000);
                }
            });
            return true;
        }

        function handlerMockThirdNotify(resu){
            $.gritter.add({
                title : '模拟第三方支付成功通知成功:',
                text : '模拟第三方支付成功通知成功',
                class_name : 'gritter-info gritter-center'
            });
        }

        function prePay(isAsync){
            var data = {
                qingqingOrderId : $("#qingqingOrderId").text(),
                sourceChannel : $("#sourceChannel").val(),
                studentId : $("#studentId").val(),
                coursePriceType : $("#coursePriceType").val()
            };

            return commonAjaxRequest("${base}/v1/pay/pre_pay.json", data, handlerPrePay, isAsync, "获取订单前置接口失败:");
        }

        var installmentConfigs;
        function handlerPrePay(resu){
            // 更新支付方式下下拉框
            updateOptions("payType", resu.supportPayTypeList, "qingqing_balance");
            $("#payType_chosen").css('width','200px');
            $("#sourceChannel_chosen").css('width','200px');

            $("#orderAmountTxt").text(resu.needPayAmount);
            $("#balanceAmountTxt").text(resu.balanceAmount);

            installmentConfigs = resu.installmentConfigs;
        }

        function handlerAddOrder(resu){
            $("#qingqingGroupOrderId").text(resu.qingqingGroupOrderId);
            $("#groupOrderId").text(resu.groupOrderId);
            $("#qingqingOrderId").text(resu.qingqingOrderId);
            $("#orderId").text(resu.orderId);
            $("#orderStatus").text(resu.orderBriefStatus);
            $("#orderTypeValue").text($("#coursePriceType").find("option:selected").text());

            prePay(true);
            updatePayWayList();
            return true;
        }

        $("#sourceChannel").change(function () {
            return prePay(false);
        });

        $("#payType").change(function () {
            var payType = $("#payType").val();
            var stageList = getStageInfo(payType);
            if(stageList != null){
                updateOptions("stageConfigId", stageList, stageList[0].key);
                $("#stageChooseDiv").removeClass("hide");
            }else{
                $("#stageChooseDiv").addClass("hide");
            }
        });

        function getStageInfo(payType){
            for(idx in installmentConfigs){
                var installmentConfig = installmentConfigs[idx];
                if(installmentConfig.payType == payType){
                    var stageConfigs = new Array();
                    var siteIdx = 0;
                    for(var itemIdx in installmentConfig.items){
                        var item = installmentConfig.items[itemIdx];
                        var stageConfig = new Object();
                        stageConfig.key = item.configId;
                        stageConfig.value = "￥" + item.stageAmount +  " x " + item.stageNum + "期（手续费￥" + item.serviceAmount + "）";
                        stageConfigs[siteIdx++] = stageConfig;
                    }
                   return stageConfigs;
                }
            }

            return null;
        }

        function handlerAddress(resu){
            var txt = '';
            if(resu.user_address_details == null || resu.user_address_details.size == 0){
                txt += "<div class=\"radio\"><label><input name=\"studentAddressId\" value=\"0\" type=\"radio\" checked=\"checked\" class=\"ace\" /><span class=\"lbl\">该用户无可用地址</span></label></div>";
            }else{
                for (idx in resu.user_address_details) {
                    var address =  resu.user_address_details[idx];
                    var addressStr = address.address;
                    var id = address.id;

                    var checkedStr = '';
                    if(idx == 0){
                        checkedStr = "checked=\"checked\"";
                    }
                    txt += "<div class=\"radio\"><label><input name=\"studentAddressId\" value=\"" + id + "\" type=\"radio\" " + checkedStr + " class=\"ace\" /><span class=\"lbl\">" + addressStr + "</span></label></div>";
                }
            }
            var addressDiv = document.getElementById("addressRadio");
            addressDiv.innerHTML = txt;
        };

        function validTeacherId(teacherId){
            if(teacherId == null || teacherId < 1){
                $.gritter.add({
                    title : '提示:',
                    text : "老师ID非法",
                    class_name : 'gritter-error gritter-center'
                });
                return false;
            }
            return true;
        };

        function validStudentId(studentId){
            if(studentId == null || studentId < 1){
                $.gritter.add({
                    title : '提示:',
                    text : "学生ID非法",
                    class_name : 'gritter-error gritter-center'
                });
                return false;
            }

            return true;
        };

        $('#teacherIdBtn').click(function () {
            var teacherId = $('#teacherIdIpt').val();
            var studentId = $('#studentIdIpt').val();
            if(!validTeacherId(teacherId)){
                return;
            }
            if(!validStudentId(studentId)){
                return;
            }

            $("#studentId").val(studentId);
            $("#teacherId").val(teacherId);
            var data = {
                studentId : studentId,
                teacherId : teacherId
            };

            var addressData = {
                data : new Number(studentId)
            };

            commonAjaxRequest("${base}/v1/order/teacher/detail_for_order.json", data, handlerTeacherInfo, true, "获取老师信息for订单异常:");
            commonAjaxRequest("${base}/v1/student/addresses.json", addressData, handlerAddress, true, "获取用户地址信息异常:");
        });

        $('#gradeId').change(function(){
            // 更新上门方式选项
            refreshSiteType();
        });

        $('#coursePriceType').change(function(){
            // 更新上门方式选项
            refreshSiteType();
        });

        // 普通订单类型的价格信息
        var courseOrderList;
        // 优惠包
        var coursePackageList;
        // 内容包
        var contentPackageList;

        function handlerTeacherInfo(resu){
            courseOrderList = resu.courseOrderList;
            coursePackageList = resu.coursePackageList;
            contentPackageList = resu.courseContentPackageList;

            $("#qingqingTeacherId").val(resu.qingqingTeacherId);
            // 更新下拉款内容
            updateDiscountType(resu);
            updateOptions("courseId", resu.supportCourseList, null);
            updateOrderSelector();

            $("#orderDetailSelector").removeClass("hide");
        };

        function updateOrderSelector(){
            refreshCoursePriceType();
            refreshGradeId();
            // 更新本地数据和上门方式选项
            refreshSiteType();
        }

        function refreshGradeId(){
            var courseData = getCourseOrderList();
            var coursePriceType = $("#coursePriceType").val();

            var options = new Array();
            var optionIdx = 0;
            for(var idx in courseData) {
                var courseOrder = courseData[idx];
                if(courseOrder.coursePriceType.value == coursePriceType){
                    for(var gradeIdIdx in courseOrder.supprtGradeAndSiteTypeList){
                        var price = courseOrder.supprtGradeAndSiteTypeList[gradeIdIdx];
                        options[optionIdx++] = price.grade;
                    }
                    break;
                }
            }
            updateOptions("gradeId", options, null);
        }

        function updateDiscountType(resu){
            $("#coursePackageChooseDiv").addClass("hide");
            $("#contentPackageChooseDiv").addClass("hide");

            var discountTypes = new Array();
            var siteIdx = 0;

            var discountType = new Object();
            discountType.key = 1;
            discountType.value = '普通';
            discountTypes[siteIdx++] = discountType;

            if(resu.coursePackageList.length > 0){
                discountType = new Object();
                discountType.key = 2;
                discountType.value = '优惠包';
                discountTypes[siteIdx++] = discountType;
            }

            if(resu.courseContentPackageList.length > 0){
                discountType = new Object();
                discountType.key = 3;
                discountType.value = '内容包';
                discountTypes[siteIdx++] = discountType;
            }

            updateOptions("discountType", discountTypes, 1);
        }

        $("#discountType").change(function(){
            $("#coursePackageChooseDiv").addClass("hide");
            $("#contentPackageChooseDiv").addClass("hide");
            $("#courseTimes").removeAttr("readonly");
            $("#courseTimes").val(4);
            $("#classHour").val(20);

            var discountValue = $(this).val();
            if(discountValue == "2"){
                var options = new Array();
                var optionIdx = 0;
                for(idx in coursePackageList){
                    var coursePackage = coursePackageList[idx];

                    var option = new Object();
                    option.key = coursePackage.packageId;
                    option.value = coursePackage.packageName;
                    options[optionIdx++] = option;
                }
                updateOptions("coursePackageId", options, coursePackageList[0].packageId);
                $("#courseTimes").val(coursePackageList[0].courseTimes);
                $("#courseTimes").attr("readonly","readonly");

                $("#coursePackageChooseDiv").removeClass("hide");
            }else if(discountValue == "3"){
                var options = new Array();
                var optionIdx = 0;
                for(idx in contentPackageList){
                    var contentPackage = contentPackageList[idx];

                    var option = new Object();
                    option.key = contentPackage.packageId;
                    option.value = contentPackage.packageName;
                    options[optionIdx++] = option;
                }
                updateOptions("contentPackageId", options, contentPackageList[0].packageId);
                $("#courseTimes").val(contentPackageList[0].classCount);
                $("#classHour").val(contentPackageList[0].classHour);
                $("#courseTimes").attr("readonly","readonly");

                $("#contentPackageChooseDiv").removeClass("hide");
            }


            updateOrderSelector();
        });

        $("#coursePackageId").change(function(){
            var coursePackageId = $(this).val();
            for(idx in coursePackageList) {
                var coursePackage = coursePackageList[idx];
                if(coursePackage.packageId == coursePackageId){
                    $("#courseTimes").val(coursePackage.courseTimes);
                    break;
                }
            }
            updateOrderSelector();
        });

        $("#contentPackageId").change(function(){
            var coursePackageId = $(this).val();
            for(idx in contentPackageList) {
                var coursePackage = contentPackageList[idx];
                if(coursePackage.packageId == coursePackageId){
                    $("#courseTimes").val(coursePackage.classCount);
                    $("#classHour").val(coursePackage.classHour);
                    break;
                }
            }

            updateOrderSelector();
        });


        function refreshCoursePriceType(){ // 更新订单类型
            var courseData = getCourseOrderList();

            var options = new Array();
            var optionIdx = 0;
            for(var idx in courseData) {
                var courseOrder = courseData[idx];

                var option = new Object();
                option.key = courseOrder.coursePriceType.value;
                option.value = courseOrder.coursePriceType.name;
                options[optionIdx++] = option;
            }
            updateOptions("coursePriceType", options, 1);
        }

        function getCourseOrderList(){
            var discountType = $("#discountType").val();
            switch(discountType){
                case "1":
                    return courseOrderList;
                case "2":
                    return get(courseOrderList);
                case "3":
                    var contentPackageId = $("#contentPackageId").val();
                    for(var idx in contentPackageList){
                        var contentPackage = contentPackageList[idx];
                        if(contentPackage.packageId == contentPackageId){
                            return contentPackage.courseOrderBeanList;
                        }
                    }
                    return null;
            }
        }

        function get(courseOrderList){
            var result = new Array();
            var arrIdx = 0;
            for(var idx in courseOrderList) {
                var courseOrder = courseOrderList[idx];
                if(courseOrder.coursePriceType.value == 1){
                    result[arrIdx++] = courseOrder;
                }
            }

            return result;
        }

        function refreshSiteType(){
            var courseData = getCourseOrderList();
            var coursePriceType = $("#coursePriceType").val();
            var gradeId = $("#gradeId").val();
            var siteTypes = new Array();
            var siteIdx = 0;
            for(var idx in courseData){
                var courseOrder = courseData[idx];
                if(courseOrder.coursePriceType.value == coursePriceType){
                    for(var gradeIdx in courseOrder.supprtGradeAndSiteTypeList){
                        var supprtGradeAndSiteType = courseOrder.supprtGradeAndSiteTypeList[gradeIdx];
                        if(supprtGradeAndSiteType.grade.key == gradeId){
                            for(var siteTypeIdx in supprtGradeAndSiteType.siteTypeAnsPriceList){
                                var siteTypeAndPrice = supprtGradeAndSiteType.siteTypeAnsPriceList[siteTypeIdx];
                                var siteType = new Object();
                                siteType.key = siteTypeAndPrice.siteType.value;
                                siteType.value = siteTypeAndPrice.siteType.name + "-(" + siteTypeAndPrice.price +")";
                                siteTypes[siteIdx++] = siteType;
                            }
                            break;
                        }
                    }
                }
            }

            updateOptions("siteType", siteTypes, null);
            var siteType = $("#siteType").val();
            if(siteType == "0"){
                $('#studentAddress').removeClass('hide');
            }else{
                $('#studentAddress').addClass('hide');
            }
        }

        $("#siteType").change(function(){
            var siteType = $(this).val();
            if(siteType == "0"){
                $('#studentAddress').removeClass('hide');
            }else{
                $('#studentAddress').addClass('hide');
            }
        });

        function updateOptions(elementId, data, defaultSelectedKey){
            var selector = document.getElementById(elementId);
            selector.length = 0;

            var option;
            if(data == null || data.size == 0){
                option = document.createElement("option")

                option.text = "当前无可用数据";

                selector.options.add(option);
            }else{
                for(idx in data){
                    var siteType = data[idx];
                    option = document.createElement("option")
                    option.value = siteType.key;
                    option.text = siteType.value;

                    if(defaultSelectedKey == null && idx == 0){
                        option.setAttribute("selected", "selected");
                    }else if(defaultSelectedKey != null && siteType.key == defaultSelectedKey){
                        option.setAttribute("selected", "selected");
                    }

                    selector.options.add(option);
                }
            }
            $("#" + elementId).trigger("chosen:updated");

            $("#" + elementId + "_chosen").css('width','200px');
        };

        $('#skip-validation').removeAttr('checked').on('click', function(){
            $validation = this.checked;
            if(this.checked) {
                $('#sample-form').hide();
                $('#validation-form').removeClass('hide');
            }
            else {
                $('#validation-form').addClass('hide');
                $('#sample-form').show();
            }
        });

        $('.date-picker').datepicker({autoclose:true,minDate:'02-07-2018',gotoCurrent:true}).next().on(ace.click_event, function(){
            $(this).prev().focus();
        });


        jQuery.validator.addMethod("phone", function (value, element) {
            return this.optional(element) || /^\(\d{3}\) \d{3}\-\d{4}( x\d{1,6})?$/.test(value);
        }, "Enter a valid phone number.");

        $('#validation-form').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            rules: {
                email: {
                    required: true,
                    email:true
                },
                password: {
                    required: true,
                    minlength: 5
                },
                password2: {
                    required: true,
                    minlength: 5,
                    equalTo: "#password"
                },
                name: {
                    required: true
                },
                phone: {
                    required: true,
                    phone: 'required'
                },
                url: {
                    required: true,
                    url: true
                },
                comment: {
                    required: true
                },
                state: {
                    required: true
                },
                platform: {
                    required: true
                },
                subscription: {
                    required: true
                },
                gender: 'required',
                agree: 'required'
            },

            messages: {
                email: {
                    required: "Please provide a valid email.",
                    email: "Please provide a valid email."
                },
                password: {
                    required: "Please specify a password.",
                    minlength: "Please specify a secure password."
                },
                subscription: "Please choose at least one option",
                gender: "Please choose gender",
                agree: "Please accept our policy"
            },

            invalidHandler: function (event, validator) { //display error alert on form submit
                $('.alert-danger', $('.login-form')).show();
            },

            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },

            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-info');
                $(e).remove();
            },

            errorPlacement: function (error, element) {
                if(element.is(':checkbox') || element.is(':radio')) {
                    var controls = element.closest('div[class*="col-"]');
                    if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
                    else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
                }
                else if(element.is('.select2')) {
                    error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
                }
                else if(element.is('.chosen-select')) {
                    error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
                }
                else error.insertAfter(element.parent());
            },

            submitHandler: function (form) {
            },
            invalidHandler: function (form) {
            }
        });

        $('#modal-wizard .modal-header').ace_wizard();
        $('#modal-wizard .wizard-actions .btn[data-dismiss=modal]').removeAttr('disabled');

        $("th,td").addClass("center");
        $("#payType_chosen").css("width", "331px;");

        function add0(m){return m<10?'0'+m:m }
        function formatDate(shijianchuo)
        {
            var time = new Date(shijianchuo);
            var y = time.getFullYear();
            var m = time.getMonth()+1;
            var d = time.getDate();
            var h = time.getHours();
            var mm = time.getMinutes();
            var s = time.getSeconds();
            return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
        }
    })
</script>
        </body>
</html>