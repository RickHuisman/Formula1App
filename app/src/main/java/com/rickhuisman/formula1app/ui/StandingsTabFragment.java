package com.rickhuisman.formula1app.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.ConstructorStandings;
import com.rickhuisman.formula1app.ergast.models.DriverStandings;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.ergast.models.StandingsLists;
import com.rickhuisman.formula1app.viewmodels.StandingsViewModel;

import java.util.List;

public class StandingsTabFragment extends Fragment {
    public static final int DRIVERS_STANDING_FRAGMENT = 0;
    public static final int CONSTRUCTORS_STANDING_FRAGMENT = 1;

    private View mView;
    private StandingsAdapter mStandingsAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int resultType = getArguments().getInt("resultType");

        RecyclerView standingsList = mView.findViewById(R.id.race_result_recycler_view);
        standingsList.setLayoutManager(new LinearLayoutManager(getContext()));
        standingsList.setHasFixedSize(true);
        StandingsViewModel standingsViewModel = ViewModelProviders.of(this).get(StandingsViewModel.class);

        mStandingsAdapter = new StandingsAdapter(resultType);
        standingsList.setAdapter(mStandingsAdapter);

        if (resultType == DRIVERS_STANDING_FRAGMENT) {
            standingsViewModel.getDriverStandings().observe(this, driverStandingsDataObserver);
        } else if (resultType == CONSTRUCTORS_STANDING_FRAGMENT) {
            standingsViewModel.getConstuctorStandings().observe(this, constructorStandingsDataObserver);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_result, container, false);
        return mView;
    }

    private Observer<Feed> driverStandingsDataObserver = new Observer<Feed>() {
        @Override
        public void onChanged(@Nullable Feed feed) {
            List<StandingsLists> standings = feed.getMrData().getStandingsTable().getStandingsLists();

            mStandingsAdapter.setStandings(standings);
        }
    };

    private Observer<Feed> constructorStandingsDataObserver = new Observer<Feed>() {
        @Override
        public void onChanged(@Nullable Feed feed) {
            List<StandingsLists> standings = feed.getMrData().getStandingsTable().getStandingsLists();

            mStandingsAdapter.setStandings(standings);
        }
    };
}
