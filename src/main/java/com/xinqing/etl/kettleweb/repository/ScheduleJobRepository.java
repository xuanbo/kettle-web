package com.xinqing.etl.kettleweb.repository;

import com.xinqing.etl.kettleweb.dto.ScheduleJobDTO;
import org.springframework.stereotype.Repository;

/**
 * 计划job Repository
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Repository
public interface ScheduleJobRepository extends BaseRepository<ScheduleJobDTO> {
}
