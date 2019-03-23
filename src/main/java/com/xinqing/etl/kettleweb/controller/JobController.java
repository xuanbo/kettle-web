package com.xinqing.etl.kettleweb.controller;

import com.xinqing.etl.kettleweb.domain.Api;
import com.xinqing.etl.kettleweb.dto.JobDTO;
import com.xinqing.etl.kettleweb.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * job Controller
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Validated
@RestController
@RequestMapping("/api/job")
public class JobController extends BaseController<JobDTO> {

    @Autowired
    private JobService jobService;

    @PostMapping("/{id}/exec")
    public Api<Void> exec(@PathVariable Long id) {
        jobService.exec(id);
        return Api.ok(null);
    }

}
