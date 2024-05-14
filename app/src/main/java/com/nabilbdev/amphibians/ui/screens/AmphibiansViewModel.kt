package com.nabilbdev.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nabilbdev.amphibians.network.AmphibiansAPI
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface AmphibiansUIState {
    data class Success(val info: String) : AmphibiansUIState
    object Error : AmphibiansUIState
    object Loading : AmphibiansUIState

}

class AmphibiansViewModel : ViewModel() {

    var amphibiansUIState: AmphibiansUIState by mutableStateOf(AmphibiansUIState.Loading)
        private set

    init {
        getAmphibianInfo()
    }

    private fun getAmphibianInfo() {
        viewModelScope.launch {

            amphibiansUIState = AmphibiansUIState.Loading
            amphibiansUIState = try {
                val listResult = AmphibiansAPI.retrofitService.getAmphibiansInfo()
                AmphibiansUIState.Success(
                    "Success: ${listResult.size} numbers are fetched"
                )
            } catch (e: IOException) {
                AmphibiansUIState.Error
            } catch (e: HttpException) {
                AmphibiansUIState.Error
            }
        }
    }
}
