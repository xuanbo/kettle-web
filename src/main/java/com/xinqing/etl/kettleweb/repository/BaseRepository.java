package com.xinqing.etl.kettleweb.repository;

import com.xinqing.etl.kettleweb.dto.BaseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 基础
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseDTO> extends JpaRepository<T, Long> {
}
