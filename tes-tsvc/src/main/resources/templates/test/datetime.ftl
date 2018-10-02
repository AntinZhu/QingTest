<div class="form-group">
    <label class="col-sm-3 control-label no-padding-right" for="interfaceUrl">DateTime Picking</label>

    <div class="col-sm-9">
        <div class="clearfix">
            <div class="controls input-append date form_datetime" data-date="1979-09-16T05:25:07Z" data-date-format="yyyy-MM-dd HH:ii" data-link-field="dtp_input1">
                <input size="16" type="text" value="" readonly>
                <span class="add-on"><i class="icon-remove"></i></span>
                <span class="add-on"><i class="icon-th"></i></span>
            </div>
            <input type="hidden" id="dtp_input1" value="" /><br/>
        </div>

        <div class="space-2"></div>
    </div>
</div>

<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
</script>
