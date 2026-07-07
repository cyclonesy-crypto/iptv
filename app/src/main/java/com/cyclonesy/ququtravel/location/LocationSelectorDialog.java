package com.cyclonesy.ququtravel.location;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class LocationSelectorDialog {

    private final Context context;
    private final LocationSelection initialSelection;
    private final OnLocationSelectedListener listener;

    public LocationSelectorDialog(Context context,
                                  LocationSelection initialSelection,
                                  OnLocationSelectedListener listener) {
        this.context = context;
        this.initialSelection = initialSelection;
        this.listener = listener;
    }

    public void show() {
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        int padding = dp(20);
        container.setPadding(padding, dp(8), padding, 0);

        Spinner provinceSpinner = new Spinner(context);
        Spinner citySpinner = new Spinner(context);
        Spinner districtSpinner = new Spinner(context);

        container.addView(label("省 / 直辖市"));
        container.addView(provinceSpinner, matchWrap());
        container.addView(label("城市"));
        container.addView(citySpinner, matchWrap());
        container.addView(label("区 / 县"));
        container.addView(districtSpinner, matchWrap());

        List<String> provinces = LocationDataSource.getProvinces();
        bindSpinner(provinceSpinner, provinces);

        int provinceIndex = indexOf(provinces, initialSelection.getProvince());
        provinceSpinner.setSelection(provinceIndex);
        updateCities(citySpinner, districtSpinner, provinces.get(provinceIndex), initialSelection);

        provinceSpinner.setOnItemSelectedListener(new SimpleItemSelectedListener(position -> {
            String province = provinces.get(position);
            updateCities(citySpinner, districtSpinner, province, null);
        }));

        citySpinner.setOnItemSelectedListener(new SimpleItemSelectedListener(position -> {
            String province = (String) provinceSpinner.getSelectedItem();
            String city = (String) citySpinner.getSelectedItem();
            if (province != null && city != null) {
                bindSpinner(districtSpinner, LocationDataSource.getDistricts(province, city));
            }
        }));

        new AlertDialog.Builder(context)
                .setTitle("选择地区")
                .setView(container)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    String province = (String) provinceSpinner.getSelectedItem();
                    String city = (String) citySpinner.getSelectedItem();
                    String district = (String) districtSpinner.getSelectedItem();
                    listener.onLocationSelected(new LocationSelection(province, city, district));
                })
                .show();
    }

    private void updateCities(Spinner citySpinner,
                              Spinner districtSpinner,
                              String province,
                              LocationSelection preferred) {
        List<String> cities = LocationDataSource.getCities(province);
        bindSpinner(citySpinner, cities);
        if (cities.isEmpty()) {
            bindSpinner(districtSpinner, LocationDataSource.getDistricts(province, ""));
            return;
        }

        int cityIndex = preferred == null ? 0 : indexOf(cities, preferred.getCity());
        citySpinner.setSelection(cityIndex);
        String city = cities.get(cityIndex);

        List<String> districts = LocationDataSource.getDistricts(province, city);
        bindSpinner(districtSpinner, districts);
        if (preferred != null) {
            districtSpinner.setSelection(indexOf(districts, preferred.getDistrict()));
        }
    }

    private void bindSpinner(Spinner spinner, List<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                items
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private TextView label(String text) {
        TextView view = new TextView(context);
        view.setText(text);
        view.setTextSize(13);
        view.setPadding(0, dp(10), 0, dp(4));
        return view;
    }

    private LinearLayout.LayoutParams matchWrap() {
        return new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
    }

    private int indexOf(List<String> items, String value) {
        int index = items.indexOf(value);
        return index >= 0 ? index : 0;
    }

    private int dp(int value) {
        return (int) (value * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    private static class SimpleItemSelectedListener implements AdapterView.OnItemSelectedListener {
        interface Callback {
            void onSelected(int position);
        }

        private final Callback callback;

        SimpleItemSelectedListener(Callback callback) {
            this.callback = callback;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
            callback.onSelected(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }
}
