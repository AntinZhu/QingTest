<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Hello World!</title>
    <#include "/include/resource.ftl" />
    <link href="${base}/static/css/json/base.css" rel="stylesheet">
    <link href="${base}/static/css/json/jquery.numberedtextarea.css" rel="stylesheet">

    <script src="${base}/static/js/json/hm.js"></script>
    <script src="${base}/static/js/json/jquery.message.js"></script>
    <script src="${base}/static/js/json/jquery.json.js"></script>
    <script src="${base}/static/js/json/json2.js"></script>
    <script src="${base}/static/js/json/jsonlint.js"></script>
    <script src="${base}/static/js/json/jquery.numberedtextarea.js"></script>
    <script src="${base}/static/assets/js/jquery.maskedinput.min.js"></script>
    <script src="${base}/static/assets/js/bootstrap-tag.min.js"></script>
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
                <div class="page-content">
                    <div class="page-header">
                        <h1>
                            ES 老师&学生 数据修改
                            <small>
                                <i class="icon-double-angle-right"></i>
                                <label id = "interfaceNameDiv">修改Es-老师&学生 中的数据</label>
                            </small>
                        </h1>
                    </div><!-- /.page-header -->

                    <div class="col-xs-12 col-sm-12">
                        <div class="form-actions">
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="control-label col-xs-12 col-sm-3 no-padding-right" for="indexType">索引类型</label>
                                    <div class="col-xs-4 col-sm-4">
                                        <div>
                                            <select class="width-80 chosen-select" id="indexType" data-placeholder="选择索引类型...">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="indexName">索引名称</label>

                                    <div class="col-sm-9">
                                        <input type="text" id="indexName" placeholder="实际索引名称" class="col-xs-10 col-sm-5" value=""/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="indexNameSelect">选择索引名称</label>

                                    <div class="col-xs-4 col-sm-4">
                                        <select class="width-80 chosen-select" id="indexNameSelect" data-placeholder="选择索引名称...">
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group qing_param relation teacher">
                                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 老师ID</label>

                                    <div class="col-sm-3">
                                        <input type="text" id="teacherId" placeholder="老师ID" class="col-xs-10 col-sm-5" value="234"/>
                                    </div>
                                </div>

                                <div class="form-group qing_param relation student">
                                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 家长ID</label>

                                    <div class="col-sm-3">
                                        <input type="text" id="studentId" placeholder="家长ID" class="col-xs-10 col-sm-5" value="128985"/>
                                    </div>
                                </div>

                                <div class="form-group qing_param crm">
                                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> CrmID</label>

                                    <div class="col-sm-3">
                                        <input type="text" id="customerId" placeholder="CrmID" class="col-xs-10 col-sm-5" value="210878"/>
                                    </div>
                                </div>

                                <div class="form-group qing_param assistant">
                                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 助教ID</label>

                                    <div class="col-sm-3">
                                        <input type="text" id="assistantId" placeholder="助教ID" class="col-xs-10 col-sm-5" value="523"/>
                                    </div>
                                </div>

                                <div class="space-4"></div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="form-field-tags">修改字段</label>

                                    <div class="col-sm-9" id="form-field-tags-parent">
                                        <input type="text" name="tags" id="form-field-tags" value="" placeholder="Enter 修改字段 ..." />
                                    </div>
                                </div>

                                <div class="clearfix form-actions">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button class="btn btn-info" type="button" id="searchBtn">
                                            <i class="icon-ok bigger-110"></i>
                                            查询
                                        </button>

                                        &nbsp; &nbsp; &nbsp;
                                        <button class="btn" type="button" id ="updateBtn">
                                            <i class="icon-undo bigger-110"></i>
                                            增量更新
                                        </button>

                                        &nbsp; &nbsp; &nbsp;
                                        <button class="btn" type="button" id ="fullUpdateBtn">
                                            <i class="icon-undo bigger-110"></i>
                                            全量更新
                                        </button>

                                        &nbsp; &nbsp; &nbsp;
                                        <button class="btn btn-danger" type="button" id ="delBtn">
                                            <i class="icon-trash icon-only"></i>
                                            删除
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="row col-xs-12" id="taskList">
                        <#include "/include/paramDetail.ftl" />

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" style="text-align: right" for="fullData">全量数据</label>

                            <div class="col-sm-9">
                                <textarea id="fullData" class="autosize-transition form-control"></textarea>
                            </div>
                        </div>

                        <div id="right-box" style="width:100%;height: 87vh;min-height:520px;border:solid 1px #f6f6f6;border-radius:0;resize: none;overflow-y:scroll; outline:none;position:relative;font-size:12px;padding-top:40px;">
                            <div id="line-num" style="background-color:#fafafa;padding:0px 8px;float:left;border-right:dashed 1px #E5EBEE;display:none;z-index:-1;color:#999;position:absolute;text-align:center;over-flow:hidden;"><div>1<div></div></div></div>
                            <div class="ro" id="json-interface-detail" style="padding:0px 25px;white-space: pre-line;">
                                <span data-type="object"><i style="cursor:pointer;" class="fa icon-minus" onclick="hide(this)"></i>{<br><br>}</span></div>
                        </div>
                    </div>
                </div>
                </div>

            <#include "/include/righttool-sidebar.ftl" />

