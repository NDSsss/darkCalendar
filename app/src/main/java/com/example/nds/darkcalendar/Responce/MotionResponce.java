package com.example.nds.darkcalendar.Responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MotionResponce implements Parcelable {
    @SerializedName("success")
    private boolean success;
    @SerializedName("totalCount")
    private int totalCount;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private MotionResponceData data;

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

    public MotionResponceData getData() {
        return data;
    }

    public void setData(MotionResponceData data) {
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
        dest.writeParcelable(this.data, flags);
    }

    public MotionResponce() {
    }

    protected MotionResponce(Parcel in) {
        this.success = in.readByte() != 0;
        this.totalCount = in.readInt();
        this.message = in.readString();
        this.data = in.readParcelable(MotionResponceData.class.getClassLoader());
    }

    public static final Parcelable.Creator<MotionResponce> CREATOR = new Parcelable.Creator<MotionResponce>() {
        @Override
        public MotionResponce createFromParcel(Parcel source) {
            return new MotionResponce(source);
        }

        @Override
        public MotionResponce[] newArray(int size) {
            return new MotionResponce[size];
        }
    };
}
