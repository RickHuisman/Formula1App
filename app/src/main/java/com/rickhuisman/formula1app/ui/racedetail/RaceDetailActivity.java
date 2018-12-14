package com.rickhuisman.formula1app.ui.racedetail;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ui.racedetail.RaceOverviewTabFragment;
import com.rickhuisman.formula1app.ui.racedetail.ResultTabFragment;

public class RaceDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_detail);

        String raceName = getIntent().getExtras().getString("raceName");
        raceName = raceName.replaceAll("Grand Prix","GP").toUpperCase();

        int round = getIntent().getExtras().getInt("round");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(raceName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RacePagerAdapter racePagerAdapter = new RacePagerAdapter(getSupportFragmentManager(), round);
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(racePagerAdapter);

        TabLayout tabLayout = findViewById(R.id.container_tab_layout);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public class RacePagerAdapter extends FragmentPagerAdapter {
        int round;

        public RacePagerAdapter(FragmentManager fm, int round) {
            super(fm);
            this.round = round;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("round", round);
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
                    return new RaceOverviewTabFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
