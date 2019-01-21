package com.rickhuisman.formula1app.ui.racedetail;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.QualifyingWithDriver;
import com.rickhuisman.formula1app.ui.driverdetail.DriverActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

public class QualifyingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<QualifyingWithDriver> mQualifyingResults = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        RecyclerView.ViewHolder viewHolder;
        View header = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.header_qualifying_result, parent, false);
        viewHolder = new HeaderHolder(header);

        if (viewType == 1) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_qualifying_result, parent, false);
            return new QualifyingResultHolder(itemView);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (position != 0) {
            QualifyingResultHolder qualifyingHolder = (QualifyingResultHolder) holder;
            QualifyingWithDriver result = mQualifyingResults.get(position - 1);

            qualifyingHolder.positionTextView.setText(
                    String.valueOf(result.getQualifying().getPosition()));
            qualifyingHolder.driverTextView.setText(result.getDriver().getSurName().substring(0, 3).toUpperCase());
            qualifyingHolder.q1TimeTextView.setText(result.getQualifying().getQualifyingOne());
            qualifyingHolder.q2TimeTextView.setText(result.getQualifying().getQualifyingTwo());
            qualifyingHolder.q3TimeTextView.setText(result.getQualifying().getQualifyingThree());

            int constructorId = result.getQualifying().getConstructorId();
            DrawableCompat.setTint(qualifyingHolder.teamImageView.getDrawable(),
                    ContextCompat.getColor(mContext, getTeamColor(constructorId)));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int driverId = mQualifyingResults.get(holder.getAdapterPosition() - 1).getDriver().getDriverId();

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

    @Override
    public int getItemCount() {
        return mQualifyingResults.size() + 1;
    }

    public void setQualifyingResults(List<QualifyingWithDriver> qualifyings) {
        this.mQualifyingResults = qualifyings;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        return 1;
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {

        private HeaderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class QualifyingResultHolder extends RecyclerView.ViewHolder {
        private TextView positionTextView;
        private TextView driverTextView;
        private TextView q1TimeTextView;
        private TextView q2TimeTextView;
        private TextView q3TimeTextView;
        private ImageView teamImageView;

        public QualifyingResultHolder(View itemView) {
            super(itemView);

            positionTextView = itemView.findViewById(R.id.position_text_view);
            driverTextView = itemView.findViewById(R.id.driver_text_view);
            q1TimeTextView = itemView.findViewById(R.id.q1_time_text_view);
            q2TimeTextView = itemView.findViewById(R.id.q2_time_text_view);
            q3TimeTextView = itemView.findViewById(R.id.q3_time_text_view);

            teamImageView = itemView.findViewById(R.id.team_image_view);
        }
    }
}
