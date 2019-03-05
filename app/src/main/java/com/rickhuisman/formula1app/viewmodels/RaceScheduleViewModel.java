package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.db.entities.RaceWithWinner;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class RaceScheduleViewModel extends AndroidViewModel {

    private ErgastRepository mErgastRepository;

    public RaceScheduleViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository(application);
    }

    public LiveData<List<RaceWithWinner>> getRaceSchedule(int year) {
        return mErgastRepository.getRaceSchedule(year);
    }
}
