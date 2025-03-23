package space.walleroute.presentation.viewmodel

sealed class MainIntent {
    data object RobotsMoves : MainIntent()
}
