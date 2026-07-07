package com.cyclonesy.ququtravel.pages;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyclonesy.ququtravel.UiKit;

public class MinePage extends LinearLayout {

    private final Context context;

    public MinePage(Context context) {
        super(context);
        this.context = context;
        setOrientation(VERTICAL);
        addView(createContent());
    }

    private View createContent() {
        LinearLayout page = UiKit.pageContainer(context);
        page.setPadding(0, UiKit.dp(context, 16), 0, UiKit.dp(context, 22));

        page.addView(createGuestHeader(), UiKit.matchWrapMargin(context, 16, 8, 16, 12));
        page.addView(createBenefitCard(), UiKit.matchWrapMargin(context, 16, 0, 16, 16));

        page.addView(UiKit.sectionTitle(context, "我的旅行资产"));
        page.addView(createAssetBoard(), UiKit.matchWrapMargin(context, 16, 8, 16, 16));

        page.addView(UiKit.sectionTitle(context, "常用功能"));
        page.addView(createToolGrid(new String[][]{
                {"⭐", "收藏夹"},
                {"🗺", "浏览足迹"},
                {"🎫", "优惠券"},
                {"📍", "常用旅客"},
                {"🧾", "发票抬头"},
                {"💬", "消息中心"},
                {"🎁", "邀请有礼"},
                {"⚙", "设置"}
        }), UiKit.matchWrapMargin(context, 16, 8, 16, 16));

        page.addView(UiKit.sectionTitle(context, "服务中心"));
        page.addView(menuRow("🎧", "在线客服", "不用登录也可以咨询旅行问题"), UiKit.matchWrapMargin(context, 16, 8, 16, 1));
        page.addView(menuRow("🛡", "出行保障", "查看退改、保险、应急帮助"), UiKit.matchWrapMargin(context, 16, 0, 16, 1));
        page.addView(menuRow("📖", "攻略中心", "目的地玩法、避坑指南、路线建议"), UiKit.matchWrapMargin(context, 16, 0, 16, 1));
        page.addView(menuRow("📞", "意见反馈", "告诉我们你想要的旅行功能"), UiKit.matchWrapMargin(context, 16, 0, 16, 18));

        return UiKit.scrollPage(context, page);
    }

