package com.example.sample.mode;

import android.os.Parcel;
import android.os.Parcelable;

public class Album implements Parcelable {

  private static final Creator<Album> CREATOR = new Creator<Album>() {
    @Override
    public Album createFromParcel(Parcel source) {
      return new Album(source);
    }

    @Override
    public Album[] newArray(int size) {
      return new Album[size];
    }
  };

  private String mId;
  private String mCoverPath;
  private String mDisplayName;
  private long mCount;

  public Album(Parcel source) {
    mId = source.readString();
    mCoverPath = source.readString();
    mDisplayName = source.readString();
    mCount = source.readLong();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(mId);
    dest.writeString(mCoverPath);
    dest.writeString(mDisplayName);
    dest.writeLong(mCount);
  }
}
