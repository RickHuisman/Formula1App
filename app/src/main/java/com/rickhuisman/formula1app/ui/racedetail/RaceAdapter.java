package com.rickhuisman.formula1app.ui.racedetail;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResult;
import com.rickhuisman.formula1app.ergast.db.entities.Result;
import com.rickhuisman.formula1app.ui.driverdetail.DriverActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RaceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<RaceResult> mResults = new ArrayList<>();

    public RaceAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return  new HeaderHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.header_race_result, parent, false));
        } else {
            return new RaceResultHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_race_result, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (position != 0) {
            RaceResultHolder resultHolder = (RaceResultHolder) holder;
            RaceResult raceResult = mResults.get(position - 1);
            final Result result = raceResult.getResult();

            resultHolder.positionTextView.setText(result.getPositionText());

            String driver = raceResult.getDriver().getSurName();
            resultHolder.driverTextView.setText(driver.substring(0, 3).toUpperCase());

            int points = (int) result.getPoints();
            resultHolder.pointsTextView.setText(String.valueOf(points));

            if (raceResult.getResult().getTime() != null) {
                resultHolder.timeTextView.setText(result.getTime());
            } else {
                resultHolder.timeTextView.setText(raceResult.getStatus().getStatus());
            }

            int constructorId = result.getConstructorId();
            DrawableCompat.setTint(resultHolder.teamImageView.getDrawable(),
                    ContextCompat.getColor(mContext, getTeamColor(constructorId)));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, DriverActivity.class);
                    intent.putExtra("driverId", result.getDriverId());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    private int getTeamColor(int constructorId) {
        return mContext.getResources().getIdentifier(
                "constructor_" + constructorId, "color", mContext.getPackageName());
    }

    public void setRaceResult(List<RaceResult> results) {
        this.mResults = results;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return mResults.size() + 1;
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {

        private HeaderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class RaceResultHolder extends RecyclerView.ViewHolder {
        private TextView positionTextView;
        private TextView driverTextView;
        private TextView timeTextView;
        private TextView pointsTextView;
        private ImageView teamImageView;

        private RaceResultHolder(View itemView) {
            super(itemView);
            positionTextView = itemView.findViewById(R.id.year);
            driverTextView = itemView.findViewById(R.id.driver);
            timeTextView = itemView.findViewById(R.id.grid);
            pointsTextView = itemView.findViewById(R.id.time);
            teamImageView = itemView.findViewById(R.id.constructor);
        }
    }
}
