package com.rickhuisman.formula1app.ui.racedetail;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.rickhuisman.formula1app.ColorActivity;
import com.rickhuisman.formula1app.R;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class RaceActivity extends ColorActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_detail);

        String raceName = getIntent().getExtras().getString("raceName");
        String constructorId = getIntent().getExtras().getString("constructorId");
        int season = Integer.parseInt(getIntent().getExtras().getString("season"));
        int round = Integer.parseInt(getIntent().getExtras().getString("round"));

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(raceName.replaceAll("Grand Prix","GP").toUpperCase());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (constructorId.isEmpty()) {
            setTopAppBarColorsFor(getApplicationContext().getColor(R.color.colorPrimary));
        } else {
            setTopAppBarColorsForTeamId(constructorId);
        }

        RacePagerAdapter racePagerAdapter = new RacePagerAdapter(getSupportFragmentManager(), season, round, constructorId);
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(racePagerAdapter);

        TabLayout tabLayout = findViewById(R.id.container_tab_layout);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public class RacePagerAdapter extends FragmentPagerAdapter {
        private int season;
        private int round;
        private String constructorId;

        public RacePagerAdapter(FragmentManager fm, int season, int round, String constructorId) {
            super(fm);
            this.season = season;
            this.round = round;
            this.constructorId = constructorId;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("season", season);
            bundle.putInt("round", round);
            bundle.putString("constructorId", constructorId);
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
