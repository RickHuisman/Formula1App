package com.rickhuisman.formula1app.ui.schedule;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Circuit;
import com.rickhuisman.formula1app.ergast.models.Constructor;
import com.rickhuisman.formula1app.ergast.models.Races;
import com.rickhuisman.formula1app.ui.racedetail.RaceActivity;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RaceScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Races> mRaceSchedule = new ArrayList<>();

    public RaceScheduleAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_race, parent, false);
        return viewType == 0 ? new RaceHolder(itemView) : new FutureRaceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Races race = mRaceSchedule.get(position);
        Circuit circuit = mRaceSchedule.get(position).getCircuit();
        Constructor constructor = mRaceSchedule.get(position).getConstructor();

        if (holder instanceof RaceHolder) {
            RaceHolder raceHolder = (RaceHolder) holder;

            raceHolder.textViewRaceName.setText(race.getRaceName());
            raceHolder.textViewRaceDate.setText(getRaceDateString(race));
            raceHolder.textViewCircuitName.setText(circuit.getCircuitName());
            raceHolder.imageViewCircuit.setImageDrawable(getCircuitImage(circuit));

            int contentColor = getContrastColor(constructor);
            raceHolder.textViewRaceName.setTextColor(contentColor);
            raceHolder.textViewRaceDate.setTextColor(contentColor);
            raceHolder.textViewCircuitName.setTextColor(getTransparentColor(contentColor));
            raceHolder.imageViewCircuit.setColorFilter(contentColor);

            GradientDrawable itemViewBackground = (GradientDrawable) raceHolder.itemView.getBackground().mutate();
            itemViewBackground.setColor(mContext.getColor(getTeamColorId(constructor)));

            GradientDrawable circuitHolderBackground = (GradientDrawable) raceHolder.circuitHolder.getBackground().mutate();
            circuitHolderBackground.setColor(getDarkerTeamColor(constructor));
        } else if (holder instanceof FutureRaceHolder) {
            FutureRaceHolder futureRaceHolder = (FutureRaceHolder) holder;

            futureRaceHolder.textViewRaceName.setText(race.getRaceName());
            futureRaceHolder.textViewRaceDate.setText(getRaceDateString(race));
            futureRaceHolder.textViewCircuitName.setText(circuit.getCircuitName());
            futureRaceHolder.imageViewCircuit.setImageDrawable(getCircuitImage(circuit));
        }

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, RaceActivity.class);
//                intent.putExtra("raceId", race.getRaceId());
//                mContext.startActivity(intent);
//            }
//        });
    }

    private int getContrastColor(Constructor constructor) {
        int teamColorId = getTeamColorId(constructor);
        String colorHex = "#" + Integer.toHexString(mContext.getColor(teamColorId)).substring(2, 8);

        int red = Integer.parseInt(colorHex.substring(1, 3), 16);
        int green = Integer.parseInt(colorHex.substring(3, 5), 16);
        int blue = Integer.parseInt(colorHex.substring(5, 7), 16);

        double color = (red * 0.299) + (green * 0.587) + (blue * 0.114);

        return color > 140 ? Color.BLACK : Color.WHITE;
    }

    private int getTransparentColor(int baseHex) {
        String hexColor = String.format("%06X", (0xFFFFFF & baseHex));
        String transparentColor = "#99".concat(hexColor);

        return Color.parseColor(transparentColor);
    }

    private int getDarkerTeamColor(Constructor constructor) {
        float[] hsv = new float[3];
        int color = mContext.getColor(getTeamColorId(constructor));
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.85f;

        return Color.HSVToColor(hsv);
    }

    private int getTeamColorId(Constructor constructor) {
        String constructorId = constructor.getConstructorId().replace("-", "_");
        return getResourceId("constructor_", constructorId, "color");
    }

    private Drawable getCircuitImage(Circuit circuit) {
        String circuitRef = circuit.getCircuitId().replace("-", "_");
        return mContext.getDrawable(getResourceId("circuit_", circuitRef, "drawable"));
    }

    private int getResourceId(String prefix, String name, String resourceType) {
        return mContext.getResources().getIdentifier(
                prefix + name,
                resourceType,
                mContext.getPackageName());
    }

    private String getRaceDateString(Races race) {
        String pattern = race.getTime() != null
                ? "dd MMM - HH:mm"
                : "dd MMM";

        return getRaceDate(race).toString(pattern, Locale.ENGLISH).toLowerCase();
    }

    private DateTime getRaceDate(Races race) {
        if (race.getTime() != null) {
            DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
            String time = race.getTime().replace("Z", "");
            return format.parseDateTime(race.getDate() + " " + time);
        } else {
            DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
            return format.parseDateTime(race.getDate());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mRaceSchedule.get(position).getConstructor() == null ? 1 : 0;
    }

    @Override
    public int getItemCount() {
        return mRaceSchedule.size();
    }

    public void setSchedule(List<Races> races) {
        this.mRaceSchedule = races;

        notifyDataSetChanged();
    }

    public class RaceHolder extends RecyclerView.ViewHolder {
        private TextView textViewRaceName;
        private TextView textViewRaceDate;
        private ImageView imageViewCircuit;
        private TextView textViewCircuitName;
        private ConstraintLayout circuitHolder;

        RaceHolder(View itemView) {
            super(itemView);
            textViewRaceName = itemView.findViewById(R.id.race_name);
            textViewRaceDate = itemView.findViewById(R.id.race_date);
            textViewCircuitName = itemView.findViewById(R.id.circuit_name);
            imageViewCircuit = itemView.findViewById(R.id.circuit_image);
            circuitHolder = itemView.findViewById(R.id.circuit_image_holder);
        }
    }

    public class FutureRaceHolder extends RecyclerView.ViewHolder {
        private TextView textViewRaceName;
        private TextView textViewRaceDate;
        private ImageView imageViewCircuit;
        private TextView textViewCircuitName;
        private ConstraintLayout circuitHolder;

        FutureRaceHolder(View itemView) {
            super(itemView);
            textViewRaceName = itemView.findViewById(R.id.race_name);
            textViewRaceDate = itemView.findViewById(R.id.race_date);
            textViewCircuitName = itemView.findViewById(R.id.circuit_name);
            imageViewCircuit = itemView.findViewById(R.id.circuit_image);
            circuitHolder = itemView.findViewById(R.id.circuit_image_holder);
        }
    }
}