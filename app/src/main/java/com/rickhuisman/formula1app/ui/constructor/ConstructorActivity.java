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

        int constructorId = getIntent().getExtras().getInt("constructorId");

        ConstructorViewModel constructorViewModel = ViewModelProviders.of(this).get(ConstructorViewModel.class);
        constructorViewModel.getConstructor(constructorId).observe(this, new Observer<Constructor>() {
            @Override
            public void onChanged(Constructor constructor) {
                setToolbarTitle(constructor.getName());
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        setTopAppBarColorsForTeamId(constructorId);
        setUpPagerAdapter(constructorId);
    }

    private void setUpPagerAdapter(int constructorId) {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
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

        private PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    ConstructorInfoFragment constructorInfoFragment = new ConstructorInfoFragment();
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
