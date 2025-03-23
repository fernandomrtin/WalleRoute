package space.walleroute.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import space.walleroute.domain.bridge.MainBridge
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainBridge: MainBridge) : ViewModel() {
    private val _mainState = MutableStateFlow(MainState())
    val mainState: StateFlow<MainState> = _mainState.asStateFlow()

    init {
        viewModelScope.launch {
            mainBridge.getRobotRoute().fold({

            },{

            })
        }
    }
}