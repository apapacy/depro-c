package com.example.ide;

import com.dpcsa.compon.base.DeclareScreens;
import com.dpcsa.compon.interfaces_classes.Menu;

public class MyDeclare extends DeclareScreens {

    public final static String
        MAIN = "MAIN", NEWS = "NEWS", EVENTS = "EVENTS";

    @Override
    public void declare() {

        activity(MAIN, R.layout.activity_main)
            .toolBar(R.id.tool_bar)
            .fragmentsContainer(R.id.container_fragm)
            .menuBottom(model(menuMainMenu_b), view(R.id.menu_b));

        fragment(NEWS, R.layout.fragment_news, R.string.news_screen_title)
            .component(TC.RECYCLER, model("depro/cron/news"),
                view(R.id.list, R.layout.item_news_list_0));

        fragment(EVENTS, R.layout.fragment_events, R.string.events_screen_title)
            .component(TC.RECYCLER, model("depro/cron/events"),
                view(R.id.list, R.layout.item_events_list_0));

    }

    Menu menuMainMenu_b = new Menu(R.color.accent, R.color.color_104)
        .item(R.drawable.icon_menu_news, R.string.main_menu_b_0, NEWS, true)
        .item(R.drawable.icon_menu_settings, R.string.main_menu_b_1, EVENTS);

}
