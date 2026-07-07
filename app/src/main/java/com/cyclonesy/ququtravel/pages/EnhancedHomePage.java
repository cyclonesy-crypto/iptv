package com.cyclonesy.ququtravel.pages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyclonesy.ququtravel.TravelDetailActivity;
import com.cyclonesy.ququtravel.UiKit;

public class EnhancedHomePage extends LinearLayout {

    private final Context context;

    public EnhancedHomePage(Context context) {
        super(context);
        this.context = context;
        setOrientation(VERTICAL);
        addView(createPage());
    }

    private View createPage() {
        LinearLayout page = UiKit.pageContainer(context);
        page.setPadding(0, UiKit.dp(context, 10), 0, UiKit.dp(context, 28));

        page.addView(createHeader(), margin(16, 8, 16, 12));
        page.addView(createHero(), margin(16, 0, 16, 14));
        page.addView(createSearch(), margin(16, 0, 16, 18));

        page.addView(section("聪明规划", "从灵感到行程，一站完成"));
        page.addView(createToolGrid(), margin(12, 10, 12, 20));

        page.addView(section("热门旅行情报", "出发前先看最新攻略"));
        page.addView(createNewsScroller());

        page.addView(section("看看别人怎么玩", "热门路线直接参考"));
        page.addView(createItineraryList(), margin(16, 10, 16, 20));

        page.addView(section("旅行必备服务", "出发前一次准备好"));
        page.addView(createServiceRow(), margin(12, 10, 12, 20));

        page.addView(section("本周星榜单", "大家都在收藏的目的地"));
        page.addView(createRanking(), margin(16, 10, 16, 24));

        return UiKit.scrollPage(context, page);
    }

    private View createHeader() {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout title = new LinearLayout(context);
        title.setOrientation(VERTICAL);
        title.addView(UiKit.text(context, "去趣 chicTrip", 28, UiKit.TEXT_COLOR, true));
        title.addView(UiKit.text(context, "轻松规划旅行，从这里开始", 13, UiKit.SUB_TEXT_COLOR, false));
        row.addView(title, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));

