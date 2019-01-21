package com.rickhuisman.formula1app.ui.driverdetail;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.rickhuisman.formula1app.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class DriverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int driverId = getIntent().getExtras().getInt("driverId");

        SetUpPagerAdapter();
    }

    private void SetUpPagerAdapter() {
        PagerAdapter racePagerAdapter = new PagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(racePagerAdapter);

        TabLayout tabLayout = findViewById(R.id.container_tab_layout);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        private PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new DriverResultsFragment();
                case 1:
                    return new DriverResultsFragment();
                case 2:
                    return new DriverResultsFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
