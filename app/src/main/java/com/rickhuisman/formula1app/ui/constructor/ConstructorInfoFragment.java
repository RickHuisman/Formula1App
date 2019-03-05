package com.rickhuisman.formula1app.ui.constructor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.Constructor;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorSeason;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorWorldChampionship;
import com.rickhuisman.formula1app.ergast.db.entities.RaceCount;
import com.rickhuisman.formula1app.viewmodels.ConstructorViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class ConstructorInfoFragment extends Fragment {

    private View mView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int constructorId = getActivity().getIntent().getExtras().getInt("constructorId");

        ConstructorViewModel constructorViewModel = ViewModelProviders.of(this).get(ConstructorViewModel.class);
        constructorViewModel.getConstructor(constructorId).observe(this, new Observer<Constructor>() {
            @Override
            public void onChanged(Constructor constructor) {
                TextView nationality = mView.findViewById(R.id.nationality);
                nationality.setText(constructor.getNationality());
            }
        });
        constructorViewModel.getSeasonsFor(constructorId).observe(this, new Observer<ConstructorSeason>() {
            @Override
            public void onChanged(ConstructorSeason constructorSeason) {
                TextView seasons = mView.findViewById(R.id.seasons);
                seasons.setText(String.valueOf(constructorSeason.getStart()) + " - " + String.valueOf(constructorSeason.getEnd()));
            }
        });
        constructorViewModel.getWorldChampionshipsFor(constructorId).observe(this, new Observer<List<ConstructorWorldChampionship>>() {
            @Override
            public void onChanged(List<ConstructorWorldChampionship> constructorWorldChampionships) {
                TextView worldChampionships = mView.findViewById(R.id.world_championships);
                worldChampionships.setText(String.valueOf(constructorWorldChampionships.size()));
            }
        });
        constructorViewModel.getRaceWinsFor(constructorId).observe(this, new Observer<RaceCount>() {
            @Override
            public void onChanged(RaceCount raceCount) {
                TextView raceWins = mView.findViewById(R.id.race_wins);
                raceWins.setText(String.valueOf(raceCount.getCount()));
            }
        });
        constructorViewModel.getPolePositionsFor(constructorId).observe(this, new Observer<RaceCount>() {
            @Override
            public void onChanged(RaceCount raceCount) {
                TextView polePositions = mView.findViewById(R.id.pole_positions);
                polePositions.setText(String.valueOf(raceCount.getCount()));
            }
        });
        constructorViewModel.getRaceCountFor(constructorId).observe(this, new Observer<RaceCount>() {
            @Override
            public void onChanged(RaceCount raceCount) {
                TextView amountOfRaces = mView.findViewById(R.id.amount_of_races);
                amountOfRaces.setText(String.valueOf(raceCount.getCount()));
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_constructor_info, container, false);
        return mView;
    }
}
