package com.rickhuisman.formula1app;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

@SuppressLint("Registered")
public class ColorActivity extends AppCompatActivity {

    public void setToolbarTitle(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title.toUpperCase());
    }

    public void setTopAppBarColorsFor(int color) {
        setColors(color);
    }

    public void setTopAppBarColorsForTeamId(String constructorId) {
        int teamColorId = getResources().getIdentifier("constructor_" + constructorId, "color", getPackageName());

        setColors(getColor(teamColorId));
    }

    private void setColors(int color) {
        // Status bar
        getWindow().setStatusBarColor(getDarkerTeamColorId(color));

        // Toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));

        // TabLayout
        TabLayout tabLayout = findViewById(R.id.container_tab_layout);
        tabLayout.setBackground(new ColorDrawable(color));

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        if (blackOrWhiteText(color)) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitleTextColor(Color.BLACK);

            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black);

            tabLayout.setTabTextColors(getColor(R.color.colorBlackTabInactive), Color.BLACK);
            tabLayout.setSelectedTabIndicatorColor(Color.BLACK);

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private boolean blackOrWhiteText(int teamColorId) {
        String colorHex = "#" + Integer.toHexString(teamColorId).substring(2, 8);

        int red = Integer.parseInt(colorHex.substring(1, 3), 16);
        int green = Integer.parseInt(colorHex.substring(3, 5), 16);
        int blue = Integer.parseInt(colorHex.substring(5, 7), 16);

        double color = (red * 0.299) + (green * 0.587) + (blue * 0.114);

        return color > 140;
    }

    private int getDarkerTeamColorId(int teamColor) {
        float[] hsv = new float[3];
        int color = teamColor;
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.85f;

        return Color.HSVToColor(hsv);
    }
}
