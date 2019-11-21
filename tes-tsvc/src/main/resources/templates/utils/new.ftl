<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3" xmlns="http://www.w3.org/1999/html">
<head>
    <title>新功能介绍</title>
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
                        <div class="row">
                            <div class="col-xs-12">
                                <!-- PAGE CONTENT BEGINS -->

                                <div class="col-sm-12">
                                    <div class="widget-box ">
                                        <div class="widget-header">
                                            <h4 class="lighter smaller">
                                                <i class="icon-comment blue"></i>
                                                Conversation
                                            </h4>
                                        </div>

                                        <div class="widget-body">
                                            <div class="widget-main no-padding">
                                                <div class="dialogs">
                                                    <div class="itemdiv dialogdiv">
                                                        <div class="user">
                                                            <img alt="Alexa's Avatar" src="${base}/static/assets/avatars/avatar1.png" />
                                                        </div>

                                                        <div class="body">
                                                            <div class="time">
                                                                <i class="icon-time"></i>
                                                                <span class="green">4 sec</span>
                                                            </div>

                                                            <div class="name">
                                                                <a href="#">Alexa</a>
                                                            </div>
                                                            <div class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque commodo massa sed ipsum porttitor facilisis.</div>

                                                            <div class="tools">
                                                                <a href="#" class="btn btn-minier btn-info">
                                                                    <i class="icon-only icon-share-alt"></i>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="itemdiv dialogdiv">
                                                        <div class="user">
                                                            <img alt="John's Avatar" src="${base}/static/assets/avatars/avatar.png" />
                                                        </div>

                                                        <div class="body">
                                                            <div class="time">
                                                                <i class="icon-time"></i>
                                                                <span class="blue">38 sec</span>
                                                            </div>

                                                            <div class="name">
                                                                <a href="#">John</a>
                                                            </div>
                                                            <div class="text">Raw denim you probably haven&#39;t heard of them jean shorts Austin.</div>

                                                            <div class="tools">
                                                                <a href="#" class="btn btn-minier btn-info">
                                                                    <i class="icon-only icon-share-alt"></i>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="itemdiv dialogdiv">
                                                        <div class="user">
                                                            <img alt="Bob's Avatar" src="${base}/static/assets/avatars/user.jpg" />
                                                        </div>

                                                        <div class="body">
                                                            <div class="time">
                                                                <i class="icon-time"></i>
                                                                <span class="orange">2 min</span>
                                                            </div>

                                                            <div class="name">
                                                                <a href="#">Bob</a>
                                                                <span class="label label-info arrowed arrowed-in-right">admin</span>
                                                            </div>
                                                            <div class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque commodo massa sed ipsum porttitor facilisis.</div>

                                                            <div class="tools">
                                                                <a href="#" class="btn btn-minier btn-info">
                                                                    <i class="icon-only icon-share-alt"></i>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="itemdiv dialogdiv">
                                                        <div class="user">
                                                            <img alt="Jim's Avatar" src="${base}/static/assets/avatars/avatar4.png" />
                                                        </div>

                                                        <div class="body">
                                                            <div class="time">
                                                                <i class="icon-time"></i>
                                                                <span class="grey">3 min</span>
                                                            </div>

                                                            <div class="name">
                                                                <a href="#">Jim</a>
                                                            </div>
                                                            <div class="text">Raw denim you probably haven&#39;t heard of them jean shorts Austin.</div>

                                                            <div class="tools">
                                                                <a href="#" class="btn btn-minier btn-info">
                                                                    <i class="icon-only icon-share-alt"></i>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="itemdiv dialogdiv">
                                                        <div class="user">
                                                            <img alt="Alexa's Avatar" src="${base}/static/assets/avatars/avatar1.png" />
                                                        </div>

                                                        <div class="body">
                                                            <div class="time">
                                                                <i class="icon-time"></i>
                                                                <span class="green">4 min</span>
                                                            </div>

                                                            <div class="name">
                                                                <a href="#">Alexa</a>
                                                            </div>
                                                            <div class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</div>

                                                            <div class="tools">
                                                                <a href="#" class="btn btn-minier btn-info">
                                                                    <i class="icon-only icon-share-alt"></i>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <form>
                                                    <div class="form-actions">
                                                        <div class="input-group">
                                                            <input placeholder="Type your message here ..." type="text" class="form-control" name="message" />
                                                            <span class="input-group-btn">
																	<button class="btn btn-sm btn-info no-radius" type="button">
																		<i class="icon-share-alt"></i>
																		Send
																	</button>
																</span>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div><!-- /widget-main -->
                                        </div><!-- /widget-body -->
                                    </div><!-- /widget-box -->
                                </div><!-- /span -->
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.page-header -->
                </div>
                </div>

            <#include "/include/righttool-sidebar.ftl" />

<script type="text/javascript">
    jQuery(function($) {
        $(".chosen-select").chosen();
        $('[data-rel=tooltip]').tooltip();

        $('.dialogs,.comments').slimScroll({
            height: '300px'
        });
    });
</script>
    </div>
</body>
</html>