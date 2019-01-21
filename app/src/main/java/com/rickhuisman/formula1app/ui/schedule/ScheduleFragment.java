package com.rickhuisman.formula1app.ui.schedule;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.RaceWithWinner;
import com.rickhuisman.formula1app.viewmodels.ScheduleViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleFragment extends Fragment {

    private View mView;
    private ScheduleAdapter mAdapter;
    private ScheduleViewModel mRaceScheduleViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toolbar toolbar = mView.findViewById(R.id.toolbar);
        toolbar.setTitle("SCHEDULE");

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        RecyclerView raceList = mView.findViewById(R.id.recycler_view);
        raceList.setLayoutManager(new LinearLayoutManager(getContext()));
        raceList.setHasFixedSize(true);

        mAdapter = new ScheduleAdapter(getContext());
        raceList.setAdapter(mAdapter);

        mRaceScheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        mRaceScheduleViewModel.getRaceAndWinners(2018).observe(this, raceScheduleWithWinners);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_schedule, container, false);
        return mView;
    }

    private Observer<List<RaceWithWinner>> raceScheduleWithWinners = new Observer<List<RaceWithWinner>>() {
        @Override
        public void onChanged(List<RaceWithWinner> raceWithWinner) {
            mAdapter.setSchedule(raceWithWinner);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_schedule, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_select_season) {
            selectSeasonDialog();
        }
        return true;
    }

    private void selectSeasonDialog() {
        final String[] seasons = {"2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AlertDialog);
        builder
                .setTitle("Select Season")
                .setSingleChoiceItems(seasons, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        int season = Integer.valueOf(seasons[which]);
                        mRaceScheduleViewModel.getRaceAndWinners(season).observe(getViewLifecycleOwner(), raceScheduleWithWinners);
                        dialogInterface.dismiss();
                    }
                }).show();
    }
}