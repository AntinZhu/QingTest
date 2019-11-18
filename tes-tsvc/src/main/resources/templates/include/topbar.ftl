<div class="navbar navbar-default" id="navbar">
    <input type="hidden" id="qing-base" value="${base}" />
    <script type="text/javascript">
        try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="icon-leaf"></i>
                    API TEST
                </small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="grey">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="icon-tasks"></i>
                        <span class="badge badge-grey">4</span>
                    </a>

                    <ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="icon-ok"></i>
                            4 Tasks to complete
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                    <span class="pull-left">Software Update</span>
                                    <span class="pull-right">65%</span>
                                </div>

                                <div class="progress progress-mini ">
                                    <div style="width:65%" class="progress-bar "></div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                    <span class="pull-left">Hardware Upgrade</span>
                                    <span class="pull-right">35%</span>
                                </div>

                                <div class="progress progress-mini ">
                                    <div style="width:35%" class="progress-bar progress-bar-danger"></div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                    <span class="pull-left">Unit Testing</span>
                                    <span class="pull-right">15%</span>
                                </div>

                                <div class="progress progress-mini ">
                                    <div style="width:15%" class="progress-bar progress-bar-warning"></div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                    <span class="pull-left">Bug Fixes</span>
                                    <span class="pull-right">90%</span>
                                </div>

                                <div class="progress progress-mini progress-striped active">
                                    <div style="width:90%" class="progress-bar progress-bar-success"></div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                See tasks with details
                                <i class="icon-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="purple">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="icon-bell-alt icon-animated-bell"></i>
                        <span class="badge badge-important">8</span>
                    </a>

                    <ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="icon-warning-sign"></i>
                            8 Notifications
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-pink icon-comment"></i>
												New Comments
											</span>
                                    <span class="pull-right badge badge-info">+12</span>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="btn btn-xs btn-primary icon-user"></i>
                                Bob just signed up as an editor ...
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-success icon-shopping-cart"></i>
												New Orders
											</span>
                                    <span class="pull-right badge badge-success">+8</span>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-info icon-twitter"></i>
												Followers
											</span>
                                    <span class="pull-right badge badge-info">+11</span>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                See all notifications
                                <i class="icon-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="green">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="icon-envelope icon-animated-vertical"></i>
                        <span class="badge badge-success">5</span>
                    </a>

                    <ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="icon-envelope-alt"></i>
                            5 Messages
                        </li>

                        <li>
                            <a href="#">
                                <img src="${base}/static/assets/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" />
                                <span class="msg-body">
											<span class="msg-title">
												<span class="blue">Alex:</span>
												Ciao sociis natoque penatibus et auctor ...
											</span>

											<span class="msg-time">
												<i class="icon-time"></i>
												<span>a moment ago</span>
											</span>
										</span>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <img src="${base}/static/assets/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar" />
                                <span class="msg-body">
											<span class="msg-title">
												<span class="blue">Susan:</span>
												Vestibulum id ligula porta felis euismod ...
											</span>

											<span class="msg-time">
												<i class="icon-time"></i>
												<span>20 minutes ago</span>
											</span>
										</span>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <img src="${base}/static/assets/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar" />
                                <span class="msg-body">
											<span class="msg-title">
												<span class="blue">Bob:</span>
												Nullam quis risus eget urna mollis ornare ...
											</span>

											<span class="msg-time">
												<i class="icon-time"></i>
												<span>3:15 pm</span>
											</span>
										</span>
                            </a>
                        </li>

                        <li>
                            <a href="inbox.html">
                                See all messages
                                <i class="icon-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <#if (qing_user!'') != '' && ((qing_user.headImage)!'') != ''>
                            <img class="nav-user-photo" src="${qing_user.headImage}" alt="Jason's Photo" />
                        <#else >
                            <img class="nav-user-photo" src="${base}/static/assets/avatars/user.jpg" alt="Jason's Photo" />
                        </#if>

                        <span class="user-info">
									<small>Welcome,</small>
                                    <div id="qing_ip">${qing_user_name!'俺'}</div>
                                    <input type="hidden" id="qing_user_ip" value="${qing_user_ip!''}" />
								</span>

                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="${base}/v1/user/list_page?catelogIndex=3-8">
                                <i class="icon-cog"></i>
                                Settings
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="icon-user"></i>
                                Profile
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="#">
                                <i class="icon-off"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                </li>
            </ul><!-- /.ace-nav -->
        </div><!-- /.navbar-header -->
    </div><!-- /.container -->
