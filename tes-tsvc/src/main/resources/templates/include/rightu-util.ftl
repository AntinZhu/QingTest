<div class="ace-settings-container" id="ace-settings-container">
    <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
        <i class="icon-cog bigger-150"></i>
    </div>

    <div class="ace-settings-box" id="ace-settings-box" style="width: auto; height: 200px">
        <div class="tabbable tabs-left">
            <ul class="nav nav-tabs" id="myTab3">
                <li class="active">
                    <a data-toggle="tab" href="#home3">
                        <i class="pink icon-dashboard bigger-110"></i>
                        订单
                    </a>
                </li>

                <li>
                    <a data-toggle="tab" href="#profile3">
                        <i class="blue icon-user bigger-110"></i>
                        用户
                    </a>
                </li>

                <li>
                    <a data-toggle="tab" href="#dropdown13">
                        <i class="icon-rocket"></i>
                        More
                    </a>
                </li>
            </ul>

            <div class="tab-content">
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

                <div id="profile3" class="tab-pane">
                    <p>TODO.</p>
                </div>

                <div id="dropdown13" class="tab-pane">
                    <p>TODO</p>
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

        var request = {
            url : "${base}/v1/utils/order/decode.json",
            data : data,
            handlerFunc : handlerOrderDecode,
            isASync : true,
            failTitle :"解密结果:"
        };

        commonAjaxRequest(request);
    }

    function handlerOrderDecode(resu){
        $("#orderId_conv").val(resu.resultList)
    }

    function encode(dataValue){
        var data = {
            data : dataValue
        };

        var request = {
            url : "${base}/v1/utils/order/encode.json",
            data : data,
            handlerFunc : handlerOrderEncode,
            isASync : true,
            failTitle :"解密结果:"
        };

        commonAjaxRequest(request);
    }

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
</script>