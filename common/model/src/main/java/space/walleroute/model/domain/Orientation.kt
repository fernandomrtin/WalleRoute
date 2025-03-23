package space.walleroute.model.domain

sealed class Orientation {
    data object North: Orientation()
    data object South: Orientation()
    data object East: Orientation()
    data object West: Orientation()
}
