package com.rickhuisman.formula1app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rickhuisman.formula1app.ui.news.NewsFragment;
import com.rickhuisman.formula1app.ui.schedule.ScheduleFragment;
import com.rickhuisman.formula1app.ui.standings.StandingsFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

//      Start fragment
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
                        case R.id.nav_news:
                            selectedFragment = new NewsFragment();
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