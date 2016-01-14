package com.dhair.costin.data.remote.converter;

import com.google.gson.Gson;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Creator: dengshengjin on 16/1/13 20:48
 * Email: deng.shengjin@zuimeia.com
 */
public class JsonConverterFactory extends Converter.Factory {
    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static JsonConverterFactory create() {
        return new JsonConverterFactory();
    }


    private JsonConverterFactory() {
    }

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        return new JsonResponseBodyConverter<>(JsonResponseBodyConverter.BODY_FROM, type);
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return new JsonResponseBodyConverter<>(JsonResponseBodyConverter.BODY_TO, type);
    }
}
