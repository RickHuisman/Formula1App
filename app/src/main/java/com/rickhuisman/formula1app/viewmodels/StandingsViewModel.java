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

public class StandingsViewModel extends AndroidViewModel {

    private static final String TAG = "StandingsViewModel";

    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private ErgastRepository mErgastRepository;

    private LiveData<Feed> mDriverStandings = new MutableLiveData<>();
    private LiveData<Feed> mConstructorStandings = new MutableLiveData<>();

    public StandingsViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository();
    }

    public LiveData<Feed> getDriverStandings() {
        mDisposable.add(mErgastRepository.getDriverStandings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Feed>() {
                    @Override
                    public void accept(Feed feed) {
                        ((MutableLiveData<Feed>) mDriverStandings).setValue(feed);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.e(TAG, "Error: " + throwable.getMessage(), throwable);
                    }
                }));
        return mDriverStandings;
    }

    public LiveData<Feed> getConstuctorStandings() {
        mDisposable.add(mErgastRepository.getConstructorStandings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Feed>() {
                    @Override
                    public void accept(Feed feed) {
                        ((MutableLiveData<Feed>) mConstructorStandings).setValue(feed);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.e(TAG, "Error: " + throwable.getMessage(), throwable);
                    }
                }));
        return mConstructorStandings;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.dispose();
    }
}
