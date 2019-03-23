package com.xinqing.etl.kettleweb.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * job参数
 *
 * @author 奔波儿灞
 * @since 1.0
 */
public class JobParam implements Serializable {

    /**
     * job id
     */
    private Long id;

    /**
     * job path（kjb only）
     */
    private String path;

    /**
     * 变量，可在job中获取到
     */
    private Map<String, String> variables;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }
}
