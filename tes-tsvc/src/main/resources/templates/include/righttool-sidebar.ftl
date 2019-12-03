<div class="ace-settings-container" style="top: 13px;" id="ace-settings-container">
    <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
        <i class="icon-cog bigger-150"></i>
    </div>

    <div class="sidebar_right hide" id="sidebar_right" style="z-index: 12;">
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
        </script>

        <div class="sidebar-shortcuts" id="sidebar-shortcuts">
            <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            </div>

            <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                <span class="btn btn-success"></span>

                <span class="btn btn-info"></span>

                <span class="btn btn-warning"></span>

                <span class="btn btn-danger"></span>
            </div>
        </div><!-- #sidebar-shortcuts -->

        <div id="qing_tools" class="accordion-style2">
            <div class="group">
                <h3 class="accordion-header">订单加解密</h3>

                <div>
                    <div id="home3" class="tab-pane in active">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">加密串：</label>

                            <div class="col-sm-9">
                                <span class="input-icon">
                                    <input type="text" class="order_conv" id="qingqingOrderId_conv" />
                                    <i class="icon-lock blue"></i>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                                <button class="btn btn-grey btn-sm" id="orderIdConverter">
                                    <i class="icon-refresh"></i>
                                </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">订单ID：</label>

                            <div class="col-sm-9">
                                        <span class="input-icon">
                                            <input type="text" class="order_conv" id="orderId_conv" />
                                            <i class="icon-unlock blue"></i>
                                        </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="group">
                <h3 class="accordion-header">用户ID加解密</h3>

                <div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" style="text-align: right">环境：</label>

                        <div class="col-sm-9">
                            <input type="hidden" id="user_env_conv" value="dev" />
                            <button type="button" value="dev" style="border-radius: 8px" class="btn btn-xs user_env_conv btn-primary">dev</button>
                            <button type="button" value="tst" style="border-radius: 8px" class="btn btn-xs user_env_conv">tst</button>
                            <button type="button" value="fws" style="border-radius: 8px" class="btn btn-xs user_env_conv">fws</button>
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 33px;">
                        <label class="col-sm-3 control-label no-padding-right" style="text-align: right">用户类型：</label>

                        <div class="col-sm-9">
                            <input type="hidden" id="user_type_conv" value="student" />
                            <button type="button" value="student" style="border-radius: 8px" class="btn btn-xs user_type_conv btn-primary">student</button>
                            <button type="button" value="teacher" style="border-radius: 8px" class="btn btn-xs user_type_conv">teacher</button>
                            <button type="button" value="ta" style="border-radius: 8px" class="btn btn-xs user_type_conv">&nbsp;ta&nbsp;</button>
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 67px;">
                        <label class="col-sm-3 control-label no-padding-right" style="text-align: right">加密ID：</label>

                        <div class="col-sm-9">
                            <span class="input-icon">
                                <input type="text" class="user_conv_input" id="qingqingUser_conv" />
                                <i class="icon-lock blue"></i>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                            <button class="btn btn-grey btn-sm" id="userConverter">
                                <i class="icon-refresh"></i>
                            </button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" style="text-align: right">用户ID：</label>

                        <div class="col-sm-9">
                             <span class="input-icon">
                                <input type="text" class="user_conv_input" id="userId_conv" />
                                <i class="icon-unlock blue"></i>
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="group">
                <h3 class="accordion-header">用户手机号</h3>

                <div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" style="text-align: right">环境：</label>

                        <div class="col-sm-9">
                            <input type="hidden" id="user_type_phone_env_conv" value="dev" />
                            <button type="button" value="dev" style="border-radius: 8px" class="btn btn-xs user_type_phone_env_conv btn-primary">dev</button>
                            <button type="button" value="tst" style="border-radius: 8px" class="btn btn-xs user_type_phone_env_conv">tst</button>
                            <button type="button" value="fws" style="border-radius: 8px" class="btn btn-xs user_type_phone_env_conv">fws</button>
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 33px;">
                        <label class="col-sm-3 control-label no-padding-right" style="text-align: right">用户类型：</label>

                        <div class="col-sm-9">
                            <input type="hidden" id="user_type_phone_conv" value="student" />
                            <button type="button" value="student" style="border-radius: 8px" class="btn btn-xs user_type_phone_conv btn-primary">student</button>
                            <button type="button" value="teacher" style="border-radius: 8px" class="btn btn-xs user_type_phone_conv">teacher</button>
                            <button type="button" value="ta" style="border-radius: 8px" class="btn btn-xs user_type_phone_conv">&nbsp;ta&nbsp;</button>
                        </div>
                    </div>

                    <div class="form-group" style="margin-top: 67px;">
                        <label class="col-sm-3 control-label no-padding-right" style="text-align: right">手机号：</label>

                        <div class="col-sm-9">
                            <input type="text" class="phone_user_conv" id="userPhone_conv" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                            <button class="btn btn-grey btn-sm" id="userPhoneConverter">
                                <i class="icon-refresh"></i>
                            </button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" style="text-align: right">用户ID：</label>

                        <div class="col-sm-9">
                            <input type="text" class="phone_user_conv" id="phoneUserId_conv" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="group qing_catelog_hide">
                <h3 class="accordion-header">线上手机号批量解密</h3>

                <div>
                    <form id="onlinePhoneDecodeForm" method="post" enctype="multipart/form-data" target="_blank" action="${base}/v1/utils/phone/online/decode">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">SessionID：</label>

                            <div class="col-sm-9">
                                <input type="text" name="session" id="sessionId" />
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="file" name = "file" id="id-input-file-3" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                                <button type="button" class="btn btn-grey btn-sm" id="onlinePhoneDecodeBtn">
                                    <i class="icon-refresh"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="group">
                <h3 class="accordion-header">手机号加解密</h3>

                <div>
                    <div id="home3" class="tab-pane in active">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">加密串：</label>

                            <div class="col-sm-9">
                                <span class="input-icon">
                                    <input type="text" class="phoneNumber_conv" id="qingqingPhoneNumber_conv" />
                                    <i class="icon-lock blue"></i>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                                <button class="btn btn-grey btn-sm" id="phoneNumberConverter">
                                    <i class="icon-refresh"></i>
                                </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">手机号：</label>

                            <div class="col-sm-9">
                                        <span class="input-icon">
                                            <input type="text" class="phoneNumber_conv" id="phoneNumber_conv" />
                                            <i class="icon-unlock blue"></i>
                                        </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="group qing_catelog_hide">
                <h3 class="accordion-header">这是啥</h3>

                <div>
                    <form id="qing_sql_file_form" method="post" enctype="multipart/form-data" target="_blank" action="${base}/v1/utils/sql/run">
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="file" name = "file" id="qing_sql_file" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                                <button type="button" class="btn btn-grey btn-sm" id="qing_sql_file_btn">
                                    <i class="icon-refresh"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="group qing_catelog_hide">
                <h3 class="accordion-header">头像上传</h3>

                <div>
                    <form id="qing_head_file_form" method="post" enctype="multipart/form-data" target="_blank" action="${base}/v1/user/up/head_image">
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input type="file" name = "file" id="qing_head_file" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                                <button type="button" class="btn btn-grey btn-sm" id="qing_head_file_btn">
                                    <i class="icon-refresh"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="group">
                <h3 class="accordion-header">类全名查找</h3>

                <div>
                    <div id="home3" class="tab-pane in active">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">类名：</label>

                            <div class="col-sm-9">
                                <span class="input-icon">
                                    <input type="text" class="protoClassName_conv" id="protoClassName_simple_conv" />
                                    <i class="icon-lock blue"></i>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                                <button class="btn btn-grey btn-sm" id="protoClassNameConverter">
                                    <i class="icon-refresh"></i>
                                </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">全名：</label>

                            <div class="col-sm-9">
                                        <span class="input-icon">
                                            <input type="text" class="protoClassName_conv" id="protoClassName_full_conv" />
                                            <i class="icon-unlock blue"></i>
                                        </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="group">
                <h3 class="accordion-header">学生端课程报告加解密</h3>

                <div>
                    <div id="home3" class="tab-pane in active">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">加密串：</label>

                            <div class="col-sm-9">
                                <span class="input-icon">
                                    <input type="text" id="studentEncodeReportId_input" />
                                    <i class="icon-lock blue"></i>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                                <button class="btn btn-grey btn-sm" id="studentReportIdBtn">
                                    <i class="icon-refresh"></i>
                                </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">报告id：</label>

                            <div class="col-sm-9">
                                        <span class="input-icon">
                                            <input type="text" id="studentReportId_input" />
                                            <i class="icon-unlock blue"></i>
                                        </span>
                            </div>

                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">学生id：</label>

                            <div class="col-sm-9">
                                        <span class="input-icon">
                                            <input type="text" id="studentReportIdStudentId_input" />
                                            <i class="icon-unlock blue"></i>
                                        </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="group">
                <h3 class="accordion-header">老师端课程报告加解密</h3>

                <div>
                    <div id="home3" class="tab-pane in active">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">加密串：</label>

                            <div class="col-sm-9">
                                <span class="input-icon">
                                    <input type="text" id="teacherEncodeReportId_input" />
                                    <i class="icon-lock blue"></i>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                                <button class="btn btn-grey btn-sm" id="teacherReportIdConverter">
                                    <i class="icon-refresh"></i>
                                </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">报告id：</label>

                            <div class="col-sm-9">
                                        <span class="input-icon">
                                            <input type="text" id="teacherReportId_input" />
                                            <i class="icon-unlock blue"></i>
                                        </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="group">
                <h3 class="accordion-header">时间转换</h3>

                <div>
                    <div id="home3" class="tab-pane in active">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">时间戳：</label>

                            <div class="col-sm-9">
                                <span class="input-icon">
                                    <input type="text" class="qing_time_conv" id="qing_timestamp_input" />
                                    <i class="icon-lock blue"></i>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                                <button class="btn btn-grey btn-sm" id="qing_time_converter">
                                    <i class="icon-refresh"></i>
                                </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">时间：</label>

                            <div class="col-sm-9">
                                        <span class="input-icon">
                                            <input type="text" class="qing_time_conv" id="qing_time_input" />
                                            <i class="icon-unlock blue"></i>
                                        </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="group qing_catelog_hide">
                <h3 class="accordion-header">用户IP查询</h3>

                <div>
                    <div id="home3" class="tab-pane">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">用户IP：</label>

                            <div class="col-sm-9">
                                <span class="input-icon">
                                    <input type="text" class="user_ip_conv" id="user_ip_conv" />
                                    <i class="icon-lock blue"></i>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                                <button class="btn btn-grey btn-sm" id="userIpConverter">
                                    <i class="icon-refresh"></i>
                                </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">用户名：</label>

                            <div class="col-sm-9">
                                        <span class="input-icon">
                                            <input type="text" class="user_ip_conv" id="user_name_conv" />
                                            <i class="icon-unlock blue"></i>
                                        </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="group">
                <h3 class="accordion-header">在线旁听2.0分享码</h3>

                <div>
                    <div id="home3" class="tab-pane in active">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">分享码：</label>

                            <div class="col-sm-9">
                                <span class="input-icon">
                                    <input type="text" id="course_list_v2_code_input" />
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                                <button class="btn btn-grey btn-sm" id="course_listen_v2_converter">
                                    <i class="icon-refresh"></i>
                                </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-5 control-label no-padding-right" style="text-align: right">groupOrderCourseId</label>

                            <div class="col-sm-7">
                                 <span class="input-icon">
                                     <input type="text" id="course_listen_v2_group_order_course_id"/>
                                 </span>
                            </div>

                            <label class="col-sm-4 control-label no-padding-right" style="text-align: right">有效期：</label>
                            <div class="col-sm-8">
                                <div class="input-group">
                                    <input class="form-control date-picker" id="course_listen_v2_timepicker_date" type="text" data-date-format="yyyy-mm-dd"/>
                                    <span class="input-group-addon">
                                        <i class="icon-calendar bigger-110"></i>
                                    </span>
                                </div>

                                <div class="input-group bootstrap-timepicker">
                                    <input id="course_listen_v2_timepicker" type="text" class="form-control lesson_timepicker"/>
                                    <span class="input-group-addon">
																<i class="icon-time bigger-110"></i>
															</span>
                                </div>
                            </div>

                            <label class="col-sm-4 control-label no-padding-right" style="text-align: right">（直播/回放）：</label>
                            <div class="col-sm-8">
                            <span class="input-icon">
                                            <select class="form-control" id="course_listen_v2_code_type">
                                                <option value="1">直播</option>
                                                <option value="2">回放</option>
                                            </select>
                                        </span>

                            </div>

                            <label class="col-sm-4 control-label no-padding-right"
                                   style="text-align: right">admin权限：</label>
                            <div class="col-sm-8">
                            <span class="input-icon">
                                            <select class="form-control" id="course_listen_v2_admin_type">
                                                <option value="true">是</option>
                                                <option value="false">否</option>
                                            </select>
                                        </span>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <div class="group">
                <h3 class="accordion-header">在线课分享码</h3>

                <div>
                    <div id="home3" class="tab-pane in active">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">分享码：</label>

                            <div class="col-sm-9">
                                <span class="input-icon">
                                    <input type="text" id="live_lesson_code_input" />
                                </span>
                            </div>

                            <label class="col-sm-5 control-label no-padding-right"
                                   style="text-align: right">是否校验有效期：</label>
                            <div class="col-sm-7">
                                <span class="input-icon">
                                    <select class="form-control" id="live_lesson_is_need_expire">
                                        <option value="false">否</option>
                                    </select>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                                <button class="btn btn-grey btn-sm" id="live_lesson_converter">
                                    <i class="icon-refresh"></i>
                                </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label no-padding-right" style="text-align: right">OrderCourseId:</label>
                            <div class="col-sm-8">
                                 <span class="input-icon">
                                     <input type="text" id="live_lesson_order_course_id" class="live_lesson_input_data"/>
                                 </span>
                            </div>

                            <label class="col-sm-4 control-label no-padding-right" style="text-align: right">UserId</label>
                            <div class="col-sm-8">
                                 <span class="input-icon">
                                     <input type="text" id="live_lesson_user_id" class="live_lesson_input_data"/>
                                 </span>
                            </div>

                            <label class="col-sm-4 control-label no-padding-right" style="text-align: right">UserType</label>
                            <div class="col-sm-8">
                                <span class="input-icon">
                                            <select class="form-control live_lesson_input_data" id="live_lesson_user_type" >
                                                <option value="1">teacher</option>
                                                <option value="0">student</option>
                                            </select>
                                        </span>
                            </div>

                            <label class="col-sm-4 control-label no-padding-right" style="text-align: right">有效期：</label>
                            <div class="col-sm-8">
                                <div class="input-group">
                                    <input class="form-control date-picker live_lesson_input_data" id="live_lesson_timepicker_date" type="text" data-date-format="yyyy-mm-dd" />
                                    <span class="input-group-addon">
                                        <i class="icon-calendar bigger-110"></i>
                                    </span>
                                </div>

                                <div class="input-group bootstrap-timepicker">
                                    <input id="live_lesson_timepicker" type="text" class="form-control lesson_timepicker live_lesson_input_data"/>
                                    <span class="input-group-addon">
																<i class="icon-time bigger-110"></i>
															</span>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

            </div>

            <div class="group">
                <h3 class="accordion-header">PI-接口校验所需参数</h3>

                <div>
                    <div id="home3" class="tab-pane in active">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">时间戳：</label>

                            <div class="col-sm-9">
                                <span class="input-icon">
                                    <input type="text" class="qing_pi_conv" id="qing_pi_output_timestamp" />
                                    <i class="icon-lock blue"></i>
                                </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right">AuthKey：</label>

                            <div class="col-sm-9">
                                        <span class="input-icon">
                                            <input type="text" class="qing_pi_conv" id="qing_pi_output_auth_key" />
                                            <i class="icon-unlock blue"></i>
                                        </span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 center" style="margin-bottom: 7px;margin-top: 7px;">
                                <button class="btn btn-grey btn-sm" id="qing_pi_converter">
                                    <i class="icon-refresh"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- /#ace-settings-container -->

    <script type="text/javascript">
        $("#orderIdConverter").click(function(){
            var qingqingOrderId = $("#qingqingOrderId_conv").val();
            var orderId = $("#orderId_conv").val();

            if(qingqingOrderId != null && qingqingOrderId != ""){
                $("#orderId_conv").val(decode(qingqingOrderId));
            }else if(orderId != null && orderId != ""){
                $("#qingqingOrderId_conv").val(encode(orderId));
            }
        });

        function decode(dataValue){
            var data = {
                data : dataValue
            };

            var request = {
                url : "${base}/v1/utils/order/decode.json",
                data : data,
                handlerFunc : handlerOrderDecode,
                isASync : false,
                failTitle :"解密结果:"
            };

            return commonAjaxRequest(request);
        }

        function handlerOrderDecode(resu){
            return resu.resultList;
        }

        function encode(dataValue){
            var data = {
                data : dataValue
            };

            var request = {
                url : "${base}/v1/utils/order/encode.json",
                data : data,
                handlerFunc : handlerOrderEncode,
                isASync : false,
                failTitle :"解密结果:"
            };

            return commonAjaxRequest(request);
        }

        $("#ace-settings-btn").click(function () {
            if($("#sidebar_right").hasClass("hide")){
                $("#sidebar_right").removeClass("hide");
            }else{
                $("#sidebar_right").addClass("hide");
            }
        });

        function handlerOrderEncode(resu){
            return resu.resultList;
        }

        $(".order_conv").blur(function(){
            if($(this).val() == ''){
                return;
            }

            var valueKey = this.id;
            $(".order_conv").each(function(key,value){
                if(value.id != valueKey){
                    $(value).val("");
                }
            });
        });

        $(".qing_time_conv").blur(function(){
            if($(this).val() == ''){
                return;
            }

            var valueKey = this.id;
            $(".qing_time_conv").each(function(key,value){
                if(value.id != valueKey){
                    $(value).val("");
                }
            });
        });

        $("#qing_time_converter").click(function(){
            var time = $("#qing_time_input").val();
            var timestamp = $("#qing_timestamp_input").val();

            if(time != null && time != ""){
                $("#qing_timestamp_input").val(toShijiancuo(time));
            }else if(timestamp != null && timestamp != ""){
                $("#qing_time_input").val(formatDate(new Number(timestamp)));
            }
        });


        $('#id-input-file-3').ace_file_input({
            no_file:'No File ...',
            btn_choose:'Choose',
            btn_change:'Change',
            droppable:false,
            onchange:null,
            thumbnail:false //| true | large
            //whitelist:'gif|png|jpg|jpeg'
            //blacklist:'exe|php'
            //onchange:''
            //
        });

        $('#qing_sql_file').ace_file_input({
            no_file:'No File ...',
            btn_choose:'Choose',
            btn_change:'Change',
            droppable:false,
            onchange:null,
            thumbnail:false //| true | large
            //whitelist:'gif|png|jpg|jpeg'
            //blacklist:'exe|php'
            //onchange:''
            //
        });

        $('#qing_head_file').ace_file_input({
            no_file:'No File ...',
            btn_choose:'Choose',
            btn_change:'Change',
            droppable:false,
            onchange:null,
            thumbnail:false, //| true | large
            whitelist:'png|jpg|jpeg'
            //blacklist:'exe|php'
            //onchange:''
            //
        });

        $("#qing_sql_file_btn").click(function(){
            $("#qing_sql_file_form").submit();
        });

        $("#qing_head_file_btn").click(function(){
            $("#qing_head_file_form").submit();
        });

        //jquery accordion
        $( "#qing_tools" ).accordion({
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

        $("#userPhoneConverter").click(function(){
            var phone = $("#userPhone_conv").val();
            var userId = $("#phoneUserId_conv").val();

            if(phone != null && phone != ""){
                phoneUserConvert(phone);
            }else if(userId != null && userId != ""){
                userPhoneConvert(userId)
            }
        });

        $(".user_type_phone_conv").click(function(){
            $(".user_type_phone_conv.btn-primary").removeClass("btn-primary");
            $(this).addClass("btn-primary");
            $("#user_type_phone_conv").val($(this).val());

            $(".phone_user_conv[last_input!='1']").val("");
        });

        $(".user_type_phone_env_conv").click(function(){
            $(".user_type_phone_env_conv.btn-primary").removeClass("btn-primary");
            $(this).addClass("btn-primary");
            $("#user_type_phone_env_conv").val($(this).val());

            $(".phone_user_conv[last_input!='1']").val("");
        });

        $(".user_type_conv").click(function(){
            $(".user_type_conv.btn-primary").removeClass("btn-primary");
            $(this).addClass("btn-primary");
            $("#user_type_conv").val($(this).val());

            $(".user_conv_input[last_input!='1']").val("");
        });

        $(".user_env_conv").click(function(){
            $(".user_env_conv.btn-primary").removeClass("btn-primary");
            $(this).addClass("btn-primary");
            $("#user_env_conv").val($(this).val());

            $(".user_conv_input[last_input!='1']").val("");
        });

        function userPhoneConvert(dataValue){
            $("#userPhone_conv").val("");
            var data = {
                user_id : new Number(dataValue),
                user_type : $("#user_type_phone_conv").val()
            };

            var request = {
                url : "${base}/v1/utils/user_phone/convert.json",
                data : data,
                handlerFunc : handlerUserPhoneConvert,
                isASync : true,
                failTitle :"解密结果:",
                env : $("#user_type_phone_env_conv").val()
            };

            commonAjaxRequest(request);
        }

        function handlerUserPhoneConvert(resu){
            if(resu.resultList == null){
                $.gritter.add({
                    title : "查询结果",
                    text : "未查询到用户手机号",
                    class_name : 'gritter-error gritter-right'
                });
            }else {
                $("#userPhone_conv").val(resu.resultList);
            }
        }

        function phoneUserConvert(dataValue){
            $("#phoneUserId_conv").val("");
            var data = {
                userType : $("#user_type_phone_conv").val(),
                data : dataValue
            };

            var request = {
                url : "${base}/v1/utils/phone_user/convert.json",
                data : data,
                handlerFunc : handlerPhoneUserConvert,
                isASync : false,
                failTitle :"解密结果:",
                env : $("#user_type_phone_env_conv").val()
            };

            commonAjaxRequest(request);
        }

        function handlerPhoneUserConvert(resu){
            if(resu.resultList == null){
                $.gritter.add({
                    title : "查询结果",
                    text : "未查询到用户ID",
                    class_name : 'gritter-error gritter-right'
                });
            }else{
                $("#phoneUserId_conv").val(resu.resultList);
            }
        }

        $(".phone_user_conv").blur(function(){
            if($(this).val() == ''){
                return;
            }

            var valueKey = this.id;
            $(".phone_user_conv").each(function(key,value){
                if(value.id != valueKey){
                    $(value).val("");
                    $(value).attr("last_input", 0);
                }else{
                    $(value).attr("last_input", 1);
                }
            });
        });

        $("#userConverter").click(function(){
            var qingUserId = $("#qingqingUser_conv").val();
            var userId = $("#userId_conv").val();

            if(qingUserId != null && qingUserId != ""){
                decodeUserCov(qingUserId);
            }else if(userId != null && userId != ""){
                encodeUserCov(userId)
            }
        });

        function decodeUserCov(dataValue){
//        $.gritter.add({
//            title : "加密结果",
//            text : "暂不支持该操作，等待后续支持",
//            class_name : 'gritter-error gritter-right'
//        });

            var data = {
                data : dataValue
            };

            var request = {
                url : "${base}/v1/utils/user/decode.json",
                data : data,
                handlerFunc : handlerDecodeUserCov,
                isASync : true,
                failTitle :"解密结果:",
                env : $("#user_env_conv").val()
            };

            commonAjaxRequest(request);
        }

        function encodeUserCov(dataValue){
            $("#qingqingUser_conv").val("");

            encodeUser(dataValue, $("#user_type_conv").val());
        }

        function encodeUser(userId, userType) {
            var data = {
                user_id : new Number(userId),
                user_type : userType
            };

            var request = {
                url : "${base}/v1/utils/user/encode.json",
                data : data,
                handlerFunc : handlerEncodeUserCov,
                isASync : false,
                failTitle :"加密结果:",
                env : $("#user_env_conv").val()
            };

            return commonAjaxRequest(request);
        }

        function handlerDecodeUserCov(resu){
            if(resu.resultList == null){
                $.gritter.add({
                    title : "加密结果",
                    text : "加密失败，请检查用户是否存在",
                    class_name : 'gritter-error gritter-right'
                });
            }else{
                var userType = resu.resultList.userType;
                $("#userId_conv").val(resu.resultList.userId);
                $("#user_type_conv").val(userType);
                $(".user_type_conv.btn-primary").removeClass("btn-primary");
                $(".user_type_conv[value='" + userType + "']").addClass("btn-primary");
            }
        }

        function handlerEncodeUserCov(resu){
            if(resu.resultList == null){
                $.gritter.add({
                    title : "加密结果",
                    text : "加密失败，请检查用户是否存在",
                    class_name : 'gritter-error gritter-right'
                });
            }else{
                $("#qingqingUser_conv").val(resu.resultList);

                return resu.resultList;
            }
        }

        $(".user_conv_input").blur(function(){
            if($(this).val() == ''){
                return;
            }

            var valueKey = this.id;
            $(".user_conv_input").each(function(key,value){
                if(value.id != valueKey){
                    $(value).val("");
                    $(value).attr("last_input", 0);
                }else{
                    $(value).attr("last_input", 1);
                }
            });
        });

        $("#onlinePhoneDecodeBtn").click(function(){
            $("#onlinePhoneDecodeForm").submit();
        });

        $("#phoneNumberConverter").click(function(){
            var qingqingPhoneNumber = $("#qingqingPhoneNumber_conv").val();
            var phoneNumber = $("#phoneNumber_conv").val();

            if(qingqingPhoneNumber != null && qingqingPhoneNumber != ""){
                decodePhoneNumber(qingqingPhoneNumber);
            }else if(phoneNumber != null && phoneNumber != ""){
                encodePhoneNumber(phoneNumber)
            }
        });

        function decodePhoneNumber(qingqingPhoneNumber){
            var data = {
                data : qingqingPhoneNumber
            };

            var request = {
                url : "${base}/v1/utils/phone/decode.json",
                data : data,
                handlerFunc : handlerPhoneDecode,
                isASync : true,
                failTitle :"解密结果:"
            };

            commonAjaxRequest(request);
        }

        function handlerPhoneDecode(resu){
            $("#phoneNumber_conv").val(resu.resultList);
        }

        function encodePhoneNumber(phoneNumber){
            var data = {
                data : phoneNumber
            };

            var request = {
                url : "${base}/v1/utils/phone/encode.json",
                data : data,
                handlerFunc : handlerPhoneEncode,
                isASync : true,
                failTitle :"解密结果:"
            };

            commonAjaxRequest(request);
        }

        function handlerPhoneEncode(resu){
            $("#qingqingPhoneNumber_conv").val(resu.resultList);
        }

        $(".phoneNumber_conv").blur(function(){
            if($(this).val() == ''){
                return;
            }

            var valueKey = this.id;
            $(".phoneNumber_conv").each(function(key,value){
                if(value.id != valueKey){
                    $(value).val("");
                }
            });
        });

        $("#protoClassNameConverter").click(function(){
            var simpleClassName = $("#protoClassName_simple_conv").val();
            if(simpleClassName == null || simpleClassName == ""){
                $.gritter.add({
                    title : "查询结果",
                    text : "请输入类名",
                    class_name : 'gritter-error gritter-right'
                });
                return;
            }

            var data = {
                data : simpleClassName
            };

            var request = {
                url : "${base}/v1/utils/get_full_name.json",
                data : data,
                handlerFunc : handlerFullClassName,
                isASync : true,
                failTitle :"查询结果:"
            };

            commonAjaxRequest(request);
        });

        function handlerFullClassName(resp){
            if(resp.resultList != null && resp.resultList.length > 0){
                $("#protoClassName_full_conv").val(resp.resultList[0]);
            }else{
                $.gritter.add({
                    title : "查询结果",
                    text : "未找到该类",
                    class_name : 'gritter-error gritter-right'
                });
            }
        }

        $("#teacherReportIdConverter").click(function(){
            var encodeReportId = $("#teacherEncodeReportId_input").val();
            var reportId = $("#teacherReportId_input").val();

            if(encodeReportId != null && encodeReportId != ""){
                decodeTeacherReportId(encodeReportId);
            }else if(reportId != null && reportId != ""){
                encodeTeacherReportId(reportId);
            }
        });

        function encodeTeacherReportId(reportId) {
            var data = {
                data : reportId
            };

            var request = {
                url : "${base}/v1/utils/report/teacher/encode.json",
                data : data,
                handlerFunc : handleTeacherReportEncode,
                isASync : true,
                failTitle :"加密结果:"
            };

            commonAjaxRequest(request);
        }

        function decodeTeacherReportId(shareCode) {
            var data = {
                data : shareCode
            };

            var request = {
                url : "${base}/v1/utils/report/teacher/decode.json",
                data : data,
                handlerFunc : handleTeacherReportDecode,
                isASync : true,
                failTitle :"解密结果:"
            };

            commonAjaxRequest(request);
        }

        function handleTeacherReportEncode(r){
            $("#teacherEncodeReportId_input").val(r.resultList);
        }

        function handleTeacherReportDecode(r) {
            $("#teacherReportId_input").val(r.resultList);
        }

        $("#studentReportIdBtn").click(function(){
            var encodeReportId = $("#studentEncodeReportId_input").val();
            var reportId = $("#studentReportId_input").val();
            var studentId=$("#studentReportIdStudentId_input").val();

            if (encodeReportId != null && encodeReportId != "") {
                decodeStudentReportId(encodeReportId);
            } else if (reportId != null && reportId != "" && studentId != null && studentId != "") {
                encodeStudentReportId(reportId, studentId);
            }
        });

        function encodeStudentReportId(reportId, studentId) {
            var data = {
                data: [reportId,studentId]
            };

            var request = {
                url : "${base}/v1/utils/report/student/encode.json",
                data : data,
                handlerFunc : handleStudentReportEncode,
                isASync : true,
                failTitle :"加密结果:"
            };

            commonAjaxRequest(request);
        }

        function decodeStudentReportId(shareCode) {
            var data = {
                data : shareCode
            };

            var request = {
                url : "${base}/v1/utils/report/student/decode.json",
                data : data,
                handlerFunc : handleStudentReportDecode,
                isASync : true,
                failTitle :"解密结果:"
            };

            commonAjaxRequest(request);
        }

        function handleStudentReportEncode(r){
            $("#studentEncodeReportId_input").val(r.resultList);
        }

        function handleStudentReportDecode(r) {
            $("#studentReportId_input").val(r.resultList[0]);
            $("#studentReportIdStudentId_input").val(r.resultList[1]);
        }

        $(".user_ip_conv").blur(function(){
            if($(this).val() == ''){
                return;
            }

            var valueKey = this.id;
            $(".user_ip_conv").each(function(key,value){
                if(value.id != valueKey){
                    $(value).val("");
                }
            });
        });

        $("#userIpConverter").click(function(){
            var userIp = $("#user_ip_conv").val();
            var userName = $("#user_name_conv").val();

            if (!isStringEmpty(userIp)) {
                queryUserName(userIp);
            } else if (!isStringEmpty(userName)) {
                queryUserIp(userName);
            }
        });

        function queryUserName(userIp){
            var data = {
                data : userIp
            };

            var request = {
                url : "${base}/v1/utils/ip/query_by_ip.json",
                data : data,
                handlerFunc : handleQueryUserNameResult,
                isASync : true,
                failTitle :"查询用户IP结果:"
            };

            commonAjaxRequest(request);
        }

        function handleQueryUserNameResult(resu){
            if(isStringEmpty(resu.resultList)){
                $.gritter.add({
                    title : "查询结果",
                    text : "未找到该用户信息",
                    class_name : 'gritter-error gritter-right'
                });
                return;
            }

            $("#user_name_conv").val(resu.resultList);
        }

        function queryUserIp(userName){
            var data = {
                data : userName
            };

            var request = {
                url : "${base}/v1/utils/ip/query_by_name.json",
                data : data,
                handlerFunc : handleQueryUserIpResult,
                isASync : true,
                failTitle :"查询用户IP结果:"
            };

            commonAjaxRequest(request);
        }

        function handleQueryUserIpResult(resu){
            if(isStringEmpty(resu.resultList)){
                $.gritter.add({
                    title : "查询结果",
                    text : "未找到该用户信息",
                    class_name : 'gritter-error gritter-right'
                });
                return;
            }

            $("#user_ip_conv").val(resu.resultList);
        }

        $("#course_listen_v2_converter").click(function(){
            var code = $("#course_list_v2_code_input").val();
            var groupOrderCourse = $("#course_listen_v2_group_order_course_id").val();
            var date = $("#course_listen_v2_timepicker_date").val();
            var time = $("#course_listen_v2_timepicker").val();
            var type = $("#course_listen_v2_code_type").val();
            var admin= $("#course_listen_v2_admin_type").val();


            if (code != null && code != "") {
                decodeCourseListenV2(code);
            } else if (groupOrderCourse != null && groupOrderCourse != "" && date != null && date != "" && time != null && time != "" && type != null && type != "") {
                courseListenV2Encode(groupOrderCourse,date,time,type,admin);
            }
        });

        function decodeCourseListenV2(code){
            var obj = new Object();

            var data = {
                url : "/svc/api/pi/v1/course_listen_v2_test/decode?code="+code,
                param: JSON.stringify(obj),
                userId:1,
                userType : 'admin'
            };

            var request = {
                url : "${base}/v1/common/pi.json",
                data : data,
                handlerFunc : handleCourseListenV2Decode,
                isASync : true,
                failTitle :"解密结果:"
            };

            commonRestAjaxRequest(request);
        }

        $('.lesson_timepicker').timepicker({
            minuteStep: 1,
            showSeconds: true,
            showMeridian: false
        }).next().on(ace.click_event, function(){
            $(this).prev().focus();
        });


        function handleCourseListenV2Decode(r) {
            if(r==null || r==""){
                $.gritter.add({
                    title : '错误',
                    text : '解码失败',
                    class_name : 'gritter-info gritter-center'
                });
            }
            console.log(r);

            var date = formatDate_yyyyMMDD(new Number(r.endEffectiveTime));
            var time = formatDate_hhmmss(new Number(r.endEffectiveTime));
            $("#course_listen_v2_group_order_course_id").val(r.groupOrderCourseId);
            $("#course_listen_v2_timepicker_date").val(date);
            $("#course_listen_v2_admin_type").val(String(r.admin));

            var type = 1
            if (r.shareCodeType == 'playback') {
                type = 2;
            } else {
                type = 1;
            }
            $("#course_listen_v2_timepicker").val(time)
            $("#course_listen_v2_code_type").val(type);
        }

        function courseListenV2Encode(groupOrderCourse,date,time,type,admin) {
            var t=date+" "+time;
            var timep =toShijiancuo(t);

            var obj = new Object();

            var data = {
                url: "/svc/api/pi/v1/course_listen_v2_test/encode?groupOrderCourseId=" + groupOrderCourse + "&endEffectiveTime=" + timep + "&shareCodeTypeValue=" + type + "&isAdmin="+admin,
                param: JSON.stringify(obj),
                userId:1,
                userType : 'admin'
            };

            var request = {
                url : "${base}/v1/common/pi.json",
                data : data,
                handlerFunc : handleCourseListenV2Encode,
                isASync : true,
                failTitle :"加密结果:"
            };

            commonAjaxRequest(request);
        }

        function handleCourseListenV2Encode(r) {
            $("#course_list_v2_code_input").val(r.data);
        }

        $('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
            $(this).prev().focus();
        });

        $("#qing_pi_converter").click(function(){
            var user = {
                user_id :  3856,
                user_type  : 'teacher'
            };

            var request = {
                url : "${base}/v1/test/user/token.json",
                data : user,
                handlerFunc : handlerPiToolFilterFillInvoke,
                isASync : true,
                failTitle :"接口调用异常:"
            };

            commonAjaxRequest(request);
        });

        function handlerPiToolFilterFillInvoke(resu){
            $("#qing_pi_output_timestamp").val(resu.resultList.timestamp);
            $("#qing_pi_output_auth_key").val(resu.resultList.authkey);
        }

        $("#live_lesson_converter").click(function(){
            var code = $("#live_lesson_code_input").val();
            var isNeedExpire= $("#live_lesson_is_need_expire").val();

            var orderCOurseId = $("#live_lesson_order_course_id").val();
            var userId = $("#live_lesson_user_id").val();
            var userType = $("#live_lesson_user_type").val();
            var date = $("#live_lesson_timepicker_date").val();
            var time = $("#live_lesson_timepicker").val();



            if (code != null && code != "") {
                decodeLiveLesson(code, isNeedExpire);
            } else if (orderCOurseId != null && orderCOurseId != "" && date != null && date != "" && time != null && time != "" && userType != null && userType != "" && userId != null && userId != "") {
                liveLessonEncode(orderCOurseId,date,time,userType,userId);
            }
        });

        function decodeLiveLesson(shareCode, isNeedExpire){
            var obj = new Object();

            var data = {
                url : "/svc/api/pi/v1/live_lesson_test/decode?shareCode="+shareCode + "&isNeedCheckExpire=" + isNeedExpire + "&guid=shanyao_test_live_lesson_decode",
                param: JSON.stringify(obj),
                userId:1,
                userType : 'admin'
            };

            var request = {
                url : "${base}/v1/common/pi.json",
                data : data,
                handlerFunc : handleLiveLessonDecode,
                isASync : true,
                failTitle :"解密结果:"
            };

            commonRestAjaxRequest(request);
        }

        function handleLiveLessonDecode(r) {
            if(r==null || r==""){
                $.gritter.add({
                    title : '错误',
                    text : '解码失败',
                    class_name : 'gritter-info gritter-center'
                });
            }
            console.log("result:"+r);

            var date = formatDate_yyyyMMDD(new Number(r.expireTime));
            var time = formatDate_hhmmss(new Number(r.expireTime));
            $("#live_lesson_order_course_id").val(r.orderCourseId);
            $("#live_lesson_timepicker_date").val(date);
            $("#live_lesson_user_id").val(r.userId);

            var userType = "0";
            if ("student" == r.userType) {
                userType = "0";
            }

            if ("teacher" == r.userType) {
                userType = "1";
            }

            $("#live_lesson_user_type").val(userType);
            $("#live_lesson_timepicker").val(time);
        }

        function liveLessonEncode(orderCOurseId,date,time,userType,userId) {
            var t=date+" "+time;
            var timep =toShijiancuo(t);

            var obj = new Object();

            var data = {
                url: "/svc/api/pi/v1/live_lesson_test/encode?orderCourseId=" + orderCOurseId + "&expireTime=" + timep + "&userType=" + userType + "&userId=" + userId + "&guid=shanyao_test_live_lesson_encode",
                param: JSON.stringify(obj),
                userId:1,
                userType : 'admin'
            };

            var request = {
                url : "${base}/v1/common/pi.json",
                data : data,
                handlerFunc : handleLiveLessonEncode,
                isASync : true,
                failTitle :"加密结果:"
            };

            commonAjaxRequest(request);
        }

        function handleLiveLessonEncode(r) {
            $("#live_lesson_code_input").val(r.data);
        }

        $('.live_lesson_input_data').click(function(){
            console.log("click")
            $("#live_lesson_code_input").val('');
        })



    </script>