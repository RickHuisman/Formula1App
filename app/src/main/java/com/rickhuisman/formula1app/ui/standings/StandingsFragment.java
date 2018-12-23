package com.rickhuisman.formula1app.ui.standings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.rickhuisman.formula1app.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

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
