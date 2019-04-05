package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.db.entities.Driver;
import com.rickhuisman.formula1app.ergast.db.entities.HighestGrid;
import com.rickhuisman.formula1app.ergast.db.entities.HighestRaceFinish;
import com.rickhuisman.formula1app.ergast.db.entities.PodiumCount;
import com.rickhuisman.formula1app.ergast.db.entities.RaceCount;
import com.rickhuisman.formula1app.ergast.db.entities.Result;
import com.rickhuisman.formula1app.ergast.db.entities.Team;
import com.rickhuisman.formula1app.ergast.db.entities.WorldChampionships;
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

public class DriverViewModel extends AndroidViewModel {

    private ErgastRepository mErgastRepository;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private LiveData<Feed> mDriverInfo = new MutableLiveData<>();

    public DriverViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository();
    }

    public LiveData<Feed> getDriverInfo(String driver) {
        mDisposable.add(mErgastRepository.getDriverInfo(driver)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Feed>() {
                    @Override
                    public void accept(Feed feed) {
                        ((MutableLiveData<Feed>) mDriverInfo).setValue(feed);
                    }
                }));
        return mDriverInfo;
    }
}
