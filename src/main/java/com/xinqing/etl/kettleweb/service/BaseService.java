package com.xinqing.etl.kettleweb.service;

import com.xinqing.etl.kettleweb.dto.BaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 基础服务
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public interface BaseService<T extends BaseDTO> {

    Page<T> page(Pageable pageable);

    Page<T> page(T entity, Pageable pageable);

    void save(T entity);

    T find(Long id);

    void delete(Long id);

}
