package com.schedule.aibaseproject.chatgpt

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.openai.com/"
    private const val Personal_Key  = "sk-NueC5FdesUMz2elZ_wWpr89-Ww8KI5HLBaOtZYszHKT3BlbkFJuNb6Ft1NifRDk0fJFdfz6lFnZtlaSseSzL0BBetgEA"

    // Function to create Retrofit instance
    fun getRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // Log network requests
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                        //key
                    .addHeader("Authorization", "Bearer $Personal_Key")
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}
