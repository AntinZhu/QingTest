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
                            <label class="col-sm-3 control-label no-padding-right">加密串：</label>

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
                            <label class="col-sm-3 control-label no-padding-right">订单ID：</label>

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
                    <p>
                        TODO
                    </p>
                </div>
            </div>

            <div class="group">
                <h3 class="accordion-header">用户手机号</h3>

                <div>
                    <p>
                        TODO
                    </p>
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
</script>