package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.db.entities.Constructor;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorSeason;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorWorldChampionship;
import com.rickhuisman.formula1app.ergast.db.entities.RaceCount;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ConstructorViewModel extends AndroidViewModel {

    private ErgastRepository mErgastRepository;

    public ConstructorViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository(application);
    }

    public LiveData<Constructor> getConstructor(int constructorId) {
        return mErgastRepository.getConstructor(constructorId);
    }

    public LiveData<ConstructorSeason> getSeasonsFor(int constructorId) {
        return mErgastRepository.getSeasonsFor(constructorId);
    }

    public LiveData<List<ConstructorWorldChampionship>> getWorldChampionshipsFor(int constructorId) {
        return mErgastRepository.getWorldChampionshipsFor(constructorId);
    }

    public LiveData<RaceCount> getPolePositionsFor(int constructorId) {
        return mErgastRepository.getPolePositionsFor(constructorId);
    }

    public LiveData<RaceCount> getRaceWinsFor(int constructorId) {
        return mErgastRepository.getRaceWinsFor(constructorId);
    }

    public LiveData<RaceCount> getRaceCountFor(int constructorId) {
        return mErgastRepository.getRaceCountFor(constructorId);
    }
}
