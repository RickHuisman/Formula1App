package com.rickhuisman.formula1app.ui.racedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.test.db.entities.Qualifying;
import com.rickhuisman.formula1app.ergast.test.db.entities.QualifyingWithDriver;
import com.rickhuisman.formula1app.ergast.test.db.entities.RaceResultWithDriver;
import com.rickhuisman.formula1app.viewmodels.RaceDetailViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ResultTabFragment extends Fragment {
    private static final int QUALIFYING_RESULT_FRAGMENT = 0;
    private static final int RACE_RESULT_FRAGMENT = 1;

    private View mView;
    private ResultAdapter mResultAdapter;
    private QualifyingAdapter mQualifyingAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int resultType = getArguments().getInt("resultType");
        int raceId = getArguments().getInt("raceId");

        RecyclerView resultList = mView.findViewById(R.id.race_result_recycler_view);
        resultList.setLayoutManager(new LinearLayoutManager(getContext()));
        resultList.setHasFixedSize(true);

        RaceDetailViewModel raceDetailViewModel = ViewModelProviders.of(this).get(RaceDetailViewModel.class);

        if (resultType == QUALIFYING_RESULT_FRAGMENT) {
            mQualifyingAdapter = new QualifyingAdapter();
            resultList.setAdapter(mQualifyingAdapter);

            raceDetailViewModel.getQualifyingWithDriver(raceId).observe(this, QualifyingWithDriverObserver);
        }
        else if (resultType == RACE_RESULT_FRAGMENT) {
            mResultAdapter = new ResultAdapter();
            resultList.setAdapter(mResultAdapter);

            raceDetailViewModel.getRaceResultWithDriver(raceId).observe(this, RaceResultWithDriverObserver);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_result, container, false);
        return mView;
    }

    private Observer<List<QualifyingWithDriver>> QualifyingWithDriverObserver = new Observer<List<QualifyingWithDriver>>() {
        @Override
        public void onChanged(List<QualifyingWithDriver> qualifyingWithDrivers) {
            mQualifyingAdapter.setQualifyingResults(qualifyingWithDrivers);
        }
    };

    private Observer<List<RaceResultWithDriver>> RaceResultWithDriverObserver = new Observer<List<RaceResultWithDriver>>() {
        @Override
        public void onChanged(List<RaceResultWithDriver> raceResultWithDriver) {
            mResultAdapter.setRaceResultsWithDriver(raceResultWithDriver);
        }
    };
}
