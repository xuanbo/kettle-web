package com.xinqing.etl.kettleweb.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * job组
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Entity
@Table(name = "job_group")
public class JobGroupDTO extends BaseDTO {

    @Column(unique = true)
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
