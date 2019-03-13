package com.rickhuisman.formula1app.ui.racedetail;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.rickhuisman.formula1app.ColorActivity;
import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.Race;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResult;
import com.rickhuisman.formula1app.viewmodels.RaceViewModel;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

public class RaceActivity extends ColorActivity {

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
                    setTopAppBarColorsFor(getApplicationContext().getColor(R.color.colorPrimary));
                } else {
                    setTopAppBarColorsForTeamId(raceResults.get(0).getResult().getConstructorId());
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
