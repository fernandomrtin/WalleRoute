package space.walleroute.model.domain

sealed class Orientation {
    data object North: Orientation()
    data object South: Orientation()
    data object East: Orientation()
    data object West: Orientation()

    companion object {
        fun fromString(value: String?): Orientation {
            return when (value?.lowercase()) {
                "N" -> North
                "S" -> South
                "E" -> East
                "W" -> West
                else -> North
            }
        }
    }
}
