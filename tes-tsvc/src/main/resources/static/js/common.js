$.fn.setCursorPosition = function(position){
    if(this.lengh == 0) return this;
    return $(this).setSelection(position, position);
}

$.fn.setSelection = function(selectionStart, selectionEnd) {
    if(this.lengh == 0) return this;
    input = this[0];

    if (input.createTextRange) {
        var range = input.createTextRange();
        range.collapse(true);
        range.moveEnd('character', selectionEnd);
        range.moveStart('character', selectionStart);
        range.select();
    } else if (input.setSelectionRange) {
        input.focus();
        input.setSelectionRange(selectionStart, selectionEnd);
    }

    return this;
}

$.fn.focusEnd = function(){
    this.setCursorPosition(this.val().length);
}

Date.prototype.format=function(fmt) {        
    var o = {        
    "M+" : this.getMonth()+1,
    "d+" : this.getDate(),
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12,
    "H+" : this.getHours(),
    "m+" : this.getMinutes(),
    "s+" : this.getSeconds(), 
    "q+" : Math.floor((this.getMonth()+3)/3),
    "S" : this.getMilliseconds()
    };        
    var week = {        
    "0" : "\u65e5",        
    "1" : "\u4e00",        
    "2" : "\u4e8c",        
    "3" : "\u4e09",        
    "4" : "\u56db",        
    "5" : "\u4e94",        
    "6" : "\u516d"       
    };        
    if(/(y+)/.test(fmt)){        
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));        
    }        
    if(/(E+)/.test(fmt)){        
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);        
    }        
    for(var k in o){        
        if(new RegExp("("+ k +")").test(fmt)){        
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));        
        }        
    }        
    return fmt;        
}  

function   chineseFromUtf8Url(strUtf8)    
{ 
var   bstr   =   ""; 
var   nOffset   =   0; //   processing   point   on   strUtf8 
   
if(   strUtf8   ==   ""   ) 
      return   ""; 
   
strUtf8   =   strUtf8.toLowerCase(); 
nOffset   =   strUtf8.indexOf("%e"); 
if(   nOffset   ==   -1   ) 
      return   strUtf8; 
       
while(   nOffset   !=   -1   ) 
{ 
      bstr   +=   strUtf8.substr(0,   nOffset); 
      strUtf8   =   strUtf8.substr(nOffset,   strUtf8.length   -   nOffset); 
      if(   strUtf8   ==   ""   ||   strUtf8.length   <   9   )       //   bad   string 
          return   bstr; 
       
      bstr   +=   utf8CodeToChineseChar(strUtf8.substr(0,   9)); 
      strUtf8   =   strUtf8.substr(9,   strUtf8.length   -   9); 
      nOffset   =   strUtf8.indexOf("%e"); 
} 
   
return   bstr   +   strUtf8; 
}

function commonAjaxRequest(request){
    var url = request.url;
    var data = request.data;
    var handlerFunc = request.handlerFunc;
    var isASync = request.isASync;
    var failTitle = request.failTitle;
    var env = request.env;
    var otherData = request.otherData;
    var guid = request.guid;
    var headers = request.headers;

    var result = true;
    if(env == null){
        env = "";
    }
    if(guid == null){
        guid = "";
    }

    if(url.indexOf("?") == -1){
        url += "?env=" + env + "&guid=" + guid
    }else{
        url += "&env=" + env + "&guid=" + guid
    }

    $.ajax({
        type : "POST",
        url : url,
        timeout : 300000,
        headers: headers,
        data : JSON.stringify(data),
        dataType : 'json',
        crossDomain: headers != null,
        async :isASync,
        contentType: 'application/json',
        success : function(resu) {
            if(resu.response.error_code != 0){
                switch (resu.response.error_code){
                    case 422:
                        handlerErrorStatusCode(resu.response.error_code, resu);
                        break;
                    default :
                        gritterError(failTitle, resu.response);
                        break;
                }
                result = false;
            }else{
                result = handlerFunc(resu, otherData);
            }
        },
        error :function (jqXHR, textStatus, errorThrown) {
            switch (jqXHR.status){
                case 422:
                    handlerErrorStatusCode(422, jqXHR.responseText);
                    break;
            }
        },
        statusCode: {
            404: function() {
                handlerErrorStatusCode(404);
            },
            500: function() {
                handlerErrorStatusCode(500);
            }
        }
    });

    return result;
}

