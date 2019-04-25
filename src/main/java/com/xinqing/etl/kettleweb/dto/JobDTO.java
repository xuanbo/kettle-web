package com.xinqing.etl.kettleweb.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * job
 *
 * @author 奔波儿灞
 * @since 1.0
 */
@Entity
@Table(name = "job")
public class JobDTO extends BaseDTO {

    @Column(unique = true)
    private String name;

    private Long groupId;

    private String description;

    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "JobDTO{" +
                "name='" + name + '\'' +
                ", groupId=" + groupId +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
