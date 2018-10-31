package com.rickhuisman.formula1app.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.ergast.models.Races;
import com.rickhuisman.formula1app.viewmodels.CalendarViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CalendarFragment extends Fragment {
    private View mView;

    private CalendarViewModel mNoteViewModel;
    private CalendarAdapter mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar = mView.findViewById(R.id.toolbar);
        setToolbarTitle(toolbar);

        RecyclerView recyclerView = mView.findViewById(R.id.recycler_view);
        setUpRecyclerView(recyclerView);

        mNoteViewModel = ViewModelProviders.of(this).get(CalendarViewModel.class);
        mNoteViewModel.getRaceSchedule().observe(this, scheduleDataObserver);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_calendar, container, false);
        return mView;
    }

    private void setToolbarTitle(Toolbar toolbar) {
        toolbar.setTitle("SCHEDULE");
    }

    private void setUpRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        mAdapter = new CalendarAdapter(getContext());

        recyclerView.setAdapter(mAdapter);
    }

    private Observer<Feed> scheduleDataObserver = new Observer<Feed>() {
        @Override
        public void onChanged(@Nullable Feed feed) {
            List<Races> races = feed.getMrData().getRaceTable().getRaces();

            mAdapter.setCalendar(races);
        }
    };
}