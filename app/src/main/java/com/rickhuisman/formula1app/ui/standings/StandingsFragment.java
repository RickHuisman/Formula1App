package com.rickhuisman.formula1app.ui.standings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.rickhuisman.formula1app.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class StandingsFragment extends Fragment {

    private View mView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TabLayout tabLayout = mView.findViewById(R.id.container_tab_layout);

        ViewPager viewPager = mView.findViewById(R.id.container);
        viewPager.setAdapter(new StandingsPagerAdapter());

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

        private static final int DRIVER_STANDINGS = 0;
        private static final int CONSTRUCTOR_STANDINGS = 1;

        public StandingsPagerAdapter() {
            super(getActivity().getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            StandingsTabFragment standingsTabFragment = new StandingsTabFragment();
            Bundle bundle = new Bundle();

            int type = position == 0 ? DRIVER_STANDINGS : CONSTRUCTOR_STANDINGS;

            bundle.putInt("standingsType", type);
            standingsTabFragment.setArguments(bundle);

            return standingsTabFragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
