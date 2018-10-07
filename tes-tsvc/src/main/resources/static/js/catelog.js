function showParentCatelog(catelogUrl, cateDivId, selectValueId, defaultSelectId){
    var othData ={
        cateDivId :cateDivId,
        selectValueId :selectValueId,
        defaultSelectId : defaultSelectId
    };
    commonAjaxRequest(catelogUrl, null, handleCateLogParam, false, "获取分类信息失败:", "", othData);
}

function handleCateLogParam(resu, othData){
    var cateTreeData = generateCateLogParam(resu.resultList, othData.defaultSelectId);

    $('#' + othData.cateDivId).ace_tree({
        dataSource: new DataSourceTree({data: cateTreeData}),
        multiSelect:false,
        cacheItems: false,
        loadingHTML:'<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
        'open-icon' : 'icon-minus',
        'close-icon' : 'icon-plus',
        'selectable' : true,
        'selected-icon' : 'icon-ok',
        'unselected-icon' : 'icon-remove'
    });

    $('#' + othData.cateDivId).on('updated', function(e, result) {

        //console.log(result.info[0].id);
        //result.info  >> an array containing selected items
        //result.item
        //result.eventType >> (selected or unselected)
    }).on('selected', function(e,result) {
        $("#" + othData.selectValueId).val(result.info[0].cate_id);
    }).on('unselected', function(e) {
        $("#" + othData.selectValueId).val("");
    }).on('opened', function(e, result) {
        //打开文件夹的方法
    }).on('closed', function(e) {
        //关闭文件夹的方法
    });
}

function generateCateLogParam(resultList,defaultSelectId){
    var catelogList;
    for(var idx in resultList){
        var catelogBean = resultList[idx];

        var catelog = catelogBean.catelog;
        if(catelog.refType == "cate") {
            if(catelogList == null){
                catelogList= new Object();
            }
            var cateItemObj = new Object();
            cateItemObj.name = catelog.catelogName;
            cateItemObj.type = "item";
            cateItemObj.cate_id = catelog.id;
            catelogList[catelog.catelogName] = cateItemObj;
            if(defaultSelectId != null && catelog.id == defaultSelectId){
                cateItemObj["additionalParameters"] = new Object();
                cateItemObj["additionalParameters"]["item-selected"] = true;
            }

            var subCateObj = generateCateLogParam(catelogBean.subCategoryList, defaultSelectId);
            if(subCateObj != null){
                var cateObj = new Object();
                cateObj.name = catelog.catelogName;
                cateObj.type = "folder";
                cateObj.cate_id = catelog.id;
                var addiObj = new Object();
                cateObj.additionalParameters = addiObj;
                addiObj.children = subCateObj;

                catelogList[catelog.catelogName + "-1"] =cateObj;
            }
        }
    }

    return catelogList;
}

var item_html = '<li class="{index}"\><a href="{linkUrl}" class="qing_catelog"><i class=""></i>{catelogName}</a></li>';
var catelog_html = '<li class="{index}"><button type="button"style="display:inline-block;width: 39px;background-color: #fff;"  class="btn-sm qing_catelog_del pull-right hide"><i class="red icon-trash"></i></button><a href="#" style="width: 100%;display:inline-block" class="dropdown-toggle qing_catelog"><i class="{icon}"></i><span class="menu-text">{catelogName}</span><b class="arrow icon-angle-down"></b></a><ul class="submenu">{items}</ul></li>';

function catelogList(catelogList, baseUrl){11
    var catelogHtml = "";
    for(var idx in catelogList){
        var catelog = catelogList[idx];

        var thisHtml;
        if(catelog.catelog.refType == "cate"){
            thisHtml = catelogWithSub(catelog, baseUrl);
        }else{
            thisHtml = catelogItem(catelog, baseUrl);
        }
        catelogHtml += thisHtml;
    }

    return catelogHtml;
}

function catelogWithSub(catelog, baseUrl){
    var thisHtml = catelog_html;
    thisHtml = thisHtml.replace("{catelogName}", catelog.catelog.catelogName);
    thisHtml = thisHtml.replace("{index}", catelog.catelogIndex);
    thisHtml = thisHtml.replace("{icon}", randomIcon());
    thisHtml = thisHtml.replace("{items}", catelogList(catelog.subCategoryList, baseUrl));

    return thisHtml;
}

function catelogItem(catelog, baseUrl){
    var thisHtml = item_html;
    thisHtml = thisHtml.replace("{catelogName}", catelog.catelog.catelogName);
    thisHtml = thisHtml.replace("{index}", catelog.catelogIndex);
    var linkUrl;
    switch (catelog.catelog.refType){
        case "url":
            linkUrl = baseUrl + catelog.catelog.refValue;
            break;
        case "inter":
            linkUrl =  baseUrl + "/v1/test/json_format?id=" + catelog.catelog.refValue;
            break;
        default:
            linkUrl = "#";
    }
    if(linkUrl != null && "#" != linkUrl){
        if(linkUrl.indexOf("?") >= 0){
            linkUrl += "&catelogIndex=" + catelog.catelogIndex;
        }else{
            linkUrl += "?catelogIndex=" + catelog.catelogIndex;
        }
    }
    thisHtml = thisHtml.replace("{linkUrl}", linkUrl);

    return thisHtml;
}