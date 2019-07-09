package com.keke.kekeweather.util;

import android.text.TextUtils;

import com.keke.kekeweather.db.City;
import com.keke.kekeweather.db.County;
import com.keke.kekeweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.util.List;

public class Utility {
    //解析和处理服务器返回的省级数据


    public static boolean handleProvinceResponse(String response){

        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i=0 ;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
//                    List<Province> provinces= DataSupport.where("id=?", String.valueOf(jsonObject.getInt("id"))).find(Province.class);
//                    if (provinces.size()>0){
//                        continue;
//                    }
                    Province province=new Province();
                    province.setProvinceCode(jsonObject.getInt("id"));
                    province.setProvinceName(jsonObject.getString("name"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    //解析和处理服务器返回的市级数据


    public static boolean handleCityResponse(String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i=0 ;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
//                    List<City> cityList= DataSupport.where("id=?", String.valueOf(jsonObject.getInt("id"))).find(City.class);
//                    if (cityList.size()>0){
//                        continue;
//                    }
                    City city=new City();
                    city.setCityCode(jsonObject.getInt("id"));
                    city.setCityName(jsonObject.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //解析和处理服务器返回的县级数据


    public static boolean handleCountyResponse(String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i=0 ;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
//                    List<County> counties= DataSupport.where("id=?", String.valueOf(jsonObject.getInt("id"))).find(County.class);
//                    if (counties.size()>0){
//                        continue;
//                    }
                    County county=new County();
                    county.setCountyName(jsonObject.getString("name"));
                    county.setWeatherId(jsonObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


}
