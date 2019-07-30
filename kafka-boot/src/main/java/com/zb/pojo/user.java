package com.zb.pojo;

import io.swagger.annotations.ApiModelProperty;

public class user {
    @ApiModelProperty(value = "用户ID")
    private int id;
    @ApiModelProperty(value = "用户姓名")
    private String name;

    public user() {

    }

    public user(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
