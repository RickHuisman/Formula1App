package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;
import android.util.Log;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.ergast.test.db.entities.QualifyingWithDriver;
import com.rickhuisman.formula1app.ergast.test.db.entities.RaceResultWithDriver;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RaceDetailViewModel extends AndroidViewModel {

    private ErgastRepository mErgastRepository;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public RaceDetailViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository(application);
    }

    public LiveData<List<RaceResultWithDriver>> getRaceResultWithDriver(int raceId) {
        return mErgastRepository.getRaceResultWithDriver(raceId);
    }

    public LiveData<List<QualifyingWithDriver>> getQualifyingWithDriver(int raceId) {
        return mErgastRepository.getQualifyingWithDriver(raceId);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.dispose();
    }
}
