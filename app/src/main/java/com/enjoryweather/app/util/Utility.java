package com.enjoryweather.app.util;

import android.text.TextUtils;

import com.enjoryweather.app.db.EnjoryWeatherDB;
import com.enjoryweather.app.model.City;
import com.enjoryweather.app.model.County;
import com.enjoryweather.app.model.Province;

/**
 * Created by KD on 2016/5/8.
 * 解析和处理服务器返回数据
 */
public class Utility {
    //解析和处理服务器返回的省级数据
    public synchronized static boolean handleProvincesResponse(EnjoryWeatherDB enjoryWeatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将解析出来的数据存到Province表
                    enjoryWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    //解析和处理服务器返回的市级数据
    public synchronized static boolean handleCitiesResponse(EnjoryWeatherDB enjoryWeatherDB, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据存到City表
                    enjoryWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    //解析和处理服务器返回的县级数据
    public synchronized static boolean handleCountiesResponse(EnjoryWeatherDB enjoryWeatherDB, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allcounties = response.split(",");
            if (allcounties != null && allcounties.length > 0) {
                for (String c : allcounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    //将解析出来的数据存到County表
                    enjoryWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
