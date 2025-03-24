package space.walleroute.domain.usecase

import arrow.core.Either
import arrow.core.right
import space.walleroute.model.domain.Coordinates
import space.walleroute.model.domain.Movement
import space.walleroute.model.domain.Orientation
import space.walleroute.model.domain.PlanetInfo
import space.walleroute.model.domain.Position
import space.walleroute.model.domain.turn
import space.walleroute.model.failure.Failure
import javax.inject.Inject

class GetRobotRouteUseCase @Inject constructor() {

    operator fun invoke(planetInfo: PlanetInfo): Either<Failure, ArrayList<Position>> {
        var currentPosition = planetInfo.robotPosition
        val movements = planetInfo.robotMovements
        val route = arrayListOf<Position>()
        route.add(currentPosition)
        for (index in movements.indices) {
            val nextMove = move(currentPosition = currentPosition, movement = movements[index], planetDimensions = planetInfo.dimensions)
            route.add(nextMove)
            currentPosition = nextMove
        }

        return route.right()
    }

    private fun move(currentPosition: Position, movement: Movement, planetDimensions: Pair<Int, Int>): Position {
        var targetPosition = currentPosition
        when (movement) {
            is Movement.Left, Movement.Right -> {
                targetPosition = Position(coordinates = targetPosition.coordinates, orientation = targetPosition.orientation.turn(movement))
            }
            is Movement.Forward -> {
                when (targetPosition.orientation) {
                    Orientation.North -> {
                        if (targetPosition.coordinates.yAxis + 1 <= planetDimensions.second) {
                            targetPosition = Position(
                                coordinates = Coordinates(
                                    xAxis = targetPosition.coordinates.xAxis,
                                    yAxis = targetPosition.coordinates.yAxis + 1
                                ), orientation = targetPosition.orientation
                            )
                        }
                    }
                    Orientation.South -> if (targetPosition.coordinates.yAxis - 1 >= 0) {
                        targetPosition = Position(
                            coordinates = Coordinates(
                                xAxis = targetPosition.coordinates.xAxis,
                                yAxis = targetPosition.coordinates.yAxis - 1
                            ), orientation = targetPosition.orientation
                        )
                    }

                    Orientation.East -> if (targetPosition.coordinates.xAxis + 1 <= planetDimensions.first) {
                        targetPosition = Position(
                            coordinates = Coordinates(
                                xAxis = targetPosition.coordinates.xAxis + 1,
                                yAxis = targetPosition.coordinates.yAxis
                            ), orientation = targetPosition.orientation
                        )
                    }
                    Orientation.West -> if (targetPosition.coordinates.xAxis - 1 >= 0) {
                        targetPosition = Position(
                            coordinates = Coordinates(
                                xAxis = targetPosition.coordinates.xAxis - 1,
                                yAxis = targetPosition.coordinates.yAxis
                            ), orientation = targetPosition.orientation
                        )
                    }
                }
            }
        }
        return targetPosition
    }
}