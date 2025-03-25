package space.walleroute.model.domain

import space.walleroute.model.dto.PlanetInfoDto

data class PlanetInfo(
    val dimensions: Pair<Int, Int>,
    val robotPosition: Position,
    val robotMovements: List<Movement>
)

fun PlanetInfoDto.toPlanetInfo() =
    PlanetInfo(
        dimensions = Pair(this.topRightCorner?.x ?: 0, this.topRightCorner?.y ?: 0),
        robotPosition = Position(
            Coordinates(this.roverPosition?.x ?: 0, this.roverPosition?.y ?: 0),
            orientation = Orientation.fromString(this.roverDirection)
        ),
        robotMovements = this.roverMovements?.mapNotNull {
            when (it) {
                'L' -> Movement.Left
                'R' -> Movement.Right
                'M' -> Movement.Forward
                else -> Movement.Left
            }
        } ?: emptyList()
    )
