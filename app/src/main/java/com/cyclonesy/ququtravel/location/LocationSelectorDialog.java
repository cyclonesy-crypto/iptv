package com.cyclonesy.ququtravel.location;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LocationSelectorDialog {

    private final Context context;
    private final LocationSelection initialSelection;
    private final OnLocationSelectedListener listener;

    private final List<String> provinces = new ArrayList<>();
    private List<String> cities = new ArrayList<>();
    private List<String> districts = new ArrayList<>();

    private NumberPicker provincePicker;
    private NumberPicker cityPicker;
    private NumberPicker districtPicker;

    public LocationSelectorDialog(Context context,
                                  LocationSelection initialSelection,
                                  OnLocationSelectedListener listener) {
        this.context = context;
        this.initialSelection = initialSelection;
        this.listener = listener;
    }

    public void show() {
        LocationDataSource.initialize(context);

        provinces.clear();
        provinces.addAll(LocationDataSource.getProvinces());

        LinearLayout root = new LinearLayout(context);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(16), dp(8), dp(16), 0);

        root.addView(createHeader());
        root.addView(createWheelRow(), new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(220)
        ));

        initPickers();
        bindListeners();

        new AlertDialog.Builder(context)
                .setTitle("选择地区")
                .setView(root)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> listener.onLocationSelected(
                        new LocationSelection(
                                provinces.get(provincePicker.getValue()),
                                cities.get(cityPicker.getValue()),
                                districts.get(districtPicker.getValue())
                        )
                ))
                .show();
    }

    private LinearLayout createHeader() {
        LinearLayout header = new LinearLayout(context);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setGravity(Gravity.CENTER);
        header.addView(headerText("省 / 直辖市"), weightParams());
        header.addView(headerText("城市"), weightParams());
        header.addView(headerText("区 / 县"), weightParams());
        return header;
    }

    private LinearLayout createWheelRow() {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER);

        provincePicker = createPicker();
        cityPicker = createPicker();
        districtPicker = createPicker();

        row.addView(provincePicker, weightMatchParams());
        row.addView(cityPicker, weightMatchParams());
        row.addView(districtPicker, weightMatchParams());
        return row;
    }

    private void initPickers() {
        int provinceIndex = safeIndexOf(provinces, initialSelection.getProvince());
        bindPicker(provincePicker, provinces, provinceIndex);
        updateCities(provinceIndex, initialSelection.getCity(), initialSelection.getDistrict());
    }

    private void bindListeners() {
        provincePicker.setOnValueChangedListener((picker, oldVal, newVal) ->
                updateCities(newVal, null, null));

        cityPicker.setOnValueChangedListener((picker, oldVal, newVal) ->
                updateDistricts(newVal, null));
    }

    private void updateCities(int provinceIndex, String preferredCity, String preferredDistrict) {
        String province = provinces.get(provinceIndex);
        cities = LocationDataSource.getCities(province);

        int cityIndex = safeIndexOf(cities, preferredCity);
        bindPicker(cityPicker, cities, cityIndex);
        updateDistricts(cityIndex, preferredDistrict);
    }

    private void updateDistricts(int cityIndex, String preferredDistrict) {
        String province = provinces.get(provincePicker.getValue());
        String city = cities.get(cityIndex);
        districts = LocationDataSource.getDistricts(province, city);

        int districtIndex = safeIndexOf(districts, preferredDistrict);
        bindPicker(districtPicker, districts, districtIndex);
    }

    private NumberPicker createPicker() {
        NumberPicker picker = new NumberPicker(context);
        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setWrapSelectorWheel(true);
        return picker;
    }

    private void bindPicker(NumberPicker picker, List<String> values, int selectedIndex) {
        picker.setDisplayedValues(null);
        picker.setMinValue(0);
        picker.setMaxValue(Math.max(values.size() - 1, 0));
        picker.setDisplayedValues(values.toArray(new String[0]));
        picker.setValue(Math.min(selectedIndex, Math.max(values.size() - 1, 0)));
        picker.setWrapSelectorWheel(values.size() > 2);
    }

    private TextView headerText(String text) {
        TextView view = new TextView(context);
        view.setText(text);
        view.setTextSize(13);
        view.setTextColor(Color.parseColor("#6B7F86"));
        view.setGravity(Gravity.CENTER);
        view.setPadding(0, dp(8), 0, dp(4));
        return view;
    }

    private LinearLayout.LayoutParams weightParams() {
        return new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
    }

    private LinearLayout.LayoutParams weightMatchParams() {
        return new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
    }

    private int safeIndexOf(List<String> values, String target) {
        if (values == null || values.isEmpty()) {
            return 0;
        }
        int index = target == null ? -1 : values.indexOf(target);
        return index >= 0 ? index : 0;
    }

    private int dp(int value) {
        return (int) (value * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
