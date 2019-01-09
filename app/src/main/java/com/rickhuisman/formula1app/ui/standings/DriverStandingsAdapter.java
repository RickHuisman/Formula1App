package com.rickhuisman.formula1app.ui.standings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.test.db.entities.DriverStanding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DriverStandingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DriverStanding> standings = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        View header = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.header_race_result, parent, false);
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

            DriverStanding driverStanding = standings.get(position - 1);

//            String driverName = standing.getDriver().getGivenName()
//                    + " " + standing.getDriver().getFamilyName();
            String driverName = "test";

            standingHolder.driverTextView.setText(driverName.toUpperCase());
            standingHolder.teamTextView.setText("Ferrari");
            standingHolder.pointsTextView.setText(
                    String.valueOf(driverStanding.getPoints()));
            standingHolder.positionTextView.setText(
                    String.valueOf(driverStanding.getPosition()));

//            String team = standing.getConstructors().get(0).getName().replaceAll(" ", "");
//            DrawableCompat.setTint(standingHolder.teamImageView.getDrawable(),
//                    ContextCompat.getColor(mContext, getTeamColor(team)));
        }
    }

    public void setStandings(List<DriverStanding> driverStandings) {
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
