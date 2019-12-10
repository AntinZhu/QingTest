<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>城市配置修改</title>
    <#include "/include/resource.ftl" />
    <link href="${base}/static/css/json/base.css" rel="stylesheet">
    <link href="${base}/static/css/json/jquery.numberedtextarea.css" rel="stylesheet">

    <script src="${base}/static/js/json/jquery.message.js"></script>
    <script src="${base}/static/js/json/jquery.json.js"></script>
    <script src="${base}/static/js/json/json2.js"></script>
    <script src="${base}/static/js/json/jsonlint.js"></script>
    <script src="${base}/static/js/json/jquery.numberedtextarea.js"></script>
    <script src="${base}/static/assets/js/jquery.maskedinput.min.js"></script>
    <script src="${base}/static/assets/js/bootstrap-tag.min.js"></script>
    <script src="${base}/static/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
    <script src="${base}/static/assets/js/jqGrid/i18n/grid.locale-en.js"></script>
    <script src="${base}/static/js/param.js"></script>
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

            <#include "/include/sidebar.ftl" />

            <div class="main-content">
                <div class="breadcrumbs" id="breadcrumbs">
                    <script type="text/javascript">
                        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                    </script>

                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home home-icon"></i>
                            <a href="#">Home</a>
                        </li>
                        <li class="active">Dashboard</li>
                    </ul><!-- .breadcrumb -->

                    <div class="nav-search" id="nav-search">
                        <span class="input-icon">
                            <input type="text" placeholder="Search ..." class="nav-search-input" id="qing_config_search" autocomplete="off" value="${configKey!''}" />
                            <i class="icon-search nav-search-icon"></i>
                        </span>
                    </div><!-- #nav-search -->
                </div>

                <div class="page-content">
                    <div class="page-header">
                        <div class="row">
                            <div class="col-xs-12 label label-lg label-light arrowed-in arrowed-right qing_input_tip center" style="color: #333;text-align: left;">
                                如果有经常需要使用的开关配置，可联系<b class="red"><a  href="#" onclick="feedback()">管理员</a></b>加入该页面
                            </div>
                            <div class="hr hr-dotted"></div>
                            <div class="hr hr-dotted"></div>

                            <div class="col-xs-12">
                                <#include "/include/env.ftl" />
                            </div>
                            <div class="col-xs-12" id="qingDataContent">
                                <!-- PAGE CONTENT BEGINS -->

                                <table id="grid-table"></table>

                                <div id="grid-pager"></div>
                                <!-- PAGE CONTENT ENDS -->
                            </div><!-- /.col -->
                        </div>
                    </div><!-- /.page-header -->
                </div>
            </div>

            <#include "/include/righttool-sidebar.ftl" />

