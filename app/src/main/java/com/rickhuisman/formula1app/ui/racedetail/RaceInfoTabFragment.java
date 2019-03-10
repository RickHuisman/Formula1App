package com.rickhuisman.formula1app.ui.racedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.CircuitAndFirstGP;
import com.rickhuisman.formula1app.ergast.db.entities.HighestClimb;
import com.rickhuisman.formula1app.ergast.db.entities.LapRecord;
import com.rickhuisman.formula1app.ergast.db.entities.QualifyingResult;
import com.rickhuisman.formula1app.ergast.db.entities.Race;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResultDriver;
import com.rickhuisman.formula1app.ergast.db.entities.RaceWinner;
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

        int raceId = getArguments().getInt("raceId");

        final RaceViewModel raceViewModel = ViewModelProviders.of(this).get(RaceViewModel.class);
        raceViewModel.getRace(raceId).observe(this, new Observer<Race>() {
            @Override
            public void onChanged(Race race) {
                int circuitId = race.getCircuitId();

                raceViewModel.getCircuitAndFirstGP(circuitId).observe(getViewLifecycleOwner(), circuitAndFirstGPObserver);
                raceViewModel.getRaceResultForCircuit(circuitId).observe(getViewLifecycleOwner(), raceResultDriverObserver);
                raceViewModel.getLapRecordFor(circuitId).observe(getViewLifecycleOwner(), lapRecordObserver);
            }
        });
        raceViewModel.getRaceWinner(raceId).observe(this, raceWinnerObserver);
        raceViewModel.getPolePosition(raceId).observe(this, qualifyingResultObserver);
        raceViewModel.getHighestClimb(raceId).observe(this, highestClimbObserver);
        raceViewModel.getRaceDate(raceId).observe(this, raceDateObserver);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_race_info, container, false);
        return mView;
    }

    private Observer<RaceWinner> raceWinnerObserver = new Observer<RaceWinner>() {
        @Override
        public void onChanged(RaceWinner result) {
            TextView raceWinner = mView.findViewById(R.id.date_of_birth);

            String driver = result.getDriver().getForeName()
                    + " " + result.getDriver().getSurName();
            raceWinner.setText(driver);
        }
    };

    private Observer<QualifyingResult> qualifyingResultObserver = new Observer<QualifyingResult>() {
        @Override
        public void onChanged(QualifyingResult qualifyingResult) {
            TextView polePosition = mView.findViewById(R.id.nationality);

            String driver = qualifyingResult.getDriver().getForeName() + " " + qualifyingResult.getDriver().getSurName();

            polePosition.setText(driver);
        }
    };

    private Observer<HighestClimb> highestClimbObserver = new Observer<HighestClimb>() {
        @Override
        public void onChanged(HighestClimb highestClimb) {
            TextView climber = mView.findViewById(R.id.highest_climber);
            TextView amount = mView.findViewById(R.id.highest_climber_amount);

            String driver = highestClimb.getDriver().getForeName() + " " + highestClimb.getDriver().getSurName();

            climber.setText(driver);
            amount.setText(String.valueOf(highestClimb.getClimb() + " positions"));
        }
    };

    private Observer<Race> raceDateObserver = new Observer<Race>() {
        @Override
        public void onChanged(Race race) {
            TextView raceDateTime = mView.findViewById(R.id.race_date_time);

            raceDateTime.setText(getRaceDate(race));
        }
    };

    private Observer<CircuitAndFirstGP> circuitAndFirstGPObserver = new Observer<CircuitAndFirstGP>() {
        @Override
        public void onChanged(CircuitAndFirstGP circuitAndFirstGP) {
            TextView circuitName = mView.findViewById(R.id.circuit_name);
            TextView firstGrandPrix = mView.findViewById(R.id.first_grand_prix);

            circuitName.setText(circuitAndFirstGP.getCircuit().getName());
            firstGrandPrix.setText(String.valueOf(circuitAndFirstGP.getYear()));
        }
    };

    private Observer<List<RaceResultDriver>> raceResultDriverObserver = new Observer<List<RaceResultDriver>>() {
        @Override
        public void onChanged(List<RaceResultDriver> results) {
            RecyclerView list = mView.findViewById(R.id.race_winner_list);
            list.setLayoutManager(new LinearLayoutManager(getContext()));
            list.setHasFixedSize(true);

            RaceWinnersAdapter adapter = new RaceWinnersAdapter(getContext());
            list.setAdapter(adapter);
            adapter.setResults(results);
        }
    };

    private Observer<LapRecord> lapRecordObserver = new Observer<LapRecord>() {
        @Override
        public void onChanged(LapRecord lapRecord) {
            TextView lapRecordTextView = mView.findViewById(R.id.lap_record);
            lapRecordTextView.setText(lapRecord.getLapTime().getTime());

            TextView recordHolderTextView = mView.findViewById(R.id.record_holder);
            recordHolderTextView.setText(lapRecord.getDriver().getForeName() + " " + lapRecord.getDriver().getSurName());
        }
    };

    private String getRaceDate(Race race) {
        if (race.getTime() != null) {

            DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
            DateTime formattedDateTime = format.parseDateTime(race.getDate() + " " + race.getTime());

            return formattedDateTime.toString("dd MMM, HH:mm", Locale.ENGLISH).toLowerCase();
        } else {

            DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
            DateTime formattedDate = format.parseDateTime(race.getDate());

            return formattedDate.toString("dd MMM", Locale.ENGLISH).toLowerCase();
        }
    }
}