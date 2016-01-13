package com.dhair.costin.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Creator: dengshengjin on 16/1/13 14:56
 * Email: deng.shengjin@zuimeia.com
 */
public class UserModel implements Parcelable {
    private long mUserId;
    private String mUsername;

    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long userId) {
        mUserId = userId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mUserId);
        dest.writeString(this.mUsername);
    }

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        this.mUserId = in.readLong();
        this.mUsername = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    @Override
    public String toString() {
        return "UserModel{" +
                "mUserId=" + mUserId +
                ", mUsername='" + mUsername + '\'' +
                '}';
    }
}
