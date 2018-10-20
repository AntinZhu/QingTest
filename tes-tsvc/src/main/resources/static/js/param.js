$.fn.editable.defaults.mode = 'inline';
$.fn.editableform.loading = "<div class='editableform-loading'><i class='light-blue icon-2x icon-spinner icon-spin'></i></div>";
$.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="icon-ok icon-white"></i></button>'+
'<button type="button" class="btn editable-cancel"><i class="icon-remove"></i></button>';

function cloneInput(){
    var inputClone = $(this).parent().parent().parent().clone();
    inputClone.children("div .addInputDiv").remove();
    var nowParent = $(this).parent().parent().parent();
    var nowParentAlt = $(nowParent).attr("alt");
    var childCnt = $(nowParent).parent().children(".profile-info-value").length;
    var newAltParent = replaceLast(nowParentAlt, childCnt);
    //var firstAltParent = firstAlt(newAltParent);

    $(inputClone).attr("alt", newAltParent);
    inputClone.find("[alt]").each(function(index,element){
        var nowAlt = $(element).attr("alt");
        var newAlt= nowAlt.replace(nowParentAlt, newAltParent);
        $(element).attr("alt", newAlt);
    });
    $(nowParent).parent().append(inputClone);

    editableInit();
}

function replaceLast(alt, lastNum){
    var newAlt = "";

    var splitArr = alt.split("-");
    for(var idx in splitArr){
        var altItem = splitArr[idx];
        if(idx == splitArr.length - 1){
            altItem = lastNum;
        }
        newAlt += newAlt ==""? altItem : "-" + altItem;
    }

    return newAlt;
}

function firstAlt(alt){
    var newAlt = "";

    var splitArr = alt.split("-");
    for(var idx in splitArr){
        var altItem = splitArr[idx];
        if(idx == splitArr.length - 1){
            altItem = 0;
        }
        newAlt += newAlt ==""? altItem : "-" + altItem;
    }

    return newAlt;
}

function removeInput(){
    if($(this).attr("isMulti") == "true"){
        var par = $(this).parent().parent().parent();
        $(this).parent().parent().remove();
        if(par.children("div.profile-info-value").length == 0){
            par.remove();
        }
    }else{
        $(this).parent().parent().parent().remove();
    }
    notifyParamChanged();
}

var paramInfo = new Object();
function initHtml(parentKey, params, valueChangedNotifyId){
    if(parentKey != ''){
        parentKey += "-";
    }

    for(var paramIdx in params) {
        var param = params[paramIdx];
        var isMulti = Array.isArray(param);
        if(isMulti){
            param = param[0];
        }

        var paramKey = parentKey + param.key;
        paramInfo[paramKey] = new Object();
        paramInfo[paramKey]["isMulti"] = isMulti;
        paramInfo["valueChangedNotifyId"] = valueChangedNotifyId;

        if(param.detail != null){
            initHtml(paramKey, param.detail);
        }else{
            var defaultName = "";
            var defaultValue = "";
            if(param.defaultValue != null){
                if(param.defaultValue.name != null){
                    defaultName = param.defaultValue.name;
                    defaultValue =  param.defaultValue.value;
                }else{
                    defaultName = param.defaultValue;
                    defaultValue = param.defaultValue;
                }
            }

            if(param.selectable != null){
                var options = [];
                for(var secIdx in param.selectable){
                    var sec = param.selectable[secIdx];
                    options.push({value: sec.value, text: sec.name});
                }

                paramInfo[paramKey]["options"] = options;
                paramInfo[paramKey]["is_selectable"] = true;
                paramInfo[paramKey]["selectable"] = param.selectable;
                paramInfo[paramKey]["defaultValue"] = param.defaultValue.value;
            }else{
                paramInfo[paramKey]["is_selectable"] = false;
            }

            paramInfo[paramKey]["type"] = Object.prototype.toString.call(defaultValue);
            paramInfo[paramKey]["class"] = param.class;
        }
    }

    editableInit();
}

function isNumber(value){
    return Object.prototype.toString.call(value) == "[object Number]";
}

