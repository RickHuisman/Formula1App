package com.rickhuisman.formula1app.ergast.db;

import android.content.Context;
import android.os.AsyncTask;

import com.rickhuisman.formula1app.ergast.db.dao.ErgastDao;
import com.rickhuisman.formula1app.ergast.db.entities.Circuit;
import com.rickhuisman.formula1app.ergast.db.entities.Constructor;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorResult;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorStandings;
import com.rickhuisman.formula1app.ergast.db.entities.Driver;
import com.rickhuisman.formula1app.ergast.db.entities.DriverStanding;
import com.rickhuisman.formula1app.ergast.db.entities.LapTimes;
import com.rickhuisman.formula1app.ergast.db.entities.Qualifying;
import com.rickhuisman.formula1app.ergast.db.entities.Race;
import com.rickhuisman.formula1app.ergast.db.entities.Result;
import com.rickhuisman.formula1app.ergast.db.entities.Status;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {
        Race.class,
        Result.class,
        Circuit.class,
        Constructor.class,
        Driver.class,
        Qualifying.class,
        DriverStanding.class,
        ConstructorStandings.class,
        ConstructorResult.class,
        Status.class,
        LapTimes.class}, version = 1, exportSchema = false)
public abstract class ErgastDatabase extends RoomDatabase {

    private static ErgastDatabase sInstance;

    public abstract ErgastDao mErgastDao();

    private static DatabaseAccess databaseAccess;

    public static synchronized ErgastDatabase getInstance(Context context) {
        if (sInstance == null) {

            databaseAccess = DatabaseAccess.getInstance(context);

            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ErgastDatabase.class, "notedb.db")
                    .allowMainThreadQueries()
                    .addCallback(roomCallback)
                    .build();
        }
        return sInstance;
    }

    private static Callback roomCallback = new Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(sInstance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ErgastDao mErgastDao;

        private PopulateDbAsyncTask(ErgastDatabase db) {
            this.mErgastDao = db.mErgastDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            databaseAccess.open();

            // Race
            ArrayList<Race> races = databaseAccess.getRaces();
            for (int i = 0; i < races.size(); i++) {
                mErgastDao.insertRaces(races.get(i));
            }
            // Result
            ArrayList<Result> results = databaseAccess.getResults();
            for (int i = 0; i < results.size(); i++) {
                mErgastDao.insertResults(results.get(i));
            }
            // Circuit
            ArrayList<Circuit> circuits = databaseAccess.getCircuits();
            for (int i = 0; i < circuits.size(); i++) {
                mErgastDao.insertCircuits(circuits.get(i));
            }
            // Constructors
            ArrayList<Constructor> constructors = databaseAccess.getConstructors();
            for (int i = 0; i < constructors.size(); i++) {
                mErgastDao.insertConstructors(constructors.get(i));
            }
            // Drivers
            ArrayList<Driver> drivers = databaseAccess.getDrivers();
            for (int i = 0; i < drivers.size(); i++) {
                mErgastDao.insertDrivers(drivers.get(i));
            }
            // Qualifying
            ArrayList<Qualifying> qualifying = databaseAccess.getQualifying();
            for (int i = 0; i < qualifying.size(); i++) {
                mErgastDao.insertQualifying(qualifying.get(i));
            }
            // DriverStandings
            ArrayList<DriverStanding> driverStandings = databaseAccess.getDriverStandings();
            for (int i = 0; i < driverStandings.size(); i++) {
                mErgastDao.insertDriverStandings(driverStandings.get(i));
            }
            // ConstructorStandings
            ArrayList<ConstructorStandings> constructorStandings = databaseAccess.getConstructorStandings();
            for (int i = 0; i < constructorStandings.size(); i++) {
                mErgastDao.insertConstructorStandings(constructorStandings.get(i));
            }
            // ConstructorResults
            ArrayList<ConstructorResult> constructorResults = databaseAccess.getConstructorResults();
            for (int i = 0; i < constructorResults.size(); i++) {
                mErgastDao.insertConstructorResults(constructorResults.get(i));
            }
            // Status
            ArrayList<com.rickhuisman.formula1app.ergast.db.entities.Status> status = databaseAccess.getStatus();
            for (int i = 0; i < status.size(); i++) {
                mErgastDao.insertStatus(status.get(i));
            }
            // LapTimes
            ArrayList<LapTimes> lapTimes = databaseAccess.getLapTimes();
            for (int i = 0; i < lapTimes.size(); i++) {
                mErgastDao.insertLapTimes(lapTimes.get(i));
                System.out.println("Test - " + i);
            }

            databaseAccess.close();

            return null;
        }
    }
}