package com.rickhuisman.formula1app.ui.standings;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorStandings;
import com.rickhuisman.formula1app.ergast.db.entities.ConstructorStandingsWithConstructorAndDrivers;
import com.rickhuisman.formula1app.ui.constructor.ConstructorActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ConstructorStandingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ConstructorStandingsWithConstructorAndDrivers> mStandings = new ArrayList<>();

    public ConstructorStandingsAdapter(Context mContext) {
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
                    .inflate(R.layout.item_constructor_standing, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position != 0) {
            StandingsHolder standingHolder = (StandingsHolder) holder;

            final ConstructorStandings constructorStanding = mStandings.get(position - 1).getConstructorStandings();

            standingHolder.constructor.setText(mStandings.get(position - 1).getConstructor().getName().toUpperCase());

            standingHolder.driver.setText("Hamilton - Bottas");

            int points = (int) constructorStanding.getPoints();
            standingHolder.pointsTextView.setText(String.valueOf(points));

            standingHolder.wins.setText(String.valueOf(mStandings.get(position - 1).getConstructorStandings().getWins()));

            standingHolder.positionTextView.setText(constructorStanding.getPositionText());

            int constructorId = mStandings.get(position - 1).getConstructor().getConstructorId();
            DrawableCompat.setTint(standingHolder.teamImageView.getDrawable(), mContext.getColor(getTeamColor(constructorId)));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int constructorId = constructorStanding.getConstructorId();

                    Intent intent = new Intent(mContext, ConstructorActivity.class);
                    intent.putExtra("constructorId", constructorId);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    private int getTeamColor(int constructorId) {
        return mContext.getResources().getIdentifier(
                "constructor_" + constructorId, "color", mContext.getPackageName());
    }

    public void setStandings(List<ConstructorStandingsWithConstructorAndDrivers> constructorStandings) {
        this.mStandings = constructorStandings;

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
        private TextView positionTextView;
        private TextView constructor;
        private TextView driver;
        private TextView pointsTextView;
        private TextView wins;
        private ImageView teamImageView;

        public StandingsHolder(View itemView) {
            super(itemView);
            positionTextView = itemView.findViewById(R.id.year);
            constructor = itemView.findViewById(R.id.constructor);
            driver = itemView.findViewById(R.id.driver);
            pointsTextView = itemView.findViewById(R.id.time);
            wins = itemView.findViewById(R.id.wins);

            teamImageView = itemView.findViewById(R.id.constructor_image);
        }
    }
}
