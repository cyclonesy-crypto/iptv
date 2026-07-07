package com.cyclonesy.ququtravel.pages;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.cyclonesy.ququtravel.UiKit;

public class DestinationPage extends LinearLayout {

    private final Context context;

    public DestinationPage(Context context) {
        super(context);
        this.context = context;
        setOrientation(VERTICAL);
        addView(createContent());
    }

    private View createContent() {
        LinearLayout page = UiKit.pageContainer(context);
        page.setPadding(0, UiKit.dp(context, 16), 0, UiKit.dp(context, 22));

        page.addView(UiKit.text(context, "目的地探索", 28, UiKit.TEXT_COLOR, true), UiKit.matchWrapMargin(context, 16, 10, 16, 4));
        page.addView(UiKit.text(context, "不用登录，先看看哪里值得去", 15, UiKit.SUB_TEXT_COLOR, false), UiKit.matchWrapMargin(context, 16, 0, 16, 14));

        page.addView(createMapCard(), UiKit.matchWrapMargin(context, 16, 0, 16, 16));
        page.addView(createCityTabs());

        page.addView(UiKit.sectionTitle(context, "热门城市"));
        page.addView(cityCard("成都", "火锅、熊猫、慢生活", "3天2晚", "¥599起"), UiKit.matchWrapMargin(context, 16, 8, 16, 10));
        page.addView(cityCard("大理", "洱海骑行、古城散步", "4天3晚", "¥799起"), UiKit.matchWrapMargin(context, 16, 0, 16, 10));
        page.addView(cityCard("杭州", "西湖、灵隐、宋韵体验", "2天1晚", "¥399起"), UiKit.matchWrapMargin(context, 16, 0, 16, 16));

        page.addView(UiKit.sectionTitle(context, "主题榜单"));
        page.addView(rankCard("01", "最适合周末出发", "苏州 · 杭州 · 南京"), UiKit.matchWrapMargin(context, 16, 8, 16, 8));
        page.addView(rankCard("02", "年轻人最爱夜游", "长沙 · 重庆 · 西安"), UiKit.matchWrapMargin(context, 16, 0, 16, 8));
        page.addView(rankCard("03", "亲子轻松不累", "珠海 · 广州 · 上海"), UiKit.matchWrapMargin(context, 16, 0, 16, 18));

        return UiKit.scrollPage(context, page);
    }

    private View createMapCard() {
        LinearLayout card = UiKit.card(context, Color.parseColor("#eaf6ff"), 150);
        card.setGravity(Gravity.CENTER);
        card.setPadding(UiKit.dp(context, 18), UiKit.dp(context, 18), UiKit.dp(context, 18), UiKit.dp(context, 18));
        card.addView(UiKit.text(context, "🗺", 42, UiKit.TEXT_COLOR, false));
        card.addView(UiKit.space(context, 8));
        card.addView(UiKit.text(context, "中国目的地地图", 22, UiKit.TEXT_COLOR, true));
        card.addView(UiKit.text(context, "按城市、主题、预算快速筛选", 13, UiKit.SUB_TEXT_COLOR, false));
        return card;
    }

    private View createCityTabs() {
        HorizontalScrollView hsv = new HorizontalScrollView(context);
        hsv.setHorizontalScrollBarEnabled(false);
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(HORIZONTAL);
        row.setPadding(UiKit.dp(context, 16), 0, UiKit.dp(context, 16), UiKit.dp(context, 16));
        row.addView(tabChip("全部", true));
        row.addView(tabChip("周边游", false));
        row.addView(tabChip("海岛", false));
        row.addView(tabChip("古镇", false));
        row.addView(tabChip("美食", false));
        row.addView(tabChip("亲子", false));
        hsv.addView(row);
        return hsv;
    }

    private View tabChip(String text, boolean selected) {
        LinearLayout wrap = new LinearLayout(context);
        wrap.setPadding(0, 0, UiKit.dp(context, 8), 0);
        int bg = selected ? UiKit.PRIMARY_COLOR : Color.WHITE;
        int fg = selected ? Color.WHITE : UiKit.TEXT_COLOR;
        wrap.addView(UiKit.chip(context, text, bg, fg));
        return wrap;
    }

    private View cityCard(String city, String desc, String days, String price) {
        LinearLayout card = UiKit.card(context, Color.WHITE, 112);
        card.setPadding(UiKit.dp(context, 18), UiKit.dp(context, 16), UiKit.dp(context, 18), UiKit.dp(context, 16));

        LinearLayout top = new LinearLayout(context);
        top.setGravity(Gravity.CENTER_VERTICAL);
        top.addView(UiKit.text(context, city, 22, UiKit.TEXT_COLOR, true), new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        top.addView(UiKit.chip(context, days, Color.parseColor("#f0f7ff"), Color.parseColor("#3478f6")));
        card.addView(top);
        card.addView(UiKit.space(context, 8));
        card.addView(UiKit.text(context, desc, 14, UiKit.SUB_TEXT_COLOR, false));
        card.addView(UiKit.space(context, 10));
        card.addView(UiKit.text(context, price, 18, UiKit.PRIMARY_COLOR, true));
        return card;
    }

    private View rankCard(String no, String title, String desc) {
        LinearLayout row = UiKit.card(context, Color.WHITE, 72);
        row.setOrientation(HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(UiKit.dp(context, 16), UiKit.dp(context, 12), UiKit.dp(context, 16), UiKit.dp(context, 12));
        row.addView(UiKit.text(context, no, 22, UiKit.PRIMARY_COLOR, true), new LinearLayout.LayoutParams(UiKit.dp(context, 52), LayoutParams.WRAP_CONTENT));

        LinearLayout info = new LinearLayout(context);
        info.setOrientation(VERTICAL);
        info.addView(UiKit.text(context, title, 16, UiKit.TEXT_COLOR, true));
        info.addView(UiKit.space(context, 4));
        info.addView(UiKit.text(context, desc, 13, UiKit.SUB_TEXT_COLOR, false));
        row.addView(info, new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        row.addView(UiKit.text(context, "›", 26, UiKit.SUB_TEXT_COLOR, false));
        return row;
    }
}