        TextView city = UiKit.chip(context, "📍 深圳", Color.WHITE, UiKit.PRIMARY_COLOR);
        city.setOnClickListener(v -> open("选择出发城市", "定位城市并推荐附近玩法", "位置服务", "立即选择"));
        row.addView(city);
        return row;
    }

    private View createHero() {
        LinearLayout card = UiKit.card(context, Color.parseColor("#147A73"), 210);
        card.setPadding(dp(22), dp(24), dp(22), dp(20));
        card.addView(UiKit.chip(context, "AI 行程规划", Color.WHITE, UiKit.PRIMARY_COLOR));
        card.addView(UiKit.space(context, 16));
        card.addView(UiKit.text(context, "一句话，生成你的旅行", 30, Color.WHITE, true));
        card.addView(UiKit.space(context, 8));
        card.addView(UiKit.text(context, "告诉我想去哪里、玩几天、喜欢什么\n立即获得专属路线建议", 15, Color.WHITE, false));
        card.addView(UiKit.space(context, 18));
        TextView action = UiKit.chip(context, "开始规划  →", Color.WHITE, UiKit.PRIMARY_COLOR);
        card.addView(action);
        card.setOnClickListener(v -> open("AI 智能行程规划", "输入目的地、天数和偏好，快速生成旅行路线", "智能规划", "立即创建行程"));
        return card;
    }

    private View createSearch() {
        LinearLayout search = UiKit.card(context, Color.WHITE, 56);
        search.setOrientation(HORIZONTAL);
        search.setGravity(Gravity.CENTER_VERTICAL);
        search.setPadding(dp(18), 0, dp(18), 0);
        search.addView(UiKit.text(context, "🔍", 20, UiKit.TEXT_COLOR, false));
        search.addView(UiKit.text(context, "  搜索目的地、景点、美食、攻略", 15, UiKit.SUB_TEXT_COLOR, false),
                new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        search.addView(UiKit.text(context, "›", 25, UiKit.SUB_TEXT_COLOR, false));
        search.setOnClickListener(v -> open("旅行搜索", "搜索目的地、景点、美食与旅行攻略", "搜索", "查看结果"));
        return search;
    }

    private View createToolGrid() {
        LinearLayout root = new LinearLayout(context);
        root.setOrientation(VERTICAL);
        LinearLayout row1 = new LinearLayout(context);
        LinearLayout row2 = new LinearLayout(context);
        row1.setOrientation(HORIZONTAL);
        row2.setOrientation(HORIZONTAL);

        row1.addView(tool("🗓", "规划行程", "AI 自动排路线"), weight());
        row1.addView(tool("🧭", "找行程", "热门路线参考"), weight());
        row1.addView(tool("📌", "口袋名单", "收藏想去地点"), weight());
        row2.addView(tool("🎫", "优惠券", "旅行边玩边省"), weight());
        row2.addView(tool("📶", "eSIM", "出境上网必备"), weight());
        row2.addView(tool("🧳", "旅行工具", "汇率天气清单"), weight());

        root.addView(row1);
        root.addView(UiKit.space(context, 10));
        root.addView(row2);
        return root;
    }

    private View tool(String icon, String title, String desc) {
        LinearLayout item = UiKit.card(context, Color.WHITE, 112);
        item.setGravity(Gravity.CENTER);
        item.setPadding(dp(8), dp(12), dp(8), dp(10));
        item.addView(UiKit.text(context, icon, 27, UiKit.TEXT_COLOR, false));
        item.addView(UiKit.space(context, 5));
        item.addView(UiKit.text(context, title, 15, UiKit.TEXT_COLOR, true));
        item.addView(UiKit.text(context, desc, 11, UiKit.SUB_TEXT_COLOR, false));
        item.setOnClickListener(v -> open(title, desc, "旅行工具", "立即使用"));
        return item;
    }

    private View createNewsScroller() {
        HorizontalScrollView hsv = new HorizontalScrollView(context);
        hsv.setHorizontalScrollBarEnabled(false);
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(HORIZONTAL);
        row.setPadding(dp(16), dp(10), dp(16), dp(20));
        row.addView(news("🇯🇵 日本", "东京自由行新手攻略", "景点、交通、美食一次整理"));
        row.addView(news("🍜 美食", "大阪必吃与购物路线", "道顿堀、心斋桥一日玩透"));
        row.addView(news("🏝 海岛", "冲绳轻松度假指南", "亲子、美食、海边全覆盖"));
        row.addView(news("🏯 文化", "京都古寺散步路线", "清水寺到祇园经典玩法"));
        hsv.addView(row);
        return hsv;
    }

    private View news(String badge, String title, String desc) {
        LinearLayout card = UiKit.card(context, Color.WHITE, 168);
        LayoutParams lp = new LayoutParams(dp(218), dp(168));
        lp.setMargins(0, 0, dp(12), 0);
        card.setLayoutParams(lp);
        card.setPadding(dp(16), dp(16), dp(16), dp(14));
        TextView cover = UiKit.text(context, "✈  TRAVEL", 14, Color.WHITE, true);
        cover.setGravity(Gravity.CENTER);
        cover.setBackgroundColor(Color.parseColor("#73B9B2"));
        cover.setPadding(0, dp(18), 0, dp(18));
        card.addView(cover);
        card.addView(UiKit.space(context, 10));
        card.addView(UiKit.text(context, badge, 12, UiKit.PRIMARY_COLOR, true));
        card.addView(UiKit.text(context, title, 17, UiKit.TEXT_COLOR, true));
        card.addView(UiKit.text(context, desc, 12, UiKit.SUB_TEXT_COLOR, false));
        card.setOnClickListener(v -> open(title, desc, badge, "阅读攻略"));
        return card;
    }

    private View createItineraryList() {
        LinearLayout list = new LinearLayout(context);
        list.setOrientation(VERTICAL);
        list.addView(itinerary("3天2晚", "东京经典初体验", "浅草 · 银座 · 涩谷 · 新宿", "12.8万人收藏"));
        list.addView(UiKit.space(context, 10));
        list.addView(itinerary("4天3晚", "大阪京都双城游", "大阪城 · 岚山 · 清水寺", "9.6万人收藏"));
        list.addView(UiKit.space(context, 10));
        list.addView(itinerary("2天1晚", "周末城市小旅行", "咖啡 · 夜景 · 在地美食", "6.3万人收藏"));
        return list;
    }

    private View itinerary(String days, String title, String route, String count) {
        LinearLayout card = UiKit.card(context, Color.WHITE, 116);
        card.setPadding(dp(16), dp(15), dp(16), dp(14));
        LinearLayout top = new LinearLayout(context);
        top.setGravity(Gravity.CENTER_VERTICAL);
        top.addView(UiKit.chip(context, days, Color.parseColor("#E5F3F1"), UiKit.PRIMARY_COLOR));
        top.addView(UiKit.text(context, "  " + title, 18, UiKit.TEXT_COLOR, true), new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        top.addView(UiKit.text(context, "›", 24, UiKit.SUB_TEXT_COLOR, false));
        card.addView(top);
        card.addView(UiKit.space(context, 9));
        card.addView(UiKit.text(context, route, 14, UiKit.SUB_TEXT_COLOR, false));
        card.addView(UiKit.space(context, 8));
        card.addView(UiKit.text(context, "♡ " + count, 12, UiKit.PRIMARY_COLOR, true));
        card.setOnClickListener(v -> open(title, route, days + " 热门行程", count));
        return card;
    }

    private View createServiceRow() {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(HORIZONTAL);
        row.addView(service("📶", "海外 eSIM", "落地即联网"), weight());
        row.addView(service("🎟", "日本优惠券", "药妆购物省更多"), weight());
        row.addView(service("🌤", "旅行提醒", "天气汇率行前清单"), weight());
        return row;
    }

    private View service(String icon, String title, String desc) {
        LinearLayout card = UiKit.card(context, Color.WHITE, 126);
        card.setPadding(dp(12), dp(15), dp(12), dp(12));
        card.addView(UiKit.text(context, icon, 28, UiKit.TEXT_COLOR, false));
        card.addView(UiKit.space(context, 8));
        card.addView(UiKit.text(context, title, 15, UiKit.TEXT_COLOR, true));
        card.addView(UiKit.text(context, desc, 11, UiKit.SUB_TEXT_COLOR, false));
        card.setOnClickListener(v -> open(title, desc, "旅行服务", "立即查看"));
        return card;
    }

    private View createRanking() {
        LinearLayout list = new LinearLayout(context);
        list.setOrientation(VERTICAL);
        list.addView(rank("01", "东京", "城市漫游与购物首选", "9.8"));
        list.addView(UiKit.space(context, 8));
        list.addView(rank("02", "大阪", "美食与主题乐园", "9.6"));
        list.addView(UiKit.space(context, 8));
        list.addView(rank("03", "京都", "古都文化深度体验", "9.5"));
        list.addView(UiKit.space(context, 8));
        list.addView(rank("04", "冲绳", "海岛亲子轻度假", "9.3"));
        list.addView(UiKit.space(context, 8));
        list.addView(rank("05", "福冈", "拉面与九州门户", "9.2"));
        return list;
    }

    private View rank(String no, String city, String desc, String score) {
        LinearLayout row = UiKit.card(context, Color.WHITE, 78);
        row.setOrientation(HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(dp(16), dp(12), dp(16), dp(12));
        row.addView(UiKit.text(context, no, 21, UiKit.PRIMARY_COLOR, true), new LayoutParams(dp(50), LayoutParams.WRAP_CONTENT));
        LinearLayout info = new LinearLayout(context);
        info.setOrientation(VERTICAL);
        info.addView(UiKit.text(context, city, 17, UiKit.TEXT_COLOR, true));
        info.addView(UiKit.text(context, desc, 12, UiKit.SUB_TEXT_COLOR, false));
        row.addView(info, new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        row.addView(UiKit.chip(context, score + "分", Color.parseColor("#FFF3DD"), Color.parseColor("#D88400")));
        row.setOnClickListener(v -> open(city + "旅行攻略", desc, "热门目的地 Top " + no, score + "分"));
        return row;
    }

    private LinearLayout section(String title, String subtitle) {
        LinearLayout wrap = new LinearLayout(context);
        wrap.setOrientation(VERTICAL);
        wrap.setPadding(dp(16), dp(2), dp(16), 0);
        wrap.addView(UiKit.text(context, title, 21, UiKit.TEXT_COLOR, true));
        wrap.addView(UiKit.text(context, subtitle, 13, UiKit.SUB_TEXT_COLOR, false));
        return wrap;
    }

    private void open(String title, String subtitle, String type, String price) {
        Intent intent = new Intent(context, TravelDetailActivity.class);
        intent.putExtra(TravelDetailActivity.EXTRA_TITLE, title);
        intent.putExtra(TravelDetailActivity.EXTRA_SUBTITLE, subtitle);
        intent.putExtra(TravelDetailActivity.EXTRA_TYPE, type);
        intent.putExtra(TravelDetailActivity.EXTRA_PRICE, price);
        context.startActivity(intent);
    }

    private LayoutParams weight() {
        LayoutParams lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        lp.setMargins(dp(4), 0, dp(4), 0);
        return lp;
    }

    private LayoutParams margin(int l, int t, int r, int b) {
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(dp(l), dp(t), dp(r), dp(b));
        return lp;
    }

    private int dp(int value) {
        return UiKit.dp(context, value);
    }
}
