package space.walleroute.presentation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import space.walleroute.presentation.viewmodel.MainViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import space.walleroute.ui.PlanetMatrix

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val mainState by viewModel.mainState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        PlanetMatrix(
            rows = mainState.planetDimensions.first,
            columns = mainState.planetDimensions.second,
            robotPosition = mainState.robotPosition
        )
    }
}