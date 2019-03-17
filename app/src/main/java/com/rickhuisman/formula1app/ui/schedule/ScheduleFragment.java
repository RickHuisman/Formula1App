package com.rickhuisman.formula1app.ui.schedule;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.Race;
import com.rickhuisman.formula1app.ergast.db.entities.RaceWithWinner;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.ergast.models.Races;
import com.rickhuisman.formula1app.viewmodels.RaceScheduleViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleFragment extends Fragment {

    private View mView;
    private RaceScheduleAdapter mAdapter;
    private RaceScheduleViewModel mRaceScheduleViewModel;
    private MenuItem oldSelectedItem = null;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar = mView.findViewById(R.id.toolbar);
        toolbar.setTitle("SCHEDULE");

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        RecyclerView raceSchedule = mView.findViewById(R.id.race_schedule);
        raceSchedule.setLayoutManager(new LinearLayoutManager(getContext()));
        raceSchedule.setHasFixedSize(true);

        mAdapter = new RaceScheduleAdapter(getContext());
        raceSchedule.setAdapter(mAdapter);

        mRaceScheduleViewModel = ViewModelProviders.of(this).get(RaceScheduleViewModel.class);
        mRaceScheduleViewModel.getRaceSchedule(2018).observe(this, raceScheduleObserver);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mView = inflater.inflate(R.layout.fragment_schedule, container, false);
        return mView;
    }

    private Observer<Feed> raceScheduleObserver = new Observer<Feed>() {
        @Override
        public void onChanged(@Nullable Feed feed) {
            mAdapter.setSchedule(feed.getMrData().getRaceTable().getRaces());
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_schedule, menu);

        int seasons = 2019 - 1950;
        for (int i = 0; i <= seasons; i++) {
            menu.getItem(0).getSubMenu().add(Menu.NONE, 1950 + i, Menu.NONE, "Season " + String.valueOf(1950 + i));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (!item.hasSubMenu()) {
            int season = item.getItemId();

            if (oldSelectedItem != null) {
                SpannableString s = new SpannableString(oldSelectedItem.getTitle());
                s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), 0);
                oldSelectedItem.setTitle(s);
            }

            SpannableString s = new SpannableString(item.getTitle());
            s.setSpan(new ForegroundColorSpan(Color.parseColor("#66FFFFFF")), 0, s.length(), 0);
            item.setTitle(s);

            oldSelectedItem = item;

            mRaceScheduleViewModel.getRaceSchedule(season).observe(this, raceScheduleObserver);

            setUpToolbar(season);
        }
        return true;
    }

    private void setUpToolbar(int season) {
        Toolbar toolbar = mView.findViewById(R.id.toolbar);

        if (season == 2019) {
            toolbar.setTitle("SCHEDULE");
        } else {
            SpannableString s = new SpannableString("SCHEDULE" + " • " + season);
            s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            s.setSpan(new ForegroundColorSpan(Color.parseColor("#66FFFFFF")), 9, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            toolbar.setTitle(s);
        }
    }
}