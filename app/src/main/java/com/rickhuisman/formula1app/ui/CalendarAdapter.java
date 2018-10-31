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

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Races> mRaceSchedule = new ArrayList<>();
    private int mResultCount;
    private int mNextRace;

    public CalendarAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        int view = 0;

        if ((mNextRace - 1) == position)
            view = 1;

        return view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new CalendarHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_test, parent, false));
        } else {
            return new Calendar2Holder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_next_race, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Races race = mRaceSchedule.get(position);

        switch (holder.getItemViewType()) {
            case 0:
                CalendarHolder calendarHolder = (CalendarHolder) holder;

                calendarHolder.textViewRaceName.setText(race.getRaceName());
                calendarHolder.textViewCircuitName.setText(race.getCircuit().getCircuitName());
                calendarHolder.imageViewCircuitMap.setImageDrawable(mContext.getDrawable(
                        getResourceId(
                                "round_",
                                "4",
                                "drawable")));

                calendarHolder.textViewRaceDate.setText(getTime(position));

                // Past race
                if (position < mResultCount)
                    pastRace(calendarHolder, position);


                break;

            case 1:
                Calendar2Holder calendar2Holder = (Calendar2Holder) holder;
                calendar2Holder.textViewRaceName.setText(race.getRaceName());
                calendar2Holder.textViewRaceName.setText(race.getRaceName());
                calendar2Holder.textViewCircuitName.setText(race.getCircuit().getCircuitName());
                calendar2Holder.imageViewCircuitMap.setImageDrawable(mContext.getDrawable(
                        getResourceId(
                                "round_",
                                "4",
                                "drawable")));

                calendar2Holder.textViewRaceDate.setText(getTime(position));
                break;
        }
    }

    private String getTime(int position) {
        String date = "";
        Calendar raceDate = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            raceDate.setTime(sdf.parse(
                    mRaceSchedule.get(position).getDate() + " " +
                            mRaceSchedule.get(position).getTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        date = raceDate.get(Calendar.DAY_OF_MONTH) + " " +
                raceDate.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " " +
                raceDate.get(Calendar.HOUR_OF_DAY) + ":" + raceDate.get(Calendar.MINUTE);

        return date.toLowerCase();
    }

    private void pastRace(CalendarHolder holder, int pos) {
        GradientDrawable background = (GradientDrawable) holder.itemHolder.getBackground().mutate();

        String constructor = mRaceSchedule.get(pos).getResults().get(0).getConstructor().getName().replace(" ", "");
        int teamColorId = getResourceId(
                "color",
                constructor,
                "color"
        );

        background.setColor(
                ColorStateList.valueOf(mContext.getColor(teamColorId)));

        test(holder, teamColorId);
    }

    private void test(CalendarHolder holder, int teamColorId) {
        String colorResource = "#" + Integer.toHexString(ContextCompat.getColor(mContext, teamColorId))
                .substring(2, 8);

        holder.textViewRaceName.setTextColor(blackOrWhiteText(colorResource));
        holder.textViewCircuitName.setTextColor(blackOrWhiteText(colorResource));
        holder.textViewRaceDate.setTextColor(blackOrWhiteText(colorResource));
        holder.imageViewCircuitMap.setColorFilter(blackOrWhiteText(colorResource));
    }

    private int blackOrWhiteText(String colorId) {
        // Base white color
        int textColor = mContext.getColor(R.color.colorWilliams);

        int redInt = Integer.parseInt(
                colorId.substring(1, 3), 16);
        int greenInt = Integer.parseInt(
                colorId.substring(3, 5), 16);
        int blueInt = Integer.parseInt(
                colorId.substring(5, 7), 16);

        double test = (redInt * 0.299) + (greenInt * 0.587) + (blueInt * 0.114);

        if (test > 186) {
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

        mNextRace = getNextRound();

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

    class Calendar2Holder extends RecyclerView.ViewHolder {
        private TextView textViewRaceName;
        private TextView textViewCircuitName;
        private TextView textViewRaceDate;
        private ImageView imageViewCircuitMap;

        public Calendar2Holder(View itemView) {
            super(itemView);
            textViewRaceName = itemView.findViewById(R.id.race_name_text_view);
            textViewCircuitName = itemView.findViewById(R.id.circuit_name_text_view);
            textViewRaceDate = itemView.findViewById(R.id.race_date_text_view);
            imageViewCircuitMap = itemView.findViewById(R.id.circuit_map_image_view);
        }
    }
}
