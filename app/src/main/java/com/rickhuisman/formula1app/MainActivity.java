package com.rickhuisman.formula1app;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.rickhuisman.formula1app.ergast.test.db.Formula1Database;
import com.rickhuisman.formula1app.ergast.test.db.dao.Formula1Dao;
import com.rickhuisman.formula1app.ergast.test.db.entities.Races;
import com.rickhuisman.formula1app.ergast.test.db.entities.Results;
import com.rickhuisman.formula1app.ui.schedule.ScheduleFragment;
import com.rickhuisman.formula1app.ui.standings.StandingsFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        Context context = getApplicationContext();
        final Formula1Database db = Formula1Database.getInstance(context);
        final Formula1Dao dao = db.noteDao();

//        dao.getAllRaces(2018).observe(this, new Observer<List<Races>>() {
//            @Override
//            public void onChanged(@Nullable List<Races> races) {
//                for (int i = 0; i < races.size(); i++) {
//                    System.out.println(races.get(i).toString());
//                }
//            }
//        });

//        dao.getAllResults().observe(this, new Observer<List<Results>>() {
//            @Override
//            public void onChanged(@Nullable List<Results> results) {
//                for (int i = 0; i < results.size(); i++) {
//                    System.out.println(results.get(i).toString());
//                }
//            }
//        });

        for (int i = 0; i < dao.getRacesAndWinners().length; i++) {
            System.out.println("Race = " + i + " - Winner = " + dao.getRacesAndWinners()[i].driverId);
        }

        // Start fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new ScheduleFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_schedule:
                            selectedFragment = new ScheduleFragment();
                            break;
                        case R.id.nav_standings:
                            selectedFragment = new StandingsFragment();
                            break;
                    }

                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, selectedFragment).commit();
                    }
                    return true;
                }
            };
}