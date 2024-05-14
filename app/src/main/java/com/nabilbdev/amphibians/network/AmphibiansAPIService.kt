package com.nabilbdev.amphibians.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nabilbdev.amphibians.model.AmphibiansInfo
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET


private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface AmphibiansAPIService {
    @GET("amphibians")
    suspend fun getAmphibiansInfo(): List<AmphibiansInfo>
}

object AmphibiansAPI {
    val retrofitService: AmphibiansAPIService by lazy {
        retrofit.create(AmphibiansAPIService::class.java)
    }
}
