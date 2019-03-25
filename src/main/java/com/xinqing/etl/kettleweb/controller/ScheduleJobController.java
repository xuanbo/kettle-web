package com.xinqing.etl.kettleweb.controller;

import com.xinqing.etl.kettleweb.dto.ScheduleJobDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 计划job Controller
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Validated
@RestController
@RequestMapping("/api/scheduleJob")
public class ScheduleJobController extends BaseController<ScheduleJobDTO> {
}
