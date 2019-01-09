package com.rickhuisman.formula1app.ui.standings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.test.db.entities.Driver;
import com.rickhuisman.formula1app.ergast.test.db.entities.DriverStanding;
import com.rickhuisman.formula1app.ergast.test.db.entities.DriverStandingsWithDriver;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

public class DriverStandingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<DriverStandingsWithDriver> standings = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        RecyclerView.ViewHolder viewHolder;
        View header = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.header_standing, parent, false);
        viewHolder = new HeaderHolder(header);

        if (viewType == 1) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_standing, parent, false);
            return new StandingsHolder(itemView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position != 0) {
            StandingsHolder standingHolder = (StandingsHolder) holder;

            DriverStanding driverStanding = standings.get(position - 1).getDriverStanding();
            Driver driver = standings.get(position - 1).getDriver();

            String driverName = driver.getForeName() + " " + driver.getSurName();

            standingHolder.driverTextView.setText(driverName.toUpperCase());
            standingHolder.teamTextView.setText("Ferrari");

            int points = (int) driverStanding.getPoints();
            standingHolder.pointsTextView.setText(String.valueOf(points));

            standingHolder.positionTextView.setText(
                    String.valueOf(driverStanding.getPosition()));

            int constructorId = 6;
            setTeamImageColor(standingHolder.teamImageView, constructorId);
        }
    }

    private void setTeamImageColor(ImageView imageView, int constructorId) {
        DrawableCompat.setTint(imageView.getDrawable(),
                ContextCompat.getColor(mContext, getTeamColor(constructorId)));
    }

    private int getTeamColor(int constructorId) {
        return mContext.getResources().getIdentifier(
                "constructor_" + constructorId, "color", mContext.getPackageName());
    }

    public void setStandings(List<DriverStandingsWithDriver> driverStandings) {
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
        private TextView positionTextView;
        private TextView driverTextView;
        private TextView teamTextView;
        private TextView pointsTextView;
        private ImageView teamImageView;

        public StandingsHolder(View itemView) {
            super(itemView);
            positionTextView = itemView.findViewById(R.id.position_text_view);
            driverTextView = itemView.findViewById(R.id.driver_text_view);
            teamTextView = itemView.findViewById(R.id.team_text_view);
            pointsTextView = itemView.findViewById(R.id.points_text_view);

            teamImageView = itemView.findViewById(R.id.team_image_view);
        }
    }
}
