package com.example.nds.darkcalendar.Responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Frame implements Parcelable {
    @SerializedName("x")
    private int x;
    @SerializedName("y")
    private int y;
    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;
    @SerializedName("x1")
    private int x1;
    @SerializedName("y1")
    private int y1;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.x);
        dest.writeInt(this.y);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeInt(this.x1);
        dest.writeInt(this.y1);
    }

    public Frame() {
    }

    protected Frame(Parcel in) {
        this.x = in.readInt();
        this.y = in.readInt();
        this.width = in.readInt();
        this.height = in.readInt();
        this.x1 = in.readInt();
        this.y1 = in.readInt();
    }

    public static final Parcelable.Creator<Frame> CREATOR = new Parcelable.Creator<Frame>() {
        @Override
        public Frame createFromParcel(Parcel source) {
            return new Frame(source);
        }

        @Override
        public Frame[] newArray(int size) {
            return new Frame[size];
        }
    };
}
