package com.student.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val BASE_URL : String = "https://vedican.elysian.online/android/index.php/v1/"

    private val logger : HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder().addInterceptor(logger)

    private lateinit var instance : RetrofitClient

    /*private val okHttpClient : OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request()
        val builder = request.newBuilder()
            .addHeader("content-type","application/json")
            .method(request.method(),request.body())
            .build()
        chain.proceed(builder)
    }*/

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())

    private val retrofit = builder.build()

    fun <T> buildRetrofit(serviceType : Class<T>) : T {
        return retrofit.create(serviceType)
    }

}