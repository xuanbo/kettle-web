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

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 分页数据
     */
    Page<T> page(Pageable pageable);

    /**
     * 字段精确匹配，分页查询
     *
     * @param entity   实体
     * @param pageable 分页参数
     * @return 分页数据
     */
    Page<T> page(T entity, Pageable pageable);

    /**
     * 新增或更新
     *
     * @param entity 实体
     */
    void save(T entity);

    /**
     * 查询
     *
     * @param id 主键
     * @return 实体
     */
    T find(Long id);

    /**
     * 删除
     *
     * @param id 主键
     */
    void delete(Long id);

}
