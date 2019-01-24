package com.rickhuisman.formula1app.ui.standings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.Driver;
import com.rickhuisman.formula1app.ergast.db.entities.DriverStanding;
import com.rickhuisman.formula1app.ergast.db.entities.DriverStandingsWithDriver;
import com.rickhuisman.formula1app.ergast.db.entities.DriverStandingsWithDriverAndConstructor;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

public class DriverStandingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DriverStandingsWithDriverAndConstructor> standings = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View header = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.header_standing, parent, false);
        viewHolder = new HeaderHolder(header);

        if (viewType == 1) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_test, parent, false);
            return new StandingsHolder(itemView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position != 0) {
            StandingsHolder holderView = (StandingsHolder) holder;

            String driver = standings.get(position - 1).getDriver().getForeName() + " " + standings.get(position - 1).getDriver().getSurName();

            holderView.titleTextView.setText(driver.toUpperCase());
            holderView.subtitleTextView.setText(standings.get(position - 1).getConstructor().getName());
            holderView.positionTextView.setText(standings.get(position - 1).getDriverStanding().getPositionText());
            holderView.winsTextView.setText(
                    String.valueOf(standings.get(position - 1).getDriverStanding().getWins()));
            holderView.pointsTextView.setText(
                    String.valueOf((int) standings.get(position - 1).getDriverStanding().getPoints()));
        }
    }

    public void setStandings(List<DriverStandingsWithDriverAndConstructor> driverStandings) {
        this.standings = driverStandings;

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        return 1;
    }

    @Override
    public int getItemCount() {
        return standings.size() + 1;
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {

        private HeaderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class StandingsHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView subtitleTextView;
        private TextView positionTextView;
        private TextView winsTextView;
        private TextView pointsTextView;

        private StandingsHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            subtitleTextView = itemView.findViewById(R.id.subtitle_text_view);
            positionTextView = itemView.findViewById(R.id.position_text_view);
            winsTextView = itemView.findViewById(R.id.wins_text_view);
            pointsTextView = itemView.findViewById(R.id.points_text_view);
        }
    }
}
