package com.example.sample.mode;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public int userId;
    public String userName;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
    }
}
