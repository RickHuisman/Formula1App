package com.rickhuisman.formula1app.ui.driverdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.Results;
import com.rickhuisman.formula1app.viewmodels.DriverDetailViewModel;

import java.util.List;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DriverResultsFragment extends Fragment {

    private View mView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView imageView = mView.findViewById(R.id.select_season_image_view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(getContext(), view);

                menu.inflate(R.menu.menu_schedule);
                menu.show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_driver_results, container, false);

        DriverDetailViewModel viewModel = ViewModelProviders.of(this).get(DriverDetailViewModel.class);

        viewModel.getResultsForDriverId(20, 2013).observe(this, new Observer<List<Results>>() {
            @Override
            public void onChanged(List<Results> results) {
                for (Results result: results) {
                    System.out.println(result.getRaceId() + " - " + result.getPosition());
                }
            }
        });

        return mView;
    }
}
