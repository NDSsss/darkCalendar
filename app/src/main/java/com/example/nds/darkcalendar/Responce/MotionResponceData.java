package com.example.nds.darkcalendar.Responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MotionResponceData implements Parcelable {
    @SerializedName("date")
    private String date;
    @SerializedName("frames")
    private ArrayList<Frame> frames;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Frame> getFrames() {
        return frames;
    }

    public void setFrames(ArrayList<Frame> frames) {
        this.frames = frames;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeTypedList(this.frames);
    }

    public MotionResponceData() {
    }

    protected MotionResponceData(Parcel in) {
        this.date = in.readString();
        this.frames = in.createTypedArrayList(Frame.CREATOR);
    }

    public static final Parcelable.Creator<MotionResponceData> CREATOR = new Parcelable.Creator<MotionResponceData>() {
        @Override
        public MotionResponceData createFromParcel(Parcel source) {
            return new MotionResponceData(source);
        }

        @Override
        public MotionResponceData[] newArray(int size) {
            return new MotionResponceData[size];
        }
    };
}
