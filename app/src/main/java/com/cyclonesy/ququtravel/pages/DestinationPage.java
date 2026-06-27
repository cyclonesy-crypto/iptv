package com.cyclonesy.ququtravel.pages;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.cyclonesy.ququtravel.UiKit;

public class DestinationPage extends LinearLayout {

    private final Context context;

    public DestinationPage(Context context) {
        super(context);
        this.context = context;
        setOrientation(VERTICAL);
        addView(createContent());
    }

    private View createContent() {
        LinearLayout page = UiKit.simplePage(context, "目的地", "选择一个城市，开始你的旅行计划");
        page.addView(UiKit.routeCard(context, "云南", "大理 · 丽江 · 香格里拉", "热门"), UiKit.matchWrapMargin(context, 16, 16, 16, 10));
        page.addView(UiKit.routeCard(context, "海南", "三亚 · 万宁 · 海口", "海岛"), UiKit.matchWrapMargin(context, 16, 0, 16, 10));
        page.addView(UiKit.routeCard(context, "四川", "成都 · 九寨沟 · 稻城", "美食"), UiKit.matchWrapMargin(context, 16, 0, 16, 10));
        return UiKit.scrollPage(context, page);
    }
}
