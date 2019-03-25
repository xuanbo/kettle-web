package com.xinqing.etl.kettleweb.service.impl;

import com.xinqing.etl.kettleweb.dto.ScheduleJobDTO;
import com.xinqing.etl.kettleweb.scheduler.QuartzJobException;
import com.xinqing.etl.kettleweb.scheduler.QuartzJobService;
import com.xinqing.etl.kettleweb.service.ScheduleJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 计划job服务实现
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Service
@Transactional
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJobDTO> implements ScheduleJobService {

    @Autowired
    private QuartzJobService quartzJobService;

    @Override
    public void save(ScheduleJobDTO entity) {
        boolean save = true;
        if (entity.getId() != null) {
            save = false;
        }
        super.save(entity);
        try {
            if (save) {
                quartzJobService.addJob(entity);
            } else {
                quartzJobService.updateJob(entity);
            }
        } catch (Exception e) {
            throw new QuartzJobException(e);
        }
    }

    @Override
    public void delete(Long id) {
        ScheduleJobDTO scheduleJobDTO = find(id);
        if (scheduleJobDTO == null) {
            logger.warn("schedule job not exist, id: {}", id);
            return;
        }
        super.delete(id);
        try {
            quartzJobService.deleteJob(scheduleJobDTO);
        } catch (SchedulerException e) {
            throw new QuartzJobException(e);
        }
    }
}
