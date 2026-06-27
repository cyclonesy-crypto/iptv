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
import com.cyclonesy.ququtravel.pages.HomePage;
import com.cyclonesy.ququtravel.pages.MinePage;
import com.cyclonesy.ququtravel.pages.OrderPage;

public class MainActivity extends Activity {

    private FrameLayout contentContainer;
    private TextView[] tabViews;
    private final String[] tabTitles = {"首页", "目的地", "订单", "我的"};

    private final int primaryColor = UiKit.PRIMARY_COLOR;
    private final int subTextColor = UiKit.SUB_TEXT_COLOR;
    private final int bgColor = UiKit.BG_COLOR;

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
                UiKit.dp(this, 64)
        ));

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
        updateTabState(index);
        contentContainer.addView(createTabPage(index));
    }

    private void updateTabState(int selectedIndex) {
        for (int i = 0; i < tabViews.length; i++) {
            tabViews[i].setTextColor(i == selectedIndex ? primaryColor : subTextColor);
            tabViews[i].setTypeface(Typeface.DEFAULT, i == selectedIndex ? Typeface.BOLD : Typeface.NORMAL);
        }
    }

    private View createTabPage(int index) {
        if (index == 0) {
            return new HomePage(this);
        } else if (index == 1) {
            return new DestinationPage(this);
        } else if (index == 2) {
            return new OrderPage(this);
        } else {
            return new MinePage(this);
        }
    }
}
