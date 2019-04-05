package com.rickhuisman.formula1app.ui.standings;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Driver;
import com.rickhuisman.formula1app.ergast.models.DriverStandings;
import com.rickhuisman.formula1app.ui.driverdetail.DriverActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

public class DriverStandingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<DriverStandings> mStandings = new ArrayList<>();

    public DriverStandingsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new HeaderHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.header_standing, parent, false));
        } else {
            return new StandingsHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.item_driver_standing, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position != 0) {
            StandingsHolder standingsHolder = (StandingsHolder) holder;

            final Driver driver = mStandings.get(position - 1).getDriver();
            DriverStandings driverStanding = mStandings.get(position - 1);

            final String driverName = driver.getGivenName() + " " + driver.getFamilyName();
            standingsHolder.driver.setText(driverName.toUpperCase());

            standingsHolder.position.setText(driverStanding.getPositionText());
            standingsHolder.wins.setText(driverStanding.getWins());

            standingsHolder.points.setText(driverStanding.getPoints());

            final String constructorId = mStandings.get(position - 1).getConstructors().get(0).getConstructorId();
            DrawableCompat.setTint(standingsHolder.constructor.getDrawable(), mContext.getColor(getTeamColor(constructorId)));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, DriverActivity.class);
                    intent.putExtra("driverId", driver.getDriverId());
                    intent.putExtra("driverName", driverName);
                    intent.putExtra("constructorId", constructorId);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    private int getTeamColor(String constructorId) {
        return mContext.getResources().getIdentifier(
                "constructor_" + constructorId, "color", mContext.getPackageName());
    }

    public void setStandings(List<DriverStandings> standings) {
        this.mStandings = standings;

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return mStandings.size() + 1;
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {

        private HeaderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class StandingsHolder extends RecyclerView.ViewHolder {
        private TextView position;
        private ImageView constructor;
        private TextView driver;
        private TextView wins;
        private TextView points;

        private StandingsHolder(View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.year);
            constructor = itemView.findViewById(R.id.constructor);
            driver = itemView.findViewById(R.id.driver);
            wins = itemView.findViewById(R.id.wins);
            points = itemView.findViewById(R.id.time);
        }
    }
}
