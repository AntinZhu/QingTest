<#assign base=request.contextPath />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<!-- basic styles -->
<link href="${base}/static/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${base}/static/assets/css/font-awesome.min.css" />

<!--[if IE 7]>
<link rel="stylesheet" href="${base}/static/assets/css/font-awesome-ie7.min.css" />
<![endif]-->

<!-- page specific plugin styles -->
<link rel="stylesheet" href="${base}/static/assets/css/select2.css" />
<link rel="stylesheet" href="${base}/static/assets/css/chosen.css"/>
<link rel="stylesheet" href="${base}/static/assets/css/jquery-ui-1.10.3.full.min.css" />
<#--<link rel="stylesheet" href="${base}/static/assets/css/jquery-ui-1.10.3.custom.min.css" />-->
<link rel="stylesheet" href="${base}/static/assets/css/jquery.gritter.css" />
<!-- fonts -->
<link rel="stylesheet" href="${base}/static/assets/css/ace-fonts.css" />

<link rel="stylesheet" href="${base}/static/assets/css/ace.min.css" />
<link rel="stylesheet" href="${base}/static/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${base}/static/assets/css/ace-skins.min.css" />

<link rel="stylesheet" href="${base}/static/assets/css/datepicker.css" />
<link rel="stylesheet" href="${base}/static/assets/css/bootstrap-timepicker.css" />
<link rel="stylesheet" href="${base}/static/assets/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="${base}/static/assets/css/daterangepicker.css" />
<link rel="stylesheet" href="${base}/static/assets/css/colorpicker.css" />
<link rel="stylesheet" href="${base}/static/assets/css/bootstrap-editable.css" />
<link rel="stylesheet" href="${base}/static/css/common.css" />
<!-- ace styles -->

<!--[if lte IE 8]>
<link rel="stylesheet" href="${base}/static/assets/css/ace-ie.min.css" />
<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
<script src="${base}/static/assets/js/html5shiv.js"></script>
<script src="${base}/static/assets/js/respond.min.js"></script>

<![endif]-->

<!-- basic scripts -->

<!--[if !IE]> -->

<script type="text/javascript">
    window.jQuery || document.write("<script src='${base}/static/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='${base}/static/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

<script type="text/javascript">
    if("ontouchend" in document) document.write("<script src='${base}/static/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="${base}/static/assets/js/jquery-ui-1.10.3.full.min.js"></script>
<script src="${base}/static/assets/js/bootstrap.min.js"></script>
<script src="${base}/static/assets/js/typeahead-bs2.min.js"></script>

<script src="${base}/static/assets/js/jquery.dataTables.min.js"></script>
<script src="${base}/static/assets/js/jquery.dataTables.bootstrap.js"></script>

<script src="${base}/static/assets/js/jquery.gritter.min.js"></script>

<!-- page specific plugin scripts -->
<script src="${base}/static/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${base}/static/assets/js/ace-extra.min.js"></script>

<!-- ace scripts -->
<script src="${base}/static/assets/js/jquery.nestable.min.js"></script>
<script src="${base}/static/assets/js/ace-elements.min.js"></script>
<script src="${base}/static/assets/js/ace.min.js"></script>
<script src="${base}/static/assets/js/chosen.jquery.min.js"></script>
<script src="${base}/static/assets/js/select2.min.js"></script>
<script src="${base}/static/assets/js/fuelux/fuelux.wizard.min.js"></script>
<script src="${base}/static/assets/js/jquery.validate.min.js"></script>
<script src="${base}/static/assets/js/additional-methods.min.js"></script>
<script src="${base}/static/assets/js/bootbox.min.js"></script>
<script src="${base}/static/assets/js/jquery.maskedinput.min.js"></script>
<script src="${base}/static/assets/js/x-editable/bootstrap-editable.min.js"></script>
<script src="${base}/static/assets/js/x-editable/ace-editable.min.js"></script>
<script src="${base}/static/assets/js/x-editable/bootstrap-editable.min.js"></script>
<script src="${base}/static/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
<script src="${base}/static/js/common.js"></script>
<script src="${base}/static/js/catelog.js"></script>

<link rel="stylesheet" href="${base}/static/css/adminmain2.css" type="text/css"/>
<script type="text/javascript">
    jQuery(function($) {
        //override dialog's title function to allow for HTML titles
        $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
            _title: function(title) {
                var $title = this.options.title || '&nbsp;'
                if( ("title_html" in this.options) && this.options.title_html == true )
                    title.html($title);
                else title.text($title);
            }
        }));

        //custom autocomplete (category selection)
        $.widget( "custom.catcomplete", $.ui.autocomplete, {
            _renderMenu: function( ul, items ) {
                var that = this,
                        currentCategory = "";
                $.each( items, function( index, item ) {
                    if ( item.category != currentCategory ) {
                        ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
                        currentCategory = item.category;
                    }
                    that._renderItemData( ul, item );
                });
            }
        });
    });

</script>