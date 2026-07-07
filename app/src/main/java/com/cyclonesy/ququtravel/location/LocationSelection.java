package com.cyclonesy.ququtravel.location;

public class LocationSelection {

    private final String province;
    private final String city;
    private final String district;

    public LocationSelection(String province, String city, String district) {
        this.province = province;
        this.city = city;
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getDisplayName() {
        if (district == null || district.isEmpty()) {
            return city;
        }
        return city + " · " + district;
    }
}
