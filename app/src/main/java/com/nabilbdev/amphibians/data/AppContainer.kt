package com.nabilbdev.amphibians.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nabilbdev.amphibians.network.AmphibiansAPIService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val amphibiansInfoRepository: AmphibiansInfoRepository
}


/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class DefaultAppContainer : AppContainer {
    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: AmphibiansAPIService by lazy {
        retrofit.create(AmphibiansAPIService::class.java)
    }

    /**
     * DI implementation for Amphibians Info repository
     */
    override val amphibiansInfoRepository: AmphibiansInfoRepository =
        NetworkAmphibiansInfoRepository(retrofitService)
}