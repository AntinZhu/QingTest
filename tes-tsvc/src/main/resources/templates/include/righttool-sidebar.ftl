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
                            <button type="button" value="hjl" style="border-radius: 8px" class="btn btn-xs user_env_conv">hjl</button>
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
                            <button type="button" value="hjl" style="border-radius: 8px" class="btn btn-xs user_type_phone_env_conv">hjl</button>
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
            </div>
        </div>
    </div>
</div><!-- /#ace-settings-container -->

<script type="text/javascript">
    $("#orderIdConverter").click(function(){
        var qingqingOrderId = $("#qingqingOrderId_conv").val();
        var orderId = $("#orderId_conv").val();

        if(qingqingOrderId != null && qingqingOrderId != ""){
            decode(qingqingOrderId);
        }else if(orderId != null && orderId != ""){
            encode(orderId)
        }
    });

    function decode(dataValue){
        var data = {
            data : dataValue
        };
        commonAjaxRequest("${base}/v1/utils/order/decode.json", data, handlerOrderDecode, true, "解密结果:");
    }

    function handlerOrderDecode(resu){
        $("#orderId_conv").val(resu.resultList)
    }

    function encode(dataValue){
        var data = {
            data : dataValue
        };
        commonAjaxRequest("${base}/v1/utils/order/encode.json", data, handlerOrderEncode, true, "加密结果:");
    }

    $("#ace-settings-btn").click(function () {
        if($("#sidebar_right").hasClass("hide")){
            $("#sidebar_right").removeClass("hide");
        }else{
            $("#sidebar_right").addClass("hide");
        }
    });

    function handlerOrderEncode(resu){
        $("#qingqingOrderId_conv").val(resu.resultList)
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
        commonAjaxRequest("${base}/v1/utils/user_phone/convert.json", data, handlerUserPhoneConvert, true, "解密结果:", $("#user_type_phone_env_conv").val());
    }

    function handlerUserPhoneConvert(resu){
        if(resu.resultList == null){
            $.gritter.add({
                title : "查询结果",
                text : "未查询到用户手机号",
                class_name : 'gritter-error gritter-center'
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
        commonAjaxRequest("${base}/v1/utils/phone_user/convert.json", data, handlerPhoneUserConvert, true, "解密结果:", $("#user_type_phone_env_conv").val());
    }

    function handlerPhoneUserConvert(resu){
        if(resu.resultList == null){
            $.gritter.add({
                title : "查询结果",
                text : "未查询到用户ID",
                class_name : 'gritter-error gritter-center'
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
        $.gritter.add({
            title : "加密结果",
            text : "暂不支持该操作，等待后续支持",
            class_name : 'gritter-error gritter-center'
        });
    }

    function encodeUserCov(dataValue){
        $("#qingqingUser_conv").val("");
        var data = {
            user_id : new Number(dataValue),
            user_type : $("#user_type_conv").val()
        };
        commonAjaxRequest("${base}/v1/utils/user/encode.json", data, handlerEncodeUserCov, true, "解密结果:", $("#user_env_conv").val());
    }

    function handlerEncodeUserCov(resu){
        if(resu.resultList == null){
            $.gritter.add({
                title : "加密结果",
                text : "加密失败，请检查用户是否存在",
                class_name : 'gritter-error gritter-center'
            });
        }else{
            $("#qingqingUser_conv").val(resu.resultList);
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
</script>