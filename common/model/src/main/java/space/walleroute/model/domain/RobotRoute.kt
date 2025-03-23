package space.walleroute.model.domain

import space.walleroute.model.dto.RobotRouteDto

data class RobotRoute(
    val position: Position,
    val planetMatrixX: Int,
    val planetMatrixY: Int,
    val movements: String
)

fun RobotRouteDto.toRobotRoute() =
    RobotRoute(
        position = Position(
            Coordinates(this.roverPosition?.x ?: 0, this.roverPosition?.y ?: 0),
            orientation = Orientation.fromString(this.roverDirection)
        ),
        planetMatrixX = this.topRightCorner?.x ?: 0,
        planetMatrixY = this.topRightCorner?.y ?: 0,
        movements = this.movements ?: ""
    )
