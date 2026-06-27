package com.cyclonesy.ququtravel.pages;

import android.content.Context;
import android.graphics.Color;
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

        TextView title = UiKit.text(context, "去趣", 30, Color.WHITE, true);
        TextView subtitle = UiKit.text(context, "发现周边好玩的地方", 16, Color.WHITE, false);
        LinearLayout banner = UiKit.card(context, UiKit.PRIMARY_COLOR, 24);
        banner.setPadding(UiKit.dp(context, 22), UiKit.dp(context, 22), UiKit.dp(context, 22), UiKit.dp(context, 22));
        banner.addView(title);
        banner.addView(UiKit.space(context, 8));
        banner.addView(subtitle);
        banner.addView(UiKit.space(context, 18));
        banner.addView(UiKit.chip(context, "周末游 · 亲子游 · 城市漫步", Color.WHITE, UiKit.PRIMARY_COLOR));
        page.addView(banner, UiKit.matchWrapMargin(context, 16, 16, 16, 12));

        TextView search = UiKit.text(context, "🔍 搜索城市、景点、酒店、美食", 15, UiKit.SUB_TEXT_COLOR, false);
        LinearLayout searchBox = UiKit.card(context, Color.WHITE, 18);
        searchBox.setPadding(UiKit.dp(context, 18), UiKit.dp(context, 14), UiKit.dp(context, 18), UiKit.dp(context, 14));
        searchBox.addView(search);
        page.addView(searchBox, UiKit.matchWrapMargin(context, 16, 0, 16, 18));

        page.addView(UiKit.sectionTitle(context, "热门玩法"));
        LinearLayout playRow = new LinearLayout(context);
        playRow.setOrientation(HORIZONTAL);
        playRow.addView(playItem("🏖", "海岛度假"), UiKit.weightItem(context));
        playRow.addView(playItem("🏔", "山野徒步"), UiKit.weightItem(context));
        playRow.addView(playItem("🏙", "城市探索"), UiKit.weightItem(context));
        page.addView(playRow, UiKit.matchWrapMargin(context, 16, 8, 16, 18));

        page.addView(UiKit.sectionTitle(context, "精选目的地"));
        HorizontalScrollView hsv = new HorizontalScrollView(context);
        hsv.setHorizontalScrollBarEnabled(false);
        LinearLayout destinationRow = new LinearLayout(context);
        destinationRow.setOrientation(HORIZONTAL);
        destinationRow.setPadding(UiKit.dp(context, 16), UiKit.dp(context, 8), UiKit.dp(context, 16), UiKit.dp(context, 8));
        destinationRow.addView(destinationCard("三亚", "阳光海滩", "¥899起"));
        destinationRow.addView(destinationCard("大理", "风花雪月", "¥699起"));
        destinationRow.addView(destinationCard("成都", "美食慢生活", "¥599起"));
        hsv.addView(destinationRow);
        page.addView(hsv);

        page.addView(UiKit.sectionTitle(context, "今日推荐"));
        page.addView(UiKit.routeCard(context, "杭州西湖一日游", "含船票 · 精致小团 · 4.8分", "¥198起"), UiKit.matchWrapMargin(context, 16, 8, 16, 10));
        page.addView(UiKit.routeCard(context, "上海迪士尼亲子游", "快速入园 · 亲子优选 · 4.9分", "¥399起"), UiKit.matchWrapMargin(context, 16, 0, 16, 20));

        return UiKit.scrollPage(context, page);
    }

    private LinearLayout playItem(String icon, String title) {
        LinearLayout item = UiKit.card(context, Color.WHITE, 18);
        item.setGravity(android.view.Gravity.CENTER);
        item.setPadding(UiKit.dp(context, 8), UiKit.dp(context, 16), UiKit.dp(context, 8), UiKit.dp(context, 16));
        item.addView(UiKit.text(context, icon, 26, UiKit.TEXT_COLOR, false));
        item.addView(UiKit.space(context, 6));
        item.addView(UiKit.text(context, title, 13, UiKit.TEXT_COLOR, true));
        return item;
    }

    private LinearLayout destinationCard(String city, String desc, String price) {
        LinearLayout card = UiKit.card(context, Color.WHITE, 20);
        card.setPadding(UiKit.dp(context, 18), UiKit.dp(context, 18), UiKit.dp(context, 18), UiKit.dp(context, 18));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(UiKit.dp(context, 150), UiKit.dp(context, 145));
        lp.setMargins(0, 0, UiKit.dp(context, 12), 0);
        card.setLayoutParams(lp);
        card.addView(UiKit.text(context, city, 24, UiKit.TEXT_COLOR, true));
        card.addView(UiKit.space(context, 6));
        card.addView(UiKit.text(context, desc, 14, UiKit.SUB_TEXT_COLOR, false));
        card.addView(UiKit.space(context, 22));
        card.addView(UiKit.chip(context, price, Color.parseColor("#fff1eb"), UiKit.PRIMARY_COLOR));
        return card;
    }
}