var notParamPropertiesArr = ["valueChangedNotifyId"];
function editableInit(){
    for(var prop in paramInfo){
        if($.inArray(prop, notParamPropertiesArr) != -1){
            continue;
        }

        if(paramInfo[prop]["is_selectable"]){
            var options = paramInfo[prop]["options"];
            $('.' + prop + "_label").editable({
                type: 'select2',
                value : paramInfo[prop]["defaultValue"],
                source: options,
                // mode: "popup",
                disabled : getEditDisableStatus("#editBtnSwitch"),
                success: function(response, newValue) {
                    $(this).prev("input").val(newValue);
                    notifyParamChanged();
                }
            });
        }

        if(paramInfo[prop]["isMulti"]){
            $("div ." + prop).removeClass("hide");
            $("div ." + prop).removeClass("hide");
            $("." + prop + "_label").css("display", "block");
        }
    }

    $(document).find(".date_editable").each(function(key,value){
        $(value).editable({
            type: 'date',
            format: 'yyyy-mm-dd',
            viewformat: 'yyyy-mm-dd',
            disabled : getEditDisableStatus("#editBtnSwitch"),
            datepicker: {
                weekStart: 1
            },
            success: function(response, newValue) {
                $(this).prev("input").val(newValue.getTime());
                notifyParamChanged();
            }
        });

        var key = $(this).prev("input").attr("name");
        $("span[key='" + key + "']").removeClass("label-primary");
        $("span[key='" + key + "'][clazz='date_editable']").addClass("label-primary");
    });

    $(document).find(".input_editable").each(function(key,value){
        $(value).editable({
            type: 'text',
            disabled : getEditDisableStatus("#editBtnSwitch"),
            success: function(response, newValue) {
                $(this).prev("input").val(newValue);
                notifyParamChanged();
            }
        });
    });
}

function notifyParamChanged(){
    if(paramInfo["valueChangedNotifyId"] != null){
        $("#" + paramInfo["valueChangedNotifyId"]).trigger("change");
    }
}

function getEditDisableStatus(statusLocation){
    var editable = $(statusLocation).val();
    return editable != "1";
}

var del_btn_html = "<div class='spinner-buttons input-group-btn delInputDiv' style='display: inline-block;margin-right: 25px;'><button class='btn spinner-down btn-xs btn-danger delInputBtn' type='button'><i class='icon-minus smaller-75'></i></button></div>";
var add_btn_html = "<div class='spinner-buttons input-group-btn addInputDiv' style='display: inline-block;'><button class='btn spinner-up btn-xs btn-success addInputBtn'  type='button'><i class='icon-plus smaller-75'></i></button></div>";
del_btn_html = "<div class='pull-right action-buttons'><a class='red delInputBtn hide qing_param_edit' isMulti='{isMulti}' href='###'><i class='icon-trash bigger-130'></i></a></div>";
add_btn_html = "<div style='margin-bottom: 22px;' class='qing_param_edit hide'><div class='pull-right action-buttons'><a class='blue addInputBtn' href='###'><i class='icon-plus bigger-130'></i></a></div></div>";

var input_editable_html = "<div class='profile-info-row' alt='{alt}'><div class='profile-info-name'> {name} </div><div class='profile-info-value' alt='{alt}'>{editable}" + del_btn_html + "<input key='{key}' type='hidden' name='{key}' alt='{alt}' value='{defaultValue}'/><span class='editable input_label {key}_label {class}'>{defaultName}</span></div></div>";
var editable_table_html = "<div class='profile-user-info profile-user-info-striped' id = '{id}'>{paramList}</div>";
var sub_editable_html = "<div class='profile-info-row' alt='{alt}'><div class='profile-info-name'> {name} </div><div class='profile-info-value' alt='{alt}'>{editable}" + del_btn_html + "<div style='margin-right: 13px;'>{paramList}</div></div></div>";

var number_type_html = "<span class='col-xs-9 pull-right qing_param_edit_1 hide'><span class='pull-right inline'><span href='#' key='{key}' clazz='input_editable' class='label label-large label-primary arrowed-in arrowed-right qing_value_type'>数值</span> <span href='#' key='{key}' clazz='date_editable' class='label label-large arrowed-in arrowed-right qing_value_type'>日期毫秒值</span><span href='#' key='{key}' clazz='datetime_editable' class='label label-large arrowed-in arrowed-right qing_value_type'>日期+时间毫秒值</span></span></span><!-- /span -->";
number_type_html = "<span class='col-xs-9 pull-right qing_param_edit hide'><span class='pull-right inline'><span href='#' key='{key}' clazz='input_editable' class='label label-large label-primary arrowed-in arrowed-right qing_value_type'>数值</span> <span href='#' key='{key}' clazz='date_editable' class='label label-large arrowed-in arrowed-right qing_value_type'>日期毫秒值</span></span></span><!-- /span -->";

