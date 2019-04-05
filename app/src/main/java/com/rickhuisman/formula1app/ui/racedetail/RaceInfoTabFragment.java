package com.rickhuisman.formula1app.ui.racedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Circuit;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.ergast.models.LapRecord;
import com.rickhuisman.formula1app.ergast.models.Races;
import com.rickhuisman.formula1app.ergast.models.Summary;
import com.rickhuisman.formula1app.viewmodels.RaceViewModel;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RaceInfoTabFragment extends Fragment {

    private View mView;
    private ProgressBar mProgressBar;
    private NestedScrollView mContent;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int season = getArguments().getInt("season");
        int round = getArguments().getInt("round");
        String constructorId = getArguments().getString("constructorId");

        mProgressBar = mView.findViewById(R.id.progressbar);
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setIndeterminateTintList(getContext().getColorStateList(getTeamColor(constructorId)));

        mContent = mView.findViewById(R.id.content);
        mContent.setVisibility(View.INVISIBLE);

        RaceViewModel raceViewModel = ViewModelProviders.of(this).get(RaceViewModel.class);
        raceViewModel.getRaceInfo(season, round).observe(this, new Observer<Feed>() {
            @Override
            public void onChanged(Feed feed) {
                mProgressBar.setVisibility(View.INVISIBLE);

                Summary summary = feed.getMrData().getRaceTable().getRaces().get(0).getSummary();

                TextView raceWinner = mView.findViewById(R.id.date_of_birth);
                raceWinner.setText(summary.getRaceWinner());

                TextView polePosition = mView.findViewById(R.id.nationality);
                polePosition.setText(summary.getPolePosition());

                TextView fastestLapDriver = mView.findViewById(R.id.fastest_lap_driver);
                TextView fastestLapTime = mView.findViewById(R.id.fastest_lap_time);

                fastestLapDriver.setText(summary.getFastestLap().getDriver().getGivenName() + " " + summary.getFastestLap().getDriver().getFamilyName());
                fastestLapTime.setText(summary.getFastestLap().getLapTime());

                TextView climber = mView.findViewById(R.id.highest_climber);
                TextView positions = mView.findViewById(R.id.highest_climber_amount);

                climber.setText(summary.getHighestClimber().getDriver().getGivenName() + " " + summary.getHighestClimber().getDriver().getFamilyName());
                positions.setText(summary.getHighestClimber().getPositions() + " positions");

                LapRecord lapRecord = feed.getMrData().getRaceTable().getRaces().get(0).getLapRecord();

                TextView lapRecordTextView = mView.findViewById(R.id.lap_record);
                lapRecordTextView.setText(lapRecord.getLapTime());

                TextView recordHolderTextView = mView.findViewById(R.id.record_holder);
                recordHolderTextView.setText(lapRecord.getDriverName());

                TextView circuitName = mView.findViewById(R.id.circuit_name);
                TextView firstGrandPrix = mView.findViewById(R.id.first_grand_prix);

                Circuit circuit = feed.getMrData().getRaceTable().getRaces().get(0).getCircuit();
                circuitName.setText(circuit.getCircuitName());
                firstGrandPrix.setText(circuit.getFirstGrandPrix());

                TextView raceDateTime = mView.findViewById(R.id.race_date_time);
                raceDateTime.setText(getRaceDate(feed.getMrData().getRaceTable().getRaces().get(0)));

                RecyclerView list = mView.findViewById(R.id.race_winner_list);
                list.setLayoutManager(new LinearLayoutManager(getContext()));
                list.setHasFixedSize(true);

                RaceWinnersAdapter adapter = new RaceWinnersAdapter(getContext());
                list.setAdapter(adapter);
                adapter.setResults(feed.getMrData().getRaceTable().getRaces().get(0).getPastWinners());

                mContent.setVisibility(View.VISIBLE);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_race_info, container, false);
        return mView;
    }

    private int getTeamColor(String constructorId) {
        return getContext().getResources().getIdentifier(
                "constructor_" + constructorId, "color", getContext().getPackageName());
    }

    private String getRaceDate(Races race) {
        if (race.getTime() != null) {

            DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ssZ");
            DateTime formattedDateTime = format.parseDateTime(race.getDate() + " " + race.getTime());

            return formattedDateTime.toString("dd MMM, HH:mm", Locale.ENGLISH).toLowerCase();
        } else {

            DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
            DateTime formattedDate = format.parseDateTime(race.getDate());
            return formattedDate.toString("dd MMM", Locale.ENGLISH).toLowerCase();
        }
    }
}