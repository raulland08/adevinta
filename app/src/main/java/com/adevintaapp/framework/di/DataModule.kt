package com.adevintaapp.framework.di

import com.adevintaapp.AdevintaApplication
import com.adevintaapp.BuildConfig.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

val  dataModule = module(override = true) {
    single { createOkHttpClient() }
    single { provideRetrofit(get()) }
    single { AdevintaApplication.prefs }
}

fun createOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor { chain ->

            val original: Request = chain.request()

            val url = original.url.newBuilder()
                .build()

            val request: Request = original.newBuilder()
                .url(url)
                .build()

            runCatching {
                chain.proceed(request)
            }.onFailure {
                throw IOException(it)
            }.getOrThrow()

        }
        .build()

/**
 * Creates Retrofit instance with its configurations
 *
 * @param   okHttpClient    OkHttpClient object representing the client property for the retrofit instance
 * @return                  Retrofit object
 */
fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(okHttpClient)
        .build()

