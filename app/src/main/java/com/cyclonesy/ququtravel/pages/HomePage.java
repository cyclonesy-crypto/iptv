package com.cyclonesy.ququtravel.pages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyclonesy.ququtravel.TravelDetailActivity;
import com.cyclonesy.ququtravel.UiKit;
import com.cyclonesy.ququtravel.location.LocationDataSource;
import com.cyclonesy.ququtravel.location.LocationSelection;
import com.cyclonesy.ququtravel.location.LocationSelectorDialog;

public class HomePage extends LinearLayout {

    private static final String PREFS_NAME = "travel_prefs";
    private static final String KEY_PROVINCE = "selected_province";
    private static final String KEY_CITY = "selected_city";
    private static final String KEY_DISTRICT = "selected_district";

    private final Context context;
    private LocationSelection selectedLocation;

    public HomePage(Context context) {
        super(context);
        this.context = context;
        this.selectedLocation = loadLocation();
        setOrientation(VERTICAL);
        renderPage();
    }

    private void renderPage() {
        removeAllViews();
        addView(createContent());
    }

    private View createContent() {
        LinearLayout page = UiKit.pageContainer(context);
        page.setPadding(0, UiKit.dp(context, 10), 0, UiKit.dp(context, 22));

        page.addView(createTopBar(), UiKit.matchWrapMargin(context, 16, 8, 16, 10));
        page.addView(createHeroCard(), UiKit.matchWrapMargin(context, 16, 0, 16, 12));
        page.addView(createSearchBox(), UiKit.matchWrapMargin(context, 16, 0, 16, 16));

        page.addView(UiKit.sectionTitle(context, cityName() + "旅行灵感"));
        page.addView(createInspirationRow(), UiKit.matchWrapMargin(context, 16, 8, 16, 18));

        page.addView(UiKit.sectionTitle(context, districtName() + "本地精选"));
        String[] local = getLocalDeals(cityName());
        page.addView(dealCard(local[0], local[1], local[2], "本地推荐"), UiKit.matchWrapMargin(context, 16, 8, 16, 10));
        page.addView(dealCard(local[3], local[4], local[5], "近期热门"), UiKit.matchWrapMargin(context, 16, 0, 16, 18));

        page.addView(UiKit.sectionTitle(context, "从" + cityName() + "出发"));
        page.addView(createHorizontalDestinations());

        return UiKit.scrollPage(context, page);
    }

    private View createTopBar() {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout left = new LinearLayout(context);
        left.setOrientation(VERTICAL);
        left.addView(UiKit.text(context, "去趣", 30, UiKit.TEXT_COLOR, true));
        left.addView(UiKit.text(context, "发现你身边的旅行灵感", 14, UiKit.SUB_TEXT_COLOR, false));
        row.addView(left, new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));

