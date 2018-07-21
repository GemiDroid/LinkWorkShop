package com.gemi_droid.linkdev_workshop.model.network.retrofit;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRepository {

    Retrofit retrofit;

    private final String BASE_URL = "https://newsapi.org/";
    public final static String API_KEY = "533af958594143758318137469b41ba9";
    public final static String NEWS_SOURCE = "the-next-web";
    public static final int CODE_OK_200 = 200;
    public static final int CODE_OK_300 = 300;

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOKHTTPClient())
                    .build();
        }
        return retrofit;
    }

    public static OkHttpClient getOKHTTPClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient();
        client = client.newBuilder().addInterceptor(httpLoggingInterceptor)
                .readTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(1, TimeUnit.MINUTES)
                .build();
        return client;
    }
}
