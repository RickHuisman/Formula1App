package com.rickhuisman.formula1app.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.RaceWithWinner;
import com.rickhuisman.formula1app.viewmodels.RaceScheduleViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleFragment extends Fragment {

    private View mView;
    private RaceScheduleAdapter mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView raceSchedule = mView.findViewById(R.id.race_schedule);
        raceSchedule.setLayoutManager(new LinearLayoutManager(getContext()));
        raceSchedule.setHasFixedSize(true);

        mAdapter = new RaceScheduleAdapter(getContext());
        raceSchedule.setAdapter(mAdapter);

        RaceScheduleViewModel raceScheduleViewModel = ViewModelProviders.of(this).get(RaceScheduleViewModel.class);
        raceScheduleViewModel.getRaceSchedule(2012).observe(this, raceScheduleObserver);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_schedule, container, false);
        return mView;
    }

    private Observer<List<RaceWithWinner>> raceScheduleObserver = new Observer<List<RaceWithWinner>>() {
        @Override
        public void onChanged(List<RaceWithWinner> raceWithWinner) {
            mAdapter.setSchedule(raceWithWinner);
        }
    };
}