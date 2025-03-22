package space.walleroute.presentation.viewmodel

sealed class MainIntent {
    data object WalleMoves : MainIntent()
}
