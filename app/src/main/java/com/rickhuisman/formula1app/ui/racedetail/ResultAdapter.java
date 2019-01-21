package com.rickhuisman.formula1app.ui.racedetail;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.RaceResultWithDriverAndStatus;
import com.rickhuisman.formula1app.ui.driverdetail.DriverActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<RaceResultWithDriverAndStatus> mResults = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        RecyclerView.ViewHolder viewHolder;
        View header = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.header_race_result, parent, false);
        viewHolder = new HeaderHolder(header);

        if (viewType == 1) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_race_result, parent, false);
            return new RaceResultHolder(itemView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (position != 0) {
            RaceResultHolder resultHolder = (RaceResultHolder) holder;
            RaceResultWithDriverAndStatus result = mResults.get(position - 1);

            resultHolder.positionTextView.setText(result.getResult().getPositionText());

            String driver = result.getDriver().getSurName();
            resultHolder.driverTextView.setText(driver.substring(0, 3).toUpperCase());

            int points = (int) result.getResult().getPoints();
            resultHolder.pointsTextView.setText(String.valueOf(points));

            if (result.getResult().getTime() != null) {
                resultHolder.timeTextView.setText(result.getResult().getTime());
            } else {
                resultHolder.timeTextView.setText(result.getStatus().getStatus());
            }

            int constructorId = result.getResult().getConstructorId();
            DrawableCompat.setTint(resultHolder.teamImageView.getDrawable(),
                    ContextCompat.getColor(mContext, getTeamColor(constructorId)));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int driverId = mResults.get(holder.getAdapterPosition() - 1).getDriver().getDriverId();

                    Intent intent = new Intent(mContext, DriverActivity.class);
                    intent.putExtra("driverId", driverId);

                    mContext.startActivity(intent);
                }
            });
        }
    }

    private int getTeamColor(int constructorId) {
        return mContext.getResources().getIdentifier(
                "constructor_" + constructorId, "color", mContext.getPackageName());
    }

    public void setRaceResultsWithDriver(List<RaceResultWithDriverAndStatus> results) {
        this.mResults = results;
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
            positionTextView = itemView.findViewById(R.id.position_text_view);
            driverTextView = itemView.findViewById(R.id.driver_text_view);
            timeTextView = itemView.findViewById(R.id.time_text_view);
            pointsTextView = itemView.findViewById(R.id.points_text_view);
            teamImageView = itemView.findViewById(R.id.team_image_view);
        }
    }
}
