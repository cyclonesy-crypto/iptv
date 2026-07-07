package com.cyclonesy.ququtravel;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TravelDetailActivity extends Activity {

    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_SUBTITLE = "subtitle";
    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_PRICE = "price";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createPage());
    }

    private View createPage() {
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String subtitle = getIntent().getStringExtra(EXTRA_SUBTITLE);
        String type = getIntent().getStringExtra(EXTRA_TYPE);
        String price = getIntent().getStringExtra(EXTRA_PRICE);

        LinearLayout page = UiKit.pageContainer(this);
        page.setPadding(UiKit.dp(this, 16), UiKit.dp(this, 18), UiKit.dp(this, 16), UiKit.dp(this, 24));

        TextView back = UiKit.text(this, "‹  返回", 16, UiKit.PRIMARY_COLOR, true);
        back.setPadding(0, UiKit.dp(this, 8), 0, UiKit.dp(this, 16));
        back.setOnClickListener(v -> finish());
        page.addView(back);

        LinearLayout hero = UiKit.card(this, UiKit.PRIMARY_COLOR, 190);
        hero.setPadding(UiKit.dp(this, 20), UiKit.dp(this, 24), UiKit.dp(this, 20), UiKit.dp(this, 20));
        hero.addView(UiKit.chip(this, safe(type, "旅行灵感"), Color.WHITE, UiKit.PRIMARY_COLOR));
        hero.addView(UiKit.space(this, 16));
        hero.addView(UiKit.text(this, safe(title, "发现好去处"), 30, Color.WHITE, true));
        hero.addView(UiKit.space(this, 10));
        hero.addView(UiKit.text(this, safe(subtitle, "精选路线与本地玩法"), 15, Color.WHITE, false));
        page.addView(hero);

        page.addView(UiKit.space(this, 18));
        page.addView(UiKit.sectionTitle(this, "推荐玩法"));
        page.addView(UiKit.routeCard(this, "经典打卡路线", "热门地标串联，适合第一次到访", "轻松出发"), UiKit.matchWrapMargin(this, 0, 10, 0, 10));
        page.addView(UiKit.routeCard(this, "本地人路线", "避开拥挤区域，体验城市生活方式", "深度体验"), UiKit.matchWrapMargin(this, 0, 0, 0, 10));
        page.addView(UiKit.routeCard(this, "美食休闲路线", "边走边吃，行程节奏更轻松", "人气推荐"), UiKit.matchWrapMargin(this, 0, 0, 0, 18));

        LinearLayout booking = UiKit.card(this, Color.WHITE, 72);
        booking.setOrientation(LinearLayout.HORIZONTAL);
        booking.setGravity(Gravity.CENTER_VERTICAL);
        booking.setPadding(UiKit.dp(this, 18), UiKit.dp(this, 12), UiKit.dp(this, 18), UiKit.dp(this, 12));
        booking.addView(UiKit.text(this, safe(price, "查看可订方案"), 20, UiKit.SECONDARY_COLOR, true),
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        TextView button = UiKit.chip(this, "立即查看", UiKit.PRIMARY_COLOR, Color.WHITE);
        button.setOnClickListener(v -> showBookingResult(title));
        booking.addView(button);
        page.addView(booking);

        return UiKit.scrollPage(this, page);
    }

    private void showBookingResult(String title) {
        new android.app.AlertDialog.Builder(this)
                .setTitle("已选择 " + safe(title, "旅行方案"))
                .setMessage("当前为演示数据。后续接入酒店、门票或路线接口后，可在这里展示日期、人数、库存和价格。")
                .setPositiveButton("知道了", null)
                .show();
    }

    private String safe(String value, String fallback) {
        return value == null || value.trim().isEmpty() ? fallback : value;
    }
}
