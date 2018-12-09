package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.models.Feed;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RaceDetailViewModel extends AndroidViewModel {

    private static final String TAG = "RaceDetailViewModel";

    private ErgastRepository mErgastRepository;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private LiveData<Feed> mRaceResults = new MutableLiveData<>();
    private LiveData<Feed> mQualifyingResults = new MutableLiveData<>();

    public RaceDetailViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository();
    }

    public LiveData<Feed> getRaceResults(int round) {
        mDisposable.add(mErgastRepository.getRaceResultsTest(round)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Feed>() {
                    @Override
                    public void accept(Feed feed) {
                        ((MutableLiveData<Feed>) mRaceResults).setValue(feed);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.e(TAG, "Error: " + throwable.getMessage(), throwable);
                    }
                }));
        return mRaceResults;
    }

    public LiveData<Feed> getQualifyingResults(int round) {
        mDisposable.add(mErgastRepository.getQualifyingResults(round)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Feed>() {
                    @Override
                    public void accept(Feed feed) {
                        ((MutableLiveData<Feed>) mQualifyingResults).setValue(feed);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.e(TAG, "Error: " + throwable.getMessage(), throwable);
                    }
                }));
        return mQualifyingResults;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.dispose();
    }
}
