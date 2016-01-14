package com.dhair.costin.data.remote.converter;

import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.internal.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

import retrofit.Converter;


/**
 * Creator: dengshengjin on 16/1/13 20:55
 * Email: deng.shengjin@zuimeia.com
 */
public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    public final static int BODY_FROM = 0x1;
    public final static int BODY_TO = 0x2;
    private final int mBodyType;
    private final Type mType;

    JsonResponseBodyConverter(int bodyType, Type type) {
        mBodyType = bodyType;
        mType = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        if (mBodyType == BODY_FROM) {
            return (T) fromBody(value);
        } else {
            return toBody(value);
        }
    }

    private Object fromBody(ResponseBody value) throws IOException {
        String json = value.string();
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.optInt("result") == 1) {
                if (jsonObject.has("data")) {
                    JSONObject dataObj = jsonObject.optJSONObject("data");
                    if (dataObj != null) {
                        return dataObj;
                    } else {
                        JSONArray array = jsonObject.optJSONArray("data");
                        if (array != null) {
                            return array;
                        } else {
                            return new JSONObject();
                        }
                    }
                } else {
                    return new JSONObject();
                }
            } else {
                throw new IOException("result is not true");
            }
        } catch (JSONException e) {
            throw new IOException(e);
        } finally {
            value.close();
        }
    }

    private T toBody(ResponseBody value) throws IOException {
        Reader reader = value.charStream();
        try {
            return new Gson().fromJson(reader, mType);
        } finally {
            Util.closeQuietly(reader);
        }
    }


}
