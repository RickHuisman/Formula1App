package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;
import android.util.Log;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.ergast.models.Races;
import com.rickhuisman.formula1app.ergast.models.Results;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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

        // Merge Race Schedule and Race Winner Observables
        Observable<Feed> getFeedObservable = Observable.zip(
                ergastRepository.getRaceSchedule(),
                ergastRepository.getRaceResults(),
                new BiFunction<Feed, Feed, Feed>() {
                    @Override
                    public Feed apply(Feed scheduleFeed, Feed resultFeed) {
                        ArrayList<Races> races = scheduleFeed.getMrData().getRaceTable().getRaces();
                        int amountOfRaceWinners = resultFeed.getMrData().getRaceTable().getRaces().size();

                        for (int i = 0; i < amountOfRaceWinners; i++) {
                            ArrayList<Results> raceResults =
                                    resultFeed.getMrData().getRaceTable().getRaces().get(i).getResults();

                            races.get(i).setResults(raceResults);
                        }
                        return scheduleFeed;
                    }
                });

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
