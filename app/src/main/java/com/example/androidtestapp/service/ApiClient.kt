package com.example.androidtestapp.service

import android.content.Context
import com.example.androidtestapp.utils.Constants
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    object SampleObject {
        @JvmStatic
        var apiClient: ApiClient? = null

        @JvmStatic
        fun getInstance(): ApiClient {
            if (apiClient == null) {
                apiClient = ApiClient()
            }
            return apiClient as ApiClient
        }
    }

    /**
     * retrofit caching mechanism is used for image caching
     */
    fun getRetrofit(context: Context, isNetworkConnected: Boolean): Retrofit {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient =
            OkHttpClient.Builder().cache(myCache).addInterceptor(logging).addInterceptor { chain ->
                var request = chain.request()
                request = if (isNetworkConnected)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }

                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()

        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}