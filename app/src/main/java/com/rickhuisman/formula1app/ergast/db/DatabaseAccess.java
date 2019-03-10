package com.rickhuisman.formula1app.ergast.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;

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

    public ArrayList<Race> getRaces() {
        Cursor test = db.rawQuery("SELECT * FROM races", new String[]{});

        ArrayList<Race> races = new ArrayList<>();
        while (test.moveToNext()) {
            races.add(new Race(
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

    public ArrayList<Result> getResults() {
        Cursor test = db.rawQuery("SELECT * FROM results", new String[]{});

        ArrayList<Result> results = new ArrayList<>();
        while (test.moveToNext()) {
            results.add(new Result(
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

    public ArrayList<Circuit> getCircuits() {
        Cursor test = db.rawQuery("SELECT * FROM circuits", new String[]{});

        ArrayList<Circuit> circuits = new ArrayList<>();
        while (test.moveToNext()) {
            circuits.add(new Circuit(
                    test.getInt(0),
                    test.getString(1),
                    test.getString(2),
                    test.getString(3),
                    test.getString(4),
                    test.getFloat(5),
                    test.getFloat(6),
                    test.getInt(7),
                    test.getString(8)));
        }
        test.close();
        return circuits;
    }

    public ArrayList<Constructor> getConstructors() {
        Cursor test = db.rawQuery("SELECT * FROM constructors", new String[]{});

        ArrayList<Constructor> constructors = new ArrayList<>();
        while (test.moveToNext()) {
            constructors.add(new Constructor(
                    test.getInt(0),
                    test.getString(1),
                    test.getString(2),
                    test.getString(3),
                    test.getString(4)));
        }
        test.close();
        return constructors;
    }

    public ArrayList<Driver> getDrivers() {
        Cursor test = db.rawQuery("SELECT * FROM drivers", new String[]{});

        ArrayList<Driver> drivers = new ArrayList<>();
        while (test.moveToNext()) {
            drivers.add(new Driver(
                    test.getInt(0),
                    test.getString(1),
                    test.getInt(2),
                    test.getString(3),
                    test.getString(4),
                    test.getString(5),
                    test.getString(6),
                    test.getString(7),
                    test.getString(8)));
        }
        test.close();
        return drivers;
    }

    public ArrayList<Qualifying> getQualifying() {
        Cursor test = db.rawQuery("SELECT * FROM qualifying", new String[]{});

        ArrayList<Qualifying> qualifying = new ArrayList<>();
        while (test.moveToNext()) {
            qualifying.add(new Qualifying(
                    test.getInt(0),
                    test.getInt(1),
                    test.getInt(2),
                    test.getInt(3),
                    test.getInt(4),
                    test.getInt(5),
                    test.getString(6),
                    test.getString(7),
                    test.getString(8)));
        }
        test.close();
        return qualifying;
    }

    public ArrayList<DriverStanding> getDriverStandings() {
        Cursor test = db.rawQuery("SELECT * FROM driverStandings", new String[]{});

        ArrayList<DriverStanding> driverStandings = new ArrayList<>();
        while (test.moveToNext()) {
            driverStandings.add(new DriverStanding(
                    test.getInt(0),
                    test.getInt(1),
                    test.getInt(2),
                    test.getInt(3),
                    test.getInt(4),
                    test.getString(5),
                    test.getInt(6)));
        }
        test.close();
        return driverStandings;
    }

    public ArrayList<ConstructorStandings> getConstructorStandings() {
        Cursor test = db.rawQuery("SELECT * FROM constructorStandings", new String[]{});

        ArrayList<ConstructorStandings> constructorStandings = new ArrayList<>();
        while (test.moveToNext()) {
            constructorStandings.add(new ConstructorStandings(
                    test.getInt(0),
                    test.getInt(1),
                    test.getInt(2),
                    test.getInt(3),
                    test.getInt(4),
                    test.getString(5),
                    test.getInt(6)));
        }
        test.close();
        return constructorStandings;
    }

    public ArrayList<ConstructorResult> getConstructorResults() {
        Cursor test = db.rawQuery("SELECT * FROM constructorResults", new String[]{});

        ArrayList<ConstructorResult> constructorResults = new ArrayList<>();
        while (test.moveToNext()) {
            constructorResults.add(new ConstructorResult(
                    test.getInt(0),
                    test.getInt(1),
                    test.getInt(2),
                    test.getInt(3),
                    test.getString(4)));
        }
        test.close();
        return constructorResults;
    }

    public ArrayList<Status> getStatus() {
        Cursor test = db.rawQuery("SELECT * FROM status", new String[]{});

        ArrayList<Status> statusList = new ArrayList<>();
        while (test.moveToNext()) {
            statusList.add(new Status(
                    test.getInt(0),
                    test.getString(1)));
        }
        test.close();
        return statusList;
    }

    public ArrayList<LapTimes> getLapTimes() {
        Cursor test = db.rawQuery("SELECT * FROM lapTimes", new String[]{});

        ArrayList<LapTimes> lapTimes = new ArrayList<>();
        while (test.moveToNext()) {
            lapTimes.add(new LapTimes(
                    test.getInt(0),
                    test.getInt(1),
                    test.getInt(2),
                    test.getInt(3),
                    test.getString(4),
                    test.getInt(5)));
        }
        test.close();
        return lapTimes;
    }
}
