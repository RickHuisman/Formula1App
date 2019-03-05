package com.rickhuisman.formula1app.ui.driverdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.Driver;
import com.rickhuisman.formula1app.ergast.db.entities.HighestGrid;
import com.rickhuisman.formula1app.ergast.db.entities.HighestRaceFinish;
import com.rickhuisman.formula1app.ergast.db.entities.PodiumCount;
import com.rickhuisman.formula1app.ergast.db.entities.RaceCount;
import com.rickhuisman.formula1app.ergast.db.entities.Team;
import com.rickhuisman.formula1app.ergast.db.entities.WorldChampionships;
import com.rickhuisman.formula1app.viewmodels.DriverViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DriverInfoFragment extends Fragment {

    private View mView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final int driverId = getActivity().getIntent().getExtras().getInt("driverId");

        DriverViewModel driverViewModel = ViewModelProviders.of(this).get(DriverViewModel.class);
        driverViewModel.getDriver(driverId).observe(this, new Observer<Driver>() {
            @Override
            public void onChanged(Driver driver) {
                TextView dateOfBirth = mView.findViewById(R.id.date_of_birth);
                dateOfBirth.setText(driver.getDob());

                TextView nationality = mView.findViewById(R.id.nationality);
                nationality.setText(driver.getNationality());

                TextView number = mView.findViewById(R.id.number);
                number.setText(String.valueOf(driver.getNumber()));
            }
        });
        driverViewModel.getRaceCount(driverId).observe(this, new Observer<RaceCount>() {
            @Override
            public void onChanged(RaceCount raceCount) {
                TextView grandPrixEntered = mView.findViewById(R.id.amount_of_races);
                grandPrixEntered.setText(String.valueOf(raceCount.getCount()));
            }
        });
        driverViewModel.getPodiumsCount(driverId).observe(this, new Observer<PodiumCount>() {
            @Override
            public void onChanged(PodiumCount podiumCount) {
                TextView podiums = mView.findViewById(R.id.podium_count);
                podiums.setText(String.valueOf(podiumCount.getCount()));
            }
        });
        driverViewModel.getHighestRaceFinish(driverId).observe(this, new Observer<HighestRaceFinish>() {
            @Override
            public void onChanged(HighestRaceFinish highestRaceFinish) {
                TextView raceFinish = mView.findViewById(R.id.highest_race_finish);
                String test = highestRaceFinish.getResult().getPosition()
                        + " (x" + String.valueOf(highestRaceFinish.getCount()) + ")";
                raceFinish.setText(test);
            }
        });
        driverViewModel.getHighestGrid(driverId).observe(this, new Observer<HighestGrid>() {
            @Override
            public void onChanged(HighestGrid highestGrid) {
                TextView highestGridCount = mView.findViewById(R.id.highest_grid_position);
                String test = String.valueOf(highestGrid.getQualifying().getPosition())
                        + " (x" + String.valueOf(highestGrid.getCount()) + ")";
                highestGridCount.setText(test);
            }
        });
        driverViewModel.getWorldChampionships(driverId).observe(this, new Observer<List<WorldChampionships>>() {
            @Override
            public void onChanged(List<WorldChampionships> worldChampionships) {
                TextView championships = mView.findViewById(R.id.world_championships);
                championships.setText(String.valueOf(worldChampionships.size()));
            }
        });
        driverViewModel.getTeams(driverId).observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teams) {
                RecyclerView list = mView.findViewById(R.id.list);
                list.setLayoutManager(new LinearLayoutManager(getContext()));
                list.setHasFixedSize(true);

                TeamAdapter adapter = new TeamAdapter(getContext());
                list.setAdapter(adapter);

                adapter.setTeams(teams);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_driver_info, container, false);
        return mView;
    }
}
