package com.example.nds.darkcalendar.Responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CamRecordsData implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("path")
    private String path;
    @SerializedName("size")
    private int size;
    @SerializedName("startTime")
    private int startTime;
    @SerializedName("duration")
    private float duration;
    @SerializedName("bitrate")
    private int bitrate;
    @SerializedName("thumbnail")
    private CamRecordsDataThumbnail thumbnail;
    @SerializedName("datetime")
    private String datetime;
    @SerializedName("start")
    private String start;
    @SerializedName("end")
    private String end;
    @SerializedName("hasMotion")
    private int hasMotion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public CamRecordsDataThumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(CamRecordsDataThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getHasMotion() {
        return hasMotion;
    }

    public void setHasMotion(int hasMotion) {
        this.hasMotion = hasMotion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.path);
        dest.writeInt(this.size);
        dest.writeInt(this.startTime);
        dest.writeFloat(this.duration);
        dest.writeInt(this.bitrate);
        dest.writeParcelable(this.thumbnail, flags);
        dest.writeString(this.datetime);
        dest.writeString(this.start);
        dest.writeString(this.end);
        dest.writeInt(this.hasMotion);
    }

    public CamRecordsData() {
    }

    protected CamRecordsData(Parcel in) {
        this.name = in.readString();
        this.path = in.readString();
        this.size = in.readInt();
        this.startTime = in.readInt();
        this.duration = in.readFloat();
        this.bitrate = in.readInt();
        this.thumbnail = in.readParcelable(CamRecordsDataThumbnail.class.getClassLoader());
        this.datetime = in.readString();
        this.start = in.readString();
        this.end = in.readString();
        this.hasMotion = in.readInt();
    }

    public static final Parcelable.Creator<CamRecordsData> CREATOR = new Parcelable.Creator<CamRecordsData>() {
        @Override
        public CamRecordsData createFromParcel(Parcel source) {
            return new CamRecordsData(source);
        }

        @Override
        public CamRecordsData[] newArray(int size) {
            return new CamRecordsData[size];
        }
    };
}
