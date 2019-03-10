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
import com.rickhuisman.formula1app.ergast.db.entities.Circuit;
import com.rickhuisman.formula1app.ergast.db.entities.Constructor;
import com.rickhuisman.formula1app.ergast.db.entities.Race;
import com.rickhuisman.formula1app.ergast.db.entities.RaceWithWinner;
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
    private List<RaceWithWinner> mRaceSchedule = new ArrayList<>();

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
        final Race race = mRaceSchedule.get(position).getRace();
        Circuit circuit = mRaceSchedule.get(position).getCircuit();
        Constructor constructor = mRaceSchedule.get(position).getConstructor();

        if (holder instanceof RaceHolder) {
            RaceHolder raceHolder = (RaceHolder) holder;

            raceHolder.textViewRaceName.setText(race.getName());
            raceHolder.textViewRaceDate.setText(getRaceDateString(race));
            raceHolder.textViewCircuitName.setText(circuit.getName());
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

            futureRaceHolder.textViewRaceName.setText(race.getName());
            futureRaceHolder.textViewRaceDate.setText(getRaceDateString(race));
            futureRaceHolder.textViewCircuitName.setText(circuit.getName());
            futureRaceHolder.imageViewCircuit.setImageDrawable(getCircuitImage(circuit));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RaceActivity.class);
                intent.putExtra("raceId", race.getRaceId());
                mContext.startActivity(intent);
            }
        });
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
        return getResourceId("constructor_", constructor.getConstructorId(), "color");
    }

    private Drawable getCircuitImage(Circuit circuit) {
        return mContext.getDrawable(getResourceId("circuit_", circuit.getCircuitId(), "drawable"));
    }

    private int getResourceId(String prefix, int name, String resourceType) {
        return mContext.getResources().getIdentifier(
                prefix + String.valueOf(name),
                resourceType,
                mContext.getPackageName());
    }

    private String getRaceDateString(Race race) {
        String pattern = race.getTime() != null
                ? "dd MMM - HH:mm"
                : "dd MMM";

        return getRaceDate(race).toString(pattern, Locale.ENGLISH).toLowerCase();
    }

    private DateTime getRaceDate(Race race) {
        if (race.getTime() != null) {

            DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
            return format.parseDateTime(race.getDate() + " " + race.getTime());
        } else {

            DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
            return format.parseDateTime(race.getDate());
        }
    }

    @Override
    public int getItemViewType(int position) {
        DateTime currentDateTime = new DateTime();
        DateTime raceDateTime = getRaceDate(mRaceSchedule.get(position).getRace());

        int result = currentDateTime.compareTo(raceDateTime);

        return result < 1 ? 1 : 0;
    }

    @Override
    public int getItemCount() {
        return mRaceSchedule.size();
    }

    public void setSchedule(List<RaceWithWinner> races) {
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