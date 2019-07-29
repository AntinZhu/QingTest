<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>刷性能测试环境数据</title>
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
                            性能测试环境用户数据刷新
                        </h1>
                    </div><!-- /.page-header -->

                    <div class="col-xs-12 col-sm-12">
                        <div class="row hide" id="taDiv">
                            <div class="col-sm-12">
                                <h3 class="header blue lighter smaller">
                                    <i class="icon-spinner"></i>
                                    t_assistant ---> t_user_login_account
                                </h3>

                                <div id="ta"></div>
                            </div><!-- ./span -->
                        </div><!-- ./row-fluid -->

                        <div class="row hide" id="teacherDiv">
                            <div class="col-sm-12">
                                <h3 class="header blue lighter smaller">
                                    <i class="icon-spinner"></i>
                                    t_teacher ---> t_user_login_account
                                </h3>

                                <div id="teacher"></div>
                            </div><!-- ./span -->
                        </div><!-- ./row-fluid -->

                        <div class="row hide" id="studentDiv">
                            <div class="col-sm-12">
                                <h3 class="header blue lighter smaller">
                                    <i class="icon-spinner"></i>
                                    t_user_info ---> t_user_login_account
                                </h3>

                                <div id="student"></div>
                            </div><!-- ./span -->
                        </div><!-- ./row-fluid -->


                        <button class="btn btn-info" type="button" id="searchBtn">
                            <i class="icon-ok bigger-110"></i>
                            开始
                        </button>
                        <div id="message">
                        </div>
                    </div>
                </div>
                </div>

            <#include "/include/righttool-sidebar.ftl" />

<script type="text/javascript">
    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();

        $("#searchBtn").click(function(){
            var request = {
                url : "${base}/v1/utils/phoneNumber/sync",
                data : null,
                handlerFunc : handleSearch,
                isASync : true,
                failTitle :"刷新性能测试环境用户数据:"
            };

            commonAjaxRequest(request);
        });
    });

    function handleSearch() {

    }
    
    var websocket = null;

    //判断当前浏览器是否支持WebSocket
    if('WebSocket' in window){
        var domain = window.location.host;
        websocket = new WebSocket("ws://" + domain + "/${base}/websocket/phoneNumber-sync");
    }
    else{
        alert('Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function(){
        setMessageInnerHTML("error");
    };

    //连接成功建立的回调方法
    websocket.onopen = function(event){
        setMessageInnerHTML("open");
    }

    //接收到消息的回调方法
    websocket.onmessage = function(event){
        setMessageData(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function(){
        setMessageInnerHTML("close");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function(){
        websocket.close();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(data){
        document.getElementById('message').innerHTML += data + '<br/>';
    }

    //将消息显示在网页上
    function setMessageData(data){
        data = JSON.parse(data);
        document.getElementById('message').innerHTML = data.desc + '<br/>' + document.getElementById('message').innerHTML;
        if(data.userType != null){
            $("#" + data.userType + "Div").removeClass("hide");
            $( "#" + data.userType ).progressbar({
                max :data.total,
                value: data.end,
                create: function( event, ui ) {
                    $(this).addClass('progress progress-striped active')
                            .children(0).addClass('progress-bar progress-bar-success');
                }
            });
        }
    }

    //关闭连接
    function closeWebSocket(){
        websocket.close();
    }

    //发送消息
    function send(){
        var message = document.getElementById('text').value;
        websocket.send(message);
    }
</script>
    </div>
</body>
</html>