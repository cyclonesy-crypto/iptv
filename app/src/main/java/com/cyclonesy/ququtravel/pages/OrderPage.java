package com.cyclonesy.ququtravel.pages;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.cyclonesy.ququtravel.UiKit;

public class OrderPage extends LinearLayout {

    private final Context context;

    public OrderPage(Context context) {
        super(context);
        this.context = context;
        setOrientation(VERTICAL);
        addView(createContent());
    }

    private View createContent() {
        LinearLayout page = UiKit.pageContainer(context);
        page.setPadding(0, UiKit.dp(context, 16), 0, UiKit.dp(context, 22));

        page.addView(UiKit.text(context, "行程", 28, UiKit.TEXT_COLOR, true), UiKit.matchWrapMargin(context, 16, 10, 16, 4));
        page.addView(UiKit.text(context, "未登录也可以先规划路线、保存草稿", 15, UiKit.SUB_TEXT_COLOR, false), UiKit.matchWrapMargin(context, 16, 0, 16, 14));

        page.addView(createDraftTripCard(), UiKit.matchWrapMargin(context, 16, 0, 16, 16));

        page.addView(UiKit.sectionTitle(context, "行程状态"));
        page.addView(createStatusBoard(), UiKit.matchWrapMargin(context, 16, 8, 16, 16));

        page.addView(UiKit.sectionTitle(context, "推荐行程模板"));
        page.addView(planCard("上海周边 2 日游", "苏州园林 · 平江路 · 金鸡湖夜景", "适合周末"), UiKit.matchWrapMargin(context, 16, 8, 16, 10));
        page.addView(planCard("成都美食 3 日游", "宽窄巷子 · 熊猫基地 · 九眼桥", "吃货必选"), UiKit.matchWrapMargin(context, 16, 0, 16, 10));
        page.addView(planCard("三亚躺平 4 日游", "海景酒店 · 免税店 · 蜈支洲岛", "轻松度假"), UiKit.matchWrapMargin(context, 16, 0, 16, 16));

        page.addView(UiKit.sectionTitle(context, "出行工具"));
        page.addView(toolRow("📅", "旅行日历", "查看假期、调休和最佳出发日"), UiKit.matchWrapMargin(context, 16, 8, 16, 1));
        page.addView(toolRow("🧳", "行李清单", "按目的地自动生成准备事项"), UiKit.matchWrapMargin(context, 16, 0, 16, 1));
        page.addView(toolRow("🌦", "天气提醒", "出发前查看当地天气变化"), UiKit.matchWrapMargin(context, 16, 0, 16, 18));

        return UiKit.scrollPage(context, page);
    }

    private View createDraftTripCard() {
        LinearLayout card = UiKit.card(context, Color.parseColor("#fff1eb"), 150);
        card.setPadding(UiKit.dp(context, 18), UiKit.dp(context, 18), UiKit.dp(context, 18), UiKit.dp(context, 18));
        card.addView(UiKit.text(context, "还没有正式订单", 22, UiKit.TEXT_COLOR, true));
        card.addView(UiKit.space(context, 8));
        card.addView(UiKit.text(context, "可以先创建一个旅行计划，选好路线后再下单。", 14, UiKit.SUB_TEXT_COLOR, false));
        card.addView(UiKit.space(context, 18));
        card.addView(UiKit.chip(context, "+ 创建我的行程", UiKit.PRIMARY_COLOR, Color.WHITE));
        return card;
    }

    private View createStatusBoard() {
        LinearLayout card = UiKit.card(context, Color.WHITE, 112);
        card.setPadding(UiKit.dp(context, 8), UiKit.dp(context, 16), UiKit.dp(context, 8), UiKit.dp(context, 16));

        LinearLayout row = new LinearLayout(context);
        row.setOrientation(HORIZONTAL);
        row.addView(statusItem("0", "待支付"), UiKit.weightNoMargin());
        row.addView(statusItem("0", "待出行"), UiKit.weightNoMargin());
        row.addView(statusItem("0", "待评价"), UiKit.weightNoMargin());
        row.addView(statusItem("0", "售后"), UiKit.weightNoMargin());
        card.addView(row);
        return card;
    }

    private View statusItem(String count, String label) {
        LinearLayout item = new LinearLayout(context);
        item.setOrientation(VERTICAL);
        item.setGravity(Gravity.CENTER);
        item.addView(UiKit.text(context, count, 24, UiKit.PRIMARY_COLOR, true));
        item.addView(UiKit.space(context, 6));
        item.addView(UiKit.text(context, label, 13, UiKit.TEXT_COLOR, false));
        return item;
    }

    private View planCard(String title, String desc, String tag) {
        LinearLayout card = UiKit.card(context, Color.WHITE, 106);
        card.setPadding(UiKit.dp(context, 18), UiKit.dp(context, 16), UiKit.dp(context, 18), UiKit.dp(context, 16));
        card.addView(UiKit.text(context, title, 18, UiKit.TEXT_COLOR, true));
        card.addView(UiKit.space(context, 8));
        card.addView(UiKit.text(context, desc, 14, UiKit.SUB_TEXT_COLOR, false));
        card.addView(UiKit.space(context, 10));
        card.addView(UiKit.chip(context, tag, Color.parseColor("#f0f7ff"), Color.parseColor("#3478f6")));
        return card;
    }

    private View toolRow(String icon, String title, String desc) {
        LinearLayout row = UiKit.card(context, Color.WHITE, 68);
        row.setOrientation(HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(UiKit.dp(context, 16), UiKit.dp(context, 12), UiKit.dp(context, 16), UiKit.dp(context, 12));
        row.addView(UiKit.text(context, icon, 24, UiKit.TEXT_COLOR, false), new LinearLayout.LayoutParams(UiKit.dp(context, 42), LayoutParams.WRAP_CONTENT));

        LinearLayout info = new LinearLayout(context);
        info.setOrientation(VERTICAL);
        info.addView(UiKit.text(context, title, 16, UiKit.TEXT_COLOR, true));
        info.addView(UiKit.space(context, 3));
        info.addView(UiKit.text(context, desc, 12, UiKit.SUB_TEXT_COLOR, false));
        row.addView(info, new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        row.addView(UiKit.text(context, "›", 26, UiKit.SUB_TEXT_COLOR, false));
        return row;
    }
}
