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

public class ScheduleViewModel extends AndroidViewModel {
    private static final String TAG = "ScheduleViewModel";

    private LiveData<Feed> mRaceSchedule = new MutableLiveData<>();
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public ScheduleViewModel(@NonNull Application application) {
        super(application);
        ErgastRepository ergastRepository = new ErgastRepository();

        // Zip Race Schedule and Race Winner Observables
        Observable<Feed> getFeedObservable = Observable.zip(
                ergastRepository.getRaceSchedule(),
                ergastRepository.getRaceResults(),
                new BiFunction<Feed, Feed, Feed>() {
                    @Override
                    public Feed apply(Feed scheduleFeed, Feed resultFeed) {
                        ArrayList<Races> races = scheduleFeed.getMrData().getRaceTable().getRaces();
                        int resultFeedSize = resultFeed.getMrData().getRaceTable().getRaces().size();

                        for (int i = 0; i < resultFeedSize; i++) {
                            races.get(i).setResults(resultFeed.getMrData().getRaceTable()
                                    .getRaces().get(i).getResults());
                        }
                        return scheduleFeed;
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
