package com.rickhuisman.formula1app.ui.testadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Driver;
import com.rickhuisman.formula1app.ergast.models.Races;
import com.rickhuisman.formula1app.ergast.models.Results;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.MultiTypeExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends MultiTypeExpandableRecyclerViewAdapter<RaceViewHolder, DriversViewHolder> {
    public TestAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public RaceViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_race, parent, false);
        return new RaceViewHolder(v);
    }

    @Override
    public DriversViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
        return new DriversViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(DriversViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        Races race = (Races) group.getItems().get(childIndex);

        List<Driver> drivers = new ArrayList<>();

        for (Results result: race.getResults()) {
            drivers.add(result.getDriver());
        }

        holder.onBind(drivers);
    }

    @Override
    public void onBindGroupViewHolder(RaceViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setRaceName(group);
    }
}