function commonRestAjaxRequest(request){
    var url = request.url;
    var data = request.data;
    var handlerFunc = request.handlerFunc;
    var isASync = request.isASync;
    var failTitle = request.failTitle;
    var env = request.env;
    var otherData = request.otherData;
    var guid = request.guid;
    var headers = request.headers;

    var result = true;
    if(env == null){
        env = "";
    }
    if(guid == null){
        guid = "";
    }

    if(url.indexOf("?") == -1){
        url += "?env=" + env + "&guid=" + guid
    }else{
        url += "&env=" + env + "&guid=" + guid
    }

    $.ajax({
        type : "POST",
        url : url,
        timeout : 300000,
        headers: headers,
        data : JSON.stringify(data),
        dataType : 'json',
        crossDomain: headers != null,
        async :isASync,
        contentType: 'application/json',
        success : function(resu) {
            result = handlerFunc(resu, otherData);
        },
        error :function (jqXHR, textStatus, errorThrown) {
            switch (jqXHR.status){
                case 422:
                    handlerErrorStatusCode(422, jqXHR.responseText);
                    break;
            }
        },
        statusCode: {
            404: function() {
                handlerErrorStatusCode(404);
            },
            500: function() {
                handlerErrorStatusCode(500);
            }
        }
    });
    return result;
}


function handlerErrorStatusCode(errorStatus, resu){
    switch (errorStatus){
        case 500:
            $.gritter.add({
                title : '呀呀的:',
                text : '服务自己500了，你叫我怎么办',
                class_name : 'gritter-error gritter-center'
            });
            break;
        case 404:
            $.gritter.add({
                title : '呵呵:',
                text : '你确认你的请求url配置正确了？',
                class_name : 'gritter-error gritter-center'
            });
            break;
        case 422:
            var hintMsg = "";
            if(resu != ""){
                var response = JSON.parse(resu);
                hintMsg = response.response.error_message;
            }
            $.gritter.add({
                title : '咋了:',
                text : '请求参数出错了,服务器返回：' +  hintMsg,
                class_name : 'gritter-error gritter-center'
            });
            break;
    }
}

function gritterError(title, response){
    var msg = response.hint_message;
    if(msg == null || "" == msg){
        msg = response.error_message;
    }
    $.gritter.add({
        title : title,
        text : msg,
        class_name : 'gritter-error gritter-center'
    });
}

function notice(title, msg){
    $.gritter.add({
        title : title,
        text : msg,
        class_name : 'gritter-error gritter-center'
    });
}

function deleteEmptyProperty(object){
    for (var i in object) {
        var value = object[i];
        if (typeof value === 'object') {
            if (Array.isArray(value)) {
                if (value.length == 0) {
                    delete object[i];
                    continue;
                }
            }
            this.deleteEmptyProperty(value);
            if (this.isEmpty(value)) {
                delete object[i];
            }
        } else {
            if (value === '' || value === null || value === undefined) {
                delete object[i];
            } else {
            }
        }
    }
}

function isStringEmpty(object) {
    return object == null || object == "";
}

function isEmpty(object) {
    for (var name in object) {
        return false;
    }
    return true;
}

