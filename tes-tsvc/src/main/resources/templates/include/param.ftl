<form class="form-horizontal" role="form">
    <input type="hidden" id="interfaceId" name="interfaceId" />
    <div class="form-group">
        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="env">环境选择:</label>

        <div class="col-xs-12 col-sm-9">
            <div class="clearfix">
                <input type="hidden" name="env" id="env" value="dev" class="col-xs-12 col-sm-3" />
                <button type="button" value="dev" class="btn env btn-primary">开发环境</button>
                <button type="button" value="hjl" class="btn env">接口测试环境</button>
                <button type="button" value="tst" class="btn env">测试环境</button>
            </div>
        </div>
    </div>

    <div class="hr hr-dotted"></div>
    <div class="hr hr-dotted"></div>

    <div class="hide" id="requestUserIdDev">
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
    </div>

    <#include "/include/paramDetail.ftl" />

    <div class="hr hr-dotted"></div>
    <div class="hr hr-dotted"></div>
</form>