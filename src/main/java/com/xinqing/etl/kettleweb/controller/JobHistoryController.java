package com.xinqing.etl.kettleweb.controller;

import com.xinqing.etl.kettleweb.dto.JobHistoryDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