var allIcon = [
    "icon-adjust",
    "icon-asterisk",
    "icon-ban-circle",
    "icon-bar-chart",
    "icon-barcode",
    "icon-beaker",
    "icon-beer",
    "icon-bell",
    "icon-bell-alt",
    "icon-bolt",
    "icon-book",
    "icon-bookmark",
    "icon-bookmark-empty",
    "icon-briefcase",
    "icon-bullhorn",
    "icon-calendar",
    "icon-camera",
    "icon-camera-retro",
    "icon-certificate",
    "icon-check",
    "icon-check-empty",
    "icon-circle",
    "icon-circle-blank",
    "icon-cloud",
    "icon-cloud-download",
    "icon-cloud-upload",
    "icon-coffee",
    "icon-cog",
    "icon-cogs",
    "icon-comment",
    "icon-comment-alt",
    "icon-comments",
    "icon-comments-alt",
    "icon-credit-card",
    "icon-dashboard",
    "icon-desktop",
    "icon-download",
    "icon-download-alt",
    "icon-edit",
    "icon-envelope",
    "icon-envelope-alt",
    "icon-exchange",
    "icon-exclamation-sign",
    "icon-external-link",
    "icon-eye-close",
    "icon-eye-open",
    "icon-facetime-video",
    "icon-fighter-jet",
    "icon-film",
    "icon-filter",
    "icon-fire",
    "icon-flag",
    "icon-folder-close",
    "icon-folder-open",
    "icon-folder-close-alt",
    "icon-folder-open-alt",
    "icon-food",
    "icon-gift",
    "icon-glass",
    "icon-globe",
    "icon-group",
    "icon-hdd",
    "icon-headphones",
    "icon-heart",
    "icon-heart-empty",
    "icon-home",
    "icon-inbox",
    "icon-info-sign",
    "icon-key",
    "icon-leaf",
    "icon-laptop",
    "icon-legal",
    "icon-lemon",
    "icon-lightbulb",
    "icon-lock",
    "icon-unlock"
];

function randomIcon(){
    return allIcon[random(0, allIcon.length - 1)];
}


/**
 * 产生随机整数，包含下限值，但不包括上限值
 * @param {Number} lower 下限
 * @param {Number} upper 上限
 * @return {Number} 返回在下限到上限之间的一个随机整数
 */
function random(lower, upper) {
    return Math.floor(Math.random() * (upper - lower)) + lower;
}

function activeCatelog(catelogIndex){
    if(catelogIndex != null && catelogIndex != ""){
        var activeIndexArr = catelogIndex.split("-");
        var activeIndex = "";
        for(var idx in activeIndexArr){
            var index = activeIndexArr[idx];
            if(idx > 0){
                activeIndex += "-";
            }
            activeIndex = activeIndex + index;
            $("." + activeIndex).addClass("active");
            $("." + activeIndex + " a:first i:first").addClass("icon-bookmark");
        }
    }
}

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

function jsonShow(content, id){
    var result = '';
    if(Object.prototype.toString.call(content) != "[object String]"){
        content = JSON.stringify(content);
    }
    if (content!='') {
        try{
            current_json = jsonlint.parse(content);
            current_json_str = JSON.stringify(current_json);
            result = new JSONFormat(content,4).toString();
        }catch(e){
            result = '<span style="color: #f1592a;font-weight:bold;">' + e + '</span>';
            current_json_str = result;
        }

        $('#' + id).html(result);
    }else{
        $('#' + id).html('');
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

var tradeNotifyLogUrl = "http://172.22.12.14:5601/app/logtrail#/?q=env_type:%20%22{env}%22%20%26%26%20%22{tradeId}%22&h=svc&t=Now&i=rsyslog-app*&_g=()";
function thirdPayWayList(payBriefList){
    var trText = "";
    for(idx in payBriefList){
        var payBrief = payBriefList[idx];

        var logUrl = tradeNotifyLogUrl;
        logUrl = logUrl.replace("{env}", $("#env").val());
        logUrl = logUrl.replace("{tradeId}", payBrief.tradeId);

        trText = trText + "<tr><td>" + payBrief.payTypeName + "</td><td class=\"hidden-480\">" + payBrief.qingqingTradeNo + "</td><td>" + payBrief.tradeId + "</td><td class=\"hidden-480\"><span class=\"label label-sm label-warning\">" + payBrief.payStatus + "</span></td><td><div class=\" btn-group\"><button type=\"button\" class=\"btn btn-xs btn-success mockPayBtn\"><i class=\"icon-ok bigger-120\"></i></button></div></td><td><a target=\"_blank\" href='" + logUrl + "'>查看日志</a> </td></tr>";
    }

    return trText;
}

function getAndValidateEmpty(id, paranName){
    var param = $("#" + id).val();
    if(param == null || param == ""){
        $.gritter.add({
            title : '参数错误:',
            text : paranName + "不能为空",
            class_name : 'gritter-error gritter-center'
        });
    }

    return param;
}

function formatObj(obj){
    return JSON.stringify(obj);
}

function getyyyyMMdd(){
    var d = new Date();
    var curr_date = d.getDate();
    var curr_month = d.getMonth() + 1;
    var curr_year = d.getFullYear();
    String(curr_month).length < 2 ? (curr_month = "0" + curr_month): curr_month;
    String(curr_date).length < 2 ? (curr_date = "0" + curr_date): curr_date;
    var yyyyMMdd = curr_year + "-" + curr_month +"-"+ curr_date;
    return yyyyMMdd;
}

function generateGuid() { 
    return 'test-api-xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {        
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);        
        return v.toString(16);    
    });
}

