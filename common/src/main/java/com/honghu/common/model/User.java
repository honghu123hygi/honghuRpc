package com.honghu.common.model;

import java.io.Serializable;

/**
 * @author hzd
 */
public class User implements Serializable {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
