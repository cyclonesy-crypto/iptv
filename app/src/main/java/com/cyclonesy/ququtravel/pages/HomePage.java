package com.cyclonesy.ququtravel.pages;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyclonesy.ququtravel.UiKit;

public class HomePage extends LinearLayout {

    private final Context context;

    public HomePage(Context context) {
        super(context);
        this.context = context;
        setOrientation(VERTICAL);
        addView(createContent());
    }

    private View createContent() {
        LinearLayout page = UiKit.pageContainer(context);
        page.setPadding(0, UiKit.dp(context, 10), 0, UiKit.dp(context, 22));

        page.addView(createTopBar(), UiKit.matchWrapMargin(context, 16, 8, 16, 10));
        page.addView(createHeroCard(), UiKit.matchWrapMargin(context, 16, 0, 16, 12));
        page.addView(createSearchBox(), UiKit.matchWrapMargin(context, 16, 0, 16, 16));

        page.addView(UiKit.sectionTitle(context, "旅行灵感"));
        page.addView(createInspirationRow(), UiKit.matchWrapMargin(context, 16, 8, 16, 18));

        page.addView(UiKit.sectionTitle(context, "限时特惠"));
        page.addView(dealCard("周末住进山里", "莫干山民宿 2天1晚 · 含早餐", "¥499起", "今日热卖"), UiKit.matchWrapMargin(context, 16, 8, 16, 10));
        page.addView(dealCard("海边躺平计划", "三亚湾自由行 4天3晚 · 接送机", "¥1299起", "省 ¥300"), UiKit.matchWrapMargin(context, 16, 0, 16, 18));

        page.addView(UiKit.sectionTitle(context, "猜你想去"));
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
        left.addView(UiKit.text(context, "今天想去哪里玩？", 14, UiKit.SUB_TEXT_COLOR, false));
        row.addView(left, new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));

        TextView city = UiKit.chip(context, "上海 ▼", Color.WHITE, UiKit.PRIMARY_COLOR);
        row.addView(city);
        return row;
    }

    private View createHeroCard() {
        LinearLayout hero = UiKit.card(context, UiKit.PRIMARY_COLOR, 178);
        hero.setPadding(UiKit.dp(context, 20), UiKit.dp(context, 22), UiKit.dp(context, 20), UiKit.dp(context, 18));
        hero.addView(UiKit.text(context, "夏日出逃计划", 28, Color.WHITE, true));
        hero.addView(UiKit.space(context, 8));
        hero.addView(UiKit.text(context, "精选海岛、山野、亲子路线", 15, Color.WHITE, false));
        hero.addView(UiKit.space(context, 18));

        LinearLayout tags = new LinearLayout(context);
        tags.setOrientation(HORIZONTAL);
        tags.addView(UiKit.chip(context, "免攻略", Color.WHITE, UiKit.PRIMARY_COLOR));
        tags.addView(UiKit.space(context, 1));
        tags.addView(UiKit.chip(context, "小团游", Color.WHITE, UiKit.PRIMARY_COLOR));
        tags.addView(UiKit.space(context, 1));
        tags.addView(UiKit.chip(context, "可退改", Color.WHITE, UiKit.PRIMARY_COLOR));
        hero.addView(tags);
        hero.addView(UiKit.space(context, 16));
        hero.addView(UiKit.text(context, "立即探索  →", 16, Color.WHITE, true));
        return hero;
    }

    private View createSearchBox() {
        LinearLayout searchBox = UiKit.card(context, Color.WHITE, 52);
        searchBox.setGravity(Gravity.CENTER_VERTICAL);
        searchBox.setPadding(UiKit.dp(context, 18), 0, UiKit.dp(context, 18), 0);
        searchBox.addView(UiKit.text(context, "🔍 搜索城市、景点、酒店、美食", 15, UiKit.SUB_TEXT_COLOR, false));
        return searchBox;
    }

    private View createInspirationRow() {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(HORIZONTAL);
        row.addView(inspirationItem("🏝", "海岛", "阳光沙滩"), UiKit.weightItem(context));
        row.addView(inspirationItem("⛺", "露营", "山野星空"), UiKit.weightItem(context));
        row.addView(inspirationItem("🎡", "亲子", "轻松遛娃"), UiKit.weightItem(context));
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
        return item;
    }

    private View dealCard(String title, String desc, String price, String badge) {
        LinearLayout card = UiKit.card(context, Color.WHITE, 118);
        card.setPadding(UiKit.dp(context, 18), UiKit.dp(context, 16), UiKit.dp(context, 18), UiKit.dp(context, 16));
        LinearLayout top = new LinearLayout(context);
        top.setGravity(Gravity.CENTER_VERTICAL);
        top.addView(UiKit.text(context, title, 19, UiKit.TEXT_COLOR, true), new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        top.addView(UiKit.chip(context, badge, Color.parseColor("#fff1eb"), UiKit.PRIMARY_COLOR));
        card.addView(top);
        card.addView(UiKit.space(context, 8));
        card.addView(UiKit.text(context, desc, 14, UiKit.SUB_TEXT_COLOR, false));
        card.addView(UiKit.space(context, 12));
        card.addView(UiKit.text(context, price, 20, UiKit.PRIMARY_COLOR, true));
        return card;
    }

    private View createHorizontalDestinations() {
        HorizontalScrollView hsv = new HorizontalScrollView(context);
        hsv.setHorizontalScrollBarEnabled(false);
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(HORIZONTAL);
        row.setPadding(UiKit.dp(context, 16), UiKit.dp(context, 8), UiKit.dp(context, 16), UiKit.dp(context, 18));
        row.addView(destinationCard("厦门", "海风骑行", "4.8分"));
        row.addView(destinationCard("长沙", "夜市美食", "4.7分"));
        row.addView(destinationCard("青岛", "啤酒海岸", "4.9分"));
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
        card.addView(UiKit.chip(context, score, Color.parseColor("#fff7e8"), Color.parseColor("#d98900")));
        return card;
    }
}
