package com.rickhuisman.formula1app.ui.constructor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Constructor;
import com.rickhuisman.formula1app.ergast.models.Feed;
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

        String constructorId = getActivity().getIntent().getExtras().getString("constructorId");

        ConstructorViewModel constructorViewModel = ViewModelProviders.of(this).get(ConstructorViewModel.class);
        constructorViewModel.getConstructorInfo(constructorId).observe(this, new Observer<Feed>() {
            @Override
            public void onChanged(Feed feed) {
                Constructor constructor = feed.getMrData().getConstructorTable().getConstructors().get(0);

                TextView seasons = mView.findViewById(R.id.seasons);
                seasons.setText(constructor.getSeasons().getSeasonFirst() + " - " + constructor.getSeasons().getSeasonLast());

                TextView nationality = mView.findViewById(R.id.nationality);
                nationality.setText(constructor.getNationality());

                TextView raceWins = mView.findViewById(R.id.race_wins);
                raceWins.setText(constructor.getRaceWins());

                TextView polePositions = mView.findViewById(R.id.pole_positions);
                polePositions.setText(constructor.getPolePositions());

                TextView amountOfRaces = mView.findViewById(R.id.amount_of_races);
                amountOfRaces.setText(constructor.getGrandPrixEntered());
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
