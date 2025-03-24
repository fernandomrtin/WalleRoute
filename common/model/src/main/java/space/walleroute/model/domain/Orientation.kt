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

fun Orientation.turn(movement: Movement): Orientation {
    return when (this) {
        is Orientation.North -> when (movement) {
            is Movement.Left -> Orientation.West
            is Movement.Right -> Orientation.East
            else -> this
        }
        is Orientation.South -> when (movement) {
            is Movement.Left -> Orientation.East
            is Movement.Right -> Orientation.West
            else -> this
        }
        is Orientation.East -> when (movement) {
            is Movement.Left -> Orientation.North
            is Movement.Right -> Orientation.South
            else -> this
        }
        is Orientation.West -> when (movement) {
            is Movement.Left -> Orientation.South
            is Movement.Right -> Orientation.North
            else -> this
        }
    }
}
