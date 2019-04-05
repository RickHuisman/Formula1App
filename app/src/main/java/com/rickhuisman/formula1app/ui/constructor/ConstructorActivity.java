package com.rickhuisman.formula1app.ui.constructor;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.rickhuisman.formula1app.ColorActivity;
import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.Constructor;
import com.rickhuisman.formula1app.ui.driverdetail.DriverResultsFragment;
import com.rickhuisman.formula1app.viewmodels.ConstructorViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

public class ConstructorActivity extends ColorActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constructor);

        String constructorId = getIntent().getExtras().getString("constructorId");
        String constructorName = getIntent().getExtras().getString("constructorName");

        setToolbarTitle(constructorName);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpPagerAdapter(constructorId);
        setTopAppBarColorsForTeamId(constructorId);
    }

    private void setUpPagerAdapter(String constructorId) {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), constructorId);
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(pagerAdapter);

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

        private String mConstructorId;

        private PagerAdapter(FragmentManager fm, String constructorId) {
            super(fm);
            this.mConstructorId = constructorId;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Bundle bundle = new Bundle();
                    bundle.putString("constructorId", mConstructorId);

                    ConstructorInfoFragment constructorInfoFragment = new ConstructorInfoFragment();
                    constructorInfoFragment.setArguments(bundle);

                    return constructorInfoFragment;
                case 1:
                    DriverResultsFragment fragment = new DriverResultsFragment();
                    return fragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