var input_editable_html_edit = "<div class='profile-info-row' alt='{alt}'><div class='profile-info-name'> <input key='{key}' class='qing_editable' isMulti='{isMulti}' type='hidden' id='{key}--name' alt='{alt}' value='{name}'/><span class='editable input_editable input_label'>{name}</span> </div><div class='profile-info-value'>" + del_btn_html + "<input key='{key}' class='qing_editable' type='hidden' name='{key}' alt='{alt}' isMulti='{isMulti}' value='{defaultValue}'/><span class='editable input_label {key}_label {class}'>{defaultName}</span>{valueType}</div></div>";
var sub_editable_html_edit = "<div class='profile-info-row' alt='{alt}'><div class='profile-info-name'>  <input key='{key}' class='qing_editable' type='hidden' id='{key}--name' isMulti='{isMulti}' alt='{alt}' value='{name}'/><span class='editable input_editable input_label'>{name}</span>  </div><div class='profile-info-value'>" + del_btn_html + "<div style='margin-right: 13px;'>{paramList}</div></div></div>";

function genHtml(parentKey, params, paramAlt){
    return genHtml(parentKey, params, paramAlt, false);
}

function genHtml(parentKey, params, paramAlt, isEditStatus){
    if(parentKey != ''){
        parentKey += "-";
    }

    var paramHtmls = "";
    for(var paramIdx in params){
        var param = params[paramIdx];
        var paramHtml = "";
        var isArray = Array.isArray(param);
        if(isArray){
            param = param[0];
        }
        if(param.detail != null){
            var subTableHtml = genHtml(parentKey + param.key, param.detail, paramAlt + "-0",isEditStatus);

            var subHtml = isEditStatus? sub_editable_html_edit:sub_editable_html;
            paramHtml = subHtml.replace(new RegExp("{key}","gm"), parentKey + param.key);
            paramHtml = paramHtml.replace(new RegExp("{name}","gm"), param.name);
            paramHtml = paramHtml.replace(new RegExp("{alt}","gm"), paramAlt);
            if(!isArray){
                paramHtml = paramHtml.replace(new RegExp("{editable}","gm"), "");
                paramHtml = paramHtml.replace(new RegExp("{isMulti}","gm"), "false");
            }else{
                paramHtml = paramHtml.replace(new RegExp("{isMulti}","gm"), "true");
                paramHtml = paramHtml.replace(new RegExp("{editable}","gm"), add_btn_html);
            }
            paramHtml = paramHtml.replace(new RegExp("{paramList}","gm"), subTableHtml);
        }else{
            paramHtml = initInput(parentKey, param, paramAlt, isArray, isEditStatus);
        }
        paramHtmls += paramHtml;
    }

    var tableHtml = editable_table_html;
    tableHtml = tableHtml.replace(new RegExp("{id}","gm"), parentKey);
    tableHtml = tableHtml.replace(new RegExp("{paramList}","gm"), paramHtmls);

    return tableHtml;
}

function initInput(paramKey, param, paramAlt, isArray, isEditStatus){
    var key = paramKey + param.key;
    var html = isEditStatus? input_editable_html_edit:input_editable_html;
    var paramHtml = html.replace(new RegExp("{name}","gm"), param.name);
    paramHtml = paramHtml.replace(new RegExp("{key}","gm"), key);
    paramHtml = paramHtml.replace(new RegExp("{alt}","gm"), paramAlt);
    var defaultName = "";
    var defaultValue = "";
    var valueType = "";
    if(param.defaultValue != null){
        if(param.defaultValue.name != null){
            defaultName = param.defaultValue.name;
            defaultValue =  param.defaultValue.value;
        }else{
            defaultName = param.defaultValue;
            defaultValue = param.defaultValue;
        }

        if(isNumber(defaultValue)){
            valueType = number_type_html.replace(new RegExp("{key}","gm"), key);;
        }
    }
    var classes = "";
    if(param.selectable == null){
        if(param.class != null){
            classes = param.class;
        }else{
            classes = "input_editable";
        }
    }else{
        classes = "select_editable";
    }
    paramHtml = paramHtml.replace(new RegExp("{class}","gm"), classes);
    paramHtml = paramHtml.replace(new RegExp("{valueType}","gm"), valueType);
    paramHtml = paramHtml.replace(new RegExp("{defaultName}","gm"), defaultName);
    paramHtml = paramHtml.replace(new RegExp("{defaultValue}","gm"), defaultValue);
    if(!isArray){
        paramHtml = paramHtml.replace(new RegExp("{editable}","gm"), "");
        paramHtml = paramHtml.replace(new RegExp("{isMulti}","gm"), "false");
    }else{
        paramHtml = paramHtml.replace(new RegExp("{editable}","gm"), add_btn_html);
        paramHtml = paramHtml.replace(new RegExp("{isMulti}","gm"), "true");
    }

    return paramHtml;
}