<script type="text/javascript">
    // indexPrefix: 索引前缀 isRelation:
    var supportIndexArr = [
        {indexPrefix:'bi_tr_stu_index',isRelation:true, userType:'',uniqueKey:'student-teacher', clazz:'relation', userName:'老师和学生'},
        {indexPrefix:'bi_tr_stu_all_index',isRelation:true, userType:'',uniqueKey:'student-teacher', clazz:'relation', userName:'老师和学生'},
        {indexPrefix:'bi_teacher_index',isRelation:false, userType:'teacher',uniqueKey:'teacher', clazz:'teacher', userName:'老师'},
        {indexPrefix:'bi_student_index',isRelation:false, userType:'student',uniqueKey:'student', clazz:'student', userName:'学生'},
        {indexPrefix:'bi_crm_index',isRelation:false, userType:'customer',uniqueKey:'crm', clazz:'crm', userName:'customer'},
        {indexPrefix:'bi_assistant_index',isRelation:false, userType:'assistant',uniqueKey:'assistant', clazz:'assistant', userName:'助教'},
    ];
    var selectable = [];
    for(var idx in supportIndexArr){
        var item = supportIndexArr[idx];
        var selectItem = new Object();
        selectItem.key = item.indexPrefix;
        selectItem.value = item.indexPrefix;

        selectable.push(selectItem);
    }

    function init(){
        updateOptions("indexType", selectable, selectable[0].value);
        updateOptions("indexNameSelect", getEmptyOptions());

        $(".qing_param").addClass("hide");
        $("." + getIndexInfo($("#indexType").val()).clazz).removeClass("hide");
    }

    function getIndexInfo(indexPrefix){
        for(var idx in supportIndexArr){
            var item = supportIndexArr[idx];
            if(item.indexPrefix == indexPrefix){
                return item;
            }
        }

        return null;
    }

    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();
        init();

