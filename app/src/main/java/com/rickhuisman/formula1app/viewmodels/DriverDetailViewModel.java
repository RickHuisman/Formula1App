package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.db.entities.Results;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DriverDetailViewModel extends AndroidViewModel {

    private ErgastRepository mErgastRepository;

    public DriverDetailViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository(application);
    }

    public LiveData<List<Results>> getResultsForDriverId(int driverId, int year) {
        return mErgastRepository.getResultsForDriverId(driverId, year);
    }
}
