package com.xinqing.etl.kettleweb.repository.impl;

import com.xinqing.etl.kettleweb.dto.BaseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * 新增:字段为null则不更新
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class SimpleJpaRepositoryImpl<T extends BaseDTO, ID> extends SimpleJpaRepository<T, ID> {

    private final JpaEntityInformation<T, ID> entityInformation;
    private final EntityManager em;

    @Autowired
    public SimpleJpaRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    /**
     * 通用save方法 ：新增/选择性更新
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public <S extends T> S save(S entity) {
        // 获取ID
        ID entityId = entityInformation.getId(entity);
        if (entityId == null) {
            // 若ID为空，为新增的数据
            // 新增
            fireInsertDate(entity);
            em.persist(entity);
            return entity;
        } else {
            // 若ID非空，则需要更新
            Optional<T> optional = findById(entityId);
            if (optional.isPresent()) {
                T target = optional.get();
                // 获取空属性并处理成null
                String[] nullProperties = getNullProperties(entity);
                // 更新非空属性
                BeanUtils.copyProperties(entity, target, nullProperties);
                fireUpdateDate(target);
                em.merge(target);
                // 复制回去。。
                BeanUtils.copyProperties(target, entity);
                return entity;
            } else {
                // 虽然有ID，但数据库不存在
                // 新增
                fireInsertDate(entity);
                em.persist(entity);
                return entity;
            }
        }
    }

    private <S extends T> void fireInsertDate(S entity) {
        Date now = new Date();
        if (entity.getCreateAt() == null) {
            entity.setCreateAt(now);
        }
        if (entity.getUpdateAt() == null) {
            entity.setUpdateAt(now);
        }
    }

    private <S extends T> void fireUpdateDate(S entity) {
        entity.setUpdateAt(new Date());
    }

    /**
     * 获取对象的空属性
     */
    private static String[] getNullProperties(Object src) {
        // 获取Bean
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        // 获取Bean的属性描述
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        // 获取Bean的空属性
        Set<String> properties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : pds) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = srcBean.getPropertyValue(propertyName);
            if (StringUtils.isEmpty(propertyValue)) {
                srcBean.setPropertyValue(propertyName, null);
                properties.add(propertyName);
            }
        }
        String[] nullProperties = new String[properties.size()];
        properties.toArray(nullProperties);
        return nullProperties;
    }
}
