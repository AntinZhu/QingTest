<div class="step-pane" id="step1">
    <h3 class="lighter block green">下单参数选择</h3>
    <div class="hr hr-dotted"></div>
    <form class="form-horizontal" id="validation-form" method="get" novalidate="novalidate">
        <div class="form-group">
            <label class="control-label col-xs-12 col-sm-3 no-padding-right" style="text-align: right;" for="env">环境选择:</label>

            <div class="col-xs-12 col-sm-9">
                <div class="clearfix">
                    <input type="hidden" name="env" id="env" value="dev" class="col-xs-12 col-sm-3">
                    <button type="button" value="dev" style="border-radius: 8px" class="btn env btn-primary">开发环境</button>
                    <button type="button" value="fws" style="border-radius: 8px" class="btn env">接口测试环境</button>
                    <button type="button" value="tst" style="border-radius: 8px" class="btn env">测试环境</button>
                    <button type="button" value="pfm" style="border-radius: 8px;display: none" class="btn env">性能测试环境</button>
                    <button type="button" value="on_line" style="border-radius: 8px;display: none" class="btn env">线上环境</button>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-12 col-sm-3 no-padding-right" style="text-align: right;" for="env">本地调试开关:</label>


            <div class="col-xs-12 col-sm-9">
                <label class="pull-left inline" title="" data-rel="tooltip" data-original-title="开启会调本地接口">
                    <input id="isLocalDebug" type="checkbox" class="ace ace-switch ace-switch-5" value="0">
                    <span class="lbl"></span>
                </label>
                <div class="isLocalDebug hide">
                    <input class="col-xs-3" type="number" id="localDebugPort" placeholder="本地服务端口" value="8080">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="teacherIdIpt">老师ID:</label>

            <div class="col-xs-12 col-sm-9">
                <div class="clearfix">
                    <input type="number" name="teacherIdIpt" id="teacherIdIpt" value="3856" class="col-xs-12 col-sm-3">
                    <input type="hidden" name="qingqingTeacherId" id="qingqingTeacherId" value="615667425">
                    <input type="hidden" name="teacherId" id="teacherId" value="3856">
                    <span class="input-group-btn">
																		<button type="button" class="btn btn-purple btn-xs" id="teacherIdBtn">
																			Search
																			<i class="icon-search icon-on-right bigger-110"></i>
																		</button>
																	</span>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="studentIdIpt">学生ID:</label>

            <div class="col-xs-12 col-sm-9">
                <div class="clearfix">
                    <input type="number" name="studentIdIpt" id="studentIdIpt" value="22367" class="col-xs-12 col-sm-3">
                    <input type="hidden" name="studentId" id="studentId" value="22367">
                </div>
            </div>
        </div>

        <div class="space-2"></div>/


        <!-- 年级科目信息 -->
        <div id="orderDetailSelector" class="">
            <div class="form-group">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="className">小组课名称:</label>
                <div class="col-xs-12 col-sm-3">
                                                                     <span class="input-icon">
                                                                        <input type="text" id="className" value="我是小组课">
                                                                        <i class="icon-leaf blue"></i>
                                                                    </span>
                </div><!-- /span -->
            </div>
            <div class="form-group">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="courseDesc">课程简介:</label>
                <div class="col-xs-12 col-sm-3">
                                                                     <span class="input-icon">
                                                                        <input type="text" id="courseDesc" value="我是课程简介">
                                                                        <i class="icon-leaf blue"></i>
                                                                    </span>
                </div><!-- /span -->
            </div>
            <div class="form-group">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="timeDesc">课程时间简介:</label>
                <div class="col-xs-12 col-sm-3">
                                                                     <span class="input-icon">
                                                                        <input type="text" id="timeDesc" value="我是课程时间简介">
                                                                        <i class="icon-leaf blue"></i>
                                                                    </span>
                </div><!-- /span -->
            </div>
            <div class="form-group ">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right">年级:</label>

                <div class="col-xs-12 col-sm-3">
                    <select class="width-80 chosen-select" id="gradeId" data-placeholder="选择年级..." style="display: none;">

                        <option value="1" selected="selected">小学一年级</option><option value="2">小学二年级</option><option value="7">初中一年级</option></select><div class="chosen-container chosen-container-single" style="width: 200px;" title="" id="gradeId_chosen"><a class="chosen-single" tabindex="-1"><span>小学一年级</span><div><b></b></div></a><div class="chosen-drop"><div class="chosen-search"><input type="text" autocomplete="off"></div><ul class="chosen-results"></ul></div></div>
                </div>
            </div>
            <div class="form-group ">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right">科目:</label>

                <div class="col-xs-12 col-sm-3">
                    <select class="width-80 chosen-select" id="courseId" data-placeholder="选择科目..." style="display: none;">

                        <option value="1" selected="selected">语文</option></select><div class="chosen-container chosen-container-single" style="width: 200px;" title="" id="courseId_chosen"><a class="chosen-single" tabindex="-1"><span>语文</span><div><b></b></div></a><div class="chosen-drop"><div class="chosen-search"><input type="text" autocomplete="off"></div><ul class="chosen-results"></ul></div></div>
                </div>
            </div>
            <div class="form-group ">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right">教材:</label>

                <div class="col-xs-12 col-sm-3">
                    <select class="width-80 chosen-select" id="textCategoryId" data-placeholder="选择教材..." style="display: none;">

                        <option value="3" selected="selected">安徽版</option><option value="5">北师大版</option><option value="62">北京课改版</option><option value="55">长春社</option><option value="6">鄂教版</option><option value="8">河北大学版</option><option value="9">河教版</option><option value="11">沪教版</option><option value="14">冀教版</option><option value="17">科技版</option><option value="18">科教版</option><option value="31">人教版</option><option value="32">人教标准版</option><option value="34">山教版</option><option value="38">苏教版</option><option value="40">西师大版</option><option value="43">湘教版</option><option value="52">语文社</option></select><div class="chosen-container chosen-container-single" style="width: 200px;" title="" id="textCategoryId_chosen"><a class="chosen-single" tabindex="-1"><span>安徽版</span><div><b></b></div></a><div class="chosen-drop"><div class="chosen-search"><input type="text" autocomplete="off"></div><ul class="chosen-results"></ul></div></div>
                </div>
            </div>
            <div class="form-group ">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right">价格:</label>

                <div class="col-xs-12 col-sm-3">
                    <input type="number" name="price" id="price" value="0" class="col-xs-12 col-sm-3">
                </div>
            </div>

            <div class="form-group ">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right">最小开班学生数:</label>

                <div class="col-xs-12 col-sm-3">
                    <input type="number" name="minStudentCnt" id="minStudentCnt" value="2" class="col-xs-12 col-sm-3">
                </div>
            </div>

            <div class="form-group ">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right">最大学生数:</label>

                <div class="col-xs-12 col-sm-3">
                    <input type="number" name="maxStudentCnt" id="maxStudentCnt" value="5" class="col-xs-12 col-sm-3">
                </div>
            </div>

            <!-- 老师的相关信息 -->
            <div class="form-group hide">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="startCourseTime">首次上课时间:</label>
                <div class="input-group col-xs-12 col-sm-6">
                    <input class="form-control date-picker hasDatepicker" id="id-date-picker-1" type="startCourseTime" data-date-format="yyyy-mm-dd">
                    <span class="input-group-addon">
                                                                            <i class="icon-calendar bigger-110"></i>
                                                                        </span>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="courseTimes">课次:</label>
                <div class="col-xs-12 col-sm-3">
                    <input type="number" name="courseTimes" id="courseTimes" value="4">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="classHour">课时:</label>
                <div class="col-xs-12 col-sm-3">
                    <input type="number" name="classHour" id="classHour" disabled="disabled" value="20">
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

        </div></form>

    <div class="step-pane active" id="step2">
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
                                <select id="simple-colorpicker-1" class="hide" style="display: none;">
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
                                </select><div class="dropdown dropdown-colorpicker"><a data-toggle="dropdown" class="dropdown-toggle" href="#"><span class="btn-colorpicker" style="background-color:#307ECC"></span></a><ul class="dropdown-menu dropdown-caret pull-right"><li><a class="colorpick-btn selected" href="#" style="background-color:#307ECC;" data-color="#307ECC"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#5090C1;" data-color="#5090C1"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#6379AA;" data-color="#6379AA"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#82AF6F;" data-color="#82AF6F"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#2E8965;" data-color="#2E8965"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#5FBC47;" data-color="#5FBC47"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#E2755F;" data-color="#E2755F"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#E04141;" data-color="#E04141"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#D15B47;" data-color="#D15B47"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#FFC657;" data-color="#FFC657"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#7E6EB0;" data-color="#7E6EB0"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#CE6F9E;" data-color="#CE6F9E"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#404040;" data-color="#404040"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#848484;" data-color="#848484"></a></li><li><a class="colorpick-btn" href="#" style="background-color:#EEE;" data-color="#EEE"></a></li></ul></div>
                            </div>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main no-padding">
                                <table class="table table-striped table-bordered table-hover">
                                    <thead class="thin-border-bottom">
                                    <tr>
                                        <th class="center">名称</th>
                                        <th class="center">qingqingId</th>

                                        <th class="hidden-480 center">值</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <tr>
                                        <td class="center">Class Order ID</td>

                                        <td id="qingqingClassOrderId" class="center"></td>
                                        <td id="classOrderId" class="center">336</td>
                                    </tr>

                                    <tr>
                                        <td class="center">父订单ID</td>

                                        <td id="qingqingGroupOrderId" class="center">166056268393</td>
                                        <td id="groupOrderId" class="center">50016958</td>
                                    </tr>

                                    <tr>
                                        <td class="center">子订单ID</td>

                                        <td id="qingqingOrderId" class="center">224016078909</td>
                                        <td id="orderId" class="center">50111348</td>
                                    </tr>

                                    <tr>
                                        <td class="center">订单状态</td>

                                        <td id="orderStatus" class="center">待支付</td>
                                        <td class="center">中文就不告诉你</td>
                                    </tr>

                                    <tr>
                                        <td class="center">订单类型</td>

                                        <td id="orderTypeValue" class="center">103</td>
                                        <td class="center"></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <hr>
            <div class="form-group">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="sourceChannel">切换支付场景</label>
                <div class="col-xs-12 col-sm-8">
                    <select class="width-80 chosen-select" id="sourceChannel" data-placeholder="选择支付场景..." style="display: none;">
                        <option value="1" se="">APP内</option>
                        <option value="2">H5浏览器内</option>
                        <option value="3">H5微信内</option>
                        <option value="4">H5和教育</option>
                        <option value="5">pc端</option>
                    </select><div class="chosen-container chosen-container-single" style="width: 200px;" title="" id="sourceChannel_chosen"><a class="chosen-single" tabindex="-1"><span>APP内</span><div><b></b></div></a><div class="chosen-drop"><div class="chosen-search"><input type="text" autocomplete="off"></div><ul class="chosen-results"></ul></div></div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="payType">选择支付方式</label>
                <div class="col-xs-12 col-sm-8">
                    <select class="width-80 chosen-select" id="payType" data-placeholder="选择支付方式..." style="display: none;">

                        <option value="qingqing_balance" selected="selected">轻轻钱包</option><option value="alipay">支付宝</option><option value="multiple_pay">分次支付-(折叠)</option><option value="weixin_pay">微信-(折叠)</option><option value="new_unionpay">新银联支付-(折叠)</option><option value="apple_pay">Apple Pay-(折叠)</option></select><div class="chosen-container chosen-container-single" style="width: 200px;" title="" id="payType_chosen"><a class="chosen-single" tabindex="-1"><span>轻轻钱包</span><div><b></b></div></a><div class="chosen-drop"><div class="chosen-search"><input type="text" autocomplete="off"></div><ul class="chosen-results"></ul></div></div>
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
                    <input type="number" id="balancePayAmount" value="0">
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
                            <span id="orderAmountTxt">160.0</span>
                        </strong>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="balanceAmount">钱包余额</label>
                <div class="col-xs-12 col-sm-3">
                    <div class="alert alert-danger center">
                        <strong>
                            <span id="balanceAmountTxt">999,999,999,911,804.4</span>
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
                                <th class="center">支付方式</th>
                                <th class="center">关联记录</th>
                                <th class="center">TradeId</th>
                                <th class="hidden-480 center">Status</th>
                                <th class="center">操作</th>
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
            <h3 class="blue lighter" id="orderDetail">This is step 3</h3>
            <div class="row">
                <div class="col-xs-12">
                    <div class="table-responsive center">
                        <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label>
                                        <input type="checkbox" class="ace">
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th class="center">父课程ID</th>
                                <th class="center">子课程ID</th>
                                <th class="center">
                                    <i class="icon-time bigger-110 hidden-480"></i>
                                    预计开始时间
                                </th>
                                <th class="center">
                                    <i class="icon-time bigger-110 hidden-480"></i>
                                    预计结束时间
                                </th>
                                <th class="center">价格(含税)</th>
                                <th class="center">价格(不含税)</th>
                                <th class="hidden-480 center">状态</th>
                                <th class="center">上课 | 三方赔付 | 删课 | 结课 | </th>
                            </tr>
                            </thead>

                            <tbody id="orderCourseBody">
                            <tr>
                                <td class="center">
                                    <label>
                                        <input type="checkbox" value="529502" class="ace">
                                        <span class="lbl"></span>
                                    </label>
                                </td>
                                <td class="center">50229066</td>
                                <td class="center">529502</td>
                                <td class="center">2019-05-17 08:00:00</td>
                                <td class="center">2019-05-17 10:00:00</td>
                                <td class="center">40</td>
                                <td class="center">38</td>

                                <td class="hidden-480 center">
                                    <span class="label label-sm label-warning">待支付</span>
                                </td>

                                <td class="center">
                                    <div class="btn-group">
                                        <button class="btn btn-xs btn-success start-class">
                                            <input type="hidden" value="50229066">
                                            <i class="icon-ok bigger-120"></i>
                                        </button>

                                        <button class="btn btn-xs btn-info apply-freeze">
                                            <input type="hidden" value="529502">
                                            <i class="icon-edit bigger-120"></i>
                                        </button>

                                        <button class="btn btn-xs btn-danger apply-cancel">
                                            <input type="hidden" value="529502">
                                            <i class="icon-trash bigger-120"></i>
                                        </button>

                                        <button class="btn btn-xs btn-warning finish-class">
                                            <input type="hidden" value="529502">
                                            <i class="icon-flag bigger-120"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td class="center">
                                    <label>
                                        <input type="checkbox" value="529503" class="ace">
                                        <span class="lbl"></span>
                                    </label>
                                </td>
                                <td class="center">50229067</td>
                                <td class="center">529503</td>
                                <td class="center">2019-05-17 10:00:00</td>
                                <td class="center">2019-05-17 12:00:00</td>
                                <td class="center">40</td>
                                <td class="center">38</td>

                                <td class="hidden-480 center">
                                    <span class="label label-sm label-warning">待支付</span>
                                </td>

                                <td class="center">
                                    <div class="btn-group">
                                        <button class="btn btn-xs btn-success start-class">
                                            <input type="hidden" value="50229067">
                                            <i class="icon-ok bigger-120"></i>
                                        </button>

                                        <button class="btn btn-xs btn-info apply-freeze">
                                            <input type="hidden" value="529503">
                                            <i class="icon-edit bigger-120"></i>
                                        </button>

                                        <button class="btn btn-xs btn-danger apply-cancel">
                                            <input type="hidden" value="529503">
                                            <i class="icon-trash bigger-120"></i>
                                        </button>

                                        <button class="btn btn-xs btn-warning finish-class">
                                            <input type="hidden" value="529503">
                                            <i class="icon-flag bigger-120"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td class="center">
                                    <label>
                                        <input type="checkbox" value="529504" class="ace">
                                        <span class="lbl"></span>
                                    </label>
                                </td>
                                <td class="center">50229068</td>
                                <td class="center">529504</td>
                                <td class="center">2019-05-17 12:00:00</td>
                                <td class="center">2019-05-17 14:00:00</td>
                                <td class="center">40</td>
                                <td class="center">38</td>

                                <td class="hidden-480 center">
                                    <span class="label label-sm label-warning">待支付</span>
                                </td>

                                <td class="center">
                                    <div class="btn-group">
                                        <button class="btn btn-xs btn-success start-class">
                                            <input type="hidden" value="50229068">
                                            <i class="icon-ok bigger-120"></i>
                                        </button>

                                        <button class="btn btn-xs btn-info apply-freeze">
                                            <input type="hidden" value="529504">
                                            <i class="icon-edit bigger-120"></i>
                                        </button>

                                        <button class="btn btn-xs btn-danger apply-cancel">
                                            <input type="hidden" value="529504">
                                            <i class="icon-trash bigger-120"></i>
                                        </button>

                                        <button class="btn btn-xs btn-warning finish-class">
                                            <input type="hidden" value="529504">
                                            <i class="icon-flag bigger-120"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <td class="center">
                                    <label>
                                        <input type="checkbox" value="529505" class="ace">
                                        <span class="lbl"></span>
                                    </label>
                                </td>
                                <td class="center">50229069</td>
                                <td class="center">529505</td>
                                <td class="center">2019-05-17 14:00:00</td>
                                <td class="center">2019-05-17 16:00:00</td>
                                <td class="center">40</td>
                                <td class="center">38</td>

                                <td class="hidden-480 center">
                                    <span class="label label-sm label-warning">待支付</span>
                                </td>

                                <td class="center">
                                    <div class="btn-group">
                                        <button class="btn btn-xs btn-success start-class">
                                            <input type="hidden" value="50229069">
                                            <i class="icon-ok bigger-120"></i>
                                        </button>

                                        <button class="btn btn-xs btn-info apply-freeze">
                                            <input type="hidden" value="529505">
                                            <i class="icon-edit bigger-120"></i>
                                        </button>

                                        <button class="btn btn-xs btn-danger apply-cancel">
                                            <input type="hidden" value="529505">
                                            <i class="icon-trash bigger-120"></i>
                                        </button>

                                        <button class="btn btn-xs btn-warning finish-class">
                                            <input type="hidden" value="529505">
                                            <i class="icon-flag bigger-120"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>
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