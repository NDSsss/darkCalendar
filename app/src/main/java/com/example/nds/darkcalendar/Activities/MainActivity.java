package com.example.nds.darkcalendar.Activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.nds.darkcalendar.R;
import com.example.nds.darkcalendar.Responce.GetDatesResponce;
import com.example.nds.darkcalendar.Services.ICamRecordsService;
import com.example.nds.darkcalendar.ZoomLayout.ZoomActivity;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity  {

    RelativeLayout container;
    Button btnRefresh;
    CaldroidFragment caldroidFragment;
    ICalendarItemCliced calendarItemCliced;
    public Retrofit retrofit;
    CaldroidListener caldroidListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        //create an instance of Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://owlsight.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        container = (RelativeLayout) findViewById(R.id.calendar_containter);
        btnRefresh = (Button) findViewById(R.id.btn_refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVideoScrollActivity();
            }
        });
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
        createCaldroidListener();
        caldroidFragment.setCaldroidListener(caldroidListener);
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar_containter, caldroidFragment);
        t.commit();
        getDates();
    }

    private void createCaldroidListener(){
        caldroidListener =  new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {

            }

            @Override
            public void onCaldroidViewCreated() {
                super.onCaldroidViewCreated();
                caldroidFragment.getWeekdayGridView().setBackground(getResources().getDrawable(R.drawable.week_background));
//        caldroidFragment.getWeekdayGridView().setPadding(10,20,10,20);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.bottomMargin = 30;
                params.topMargin = 30;
                params.leftMargin = 30;
                params.rightMargin = 30;
                caldroidFragment.getWeekdayGridView().setLayoutParams(params);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getDates(){
        ICamRecordsService camRecordsService = retrofit.create(ICamRecordsService.class);
        Call<GetDatesResponce> datesResponce = camRecordsService.getDates("PHPSESSID=lmots4gqjsc1i4kkh7elipgkt5", 179);
        datesResponce.enqueue(new Callback<GetDatesResponce>() {
            @Override
            public void onResponse(Call<GetDatesResponce> call, Response<GetDatesResponce> response) {
                handleDatesResponce(response.body());
            }

            @Override
            public void onFailure(Call<GetDatesResponce> call, Throwable t) {
                showError(t.getLocalizedMessage());
            }
        });
    }

    private void handleDatesResponce(GetDatesResponce responce){
        if(responce.isSuccess()) {
            Map<Date, Drawable> dates = new HashMap<>();
            final Map<Date, Integer> colors = new HashMap<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            final ArrayList<Date> markedDays = new ArrayList<>();
            try {
                for (int i = 0; i < responce.getGetDatesData().getFolders().size(); i++) {
                    markedDays.add(format.parse(responce.getGetDatesData().getFolders().get(i)));
                }
            } catch (ParseException e) {
                Toast.makeText(this,e.getLocalizedMessage(),Toast.LENGTH_LONG);
            }
            for (int i = 0; i < markedDays.size(); i++) {
                dates.put(markedDays.get(i), getResources().getDrawable(R.drawable.white_background_vector));
            }

            for (int i = 0; i < markedDays.size(); i++) {
                colors.put(markedDays.get(i), (R.color.colorAccent));
            }

            caldroidFragment.setBackgroundDrawableForDates(dates);
            caldroidFragment.setTextColorForDates(colors);
            caldroidFragment.refreshView();

        } else {
            Toast.makeText(this,responce.getMessage(),Toast.LENGTH_LONG);
        }
    }

    private void showError(String errorMessage){
        Toast.makeText(this, errorMessage,Toast.LENGTH_LONG);
    }

    private void openVideoScrollActivity(){
        Intent intent = new Intent(this,ZoomActivity.class);
        startActivity(intent);
    }




    public interface ICalendarItemCliced {
        public void dateFromMapClicked(int position);
    }
}
