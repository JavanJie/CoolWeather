package com.chenjie.coolweather.dao;

import org.litepal.crud.DataSupport;

/**
 * Created by chenjie on 2017/7/15.
 */

public class Country extends DataSupport {

    private int id;
    private String name;
    private int cityID;
    private String weatherID;

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

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public String getWeatherID() {
        return weatherID;
    }

    public void setWeatherID(String weatherID) {
        this.weatherID = weatherID;
    }
}
