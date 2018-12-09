package com.rickhuisman.formula1app.ui;

import android.arch.lifecycle.ViewModelProviders;
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
import com.rickhuisman.formula1app.ui.RacePager.RaceOverviewTabFragment;
import com.rickhuisman.formula1app.ui.RacePager.ResultTabFragment;
import com.rickhuisman.formula1app.viewmodels.RaceDetailViewModel;

public class RaceDetailActivity extends AppCompatActivity {

    private RacePagerAdapter mRacePagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("BELGIAN GP");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int round = getIntent().getExtras().getInt("round");

        mRacePagerAdapter = new RacePagerAdapter(getSupportFragmentManager(), round);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mRacePagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.container_tab_layout);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        RaceDetailViewModel raceDetailViewModel = ViewModelProviders.of(this).get(RaceDetailViewModel.class);
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
