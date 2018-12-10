package com.example.nds.darkcalendar.Activities.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nds.darkcalendar.R;
import com.example.nds.darkcalendar.Responce.CamRecordsRespone;
import com.example.nds.darkcalendar.zoomlayout.ZoomLayout;

import java.util.ArrayList;

public class ScrollFragment extends Fragment implements ZoomLayout.IZoomCallback {
    public static final String RECORDS_RESPONCE = "ScrollFragment.RECORDS_RESPONCE";
    CamRecordsRespone mRecordsRespone;
    TextView textView;
    private int[] redSeconds = {10,11,12,13,14,15,16,17,19,20,21,22,23,24,25,
            110,111,112,113,114,115,116,117,119,120,121,122,123,124,125,
            210,211,212,213,214,215,216,217,219,220,221,222,223,224,225,
            310,311,312,313,314,315,316,317,319,320,321,322,323,324,325,
            410,411,412,413,414,415,416,417,419,420,421,422,423,424,425,
            510,511,512,513,514,515,516,517,519,520,521,522,523,524,525,
            610,611,612,613,614,615,616,617,619,620,621,622,623,624,625,
            710,711,712,713,714,715,716,717,719,720,721,722,723,724,725,
            810,811,812,813,814,815,816,817,819,820,821,822,823,824,825};

    private ZoomLayout zoomLayout;
    private ArrayList<Integer> redSecondsList;

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if(args!=null){
            mRecordsRespone = args.getParcelable(RECORDS_RESPONCE);
//            textView.setText(mRecordsRespone.getData().get(0).getDatetime());

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scroll,null);
        textView = view.findViewById(R.id.tv_fragment_scroll);
        textView.setText(mRecordsRespone.getData().get(0).getDatetime());
        zoomLayout =(ZoomLayout) view.findViewById(R.id.zoom_fragment_scroll);
        zoomLayout.setISetScaleListener(this);
        redSecondsList = new ArrayList<>();
        for(int i =0; i<redSeconds.length;i++){
            redSecondsList.add(redSeconds[i]);
        }
        zoomLayout.setExtraStartSecs(ZoomLayout.SECS_IN_DAY/24*6);
        zoomLayout.setExtraEndSecs(ZoomLayout.SECS_IN_DAY/24*3);
        zoomLayout.setRedLi(redSecondsList);
        zoomLayout.setProgress(ZoomLayout.SECS_IN_DAY/2);
//        et1 = (TextView)findViewById(R.id.tv_info);
        return view;
    }


    @Override
    public void setScale(float scale) {
//        mScale = scale;
//        et1.setText("Scale "+String.valueOf(mScale));
    }

    @Override
    public void secondClicked(float clickedSecond) {
//        et1.setText("Clicked on "+clickedSecond+" second");

    }

    @Override
    public ArrayList<Integer> getRedLines() {
        return redSecondsList;
    }
}