        TextView location = UiKit.chip(context, "📍 " + selectedLocation.getDisplayName() + " ▼", Color.WHITE, UiKit.PRIMARY_COLOR);
        location.setOnClickListener(v -> showLocationSelector());
        row.addView(location);
        return row;
    }

    private void showLocationSelector() {
        new LocationSelectorDialog(context, selectedLocation, selection -> {
            selectedLocation = selection;
            saveLocation(selection);
            renderPage();
        }).show();
    }

    private LocationSelection loadLocation() {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        LocationSelection defaults = LocationDataSource.getDefaultSelection();
        return new LocationSelection(
                preferences.getString(KEY_PROVINCE, defaults.getProvince()),
                preferences.getString(KEY_CITY, defaults.getCity()),
                preferences.getString(KEY_DISTRICT, defaults.getDistrict())
        );
    }

    private void saveLocation(LocationSelection selection) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_PROVINCE, selection.getProvince())
                .putString(KEY_CITY, selection.getCity())
                .putString(KEY_DISTRICT, selection.getDistrict())
                .apply();
    }

    private String cityName() {
        return selectedLocation.getCity().replace("市", "");
    }

    private String districtName() {
        String district = selectedLocation.getDistrict();
        return district == null || district.isEmpty() ? cityName() : district;
    }

    private View createHeroCard() {
        LinearLayout hero = UiKit.card(context, UiKit.PRIMARY_COLOR, 178);
        hero.setPadding(UiKit.dp(context, 20), UiKit.dp(context, 22), UiKit.dp(context, 20), UiKit.dp(context, 18));
        hero.addView(UiKit.text(context, districtName() + "周末灵感", 28, Color.WHITE, true));
        hero.addView(UiKit.space(context, 8));
        hero.addView(UiKit.text(context, "城市漫游、周边度假、特色美食", 15, Color.WHITE, false));
        hero.addView(UiKit.space(context, 18));

        LinearLayout tags = new LinearLayout(context);
        tags.setOrientation(HORIZONTAL);
        tags.addView(UiKit.chip(context, "轻松出发", Color.WHITE, UiKit.PRIMARY_COLOR));
        tags.addView(UiKit.space(context, 1));
        tags.addView(UiKit.chip(context, "周边精选", Color.WHITE, UiKit.PRIMARY_COLOR));
        tags.addView(UiKit.space(context, 1));
        tags.addView(UiKit.chip(context, "可随时改", Color.WHITE, UiKit.PRIMARY_COLOR));
        hero.addView(tags);
        hero.addView(UiKit.space(context, 16));
        hero.addView(UiKit.text(context, "探索附近好去处  →", 16, Color.WHITE, true));
        hero.setOnClickListener(v -> openDetail(
                districtName() + "周末灵感",
                "城市漫游、周边度假、特色美食",
                "附近好去处",
                "查看周末方案"
        ));
        return hero;
    }

    private View createSearchBox() {
        LinearLayout searchBox = UiKit.card(context, Color.WHITE, 52);
        searchBox.setGravity(Gravity.CENTER_VERTICAL);
        searchBox.setPadding(UiKit.dp(context, 18), 0, UiKit.dp(context, 18), 0);
        searchBox.addView(UiKit.text(context, "🔍 搜索" + cityName() + "景点、酒店、美食", 15, UiKit.SUB_TEXT_COLOR, false));
        searchBox.setOnClickListener(v -> showSearchDialog());
        return searchBox;
    }

    private void showSearchDialog() {
        final android.widget.EditText input = new android.widget.EditText(context);
        input.setHint("输入景点、酒店或美食");
        input.setSingleLine(true);
        int padding = UiKit.dp(context, 20);
        input.setPadding(padding, padding / 2, padding, padding / 2);

        new android.app.AlertDialog.Builder(context)
                .setTitle("搜索" + cityName())
                .setView(input)
                .setNegativeButton("取消", null)
                .setPositiveButton("搜索", (dialog, which) -> {
                    String keyword = input.getText().toString().trim();
                    if (keyword.isEmpty()) {
                        keyword = cityName() + "热门玩法";
                    }
                    openDetail(keyword, "为你整理相关景点、酒店、美食与路线", "搜索结果", "查看推荐");
                })
                .show();
    }

    private View createInspirationRow() {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(HORIZONTAL);
        row.addView(inspirationItem("🌿", "周边游", "当天往返"), UiKit.weightItem(context));
        row.addView(inspirationItem("🏨", "度假住", "精品酒店"), UiKit.weightItem(context));
        row.addView(inspirationItem("🍜", "本地味", "城市美食"), UiKit.weightItem(context));
        return row;
    }

    private View inspirationItem(String icon, String title, String desc) {
        LinearLayout item = UiKit.card(context, Color.WHITE, 110);
        item.setGravity(Gravity.CENTER);
        item.setPadding(UiKit.dp(context, 8), UiKit.dp(context, 12), UiKit.dp(context, 8), UiKit.dp(context, 12));
        item.addView(UiKit.text(context, icon, 28, UiKit.TEXT_COLOR, false));
        item.addView(UiKit.space(context, 6));
        item.addView(UiKit.text(context, title, 15, UiKit.TEXT_COLOR, true));
        item.addView(UiKit.text(context, desc, 11, UiKit.SUB_TEXT_COLOR, false));
        item.setOnClickListener(v -> openDetail(cityName() + title, desc, "旅行灵感", "查看精选方案"));
        return item;
    }

    private View dealCard(String title, String desc, String price, String badge) {
        LinearLayout card = UiKit.card(context, Color.WHITE, 118);
        card.setPadding(UiKit.dp(context, 18), UiKit.dp(context, 16), UiKit.dp(context, 18), UiKit.dp(context, 16));
        LinearLayout top = new LinearLayout(context);
        top.setGravity(Gravity.CENTER_VERTICAL);
        top.addView(UiKit.text(context, title, 19, UiKit.TEXT_COLOR, true), new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        top.addView(UiKit.chip(context, badge, Color.parseColor("#E5F3F1"), UiKit.PRIMARY_COLOR));
        card.addView(top);
        card.addView(UiKit.space(context, 8));
        card.addView(UiKit.text(context, desc, 14, UiKit.SUB_TEXT_COLOR, false));
        card.addView(UiKit.space(context, 12));
        card.addView(UiKit.text(context, price, 20, UiKit.SECONDARY_COLOR, true));
        card.setOnClickListener(v -> openDetail(title, desc, badge, price));
        return card;
    }

    private View createHorizontalDestinations() {
        String[][] data = getNearbyCities(cityName());
        HorizontalScrollView hsv = new HorizontalScrollView(context);
        hsv.setHorizontalScrollBarEnabled(false);
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(HORIZONTAL);
        row.setPadding(UiKit.dp(context, 16), UiKit.dp(context, 8), UiKit.dp(context, 16), UiKit.dp(context, 18));
        for (String[] item : data) {
            row.addView(destinationCard(item[0], item[1], item[2]));
        }
        hsv.addView(row);
        return hsv;
    }

    private View destinationCard(String city, String desc, String score) {
        LinearLayout card = UiKit.card(context, Color.WHITE, 138);
        card.setPadding(UiKit.dp(context, 16), UiKit.dp(context, 16), UiKit.dp(context, 16), UiKit.dp(context, 16));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(UiKit.dp(context, 142), UiKit.dp(context, 138));
        lp.setMargins(0, 0, UiKit.dp(context, 12), 0);
        card.setLayoutParams(lp);
        card.addView(UiKit.text(context, city, 24, UiKit.TEXT_COLOR, true));
        card.addView(UiKit.space(context, 8));
        card.addView(UiKit.text(context, desc, 14, UiKit.SUB_TEXT_COLOR, false));
        card.addView(UiKit.space(context, 18));
        card.addView(UiKit.chip(context, score, Color.parseColor("#EAF3F8"), UiKit.SECONDARY_COLOR));
        card.setOnClickListener(v -> openDetail(city, desc, "从" + cityName() + "出发 · " + score, "查看出行方案"));
        return card;
    }

    private void openDetail(String title, String subtitle, String type, String price) {
        Intent intent = new Intent(context, TravelDetailActivity.class);
        intent.putExtra(TravelDetailActivity.EXTRA_TITLE, title);
        intent.putExtra(TravelDetailActivity.EXTRA_SUBTITLE, subtitle);
        intent.putExtra(TravelDetailActivity.EXTRA_TYPE, type);
        intent.putExtra(TravelDetailActivity.EXTRA_PRICE, price);
        context.startActivity(intent);
    }

    private String[] getLocalDeals(String city) {
        switch (city) {
            case "北京":
                return new String[]{"胡同文化漫游", "鼓楼 · 什刹海 · 老北京小吃", "¥168起", "长城轻徒步", "慕田峪一日游 · 含往返交通", "¥299起"};
            case "广州":
                return new String[]{"早茶城市漫游", "西关骑楼 · 老字号茶楼", "¥128起", "珠江夜游", "城市夜景 · 船票套餐", "¥188起"};
            case "深圳":
                return new String[]{"滨海骑行", "深圳湾 · 人才公园 · 海风路线", "¥99起", "大鹏半岛度假", "海边民宿 2天1晚", "¥499起"};
            case "成都":
                return new String[]{"成都慢生活", "宽窄巷子 · 茶馆 · 川味小吃", "¥158起", "熊猫基地半日游", "早场入园 · 专车接送", "¥228起"};
            case "杭州":
                return new String[]{"西湖宋韵漫游", "断桥 · 苏堤 · 灵隐寺", "¥138起", "千岛湖度假", "湖景酒店 2天1晚", "¥599起"};
            default:
                return new String[]{"城市经典漫游", city + "热门地标 · 本地美食", "¥128起", "周边轻度假", "精品住宿 2天1晚", "¥499起"};
        }
    }

    private String[][] getNearbyCities(String city) {
        switch (city) {
            case "北京":
                return new String[][]{{"天津", "海河夜景", "4.8分"}, {"承德", "避暑山庄", "4.7分"}, {"秦皇岛", "海边度假", "4.8分"}};
            case "广州":
            case "深圳":
                return new String[][]{{"珠海", "海岛慢游", "4.8分"}, {"佛山", "岭南美食", "4.7分"}, {"惠州", "海湾度假", "4.8分"}};
            case "成都":
                return new String[][]{{"乐山", "大佛美食", "4.8分"}, {"都江堰", "山水人文", "4.9分"}, {"重庆", "山城夜景", "4.8分"}};
            case "杭州":
            case "上海":
                return new String[][]{{"苏州", "园林古巷", "4.9分"}, {"湖州", "山野民宿", "4.7分"}, {"宁波", "海鲜海岸", "4.8分"}};
            default:
                return new String[][]{{"周边古镇", "慢游一天", "4.7分"}, {"山野营地", "自然放松", "4.8分"}, {"滨海小城", "轻松度假", "4.8分"}};
        }
    }
}
