package com.rickhuisman.formula1app.ui.schedule;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.db.entities.Circuits;
import com.rickhuisman.formula1app.ergast.db.entities.Constructor;
import com.rickhuisman.formula1app.ergast.db.entities.RaceWithWinner;
import com.rickhuisman.formula1app.ergast.db.entities.Races;
import com.rickhuisman.formula1app.ui.racedetail.RaceDetailActivity;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<RaceWithWinner> mRaceSchedule = new ArrayList<>();

    public ScheduleAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        int view = 0;

        return view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = new BaseHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_race, parent, false));

        if (viewType == 1) {
            viewHolder = new NextHolder(LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_next_race, parent, false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Races race = mRaceSchedule.get(position).getRace();
        Circuits circuit = mRaceSchedule.get(position).getCircuit();
        Constructor constructor = mRaceSchedule.get(position).getConstructor();

        if (holder instanceof BaseHolder) {
            BaseHolder baseHolder = (BaseHolder) holder;

            setRaceName(baseHolder.textViewRaceName, race);
            setRaceDate(baseHolder.textViewRaceDate, race);
            setCircuitName(baseHolder.textViewCircuitName, circuit);
            setCircuitImage(baseHolder.imageViewCircuit, circuit);
            setBackgroundColor(baseHolder.itemHolder, constructor);
            setHolderColors(baseHolder, constructor);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RaceDetailActivity.class);
                intent.putExtra("raceId", race.getRaceId());
                intent.putExtra("raceName", race.getName());
                mContext.startActivity(intent);
            }
        });
    }

    private void setRaceName(TextView textViewRaceName, Races race) {
        textViewRaceName.setText(race.getName());
    }

    private void setRaceDate(TextView textViewRaceDate, Races race) {
        textViewRaceDate.setText(getRaceDate(race));
    }

    private void setCircuitName(TextView textViewCircuitName, Circuits circuit) {
        textViewCircuitName.setText(circuit.getName());
    }

    private void setCircuitImage(ImageView imageViewCircuit, Circuits circuit) {
        String circuitId = String.valueOf(circuit.getCircuitId());
        int circuitImageId = getResourceId("circuit_", circuitId,"drawable");

        imageViewCircuit.setImageDrawable(mContext.getDrawable(circuitImageId));
    }

    private void setBackgroundColor(ConstraintLayout itemHolder, Constructor constructor) {
        int teamColorId = getTeamColorId(constructor.getConstructorId());

        GradientDrawable background = (GradientDrawable) itemHolder.getBackground().mutate();
        background.setColor(mContext.getColor(teamColorId));
    }

    private void setHolderColors(BaseHolder holder, Constructor constructor) {
        int teamColorId = getTeamColorId(constructor.getConstructorId());

        int color = getContrastWithTeamColorId(teamColorId);

        holder.textViewRaceName.setTextColor(color);
        holder.textViewRaceDate.setTextColor(color);
        holder.imageViewCircuit.setColorFilter(color);

        // Convert color integer to hex
        String hexColor = String.format("%06X", (0xFFFFFF & color));
        String colorWithTransparency = "#B3".concat(hexColor);
        holder.textViewCircuitName.setTextColor(Color.parseColor(colorWithTransparency));
    }

    private int getTeamColorId(int constructorId) {
        return getResourceId(
                "constructor_",
                String.valueOf(constructorId),
                "color");
    }

    private int getContrastWithTeamColorId(int teamColorId) {
        String colorHex = "#" + Integer.toHexString(ContextCompat.getColor(mContext, teamColorId)).substring(2, 8);

        int red = Integer.parseInt(colorHex.substring(1, 3), 16);
        int green = Integer.parseInt(colorHex.substring(3, 5), 16);
        int blue = Integer.parseInt(colorHex.substring(5, 7), 16);

        double color = (red * 0.299) + (green * 0.587) + (blue * 0.114);

        return color > 140 ? Color.BLACK : Color.WHITE;
    }

    private String getRaceDate(Races race) {
        String dateToFormat = race.getDate();

        if (race.getTime() != null) {
            dateToFormat += " " + race.getTime();
            return formatDateAndTime(dateToFormat);
        }

        return formatDate(dateToFormat);
    }

    private String formatDateAndTime(String dateTimeToFormat) {
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        DateTime formattedDateTime = format.parseDateTime(dateTimeToFormat);
        return formattedDateTime.toString("yyyy dd MMM - HH:mm", Locale.ENGLISH).toLowerCase();
    }

    private String formatDate(String dateToFormat) {
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");

        DateTime formattedDate = format.parseDateTime(dateToFormat);
        return formattedDate.toString("yyyy dd MMM", Locale.ENGLISH).toLowerCase();
    }

    private int getResourceId(String prefix, String name, String resourceType) {
        return mContext.getResources().getIdentifier(
                prefix + name,
                resourceType,
                mContext.getPackageName());
    }

    @Override
    public int getItemCount() {
        return mRaceSchedule.size();
    }

    public void setSchedule(List<RaceWithWinner> races) {
        this.mRaceSchedule = races;

        notifyDataSetChanged();
    }

    class BaseHolder extends RecyclerView.ViewHolder {
        private TextView textViewRaceName;
        private TextView textViewRaceDate;
        private ImageView imageViewCircuit;
        private TextView textViewCircuitName;
        private ConstraintLayout itemHolder;

        BaseHolder(View itemView) {
            super(itemView);
            textViewRaceName = itemView.findViewById(R.id.race_name_text_view);
            textViewRaceDate = itemView.findViewById(R.id.race_date_text_view);
            textViewCircuitName = itemView.findViewById(R.id.circuit_name_text_view);
            imageViewCircuit = itemView.findViewById(R.id.circuit_map_image_view);
            itemHolder = itemView.findViewById(R.id.item_holder);
        }
    }

    class NextHolder extends BaseHolder {
        private TextView textViewCountDown;

        NextHolder(View itemView) {
            super(itemView);
            textViewCountDown = itemView.findViewById(R.id.countdown_text_view);
        }
    }
}
