package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.db.entities.RaceWithWinner;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ScheduleViewModel extends AndroidViewModel {

    ErgastRepository mErgastRepository;

    public ScheduleViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository(application);
    }

    public LiveData<List<RaceWithWinner>> getRaceAndWinners(int year) {
        return mErgastRepository.getRacesAndWinners(year);
    }
}
