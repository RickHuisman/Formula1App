package com.rickhuisman.formula1app.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.ConstructorStandings;
import com.rickhuisman.formula1app.ergast.models.DriverStandings;
import com.rickhuisman.formula1app.ergast.models.StandingsLists;

import java.util.ArrayList;
import java.util.List;

public class StandingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<DriverStandings> mDriverStandings = new ArrayList<>();
    private List<ConstructorStandings> mConstructorStandings = new ArrayList<>();
    private int mStandingType;

    public StandingsAdapter(int standingType) {
        this.mStandingType = standingType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        View header = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.header_standing, parent, false);
        viewHolder = new HeaderHolder(header);

        if (viewType == 1) {
            if (mStandingType == 0) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_standing, parent, false);
                return new DriverStandingsHolder(itemView);
            } else {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_standing, parent, false);
                return new DriverStandingsHolder(itemView);
            }
        }

        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0)
            return;

        if (holder instanceof DriverStandingsHolder) {
            DriverStandingsHolder driverHolder = (DriverStandingsHolder) holder;

            if (mStandingType == 0) {
                DriverStandings standing = mDriverStandings.get(position - 1);
                driverHolder.driverTextView.setText((standing.getDriver().getGivenName() + " " + standing.getDriver().getFamilyName()).toUpperCase());
                driverHolder.teamTextView.setText(standing.getConstructors().get(0).getName());
                driverHolder.pointsTextView.setText(standing.getPoints());
                driverHolder.positionTextView.setText(standing.getPosition());

                String team = standing.getConstructors().get(0).getName().replaceAll(" ", "");
                DrawableCompat.setTint(driverHolder.teamImageView.getDrawable(),
                    ContextCompat.getColor(mContext, getTeamColor(team)));

            } else if (mStandingType == 1) {
                ConstructorStandings standing = mConstructorStandings.get(position - 1);

                driverHolder.driverTextView.setText(standing.getConstructor().getName());

                driverHolder.teamTextView.setText(standing.getConstructor().getName());
                driverHolder.pointsTextView.setText(standing.getPoints());
                driverHolder.positionTextView.setText(standing.getPosition());

                String team = standing.getConstructor().getName().replaceAll(" ", "");
                DrawableCompat.setTint(driverHolder.teamImageView.getDrawable(),
                        ContextCompat.getColor(mContext, getTeamColor(team)));
            }

//            driverHolder.driverTextView.setText((standing.getDriver().getGivenName() + " " + standing.getDriver().getFamilyName()).toUpperCase());
//            driverHolder.teamTextView.setText(standing.getConstructors().get(0).getName());
//            driverHolder.pointsTextView.setText(standing.getPoints());
//            driverHolder.positionTextView.setText(standing.getPosition());
//
//            String team = standing.getConstructors().get(0).getName().replaceAll(" ", "");
//            DrawableCompat.setTint(driverHolder.teamImageView.getDrawable(),
//                    ContextCompat.getColor(mContext, getTeamColor(team)));
        }
    }

    private int getTeamColor(String team) {
        return mContext.getResources().getIdentifier(
                "color" + team, "color", mContext.getPackageName());
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        return 1;
    }

    @Override
    public int getItemCount() {
        if (mStandingType == 0) {
            return mDriverStandings.size() + 1;
        } else {
            return mConstructorStandings.size() + 1;
        }
    }

    public void setStandings(List<StandingsLists> standingsLists) {
        if (mStandingType == 0) {
            this.mDriverStandings = standingsLists.get(0).getDriverStandings();
        } else if (mStandingType == 1) {
            this.mConstructorStandings = standingsLists.get(0).getConstructorStandings();
        }
        notifyDataSetChanged();
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {

        private HeaderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class DriverStandingsHolder extends RecyclerView.ViewHolder {
        private TextView positionTextView;
        private TextView driverTextView;
        private TextView teamTextView;
        private TextView pointsTextView;
        private ImageView teamImageView;

        public DriverStandingsHolder(View itemView) {
            super(itemView);
            positionTextView = itemView.findViewById(R.id.position_text_view);
            driverTextView = itemView.findViewById(R.id.driver_text_view);
            teamTextView = itemView.findViewById(R.id.team_text_view);
            pointsTextView = itemView.findViewById(R.id.points_text_view);

            teamImageView = itemView.findViewById(R.id.team_image_view);
        }
    }
}
