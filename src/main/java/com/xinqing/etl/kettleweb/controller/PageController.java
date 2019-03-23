package com.xinqing.etl.kettleweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Controller
public class PageController {

    @GetMapping({"/", "index", "index.html"})
    public String index() {
        return "index";
    }

    @GetMapping("/home.html")
    public String home() {
        return "home";
    }

    @GetMapping("/job-module/jobGroup.html")
    public String jobGroup() {
        return "job-module/jobGroup";
    }

    @GetMapping("/job-module/job.html")
    public String job() {
        return "job-module/job";
    }

    @GetMapping("/job-module/jobVariable.html")
    public String jobVariable() {
        return "job-module/jobVariable";
    }

    @GetMapping("/manage-module/jobHistory.html")
    public String jobHistory() {
        return "manage-module/jobHistory";
    }

}
