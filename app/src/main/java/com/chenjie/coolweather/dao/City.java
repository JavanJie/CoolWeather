package com.chenjie.coolweather.dao;

import org.litepal.crud.DataSupport;

/**
 * Created by chenjie on 2017/7/15.
 */

public class City extends DataSupport {

    private int id;
    private String name;
    private int code;
    private int provinceID;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }
}
