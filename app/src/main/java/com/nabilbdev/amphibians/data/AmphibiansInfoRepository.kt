package com.nabilbdev.amphibians.data

import com.nabilbdev.amphibians.model.AmphibiansInfo
import com.nabilbdev.amphibians.network.AmphibiansAPIService

/**
 * Repository that fetch amphibians info list from amphibiansAPI.
 */

interface AmphibiansInfoRepository {
    /** Fetches list of [AmphibiansInfo] from [AmphibiansAPIService] */
    suspend fun getAmphibiansInfo(): List<AmphibiansInfo>
}


/** Network Implementation of Repository that fetch amphibians info list from amphibiansApi. */
class NetworkAmphibiansInfoRepository(
    private val amphibiansApi: AmphibiansAPIService
) : AmphibiansInfoRepository {
    override suspend fun getAmphibiansInfo(): List<AmphibiansInfo> {
        return amphibiansApi.getAmphibiansInfo()
    }
}

