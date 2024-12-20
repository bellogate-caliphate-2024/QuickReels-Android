package com.bellogatecaliphate.core.source.remote

import com.bellogatecaliphate.core.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitBuilder {
	
	fun getRetrofit(): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BuildConfig.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}
}