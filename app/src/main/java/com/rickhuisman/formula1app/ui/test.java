package com.rickhuisman.formula1app.ui;

public class test {

//    private ColorStateList getTeamColor(String constructor) {
//        constructor = constructor.replace(" ", "");
//
//        int colorId = mContext.getResources().getIdentifier(
//                "color" + constructor,
//                "color",
//                mContext.getPackageName());
//
//        ColorStateList color = ColorStateList.valueOf(mContext.getColor(colorId));
//
//        return color;
//    }
//
//    private Drawable getCircuitDrawable(int round) {
//        int resourceId = mContext.getResources().getIdentifier(
//                "round_" + (round + 1),
//                "drawable",
//                mContext.getPackageName());
//
//        Drawable circuit = mContext.getResources().getDrawable(R.drawable.round_1);
//        if (resourceId != 0)
//            circuit = mContext.getResources().getDrawable(resourceId);
//
//        return circuit;
//    }



//    private void pastRace(ConstraintLayout itemHolder, int pos) {
//        GradientDrawable background = (GradientDrawable) itemHolder.getBackground().mutate();
//
//        ColorStateList color = getTeamColor(
//                mRaceSchedule.get(pos).getResults().get(0).getConstructor().getName());
//
//        background.setColor(color);
//    }

    //
//     Get int of the next round
//    String nextRound = "";
//            try {
//        for (int i = 0; i < races.size(); i++) {
//
//            Date raceDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(
//                    races.get(i).getDate() + " " + races.get(i).getTime());
//
//            java.util.Calendar cal = java.util.Calendar.getInstance();
//            Date currentDate = cal.getTime();
//
//            int result = raceDate.compareTo(currentDate);
//
//            if (result > 0) {
//                nextRound = races.get(i).getRound();
//                break;
//            }
//        }
//    } catch (ParseException e) {
//        e.printStackTrace();
//    }




//    String colorResource = "#" + Integer.toHexString(ContextCompat.getColor(mContext, R.color.colorFerrari) & 0x00ffffff);
//
//        holder.textViewRaceName.setTextColor(blackOrWhiteText(colorResource));




//    private int blackOrWhiteText(String colorId) {
//        // Base white color
//        int textColor = mContext.getColor(R.color.colorWilliams);
//
//        int redInt = Integer.parseInt(
//                colorId.substring(1, 3), 16);
//        int greenInt = Integer.parseInt(
//                colorId.substring(3, 5), 16);
//        int blueInt = Integer.parseInt(
//                colorId.substring(5, 7), 16);
//
//        System.out.println("Red - " + redInt + ", Green - " + greenInt + ", Blue - " + blueInt);
//
//        double test = (redInt * 0.299) + (greenInt * 0.587) + (blueInt * 0.114);
//
//        if (test > 186) {
//            textColor = mContext.getColor(R.color.colorPrimaryDark);
//        } else {
//            textColor = mContext.getColor(R.color.colorWilliams);
//        }
//
//        return textColor;
//    }
}