    private View createGuestHeader() {
        LinearLayout header = UiKit.card(context, Color.parseColor("#2b2b2b"), 164);
        header.setPadding(UiKit.dp(context, 18), UiKit.dp(context, 20), UiKit.dp(context, 18), UiKit.dp(context, 18));

        LinearLayout topRow = new LinearLayout(context);
        topRow.setOrientation(HORIZONTAL);
        topRow.setGravity(Gravity.CENTER_VERTICAL);

        TextView avatar = UiKit.text(context, "趣", 26, Color.parseColor("#2b2b2b"), true);
        avatar.setGravity(Gravity.CENTER);
        avatar.setBackgroundColor(Color.parseColor("#ffd89b"));
        topRow.addView(avatar, new LinearLayout.LayoutParams(UiKit.dp(context, 60), UiKit.dp(context, 60)));

        LinearLayout userInfo = new LinearLayout(context);
        userInfo.setOrientation(VERTICAL);
        userInfo.setPadding(UiKit.dp(context, 14), 0, 0, 0);
        userInfo.addView(UiKit.text(context, "游客旅行家", 22, Color.WHITE, true));
        userInfo.addView(UiKit.space(context, 4));
        userInfo.addView(UiKit.text(context, "先逛逛也可以，喜欢的路线可稍后保存", 13, Color.WHITE, false));
        topRow.addView(userInfo, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        header.addView(topRow);
        header.addView(UiKit.space(context, 18));
        header.addView(UiKit.chip(context, "开启去趣体验  →", UiKit.PRIMARY_COLOR, Color.WHITE));
        return header;
    }

    private View createBenefitCard() {
        LinearLayout card = UiKit.card(context, Color.parseColor("#fff7e8"), 92);
        card.setPadding(UiKit.dp(context, 18), UiKit.dp(context, 16), UiKit.dp(context, 18), UiKit.dp(context, 16));
        card.addView(UiKit.text(context, "去趣会员权益", 18, UiKit.TEXT_COLOR, true));
        card.addView(UiKit.space(context, 8));
        card.addView(UiKit.text(context, "酒店折扣 · 专属客服 · 景点快速入园 · 生日旅行券", 13, UiKit.SUB_TEXT_COLOR, false));
        return card;
    }

    private View createAssetBoard() {
        LinearLayout card = UiKit.card(context, Color.WHITE, 112);
        card.setPadding(UiKit.dp(context, 8), UiKit.dp(context, 16), UiKit.dp(context, 8), UiKit.dp(context, 16));
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(HORIZONTAL);
        row.addView(assetItem("12", "收藏"), UiKit.weightNoMargin());
        row.addView(assetItem("3", "足迹"), UiKit.weightNoMargin());
        row.addView(assetItem("5", "优惠券"), UiKit.weightNoMargin());
        row.addView(assetItem("Lv.0", "游客"), UiKit.weightNoMargin());
        card.addView(row);
        return card;
    }

    private View createToolGrid(String[][] tools) {
        LinearLayout card = UiKit.card(context, Color.WHITE, 160);
        card.setPadding(UiKit.dp(context, 8), UiKit.dp(context, 16), UiKit.dp(context, 8), UiKit.dp(context, 12));

        LinearLayout row1 = new LinearLayout(context);
        row1.setOrientation(HORIZONTAL);
        LinearLayout row2 = new LinearLayout(context);
        row2.setOrientation(HORIZONTAL);

        for (int i = 0; i < tools.length; i++) {
            View item = toolItem(tools[i][0], tools[i][1]);
            if (i < 4) {
                row1.addView(item, UiKit.weightNoMargin());
            } else {
                row2.addView(item, UiKit.weightNoMargin());
            }
        }

        card.addView(row1);
        card.addView(UiKit.space(context, 12));
        card.addView(row2);
        return card;
    }

    private View assetItem(String value, String label) {
        LinearLayout item = new LinearLayout(context);
        item.setOrientation(VERTICAL);
        item.setGravity(Gravity.CENTER);
        item.addView(UiKit.text(context, value, 22, UiKit.PRIMARY_COLOR, true));
        item.addView(UiKit.space(context, 6));
        item.addView(UiKit.text(context, label, 13, UiKit.TEXT_COLOR, false));
        return item;
    }

    private View toolItem(String icon, String label) {
        LinearLayout item = new LinearLayout(context);
        item.setOrientation(VERTICAL);
        item.setGravity(Gravity.CENTER);
        item.addView(UiKit.text(context, icon, 24, UiKit.TEXT_COLOR, false));
        item.addView(UiKit.space(context, 6));
        item.addView(UiKit.text(context, label, 13, UiKit.TEXT_COLOR, false));
        return item;
    }

    private View menuRow(String icon, String title, String desc) {
        LinearLayout row = UiKit.card(context, Color.WHITE, 70);
        row.setOrientation(HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(UiKit.dp(context, 16), UiKit.dp(context, 12), UiKit.dp(context, 16), UiKit.dp(context, 12));

        TextView iconView = UiKit.text(context, icon, 24, UiKit.TEXT_COLOR, false);
        iconView.setGravity(Gravity.CENTER);
        row.addView(iconView, new LinearLayout.LayoutParams(UiKit.dp(context, 40), UiKit.dp(context, 40)));

        LinearLayout info = new LinearLayout(context);
        info.setOrientation(VERTICAL);
        info.setPadding(UiKit.dp(context, 12), 0, 0, 0);
        info.addView(UiKit.text(context, title, 16, UiKit.TEXT_COLOR, true));
        info.addView(UiKit.space(context, 3));
        info.addView(UiKit.text(context, desc, 12, UiKit.SUB_TEXT_COLOR, false));
        row.addView(info, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        row.addView(UiKit.text(context, "›", 26, UiKit.SUB_TEXT_COLOR, false));
        return row;
    }
}
