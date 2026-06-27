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
        page.setPadding(0, 0, 0, UiKit.dp(context, 22));

        page.addView(createProfileHeader(), UiKit.matchWrapMargin(context, 16, 18, 16, 12));
        page.addView(createVipCard(), UiKit.matchWrapMargin(context, 16, 0, 16, 12));

        page.addView(UiKit.sectionTitle(context, "我的订单"));
        page.addView(createOrderShortcutCard(), UiKit.matchWrapMargin(context, 16, 8, 16, 16));

        page.addView(UiKit.sectionTitle(context, "常用功能"));
        page.addView(createToolGrid(new String[][]{
                {"⭐", "我的收藏"},
                {"🎫", "优惠券"},
                {"📍", "常用旅客"},
                {"🧾", "发票抬头"},
                {"🗺", "旅行足迹"},
                {"💬", "消息中心"},
                {"🎁", "邀请有礼"},
                {"⚙", "设置"}
        }), UiKit.matchWrapMargin(context, 16, 8, 16, 16));

        page.addView(UiKit.sectionTitle(context, "服务中心"));
        page.addView(menuRow("🎧", "在线客服", "7x24小时为你解决出行问题"), UiKit.matchWrapMargin(context, 16, 8, 16, 1));
        page.addView(menuRow("🛡", "出行保障", "退改保障、保险服务、应急帮助"), UiKit.matchWrapMargin(context, 16, 0, 16, 1));
        page.addView(menuRow("📖", "旅行攻略", "查看目的地攻略和避坑指南"), UiKit.matchWrapMargin(context, 16, 0, 16, 1));
        page.addView(menuRow("📞", "意见反馈", "帮助我们把去趣做得更好"), UiKit.matchWrapMargin(context, 16, 0, 16, 18));

        return UiKit.scrollPage(context, page);
    }

    private View createProfileHeader() {
        LinearLayout header = UiKit.card(context, UiKit.PRIMARY_COLOR, 150);
        header.setPadding(UiKit.dp(context, 18), UiKit.dp(context, 20), UiKit.dp(context, 18), UiKit.dp(context, 18));

        LinearLayout topRow = new LinearLayout(context);
        topRow.setOrientation(HORIZONTAL);
        topRow.setGravity(Gravity.CENTER_VERTICAL);

        TextView avatar = UiKit.text(context, "趣", 24, UiKit.PRIMARY_COLOR, true);
        avatar.setGravity(Gravity.CENTER);
        avatar.setBackgroundColor(Color.WHITE);
        topRow.addView(avatar, new LinearLayout.LayoutParams(UiKit.dp(context, 58), UiKit.dp(context, 58)));

        LinearLayout userInfo = new LinearLayout(context);
        userInfo.setOrientation(VERTICAL);
        userInfo.setPadding(UiKit.dp(context, 14), 0, 0, 0);
        userInfo.addView(UiKit.text(context, "去趣旅行家", 22, Color.WHITE, true));
        userInfo.addView(UiKit.space(context, 4));
        userInfo.addView(UiKit.text(context, "点击完善资料 · 解锁更多旅行权益", 13, Color.WHITE, false));
        topRow.addView(userInfo, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        TextView edit = UiKit.chip(context, "编辑", Color.WHITE, UiKit.PRIMARY_COLOR);
        topRow.addView(edit);
        header.addView(topRow);

        header.addView(UiKit.space(context, 18));

        LinearLayout statRow = new LinearLayout(context);
        statRow.setOrientation(HORIZONTAL);
        statRow.addView(profileStat("12", "收藏"), UiKit.weightNoMargin());
        statRow.addView(profileStat("3", "足迹"), UiKit.weightNoMargin());
        statRow.addView(profileStat("5", "优惠券"), UiKit.weightNoMargin());
        statRow.addView(profileStat("Lv.2", "会员"), UiKit.weightNoMargin());
        header.addView(statRow);

        return header;
    }

    private View createVipCard() {
        LinearLayout vip = UiKit.card(context, Color.parseColor("#2b2b2b"), 72);
        vip.setPadding(UiKit.dp(context, 18), UiKit.dp(context, 14), UiKit.dp(context, 18), UiKit.dp(context, 14));
        vip.addView(UiKit.text(context, "黑金会员 · 专属旅行权益", 17, Color.parseColor("#ffd89b"), true));
        vip.addView(UiKit.space(context, 6));
        vip.addView(UiKit.text(context, "酒店折扣、专属客服、景点快速入园", 13, Color.WHITE, false));
        return vip;
    }

    private View createOrderShortcutCard() {
        LinearLayout card = UiKit.card(context, Color.WHITE, 96);
        card.setPadding(UiKit.dp(context, 8), UiKit.dp(context, 14), UiKit.dp(context, 8), UiKit.dp(context, 14));
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(HORIZONTAL);
        row.addView(orderShortcut("💳", "待支付"), UiKit.weightNoMargin());
        row.addView(orderShortcut("🧳", "待出行"), UiKit.weightNoMargin());
        row.addView(orderShortcut("✍", "待评价"), UiKit.weightNoMargin());
        row.addView(orderShortcut("↩", "退款/售后"), UiKit.weightNoMargin());
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

    private View profileStat(String value, String label) {
        LinearLayout item = new LinearLayout(context);
        item.setOrientation(VERTICAL);
        item.setGravity(Gravity.CENTER);
        item.addView(UiKit.text(context, value, 18, Color.WHITE, true));
        item.addView(UiKit.text(context, label, 12, Color.WHITE, false));
        return item;
    }

    private View orderShortcut(String icon, String label) {
        LinearLayout item = new LinearLayout(context);
        item.setOrientation(VERTICAL);
        item.setGravity(Gravity.CENTER);
        item.addView(UiKit.text(context, icon, 24, UiKit.TEXT_COLOR, false));
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
