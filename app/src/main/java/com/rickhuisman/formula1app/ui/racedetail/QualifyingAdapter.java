package com.rickhuisman.formula1app.ui.racedetail;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.Qualifying;
import com.rickhuisman.formula1app.ergast.db.entities.QualifyingResult;
import com.rickhuisman.formula1app.ui.driverdetail.DriverActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

public class QualifyingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<QualifyingResult> mQualifyingResults = new ArrayList<>();

    public QualifyingAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new HeaderHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.header_qualifying_result, parent, false));
        } else {
            return new QualifyingResultHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.item_qualifying_result, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (position != 0) {
            QualifyingResultHolder qualifyingHolder = (QualifyingResultHolder) holder;

            final QualifyingResult result = mQualifyingResults.get(position - 1);
            Qualifying qualifying = result.getQualifying();

            qualifyingHolder.textViewPosition.setText(String.valueOf(qualifying.getPosition()));

            String driverName = result.getDriver().getSurName().substring(0, 3).toUpperCase();
            qualifyingHolder.textViewDriver.setText(driverName);

            qualifyingHolder.textViewTimeQ1.setText(qualifying.getQualifyingOne());
            qualifyingHolder.textViewTimeQ2.setText(qualifying.getQualifyingTwo());
            qualifyingHolder.textViewTimeQ3.setText(qualifying.getQualifyingThree());

            final int constructorId = qualifying.getConstructorId();
            DrawableCompat.setTint(qualifyingHolder.teamImageView.getDrawable(),
                    ContextCompat.getColor(mContext, getTeamColor(constructorId)));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, DriverActivity.class);
                    intent.putExtra("driverId", result.getDriver().getDriverId());
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

    public void setQualifying(List<QualifyingResult> qualifying) {
        this.mQualifyingResults = qualifying;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {

        private HeaderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class QualifyingResultHolder extends RecyclerView.ViewHolder {
        private TextView textViewPosition, textViewDriver, textViewTimeQ1, textViewTimeQ2, textViewTimeQ3;
        private ImageView teamImageView;

        public QualifyingResultHolder(View itemView) {
            super(itemView);

            textViewPosition = itemView.findViewById(R.id.year);
            textViewDriver = itemView.findViewById(R.id.driver);
            textViewTimeQ1 = itemView.findViewById(R.id.time_q1);
            textViewTimeQ2 = itemView.findViewById(R.id.time_q2);
            textViewTimeQ3 = itemView.findViewById(R.id.time_q3);
            teamImageView = itemView.findViewById(R.id.constructor);
        }
    }
}
