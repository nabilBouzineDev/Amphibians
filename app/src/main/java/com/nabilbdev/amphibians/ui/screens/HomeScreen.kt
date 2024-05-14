package com.nabilbdev.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nabilbdev.amphibians.R
import com.nabilbdev.amphibians.model.AmphibiansInfo
import com.nabilbdev.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(
    amphibiansUIState: AmphibiansUIState,
    retryOption: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (amphibiansUIState) {
        is AmphibiansUIState.Success -> SuccessScreen(
            amphibiansUIState.amphibiansInfos,
            modifier = modifier.fillMaxSize()
        )

        is AmphibiansUIState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxSize()
        )

        is AmphibiansUIState.Error -> ErrorScreen(
            retryOption,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun ErrorScreen(
    retryOption: () -> Unit,
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
        Text(
            text = stringResource(id = R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryOption) {
            Text(text = stringResource(id = R.string.retry))
        }
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
    amphibianInfos: List<AmphibiansInfo>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(
            vertical = 8.dp
        )
    ) {
        items(
            items = amphibianInfos,
        ) { amphibianInfo ->
            AmphibianCard(
                amphibianInfo = amphibianInfo,
            )
        }
    }
}

@Composable
fun AmphibianCard(
    amphibianInfo: AmphibiansInfo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = CardDefaults.elevatedShape
    ) {
        Text(
            text = "${amphibianInfo.name}(${amphibianInfo.type})",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(amphibianInfo.imgSrc)
                .crossfade(true)
                .build(),
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(4.dp)
        )
        Text(
            text = amphibianInfo.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
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
        ErrorScreen(
            retryOption = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessScreenPreview() {
    AmphibiansTheme {
        SuccessScreen(
            amphibianInfos = listOf(
                AmphibiansInfo(
                    "Great B.S",
                    "Toad",
                    "He's spread most of its life underground....",
                    "[ x ]"
                ),
                AmphibiansInfo(
                    "Great B.S",
                    "Toad",
                    "He's spread most of its life underground....",
                    "[ x ]"
                ),
                AmphibiansInfo(
                    "Great B.S",
                    "Toad",
                    "He's spread most of its life underground....",
                    "[ x ]"
                )
            )
        )
    }
}

