package com.rickhuisman.formula1app.ui.racedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Circuit;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.ergast.models.LapRecord;
import com.rickhuisman.formula1app.ergast.models.Summary;
import com.rickhuisman.formula1app.viewmodels.RaceViewModel;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RaceInfoTabFragment extends Fragment {

    private View mView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int season = getArguments().getInt("season");
        int round = getArguments().getInt("round");

        RaceViewModel raceViewModel = ViewModelProviders.of(this).get(RaceViewModel.class);
        raceViewModel.getRaceInfo(season, round).observe(this, new Observer<Feed>() {
            @Override
            public void onChanged(Feed feed) {

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

                RecyclerView list = mView.findViewById(R.id.race_winner_list);
                list.setLayoutManager(new LinearLayoutManager(getContext()));
                list.setHasFixedSize(true);

                RaceWinnersAdapter adapter = new RaceWinnersAdapter(getContext());
                list.setAdapter(adapter);
                adapter.setResults(feed.getMrData().getRaceTable().getRaces().get(0).getPastWinners());
            }
        });
//        raceViewModel.getRace(raceId).observe(this, new Observer<Race>() {
//            @Override
//            public void onChanged(Race race) {
//
//                DateTime test = getRaceDate(race);
//                DateTime now = DateTime.now();
//
//                if (test.compareTo(now) > 0) {
//                    setRaceSummaryGone();
//                } else {
//                    raceViewModel.getRaceWinner(race.getRaceId()).observe(getViewLifecycleOwner(), raceWinnerObserver);
//                    raceViewModel.getPolePosition(race.getRaceId()).observe(getViewLifecycleOwner(), qualifyingResultObserver);
//                    raceViewModel.getHighestClimb(race.getRaceId()).observe(getViewLifecycleOwner(), highestClimbObserver);
//                }
//
//                int circuitId = race.getCircuitId();
//
//                raceViewModel.getCircuitAndFirstGP(circuitId).observe(getViewLifecycleOwner(), circuitAndFirstGPObserver);
//                raceViewModel.getRaceResultForCircuit(circuitId).observe(getViewLifecycleOwner(), raceResultDriverObserver);
//                raceViewModel.getLapRecordFor(circuitId).observe(getViewLifecycleOwner(), lapRecordObserver);
//            }
//        });
//        raceViewModel.getRaceDate(raceId).observe(this, raceDateObserver);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_race_info, container, false);
        return mView;
    }

//    private DateTime getRaceDate(Race race) {
//        if (race.getTime() != null) {
//
//            DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
//            return format.parseDateTime(race.getDate() + " " + race.getTime());
//        } else {
//
//            DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
//            return format.parseDateTime(race.getDate());
//        }
//    }
//
//    private void setRaceSummaryGone() {
//        TextView raceSummaryTextView = mView.findViewById(R.id.race_summary_text_view);
//        raceSummaryTextView.setVisibility(View.GONE);
//
//        LinearLayout raceWinnerLayout = mView.findViewById(R.id.race_winner_layout);
//        raceWinnerLayout.setVisibility(View.GONE);
//
//        LinearLayout polePositionLayout = mView.findViewById(R.id.pole_position_layout);
//        polePositionLayout.setVisibility(View.GONE);
//
//        LinearLayout fastestLapLayout = mView.findViewById(R.id.fastest_lap_layout);
//        fastestLapLayout.setVisibility(View.GONE);
//
//        LinearLayout highestClimberLayout = mView.findViewById(R.id.highest_climber_layout);
//        highestClimberLayout.setVisibility(View.GONE);
//    }
//
//    private Observer<List<RaceResultDriver>> raceResultDriverObserver = new Observer<List<RaceResultDriver>>() {
//        @Override
//        public void onChanged(List<RaceResultDriver> results) {
//            RecyclerView list = mView.findViewById(R.id.race_winner_list);
//            list.setLayoutManager(new LinearLayoutManager(getContext()));
//            list.setHasFixedSize(true);
//
//            RaceWinnersAdapter adapter = new RaceWinnersAdapter(getContext());
//            list.setAdapter(adapter);
//            adapter.setResults(results);
//        }
//    };
}