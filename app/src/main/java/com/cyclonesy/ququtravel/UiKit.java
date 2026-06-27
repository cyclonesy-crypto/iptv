package com.cyclonesy.ququtravel;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public final class UiKit {

    public static final int PRIMARY_COLOR = Color.parseColor("#ff6f3c");
    public static final int TEXT_COLOR = Color.parseColor("#222222");
    public static final int SUB_TEXT_COLOR = Color.parseColor("#777777");
    public static final int BG_COLOR = Color.parseColor("#f7f8fa");
    public static final int CARD_COLOR = Color.WHITE;

    private UiKit() {
    }

    public static LinearLayout pageContainer(Context context) {
        LinearLayout page = new LinearLayout(context);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(0, dp(context, 12), 0, dp(context, 18));
        page.setBackgroundColor(BG_COLOR);
        return page;
    }

    public static ScrollView scrollPage(Context context, LinearLayout page) {
        ScrollView scrollView = new ScrollView(context);
        scrollView.addView(page);
        return scrollView;
    }

    public static LinearLayout simplePage(Context context, String titleText, String subTitleText) {
        LinearLayout page = pageContainer(context);
        page.addView(text(context, titleText, 28, TEXT_COLOR, true), matchWrapMargin(context, 16, 24, 16, 6));
        page.addView(text(context, subTitleText, 15, SUB_TEXT_COLOR, false), matchWrapMargin(context, 16, 0, 16, 8));
        return page;
    }

    public static TextView sectionTitle(Context context, String value) {
        TextView tv = text(context, value, 20, TEXT_COLOR, true);
        tv.setPadding(dp(context, 16), dp(context, 4), dp(context, 16), 0);
        return tv;
    }

    public static LinearLayout card(Context context, int color, int minHeightDp) {
        LinearLayout card = new LinearLayout(context);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setMinimumHeight(dp(context, minHeightDp));
        card.setBackgroundColor(color);
        return card;
    }

    public static LinearLayout routeCard(Context context, String title, String desc, String tag) {
        LinearLayout card = card(context, CARD_COLOR, 18);
        card.setPadding(dp(context, 18), dp(context, 16), dp(context, 18), dp(context, 16));
        card.addView(text(context, title, 18, TEXT_COLOR, true));
        card.addView(space(context, 6));
        card.addView(text(context, desc, 14, SUB_TEXT_COLOR, false));
        card.addView(space(context, 12));
        card.addView(chip(context, tag, Color.parseColor("#fff1eb"), PRIMARY_COLOR));
        return card;
    }

    public static LinearLayout emptyCard(Context context, String value) {
        LinearLayout card = card(context, Color.WHITE, 20);
        card.setGravity(Gravity.CENTER);
        card.setPadding(dp(context, 20), dp(context, 50), dp(context, 20), dp(context, 50));
        TextView tv = text(context, value, 16, SUB_TEXT_COLOR, false);
        tv.setGravity(Gravity.CENTER);
        card.addView(tv);
        return card;
    }

    public static TextView chip(Context context, String value, int bg, int textColor) {
        TextView tv = text(context, value, 13, textColor, true);
        tv.setPadding(dp(context, 12), dp(context, 7), dp(context, 12), dp(context, 7));
        tv.setBackgroundColor(bg);
        return tv;
    }

    public static TextView text(Context context, String value, int sp, int color, boolean bold) {
        TextView tv = new TextView(context);
        tv.setText(value);
        tv.setTextSize(sp);
        tv.setTextColor(color);
        tv.setIncludeFontPadding(true);
        if (bold) {
            tv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        }
        return tv;
    }

    public static View space(Context context, int heightDp) {
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams(1, dp(context, heightDp)));
        return view;
    }

    public static LinearLayout.LayoutParams matchWrapMargin(Context context, int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        lp.setMargins(dp(context, left), dp(context, top), dp(context, right), dp(context, bottom));
        return lp;
    }

    public static LinearLayout.LayoutParams weightItem(Context context) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        lp.setMargins(dp(context, 4), 0, dp(context, 4), 0);
        return lp;
    }

    public static LinearLayout.LayoutParams weightNoMargin() {
        return new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
    }

    public static int dp(Context context, int value) {
        return (int) (value * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
