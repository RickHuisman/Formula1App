package com.rickhuisman.formula1app.ui.racedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.viewmodels.RaceViewModel;

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
    private RecyclerView mResultList;
    private ProgressBar mProgressBar;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int resultType = getArguments().getInt("resultType");
        int season = getArguments().getInt("season");
        int round = getArguments().getInt("round");
        String constructorId = getArguments().getString("constructorId");

        RaceViewModel raceViewModel = ViewModelProviders.of(this).get(RaceViewModel.class);

        mResultList = mView.findViewById(R.id.standings);
        mResultList.setLayoutManager(new LinearLayoutManager(getContext()));
        mResultList.setHasFixedSize(true);

        mResultList.setVisibility(View.INVISIBLE);

        mProgressBar = mView.findViewById(R.id.progressbar);
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setIndeterminateTintList(getContext().getColorStateList(getTeamColor(constructorId)));

        if (resultType == QUALIFYING_RESULT_FRAGMENT) {
            mQualifyingAdapter = new QualifyingAdapter(getContext());
            mResultList.setAdapter(mQualifyingAdapter);

            raceViewModel.getQualifyingResults(season, round).observe(this, QualifyingObserver);
        }
        else if (resultType == RACE_RESULT_FRAGMENT) {
            mRaceAdapter = new RaceAdapter(getContext());
            mResultList.setAdapter(mRaceAdapter);

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

    private int getTeamColor(String constructorId) {
        return getContext().getResources().getIdentifier(
                "constructor_" + constructorId, "color", getContext().getPackageName());
    }

    private Observer<Feed> QualifyingObserver = new Observer<Feed>() {
        @Override
        public void onChanged(Feed feed) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mQualifyingAdapter.setQualifying(feed.getMrData().getRaceTable().getRaces().get(0).getQualifyingResults());
            mResultList.setVisibility(View.VISIBLE);
        }
    };

    private Observer<Feed> RaceResultsObserver = new Observer<Feed>() {
        @Override
        public void onChanged(Feed feed) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mRaceAdapter.setRaceResult(feed.getMrData().getRaceTable().getRaces().get(0).getResults());
            mResultList.setVisibility(View.VISIBLE);
        }
    };
}
