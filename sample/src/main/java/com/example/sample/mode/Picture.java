package com.example.sample.mode;

import android.os.Parcel;
import android.os.Parcelable;

public class Picture implements Parcelable {

    private int mData;

    public int describeContents() {
        return 0;
    }
    //序列化
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }
    //反序列化
    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    private Picture(Parcel in) {
        mData = in.readInt();
    }

}
