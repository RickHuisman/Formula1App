package com.rickhuisman.formula1app.ergast.test.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.rickhuisman.formula1app.ergast.test.DatabaseAccess;
import com.rickhuisman.formula1app.ergast.test.db.dao.Formula1Dao;
import com.rickhuisman.formula1app.ergast.test.db.entities.Races;
import com.rickhuisman.formula1app.ergast.test.db.entities.Results;

import java.util.ArrayList;

@Database(entities = {Races.class, Results.class}, version = 1, exportSchema = false)
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

            databaseAccess.close();

            return null;
        }
    }
}