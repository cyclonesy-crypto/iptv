package com.cyclonesy.ququtravel.location;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class LocationDataSource {

    private static final String ASSET_NAME = "china_regions.json";
    private static final Map<String, Map<String, List<String>>> DATA = new LinkedHashMap<>();
    private static boolean initialized;

    private LocationDataSource() {
    }

    public static synchronized void initialize(Context context) {
        if (initialized) {
            return;
        }

        DATA.clear();
        try {
            String json = readAsset(context.getApplicationContext(), ASSET_NAME);
            parseRegions(new JSONArray(json));
            initialized = !DATA.isEmpty();
        } catch (Exception e) {
            initialized = false;
            DATA.clear();
            addFallbackData();
        }
    }

    public static List<String> getProvinces() {
        return new ArrayList<>(DATA.keySet());
    }

    public static List<String> getCities(String province) {
        Map<String, List<String>> cities = DATA.get(province);
        return cities == null ? new ArrayList<>() : new ArrayList<>(cities.keySet());
    }

    public static List<String> getDistricts(String province, String city) {
        Map<String, List<String>> cities = DATA.get(province);
        if (cities == null) {
            return new ArrayList<>();
        }
        List<String> districts = cities.get(city);
        return districts == null ? new ArrayList<>() : new ArrayList<>(districts);
    }

    public static LocationSelection getDefaultSelection() {
        return new LocationSelection("上海市", "上海市", "浦东新区");
    }

    private static void parseRegions(JSONArray provinces) throws Exception {
        for (int i = 0; i < provinces.length(); i++) {
            JSONObject provinceObject = provinces.getJSONObject(i);
            String provinceName = provinceObject.optString("name");
            JSONArray cityArray = provinceObject.optJSONArray("children");
            if (provinceName.isEmpty() || cityArray == null) {
                continue;
            }

            Map<String, List<String>> cityMap = new LinkedHashMap<>();
            for (int j = 0; j < cityArray.length(); j++) {
                JSONObject cityObject = cityArray.getJSONObject(j);
                String rawCityName = cityObject.optString("name");
                JSONArray districtArray = cityObject.optJSONArray("children");
                if (rawCityName.isEmpty() || districtArray == null) {
                    continue;
                }

                String cityName = normalizeCityName(provinceName, rawCityName);
                List<String> districts = new ArrayList<>();
                for (int k = 0; k < districtArray.length(); k++) {
                    String districtName = districtArray.getJSONObject(k).optString("name");
                    if (!districtName.isEmpty()) {
                        districts.add(districtName);
                    }
                }

                if (!districts.isEmpty()) {
                    List<String> existing = cityMap.get(cityName);
                    if (existing == null) {
                        cityMap.put(cityName, districts);
                    } else {
                        existing.addAll(districts);
                    }
                }
            }

            if (!cityMap.isEmpty()) {
                DATA.put(provinceName, cityMap);
            }
        }
    }

    private static String normalizeCityName(String provinceName, String cityName) {
        if ("市辖区".equals(cityName) || "县".equals(cityName)) {
            return provinceName;
        }
        return cityName;
    }

    private static String readAsset(Context context, String fileName) throws Exception {
        StringBuilder builder = new StringBuilder();
        try (InputStream inputStream = context.getAssets().open(fileName);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }
        return builder.toString();
    }

    private static void addFallbackData() {
        Map<String, List<String>> cityMap = new LinkedHashMap<>();
        List<String> districts = new ArrayList<>();
        districts.add("浦东新区");
        districts.add("黄浦区");
        districts.add("徐汇区");
        cityMap.put("上海市", districts);
        DATA.put("上海市", cityMap);
    }
}
