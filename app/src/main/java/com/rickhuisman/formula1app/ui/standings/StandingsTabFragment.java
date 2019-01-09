package com.rickhuisman.formula1app.ui.standings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.ergast.models.StandingsLists;
import com.rickhuisman.formula1app.ergast.test.db.entities.DriverStanding;
import com.rickhuisman.formula1app.ergast.test.db.entities.DriverStandingsWithDriver;
import com.rickhuisman.formula1app.viewmodels.StandingsViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StandingsTabFragment extends Fragment {
    public static final int DRIVERS_STANDING_FRAGMENT = 0;
    public static final int CONSTRUCTORS_STANDING_FRAGMENT = 1;

    private View mView;
    private DriverStandingsAdapter mStandingsAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int resultType = getArguments().getInt("resultType");

        RecyclerView standingsList = mView.findViewById(R.id.race_result_recycler_view);
        standingsList.setLayoutManager(new LinearLayoutManager(getContext()));
        standingsList.setHasFixedSize(true);
        StandingsViewModel standingsViewModel = ViewModelProviders.of(this).get(StandingsViewModel.class);

        mStandingsAdapter = new DriverStandingsAdapter();
        standingsList.setAdapter(mStandingsAdapter);

        if (resultType == DRIVERS_STANDING_FRAGMENT) {
            standingsViewModel.getDriverStandingsWithDriver(1009).observe(this, driverStandingsDataObserver);
        } else if (resultType == CONSTRUCTORS_STANDING_FRAGMENT) {
//            standingsViewModel.getConstuctorStandings().observe(this, constructorStandingsDataObserver);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_result, container, false);
        return mView;
    }

    private Observer<List<DriverStandingsWithDriver>> driverStandingsDataObserver = new Observer<List<DriverStandingsWithDriver>>() {
        @Override
        public void onChanged(List<DriverStandingsWithDriver> standings) {
            mStandingsAdapter.setStandings(standings);
        }
    };
}
