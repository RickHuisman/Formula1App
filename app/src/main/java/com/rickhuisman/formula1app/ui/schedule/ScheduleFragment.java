package com.rickhuisman.formula1app.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.test.db.entities.RaceWithWinner;
import com.rickhuisman.formula1app.viewmodels.ScheduleViewModel;

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
    private ScheduleAdapter mAdapter;
    private ScheduleViewModel mRaceScheduleViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar = mView.findViewById(R.id.toolbar);
        toolbar.setTitle("SCHEDULE");

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        RecyclerView raceList = mView.findViewById(R.id.recycler_view);
        raceList.setLayoutManager(new LinearLayoutManager(getContext()));
        raceList.setHasFixedSize(true);

        mAdapter = new ScheduleAdapter(getContext());
        raceList.setAdapter(mAdapter);

        mRaceScheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        mRaceScheduleViewModel.getRaceAndWinners(2018).observe(this, raceScheduleWithWinners);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_schedule, container, false);
        return mView;
    }

    private Observer<List<RaceWithWinner>> raceScheduleWithWinners = new Observer<List<RaceWithWinner>>() {
        @Override
        public void onChanged(List<RaceWithWinner> raceWithWinner) {
            mAdapter.setSchedule(raceWithWinner);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        int seasons = 2018 - 1950;
        for (int i = 0; i <= seasons; i++) {
            menu.add(Menu.NONE, 1950 + i, Menu.NONE, "Season " + String.valueOf(1950 + i));
        }
        inflater.inflate(R.menu.menu_schedule, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int season = item.getItemId();

        mRaceScheduleViewModel.getRaceAndWinners(season).observe(this, raceScheduleWithWinners);
        return true;
    }
}