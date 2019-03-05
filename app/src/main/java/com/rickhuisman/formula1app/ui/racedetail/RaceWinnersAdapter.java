package com.rickhuisman.formula1app.ui.racedetail;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResultDriver;
import com.rickhuisman.formula1app.ui.driverdetail.DriverActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RaceWinnersAdapter extends RecyclerView.Adapter<RaceWinnersAdapter.RaceWinnerHolder> {

    private Context mContext;
    private List<RaceResultDriver> mResults = new ArrayList<>();

    public RaceWinnersAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RaceWinnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_race_winner, parent, false);
        return new RaceWinnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RaceWinnerHolder holder, int position) {
        final RaceResultDriver result = mResults.get(position);

        holder.year.setText(String.valueOf(result.getYear()));
        holder.driver.setText(result.getDriver().getSurName().substring(0, 3).toUpperCase());
        holder.grid.setText(String.valueOf(result.getGrid()));
        holder.time.setText(String.valueOf(result.getTime()));

        int constructorId = result.getConstructorId();
        DrawableCompat.setTint(holder.constructor.getDrawable(), mContext.getColor(getTeamColor(constructorId)));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int driverId = result.getDriver().getDriverId();
                Intent intent = new Intent(mContext, DriverActivity.class);
                intent.putExtra("driverId", driverId);
                mContext.startActivity(intent);
            }
        });
    }

    private int getTeamColor(int constructorId) {
        return mContext.getResources().getIdentifier(
                "constructor_" + constructorId, "color", mContext.getPackageName());
    }

    public void setResults(List<RaceResultDriver> results) {
        this.mResults = results;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public class RaceWinnerHolder extends RecyclerView.ViewHolder {
        private TextView year, driver, grid, time;
        private ImageView constructor;

        public RaceWinnerHolder(@NonNull View itemView) {
            super(itemView);
            year = itemView.findViewById(R.id.year);
            driver = itemView.findViewById(R.id.driver);
            grid = itemView.findViewById(R.id.grid);
            time = itemView.findViewById(R.id.time);
            constructor = itemView.findViewById(R.id.constructor);
        }
    }
}
