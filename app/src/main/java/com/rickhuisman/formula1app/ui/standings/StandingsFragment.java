package com.rickhuisman.formula1app.ui.standings;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.viewmodels.ScheduleViewModel;

public class StandingsFragment extends Fragment {

    private View mView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar = mView.findViewById(R.id.toolbar);
        toolbar.setTitle("STANDINGS");

        StandingsPagerAdapter resultsPagerAdapter = new StandingsPagerAdapter(getActivity().getSupportFragmentManager());
        ViewPager viewPager = mView.findViewById(R.id.container);
        viewPager.setAdapter(resultsPagerAdapter);

        TabLayout tabLayout = mView.findViewById(R.id.container_tab_layout);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_standings, container, false);
        return mView;
    }

    public class StandingsPagerAdapter extends FragmentStatePagerAdapter {

        public StandingsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            StandingsTabFragment standingsTabFragment = new StandingsTabFragment();
            Bundle bundle = new Bundle();

            switch (position) {
                case 0:
                    bundle.putInt("resultType", 0);
                    standingsTabFragment.setArguments(bundle);

                    return standingsTabFragment;
                case 1:
                    bundle.putInt("resultType", 1);
                    standingsTabFragment.setArguments(bundle);

                    return standingsTabFragment;
                default:
                    return standingsTabFragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
