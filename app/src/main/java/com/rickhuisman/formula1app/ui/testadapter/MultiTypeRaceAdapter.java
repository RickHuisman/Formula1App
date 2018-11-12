package com.rickhuisman.formula1app.ui.testadapter;

import android.view.ViewGroup;

import com.rickhuisman.formula1app.ergast.models.Races;
import com.thoughtbot.expandablerecyclerview.MultiTypeExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MultiTypeRaceAdapter extends MultiTypeExpandableRecyclerViewAdapter<GroupViewHolder, DriversViewHolder> {
    public static final int PAST_RACE_VIEW_TYPE = 3;
    public static final int NEXT_RACE_VIEW_TYPE = 4;

    private List<Races> mRaceSchedule;

    public MultiTypeRaceAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
        mRaceSchedule = groups.get(0).getItems();
    }

    @Override
    public int getGroupViewType(int position, ExpandableGroup group) {
//        if (((Genre) group).getItems().get(childIndex).isFavorite()) {
//            return FAVORITE_VIEW_TYPE;
//        } else {
//            return ARTIST_VIEW_TYPE;
//        }
    }

    @Override
    public GroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public DriversViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindChildViewHolder(DriversViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {

    }

    @Override
    public void onBindGroupViewHolder(GroupViewHolder holder, int flatPosition, ExpandableGroup group) {

    }

    private int getNextRound() {
        int nextRound = 0;
        try {
            for (int i = 0; i < mRaceSchedule.size(); i++) {
                Date raceDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
                        mRaceSchedule.get(i).getDate() + " " + mRaceSchedule.get(i).getTime());

                java.util.Calendar cal = java.util.Calendar.getInstance();
                Date currentDate = cal.getTime();

                int result = raceDate.compareTo(currentDate);

                if (result > 0) {
                    nextRound = Integer.valueOf(mRaceSchedule.get(i).getRound());
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nextRound;
    }
}
