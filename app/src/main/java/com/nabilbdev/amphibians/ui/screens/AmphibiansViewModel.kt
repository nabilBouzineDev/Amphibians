package com.nabilbdev.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nabilbdev.amphibians.AmphibiansInfoApplication
import com.nabilbdev.amphibians.data.AmphibiansInfoRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface AmphibiansUIState {
    data class Success(val info: String) : AmphibiansUIState
    object Error : AmphibiansUIState
    object Loading : AmphibiansUIState

}

class AmphibiansViewModel(private val amphibiansInfoRepository: AmphibiansInfoRepository) :
    ViewModel() {

    /** The mutable State that stores the status of the most recent request */
    var amphibiansUIState: AmphibiansUIState by mutableStateOf(AmphibiansUIState.Loading)
        private set

    init {
        getAmphibianInfo()
    }

    private fun getAmphibianInfo() {
        viewModelScope.launch {

            amphibiansUIState = AmphibiansUIState.Loading
            amphibiansUIState = try {
                val listResult = amphibiansInfoRepository.getAmphibiansInfo()
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

    /**
     * Factory for [AmphibiansViewModel] that takes [AmphibiansInfoRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansInfoApplication)
                val amphibiansInfoRepository = application.container.amphibiansInfoRepository
                AmphibiansViewModel(amphibiansInfoRepository)
            }
        }
    }

}
