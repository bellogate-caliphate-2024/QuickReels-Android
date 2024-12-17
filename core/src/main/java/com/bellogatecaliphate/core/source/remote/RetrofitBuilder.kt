package com.bellogatecaliphate.core.source.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitBuilder {
	
	private const val BASE_URL = "https://api.themoviedb.org/3/tv/"
	
	fun getRetrofit(): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}
}