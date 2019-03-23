package com.xinqing.etl.kettleweb.controller;

import com.xinqing.etl.kettleweb.domain.Api;
import com.xinqing.etl.kettleweb.dto.BaseDTO;
import com.xinqing.etl.kettleweb.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 基础Controller
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public abstract class BaseController<T extends BaseDTO> {

    @Autowired
    private BaseService<T> service;

    @GetMapping
    public Api<Page<T>> page(T entity, @PageableDefault Pageable pageable) {
        return Api.ok(service.page(entity, pageable));
    }

    @GetMapping("/{id}")
    public Api<T> find(@PathVariable Long id) {
        return Api.ok(service.find(id));
    }

    @PostMapping
    public Api<T> add(@RequestBody T entity) {
        service.save(entity);
        return Api.ok(null);
    }

    @PutMapping
    public Api<T> modify(@RequestBody T entity) {
        service.save(entity);
        return Api.ok(null);
    }

    @DeleteMapping("/{id}")
    public Api<T> remove(@PathVariable Long id) {
        service.delete(id);
        return Api.ok(null);
    }
}
