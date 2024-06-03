package com.hadykahlout.doctory.network

import BASE_DOMAIN
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val httpLoggingInterceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(httpLoggingInterceptor)
    .readTimeout(1, TimeUnit.MINUTES)
    .connectTimeout(1, TimeUnit.MINUTES)
    .build()

var gson: Gson = GsonBuilder()
    .setLenient()
    .create()

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_DOMAIN)
    .client(okHttpClient)
    .build()

object ApiService {
    val authClient: AuthApiClient by lazy {
        retrofit.create(AuthApiClient::class.java)
    }
    val doctorClient: DoctorApiClient by lazy {
        retrofit.create(DoctorApiClient::class.java)
    }
    val patientClient: PatientApiClient by lazy {
        retrofit.create(PatientApiClient::class.java)
    }
}