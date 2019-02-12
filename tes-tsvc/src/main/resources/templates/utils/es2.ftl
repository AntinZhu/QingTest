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
                            ES bi_teacher_index
                            <small>
                                <i class="icon-double-angle-right"></i>
                                <label id = "interfaceNameDiv">覆盖bi_teacher_index中的数据</label>
                            </small>
                        </h1>
                    </div><!-- /.page-header -->

                    <div class="col-xs-12 col-sm-12">
                        <div class="form-actions">
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 实际索引名称</label>

                                    <div class="col-sm-9">
                                        <input type="text" id="indexName" placeholder="可不传 ..." class="col-xs-10 col-sm-5"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 老师ID</label>

                                    <div class="col-sm-3">
                                        <input type="text" id="teacherId" placeholder="老师ID" class="col-xs-10 col-sm-5" />
                                    </div>
                                </div>

                                <div class="space-4"></div>

                                <div class="clearfix form-actions">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button class="btn btn-info" type="button" id="searchBtn">
                                            <i class="icon-ok bigger-110"></i>
                                            Submit
                                        </button>

                                        &nbsp; &nbsp; &nbsp;
                                        <button class="btn" type="button" id ="updateBtn">
                                            <i class="icon-undo bigger-110"></i>
                                            Reset
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="row col-xs-12" id="taskList">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-tags">全量数据</label>

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
    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();

        $(".env").click(function(){
            $(".env.btn-primary").removeClass("btn-primary");
            $(this).addClass("btn-primary");
            $("#env").val($(this).val());
        });

        $("#searchBtn").click(function(){
            var search = $("#teacherId").val();

            searchTeacher(search);
        });

        function searchTeacher(teacherId){
            var data = {
                data : $("#indexName").val()
            }

            commonAjaxRequest("${base}/v1/utils/es/teacher/" + teacherId, data, handleSearch, false, "查询接口信息出错:", $("#env").val());
        }

        var indexInfo;
        function handleSearch(resu){
//            alert(resu.resultList);
            var jsonResult = JSON.parse(resu.resultList);

            var count = jsonResult.total;
            if(count > 0){
                var latest = jsonResult.hits[count -1];
                indexInfo = latest;
                var teacherData = latest._source;

                var text = JSON.stringify(teacherData);
                $("#fullData").val(text);
                var len = (text.length / 6.5);
                if(len < 10){
                    len = 10;
                }
                $('#fullData').css("height",  len + "px");
                jsonShow(teacherData, "json-interface-detail");
            }
        }

        $("#updateBtn").click(function () {
            var fullData = indexInfo._source;
            var data = {
                teacherId : indexInfo._id,
                indexName : indexInfo._index,
                data : $("#fullData").val()
            }

            commonAjaxRequest("${base}/v1/utils/es/teacher/update", data, handleUpdate, false, "更新索引数据错误:");
        });

        function handleUpdate(){
//            searchTeacher(indexInfo._id);
        }
    });
</script>
    </div>
</body>
</html>