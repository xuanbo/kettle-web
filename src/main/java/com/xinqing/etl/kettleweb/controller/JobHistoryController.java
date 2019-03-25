package com.xinqing.etl.kettleweb.controller;

import com.xinqing.etl.kettleweb.domain.Api;
import com.xinqing.etl.kettleweb.domain.HistoryDashboard;
import com.xinqing.etl.kettleweb.dto.JobHistoryDTO;
import com.xinqing.etl.kettleweb.service.JobHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * job历史Controller
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Validated
@RestController
@RequestMapping("/api/jobHistory")
public class JobHistoryController extends BaseController<JobHistoryDTO> {

    @Autowired
    private JobHistoryService jobHistoryService;

    @GetMapping("/findGroupByStatus")
    public Api<List<HistoryDashboard>> findGroupByStatus() {
        return Api.ok(jobHistoryService.findGroupByStatus());
    }

}
