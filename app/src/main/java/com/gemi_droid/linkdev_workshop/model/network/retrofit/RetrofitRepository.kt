package com.gemi_droid.linkdev_workshop.model.network.retrofit

import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepository {

     lateinit var retrofit:Retrofit

    private val BASE_URL = "https://newsapi.org/"

    fun retrofitInstance(): Retrofit {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okhttpClient)
                    .build()

        return retrofit
    }

    companion object {
        val API_KEY = "533af958594143758318137469b41ba9"
        val NEWS_SOURCE = "the-next-web"
        val CODE_OK_200 = 200
        val CODE_OK_300 = 300

        val okhttpClient: OkHttpClient
            get() {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                var client = OkHttpClient()
                client = client.newBuilder().addInterceptor(httpLoggingInterceptor)
                        .readTimeout(1, TimeUnit.MINUTES)
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .build()
                return client
            }
    }
}
