package com.rickhuisman.formula1app.ui.standings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorStandingsWithConstructorAndDrivers;
import com.rickhuisman.formula1app.ergast.db.entities.DriverStandingsWithDriverAndConstructor;
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
    private DriverStandingsAdapter mDriversStandingsAdapter;
    private ConstructorStandingsAdapter mConstructorStandingsAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int resultType = getArguments().getInt("resultType");

        RecyclerView standingsList = mView.findViewById(R.id.race_result_recycler_view);
        standingsList.setLayoutManager(new LinearLayoutManager(getContext()));
        standingsList.setHasFixedSize(true);
        StandingsViewModel standingsViewModel = ViewModelProviders.of(this).get(StandingsViewModel.class);

        if (resultType == DRIVERS_STANDING_FRAGMENT) {
            mDriversStandingsAdapter = new DriverStandingsAdapter();
            standingsList.setAdapter(mDriversStandingsAdapter);

            standingsViewModel.getDriverStandingsWithDriverAndConstructor(1009).observe(this, driverStandingsDataObserver);
        } else if (resultType == CONSTRUCTORS_STANDING_FRAGMENT) {
            mConstructorStandingsAdapter = new ConstructorStandingsAdapter();
            standingsList.setAdapter(mConstructorStandingsAdapter);

            standingsViewModel.getConstructorStandingsWithConstructor(131,1009)
                    .observe(this, constructorStandingsDataObserver);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_result, container, false);
        return mView;
    }

    private Observer<List<DriverStandingsWithDriverAndConstructor>> driverStandingsDataObserver = new Observer<List<DriverStandingsWithDriverAndConstructor>>() {
        @Override
        public void onChanged(List<DriverStandingsWithDriverAndConstructor> standings) {
            mDriversStandingsAdapter.setStandings(standings);
        }
    };

    private Observer<List<ConstructorStandingsWithConstructorAndDrivers>> constructorStandingsDataObserver = new Observer<List<ConstructorStandingsWithConstructorAndDrivers>>() {
        @Override
        public void onChanged(List<ConstructorStandingsWithConstructorAndDrivers> standings) {
//            mConstructorStandingsAdapter.setStandings(standings);
        }
    };
}
