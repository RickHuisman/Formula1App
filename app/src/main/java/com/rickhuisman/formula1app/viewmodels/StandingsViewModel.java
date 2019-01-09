package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;
import android.util.Log;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.ergast.test.db.entities.DriverStanding;
import com.rickhuisman.formula1app.ergast.test.db.entities.DriverStandingsWithDriver;

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

    public StandingsViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository(application);
    }

    public LiveData<List<DriverStandingsWithDriver>> getDriverStandingsWithDriver(int raceId) {
        return mErgastRepository.getDriverStandingsWithDriver(raceId);
    }
}