function generateJsonParam(localtion){
    var param = new Object();
    fromIdxInfo = new Object();
    destIdxInfo = new Object();

    $("" + localtion).each(function(key,value){
        var paramNameArr = value.name.split("-");
        var altArr = value.alt.split("-");

        formatParam(paramInfo, fromIdxInfo, destIdxInfo, param, "", paramNameArr, altArr, 0, value);
    });

    return param;
}

function formatParam(paramInfo, fromIdxInfo,destIdxInfo, paramObj, paramName, paramNameArr, altArr, arrIdx, value){
    var propName = paramNameArr[arrIdx];
    paramName = paramName == ""? propName:paramName + "-" + propName;
    if(paramInfo[paramName]["isMulti"]){ // 数组形式
        if(paramObj[propName] == null){
            paramObj[propName] = new Array();
        }

        var idx = altArr[arrIdx];
        if(arrIdx == paramNameArr.length -1){
            paramObj[propName].push(formatValue(paramInfo, value.name, value.value));
            return;
        }else{
            var obj;
            if(fromIdxInfo[paramName] != null && fromIdxInfo[paramName] == idx){
                idx = destIdxInfo[paramName];
            }else{
                if(idx > 0){
                    if(paramObj[propName][idx-1] == null){
                        fromIdxInfo[paramName] = idx;
                        while(idx > 0 && paramObj[propName][idx-1] == null){
                            idx --;
                        }
                        destIdxInfo[paramName] = idx;
                    }
                }
            }

            if(paramObj[propName][idx] == null){
                obj = new Object();
                paramObj[propName][idx] = obj;
            }else{
                obj = paramObj[propName][idx];
            }

            formatParam(paramInfo, fromIdxInfo,destIdxInfo, obj, paramName, paramNameArr, altArr, arrIdx + 1, value);
        }
    }else{
        if(arrIdx == paramNameArr.length -1){
            paramObj[propName] = formatValue(paramInfo, value.name, value.value);
            return;
        }else{
            if(paramObj[propName] == null){
                paramObj[propName] = new Object();
            }

            formatParam(paramInfo, fromIdxInfo,destIdxInfo, paramObj[propName], paramName, paramNameArr, altArr, arrIdx + 1, value);
        }
    }
}

function formatValue(paramInfo, paramKey, value){
    if(paramInfo[paramKey]["type"] == "[object Number]"){
        return new Number(value);
    }else{
        if(value=="true"){
            return true;
        }else if(value == "false"){
            return false;
        }
    }

    return  value;
}

function showParam(options){
    var paramData = options.paramData;
    var isEditStatus = false;
    if("isEditStatus" in options){
        isEditStatus = options.isEditStatus;
    }
    var valueChangedNotifyId;
    if("valueChangedNotifyId" in options){
        valueChangedNotifyId = options.valueChangedNotifyId;
    }

    if(paramData != null && paramData != "") {
        var params = JSON.parse(paramData);
        var paramHtmls = genHtml("", params, "0", isEditStatus);
        $("#paramListDiv").html(paramHtmls);

        initHtml("", params, valueChangedNotifyId);
        $("#paramDiv").removeClass("hide");
    }
}

function generateEditParam(localtion){
    var param = new Array();
    var allObject = new Object();

    $("" + localtion).each(function(key,value){
        if(value.name.indexOf("--name") >= 0 || value.name == ""){
            return;
        }

        var paramNameArr = value.name.split("-");
        formatEditParam(param, "", paramNameArr, 0, value, allObject);
    });

    return JSON.stringify(param);
}