//        $("#form-field-tags-parent").bind("DOMNodeInserted",function(e){
//            var inputText = $(e.target).html();
//            if(inputText.indexOf("\<button") > 0){
//                formatParamInput(getInputLabel(e), null);
//            }
//        });
//
//        $("#form-field-tags-parent").bind("DOMNodeRemoved",function(e){
//            var inputText = $(e.target).html();
//            if(inputText.indexOf("\<button") > 0) {
//                var tagLen = $("#form-field-tags-parent").children("div").children("span").length;
//                if (tagLen > 0) {
//                    formatParamInput(null, getInputLabel(e));
//                } else {
//                    $("#paramDiv").addClass("hide");
//                }
//            }
//        });

        function getInputLabel(e){
            var inputText = $(e.target).html();
            return inputText.substr(0, inputText.indexOf("\<button"));
        }

        $("#indexNameSelect").change(function () {
            $("#indexName").val($(this).val());
        });

        $("#indexType").change(function(){
            $(".qing_param").addClass("hide");

            var indexInfo = getIndexInfo($("#indexType").val());
            $("." + indexInfo.clazz).removeClass("hide");

            updateOptions("indexNameSelect", getEmptyOptions());
            $("#indexName").val("");

//            commonQuery(noConditionQueryString, indexInfo);
        });

        $(".env").click(function(){
            $(".env.btn-primary").removeClass("btn-primary");
            $(this).addClass("btn-primary");
            $("#env").val($(this).val());
        });

        $("#searchBtn").click(searchData);

        function searchData(){
            var indexInfo = getIndexInfo($("#indexType").val());
            if(indexInfo.isRelation){
                searchRelation(indexInfo);
            }else{
                searchSingle(indexInfo);
            }
        }

        function searchSingle(indexInfo){
            var userType = indexInfo.userType;
            var userId = $("#" + userType + "Id").val();
            if(userId == null){
                $.gritter.add({
                    title : '提示:',
                    text : "请输入" + indexInfo.userName + "ID",
                    class_name : 'gritter-error gritter-center'
                });
                return;
            }
            var indexName = $("#indexName").val();
            if(indexName == null || indexName == ''){
                indexName = indexInfo.indexPrefix + "*";
            }

            var queryString = '{"query":{"bool":{"must":[{"term":{"' + userType + '_id":"' + userId + '"}}],"must_not":[],"should":[]}},"from":0,"size":10,"sort":[{"bi_etl_data_time":{"order":"desc"}}],"aggs":{}}';
            commonQuery(queryString, indexName, handleSearch);
            commonQuery(queryString, indexInfo.indexPrefix + "*", updateIndexNameSelect);
        }

        function searchRelation(indexInfo){
            var teacherId = $("#teacherId").val();
            var studentId = $("#studentId").val();

            if(teacherId == null || studentId == null){
                // TODO 提示
                $.gritter.add({
                    title : '提示:',
                    text : "请输入" + indexInfo.userName + "ID",
                    class_name : 'gritter-error gritter-center'
                });
                return;
            }

            var queryString = '{"query":{"bool":{"must":[{"term":{"student_id":"' + studentId+ '"}},{"term":{"teacher_id":"' + teacherId + '"}}],"must_not":[],"should":[]}},"from":0,"size":10,"sort":[{"bi_etl_data_time":{"order":"desc"}}],"aggs":{}}';
            var indexName = $("#indexName").val();
            if(indexName == null || indexName == ''){
                indexName = indexInfo.indexPrefix + "*";
            }
            commonQuery(queryString, indexName, handleSearch);
            commonQuery(queryString, indexInfo.indexPrefix + "*", updateIndexNameSelect);
        }

        function commonQuery(queryString, indexName, handleFunction){
            var data = {
                queryString :queryString,
                indexName : indexName
            }
            commonAjaxRequest("${base}/v1/utils/es/query", data, handleFunction, false, "查询接口信息出错:", $("#env").val());
        }

        var indexInfo;
        function handleSearch(resu){
//            alert(resu.resultList);
            var jsonResult = JSON.parse(resu.resultList);

            var count = jsonResult.hits.length;
            if(count > 0){
                var latest = jsonResult.hits[0];
                indexInfo = latest;
                var teacherData = latest._source;

                var text = JSON.stringify(teacherData);
                $("#fullData").val(text);
                var len = (text.length / 6.5);
                if(len < 100){
                    len = 100;
                }
                $('#fullData').css("height",  len + "px");

                formatParamInput();

//                updateOptions("indexNameSelect", genOptionsWithEmpty(jsonResult.hits, "_index"), latest._index);
                $("#indexName").val(latest._index);

                jsonShow(teacherData, "json-interface-detail");
                //{"key":"end_time","name":"end_time","defaultValue":1}
            }else{
                $.gritter.add({
                    title : '提示:',
                    text : "没有查询到数据",
                    class_name : 'gritter-error gritter-center'
                });
            }
        }

        function updateIndexNameSelect(resu){
            var jsonResult = JSON.parse(resu.resultList);

            var count = jsonResult.hits.length;
            if(count > 0) {
                var latest = jsonResult.hits[0];

                updateOptions("indexNameSelect", genOptionsWithEmpty(jsonResult.hits, "_index"), $("#indexName").val());
            }
        }

        function formatParamInput(inclu, exclu){
            if(indexInfo != null){
                var teacherData = indexInfo._source;

                var modifyStr = $("#form-field-tags").val();
                var modifyArr = modifyStr.split(",");

                if(modifyArr.length == 1 && modifyArr[0] == "" && inclu != null){
                }else{
                    var items = [];
                    for(var propName in teacherData){
                        if(isMatch(modifyArr, propName, inclu, exclu)){
                            var obj = new Object();
                            obj.key = propName;
                            obj.name = propName;
                            obj.defaultValue = teacherData[propName];

                            items.push(obj);
                        }
                    }
                    showParam({paramData:JSON.stringify(items)});
                }
            }
        }

        function isMatch(itemArr, item, inclu, exclu){
            if(itemArr.length == 1 && itemArr[0] == ""){
                return true;
            }

            if(inclu != null && inclu == item){
                return true;
            }

            if(exclu != null && exclu == item){
                return false;
            }

            for(var idx in itemArr){
                var arrItem = itemArr[idx].trim();
                if(item == arrItem || arrItem == "*"){
                    return true;
                }
            }

            return false;
        }

        $("#updateBtn").click(function () {
            var modifyData = generateJsonParam("#paramListDiv input");
            var fullData = indexInfo._source;
            for(var propName in modifyData){
                fullData[propName] = modifyData[propName];
            }

            var indexInfoBean = getIndexInfo($("#indexType").val());
            updateIndex(indexInfoBean.uniqueKey, getUniqueValue(indexInfoBean), indexInfo._index, JSON.stringify(fullData));
        });

        $("#fullUpdateBtn").click(function () {
            var indexInfoBean = getIndexInfo($("#indexType").val());
            updateIndex(indexInfoBean.uniqueKey, getUniqueValue(indexInfoBean), indexInfo._index, $("#fullData").val());
        });

        $("#delBtn").click(function () {
            var indexInfoBean = getIndexInfo($("#indexType").val());
            if(indexInfo == null){
                $.gritter.add({
                    title : '提示:',
                    text : "先查询一下吧，少年",
                    class_name : 'gritter-error gritter-center'
                });
                return;
            }

            deleteIndex(indexInfoBean.uniqueKey, getUniqueValue(indexInfoBean), indexInfo._index);
        });

        function deleteIndex(uniqueKey, uniqueValue, indexName){
            var data = {
                uniqueKey : uniqueKey,
                uniqueValue : uniqueValue,
                indexName : indexName,
            }

            commonAjaxRequest("${base}/v1/utils/es/delete", data, clearData, false, "删除索引数据错误:");
        }

        function getUniqueValue(indexInfoBean){
            var uniqueValue;
            if(indexInfoBean.isRelation){
                uniqueValue = $("#studentId").val() + "-" + $("#teacherId").val();
            }else{
                uniqueValue = $("#" + indexInfoBean.userType + "Id").val();
            }

            return uniqueValue;
        }

        function updateIndex(uniqueKey, uniqueValue, indexName, data){
            var data = {
                uniqueKey : uniqueKey,
                uniqueValue : uniqueValue,
                indexName : indexName,
                data : data
            }

            commonAjaxRequest("${base}/v1/utils/es/update", data, handleUpdate, false, "更新索引数据错误:");
        }

        function clearData(){
            indexInfo = null;
            $("#fullData").val("");
            $("#paramDiv").addClass("hide");
            jsonShow(new Object(), "json-interface-detail");
            $('#fullData').css("height",  "100px");
        }

        function handleUpdate(){
            $.gritter.add({
                title : '提示:',
                text : "更新成功",
                class_name : 'gritter-info gritter-center'
            });

//            sleep(500);
//            searchData();
        }

        //we could just set the data-provide="tag" of the element inside HTML, but IE8 fails!
        var tag_input = $('#form-field-tags');
        if(! ( /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase())) )
        {
            tag_input.tag(
                    {
                        placeholder:tag_input.attr('placeholder'),
                        //enable typeahead by specifying the source array
                        source: ace.variable_US_STATES,//defined in ace.js >> ace.enable_search_ahead
                    }
            );
        }
        else {
            //display a textarea for old IE, because it doesn't support this plugin or another one I tried!
            tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
            //$('#form-field-tags').autosize({append: "\n"});
        }
    });
</script>
    </div>
</body>
</html>