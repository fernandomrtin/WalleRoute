package space.walleroute.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import space.walleroute.domain.bridge.MainBridge
import space.walleroute.model.domain.PlanetInfo
import space.walleroute.model.domain.Position
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainBridge: MainBridge) : ViewModel() {
    private val _mainState = MutableStateFlow(MainState())
    val mainState: StateFlow<MainState> = _mainState.asStateFlow()

    private var robotRoute = arrayListOf<Position>()

    init {
        viewModelScope.launch {
            mainBridge.getPlanetInfo().fold(
                ifLeft = {

                },
                ifRight = { planetInfo ->
                    _mainState.update { mainState ->
                        mainState.copy(
                            planetDimensions = planetInfo.dimensions,
                            robotPosition = planetInfo.robotPosition
                        )
                    }
                    getRobotRoute(planetInfo = planetInfo)
                })
        }
    }

    private fun getRobotRoute(planetInfo: PlanetInfo) {
        viewModelScope.launch {
            mainBridge.getRobotRoute(planetInfo = planetInfo).fold(ifLeft = {

            }, ifRight = { route ->
                robotRoute = route
            })
        }
    }

    fun onIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.StartRobotRoute -> {
                viewModelScope.launch {
                    for (position in robotRoute) {
                        _mainState.update { mainState ->
                            mainState.copy(robotPosition = position)
                        }
                        delay(3000)
                    }
                }
            }
        }
    }
}