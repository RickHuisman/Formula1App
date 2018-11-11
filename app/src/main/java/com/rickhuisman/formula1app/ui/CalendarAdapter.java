package com.rickhuisman.formula1app.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rickhuisman.formula1app.R;
import com.rickhuisman.formula1app.ergast.models.Races;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Races> mRaceSchedule = new ArrayList<>();
    private int mResultCount;

    public CalendarAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        int view = 0;

        if ((getNextRound() - 1) == position)
            view = 1;

        return view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = new CalendarHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_race, parent, false));

        if (viewType == 1) {
            viewHolder = new NextRaceHolder(LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_next_race, parent, false));
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                pastRace((CalendarHolder) holder);
                break;
            case 1:
                nextRace((NextRaceHolder) holder);
                break;
        }
    }

    private void pastRace(CalendarHolder holder) {
        int position = holder.getAdapterPosition();
        Races race = mRaceSchedule.get(position);

        holder.textViewRaceName.setText(race.getRaceName());
        holder.textViewCircuitName.setText(race.getCircuit().getCircuitName());
        holder.textViewRaceDate.setText(getRaceDate(position));
        holder.imageViewCircuitMap.setImageDrawable(mContext.getDrawable(
                getResourceId(
                        "circuit_",
                        "4",
                        "drawable")));

        // Get colored background
        if (position < mResultCount)
            setColoredBackground(holder);

        int teamColorId = getResourceId(
                "color",
                "Mercedes",
                "color");

        setTextColors(holder, teamColorId);
    }

    private void nextRace(NextRaceHolder holder) {
        int position = holder.getAdapterPosition();
        Races race = mRaceSchedule.get(position);

        holder.textViewRaceName.setText(race.getRaceName());
        holder.textViewCircuitName.setText(race.getCircuit().getCircuitName());
        holder.textViewRaceDate.setText(getRaceDate(position));
        holder.imageViewCircuitMap.setImageDrawable(mContext.getDrawable(
                getResourceId("circuit_","4","drawable")));
    }

    private String getRaceDate(int round) {
        String raceDate;
        Calendar calendar = Calendar.getInstance();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            calendar.setTime(dateFormat.parse(
                    mRaceSchedule.get(round).getDate() + " " +
                            mRaceSchedule.get(round).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        raceDate = calendar.get(Calendar.DAY_OF_MONTH) + " " +
                calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " " +
                calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);

        return raceDate.toLowerCase();
    }

    private void setColoredBackground(CalendarHolder holder) {
        String team = mRaceSchedule.get(holder.getAdapterPosition()).getResults()
                .get(0).getConstructor().getName().replace(" ", "");

        GradientDrawable background = (GradientDrawable) holder.itemHolder.getBackground().mutate();
        background.setColor(ColorStateList.valueOf(
                mContext.getColor(getTeamColorId(team))));
    }

    private void setTextColors(CalendarHolder holder, int teamColorId) {
        String colorResource = "#" + Integer.toHexString(ContextCompat.getColor(mContext, teamColorId))
                .substring(2, 8);

        holder.textViewRaceName.setTextColor(blackOrWhiteText(colorResource));
        holder.textViewCircuitName.setTextColor(blackOrWhiteText(colorResource));
        holder.textViewRaceDate.setTextColor(blackOrWhiteText(colorResource));
        holder.imageViewCircuitMap.setColorFilter(blackOrWhiteText(colorResource));
    }

    private int getTeamColorId(String team) {
        return getResourceId(
                "color",
                team,
                "color");
    }

    private int blackOrWhiteText(String colorId) {
        int textColor;

        int red = Integer.parseInt(
                colorId.substring(1, 3), 16);
        int green = Integer.parseInt(
                colorId.substring(3, 5), 16);
        int blue = Integer.parseInt(
                colorId.substring(5, 7), 16);

        double test = (red * 0.299) + (green * 0.587) + (blue * 0.114);

        if (test > 140) {
            textColor = mContext.getColor(R.color.colorPrimaryDark);
        } else {
            textColor = mContext.getColor(R.color.colorWilliams);
        }

        return textColor;
    }

    private int getResourceId(String defName, String name, String resourceType) {
        return mContext.getResources().getIdentifier(
                defName + name,
                resourceType,
                mContext.getPackageName());
    }

    @Override
    public int getItemCount() {
        return mRaceSchedule.size();
    }

    public void setCalendar(List<Races> races) {
        this.mRaceSchedule = races;

        for (int i = 0; i < mRaceSchedule.size(); i++) {
            if (mRaceSchedule.get(i).getResults() != null)
                mResultCount += 1;
        }

        notifyDataSetChanged();
    }

    private int getNextRound() {
        int nextRound = 0;
        try {
            for (int i = 0; i < mRaceSchedule.size(); i++) {
                Date raceDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
                        mRaceSchedule.get(i).getDate() + " " + mRaceSchedule.get(i).getTime());

                java.util.Calendar cal = java.util.Calendar.getInstance();
                Date currentDate = cal.getTime();

                int result = raceDate.compareTo(currentDate);

                if (result > 0) {
                    nextRound = Integer.valueOf(mRaceSchedule.get(i).getRound());
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nextRound;
    }

    class CalendarHolder extends RecyclerView.ViewHolder {
        private TextView textViewRaceName;
        private TextView textViewCircuitName;
        private TextView textViewRaceDate;
        private ImageView imageViewCircuitMap;
        private ConstraintLayout itemHolder;

        public CalendarHolder(View itemView) {
            super(itemView);
            textViewRaceName = itemView.findViewById(R.id.race_name_text_view);
            textViewCircuitName = itemView.findViewById(R.id.circuit_name_text_view);
            textViewRaceDate = itemView.findViewById(R.id.race_date_text_view);
            imageViewCircuitMap = itemView.findViewById(R.id.circuit_map_image_view);
            itemHolder = itemView.findViewById(R.id.item_holder);
        }
    }

    class NextRaceHolder extends RecyclerView.ViewHolder {
        private TextView textViewRaceName;
        private TextView textViewCircuitName;
        private TextView textViewRaceDate;
        private ImageView imageViewCircuitMap;

        public NextRaceHolder(View itemView) {
            super(itemView);
            textViewRaceName = itemView.findViewById(R.id.race_name_text_view);
            textViewCircuitName = itemView.findViewById(R.id.circuit_name_text_view);
            textViewRaceDate = itemView.findViewById(R.id.race_date_text_view);
            imageViewCircuitMap = itemView.findViewById(R.id.circuit_map_image_view);
        }
    }
}
