package com.bellogatecaliphate.core.source.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
	
	fun getRetrofit(baseUrl: String): Retrofit {
		
		val httpClient = OkHttpClient.Builder()
			.readTimeout(60, TimeUnit.SECONDS)
			.connectTimeout(60, TimeUnit.SECONDS)
			.callTimeout(60, TimeUnit.SECONDS)
			.retryOnConnectionFailure(false)
		
		httpClient.addInterceptor { chain ->
			val original = chain.request()
			
			val builder = original.newBuilder()
			val request = builder.build()
			chain.proceed(request)
		}
		
		val client = httpClient.build()
		return Retrofit.Builder()
			.baseUrl(baseUrl)
			.addConverterFactory(ScalarsConverterFactory.create())
			.addConverterFactory(GsonConverterFactory.create())
			.client(client)
			.build()
	}
}