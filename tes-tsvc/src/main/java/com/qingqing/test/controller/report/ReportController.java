package com.qingqing.test.controller.report;

import com.qingqing.test.bean.base.SimpleResponse;
import com.qingqing.test.manager.ScheduleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@Controller
@RequestMapping("/v1/report")
public class ReportController {

    @Autowired
    private ScheduleManager scheduleManager;

    @RequestMapping("daily")
    @ResponseBody
    public SimpleResponse dailyReport() {
        scheduleManager.reportTotalUse();

        return new SimpleResponse(com.qingqing.test.bean.base.BaseResponse.SUCC_RESP);
    }
}
