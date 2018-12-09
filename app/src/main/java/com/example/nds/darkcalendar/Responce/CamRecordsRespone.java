package com.example.nds.darkcalendar.Responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CamRecordsRespone implements Parcelable {
    @SerializedName("success")
    private boolean success;
    @SerializedName("totalCount")
    private int totalCount;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ArrayList<CamRecordsData> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<CamRecordsData> getData() {
        return data;
    }

    public void setData(ArrayList<CamRecordsData> data) {
        this.data = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeInt(this.totalCount);
        dest.writeString(this.message);
        dest.writeTypedList(this.data);
    }

    public CamRecordsRespone() {
    }

    protected CamRecordsRespone(Parcel in) {
        this.success = in.readByte() != 0;
        this.totalCount = in.readInt();
        this.message = in.readString();
        this.data = in.createTypedArrayList(CamRecordsData.CREATOR);
    }

    public static final Parcelable.Creator<CamRecordsRespone> CREATOR = new Parcelable.Creator<CamRecordsRespone>() {
        @Override
        public CamRecordsRespone createFromParcel(Parcel source) {
            return new CamRecordsRespone(source);
        }

        @Override
        public CamRecordsRespone[] newArray(int size) {
            return new CamRecordsRespone[size];
        }
    };
}
