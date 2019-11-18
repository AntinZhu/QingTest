var tableTemplate = '<table class="table table-striped table-bordered table-hover"><thead><tr>{thead}</tr></thead><tbody>{body}</tbody></table>';
var taskTrTemplate = '<tr><td>{taskId}</td><td>{taskType}</td><td>{refId}</td><td>{taskStatus}</td><td>{startEffectTime}</td><td>{endEffectTime}</td><td>{createTime}</td><td id="task-log-{taskId}" class="center"></td><td class="center"><div class="btn-group"><button class="btn btn-xs btn-warning task-expired-call"><input type="hidden" value="{taskId}" /><i class="icon-fire bigger-120"></i></button></div></td></tr>';
var guidLog = "http://172.22.12.14:5601/app/logtrail#/?q=guid:%20%22{guid}%22&h=All&t=Now&i=rsyslog-app*&_g=()";

var taskCache;
function showUserTask(resu){
    var result = JSON.parse(resu.data);
    var tableHtml = tableTemplate;
    var  thead = "<th>任务ID</th><th>任务类型</th><th>关联ID</th><th>任务状态</th><th>任务开始时间</th><th>任务结束时间</th><th>任务创建时间</th><th>回调日志</th><th>过期回调</th>";
    var body = ""
    taskCache = result.task_items;
    if(result.task_items != null){
        for(var taskIdx in result.task_items){
            var task = result.task_items[taskIdx];
            var tr = taskTrTemplate;
            tr = tr.replace(new RegExp("{taskId}", "gm"), task.task_id);
            tr = tr.replace(new RegExp("{taskType}", "gm"), task.task_type);
            tr = tr.replace(new RegExp("{refId}", "gm"), task.ref_id);
            tr = tr.replace(new RegExp("{taskStatus}", "gm"), toTaskStatus(task.task_status));
            tr = tr.replace(new RegExp("{startEffectTime}", "gm"), task.effect_start_time == null? "":formatDate(task.effect_start_time));
            tr = tr.replace(new RegExp("{endEffectTime}", "gm"), task.effect_end_time == null? "":formatDate(task.effect_end_time));
            tr = tr.replace(new RegExp("{createTime}", "gm"), formatDate(task.create_time));

            body = body + tr;
        }
    }

    tableHtml = tableHtml.replace(new RegExp("{thead}", "gm"), thead);
    tableHtml = tableHtml.replace(new RegExp("{body}", "gm"), body);
    $("#dataResultDiv").html(tableHtml);
}

function toTaskStatus(status){
    if("created_task_status" == status){
        return "进行中";
    }else if("done_task_status" == status){
        return "已完成";
    }else if("ignored_task_status" == status){
        return "已忽略";
    }else if("expired_task_status" == status){
        return "已过期";
    }else{
        return "未知状态";
    }
}

$(document).on("click", ".task-expired-call", function(){
    var taskId = $(this).find("input").val();
    for(var taskIdx in taskCache) {
        var task = taskCache[taskIdx];
        if(task.task_id == taskId){
            var guid = generateGuid();
            var env = $("#env").val();
            piSingleRequestV2($("#qing-base").val(), env, task.expire_callback_url + "&guid=" + guid, taskExpired);

            var logUrl = guidLog;
            logUrl = logUrl.replace("{guid}", guid);
            $("#task-log-" + taskId).html('<a target="_blank" href="' + logUrl + '">查看日志</a>');
            break;
        }
    }
});

function taskExpired(){
    $.gritter.add({
        title : "过期回调模拟",
        text : "调用成功",
        class_name : 'gritter-info gritter-center'
    });
}