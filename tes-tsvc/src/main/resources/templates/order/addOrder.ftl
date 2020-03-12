<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>下单流程</title>
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
                            <input type="hidden" id="guid" >
                            <a target="_blank" title="点击链接可查看调用日志" data-rel="tooltip" id = "logUrl" href="">
                                <div class="widget-main" id="interfaceUrl"> Took the final exam. Phew! </div>
                            </a>
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
                                                        <#include "/include/env.ftl" />
                                                        <div class="form-group">
                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="teacherIdIpt">老师ID:</label>

                                                            <div class="col-xs-12 col-sm-9">
                                                                <div class="clearfix">
                                                                    <input type="number" name="teacherIdIpt" id="teacherIdIpt" value="3856" class="col-xs-12 col-sm-3" />
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
                                                            <div class="form-group hide packageChooseDiv" id="coursePackageChooseDiv">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="coursePackageId">选择优惠包：</label>
                                                                <div class="col-xs-4 col-sm-4">
                                                                    <div>
                                                                        <select class="width-80 form-control" id="coursePackageId" multiple="multiple">
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-group hide packageChooseDiv" id="contentPackageChooseDiv">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="contentPackageId">选择内容包：</label>
                                                                <div class="col-xs-4 col-sm-4">
                                                                    <div>
                                                                        <select class="width-80 form-control" id="contentPackageId" multiple="multiple">
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-group hide packageChooseDiv" id="classHourPackageChooseDiv">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="classHourPackageId">选择课时包：</label>
                                                                <div class="col-xs-4 col-sm-4">
                                                                    <div>
                                                                        <select class="width-80 form-control" id="classHourPackageId" multiple="multiple">
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
                                                            <div class="form-group hide" id="strengthenTypeDiv">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right">搭配巩固课:</label>

                                                                <div class="col-xs-12 col-sm-3">
                                                                    <select class="width-80 chosen-select" id="strengthenType" data-placeholder="选择巩固课...">
                                                                        <option value="0">&nbsp;</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="form-group hide" id="servicePackageDiv">
                                                                <label class="control-label col-xs-12 col-sm-3 no-padding-right">选择服务包:</label>

                                                                <div class="col-xs-12 col-sm-3">
                                                                    <select class="width-80 chosen-select" id="servicePackage" data-placeholder="选择服务包...">
                                                                        <option value="0">&nbsp;</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="hr hr-dotted"></div>
                                                        </div>

                                                        <!-- 老师的相关信息 -->
                                                        <div class="form-group">
                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="startCourseTime">首次上课日期:</label>
                                                            <div class="input-group col-xs-12 col-sm-6">
                                                                <input class="form-control" id="startCourseTime" type="text" />
                                                                <span class="input-group-addon">
                                                                            <i class="icon-calendar bigger-110"></i>
                                                                        </span>
                                                            </div>
                                                        </div>

                                                        <!-- 老师的相关信息 -->
                                                        <div class="form-group">
                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="startBlock">开始上课时间:</label>
                                                            <div class="input-group col-xs-12 col-sm-6">
                                                                <select class="width-80 chosen-select" id="startBlock" data-placeholder="开始上课时间...">
                                                                    <option value="-4">06:00</option>
                                                                    <option value="-3">06:30</option>
                                                                    <option value="-2">07:00</option>
                                                                    <option value="-1">07:30</option>
                                                                    <option value="0" selected="selected">08:00</option>
                                                                    <option value="1">08:30</option>
                                                                    <option value="2">09:00</option>
                                                                    <option value="3">09:30</option>
                                                                    <option value="4">10:00</option>
                                                                    <option value="5">10:30</option>
                                                                    <option value="6">11:00</option>
                                                                    <option value="7">11:30</option>
                                                                    <option value="8">12:00</option>
                                                                    <option value="9">12:30</option>
                                                                    <option value="10">13:00</option>
                                                                    <option value="11">13:30</option>
                                                                    <option value="12">14:00</option>
                                                                    <option value="13">14:30</option>
                                                                    <option value="14">15:00</option>
                                                                    <option value="15">15:30</option>
                                                                    <option value="16">16:00</option>
                                                                    <option value="17">16:30</option>
                                                                    <option value="18">17:00</option>
                                                                    <option value="19">17:30</option>
                                                                    <option value="20">18:00</option>
                                                                    <option value="21">18:30</option>
                                                                    <option value="22">19:00</option>
                                                                    <option value="23">19:30</option>
                                                                    <option value="24">20:00</option>
                                                                    <option value="25">20:30</option>
                                                                </select>
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
                                                                <input type="number" name="classHour" id="classHour" value="20" />
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

                                                                                    <td id="qingqingGroupOrderId"></td>
                                                                                    <td id="groupOrderId">alex@email.com</td>
                                                                                </tr>

                                                                                <tr>
                                                                                    <td class="" >子订单ID</td>

                                                                                    <td id="qingqingOrderId"></td>
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
                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right">选择优惠券</label>
                                                            <div class="col-xs-12 col-sm-8">
                                                                <select class="width-80 chosen-select tag-input-style" id="vouListSelect" data-placeholder="选择优惠券" multiple="multiple">
                                                                </select>
                                                                <span class="input-group-btn">
																		<button type="button"
                                                                                class="btn btn-purple btn-xs"
                                                                                id="useValueBtn">
																			使用优惠券
																			<i class="icon-search icon-on-right bigger-110"></i>
																		</button>
																	</span>
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
                                                        <div class="form-group">
                                                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="balancePayAmount">钱包支付金额</label>
                                                            <div class="col-xs-12 col-sm-3">
                                                                <input type="number" id="balancePayAmount" value="0" />
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
                                                                                <th>TradeId</th>
                                                                                <th class="hidden-480">Status</th>
                                                                                <th>操作</th>
                                                                                <th>日志</th>
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
                                                                            <th class="hidden-480">
                                                                                状态
                                                                                <a href="#" class="btn btn-app btn-success btn-xs" id="orderCourseStatusRefresh">
                                                                                    <i class="icon-refresh bigger-230"></i>
                                                                                </a>
                                                                            </th>
                                                                            <th>上课 | 三方赔付 | 删课 | 调课申请 | 调课 | 结课 </th>
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

                                <div id="accordion" class="accordion-style2 col-xs-6 hide">
                                    <div class="group">
                                        <h3 class="accordion-header">大礼包组包参考（DEV）：</h3>

                                        <div>
                                            <h3>步骤1：生成子订单<br/></h3>
                                            <blockquote>
                                                <p>老师：40457  科目：语文 优惠包：买5送1<br/></p>
                                                <p>老师：2946   科目：数学 优惠包：买5送1<br/></p>
                                            </blockquote>

                                            <h3>步骤2：生成大礼包<br/></h3>
                                            <blockquote>
                                                <p><a href="${base}/v1/test/json_format?cross=1&id=100&catelogIndex=2-5-6-1###" target="_blank">接口地址</a> </p>
                                                <p>传入加密的子订单ID</p>
                                            </blockquote>

                                            <h3>步骤3：生成合并付订单ID<br/></h3>
                                            <blockquote>
                                                <p><a href="${base}/v1/test/json_format?cross=1&id=101&catelogIndex=2-5-6-2" target="_blank" >接口地址</a> </p>
                                                <p>传入步骤二获得的大礼包ID</p>
                                                <p>结果中可以获得加密的合并付ID，用于支付流程的测试</p>
                                            </blockquote>

                                            <h3>步骤4：支付前置<br/></h3>
                                            <blockquote>
                                                <p><a href="${base}/v1/test/json_format?cross=1&id=11&catelogIndex=2-2-1" target="_blank" >接口地址</a> </p>
                                                <p>传入步骤三获得的合并付ID, 订单类型选择：合并支付订单。</p>
                                            </blockquote>

                                            <h3>步骤5：请求支付<br/></h3>
                                            <blockquote>
                                                <p><a href="${base}/v1/test/json_format?cross=1&id=93&catelogIndex=2-2-2-7" target="_blank" >接口地址</a> </p>
                                                <p>参数示例选择中，选择一种支付方式</p>
                                                <p>订单类型，选择：combined_pay_order_type</p>
                                            </blockquote>
                                        </div>
                                    </div>
                                </div><!-- #accordion -->
                            </div>
                        </div>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div>


            <div class="hide">
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
                                <div class="btn-group">
                                    <button class="btn btn-xs btn-success start-class">
                                        <input type="hidden" value="{groupOrderCourseId}" />
                                        <i class="icon-ok bigger-120"></i>
                                    </button>

                                    <button class="btn btn-xs btn-info apply-freeze">
                                        <input type="hidden" value="{orderCourseId}" />
                                        <i class="icon-edit bigger-120"></i>
                                    </button>

                                    <button class="btn btn-xs btn-danger apply-cancel">
                                        <input type="hidden" value="{groupOrderCourseId}" />
                                        <i class="icon-trash bigger-120"></i>
                                    </button>

                                    <button class="btn btn-xs btn-warning change_apply-class">
                                        <input type="hidden" value="{groupOrderCourseId}" />
                                        <i class=" icon-fighter-jet bigger-120"></i>
                                    </button>

                                    <button class="btn btn-xs btn-warning change-class">
                                        <input type="hidden" value="{groupOrderCourseId}" />
                                        <i class="icon-fire bigger-120"></i>
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

        <#include "/include/righttool-sidebar.ftl" />