function updateOptions(elementId, data, defaultSelectedKey){
    var selector = document.getElementById(elementId);
    selector.length = 0;

    var valueMaxLen = 20;
    var option;
    if(data == null || data.size == 0){
        option = document.createElement("option");

        option.text = "当前无可用数据";

        selector.options.add(option);
    }else{
        for(idx in data){
            var siteType = data[idx];
            option = document.createElement("option");
            option.value = siteType.key;
            option.text = siteType.value;
            var valueLen = siteType.value.gblen();
            if(valueLen > valueMaxLen){
                valueMaxLen = valueLen;
            }

            if(defaultSelectedKey == null && idx == 0){
                option.setAttribute("selected", "selected");
            }else if(defaultSelectedKey != null && siteType.key == defaultSelectedKey){
                option.setAttribute("selected", "selected");
            }

            selector.options.add(option);
        }
    }
    $("#" + elementId).trigger("chosen:updated");

    $("#" + elementId + "_chosen").css('width',(valueMaxLen * 10) + 'px');
};

function emptyFunction(resu){

}

function simpleSucc(resu){
    $.gritter.add({
        title : '提示:',
        text : "接口调用成功",
        class_name : 'gritter-info gritter-center'
    });
}

function sleep(delay) {
    var start = new Date().getTime();
    while (new Date().getTime() < start + delay)
        ;
}

function genOptionsWithEmpty(arr, propName){
    var items = [];
    var item = new Object();
    item.key = "";
    item.value = "空";

    items.push(item);
    var valueArr = genOptions(arr, propName);
    for(var idx in valueArr){
        items.push(valueArr[idx]);
    }

    return items;
}

function getEmptyOptions(){
    var items = [];
    var item = new Object();
    item.key = "";
    item.value = "空";

    items.push(item);

    return items;
}

function genOptions(arr, propName){
    var items = [];

    var keyArr = [];
    for(var idx in arr){
        var arrItem = arr[idx];

        if(!contain(keyArr, arrItem[propName])){
            var item = new Object();
            item.key = arrItem[propName];
            item.value = arrItem[propName];

            items.push(item);
        }
    }

    return items;
}

function contain(arr, item){
    for(var idx in arr){
        if(arr[idx] == item){
            return true;
        }
    }

    return false;
}

String.prototype.gblen = function() {
    var len = 0;
    for (var i=0; i<this.length; i++) {
        if (this.charCodeAt(i)>127 || this.charCodeAt(i)==94) {
            len += 2;
        } else {
            len ++;
        }
    }
    return len;
}

function add0(m){return m<10?'0'+m:m }
function formatDate(shijianchuo){
    var time = new Date(shijianchuo);
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}

function formatDate_yyyyMMDD(shijianchuo){
    var time = new Date(shijianchuo);
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return y + '-' + m + "-" + d;
}

