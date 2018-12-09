package com.example.nds.darkcalendar.Responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CamRecordsDataThumbnail implements Parcelable {
    @SerializedName("original")
    private String original;
    @SerializedName("hd")
    private String hd;
    @SerializedName("high")
    private String high;
    @SerializedName("medium")
    private String medium;
    @SerializedName("low")
    private String low;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.original);
        dest.writeString(this.hd);
        dest.writeString(this.high);
        dest.writeString(this.medium);
        dest.writeString(this.low);
    }

    public CamRecordsDataThumbnail() {
    }

    protected CamRecordsDataThumbnail(Parcel in) {
        this.original = in.readString();
        this.hd = in.readString();
        this.high = in.readString();
        this.medium = in.readString();
        this.low = in.readString();
    }

    public static final Parcelable.Creator<CamRecordsDataThumbnail> CREATOR = new Parcelable.Creator<CamRecordsDataThumbnail>() {
        @Override
        public CamRecordsDataThumbnail createFromParcel(Parcel source) {
            return new CamRecordsDataThumbnail(source);
        }

        @Override
        public CamRecordsDataThumbnail[] newArray(int size) {
            return new CamRecordsDataThumbnail[size];
        }
    };
}
