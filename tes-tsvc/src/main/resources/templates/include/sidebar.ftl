<div class="sidebar" id="sidebar">
    <script type="text/javascript">
        try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
    </script>

    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="icon-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="icon-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="icon-group"></i>
            </button>

            <button class="btn btn-danger">
                <i class="icon-cogs"></i>
            </button>
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
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
            commonAjaxRequest("${base}/v1/test/catelog.json", null, handlerCatelog, false, "获取分类信息失败:");
        });

        var item_html = "<li class=\"{index}\"\><a href=\"{linkUrl}\"><i class=\"\"></i>{catelogName}</a></li>";
        var catelog_html = "<li class=\"{index}\"><a href=\"#\" class=\"dropdown-toggle\"><i class=\"{icon}\"></i><span class=\"menu-text\"> {catelogName} </span><b class=\"arrow icon-angle-down\"></b></a><ul class=\"submenu\">{items}</ul></li>";
        function handlerCatelog(resu){
            $("#catelog-ul").html(catelogList(resu.resultList));
        }

        function catelogList(catelogList){
            var catelogHtml = "";
            for(var idx in catelogList){
                var catelog = catelogList[idx];

                var thisHtml;
                if(catelog.subCategoryList != null){
                    thisHtml = catelogWithSub(catelog);
                }else{
                    thisHtml = catelogItem(catelog);
                }
                catelogHtml += thisHtml;
            }

            return catelogHtml;
        }

        function catelogWithSub(catelog){
            var thisHtml = catelog_html;
            thisHtml = thisHtml.replace("{catelogName}", catelog.catelog.catelogName);
            thisHtml = thisHtml.replace("{index}", catelog.catelog.catelogIndex);
            thisHtml = thisHtml.replace("{icon}", randomIcon());
            thisHtml = thisHtml.replace("{items}", catelogList(catelog.subCategoryList));

            return thisHtml;
        }

        function catelogItem(catelog){
            var thisHtml = item_html;
            thisHtml = thisHtml.replace("{catelogName}", catelog.catelog.catelogName);
            thisHtml = thisHtml.replace("{index}", catelog.catelog.catelogIndex);
            var linkUrl;
            if(catelog.catelog.linkUrl != null && "#" != catelog.catelog.linkUrl){
                linkUrl = "${base}" + catelog.catelog.linkUrl;
            }else{
                linkUrl = "#";
            }
            thisHtml = thisHtml.replace("{linkUrl}", linkUrl);

            return thisHtml;
        }
    </script>
</div>