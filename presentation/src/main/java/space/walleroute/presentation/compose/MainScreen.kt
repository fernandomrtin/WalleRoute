package space.walleroute.presentation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import space.walleroute.presentation.viewmodel.MainViewModel
import androidx.compose.runtime.*

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val mainState by viewModel.mainState.collectAsState()

    if (mainState.wallePosition.isNotEmpty()) {

    }

}