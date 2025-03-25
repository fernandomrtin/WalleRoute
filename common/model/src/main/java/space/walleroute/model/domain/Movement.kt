package space.walleroute.model.domain

sealed class Movement {
    data object Left: Movement()
    data object Right: Movement()
    data object Forward: Movement()
}