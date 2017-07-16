package com.chenjie.coolweather.utils;

import android.text.TextUtils;

import com.chenjie.coolweather.dao.City;
import com.chenjie.coolweather.dao.Country;
import com.chenjie.coolweather.dao.Province;
import com.chenjie.coolweather.model.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by chenjie on 2017/7/15.
 */

public class JSONUtil {

    public static boolean handleProvinceResponse(String response) {

        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);

                    Province province = new Province();
                    province.setName(provinceObject.getString("name"));
                    province.setCode(provinceObject.getInt("id"));

                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean handleCityResponse(String response, int provinceID) {

        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject cityObject = allProvinces.getJSONObject(i);

                    City city = new City();
                    city.setName(cityObject.getString("name"));
                    city.setCode(cityObject.getInt("id"));
                    city.setProvinceID(provinceID);

                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean handleCountryResponse(String response, int cityID) {

        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject countryObject = allProvinces.getJSONObject(i);

                    Country country = new Country();
                    country.setName(countryObject.getString("name"));
                    country.setWeatherID(countryObject.getString("weather_id"));
                    country.setCityID(cityID);

                    country.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
