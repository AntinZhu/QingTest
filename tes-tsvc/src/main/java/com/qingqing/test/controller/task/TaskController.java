package com.qingqing.test.controller.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhujianxing on 2018/2/4.
 */
@Controller
@RequestMapping("/v1/task")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @RequestMapping("main_page")
    public String add(@RequestParam(value = "paramId", defaultValue = "-1") Long paramId,
                      @RequestParam(value = "env", defaultValue = "dev") String env,
                      @RequestParam(value = "inv", defaultValue = "0") int inv,
                      Model model){
        model.addAttribute("interfaceId", 372);
        model.addAttribute("paramExampleId", paramId);
        model.addAttribute("env", env);
        model.addAttribute("inv", inv);
        model.addAttribute("dataResultFunction", "showUserTask");

        return "interface/jsonformat";
    }
}