<script type="text/javascript">
    var resizeChanged;
    window.onresize = function(){
        resizeChanged = qingResize(resizeChanged);
    }

    $(document).ready(function(){
        initAll();

        resizeChanged = qingResize(resizeChanged);
    });

    function initAll(){
        initConfig();
    }

    function initConfig(){
        var data = {
            url : "/svc/api/pi/v1/test/city_config/list.json",
            param:"",
            userId:22367,
            userType : 'student'
        }

        var request = {
            url : "${base}/v1/common/pi.json",
            data : data,
            handlerFunc : initResult,
            isASync : true,
            failTitle :"获取通用配置信息:",
            guid : "test-api-config",
            env : $("#env").val()
        };

        commonAjaxRequest(request);
    }

    var allConfig;
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    function initResult(resu){
        allConfig = resu.resultList;
        var env = $("#env").val();

        var items = [];


        var types = null;
        var typeObj = {};
        try{
            var resultList = getCityRuleTypes().resultList;
            for(var idx in resultList){
                var type = resultList[idx];
                types = types + type.key + ":" + type.value;
                if(idx != resultList.length - 1){
                    types = types + ";"
                }
                typeObj[type.key] = type.value;
            }
        }catch(e){
            types = null;
        }


        var assignConfigKey = $("#qing_config_search").val();
        for(var idx in resu.resultList){
            var result = resu.resultList[idx];
            var item;
            var ruleType;
            if(types){
                ruleType = typeObj[result.ruleType];
            }else{
                ruleType = result.ruleType;
            }

            if(assignConfigKey != "" && ruleType.indexOf(assignConfigKey) == -1){
                continue;
            }

            item = {id:idx, cityId:result.cityId, "ruleType": ruleType,configValue:result.configValue};
            items.push(item);
        }

        items.reverse();

        $("#qingDataContent").html('<table id="grid-table"></table><div id="grid-pager"></div>');
        var jqParam;
        if(types){
            jqParam = {
                data: items,
                datatype: "local",
                height: 600,
                colNames:[' ', '城市ID','配置名称','配置值'],
                colModel:[
                    {name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
                        formatter:'actions',
                        formatoptions:{
                            keys:true,

                            delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
                            //editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
                        }
                    },
                    {name:'cityId',index:'cityId', width:20,editable: true,editoptions:{size:"60",maxlength:"100"},search:true},
                    {name:'ruleType',index:'ruleType', width:50, editable: true,edittype:"select",editoptions:{value:types}},
                    {name:'configValue',index:'configValue', sortable:false,editable: true,edittype:"textarea", editoptions:{rows:"2",cols:"60"}}
                ],

                viewrecords : true,
                rowNum:50,
                rowList:[50,100,200],
                pager : pager_selector,
                altRows: true,
                //toppager: true,

                multiselect: true,
                //multikey: "ctrlKey",
                multiboxonly: true,

                loadComplete : function() {
                    var table = this;
                    setTimeout(function(){
                        styleCheckbox(table);

                        updateActionIcons(table);
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                },

                editurl: "${base}/v1/utils/city_config/set.json?env=" + env,//nothing is saved
                caption: "城市配置查询和设置",


                autowidth: true
            };
        }else{
            jqParam = {
                data: items,
                datatype: "local",
                height: 600,
                colNames:[' ', '城市ID','配置类型','配置值'],
                colModel:[
                    {name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
                        formatter:'actions',
                        formatoptions:{
                            keys:true,

                            delOptions:{recreateForm: true, beforeShowForm:beforeDeleteCallback},
                            //editformbutton:true, editOptions:{recreateForm: true, beforeShowForm:beforeEditCallback}
                        }
                    },
                    {name:'cityId',index:'cityId', width:20,editable: true,editoptions:{size:"60",maxlength:"100"},search:true},
                    {name:'ruleType',index:'ruleType', width:50, sortable:false,editable: true,edittype:"textarea", editoptions:{rows:"2",cols:"60"}},
                    {name:'configValue',index:'configValue', sortable:false,editable: true,edittype:"textarea", editoptions:{rows:"2",cols:"60"}}
                ],

                viewrecords : true,
                rowNum:50,
                rowList:[50,100,200],
                pager : pager_selector,
                altRows: true,
                //toppager: true,

                multiselect: true,
                //multikey: "ctrlKey",
                multiboxonly: true,

                loadComplete : function() {
                    var table = this;
                    setTimeout(function(){
                        styleCheckbox(table);

                        updateActionIcons(table);
                        updatePagerIcons(table);
                        enableTooltips(table);
                    }, 0);
                },

                editurl: "${base}/v1/utils/city_config/set.json?env=" + env,//nothing is saved
                caption: "城市配置查询和设置",


                autowidth: true
            };
        }


        jQuery(grid_selector).jqGrid(jqParam);
        //navButtons
        jQuery(grid_selector).jqGrid('navGrid',pager_selector,
                { 	//navbar options
                    edit: false,
                    editicon : 'icon-pencil blue',
                    add: true,
                    addicon : 'icon-plus-sign purple',
                    del: false,
                    delicon : 'icon-trash red',
                    search: false,
                    searchicon : 'icon-search orange',
                    refresh: true,
                    refreshicon : 'icon-refresh green',
                    view: false,
                    viewicon : 'icon-zoom-in grey',
                },
                null,
                {
                    //new record form
                    closeAfterAdd: true,
                    width: 600,
                    recreateForm: true,
                    viewPagerButtons: false,
                    beforeShowForm : function(e) {
                        var form = $(e[0]);
                        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                        style_edit_form(form);
                    },
                    afterSubmit: function(){
                        initConfig();
                        return true;
                    }
                },
                null,
                null,
                null
        );

        $(document).off("click", '#refresh_grid-table').on('click', '#refresh_grid-table',initConfig);
    }

    function getCityRuleTypes(){
        var data = {
            url : "/svc/api/pi/v1/test/city_config/type.json",
            param:"",
            userId:22367,
            userType : 'student'
        }

        var request = {
            url : "${base}/v1/common/pi.json",
            data : data,
            handlerFunc : returnResult,
            isASync : false,
            failTitle :"获取城市配置信息:",
            guid : "test-api-city_type_config",
            env : $("#env").val(),
            ignoreFail :true
        };

        return commonAjaxRequest(request);
    }

    $(".env").click(function(){
        $(".env.btn-primary").removeClass("btn-primary");
        $(this).addClass("btn-primary");
        $("#env").val($(this).val());

        initAll();
    });

    function feedback(){
        bootbox.prompt("输入你想加入的通用配置的ConfigKey", function(result) {
            if (result === null) {
//                $.gritter.add({
//                    title : "参数示例",
//                    text : "不反馈了？",
//                    class_name : 'gritter-error gritter-center'
//                });
                return;
            } else {
                var userIp = $("#qing_ip").text();
                var content = {
                    msgtype : "markdown",
                    markdown :{
                        content : "有用户请求在通用配置页面中加入新配置\n                >用户: <font color=\"comment\">" + userIp + "</font> \n                >config_key: <font color=\"comment\">" + result + "</font>"
                    }
                };

                var request = {
                    url : "${base}/v1/common/wx_notify.json?content=" + encodeURI(JSON.stringify(content)),
                    data : null,
                    handlerFunc : handlerParamSave,
                    isASync : true,
                    failTitle :"反馈出错："
                };

                commonAjaxRequest(request);
            }
        });
    }

    function handlerParamSave(){
        $.gritter.add({
            title : '提示:',
            text : "申请成功",
            class_name : 'gritter-info gritter-center'
        });
    }

    $(document).off("click", '.addInputBtn').on('click', '.addInputBtn',cloneInput);
    $(document).off("click", '.delInputBtn').on('click', '.delInputBtn',removeInput);

    function style_edit_form(form) {
        //enable datepicker on "sdate" field and switches for "stock" field
        form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
                .end().find('input[name=stock]')
                .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');

        //update buttons classes
        var buttons = form.next().find('.EditButton .fm-button');
        buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
        buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
        buttons.eq(1).prepend('<i class="icon-remove"></i>')

        buttons = form.next().find('.navButton a');
        buttons.find('.ui-icon').remove();
        buttons.eq(0).append('<i class="icon-chevron-left"></i>');
        buttons.eq(1).append('<i class="icon-chevron-right"></i>');
    }

    function style_delete_form(form) {
        var buttons = form.next().find('.EditButton .fm-button');
        buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
        buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
        buttons.eq(1).prepend('<i class="icon-remove"></i>')
    }

    function style_search_filters(form) {
        form.find('.delete-rule').val('X');
        form.find('.add-rule').addClass('btn btn-xs btn-primary');
        form.find('.add-group').addClass('btn btn-xs btn-success');
        form.find('.delete-group').addClass('btn btn-xs btn-danger');
    }
    function style_search_form(form) {
        var dialog = form.closest('.ui-jqdialog');
        var buttons = dialog.find('.EditTable')
        buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
        buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
        buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
    }

    function beforeDeleteCallback(e) {
        var form = $(e[0]);
        if(form.data('styled')) return false;

        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
        style_delete_form(form);

        form.data('styled', true);
    }

    function beforeEditCallback(e) {
        var form = $(e[0]);
        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
        style_edit_form(form);
    }



    //it causes some flicker when reloading or navigating grid
    //it may be possible to have some custom formatter to do this as the grid is being created to prevent this
    //or go back to default browser checkbox styles for the grid
    function styleCheckbox(table) {
        /**
         $(table).find('input:checkbox').addClass('ace')
         .wrap('<label />')
         .after('<span class="lbl align-top" />')


         $('.ui-jqgrid-labels th[id*="_cb"]:first-child')
         .find('input.cbox[type=checkbox]').addClass('ace')
         .wrap('<label />').after('<span class="lbl align-top" />');
         */
    }


    //unlike navButtons icons, action icons in rows seem to be hard-coded
    //you can change them like this in here if you want
    function updateActionIcons(table) {
        /**
         var replacement =
         {
             'ui-icon-pencil' : 'icon-pencil blue',
             'ui-icon-trash' : 'icon-trash red',
             'ui-icon-disk' : 'icon-ok green',
             'ui-icon-cancel' : 'icon-remove red'
         };
         $(table).find('.ui-pg-div span.ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
         */
    }

    //replace icons with FontAwesome icons like above
    function updatePagerIcons(table) {
        var replacement =
                {
                    'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
                    'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
                    'ui-icon-seek-next' : 'icon-angle-right bigger-140',
                    'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
                };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

            if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
        })
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container:'body'});
        $(table).find('.ui-pg-div').tooltip({container:'body'});
    }

    //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

    //enable search/filter toolbar
    //jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})

    //switch element when editing inline
    function aceSwitch( cellvalue, options, cell ) {
        setTimeout(function(){
            $(cell) .find('input[type=checkbox]')
                    .wrap('<label class="inline" />')
                    .addClass('ace ace-switch ace-switch-5')
                    .after('<span class="lbl"></span>');
        }, 0);
    }
    //enable datepicker
    function pickDate( cellvalue, options, cell ) {
        setTimeout(function(){
            $(cell) .find('input[type=text]')
                    .datepicker({format:'yyyy-mm-dd' , autoclose:true});
        }, 0);
    }

    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();

        $("#qing_config_search").change(function(){
            initConfig();
        });
    });
</script>
    </div>
</body>
</html>