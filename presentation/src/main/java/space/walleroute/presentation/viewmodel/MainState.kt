package space.walleroute.presentation.viewmodel

import space.walleroute.model.domain.Coordinates
import space.walleroute.model.domain.Orientation
import space.walleroute.model.domain.Position

data class MainState(
    val planetDimensions: Pair<Int, Int> = Pair(5, 5),
    val robotPosition: Position = Position(Coordinates(0, 0), Orientation.North)
)
