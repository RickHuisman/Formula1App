package com.rickhuisman.formula1app.ergast.db;

import android.content.Context;
import android.os.AsyncTask;

import com.rickhuisman.formula1app.ergast.db.dao.Formula1Dao;
import com.rickhuisman.formula1app.ergast.db.entities.Circuits;
import com.rickhuisman.formula1app.ergast.db.entities.Constructor;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorStandings;
import com.rickhuisman.formula1app.ergast.db.entities.Driver;
import com.rickhuisman.formula1app.ergast.db.entities.DriverStanding;
import com.rickhuisman.formula1app.ergast.db.entities.Qualifying;
import com.rickhuisman.formula1app.ergast.db.entities.Races;
import com.rickhuisman.formula1app.ergast.db.entities.Results;
import com.rickhuisman.formula1app.ergast.db.entities.Status;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {
        Races.class,
        Results.class,
        Circuits.class,
        Constructor.class,
        Driver.class,
        Qualifying.class,
        DriverStanding.class,
        ConstructorStandings.class,
        Status.class}, version = 1, exportSchema = false)
public abstract class Formula1Database extends RoomDatabase {

    private static Formula1Database instance;

    public abstract Formula1Dao noteDao();

    private static DatabaseAccess databaseAccess;

    public static synchronized Formula1Database getInstance(Context context) {
        if (instance == null) {

            databaseAccess = DatabaseAccess.getInstance(context);

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Formula1Database.class, "notedb.db")
                    .allowMainThreadQueries()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static Callback roomCallback = new Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private Formula1Dao noteDao;

        private PopulateDbAsyncTask(Formula1Database db) {
            this.noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            databaseAccess.open();

            // Races
            ArrayList<Races> races = databaseAccess.getRaces();
            for (int i = 0; i < races.size(); i++) {
                noteDao.insertRaces(races.get(i));
            }
            // Results
            ArrayList<Results> results = databaseAccess.getResults();
            for (int i = 0; i < results.size(); i++) {
                noteDao.insertResults(results.get(i));
            }
            // Circuits
            ArrayList<Circuits> circuits = databaseAccess.getCircuits();
            for (int i = 0; i < circuits.size(); i++) {
                noteDao.insertCircuits(circuits.get(i));
            }
            // Constructors
            ArrayList<Constructor> constructors = databaseAccess.getConstructors();
            for (int i = 0; i < constructors.size(); i++) {
                noteDao.insertConstructors(constructors.get(i));
            }
            // Drivers
            ArrayList<Driver> drivers = databaseAccess.getDrivers();
            for (int i = 0; i < drivers.size(); i++) {
                noteDao.insertDrivers(drivers.get(i));
            }
            // Qualifying
            ArrayList<Qualifying> qualifying = databaseAccess.getQualifying();
            for (int i = 0; i < qualifying.size(); i++) {
                noteDao.insertQualifying(qualifying.get(i));
            }
            // DriverStandings
            ArrayList<DriverStanding> driverStandings = databaseAccess.getDriverStandings();
            for (int i = 0; i < driverStandings.size(); i++) {
                noteDao.insertDriverStandings(driverStandings.get(i));
            }
            // ConstructorStandings
            ArrayList<ConstructorStandings> constructorStandings = databaseAccess.getConstructorStandings();
            for (int i = 0; i < constructorStandings.size(); i++) {
                noteDao.insertConstructorStandings(constructorStandings.get(i));
            }
            // Status
            ArrayList<com.rickhuisman.formula1app.ergast.db.entities.Status> status = databaseAccess.getStatus();
            for (int i = 0; i < status.size(); i++) {
                noteDao.insertStatus(status.get(i));
            }

            databaseAccess.close();

            return null;
        }
    }
}