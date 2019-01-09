package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;
import android.util.Log;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.models.Feed;
import com.rickhuisman.formula1app.ergast.models.Races;
import com.rickhuisman.formula1app.ergast.models.Results;
import com.rickhuisman.formula1app.ergast.test.db.entities.Constructor;
import com.rickhuisman.formula1app.ergast.test.db.entities.RaceWithWinner;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
