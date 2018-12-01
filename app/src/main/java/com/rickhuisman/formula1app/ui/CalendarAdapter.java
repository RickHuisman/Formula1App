package com.rickhuisman.formula1app.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
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
import com.rickhuisman.formula1app.ergast.models.Constructor;
import com.rickhuisman.formula1app.ergast.models.Races;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Races> mRaceSchedule = new ArrayList<>();
    private int mResultCount;
    private long mNextRoundMilliSec;

    public CalendarAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        int view = 0;

        if ((getNextRound() - 1) == position) {
            view = 1;
        }
        return view;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = new PastRaceHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_race, parent, false));

        if (viewType == 1) {
            viewHolder = new NextRaceHolder(LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_next_race, parent, false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Races race = mRaceSchedule.get(position);

        TextView raceName = holder.itemView.findViewById(R.id.race_name_text_view);
        TextView circuitName = holder.itemView.findViewById(R.id.circuit_name_text_view);
        TextView raceDate = holder.itemView.findViewById(R.id.race_date_text_view);
        ImageView circuitMap = holder.itemView.findViewById(R.id.circuit_map_image_view);;

        raceName.setText(race.getRaceName());
        circuitName.setText(race.getCircuit().getCircuitName());
        raceDate.setText(getRaceDateString(position));
        circuitMap.setImageDrawable(mContext.getDrawable(
                getResourceId(
                        "circuit_",
                        String.valueOf(position + 1),
                        "drawable")));

        switch (holder.getItemViewType()) {
            case 0:
                pastRace((PastRaceHolder) holder);
                break;
            case 1:
                nextRace((NextRaceHolder) holder);
                break;
        }
    }

    private void pastRace(PastRaceHolder holder) {
        int position = holder.getAdapterPosition();
        Races race = mRaceSchedule.get(position);

        // Set colored background
        if (position < mResultCount)
            setColoredBackground(holder);

        if (race.getResults() != null) {
            String team = getTeamString(race.getResults().get(0).getConstructor());

            int teamColorId = getResourceId(
                    "color",
                    team,
                    "color");
            setTextColors(holder, teamColorId);
        }
    }

    private void nextRace(final NextRaceHolder holder) {
        timer(holder);
    }

    private void timer(final NextRaceHolder holder) {
        mNextRoundMilliSec = getDateDifference();

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (mNextRoundMilliSec > 0) {
                    holder.textViewRaceCountDown.setText(
                            String.format("%02dd %02dh %02dm %02ds",
                                    TimeUnit.MILLISECONDS.toDays(mNextRoundMilliSec),
                                    TimeUnit.MILLISECONDS.toHours(mNextRoundMilliSec)- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(mNextRoundMilliSec)),
                                    TimeUnit.MILLISECONDS.toMinutes(mNextRoundMilliSec) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(mNextRoundMilliSec)),
                                    TimeUnit.MILLISECONDS.toSeconds(mNextRoundMilliSec) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mNextRoundMilliSec))));

                    mNextRoundMilliSec -= 1000;
                    handler.postDelayed(this, 1000);
                } else {
                    holder.textViewRaceCountDown.setText("Race is live!");
                }
            }
        });
    }

    private DateTime getRaceDateTime(int round) {
        String dateTime = mRaceSchedule.get(round).getDate() + " " +
                mRaceSchedule.get(round).getTime();

        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ssZ");
        DateTime raceDateTime = format.parseDateTime(dateTime);

        return raceDateTime;
    }

    private String getRaceDateString(int round) {
        DateTime raceDateTime = getRaceDateTime(round);
        return raceDateTime.toString("dd MMM - HH:mm", Locale.ENGLISH).toLowerCase();
    }

    private long getDateDifference() {
        DateTime currentDateTime = new DateTime();
        DateTime raceDate = getRaceDateTime(getNextRound());

        return raceDate.getMillis() - currentDateTime.getMillis();
    }

    private void setColoredBackground(PastRaceHolder holder) {
        String team = getTeamString(
                mRaceSchedule.get(holder.getAdapterPosition())
                        .getResults().get(0).getConstructor());

        GradientDrawable background = (GradientDrawable) holder.itemHolder.getBackground().mutate();
        background.setColor(ColorStateList.valueOf(mContext.getColor(getTeamColorId(team))));
    }

    private String getTeamString(Constructor constructor) {
        return constructor.getName().replace(" ", "");
    }

    private void setTextColors(PastRaceHolder holder, int teamColorId) {
        String colorResource = "#" + Integer.toHexString(ContextCompat.getColor(mContext, teamColorId))
                .substring(2, 8);
        int textColor = getTextColor(colorResource);

        holder.textViewRaceName.setTextColor(textColor);
        holder.textViewCircuitName.setTextColor(getCircuitTextColor(colorResource));
        holder.textViewRaceDate.setTextColor(textColor);
        holder.imageViewCircuitMap.setColorFilter(textColor);
    }

    private int getTeamColorId(String team) {
        return getResourceId(
                "color",
                team,
                "color");
    }

    private int getTextColor(String colorId) {
        int textColor;

        int red = Integer.parseInt(
                colorId.substring(1, 3), 16);
        int green = Integer.parseInt(
                colorId.substring(3, 5), 16);
        int blue = Integer.parseInt(
                colorId.substring(5, 7), 16);

        double color = (red * 0.299) + (green * 0.587) + (blue * 0.114);

        if (color > 140) {
            textColor = mContext.getColor(R.color.colorPrimaryDark);
        } else {
            textColor = mContext.getColor(R.color.colorWilliams);
        }

        return textColor;
    }

    private int getCircuitTextColor(String colorId) {
        int textColor;

        if (getTextColor(colorId) == mContext.getColor(R.color.colorPrimaryDark)) {
            textColor = Color.argb(153, 0, 0, 0);
        } else {
            textColor = Color.argb(153, 255, 255, 255);
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
        for (int i = 0; i < mRaceSchedule.size(); i++) {
            DateTime raceDateTime = getRaceDateTime(i);
            DateTime currentDateTime = new DateTime();

            int result = raceDateTime.compareTo(currentDateTime);
            if (result > 0) {
                nextRound = Integer.valueOf(mRaceSchedule.get(i).getRound());
                break;
            }
        }
        return nextRound;
    }

    class PastRaceHolder extends RecyclerView.ViewHolder {
        private TextView textViewRaceName;
        private TextView textViewCircuitName;
        private TextView textViewRaceDate;
        private ImageView imageViewCircuitMap;
        private ConstraintLayout itemHolder;

        public PastRaceHolder(View itemView) {
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
        private TextView textViewRaceCountDown;
        private ImageView imageViewCircuitMap;

        public NextRaceHolder(View itemView) {
            super(itemView);
            textViewRaceName = itemView.findViewById(R.id.race_name_text_view);
            textViewCircuitName = itemView.findViewById(R.id.circuit_name_text_view);
            textViewRaceDate = itemView.findViewById(R.id.race_date_text_view);
            textViewRaceCountDown = itemView.findViewById(R.id.race_countdown_text_view);
            imageViewCircuitMap = itemView.findViewById(R.id.circuit_map_image_view);
        }
    }
}
