package com.example.nds.darkcalendar;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    RelativeLayout container;
    CaldroidFragment caldroidFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (RelativeLayout)findViewById(R.id.calendar_containter);
        caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);
        caldroidFragment.setArguments(args);
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar_containter, caldroidFragment);
        t.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        caldroidFragment.getWeekdayGridView().setBackground(getResources().getDrawable(R.drawable.week_background));
//        caldroidFragment.getWeekdayGridView().setPadding(10,20,10,20);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 30;
        params.topMargin = 30;
        params.leftMargin = 30;
        params.rightMargin = 30;
        caldroidFragment.getWeekdayGridView().setLayoutParams(params);
        caldroidFragment.refreshView();
    }
}
