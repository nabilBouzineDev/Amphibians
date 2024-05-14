@file:OptIn(ExperimentalMaterial3Api::class)

package com.nabilbdev.amphibians.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nabilbdev.amphibians.R
import com.nabilbdev.amphibians.ui.screens.AmphibiansViewModel
import com.nabilbdev.amphibians.ui.screens.HomeScreen

@Composable
fun AmphibiansApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            AmphibiansTopAppBar(
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        val amphibiansViewModel: AmphibiansViewModel = viewModel()
        HomeScreen(
            amphibiansUIState = amphibiansViewModel.amphibiansUIState,
            modifier = Modifier.padding(it)
        )
    }

}

@Composable
fun AmphibiansTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge
            )
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}
