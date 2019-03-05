package com.rickhuisman.formula1app.ui.racedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.QualifyingResult;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResult;
import com.rickhuisman.formula1app.viewmodels.RaceViewModel;

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

    private RaceAdapter mRaceAdapter;
    private QualifyingAdapter mQualifyingAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int resultType = getArguments().getInt("resultType");
        int raceId = getArguments().getInt("raceId");

        RaceViewModel raceViewModel = ViewModelProviders.of(this).get(RaceViewModel.class);

        RecyclerView resultList = mView.findViewById(R.id.standings);
        resultList.setLayoutManager(new LinearLayoutManager(getContext()));
        resultList.setHasFixedSize(true);

        if (resultType == QUALIFYING_RESULT_FRAGMENT) {
            mQualifyingAdapter = new QualifyingAdapter(getContext());
            resultList.setAdapter(mQualifyingAdapter);

            raceViewModel.getQualifyingResult(raceId).observe(this, QualifyingObserver);
        }
        else if (resultType == RACE_RESULT_FRAGMENT) {
            mRaceAdapter = new RaceAdapter(getContext());
            resultList.setAdapter(mRaceAdapter);

            raceViewModel.getRaceResult(raceId).observe(this, RaceResultObserver);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_result, container, false);
        return mView;
    }

    private Observer<List<QualifyingResult>> QualifyingObserver = new Observer<List<QualifyingResult>>() {
        @Override
        public void onChanged(List<QualifyingResult> qualifyingResults) {
            mQualifyingAdapter.setQualifying(qualifyingResults);
        }
    };

    private Observer<List<RaceResult>> RaceResultObserver = new Observer<List<RaceResult>>() {
        @Override
        public void onChanged(List<RaceResult> result) {
            mRaceAdapter.setRaceResult(result);
        }
    };
}