function formatDate_hhmmss(shijianchuo){
    var time = new Date(shijianchuo);
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return h + ':' + mm + ":" + s;
}

function toShijiancuo(dateDtring){
    var time = new Date(dateDtring);

    return time.getTime();
}

function notOps(resu){

}

function qingReplaceStringWithObj(value, obj){
    for(propName in obj){
        var prop = obj[propName];
        if(Object.prototype.toString.call(prop) === '[Object Object]'){
            qingReplaceStringWithObjAndPrefix(propName, value, prop);
        }else{
            value = value.replace("{" + propName + "}", prop);
        }
    }

    return value;
}

function qingReplaceStringWithObjAndPrefix(propPrefix, value, obj){
    for(propName in obj){
        var prop = obj[propName];
        var fullPropName = propPrefix + "." + propName;

        if(Object.prototype.toString.call(prop) === '[Object Object]'){
            qingReplaceStringWithObjAndPrefix(fullPropName, value, prop);
        }else{
            value = value.replace("{" + fullPropName + "}", prop);
        }
    }
}

function qingResize(hasChanged){
    if(window.screen.width < 1200){
        if(!hasChanged){
            $(".qing_resize.col-sm-6").addClass("col-sm-12");
            $(".qing_resize.col-sm-6").removeClass("col-sm-6");

            $(".qing_resize.col-sm-3").addClass("col-sm-6");
            $(".qing_resize.col-sm-3").removeClass("col-sm-3");
        }
        return true;
    }else{
        if(hasChanged){
            $(".qing_resize.col-sm-6").addClass("col-sm-3");
            $(".qing_resize.col-sm-6").removeClass("col-sm-6");

            $(".qing_resize.col-sm-12").addClass("col-sm-6");
            $(".qing_resize.col-sm-12").removeClass("col-sm-12");
        }
        return false;
    }
}

function updateMutiOptions(elementId, data, selectValList) {
    var selector = document.getElementById(elementId);
    selector.length = 0;

    var option;
    if(data == null || data.size == 0){
        option = document.createElement("option");

        option.text = "当前无可用数据";

        selector.options.add(option);
    }else{
        for(idx in data){
            var siteType = data[idx];
            option = document.createElement("option");
            option.value = siteType.key;
            option.text = siteType.value;
            if (siteType.title != 'undefined') {
                option.title = siteType.title;
            }

            if (selectValList != undefined) {
                if ($.inArray(siteType.key, selectValList) >= 0) {
                    option.setAttribute("selected", "selected");
                }
            }

            selector.options.add(option);
        }
    }
    $("#" + elementId).trigger("chosen:updated");
    $("#" + elementId + "_chosen").css('width','500px');
};

/**
 * 时间戳转化为年 月 日 时 分 秒
 * number: 传入时间戳
 * format：返回格式，支持自定义，但参数必须与formateArr里保持一致
 */
function formatTime(number,format) {

    var formateArr  = ['Y','M','D','h','m','s'];
    var returnArr   = [];

    var date = new Date(number * 1000);
    returnArr.push(date.getFullYear());
    returnArr.push(formatNumber(date.getMonth() + 1));
    returnArr.push(formatNumber(date.getDate()));

    returnArr.push(formatNumber(date.getHours()));
    returnArr.push(formatNumber(date.getMinutes()));
    returnArr.push(formatNumber(date.getSeconds()));

    for (var i in returnArr)
    {
        format = format.replace(formateArr[i], returnArr[i]);
    }
    return format;
}

//数据转化
function formatNumber(n) {
    n = n.toString()
    return n[1] ? n : '0' + n
}

function piSingleRequest(base, env, url) {
    var data = {
        url : url,
        param :"{}",
        userId:22367,
        userType : 'student'
    };

    var request = {
        url : base + "/v1/common/pi.json",
        data : data,
        handlerFunc : handleIgnore,
        isASync : true,
        failTitle :"请求失败:",
        env : env
    };

    commonAjaxRequest(request);
}

function handleIgnore(resu){

}
