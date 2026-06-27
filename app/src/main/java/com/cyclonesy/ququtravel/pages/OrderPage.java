package com.cyclonesy.ququtravel.pages;

import android.content.Context;
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
        LinearLayout page = UiKit.simplePage(context, "订单", "这里展示待出行、待支付、已完成的订单");
        page.addView(UiKit.emptyCard(context, "暂无旅行订单\n下单后会在这里展示行程信息"), UiKit.matchWrapMargin(context, 16, 28, 16, 10));
        return UiKit.scrollPage(context, page);
    }
}
