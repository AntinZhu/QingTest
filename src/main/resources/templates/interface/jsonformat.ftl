<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Hello World!</title>
    <#include "/include/resource.ftl" />

    <style>
        .spinner-preview {
            width:100px;
            height:100px;
            text-align:center;
            margin-top:60px;
        }

        .dropdown-preview {
            margin:0 5px;
            display:inline-block;
        }
        .dropdown-preview  > .dropdown-menu {
            display: block;
            position: static;
            margin-bottom: 5px;
        }

        .json_key{ color: #92278f;font-weight:bold;}
        .json_null{color: #f1592a;font-weight:bold;}
        .json_string{ color: #3ab54a;font-weight:bold;}
        .json_number{ color: #25aae2;font-weight:bold;}
        .json_boolean{ color: #f98280;font-weight:bold;}
        .json_link{ color: #61D2D6;font-weight:bold;}
        .json_array_brackets{}
    </style>

    <link href="${base}/static/css/json/base.css" rel="stylesheet">
    <link href="${base}/static/css/json/jquery.numberedtextarea.css" rel="stylesheet">
    <link href="${base}/static/css/json/font-awesome.min.css" rel="stylesheet">

    <script src="${base}/static/js/json/hm.js"></script>
    <script src="${base}/static/js/json/jquery.message.js"></script>
    <script src="${base}/static/js/json/jquery.json.js"></script>
    <script src="${base}/static/js/json/json2.js"></script>
    <script src="${base}/static/js/json/jsonlint.js"></script>
    <script src="${base}/static/js/json/jquery.numberedtextarea.js"></script>

</head>
<body>
    <#include "/include/topbar.ftl" />
    <div class="main-container" id="main-container">
        <script type="text/javascript">
            try{ace.settings.check('main-container' , 'fixed')}catch(e){}
        </script>

        <div class="main-container-inner">
            <a class="menu-toggler" id="menu-toggler" href="#">
                <span class="menu-text"></span>
            </a>
            <main class="row-fluid" style="height:100%;min-height:550px;">
                <div class="col-md-6" style="padding:0px;height:100%;">
                    <div class="page-content">
                        <div class="page-header">
                            <h1>
                                接口测试
                                <small>
                                    <i class="icon-double-angle-right"></i>
                                    <label id = "interfaceNameDiv">Common form elements and layouts</label>
                                </small>
                            </h1>
                        </div><!-- /.page-header -->

                        <div class="row">
                            <div class="col-xs-12">
                                <!-- PAGE CONTENT BEGINS -->
                                <form class="form-horizontal" role="form">
                                    <input type="hidden" id="interfaceId" name="interfaceId" />
                                    <div class="form-group hide" id="requestUserIdDev">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="teacherIdIpt">请求人ID:</label>

                                        <div class="col-xs-12 col-sm-9">
                                            <div class="clearfix">
                                                <input type="hidden" name="requestUserId" id="requestUserId" value="22367" class="col-xs-12 col-sm-3" />
                                                <span class="editable editable-click editable-unsaved" id="requestUserIdDiv" style="display: inline-block; background-color: rgba(0, 0, 0, 0);">22367</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="hr hr-dotted"></div>

                                    <div class="form-group">
                                        <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="param3">请求参数:</label>

                                        <div class="col-xs-12 col-sm-9" id = "paramListDiv">
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
                                    </div>

                                    <div class="hr hr-dotted"></div>
                                    <div class="clearfix form-actions">
                                        <div class="col-md-offset-3 col-md-9">
                                            <button class="btn btn-info" type="button" id = "teacherIdBtn">
                                                <i class="icon-ok bigger-110"></i>
                                                Submit
                                            </button>

                                            &nbsp; &nbsp; &nbsp;
                                            <button class="btn" type="reset">
                                                <i class="icon-undo bigger-110"></i>
                                                Reset
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6" style="padding:0;position:relative;height:100%;">
                    <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                        <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                        <div class="ro" id="json-target" style="padding:0px 25px;white-space: pre-line;">
                            <span data-type="object"><i style="cursor:pointer;" class="fa fa-minus-square-o" onclick="hide(this)"></i>{<br><br>}</span></div>
                    </div>
                    <form id="form-save" method="POST"><input type="hidden" value="" id="txt-content" name="content"></form>
                </div>
                <br style="clear:both;">

                <div class="hide">
                    <div id="inputDiv">
                        <div class="form-group">
                            <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="{paramName}">{remark}:</label>

                            <div class="col-xs-12 col-sm-9">
                                <div class="clearfix">
                                    <input type="number" name="{paramName}" id="{paramName}" value="{defaultValue}" class="col-xs-12 col-sm-3" />
                                </div>
                            </div>
                        </div>

                        <div class="space-2"></div>
                    </div>

                    <div id="table_editable">
                        <div class="profile-user-info profile-user-info-striped" id = "{id}">
                            {paramList}
                        </div>
                    </div>

                    <div id = "sub_editable">
                        <div class="profile-info-row">
                            <div class="profile-info-name"> {name} </div>

                            <div class="profile-info-value">
                                {paramList}
                            </div>
                        </div>
                    </div>

                    <div id = "input_editable">
                        <div class="profile-info-row">
                            <div class="profile-info-name"> {name} </div>

                            <div class="profile-info-value">
                                <input type="hidden" id="{key}" name="{key}" value="{defaultValue}"/>
                                <span class="editable" id="{key}_label">{defaultName}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>

        <script type="text/javascript">
            var interfaceData;

            $('textarea').numberedtextarea();
            var current_json = '';
            var current_json_str = '';
            var xml_flag = false;
            var zip_flag = false;
            var shown_flag = false;
            var compress_flag = false;
            $('.tip').tooltip();
            function init(){
                xml_flag = false;
                zip_flag = false;
                shown_flag = false;
                compress_flag = false;
                renderLine();
            }
            $('#json-src').keyup(function(){
                init();
                var content = $.trim($(this).val());
                jsonShow(content);
            });

            function jsonShow(content){
                var result = '';
                content = JSON.stringify(content);
                if (content!='') {
                    try{
                        current_json = jsonlint.parse(content);
                        current_json_str = JSON.stringify(current_json);
                        result = new JSONFormat(content,4).toString();
                    }catch(e){
                        result = '<span style="color: #f1592a;font-weight:bold;">' + e + '</span>';
                        current_json_str = result;
                    }

                    $('#json-target').html(result);
                }else{
                    $('#json-target').html('');
                }
            }

            function renderLine(){
                var line_num = $('#json-target').height()/20;
                $('#line-num').html("");
                var line_num_html = "";
                for (var i = 1; i < line_num+1; i++) {
                    line_num_html += "<div>"+i+"<div>";
                }
                $('#line-num').html(line_num_html);
            }
            $('.clear').click(function(){
                $('#json-src').val('');
                $('#json-target').html('');
            });
            (function($){
                $.fn.innerText = function(msg) {
                    if (msg) {
                        if (document.body.innerText) {
                            for (var i in this) {
                                this[i].innerText = msg;
                            }
                        } else {
                            for (var i in this) {
                                this[i].innerHTML.replace(/&amp;lt;br&amp;gt;/gi,"n").replace(/(&amp;lt;([^&amp;gt;]+)&amp;gt;)/gi, "");
                            }
                        }
                        return this;
                    } else {
                        if (document.body.innerText) {
                            return this[0].innerText;
                        } else {
                            return this[0].innerHTML.replace(/&amp;lt;br&amp;gt;/gi,"n").replace(/(&amp;lt;([^&amp;gt;]+)&amp;gt;)/gi, "");
                        }
                    }
                };
            })(jQuery);
            $('#json-src').keyup();

            $(document).ready(function(){
                var data = {
                    data : 9
                };
                commonAjaxRequest("${base}/v1/test/interface.json", data, handlerInterface, true, "获取接口信息失败:");
            });

            function handlerInterface(resu){
                jsonShow(resu);
                $("#interfaceId").val(resu.interfaceInfo.inter.id);
                $("#interfaceNameDiv").text(resu.interfaceInfo.inter.interfaceName);
                if(resu.interfaceInfo.inter.interfaceType == "PT"){
                    $("#requestUserIdDev").removeClass("hide");
                    var requestLabel = $("#requestUserIdDev").find("label");
                    switch (resu.interfaceInfo.inter.requestUserType){
                        case "teacher":
                            requestLabel.text(requestLabel.text() + "(老师)");
                            break;
                        case "student":
                            requestLabel.text(requestLabel.text() + "(学生)");
                            break;
                    }
                }

                $("#param").text(resu.interfaceInfo.inter.paramDetail);
                var params = JSON.parse(resu.interfaceInfo.inter.paramDetail);

                var paramHtmls = genHtml("", params);
                $("#paramListDiv").html(paramHtmls);

                initHtml("", params);
            }

            function initHtml(parentKey, params){
                if(parentKey != ''){
                    parentKey += "-";
                }

                for(var paramIdx in params) {
                    var param = params[paramIdx];
                    var paramKey = parentKey + param.key;

                    if(param.detail != null){
                        initHtml(paramKey, param.detail);
                    }else{
                        if(param.selectable != null){
                            var options = [];
                            for(var secIdx in param.selectable){
                                var sec = param.selectable[secIdx];
                                options.push({id: sec.value, text: sec.name});
                            }

                            $('#' + paramKey + "_label").editable({
                                type: 'select2',
                                value : options[0].text,
                                source: options,
                                success: function(response, newValue) {
                                    $(this).prev("input").val(newValue);
                                }
                            });
                        }else{
                            $('#' + paramKey + "_label").editable({
                                type: 'text',
                                name: paramKey,
                                success: function(response, newValue) {
                                    $(this).prev("input").val(newValue);
                                }
                            });
                        }
                    }
                }
            }

            function genHtml(parentKey, params){
                if(parentKey != ''){
                    parentKey += "-";
                }

                var paramHtmls = "";
                for(var paramIdx in params){
                    var param = params[paramIdx];
                    var paramHtml = "";
                    if(param.detail != null){
                        var subTableHtml = genHtml(parentKey + param.key, param.detail);

                        var subHtml = $("#sub_editable").html();
                        paramHtml = subHtml.replace(new RegExp("{name}","gm"), param.name);
                        paramHtml = paramHtml.replace(new RegExp("{paramList}","gm"), subTableHtml);
                    }else{
                        paramHtml = initInput(parentKey, param);
                    }
                    paramHtmls += paramHtml;
                }

                var tableHtml = $("#table_editable").html();
                tableHtml = tableHtml.replace(new RegExp("{id}","gm"), parentKey);
                tableHtml = tableHtml.replace(new RegExp("{paramList}","gm"), paramHtmls);

                return tableHtml;
            }

            function initInput(paramKey, param){
                var html = $("#input_editable").html();
                var paramHtml = html.replace(new RegExp("{name}","gm"), param.name);
                paramHtml = paramHtml.replace(new RegExp("{key}","gm"), paramKey + param.key);
                var defaultName = "";
                var defaultValue = "";
                if(param.defaultValue != null){
                    if(param.defaultValue.name != null){
                        defaultName = param.defaultValue.name;
                        defaultValue =  param.defaultValue.value;
                    }else{
                        defaultName = param.defaultValue;
                        defaultValue = param.defaultValue;
                    }
                }
                paramHtml = paramHtml.replace(new RegExp("{defaultName}","gm"), defaultName);
                paramHtml = paramHtml.replace(new RegExp("{defaultValue}","gm"), defaultValue);

                return paramHtml;
            }

            jQuery(function($) {
                $('#teacherIdBtn').click(function () {
                    var param = new Object();
                    $("#paramListDiv input").each(function(key,value){
//                        alert(key + "->" + value.id + "->" + value.value);
                        var paramNameArr = value.id.split("-");
                        if(paramNameArr.length == 1){
                            param[value.id] = value.value;
                        }else{
                            var paramObj = param;
                            var paramName = paramNameArr[0];
                            for(paranNameIdx in paramNameArr){
                                paramName = paramNameArr[paranNameIdx];
                                if(paranNameIdx == paramNameArr.length - 1){
                                    break;
                                }
                                if(paramObj[paramName] == null){
                                    paramObj[paramName] = new Object();
                                }
                                paramObj = paramObj[paramName];
                            }
                            paramObj[paramName] = value.value;
                        }
                    });
//                    alert(JSON.stringify(param));
                    var data = {
                        interfaceId : $("#interfaceId").val(),
                        requestUserId : $("#requestUserId").val(),
                        param : JSON.stringify(param)
                    };
                    commonAjaxRequest("${base}/v1/test/interface/invoke.json", data, handlerTeacherInfo, true, "获取老师信息for订单异常:");
                });

                function handlerTeacherInfo(resu){
                    jsonShow(resu.data);
                };

                //editables on first profile page
                $.fn.editable.defaults.mode = 'inline';
                $.fn.editableform.loading = "<div class='editableform-loading'><i class='light-blue icon-2x icon-spinner icon-spin'></i></div>";
                $.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="icon-ok icon-white"></i></button>'+
                        '<button type="button" class="btn editable-cancel"><i class="icon-remove"></i></button>';

                $("#requestUserIdDiv").editable({
                    type: 'text',
                    name: 'username'
                });

                //editables
                $('#username').editable({
                    type: 'text',
                    name: 'username'
                });

                var countries = [];
                $.each({ "CA": "Canada", "IN": "India", "NL": "Netherlands", "TR": "Turkey", "US": "United States"}, function(k, v) {
                    countries.push({id: k, text: v});
                });

                var cities = [];
                cities["CA"] = [];
                $.each(["Toronto", "Ottawa", "Calgary", "Vancouver"] , function(k, v){
                    cities["CA"].push({id: v, text: v});
                });
                cities["IN"] = [];
                $.each(["Delhi", "Mumbai", "Bangalore"] , function(k, v){
                    cities["IN"].push({id: v, text: v});
                });
                cities["NL"] = [];
                $.each(["Amsterdam", "Rotterdam", "The Hague"] , function(k, v){
                    cities["NL"].push({id: v, text: v});
                });
                cities["TR"] = [];
                $.each(["Ankara", "Istanbul", "Izmir"] , function(k, v){
                    cities["TR"].push({id: v, text: v});
                });
                cities["US"] = [];
                $.each(["New York", "Miami", "Los Angeles", "Chicago", "Wysconsin"] , function(k, v){
                    cities["US"].push({id: v, text: v});
                });

                var currentValue = "NL";
                $('#country').editable({
                    type: 'select2',
                    value : 'NL',
                    source: countries,
                    success: function(response, newValue) {
                        if(currentValue == newValue) return;
                        currentValue = newValue;

                        var new_source = (!newValue || newValue == "") ? [] : cities[newValue];

                        //the destroy method is causing errors in x-editable v1.4.6
                        //it worked fine in v1.4.5
                        /**
                         $('#city').editable('destroy').editable({
							type: 'select2',
							source: new_source
						}).editable('setValue', null);
                         */

                                //so we remove it altogether and create a new element
                        var city = $('#city').removeAttr('id').get(0);
                        $(city).clone().attr('id', 'city').text('Select City').editable({
                            type: 'select2',
                            value : null,
                            source: new_source
                        }).insertAfter(city);//insert it after previous instance
                        $(city).remove();//remove previous instance

                    }
                });

                $('#city').editable({
                    type: 'select2',
                    value : 'Amsterdam',
                    source: cities[currentValue]
                });



                $('#signup').editable({
                    type: 'date',
                    format: 'yyyy-mm-dd',
                    viewformat: 'dd/mm/yyyy',
                    datepicker: {
                        weekStart: 1
                    }
                });

                $('#age').editable({
                    type: 'spinner',
                    name : 'age',
                    spinner : {
                        min : 16, max:99, step:1
                    }
                });

                //var $range = document.createElement("INPUT");
                //$range.type = 'range';
                $('#login').editable({
                    type: 'slider',//$range.type == 'range' ? 'range' : 'slider',
                    name : 'login',
                    slider : {
                        min : 1, max:50, width:100
                    },
                    success: function(response, newValue) {
                        if(parseInt(newValue) == 1)
                            $(this).html(newValue + " hour ago");
                        else $(this).html(newValue + " hours ago");
                    }
                });

                $('#about').editable({
                    mode: 'inline',
                    type: 'wysiwyg',
                    name : 'about',
                    wysiwyg : {
                        //css : {'max-width':'300px'}
                    },
                    success: function(response, newValue) {
                    }
                });



                // *** editable avatar *** //
                try {//ie8 throws some harmless exception, so let's catch it

                    //it seems that editable plugin calls appendChild, and as Image doesn't have it, it causes errors on IE at unpredicted points
                    //so let's have a fake appendChild for it!
                    if( /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ) Image.prototype.appendChild = function(el){}

                    var last_gritter
                    $('#avatar').editable({
                        type: 'image',
                        name: 'avatar',
                        value: null,
                        image: {
                            //specify ace file input plugin's options here
                            btn_choose: 'Change Avatar',
                            droppable: true,
                            /**
                             //this will override the default before_change that only accepts image files
                             before_change: function(files, dropped) {
								return true;
							},
                             */

                            //and a few extra ones here
                            name: 'avatar',//put the field name here as well, will be used inside the custom plugin
                            max_size: 110000,//~100Kb
                            on_error : function(code) {//on_error function will be called when the selected file has a problem
                                if(last_gritter) $.gritter.remove(last_gritter);
                                if(code == 1) {//file format error
                                    last_gritter = $.gritter.add({
                                        title: 'File is not an image!',
                                        text: 'Please choose a jpg|gif|png image!',
                                        class_name: 'gritter-error gritter-center'
                                    });
                                } else if(code == 2) {//file size rror
                                    last_gritter = $.gritter.add({
                                        title: 'File too big!',
                                        text: 'Image size should not exceed 100Kb!',
                                        class_name: 'gritter-error gritter-center'
                                    });
                                }
                                else {//other error
                                }
                            },
                            on_success : function() {
                                $.gritter.removeAll();
                            }
                        },
                        url: function(params) {
                            // ***UPDATE AVATAR HERE*** //
                            //You can replace the contents of this function with examples/profile-avatar-update.js for actual upload


                            var deferred = new $.Deferred

                            //if value is empty, means no valid files were selected
                            //but it may still be submitted by the plugin, because "" (empty string) is different from previous non-empty value whatever it was
                            //so we return just here to prevent problems
                            var value = $('#avatar').next().find('input[type=hidden]:eq(0)').val();
                            if(!value || value.length == 0) {
                                deferred.resolve();
                                return deferred.promise();
                            }


                            //dummy upload
                            setTimeout(function(){
                                if("FileReader" in window) {
                                    //for browsers that have a thumbnail of selected image
                                    var thumb = $('#avatar').next().find('img').data('thumb');
                                    if(thumb) $('#avatar').get(0).src = thumb;
                                }

                                deferred.resolve({'status':'OK'});

                                if(last_gritter) $.gritter.remove(last_gritter);
                                last_gritter = $.gritter.add({
                                    title: 'Avatar Updated!',
                                    text: 'Uploading to server can be easily implemented. A working example is included with the template.',
                                    class_name: 'gritter-info gritter-center'
                                });

                            } , parseInt(Math.random() * 800 + 800))

                            return deferred.promise();
                        },

                        success: function(response, newValue) {
                        }
                    })
                }catch(e) {}



                //another option is using modals
                $('#avatar2').on('click', function(){
                    var modal =
                            '<div class="modal hide fade">\
                                <div class="modal-header">\
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>\
                                    <h4 class="blue">Change Avatar</h4>\
                                </div>\
                                \
                                <form class="no-margin">\
                                <div class="modal-body">\
                                    <div class="space-4"></div>\
                                    <div style="width:75%;margin-left:12%;"><input type="file" name="file-input" /></div>\
                                </div>\
                                \
                                <div class="modal-footer center">\
                                    <button type="submit" class="btn btn-small btn-success"><i class="icon-ok"></i> Submit</button>\
                                    <button type="button" class="btn btn-small" data-dismiss="modal"><i class="icon-remove"></i> Cancel</button>\
                                </div>\
                                </form>\
                            </div>';


                    var modal = $(modal);
                    modal.modal("show").on("hidden", function(){
                        modal.remove();
                    });

                    var working = false;

                    var form = modal.find('form:eq(0)');
                    var file = form.find('input[type=file]').eq(0);
                    file.ace_file_input({
                        style:'well',
                        btn_choose:'Click to choose new avatar',
                        btn_change:null,
                        no_icon:'icon-picture',
                        thumbnail:'small',
                        before_remove: function() {
                            //don't remove/reset files while being uploaded
                            return !working;
                        },
                        before_change: function(files, dropped) {
                            var file = files[0];
                            if(typeof file === "string") {
                                //file is just a file name here (in browsers that don't support FileReader API)
                                if(! (/\.(jpe?g|png|gif)$/i).test(file) ) return false;
                            }
                            else {//file is a File object
                                var type = $.trim(file.type);
                                if( ( type.length > 0 && ! (/^image\/(jpe?g|png|gif)$/i).test(type) )
                                        || ( type.length == 0 && ! (/\.(jpe?g|png|gif)$/i).test(file.name) )//for android default browser!
                                ) return false;

                                if( file.size > 110000 ) {//~100Kb
                                    return false;
                                }
                            }

                            return true;
                        }
                    });

                    form.on('submit', function(){
                        if(!file.data('ace_input_files')) return false;

                        file.ace_file_input('disable');
                        form.find('button').attr('disabled', 'disabled');
                        form.find('.modal-body').append("<div class='center'><i class='icon-spinner icon-spin bigger-150 orange'></i></div>");

                        var deferred = new $.Deferred;
                        working = true;
                        deferred.done(function() {
                            form.find('button').removeAttr('disabled');
                            form.find('input[type=file]').ace_file_input('enable');
                            form.find('.modal-body > :last-child').remove();

                            modal.modal("hide");

                            var thumb = file.next().find('img').data('thumb');
                            if(thumb) $('#avatar2').get(0).src = thumb;

                            working = false;
                        });


                        setTimeout(function(){
                            deferred.resolve();
                        } , parseInt(Math.random() * 800 + 800));

                        return false;
                    });

                });



                //////////////////////////////
                $('.profile-social-links > a').tooltip();

                $('.easy-pie-chart.percentage').each(function(){
                    var barColor = $(this).data('color') || '#555';
                    var trackColor = '#E2E2E2';
                    var size = parseInt($(this).data('size')) || 72;
                    $(this).easyPieChart({
                        barColor: barColor,
                        trackColor: trackColor,
                        scaleColor: false,
                        lineCap: 'butt',
                        lineWidth: parseInt(size/10),
                        animate:false,
                        size: size
                    }).css('color', barColor);
                });

                ///////////////////////////////////////////
                $('.input-mask-phone').mask('(999) 999-9999');



                ////////////////////
                //change profile
                $('[data-toggle="buttons"] .btn').on('click', function(e){
                    var target = $(this).find('input[type=radio]');
                    var which = parseInt(target.val());
                    $('.user-profile').parent().addClass('hide');
                    $('#user-profile-'+which).parent().removeClass('hide');
                });
            });
        </script>
    </div>
</body>
</html>