function formatEditParam(paramObj, paramName, paramNameArr, arrIdx, value, allObject){
    var propName = paramNameArr[arrIdx];
    //console.log(value.name + "->" + propName);
    paramName = paramName == ""? propName:paramName + "-" + propName;

    var obj;
    if(allObject[paramName] == null){
        obj = new Object();
        obj.key = propName;
        obj.name = $("#" + paramName + "--name").val();
        if($("#" + paramName + "--name").attr("isMulti") == "true"){
            var objArr = new Array();
            objArr.push(obj);
            paramObj.push(objArr);
        }else{
            paramObj.push(obj);
        }
    }else{
        obj = allObject[paramName];
    }

    if(arrIdx == paramNameArr.length -1){
        if(paramInfo[paramName]["selectable"] != null){
            obj.selectable = paramInfo[paramName]["selectable"];
            var defaultObj = new Object();
            defaultObj.name = $("." + paramName + "_label").text();
            defaultObj.value = value.value;
            obj.defaultValue = defaultObj;
            obj.class = "select_editable";
        }else{
            var defaultObj = new Object();
            defaultObj.name = $("." + paramName + "_label").text();
            defaultObj.value = formatValue(paramInfo, value.name,value.value);
            obj.defaultValue = defaultObj;
            obj.class = $(value).attr("clazz");
        }
        return;
    }else{
        var detailArr = allObject[paramName];
        if(detailArr == null){
            detailArr = new Array();

            obj.detail = detailArr;
            allObject[paramName] =detailArr;
        }

        formatEditParam(detailArr, paramName, paramNameArr, arrIdx + 1, value, allObject);
    }
}

$(document).on("change", "#editBtnSwitch", function(){
    if($(this).val() == 0){
        $(".qing_param_edit").removeClass("hide");
        $(this).val(1);
    }else{
        $(".qing_param_edit").addClass("hide");
        $(this).val(0);
    }
    editableOff();
});

$(document).on("change", "#isLocalDebug", function(){
    if($(this).val() == 0){
        $(".isLocalDebug").removeClass("hide");
        $(this).val(1);
    }else{
        $(".isLocalDebug").addClass("hide");
        $(this).val(0);
    }
});

function editableOff(){
    $(document).find(".date_editable").each(function(key,value){
        $(value).editable('toggleDisabled');
    });

    $(document).find(".input_editable").each(function(key,value){
        $(value).editable('toggleDisabled');
    });

    $(document).find(".select_editable").each(function(key,value){
        $(value).editable('toggleDisabled');
    });
}

$(document).on("click", ".qing_value_type", function(){
    var key = $(this).attr("key");
    var clazz = $(this).attr("clazz");

    $(".qing_value_type[key='" + key + "']").removeClass("label-primary");
    $(this).addClass("label-primary");
    $("input[name='" + key + "']").attr("clazz", clazz);

    changeDefaultValue(key, clazz);
    notifyParamChanged();
});

function changeDefaultValue(key, clazz){
    var keyLabel = $("." + key + "_label");
    var newKeyLabel = keyLabel.clone();
    $(newKeyLabel).removeClass("input_editable");
    $(newKeyLabel).removeClass("date_editable");
    $(keyLabel).replaceWith(newKeyLabel);

    var defaultName = "";
    var defaultValue = 1;
    switch (clazz){
        case "input_editable":
            defaultName = 1;
            defaultValue = 1;

            $("." + key + "_label").editable({
                type: 'text',
                disabled : getEditDisableStatus("#editBtnSwitch"),
                success: function(response, newValue) {
                    $(this).prev("input").val(newValue);
                    notifyParamChanged();
                }
            });

            break;
        case "date_editable":
            defaultName = getyyyyMMdd();
            defaultValue = Date.now();
            defaultValue = defaultValue - defaultValue%100000;

            $("." + key + "_label").editable({
                type: 'date',
                format: 'yyyy-mm-dd',
                viewformat: 'yyyy-mm-dd',
                disabled : getEditDisableStatus("#editBtnSwitch"),
                datepicker: {
                    weekStart: 1
                },
                success: function(response, newValue) {
                    $(this).prev("input").val(newValue.getTime());
                    notifyParamChanged();
                }
            });
            break;
    }

    $(newKeyLabel).text(defaultName);
    $(newKeyLabel).prev("input").val(defaultValue);;
}
