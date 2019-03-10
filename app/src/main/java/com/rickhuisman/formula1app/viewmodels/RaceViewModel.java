package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.db.entities.CircuitAndFirstGP;
import com.rickhuisman.formula1app.ergast.db.entities.HighestClimb;
import com.rickhuisman.formula1app.ergast.db.entities.LapRecord;
import com.rickhuisman.formula1app.ergast.db.entities.QualifyingResult;
import com.rickhuisman.formula1app.ergast.db.entities.Race;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResult;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResultDriver;
import com.rickhuisman.formula1app.ergast.db.entities.RaceWinner;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class RaceViewModel extends AndroidViewModel {

    private ErgastRepository mErgastRepository;

    public RaceViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository(application);
    }

    public LiveData<List<QualifyingResult>> getQualifyingResult(int raceId) {
        return mErgastRepository.getQualifyingResult(raceId);
    }

    public LiveData<List<RaceResult>> getRaceResult(int raceId) {
        return mErgastRepository.getRaceResult(raceId);
    }

    public LiveData<RaceWinner> getRaceWinner(int raceId) {
        return mErgastRepository.getRaceWinner(raceId);
    }

    public LiveData<QualifyingResult> getPolePosition(int raceId) {
        return mErgastRepository.getPolePosition(raceId);
    }

    public LiveData<HighestClimb> getHighestClimb(int raceId) {
        return mErgastRepository.getHighestClimb(raceId);
    }

    public LiveData<Race> getRaceDate(int raceId) {
        return mErgastRepository.getRaceDate(raceId);
    }

    public LiveData<CircuitAndFirstGP> getCircuitAndFirstGP(int circuitId) {
        return mErgastRepository.getCircuitAndFirstGP(circuitId);
    }

    public LiveData<List<RaceResultDriver>> getRaceResultForCircuit(int circuitId) {
        return mErgastRepository.getRaceResultForCircuit(circuitId);
    }

    public LiveData<Race> getRace(int raceId) {
        return mErgastRepository.getRace(raceId);
    }

    public LiveData<LapRecord> getLapRecordFor(int raceId) {
        return mErgastRepository.getLapRecordFor(raceId);
    }
}
