<form class="form-horizontal" role="form">
    <input type="hidden" id="interfaceId" name="interfaceId" />
    <#include "/include/env.ftl" />

    <div class="hr hr-dotted"></div>
    <div class="hr hr-dotted"></div>

    <div class="hide" id = "paramChooseDiv">
        <div class="form-group">
            <label style="text-align: right;" class="control-label col-xs-12 col-sm-3 no-padding-right" for="teacherIdIpt">参数示例选择:</label>

            <div class="col-xs-12 col-sm-9">
                <div class="clearfix">
                    <select class="width-100 chosen-select form-control" id="paramChoose">
                        <option value="0">没得选</option>
                    </select>
                </div>
                <span class="input-group-btn param-ops hide">
                    <button type="button" class="btn btn-purple btn-xs qing_catelog_hide" id="param_default">
                        default
                    </button>

                     <button type="button" class="btn btn-purple btn-xs qing_catelog_hide" id="param_del">
                        delete
                    </button>
                </span>
            </div>
        </div>

        <div class="hr hr-dotted"></div>
        <div class="hr hr-dotted"></div>
    </div>

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
        <div class="form-group">
            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="requestUserType">请求人类型:</label>

            <div class="col-xs-12 col-sm-9">
                <div class="clearfix">
                    <input type="hidden" name="requestUserType" id="requestUserType" value="student" class="col-xs-12 col-sm-3" />
                    <span class="editable editable-click editable-unsaved" id="requestUserTypeDiv" style="display: inline-block; background-color: rgba(0, 0, 0, 0);">student</span>
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