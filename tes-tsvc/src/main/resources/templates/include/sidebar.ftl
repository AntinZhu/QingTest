<div class="sidebar" id="sidebar">
    <script type="text/javascript">
        try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
    </script>

    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success" id="qing_report">
                <i class="icon-signal"></i>
            </button>

            <button class="btn btn-info" id="showEdit" value="0">
                <i class="icon-pencil"></i>
            </button>

            <button class="btn btn-warning" id="upIp" >
                <i class="icon-group"></i>
            </button>

            <button class="btn btn-danger" id="qing_config_refresh">
                <i class="icon-cogs"></i>
            </button>
        </div>

        <div class="sidebar-shortcuts-mini" >
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div><!-- #sidebar-shortcuts -->

    <ul class="nav nav-list" id="catelog-ul">
        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-desktop"></i>
                <span class="menu-text"> 流程化页面 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="${base}/v1/order/student/add_order_page">
                        <i class="icon-double-angle-right"></i>
                        订单流程
                    </a>
                </li>
            </ul>
        </li>

        <li class="active">
            <a href="#" class="dropdown-toggle">
                <i class="icon-list"></i>
                <span class="menu-text"> 单接口测试 </span>

                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li  class="active">
                    <a href="tables.html">
                        <i class="icon-double-angle-right"></i>
                        Simple &amp; Dynamic
                    </a>
                </li>

                <li>
                    <a href="jqgrid.html">
                        <i class="icon-double-angle-right"></i>
                        jqGrid plugin
                    </a>
                </li>
            </ul>
        </li>
    </ul><!-- /.nav-list -->

    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>

    <script type="text/javascript">
        try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}

        $(document).ready(function(){
            refreshCatelog();
            $(".qing_catelog_hide").addClass("hide");
        });

        function refreshCatelog(){
            var request = {
                url : "${base}/v1/test/catelog.json",
                data : null,
                handlerFunc : handlerCatelog,
                isASync : false,
                failTitle :"获取分类信息失败:"
            };

            commonAjaxRequest(request);
        }

        function handlerCatelog(resu){
            $("#catelog-ul").html(catelogList(resu.resultList, "${base}"));
            activeCatelog("${Request["catelogIndex"]!"2-1"}");
        }

        var catelogHide = true;
        $("#showEdit").click(function () {
            if(catelogHide){
                $(".qing_catelog_hide").removeClass("hide");
                catelogHide = false;
            }else{
                $(".qing_catelog_hide").addClass("hide");
                catelogHide = true;
            }
        });

        $("#upIp").click(function(){
            upIp();
        });

        function upIp(){
            bootbox.prompt("IP上传:请输入你的姓名", function(result) {
                if (result === null) {
                    return;
                } else {
                    var userIp = $("#qing_user_ip").val();
                    var content = {
                        msgtype : "markdown",
                        markdown :{
                            content : "用户IP上传\n                >用户IP: <font color=\"comment\">" + userIp + "</font> \n                >用户名: <font color=\"comment\">" + result + "</font> "
                        }
                    };

                    var request = {
                        url : "${base}/v1/common/wx_notify.json?content=" + encodeURI(JSON.stringify(content)),
                        data : null,
                        handlerFunc : handlerParamSave,
                        isASync : true,
                        failTitle :"IP上传出错:",
                        otherData : {"userName":result, "userIp":userIp}
                    };

                    commonAjaxRequest(request);
                }
            });
        }

        function handlerParamSave(resu, otherData){
            var request = {
                url : "${base}/v1/user/tmp/login.json?userName=" + otherData.userName + "&userIp=" + otherData.userIp,
                data : null,
                handlerFunc : handleTmpLogin,
                isASync : true,
                failTitle :"申请临时登陆出错:"
            };

            commonAjaxRequest(request);
        }

        function handleTmpLogin(){
            $.gritter.add({
                title : '提示:',
                text : "提交成功，您将获得5分钟的临时登陆状态。待用户名审核通过后，将可一直使用。3秒后将跳转到目标页面...",
                class_name : 'gritter-info gritter-center'
            });

            setTimeout(reloadPage, 3000);
        }

        $("#qing_report").click(function(){
            var request = {
                url : "${base}/v1/report/daily.json?",
                data : null,
                handlerFunc : emp,
                isASync : true,
                failTitle :"报告出错:"
            };

            commonAjaxRequest(request);
        });

        function emp(){
        }

        $("#qing_config_refresh").click(function(){
            var request = {
                url : "${base}/cron/v1/sync?type=all",
                data : null,
                handlerFunc : emp,
                isASync : true,
                failTitle :"刷新失败:"
            };

            commonAjaxRequest(request);
        });
    </script>
</div>
