package com.rickhuisman.formula1app.ui.driverdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Driver;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.viewmodels.DriverViewModel;

import org.joda.time.LocalDate;
import org.joda.time.Years;

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

        String driverId = getActivity().getIntent().getExtras().getString("driverId");

        DriverViewModel driverViewModel = ViewModelProviders.of(this).get(DriverViewModel.class);
        driverViewModel.getDriverInfo(driverId).observe(this, new Observer<Feed>() {
            @Override
            public void onChanged(Feed feed) {
                Driver driver = feed.getMrData().getDriverTable().getDrivers().get(0);

                TextView dateOfBirth = mView.findViewById(R.id.date_of_birth);
                dateOfBirth.setText(driver.getDateOfBirth() + " (" + getAgeFor(driver) + ")");

                TextView nationality = mView.findViewById(R.id.nationality);
                nationality.setText(driver.getNationality());

                TextView number = mView.findViewById(R.id.number);
                number.setText(driver.getPermanentNumber());

                TextView podiums = mView.findViewById(R.id.podium_count);
                podiums.setText(driver.getPodiums());

                TextView grandPrixEntered = mView.findViewById(R.id.amount_of_races);
                grandPrixEntered.setText(driver.getGrandPrixEntered());

                TextView championships = mView.findViewById(R.id.world_championships);
                championships.setText(driver.getWorldChampionships());

                TextView raceFinish = mView.findViewById(R.id.highest_race_finish);
                String text = driver.getHighestRaceFinish().getPosition()
                        + " (x" + driver.getHighestGridPosition().getAmount() + ")";
                raceFinish.setText(text);

                TextView highestGridCount = mView.findViewById(R.id.highest_grid_position);
                String test = driver.getHighestGridPosition().getPosition()
                        + " (x" + driver.getHighestGridPosition().getAmount() + ")";
                highestGridCount.setText(test);

                RecyclerView list = mView.findViewById(R.id.list);
                list.setLayoutManager(new LinearLayoutManager(getContext()));
                list.setHasFixedSize(true);

                TeamAdapter adapter = new TeamAdapter(getContext());
                list.setAdapter(adapter);

                adapter.setTeams(feed.getMrData().getDriverTable().getDrivers().get(0).getTeams());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_driver_info, container, false);
        return mView;
    }

    private int getAgeFor(Driver driver) {
        LocalDate birthDate = new LocalDate(driver.getDateOfBirth());
        LocalDate currentDate = LocalDate.now();

        Years age = Years.yearsBetween(birthDate, currentDate);

        return age.getYears();
    }
}
