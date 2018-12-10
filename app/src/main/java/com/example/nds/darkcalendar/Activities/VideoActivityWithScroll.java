package com.example.nds.darkcalendar.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nds.darkcalendar.Activities.fragments.ScrollFragment;
import com.example.nds.darkcalendar.R;
import com.example.nds.darkcalendar.Responce.CamRecordsRespone;
import com.example.nds.darkcalendar.Responce.GetDatesResponce;
import com.example.nds.darkcalendar.Responce.MotionResponce;
import com.example.nds.darkcalendar.Services.ICamRecordsService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoActivityWithScroll extends AppCompatActivity {
    public static final String RECORDS_RESPONCE = "VideoActivityWithScroll.RECORDS_RESPONCE";
    public Retrofit retrofit;
    private CamRecordsRespone recordsRespone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRetrofit();
        getRectangles();
        setContentView(R.layout.activity_video_scroll);
        recordsRespone = (CamRecordsRespone) getIntent().getExtras().getParcelable(RECORDS_RESPONCE);
        LinearLayout llVideoContainer = (LinearLayout)findViewById(R.id.video_scroll_video_container);
        LinearLayout llScrollContainer = (LinearLayout)findViewById(R.id.video_scroll_scroll_container);
        ScrollFragment scrollFragment = new ScrollFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ScrollFragment.RECORDS_RESPONCE,recordsRespone);
        scrollFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.video_scroll_scroll_container,scrollFragment).commit();
    }

    private void initRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        //create an instance of Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://owlsight.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    private void getRectangles(){
        ICamRecordsService camRecordsService = retrofit.create(ICamRecordsService.class);
        Call<MotionResponce> motionResponceCall = camRecordsService.getMotions("PHPSESSID=lmots4gqjsc1i4kkh7elipgkt5", 179,recordsRespone.getData().get(0).getStart(),recordsRespone.getData().get(recordsRespone.getData().size()-1).getEnd());
        motionResponceCall.enqueue(new Callback<MotionResponce>() {
            @Override
            public void onResponse(Call<MotionResponce> call, Response<MotionResponce> response) {
                handleMotionResponce(response.body());
            }

            @Override
            public void onFailure(Call<MotionResponce> call, Throwable t) {
                showError(t.getLocalizedMessage());
            }
        });
    }

    private void handleMotionResponce(MotionResponce motionResponce){
        if(motionResponce.isSuccess()){

        } else {
            showError(motionResponce.getMessage());
        }
    }



    private void showError(String errorMessage){
        Toast.makeText(this, errorMessage,Toast.LENGTH_LONG);
    }
}
