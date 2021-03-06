package com.example.nds.darkcalendar.Services;

import com.example.nds.darkcalendar.Responce.CamRecordsRespone;
import com.example.nds.darkcalendar.Responce.GetDatesResponce;
import com.example.nds.darkcalendar.Responce.MotionResponce;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ICamRecordsService {
    @GET("papi/user-cam-record-folders")
    Call<GetDatesResponce> getDates(@Header("Cookie") String cookie, @Query("id") int id);

    @GET("papi/user-cam-record-folders")
    Call<CamRecordsRespone> getFolders(@Header("Cookie") String cookie, @Query("id") int id, @Query("folder") String folder);

    @GET("papi/get-motion-for-period")
    Call<MotionResponce> getMotions(@Header("Cookie") String cookie, @Query("id") int id,@Query("from") String from, @Query("to") String to);
}
