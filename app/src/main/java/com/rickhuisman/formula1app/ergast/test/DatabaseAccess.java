package com.rickhuisman.formula1app.ergast.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rickhuisman.formula1app.ergast.test.db.entities.Races;
import com.rickhuisman.formula1app.ergast.test.db.entities.Results;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public ArrayList<Races> getRaces() {
        Cursor test = db.rawQuery("SELECT * FROM races", new String[]{});

        ArrayList<Races> races = new ArrayList<>();
        while (test.moveToNext()) {
            races.add(new Races(
                    test.getInt(0),
                    test.getInt(1),
                    test.getInt(2),
                    test.getInt(3),
                    test.getString(4),
                    test.getString(5),
                    test.getString(6),
                    test.getString(7)));
        }
        test.close();
        return races;
    }

    public ArrayList<Results> getResults() {
        Cursor test = db.rawQuery("SELECT * FROM results", new String[]{});

        ArrayList<Results> results = new ArrayList<>();
        while (test.moveToNext()) {
            results.add(new Results(
                    test.getInt(0),
                    test.getInt(1),
                    test.getInt(2),
                    test.getInt(3),
                    test.getInt(4),
                    test.getInt(5),
                    test.getInt(6),
                    test.getString(7),
                    test.getInt(8),
                    test.getFloat(9),
                    test.getInt(10),
                    test.getString(11),
                    test.getInt(12),
                    test.getInt(13),
                    test.getInt(14),
                    test.getString(15),
                    test.getString(16),
                    test.getInt(17)));
        }
        test.close();
        return results;
    }
}