</div>

<div class="row col-xs-12 col-sm-4 qing_catelog_hide" style="z-index: 100;position: fixed;left: 40%;top: 90%;" id="qing_message_div">
    <div class="widget-box">
        <div class="widget-header">
            <h4>Message</h4>

            <span class="widget-toolbar">
                <a href="#" data-action="collapse">
                    <i class="icon-chevron-up"></i>
                </a>
            </span>
        </div>

        <div class="widget-body">
            <div class="widget-main" style="height: 63px;">
                <div>
                    <div class="input-group">
                        <input class="form-control input-mask-date" type="text" id="qing_message_input" />
                        <span class="input-group-btn">
                        <button class="btn btn-sm btn-default" id="qing_message_input_btn" type="button">
                            Go!
                        </button>
                    </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var websocket = null;
    //输入框的值改变时触发
    $(document).on("click", "#qing_message_input_btn",function(e){
        var msg = $("#qing_message_input").val();
        if(msg == null || msg == ''){
            return;
        }

        sendQingMsg(msg);
    });

    function sendQingMsg(msg){
        var assignIp = "";
        var splits = msg.split("#");
        if(splits.length > 1 && isValidIP(splits[0])){
            assignIp = splits[0];
            msg = msg.replace(assignIp + "#", "")
        }

        var userName = $("#qing_ip").text();
        var userIp = $("#qing_user_ip").val();
        var fullMsg = {
            userIp: userIp,
            userName : userName,
            msg : msg,
            assignIp : assignIp
        }

        websocket.send(JSON.stringify(fullMsg));
    }

    function isValidIP(ip) {
        var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
        return reg.test(ip);
    }

    $(document).ready(function() {
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            var domain = window.location.host;
            websocket = new WebSocket("ws://" + domain + "${base}/websocket/ip_up");
        } else {
            alert('Not support websocket')
        }

        //连接发生错误的回调方法
        websocket.onerror = function () {
        };

        //连接成功建立的回调方法
        websocket.onopen = function (event) {
        }

        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            if(event.data != null){
                var msg = JSON.parse(event.data);
                if(msg.msg != null){
                    var userIp = $("#qing_user_ip").val();
                    if(msg.userIp != userIp && (msg.assignIp == "" ||  msg.assignIp == userIp)){
                        if(msg.msg == "{up_ip}"){
                            upIp();
                        }else if(msg.msg == "{deploy}"){
                            var calc=new ActiveXObject("WScript.shell");
                            calc.run("F:/work/Github/auto_deploy.bat");
                            sendQingMsg("服务即将重启");
                        }else if(msg.msg == "{full_deploy}"){
                            var calc=new ActiveXObject("WScript.shell");
                            calc.run("F:/work/Github/full_auto_deploy.bat");
                            sendQingMsg("服务即将重启");
                        }else{
                            $.gritter.add({
                                title: '新消息',
                                text: msg.userName + ":" + msg.msg,
                                class_name: 'gritter-info'
                            });
                        }
                    }
                }
            }
        }

        //连接关闭的回调方法
        websocket.onclose = function () {
            $("#qing_message_div").addClass("hide");
            websocket.close();
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            websocket.close();
        }

        //将消息显示在网页上
        function setMessageInnerHTML(data) {
        }
    });
</script>