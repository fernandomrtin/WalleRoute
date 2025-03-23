package space.walleroute.presentation.viewmodel

import space.walleroute.model.domain.Orientation
import space.walleroute.model.domain.Position

data class MainState(
    val planetDimensions: Pair<Int, Int> = Pair(5, 5),
    val robotPosition: Position = Position(0, 0, Orientation.North),
    val isRouteFinished: Boolean = false
)
