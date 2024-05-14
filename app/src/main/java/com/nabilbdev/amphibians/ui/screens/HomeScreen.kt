package com.nabilbdev.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nabilbdev.amphibians.R
import com.nabilbdev.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(
    amphibiansUIState: AmphibiansUIState,
    modifier: Modifier = Modifier
) {
    when (amphibiansUIState) {
        is AmphibiansUIState.Success -> SuccessScreen(
            amphibiansUIState.info,
            modifier = modifier.fillMaxSize()
        )

        is AmphibiansUIState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxSize()
        )

        is AmphibiansUIState.Error -> ErrorScreen(
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = stringResource(id = R.string.loading_failed),
            alignment = Alignment.Center
        )
        Text(text = stringResource(id = R.string.loading_failed))
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(id = R.string.loading),
        alignment = Alignment.Center
    )
}

@Composable
fun SuccessScreen(
    info: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = info)
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibiansTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansTheme {
        ErrorScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessScreenPreview() {
    AmphibiansTheme {
        SuccessScreen(stringResource(R.string.placeholder_success))
    }
}

