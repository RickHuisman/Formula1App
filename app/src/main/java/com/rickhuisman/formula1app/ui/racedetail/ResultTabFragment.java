package com.rickhuisman.formula1app.ui.racedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.ergast.models.Results;
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
    public static final int QUALIFYING_RESULT_FRAGMENT = 0;
    public static final int RACE_RESULT_FRAGMENT = 1;

    private View mView;
    private ResultAdapter mResultAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int resultType = getArguments().getInt("resultType");
        int round = getArguments().getInt("round");

        RecyclerView resultList = mView.findViewById(R.id.race_result_recycler_view);
        resultList.setLayoutManager(new LinearLayoutManager(getContext()));
        resultList.setHasFixedSize(true);
        RaceDetailViewModel raceDetailViewModel = ViewModelProviders.of(this).get(RaceDetailViewModel.class);

        if (resultType == QUALIFYING_RESULT_FRAGMENT) {
            mResultAdapter = new ResultAdapter(getContext(), QUALIFYING_RESULT_FRAGMENT);
            resultList.setAdapter(mResultAdapter);

            raceDetailViewModel.getQualifyingResultsByRound(round).observe(this, qualifyingResultDataObserver);

        } else if (resultType == RACE_RESULT_FRAGMENT) {
            mResultAdapter = new ResultAdapter(getContext(), RACE_RESULT_FRAGMENT);
            resultList.setAdapter(mResultAdapter);

            raceDetailViewModel.getRaceResultsByRound(round).observe(this, raceResultDataObserver);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_result, container, false);
        return mView;
    }

    private Observer<Feed> raceResultDataObserver = new Observer<Feed>() {
        @Override
        public void onChanged(@Nullable Feed feed) {
            List<Results> results = feed.getMrData().getRaceTable().getRaces().get(0).getResults();

            mResultAdapter.setRaceResults(results);
        }
    };

    private Observer<Feed> qualifyingResultDataObserver = new Observer<Feed>() {
        @Override
        public void onChanged(@Nullable Feed feed) {
            List<Results> results = feed.getMrData().getRaceTable().getRaces().get(0).getQualifyingResults();

            mResultAdapter.setRaceResults(results);
        }
    };
}
