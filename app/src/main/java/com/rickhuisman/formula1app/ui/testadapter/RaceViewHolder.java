package com.rickhuisman.formula1app.ui.testadapter;

import android.view.View;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class RaceViewHolder extends GroupViewHolder {

    private TextView textViewRaceName;

    public RaceViewHolder(View itemView) {
        super(itemView);
        textViewRaceName = itemView.findViewById(R.id.race_name_text_view);
    }

    public void setRaceName(ExpandableGroup group) {
        textViewRaceName.setText(group.getTitle());
    }
}
