package com.example.nds.darkcalendar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RelativeLayout container;
    CaldroidFragment caldroidFragment;
    ICalendarItemCliced calendarItemCliced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (RelativeLayout) findViewById(R.id.calendar_containter);
        caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);
        args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY);
        args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, false);
        args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, true);
        caldroidFragment.setArguments(args);
        caldroidFragment.setThemeResource(R.style.CaldroidDefaultDark);
        Map<Date, Drawable> dates = new HashMap<>();
        final Map<Date, Integer> colors = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        final ArrayList<Date> markedDays = new ArrayList<>();
        try {
            markedDays.add(format.parse("05-12-2018"));
            markedDays.add(format.parse("06-12-2018"));
            markedDays.add(format.parse("07-12-2018"));
            markedDays.add(format.parse("08-12-2018"));
            markedDays.add(format.parse("09-12-2018"));
            markedDays.add(format.parse("10-12-2018"));
        } catch (ParseException e) {

        }
        for (int i = 0; i < markedDays.size(); i++) {
            dates.put(markedDays.get(i), getResources().getDrawable(R.drawable.white_background_vector));
        }

        for (int i = 0; i < markedDays.size(); i++) {
            colors.put(markedDays.get(i), (R.color.colorAccent));
        }

        calendarItemCliced= new ICalendarItemCliced() {
            @Override
            public void dateFromMapClicked(int position) {

            }
        };
        caldroidFragment.setTextColorForDates(colors);
        caldroidFragment.setBackgroundDrawableForDates(dates);
        caldroidFragment.setCaldroidListener(new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                calendarItemCliced.dateFromMapClicked(markedDays.indexOf(date));
                caldroidFragment.clearBackgroundDrawableForDate(date);
                caldroidFragment.clearTextColorForDate(date);
                caldroidFragment.refreshView();
            }
        });
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar_containter, caldroidFragment);
        t.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        caldroidFragment.getWeekdayGridView().setBackground(getResources().getDrawable(R.drawable.week_background));
//        caldroidFragment.getWeekdayGridView().setPadding(10,20,10,20);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 30;
        params.topMargin = 30;
        params.leftMargin = 30;
        params.rightMargin = 30;
        caldroidFragment.getWeekdayGridView().setLayoutParams(params);
        caldroidFragment.refreshView();
    }

    public interface ICalendarItemCliced {
        public void dateFromMapClicked(int position);
    }
}
