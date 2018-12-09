package com.rickhuisman.formula1app.ui.RacePager;

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
import com.rickhuisman.formula1app.ergast.models.Results;

import java.util.ArrayList;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private int mResultType;
    private List<Results> mResults = new ArrayList<>();

    public ResultAdapter(Context context, int resultType) {
        this.mContext = context;
        this.mResultType = resultType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        if (mResultType == 0) {
            View header = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_qualifying_result, parent, false);
            viewHolder = new HeaderHolder(header);
        } else {
            View header = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_race_result, parent, false);
            viewHolder = new HeaderHolder(header);
        }

        if (viewType == 1) {
            if (mResultType == 0) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_qualifying_result, parent, false);

                return new QualifyingResultHolder(itemView);
            } else if (mResultType == 1) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_race_result, parent, false);

                return new RaceResultHolder(itemView);
            }
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position != 0) {
            Results result = mResults.get(position - 1);
            String driver = result.getDriver().getFamilyName();
            String team = result.getConstructor().getName().replaceAll(" ", "");

            if (holder instanceof QualifyingResultHolder) {

                QualifyingResultHolder resultHolder = (QualifyingResultHolder) holder;

                resultHolder.positionTextView.setText(result.getPosition());
                resultHolder.driverTextView.setText(driver.substring(0, 3).toUpperCase());
                resultHolder.q1TimeTextView.setText(result.getQualifyingOne());
                resultHolder.q2TimeTextView.setText(result.getQualifyingTwo());
                resultHolder.q3TimeTextView.setText(result.getQualifyingThree());

                DrawableCompat.setTint(resultHolder.teamImageView.getDrawable(),
                        ContextCompat.getColor(mContext, getTeamColor(team)));

            } else if (holder instanceof RaceResultHolder) {

                RaceResultHolder resultHolder = (RaceResultHolder) holder;

                resultHolder.positionTextView.setText(result.getPosition());
                resultHolder.driverTextView.setText(driver.substring(0, 3).toUpperCase());
                resultHolder.pointsTextView.setText(result.getPoints());

                if (result.getTime() != null) {
                    resultHolder.timeTextView.setText(result.getTime().getTime());
                } else {
                    if (result.getStatus().contains("Lap")) {
                        resultHolder.timeTextView.setText(result.getStatus());
                    } else {
                        resultHolder.timeTextView.setText("DNF");
                    }
                }

                DrawableCompat.setTint(resultHolder.teamImageView.getDrawable(),
                        ContextCompat.getColor(mContext, getTeamColor(team)));
            }
        }
    }

    private int getTeamColor(String team) {
        return mContext.getResources().getIdentifier(
                "color" + team, "color", mContext.getPackageName());
    }

    public void setRaceResults(List<Results> results) {
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

    private class QualifyingResultHolder extends RecyclerView.ViewHolder {
        private TextView positionTextView;
        private TextView driverTextView;
        private TextView q1TimeTextView;
        private TextView q2TimeTextView;
        private TextView q3TimeTextView;
        private ImageView teamImageView;

        private QualifyingResultHolder(View itemView) {
            super(itemView);
            positionTextView = itemView.findViewById(R.id.position_text_view);
            driverTextView = itemView.findViewById(R.id.driver_text_view);
            q1TimeTextView = itemView.findViewById(R.id.q1_time_text_view);
            q2TimeTextView = itemView.findViewById(R.id.q2_time_text_view);
            q3TimeTextView = itemView.findViewById(R.id.q3_time_text_view);

            teamImageView = itemView.findViewById(R.id.team_image_view);
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
