package com.rickhuisman.formula1app.ui.constructor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Constructor;
import com.rickhuisman.formula1app.ergast.models.CurrentDrivers;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.viewmodels.ConstructorViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class ConstructorInfoFragment extends Fragment {

    private View mView;
    private ProgressBar mProgressBar;
    private NestedScrollView mContent;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String constructorId = getActivity().getIntent().getExtras().getString("constructorId");

        mProgressBar = mView.findViewById(R.id.progressbar);
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setIndeterminateTintList(getContext().getColorStateList(getTeamColor(constructorId)));

        mContent = mView.findViewById(R.id.content);
        mContent.setVisibility(View.INVISIBLE);

        ConstructorViewModel constructorViewModel = ViewModelProviders.of(this).get(ConstructorViewModel.class);
        constructorViewModel.getConstructorInfo(constructorId).observe(this, new Observer<Feed>() {
            @Override
            public void onChanged(Feed feed) {
                mProgressBar.setVisibility(View.INVISIBLE);

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

                CurrentDrivers currentDriverOne = constructor.getCurrentDrivers().get(0);
                CurrentDrivers currentDriverTwo = constructor.getCurrentDrivers().get(1);

                TextView driverOne = mView.findViewById(R.id.driver_one);
                String driverOneName = currentDriverOne.getDriver().getGivenName() + " " + currentDriverOne.getDriver().getFamilyName();
                driverOne.setText(driverOneName);

                TextView driverTwo = mView.findViewById(R.id.driver_two);
                String driverTwoName = currentDriverTwo.getDriver().getGivenName() + " " + currentDriverTwo.getDriver().getFamilyName();
                driverTwo.setText(driverTwoName);

                TextView raceCountOne = mView.findViewById(R.id.race_count_one);
                raceCountOne.setText(currentDriverOne.getRaceCount());

                TextView raceCountTwo = mView.findViewById(R.id.race_count_two);
                raceCountTwo.setText(currentDriverTwo.getRaceCount());

                TextView pointsOne = mView.findViewById(R.id.points_one);
                pointsOne.setText(currentDriverOne.getPoints());

                TextView pointsTwo = mView.findViewById(R.id.points_two);
                pointsTwo.setText(currentDriverTwo.getPoints());

                TextView outQualifiedOne = mView.findViewById(R.id.outqualified_one);
                outQualifiedOne.setText(currentDriverOne.getOutQualified());

                TextView outQualifiedTwo = mView.findViewById(R.id.outqualified_two);
                outQualifiedTwo.setText(currentDriverTwo.getOutQualified());

                TextView highestFinishOne = mView.findViewById(R.id.highest_finish_one);
                highestFinishOne.setText(currentDriverOne.getHighestFinish());

                TextView highestFinishTwo = mView.findViewById(R.id.highest_finish_two);
                highestFinishTwo.setText(currentDriverTwo.getHighestFinish());

                mContent.setVisibility(View.VISIBLE);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_constructor_info, container, false);
        return mView;
    }

    private int getTeamColor(String constructorId) {
        return getContext().getResources().getIdentifier(
                "constructor_" + constructorId, "color", getContext().getPackageName());
    }
}
