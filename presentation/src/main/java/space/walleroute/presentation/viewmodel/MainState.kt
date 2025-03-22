package space.walleroute.presentation.viewmodel

data class MainState(
    val planetDimensions: String = "",
    val wallePosition: String = "",
    val isRouteFinished: Boolean = false
)
