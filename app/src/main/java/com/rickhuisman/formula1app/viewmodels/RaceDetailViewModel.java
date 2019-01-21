package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.db.entities.QualifyingWithDriver;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResultWithDriverAndStatus;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import io.reactivex.disposables.CompositeDisposable;

public class RaceDetailViewModel extends AndroidViewModel {

    private ErgastRepository mErgastRepository;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public RaceDetailViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository(application);
    }

    public LiveData<List<RaceResultWithDriverAndStatus>> getRaceResultWithDriverAndStatus(int raceId) {
        return mErgastRepository.getRaceResultWithDriverAndStatus(raceId);
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
