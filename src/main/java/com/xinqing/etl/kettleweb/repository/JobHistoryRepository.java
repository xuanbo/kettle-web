package com.xinqing.etl.kettleweb.repository;

import com.xinqing.etl.kettleweb.dto.JobHistoryDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * job历史Repository
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Repository
public interface JobHistoryRepository extends BaseRepository<JobHistoryDTO> {

    @Query(value = "SELECT status, count(1) as count FROM job_history GROUP BY status", nativeQuery = true)
    List<Object[]> findGroupByStatus();

}
