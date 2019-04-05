package com.rickhuisman.formula1app.ui.driverdetail;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.rickhuisman.formula1app.ColorActivity;
import com.rickhuisman.formula1app.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class DriverActivity extends ColorActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        String driverId = getIntent().getExtras().getString("driverId");
        String driverName = getIntent().getExtras().getString("driverName");
        String constructorId = getIntent().getExtras().getString("constructorId");

        setToolbarTitle(driverName);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpPagerAdapter(driverId);
        setTopAppBarColorsForTeamId(constructorId);
    }

    private void setUpPagerAdapter(String driverId) {
        PagerAdapter racePagerAdapter = new PagerAdapter(getSupportFragmentManager(), driverId);
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

    private class PagerAdapter extends FragmentPagerAdapter {

        private String driverId;

        private PagerAdapter(FragmentManager fm, String driverId) {
            super(fm);
            this.driverId = driverId;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Bundle bundle = new Bundle();
                    bundle.putString("driverId", driverId);

                    DriverInfoFragment driverInfoFragment = new DriverInfoFragment();
                    driverInfoFragment.setArguments(bundle);

                    return driverInfoFragment;
                default:
                    return new DriverResultsFragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
