package com.xinqing.etl.kettleweb.service.impl;

import com.xinqing.etl.kettleweb.dto.JobGroupDTO;
import com.xinqing.etl.kettleweb.service.JobGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * job组服务实现
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JobGroupServiceImpl extends BaseServiceImpl<JobGroupDTO> implements JobGroupService {
}
