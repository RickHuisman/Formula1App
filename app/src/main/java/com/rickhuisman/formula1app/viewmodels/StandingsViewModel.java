package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorStandingsWithConstructorAndDrivers;
import com.rickhuisman.formula1app.ergast.db.entities.DriverStandings;
import com.rickhuisman.formula1app.ergast.models.Feed;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class StandingsViewModel extends AndroidViewModel {

    private ErgastRepository mErgastRepository;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
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
                }));
        return mDriverStandings;
    }

    public LiveData<Feed> getConstructorStandings() {
        mDisposable.add(mErgastRepository.getConstructorStandings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Feed>() {
                    @Override
                    public void accept(Feed feed) {
                        ((MutableLiveData<Feed>) mConstructorStandings).setValue(feed);
                    }
                }));
        return mConstructorStandings;
    }
}