</div>
<script type="text/javascript">
    $(document).ready(function(){
        refreshInterfaceUrl();

        initOrderParam();
    });

    var logUrl = "http://172.22.12.14:5601/app/logtrail#/?q=env_type:%20%22{env}%22%20%26%26%20guid:%20%22{guid}%22&t=Now&i=rsyslog-app*&_g=()&h=svc";
    function refreshInterfaceUrl(){
        var env = $("#env").val();
        var guid = generateGuid();

        var url = "查看日志（guid={guid} && env={env}）".replace("{env}", env);
        url = url.replace("{guid}", guid);

        var logTargetUrl = logUrl.replace("{env}", env);
        logTargetUrl = logTargetUrl.replace("{guid}", guid);

        $("#interfaceUrl").text(url);
        $("#guid").val(guid);
        $("#logUrl").attr("href", logTargetUrl);
    }

    function initOrderParam() {
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

        var request = {
            url : "${base}/v1/order/teacher/detail_for_order.json",
            data : data,
            handlerFunc : handlerTeacherInfo,
            isASync : true,
            failTitle :"获取老师信息for订单异常:",
            env : $("#env").val(),
            otherData : null,
            guid : $("#guid").val()
        };

        commonAjaxRequest(request);

        request = {
            url : "${base}/v1/student/addresses.json",
            data : addressData,
            handlerFunc : handlerAddress,
            isASync : true,
            failTitle :"获取用户地址信息异常:",
            env : $("#env").val(),
            otherData : null,
            guid : $("#guid").val()
        };

        commonAjaxRequest(request);
    }

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

    function addOrder(){
        var result = false;

        var packageCourseId = null;
        var contentPackageId = null;
        var classHourPackageId = null;
        var discountType = $("#discountType").val();
        if(discountType == "2"){
            packageCourseId = $("#coursePackageId").val()[0];
        }else if(discountType == "3"){
            contentPackageId = $("#contentPackageId").val()[0];
        }else if(discountType == "4"){
            classHourPackageId =  $("#classHourPackageId").val()[0];
        }

        var strengthenType = $("#strengthenType").val();
        var strengthenData = null;
        var normalTimes = 0;
        var strengthenTimes = 0;
        if(strengthenType > 0){
            var gradeId = $("#gradeId").val();
            var strenghtenData = getStrenghtenData(gradeId);
            for(var idx in strenghtenData){
                var strengthen = strenghtenData[idx];
                if(strengthen.strengthenType == strengthenType){
                    strengthenData = strengthen;
                    break;
                }
            }

            if(strengthenData == null){
                $.gritter.add({
                    title : '数据异常:',
                    text : '巩固课数据异常，看着办吧',
                    class_name : 'gritter-error gritter-center'
                });
                return;
            }

            normalTimes = strengthenData.normalTimes;
            strengthenTimes = strengthenData.strengthenTimes;
        }
        var servicePackageId = 0;
        var selectServicePackageId = $("#servicePackage").val();
        if(selectServicePackageId != null && selectServicePackageId > 0){
            servicePackageId = selectServicePackageId;
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
            contentPackageId :contentPackageId,
            strengthenType :strengthenType,
            normalTimes :normalTimes,
            strengthenTimes :strengthenTimes,
            servicePackageId : servicePackageId,
            startBlock : $("#startBlock").val(),
            classHourPackageId : classHourPackageId
        };

        var request = {
            url : "${base}/v1/order/student/add_order.json",
            data : data,
            handlerFunc : handlerAddOrder,
            isASync : false,
            failTitle :"生成订单失败:",
            env : $("#env").val(),
            otherData : null,
            guid : $("#guid").val()
        };

        result = commonAjaxRequest(request);

        return result;
    }

    var normal_title;
    document.addEventListener('visibilitychange',function(){ //浏览器切换事件
        if(document.visibilityState=='hidden') { //状态判断
            normal_title=document.title;
            document.title='您的订单在这呢，别忘了';
        }else {
            document.title= "来啦，老弟";
            var qingqingOrderId = $("#qingqingOrderId").text();
            if(qingqingOrderId != null && qingqingOrderId != ""){
                orderCourseList("${base}", qingqingOrderId, $("#coursePriceType").val());
            }
        }
    });

    $(document).on("click", ".start-class", function(){
        var groupOrderCourseId = $(this).find("input").val();
        var teacherId = $("#teacherIdIpt").val();
        startClass("${base}", groupOrderCourseId, teacherId);
    });

    $(document).on("click", ".finish-class", function(){
        var orderCourseId = $(this).find("input").val();
        finishClass("${base}", orderCourseId, $("#studentId").val());
    });

    $(document).on("click", ".change-class", function(){
        var groupOrderCourseId = $(this).find("input").val();
        var orderId = $("#orderId").text();
        applyChange("${base}", groupOrderCourseId, orderId);
    });

    $(document).on("click", ".change_apply-class", function(){
        var groupOrderCourseId = $(this).find("input").val();
        var orderId = $("#orderId").text();
        applyChange2("${base}", groupOrderCourseId, orderId);
    });

    $(document).on("click", ".apply-freeze", function(){
        var orderCourseId = $(this).find("input").val();
        var studentId = $("#studentId").val();

        applyFreeze("${base}", orderCourseId, studentId);
    });

    $(document).on("click", ".apply-cancel", function(){
        var groupOrderCourseId = $(this).find("input").val();
       applyCancelOrderCourse("${base}", groupOrderCourseId, 200);
    });

    $(document).on("click", "#useValueBtn", function(){
        var selectList = $("#vouListSelect").val();
        if (selectList === undefined || selectList.length == 0) {
            return;
        }

        var request={};

        var common_order = {};
        common_order["order_type"] = getOrderType($("#coursePriceType").val());
        common_order["qingqing_common_order_id"] = $("#qingqingOrderId").text();

        var order_voucher_items = {};
        order_voucher_items["common_order"] = common_order;
        order_voucher_items["value_voucher_instance_ids"] = selectList.map(Number);

        request["order_voucher_items"]=[order_voucher_items];
        var data = {
            url: "svc/api/pt/v1/valuevouchers/multi_order/use_value_vouchers.json",
            param: JSON.stringify(request),
            userId: $("#studentId").val(),
            userType: "student"
        };

        var request = {
            url : "${base}/v1/common/pt.json",
            data : data,
            handlerFunc : useValue,
            isASync : false,
            failTitle :"用券失败：",
            env : $("#env").val(),
            otherData : null,
            guid : $("#guid").val()
        };

        commonAjaxRequest(request);
    });

    function useValue(r) {
        prePay(true);
    }

    function updateValueVouchers() {
        var data={
            qingqingOrderId: $("#qingqingOrderId").text(),
            studentId:$("#studentId").val()
        }

        var request = {
            url : "${base}/v1/order/student/order/value_voucher_list.json",
            data : data,
            handlerFunc : handleValueVouchers,
            isASync : true,
            failTitle :"获取优惠券出错:",
            env : $("#env").val(),
            otherData : null,
            guid : $("#guid").val()
        };

        commonAjaxRequest(request);
    }

    function handleValueVouchers(r) {
        var options = new Array();
        var index = 0;

        if (r.total_reduce_amount > 0) {
            if (r.value_vouchers != null) {

                for (var v in   r.value_vouchers) {
                    var item = r.value_vouchers[v];
                    var option = new Object();
                    option.key = item.value_voucher_instance_id;
                    option.value = item.name+"（"+item.reward_amount+"）元";
                    option.title="过期时间："+formatTime(item.expire_time,'Y/M/D h:m:s');
                    options[index] = option;
                    index++;
                }

                updateMutiOptions("vouListSelect", options, r.recommend_value_vouchers);
            }
        }

        if(options.length<=0){
            var option = new Object();
            option.key = "-1";
            option.value = "无可用优惠券";
            options[0] = option;
            updateOptions("vouListSelect", options, -1);
        }
    }

    function handlerCommonOrderOps(resu){
        var qingqingOrderId = $("#qingqingOrderId").text();
        orderCourseList("${base}", qingqingOrderId, $("#coursePriceType").val());
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
            stageConfigId : stageConfigId,
            balancePayAmount : $("#balancePayAmount").val(),
            sourceChannel : $("#sourceChannel").val()
        };

        var request = {
            url : "${base}/v1/pay/ack_pay.json",
            data : data,
            handlerFunc : handlerPay,
            isASync : false,
            failTitle :"新增支付路径失败:",
            env : $("#env").val(),
            otherData : null,
            guid : $("#guid").val()
        };

        return commonAjaxRequest(request);
    }

    function checkPay(){
        var data = {
            qingqingOrderId : $("#qingqingOrderId").text(),
            studentId : $("#studentId").val(),
            coursePriceType : $("#coursePriceType").val(),
        };

        var request = {
            url : "${base}/v1/pay/check_pay.json",
            data : data,
            handlerFunc : handlerCheckPay,
            isASync : false,
            failTitle :"查询支付状态失败:",
            env : $("#env").val(),
            otherData : null,
            guid : $("#guid").val()
        };

        return commonAjaxRequest(request);
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

            return true;
        }else{
            return false;
        }
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

        var request = {
            url : "${base}/v1/pay/pay_infos.json",
            data : data,
            handlerFunc : handlerPayWayList,
            isASync : true,
            failTitle :"获取第三方支付路径出错:",
            env : $("#env").val(),
            otherData : null,
            guid : $("#guid").val()
        };

        commonAjaxRequest(request);
    }

    var syncPayWayList;
    function handlerPayWayList(resu){
        if(resu.payBriefList != null){
            $("#payWayList").html(thirdPayWayList(resu.payBriefList));
        }
        $('.mockPayBtn').click(function(){
            var qinqqingTradeNo = $(this).parent().parent().prev("td").prev("td").prev("td").text().trim();
            var data = {
                data : qinqqingTradeNo
            };

            var request = {
                url : "${base}/v1/pay/mock_third_pay.json",
                data : data,
                handlerFunc : handlerMockThirdNotify,
                isASync : true,
                failTitle :"模拟第三方支付成功通知失败:",
                env : $("#env").val(),
                otherData : null,
                guid : $("#guid").val()
            };

            commonAjaxRequest(request);


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

        var request = {
            url : "${base}/v1/pay/pre_pay.json",
            data : data,
            handlerFunc : handlerPrePay,
            isASync : isAsync,
            failTitle :"获取订单前置接口失败:",
            env : $("#env").val(),
            otherData : null,
            guid : $("#guid").val()
        };

        return commonAjaxRequest(request);
    }

    var installmentConfigs;
    function handlerPrePay(resu){
        // 更新支付方式下下拉框
        updateOptions("payType", resu.supportPayTypeList, "qingqing_balance");
        $("#payType_chosen").css('width','200px');
        $("#sourceChannel_chosen").css('width','200px');

        $("#orderAmountTxt").text(resu.needPayAmount);
        var balanceAmount = new Number(resu.balanceAmount)
        $("#balanceAmountTxt").text(balanceAmount.toLocaleString());

        installmentConfigs = resu.installmentConfigs;

        var payType = $("#payType").val();
        if(payType == "qingqing_balance"){
            $("#balancePayAmount").val($("#orderAmountTxt").text());
        }
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
        updateValueVouchers();
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

        if(payType == "qingqing_balance"){
            $("#balancePayAmount").val($("#orderAmountTxt").text());
        }else{
            $("#balancePayAmount").val(0);
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

    $('#teacherIdBtn').click(initOrderParam);

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
    // 巩固包
    var strengthenPackageList;
    // 课时包
    var classHourPackageList;
    function handlerTeacherInfo(resu){
        courseOrderList = resu.courseOrderList;
        coursePackageList = resu.coursePackageList;
        contentPackageList = resu.courseContentPackageList;
        strengthenPackageList = resu.strengthenPackageList;
        classHourPackageList = resu.classHourPackageList;

        $("#qingqingTeacherId").val(resu.qingqingTeacherId);
        // 更新下拉款内容
        updateDiscountType(resu);
        updateOptions("courseId", resu.supportCourseList, null);
        updateOrderSelector();

        $("#orderDetailSelector").removeClass("hide");

        if(resu.servicePackage != null){
            var options = new Array();
            var optionIdx = 0;
//            if(resu.servicePackage.isForce == false){
                var option = new Object();
                option.key = 0;
                option.value = "不购买";
                options[optionIdx++] = option;
//            }

            for(idx in resu.servicePackage.servicePackageInfoList){
                var servicePackage = resu.servicePackage.servicePackageInfoList[idx];

                var option = new Object();
                option.key = servicePackage.id;
                option.value = servicePackage.timeLength + "个月-" + servicePackage.price + (servicePackage.isRecommend == true? "(推荐)":"");
                options[optionIdx++] = option;
            }
            updateOptions("servicePackage", options, null);
            $("#servicePackageDiv").removeClass("hide");
        }
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
        $(".packageChooseDiv").addClass("hide");

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

        if(resu.classHourPackageList.length > 0){
            discountType = new Object();
            discountType.key = 4;
            discountType.value = '课时包';
            discountTypes[siteIdx++] = discountType;
        }

        updateOptions("discountType", discountTypes, 1);
    }

    $("#discountType").change(function(){
        $(".packageChooseDiv").addClass("hide");
        $("#courseTimes").removeAttr("readonly");
        $("#classHour").removeAttr("readonly");
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
        }else if(discountValue == "4"){
            var options = new Array();
            var optionIdx = 0;
            for(idx in classHourPackageList){
                var classHourPackage = classHourPackageList[idx];

                var option = new Object();
                option.key = classHourPackage.packageId;
                option.value = classHourPackage.name;
                options[optionIdx++] = option;
            }

            var firstClassHourPackage = classHourPackageList[0];
            updateOptions("classHourPackageId", options, firstClassHourPackage.packageId);
            $("#courseTimes").val((firstClassHourPackage.feeClassHour + firstClassHourPackage.freeClassHour) / 10);
            $("#classHour").val(10);
            $("#courseTimes").attr("readonly","readonly");
            $("#classHour").attr("readonly","readonly");

            $("#classHourPackageChooseDiv").removeClass("hide");
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

    $("#classHourPackageId").change(function(){
        var classHourPackageId = $(this).val();
        for(idx in classHourPackageList) {
            var coursePackage = classHourPackageList[idx];
            if(coursePackage.packageId == classHourPackageId){
                $("#courseTimes").val((coursePackage.feeClassHour + coursePackage.freeClassHour) / 10);
                $("#classHour").val(10);
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
                return filter(courseOrderList, [1,2,3,4,5,104]);
            case "2":
                return filter(courseOrderList, [1]);
            case "3":
                var contentPackageId = $("#contentPackageId").val();
                for(var idx in contentPackageList){
                    var contentPackage = contentPackageList[idx];
                    if(contentPackage.packageId == contentPackageId){
                        return contentPackage.courseOrderBeanList;
                    }
                }
                return null;
            case "4":
                return filter(courseOrderList, [104])
        }
    }

    function filter(courseOrderList, includeArr){
        var result = new Array();
        var arrIdx = 0;
        for(var idx in courseOrderList) {
            var courseOrder = courseOrderList[idx];
            if(includeArr.includes(courseOrder.coursePriceType.value)){
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
        siteTypeChanged();
    }

    $("#siteType").change(siteTypeChanged);

    function siteTypeChanged(){
        var siteType = $("#siteType").val();
        if(siteType == "0"){
            $('#studentAddress').removeClass('hide');
        }else{
            $('#studentAddress').addClass('hide');
        }

        var strengthens = getSupportStrengthen();
        updateOptions("strengthenType", strengthens, null);
        if(strengthens.length > 1){
            $('#strengthenTypeDiv').removeClass('hide');
        }else{
            $('#strengthenTypeDiv').addClass('hide');
        }
    }

    function getSupportStrengthen(){
        var strengthens = new Array();
        var strengthen = new Object();
        strengthen.key = 0;
        strengthen.value = "不搭配";
        strengthens[0] = strengthen;

        var siteType = $("#siteType").val();
        // 线下上门方式，展现巩固包类型选择
        if(siteType == "0" || siteType == "1"){
            var discountType = $("#discountType").val();
            var coursePriceType = $("#coursePriceType").val();
            if(discountType == "1" && coursePriceType == "1"){ // 普通1V1订单
                var gradeId = $("#gradeId").val();

                var data = getStrenghtenData(gradeId);
                if(data != null){
                    for(var sIdx in data){
                        var stren = data[sIdx];
                        strengthen = new Object();
                        strengthen.key = stren.strengthenType;
                        strengthen.value = stren.normalTimes + ":" + stren.strengthenTimes;
                        strengthens[sIdx + 1] = strengthen;
                    }
                }
            }
        }

        return strengthens;
    }

    function getStrenghtenData(gradeId){
        for(var idx in strengthenPackageList){
            var strengthenPackage = strengthenPackageList[idx];
            if(strengthenPackage.gradeId == gradeId){
                return strengthenPackage.strengthenInfos;
            }
        }

        return null;
    }

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

    $(".env").click(function(){
        $(".env.btn-primary").removeClass("btn-primary");
        $(this).addClass("btn-primary");
        $("#env").val($(this).val());

        refreshInterfaceUrl();
        initOrderParam();
    });

    jQuery(function($) {
        $( "#accordion" ).accordion({
            collapsible: true ,
            heightStyle: "content",
            animate: 250,
            header: ".accordion-header"
        }).sortable({
            axis: "y",
            handle: ".accordion-header",
            stop: function( event, ui ) {
                // IE doesn't register the blur when sorting
                // so trigger focusout handlers to remove .ui-state-focus
                ui.item.children( ".accordion-header" ).triggerHandler( "focusout" );
            }
        });

        $('#simple-colorpicker-1').ace_colorpicker({pull_right:true}).on('change', function(){
            var color_class = $(this).find('option:selected').data('class');
            var new_class = 'widget-header';
            if(color_class != 'default')  new_class += ' header-color-'+color_class;
            $(this).closest('.widget-header').attr('class', new_class);
        });

        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();

        $("#orderCourseStatusRefresh").click(function(){
            var qingqingOrderId = $("#qingqingOrderId").text();
            orderCourseList("${base}", qingqingOrderId, $("#coursePriceType").val());
        });

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
                if("已支付" != orderStatus && !checkPay()){
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
                    var request = {
                        url : "${base}/v1/order/made_up.json",
                        data : data,
                        handlerFunc : notOps,
                        isASync : false,
                        failTitle :"成团失败:",
                        env : $("#env").val(),
                        otherData : null,
                        guid : $("#guid").val()
                    };

                    result = commonAjaxRequest(request);
                }

                var qingqingOrderId = $("#qingqingOrderId").text();
                orderCourseList("${base}", qingqingOrderId, $("#coursePriceType").val());
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
        $("#payType_chosen").css("width", "331px");
        $("#startBlock_chosen").css("width", "160px");
        var startDate = new Date();
        startDate.setDate(startDate.getDate() + 1);
        $("#startCourseTime").datepicker({format : 'yyyy-mm-dd', startDate: formatDate(startDate.getTime()),autoclose:true}).next().on(ace.click_event, function(){
            $(this).prev().focus();
        });

        $("#startCourseTime").val(startDate.format("yyyy-MM-dd"));
    });
</script>
        </body>
</html>