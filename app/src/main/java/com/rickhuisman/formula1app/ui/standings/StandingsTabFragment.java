package com.rickhuisman.formula1app.ui.standings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.viewmodels.StandingsViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StandingsTabFragment extends Fragment {

    private static final int DRIVER_STANDINGS = 0;
    private static final int CONSTRUCTOR_STANDINGS = 1;

    private View mView;

    private DriverStandingsAdapter mDriversStandingsAdapter;
    private ConstructorStandingsAdapter mConstructorStandingsAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int standingsType = getArguments().getInt("standingsType");

        RecyclerView standingsList = mView.findViewById(R.id.standings);
        standingsList.setLayoutManager(new LinearLayoutManager(getContext()));
        standingsList.setHasFixedSize(true);

        StandingsViewModel standingsViewModel = ViewModelProviders.of(this).get(StandingsViewModel.class);

        if (standingsType == DRIVER_STANDINGS) {
            mDriversStandingsAdapter = new DriverStandingsAdapter(getContext());
            standingsList.setAdapter(mDriversStandingsAdapter);

            standingsViewModel.getDriverStandings().observe(this, driverStandingsObserver);
        } else if (standingsType == CONSTRUCTOR_STANDINGS) {
            mConstructorStandingsAdapter = new ConstructorStandingsAdapter(getContext());
            standingsList.setAdapter(mConstructorStandingsAdapter);

            standingsViewModel.getConstructorStandings().observe(this, constructorStandingsObserver);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_result, container, false);
        return mView;
    }

    private Observer<Feed> driverStandingsObserver = new Observer<Feed>() {
        @Override
        public void onChanged(Feed feed) {
            mDriversStandingsAdapter.setStandings(
                    feed.getMrData().getStandingsTable().getStandingsLists().get(0).getDriverStandings());
        }
    };

    private Observer<Feed> constructorStandingsObserver = new Observer<Feed>() {
        @Override
        public void onChanged(Feed feed) {
            mConstructorStandingsAdapter.setStandings(
                    feed.getMrData().getStandingsTable().getStandingsLists().get(0).getConstructorStandings());
        }
    };
}
