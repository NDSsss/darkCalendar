package com.example.nds.darkcalendar.Responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GetDatesResponce implements Parcelable {
    @SerializedName("success")
    private boolean success;
    @SerializedName("totalCount")
    private int totalCount;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private GetDatesData getDatesData;

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

    public GetDatesData getGetDatesData() {
        return getDatesData;
    }

    public void setGetDatesData(GetDatesData getDatesData) {
        this.getDatesData = getDatesData;
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
        dest.writeParcelable(this.getDatesData, flags);
    }

    public GetDatesResponce() {
    }

    protected GetDatesResponce(Parcel in) {
        this.success = in.readByte() != 0;
        this.totalCount = in.readInt();
        this.message = in.readString();
        this.getDatesData = in.readParcelable(GetDatesData.class.getClassLoader());
    }

    public static final Parcelable.Creator<GetDatesResponce> CREATOR = new Parcelable.Creator<GetDatesResponce>() {
        @Override
        public GetDatesResponce createFromParcel(Parcel source) {
            return new GetDatesResponce(source);
        }

        @Override
        public GetDatesResponce[] newArray(int size) {
            return new GetDatesResponce[size];
        }
    };
}
