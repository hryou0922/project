package com.hry.project.capcut.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/13 9:19
 */
public class GsonBox {
    public static final Gson PUBLIC = newGsonBuilder()
//            .setExclusionStrategies(new GsonIgnoreStrategy(GsonView.PRIVATE, GsonView.PROTECTED, GsonView.PUBLIC))
            .create();

    public static GsonBuilder newGsonBuilder() {
        return new GsonBuilder().disableHtmlEscaping()
//                .setFieldNamingStrategy(JsonFieldNamingPolicy.GsonProperty)
//                .registerTypeAdapter(byte[].class, new Base64TypeAdapter().nullSafe())
                .registerTypeAdapter(Date.class, new DateTypeAdapter().nullSafe())
//                .registerTypeAdapter(Duration.class, new DurationTypeAdapter().nullSafe())
                ;
    }
}
