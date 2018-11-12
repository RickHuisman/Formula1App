package com.rickhuisman.formula1app.ui.testadapter;

import android.view.View;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Driver;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import java.util.List;

public class DriversViewHolder extends ChildViewHolder {

    private TextView textViewDriverName;

    public DriversViewHolder(View itemView) {
        super(itemView);
        textViewDriverName = itemView.findViewById(R.id.text_view_driver_name);
    }

    public void onBind(List<Driver> drivers) {
        textViewDriverName.setText(drivers.get(0).getGivenName());
    }
}