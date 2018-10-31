package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.ergast.models.Races;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CalendarViewModel extends AndroidViewModel {
    private static final String TAG = "CalendarViewModel";

    private LiveData<Feed> mRaceSchedule = new MutableLiveData<>();
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public CalendarViewModel(@NonNull Application application) {
        super(application);
        ErgastRepository ergastRepository = new ErgastRepository();

        // Zip Race Schedule and Race Winner Observables
        Observable<Feed> getFeedObservable = Observable.zip(
                ergastRepository.getRaceSchedule(),
                ergastRepository.getRaceResults("1"),
                new BiFunction<Feed, Feed, Feed>() {
                    @Override
                    public Feed apply(Feed mainFeed, Feed raceWinnerFeed) {
                        ArrayList<Races> races = mainFeed.getMrData().getRaceTable().getRaces();
                        int raceWinnerFeedSize = raceWinnerFeed.getMrData().getRaceTable().getRaces().size();

                        for (int i = 0; i < raceWinnerFeedSize; i++) {
                            races.get(i).setResults(raceWinnerFeed.getMrData().getRaceTable()
                                    .getRaces().get(i).getResults());
                        }
                        return mainFeed;
                    }
                });

        // Get data from zip Observable
        mDisposable.add(getFeedObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Feed>() {
                    @Override
                    public void accept(Feed feed) {
                        ((MutableLiveData<Feed>) mRaceSchedule).setValue(feed);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.e(TAG, "Error: " + throwable.getMessage(), throwable);

                    }
                }));
    }

    public LiveData<Feed> getRaceSchedule() {
        return mRaceSchedule;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.dispose();
    }
}
