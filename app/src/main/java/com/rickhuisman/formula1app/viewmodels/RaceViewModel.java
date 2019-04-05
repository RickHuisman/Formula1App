package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.models.Feed;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RaceViewModel extends AndroidViewModel {

    private ErgastRepository mErgastRepository;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private LiveData<Feed> mRaceInfo = new MutableLiveData<>();
    private LiveData<Feed> mResults = new MutableLiveData<>();

    public RaceViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository();
    }

    public LiveData<Feed> getRaceInfo(int season, int round) {
        mDisposable.add(mErgastRepository.getRaceInfo(season, round)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Feed>() {
                    @Override
                    public void accept(Feed feed) {
                        ((MutableLiveData<Feed>) mRaceInfo).setValue(feed);
                    }
                }));
        return mRaceInfo;
    }

    public LiveData<Feed> getQualifyingResults(int season, int round) {
        mDisposable.add(mErgastRepository.getQualifyingResults(season, round)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Feed>() {
                    @Override
                    public void accept(Feed feed) {
                        ((MutableLiveData<Feed>) mResults).setValue(feed);
                    }
                }));
        return mResults;
    }

    public LiveData<Feed> getRaceResults(int season, int round) {
        mDisposable.add(mErgastRepository.getRaceResults(season, round)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Feed>() {
                    @Override
                    public void accept(Feed feed) {
                        ((MutableLiveData<Feed>) mResults).setValue(feed);
                    }
                }));
        return mResults;
    }
}
