package com.rickhuisman.formula1app.viewmodels;

import android.app.Application;

import com.rickhuisman.formula1app.ergast.ErgastRepository;
import com.rickhuisman.formula1app.ergast.db.entities.Constructor;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorSeason;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorWorldChampionship;
import com.rickhuisman.formula1app.ergast.db.entities.RaceCount;
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

public class ConstructorViewModel extends AndroidViewModel {

    private ErgastRepository mErgastRepository;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private LiveData<Feed> mConstructorInfo = new MutableLiveData<>();

    public ConstructorViewModel(@NonNull Application application) {
        super(application);
        mErgastRepository = new ErgastRepository();
    }

    public LiveData<Feed> getConstructorInfo(String constructor) {
        mDisposable.add(mErgastRepository.getConstructorInfo(constructor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Feed>() {
                    @Override
                    public void accept(Feed feed) {
                        ((MutableLiveData<Feed>) mConstructorInfo).setValue(feed);
                    }
                }));
        return mConstructorInfo;
    }
}
