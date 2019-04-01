package com.rickhuisman.formula1app.ui.racedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.QualifyingResult;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResult;
import com.rickhuisman.formula1app.ergast.models.Feed;
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
        int season = getArguments().getInt("season");
        int round = getArguments().getInt("round");

        RaceViewModel raceViewModel = ViewModelProviders.of(this).get(RaceViewModel.class);

        RecyclerView resultList = mView.findViewById(R.id.standings);
        resultList.setLayoutManager(new LinearLayoutManager(getContext()));
        resultList.setHasFixedSize(true);

        if (resultType == QUALIFYING_RESULT_FRAGMENT) {
            mQualifyingAdapter = new QualifyingAdapter(getContext());
            resultList.setAdapter(mQualifyingAdapter);

            raceViewModel.getQualifyingResults(season, round).observe(this, QualifyingObserver);
        }
        else if (resultType == RACE_RESULT_FRAGMENT) {
            mRaceAdapter = new RaceAdapter(getContext());
            resultList.setAdapter(mRaceAdapter);

            raceViewModel.getRaceResults(season, round).observe(this, RaceResultsObserver);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_result, container, false);
        return mView;
    }

    private Observer<Feed> QualifyingObserver = new Observer<Feed>() {
        @Override
        public void onChanged(Feed feed) {
            mQualifyingAdapter.setQualifying(feed.getMrData().getRaceTable().getRaces().get(0).getQualifyingResults());
        }
    };

    private Observer<Feed> RaceResultsObserver = new Observer<Feed>() {
        @Override
        public void onChanged(Feed feed) {
            mRaceAdapter.setRaceResult(feed.getMrData().getRaceTable().getRaces().get(0).getResults());
        }
    };
}
