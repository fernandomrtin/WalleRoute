package space.walleroute.presentation.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import space.walleroute.presentation.viewmodel.MainViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import space.walleroute.presentation.viewmodel.MainIntent
import space.walleroute.ui.PlanetMatrix
import space.walleroute.ui.StartButton

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val mainState by viewModel.mainState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.errorEvent) {
        viewModel.errorEvent.collect { errorMessage ->
            snackBarHostState.showSnackbar(errorMessage)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            PlanetMatrix(
                xAxis = mainState.planetDimensions.first,
                yAxis = mainState.planetDimensions.second,
                robotPosition = mainState.robotPosition
            )
            StartButton {
                viewModel.onIntent(MainIntent.StartRobotRoute)
            }
        }
    }
    SnackbarHost(hostState = snackBarHostState)
}