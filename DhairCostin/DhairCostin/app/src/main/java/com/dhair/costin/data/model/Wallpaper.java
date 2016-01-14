package com.dhair.costin.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Creator: dengshengjin on 16/1/12 20:32
 * Email: deng.shengjin@zuimeia.com
 */
public class Wallpaper extends BaseModel implements Parcelable {
    private long mId;
    private String mDesc;
    private String mPubDate;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String desc) {
        mDesc = desc;
    }

    public String getPubDate() {
        return mPubDate;
    }

    public void setPubDate(String pubDate) {
        mPubDate = pubDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mId);
        dest.writeString(this.mDesc);
        dest.writeString(this.mPubDate);
    }

    public Wallpaper() {
    }

    protected Wallpaper(Parcel in) {
        this.mId = in.readLong();
        this.mDesc = in.readString();
        this.mPubDate = in.readString();
    }

    public static List<Wallpaper> parse(JSONArray array) {
        List<Wallpaper> pictures = new ArrayList<Wallpaper>();
        if (array == null) {
            return pictures;
        }

        for (int i = 0; i < array.length(); i++) {
            Wallpaper picture = parsePicture(array.optJSONObject(i));
            if (picture != null) {
                pictures.add(picture);
            }
        }

        return pictures;
    }

    public static Wallpaper parsePicture(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }

        Wallpaper wallpaper = new Wallpaper();
        wallpaper.setId(jsonObject.optLong("id"));
        wallpaper.setDesc(jsonObject.optString("description"));
        wallpaper.setPubDate(jsonObject.optString("pub_time"));
        return wallpaper;
    }

    public static final Creator<Wallpaper> CREATOR = new Creator<Wallpaper>() {
        public Wallpaper createFromParcel(Parcel source) {
            return new Wallpaper(source);
        }

        public Wallpaper[] newArray(int size) {
            return new Wallpaper[size];
        }
    };
}
