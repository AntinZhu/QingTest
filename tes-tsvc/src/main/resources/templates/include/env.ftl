<div class="form-group">
    <label class="control-label col-xs-12 col-sm-3 no-padding-right" style="text-align: right;" for="env">环境选择:</label>

    <div class="col-xs-12 col-sm-9">
        <div class="clearfix">
            <input type="hidden" name="env" id="env" value="dev" class="col-xs-12 col-sm-3" />
            <button type="button" value="dev" style="border-radius: 8px" class="btn env btn-primary">开发环境</button>
            <button type="button" value="fws" style="border-radius: 8px" class="btn env">接口测试环境</button>
            <button type="button" value="tst" style="border-radius: 8px" class="btn env">测试环境</button>
            <button type="button" value="pfm" style="border-radius: 8px;display: none" class="btn env qing_catelog_hide">性能测试环境</button>
            <button type="button" value="on_line" style="border-radius: 8px;" class="btn env qing_catelog_hide">线上环境</button>
        </div>
    </div>
</div>

<div class="hide" id="qing_local_switch_div">
    <div class="hr hr-dotted"></div>
    <div class="hr hr-dotted"></div>
<#--<#if cross?? && cross == 1>-->
    <div class="form-group">
        <label class="control-label col-xs-12 col-sm-3 no-padding-right" style="text-align: right;" for="env">本地调试开关:</label>


        <div class="col-xs-12 col-sm-9">
            <label class="pull-left inline"  title="开启会调本地接口" data-rel="tooltip" >
                <input id="isLocalDebug" type="checkbox" class="ace ace-switch ace-switch-5" value="0" />
                <span class="lbl"></span>
            </label>
            <div class="isLocalDebug hide">
                <input class="col-xs-3" type="number" id="localDebugPort" placeholder="本地服务端口" value = "8080" />
            </div>
        </div>
    </div>
</div>
<#--</#if>-->