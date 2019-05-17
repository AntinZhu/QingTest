<form class="form-horizontal">
    <div class="hide" id="paramDiv" >
        <span class="col-sm-12" style="margin-bottom: 10px;">
            <label class="pull-right inline"  title="开启时可删除和编辑参数" data-rel="tooltip" >
                <small class="muted">编辑模式:</small>

                <input id="editBtnSwitch" type="checkbox" class="ace ace-switch ace-switch-5" value="0" />
                <span class="lbl"></span>
            </label>

            <label class="pull-left inline" style="padding-left: 25%"  title="开启时可全量参数" data-rel="tooltip" >
                <input id="selfParamSwitch" type="checkbox" class="ace ace-switch ace-switch-5" value="0" />
                <span class="lbl"></span>

                <small class="muted">:全量参数</small>
            </label>
        </span><!-- /span -->

        <div class="form-group">
            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="param3">请求参数:</label>

            <div class="col-xs-12 col-sm-9">
                <div id="paramListDiv">
                    <div class="profile-user-info profile-user-info-striped" >
                        <div class="profile-info-row">
                            <div class="profile-info-name"> Username </div>

                            <div class="profile-info-value">
                                <span class="editable" id="username">alexdoe</span>
                            </div>
                        </div>
                        <div class="profile-info-row">
                            <div class="profile-info-name"> Username </div>

                            <div class="profile-info-value">
                                <div class="profile-user-info profile-user-info-striped">
                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> 名称 </div>

                                        <div class="profile-info-value">
                                            <span class="editable editable-click editable-unsaved" id="user_name" style="display: inline-block; background-color: rgba(0, 0, 0, 0);">张</span>
                                        </div>
                                    </div>

                                    <div class="profile-info-row">
                                        <div class="profile-info-name"> 用户信息 </div>

                                        <div class="profile-info-value">
                                            <span class="editable editable-click" id="user" style="display: inline-block;">undefined</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="hide" id="selfParamDiv">
                    <div class="wysiwyg-editor" id="fullParam"></div>
                </div>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript">
    jQuery(function($){
        $('#fullParam').css({'height':'165px'}).ace_wysiwyg({
            toolbar:
                    [
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        {name:'undo', className:'btn-grey'},
                        {name:'redo', className:'btn-grey'}
                    ],
            'wysiwyg': {}
            }).prev().addClass('wysiwyg-style2');
    });
</script>