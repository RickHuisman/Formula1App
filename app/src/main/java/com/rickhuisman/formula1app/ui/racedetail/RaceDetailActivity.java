package com.rickhuisman.formula1app.ui.racedetail;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.rickhuisman.formula1app.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class RaceDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_detail);

        int raceId = getIntent().getExtras().getInt("raceId");

        String raceName = getIntent().getExtras().getString("raceName");
        raceName = raceName.replaceAll("Grand Prix","GP").toUpperCase();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(raceName);

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
            finish();
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
                    return new RaceOverviewTabFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
