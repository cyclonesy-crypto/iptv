package com.cyclonesy.ququtravel.location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class LocationDataSource {
    private static final Map<String, Map<String, List<String>>> DATA = new LinkedHashMap<>();

    static {
        add("上海市", "上海市", "黄浦区", "徐汇区", "浦东新区", "闵行区");
        add("北京市", "北京市", "东城区", "朝阳区", "海淀区", "通州区");
        add("广东省", "广州市", "越秀区", "天河区", "番禺区", "黄埔区");
        add("广东省", "深圳市", "福田区", "南山区", "宝安区", "龙岗区");
        add("四川省", "成都市", "锦江区", "青羊区", "武侯区", "高新区");
        add("浙江省", "杭州市", "上城区", "西湖区", "滨江区", "余杭区");
        add("江苏省", "南京市", "玄武区", "秦淮区", "鼓楼区", "江宁区");
        add("重庆市", "重庆市", "渝中区", "江北区", "南岸区", "渝北区");
        add("陕西省", "西安市", "新城区", "碑林区", "雁塔区", "长安区");
        add("福建省", "厦门市", "思明区", "湖里区", "集美区", "海沧区");
    }

    private LocationDataSource() {}

    private static void add(String province, String city, String... districts) {
        Map<String, List<String>> cities = DATA.get(province);
        if (cities == null) {
            cities = new LinkedHashMap<>();
            DATA.put(province, cities);
        }
        cities.put(city, Arrays.asList(districts));
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
        if (cities == null || cities.get(city) == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(cities.get(city));
    }

    public static LocationSelection getDefaultSelection() {
        return new LocationSelection("上海市", "上海市", "浦东新区");
    }
}
