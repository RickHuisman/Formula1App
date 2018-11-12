package com.rickhuisman.formula1app.ui.testadapter;

import com.rickhuisman.formula1app.ergast.models.Races;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class TopDrivers extends ExpandableGroup<Races> {

    public TopDrivers(String title, List<Races> items) {
        super(title, items);
    }
}
