package com.nabilbdev.amphibians.network

import com.nabilbdev.amphibians.model.AmphibiansInfo
import retrofit2.http.GET

interface AmphibiansAPIService {
    /**
     * Returns a [List] of [AmphibiansInfo] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("amphibians")
    suspend fun getAmphibiansInfo(): List<AmphibiansInfo>
}

