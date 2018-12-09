package com.example.nds.darkcalendar.Responce;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetDatesData implements Parcelable {
    @SerializedName("folders")
    private ArrayList<String> folders;

    public ArrayList<String> getFolders() {
        return folders;
    }

    public void setFolders(ArrayList<String> folders) {
        this.folders = folders;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.folders);
    }

    public GetDatesData() {
    }

    protected GetDatesData(Parcel in) {
        this.folders = in.createStringArrayList();
    }

    public static final Parcelable.Creator<GetDatesData> CREATOR = new Parcelable.Creator<GetDatesData>() {
        @Override
        public GetDatesData createFromParcel(Parcel source) {
            return new GetDatesData(source);
        }

        @Override
        public GetDatesData[] newArray(int size) {
            return new GetDatesData[size];
        }
    };
}
