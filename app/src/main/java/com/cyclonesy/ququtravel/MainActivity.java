package com.cyclonesy.ququtravel;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private FrameLayout contentContainer;
    private TextView[] tabViews;
    private final String[] tabTitles = {"首页", "目的地", "订单", "我的"};

    private final int primaryColor = Color.parseColor("#ff6f3c");
    private final int textColor = Color.parseColor("#222222");
    private final int subTextColor = Color.parseColor("#777777");
    private final int bgColor = Color.parseColor("#f7f8fa");
    private final int cardColor = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createRootView());
        switchTab(0);
    }

    private View createRootView() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(bgColor);
        root.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        contentContainer = new FrameLayout(this);
        root.addView(contentContainer, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                1
        ));

        root.addView(createBottomTabs(), new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                dp(64)
        ));

        return root;
    }

    private View createBottomTabs() {
        LinearLayout tabs = new LinearLayout(this);
        tabs.setOrientation(LinearLayout.HORIZONTAL);
        tabs.setGravity(Gravity.CENTER);
        tabs.setBackgroundColor(Color.WHITE);
        tabs.setPadding(0, dp(6), 0, dp(6));

        tabViews = new TextView[tabTitles.length];
        for (int i = 0; i < tabTitles.length; i++) {
            final int index = i;
            TextView tab = new TextView(this);
            tab.setGravity(Gravity.CENTER);
            tab.setText(tabTitles[i]);
            tab.setTextSize(14);
            tab.setOnClickListener(v -> switchTab(index));
            tabViews[i] = tab;
            tabs.addView(tab, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        }
        return tabs;
    }

    private void switchTab(int index) {
        contentContainer.removeAllViews();
        for (int i = 0; i < tabViews.length; i++) {
            tabViews[i].setTextColor(i == index ? primaryColor : subTextColor);
            tabViews[i].setTypeface(Typeface.DEFAULT, i == index ? Typeface.BOLD : Typeface.NORMAL);
        }

        if (index == 0) {
            contentContainer.addView(createHomePage());
        } else if (index == 1) {
            contentContainer.addView(createDestinationPage());
        } else if (index == 2) {
            contentContainer.addView(createOrderPage());
        } else {
            contentContainer.addView(createMinePage());
        }
    }

    private View createHomePage() {
        ScrollView scrollView = new ScrollView(this);
        LinearLayout page = createPageContainer();
        scrollView.addView(page);

        TextView title = text("去趣", 30, Color.WHITE, true);
        TextView subtitle = text("发现周边好玩的地方", 16, Color.WHITE, false);
        LinearLayout banner = card(primaryColor, dp(24));
        banner.setPadding(dp(22), dp(22), dp(22), dp(22));
        banner.addView(title);
        banner.addView(space(8));
        banner.addView(subtitle);
        banner.addView(space(18));
        banner.addView(chip("周末游 · 亲子游 · 城市漫步", Color.WHITE, primaryColor));
        page.addView(banner, matchWrapMargin(16, 16, 16, 12));

        TextView search = text("🔍 搜索城市、景点、酒店、美食", 15, subTextColor, false);
        LinearLayout searchBox = card(Color.WHITE, dp(18));
        searchBox.setPadding(dp(18), dp(14), dp(18), dp(14));
        searchBox.addView(search);
        page.addView(searchBox, matchWrapMargin(16, 0, 16, 18));

        page.addView(sectionTitle("热门玩法"));
        LinearLayout playRow = new LinearLayout(this);
        playRow.setOrientation(LinearLayout.HORIZONTAL);
        playRow.addView(playItem("🏖", "海岛度假"), weightItem());
        playRow.addView(playItem("🏔", "山野徒步"), weightItem());
        playRow.addView(playItem("🏙", "城市探索"), weightItem());
        page.addView(playRow, matchWrapMargin(16, 8, 16, 18));

        page.addView(sectionTitle("精选目的地"));
        HorizontalScrollView hsv = new HorizontalScrollView(this);
        hsv.setHorizontalScrollBarEnabled(false);
        LinearLayout destinationRow = new LinearLayout(this);
        destinationRow.setOrientation(LinearLayout.HORIZONTAL);
        destinationRow.setPadding(dp(16), dp(8), dp(16), dp(8));
        destinationRow.addView(destinationCard("三亚", "阳光海滩", "¥899起"));
        destinationRow.addView(destinationCard("大理", "风花雪月", "¥699起"));
        destinationRow.addView(destinationCard("成都", "美食慢生活", "¥599起"));
        hsv.addView(destinationRow);
        page.addView(hsv);

        page.addView(sectionTitle("今日推荐"));
        page.addView(routeCard("杭州西湖一日游", "含船票 · 精致小团 · 4.8分", "¥198起"), matchWrapMargin(16, 8, 16, 10));
        page.addView(routeCard("上海迪士尼亲子游", "快速入园 · 亲子优选 · 4.9分", "¥399起"), matchWrapMargin(16, 0, 16, 20));

        return scrollView;
    }

    private View createDestinationPage() {
        LinearLayout page = createSimplePage("目的地", "选择一个城市，开始你的旅行计划");
        page.addView(routeCard("云南", "大理 · 丽江 · 香格里拉", "热门"), matchWrapMargin(16, 16, 16, 10));
        page.addView(routeCard("海南", "三亚 · 万宁 · 海口", "海岛"), matchWrapMargin(16, 0, 16, 10));
        page.addView(routeCard("四川", "成都 · 九寨沟 · 稻城", "美食"), matchWrapMargin(16, 0, 16, 10));
        return wrapScroll(page);
    }

    private View createOrderPage() {
        LinearLayout page = createSimplePage("订单", "这里展示待出行、待支付、已完成的订单");
        page.addView(emptyCard("暂无旅行订单\n下单后会在这里展示行程信息"), matchWrapMargin(16, 28, 16, 10));
        return wrapScroll(page);
    }

    private View createMinePage() {
        LinearLayout page = createSimplePage("我的", "管理个人资料、优惠券、收藏和客服");
        page.addView(routeCard("我的收藏", "收藏喜欢的目的地和路线", "查看"), matchWrapMargin(16, 16, 16, 10));
        page.addView(routeCard("优惠券", "新人专享旅行优惠", "领取"), matchWrapMargin(16, 0, 16, 10));
        page.addView(routeCard("联系客服", "7x24小时在线支持", "咨询"), matchWrapMargin(16, 0, 16, 10));
        return wrapScroll(page);
    }

    private LinearLayout createSimplePage(String titleText, String subTitleText) {
        LinearLayout page = createPageContainer();
        page.addView(text(titleText, 28, textColor, true), matchWrapMargin(16, 24, 16, 6));
        page.addView(text(subTitleText, 15, subTextColor, false), matchWrapMargin(16, 0, 16, 8));
        return page;
    }

    private ScrollView wrapScroll(LinearLayout page) {
        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(page);
        return scrollView;
    }

    private LinearLayout createPageContainer() {
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(0, dp(12), 0, dp(18));
        page.setBackgroundColor(bgColor);
        return page;
    }

    private TextView sectionTitle(String value) {
        TextView tv = text(value, 20, textColor, true);
        tv.setPadding(dp(16), dp(4), dp(16), 0);
        return tv;
    }

    private LinearLayout playItem(String icon, String title) {
        LinearLayout item = card(Color.WHITE, dp(18));
        item.setGravity(Gravity.CENTER);
        item.setPadding(dp(8), dp(16), dp(8), dp(16));
        item.addView(text(icon, 26, textColor, false));
        item.addView(space(6));
        item.addView(text(title, 13, textColor, true));
        return item;
    }

    private LinearLayout destinationCard(String city, String desc, String price) {
        LinearLayout card = card(Color.WHITE, dp(20));
        card.setPadding(dp(18), dp(18), dp(18), dp(18));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dp(150), dp(145));
        lp.setMargins(0, 0, dp(12), 0);
        card.setLayoutParams(lp);
        card.addView(text(city, 24, textColor, true));
        card.addView(space(6));
        card.addView(text(desc, 14, subTextColor, false));
        card.addView(space(22));
        card.addView(chip(price, Color.parseColor("#fff1eb"), primaryColor));
        return card;
    }

    private LinearLayout routeCard(String title, String desc, String tag) {
        LinearLayout card = card(cardColor, dp(18));
        card.setPadding(dp(18), dp(16), dp(18), dp(16));
        card.addView(text(title, 18, textColor, true));
        card.addView(space(6));
        card.addView(text(desc, 14, subTextColor, false));
        card.addView(space(12));
        card.addView(chip(tag, Color.parseColor("#fff1eb"), primaryColor));
        return card;
    }

    private LinearLayout emptyCard(String value) {
        LinearLayout card = card(Color.WHITE, dp(20));
        card.setGravity(Gravity.CENTER);
        card.setPadding(dp(20), dp(50), dp(20), dp(50));
        TextView tv = text(value, 16, subTextColor, false);
        tv.setGravity(Gravity.CENTER);
        card.addView(tv);
        return card;
    }

    private TextView chip(String value, int bg, int text) {
        TextView tv = text(value, 13, text, true);
        tv.setPadding(dp(12), dp(7), dp(12), dp(7));
        tv.setBackgroundColor(bg);
        return tv;
    }

    private LinearLayout card(int color, int minHeight) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setMinimumHeight(minHeight);
        card.setBackgroundColor(color);
        return card;
    }

    private TextView text(String value, int sp, int color, boolean bold) {
        TextView tv = new TextView(this);
        tv.setText(value);
        tv.setTextSize(sp);
        tv.setTextColor(color);
        tv.setIncludeFontPadding(true);
        if (bold) {
            tv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        }
        return tv;
    }

    private View space(int dp) {
        View view = new View(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(1, dp(dp)));
        return view;
    }

    private LinearLayout.LayoutParams matchWrapMargin(int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        lp.setMargins(dp(left), dp(top), dp(right), dp(bottom));
        return lp;
    }

    private LinearLayout.LayoutParams weightItem() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        lp.setMargins(dp(4), 0, dp(4), 0);
        return lp;
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }
}
