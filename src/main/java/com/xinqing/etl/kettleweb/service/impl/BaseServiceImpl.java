package com.xinqing.etl.kettleweb.service.impl;

import com.xinqing.etl.kettleweb.dto.BaseDTO;
import com.xinqing.etl.kettleweb.repository.BaseRepository;
import com.xinqing.etl.kettleweb.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 基础服务实现
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Service
@Transactional
public abstract class BaseServiceImpl<T extends BaseDTO> implements BaseService<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected BaseRepository<T> repository;

    @Override
    public Page<T> page(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<T> page(T entity, Pageable pageable) {
        return repository.findAll(Example.of(entity), pageable);
    }

    @Override
    public void save(T entity) {
        repository.save(entity);
    }

    @Override
    public T find(Long id) {
        T entity = null;
        Optional<T> optional = repository.findById(id);
        if (optional.isPresent()) {
            entity = optional.get();
        }
        return entity;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
