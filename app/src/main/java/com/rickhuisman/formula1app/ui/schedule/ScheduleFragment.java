package com.rickhuisman.formula1app.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.ergast.models.Races;
import com.rickhuisman.formula1app.viewmodels.ScheduleViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleFragment extends Fragment {

    private View mView;
    private ScheduleAdapter mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar = mView.findViewById(R.id.toolbar);
        toolbar.setTitle("SCHEDULE");

        RecyclerView raceList = mView.findViewById(R.id.recycler_view);
        raceList.setLayoutManager(new LinearLayoutManager(getContext()));
        raceList.setHasFixedSize(true);

        mAdapter = new ScheduleAdapter(getContext());
        raceList.setAdapter(mAdapter);

        ScheduleViewModel raceScheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        raceScheduleViewModel.getRaceSchedule().observe(this, scheduleDataObserver);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_schedule, container, false);
        return mView;
    }

    private Observer<Feed> scheduleDataObserver = new Observer<Feed>() {
        @Override
        public void onChanged(@Nullable Feed feed) {
            List<Races> races = feed.getMrData().getRaceTable().getRaces();

            mAdapter.setSchedule(races);
        }
    };
}