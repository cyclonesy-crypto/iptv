package com.cyclonesy.ququtravel;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyclonesy.ququtravel.pages.DestinationPage;
import com.cyclonesy.ququtravel.pages.EnhancedHomePage;
import com.cyclonesy.ququtravel.pages.MinePage;
import com.cyclonesy.ququtravel.pages.OrderPage;

public class EnhancedMainActivity extends Activity {

    private FrameLayout contentContainer;
    private TextView[] tabViews;
    private final String[] tabTitles = {"首页", "目的地", "订单", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createRootView());
        switchTab(0);
    }

    private View createRootView() {
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(UiKit.BG_COLOR);

        contentContainer = new FrameLayout(this);
        root.addView(contentContainer, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1));
        root.addView(createBottomTabs(), new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, UiKit.dp(this, 64)));
        return root;
    }

    private View createBottomTabs() {
        LinearLayout tabs = new LinearLayout(this);
        tabs.setOrientation(LinearLayout.HORIZONTAL);
        tabs.setGravity(Gravity.CENTER);
        tabs.setBackgroundColor(Color.WHITE);
        tabs.setPadding(0, UiKit.dp(this, 6), 0, UiKit.dp(this, 6));

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
            tabViews[i].setTextColor(i == index ? UiKit.PRIMARY_COLOR : UiKit.SUB_TEXT_COLOR);
            tabViews[i].setTypeface(Typeface.DEFAULT, i == index ? Typeface.BOLD : Typeface.NORMAL);
        }
        contentContainer.addView(createPage(index));
    }

    private View createPage(int index) {
        if (index == 0) return new EnhancedHomePage(this);
        if (index == 1) return new DestinationPage(this);
        if (index == 2) return new OrderPage(this);
        return new MinePage(this);
    }
}
