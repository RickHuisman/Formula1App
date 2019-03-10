package com.rickhuisman.formula1app.ui.racedetail;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.Constructor;
import com.rickhuisman.formula1app.ergast.db.entities.Race;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResult;
import com.rickhuisman.formula1app.viewmodels.RaceViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

public class RaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_detail);

        int raceId = getIntent().getExtras().getInt("raceId");

        RaceViewModel raceViewModel = ViewModelProviders.of(this).get(RaceViewModel.class);
        raceViewModel.getRaceResult(raceId).observe(this, new Observer<List<RaceResult>>() {
            @Override
            public void onChanged(List<RaceResult> raceResults) {
                if (raceResults.size() == 0) {
                    setTopAppBarColors(111);
                } else {
                    setTopAppBarColors(raceResults.get(0).getResult().getConstructorId());
                }
            }
        });

        final Toolbar toolbar = findViewById(R.id.toolbar);
        raceViewModel.getRace(raceId).observe(this, new Observer<Race>() {
            @Override
            public void onChanged(Race race) {
                String raceName = race.getName().replaceAll("Grand Prix","GP").toUpperCase();
                toolbar.setTitle(raceName);
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RacePagerAdapter racePagerAdapter = new RacePagerAdapter(getSupportFragmentManager(), raceId);
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(racePagerAdapter);

        TabLayout tabLayout = findViewById(R.id.container_tab_layout);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    private void setTopAppBarColors(int constructorId) {
        int color = getTeamColor(constructorId);

        getWindow().setStatusBarColor(getDarkerTeamColorId(constructorId));

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(color)));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        TabLayout tabLayout = findViewById(R.id.container_tab_layout);
        tabLayout.setBackground(new ColorDrawable(getColor(color)));

        if (getContentColor(constructorId)) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitleTextColor(Color.BLACK);

            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black);

            tabLayout.setTabTextColors(getColor(R.color.colorBlackTabInactive), Color.BLACK);
            tabLayout.setSelectedTabIndicatorColor(Color.BLACK);

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private boolean getContentColor(int constructorId) {
        int teamColorId = getTeamColor(constructorId);
        String colorHex = "#" + Integer.toHexString(getColor(teamColorId)).substring(2, 8);

        int red = Integer.parseInt(colorHex.substring(1, 3), 16);
        int green = Integer.parseInt(colorHex.substring(3, 5), 16);
        int blue = Integer.parseInt(colorHex.substring(5, 7), 16);

        double color = (red * 0.299) + (green * 0.587) + (blue * 0.114);

        return color > 140;
    }

    private int getDarkerTeamColorId(int constructorId) {
        float[] hsv = new float[3];
        int color = getColor(getTeamColor(constructorId));
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.85f;

        return Color.HSVToColor(hsv);
    }

    private int getTeamColor(int constructorId) {
        return getResources().getIdentifier("constructor_" + constructorId, "color", getPackageName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public class RacePagerAdapter extends FragmentPagerAdapter {
        int raceId;

        public RacePagerAdapter(FragmentManager fm, int raceId) {
            super(fm);
            this.raceId = raceId;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("raceId", raceId);
            ResultTabFragment resultTabFragment = new ResultTabFragment();

            switch (position) {
                case 1:
                    bundle.putInt("resultType", 0);
                    resultTabFragment.setArguments(bundle);

                    return resultTabFragment;
                case 2:
                    bundle.putInt("resultType", 1);
                    resultTabFragment.setArguments(bundle);

                    return resultTabFragment;
                default:
                    RaceInfoTabFragment raceInfoTabFragment = new RaceInfoTabFragment();
                    raceInfoTabFragment.setArguments(bundle);
                    return raceInfoTabFragment;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
