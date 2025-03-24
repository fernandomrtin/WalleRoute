package space.walleroute.domain

import arrow.core.getOrElse
import junit.framework.TestCase.assertEquals
import org.junit.Test

import space.walleroute.domain.usecase.GetRobotRouteUseCase
import space.walleroute.model.domain.Coordinates
import space.walleroute.model.domain.Movement
import space.walleroute.model.domain.Orientation
import space.walleroute.model.domain.PlanetInfo
import space.walleroute.model.domain.Position

class GetRobotRouteUseCaseUnitTest {

    @Test
    fun `correct route from position simple`() {
        val getRobotRouteUseCase = GetRobotRouteUseCase()
        val result = getRobotRouteUseCase.invoke(
            PlanetInfo(
                dimensions = Pair(5, 5), robotPosition = Position(
                    Coordinates(1, 2), orientation = Orientation.North
                ), robotMovements = listOf(
                    Movement.Forward,
                    Movement.Forward,
                    Movement.Left
                )
            )
        )
        assertEquals(
            listOf(
                Position(Coordinates(1, 2), Orientation.North),
                Position(Coordinates(1, 3), Orientation.North),
                Position(Coordinates(1, 4), Orientation.North),
                Position(Coordinates(1, 4), Orientation.West)
            ),
            result.getOrElse { emptyList() }
        )
    }

    @Test
    fun `correct route from position complex`() {
        val getRobotRouteUseCase = GetRobotRouteUseCase()
        val result = getRobotRouteUseCase.invoke(
            PlanetInfo(
                dimensions = Pair(5, 5), robotPosition = Position(
                    Coordinates(1, 2), orientation = Orientation.North
                ), robotMovements = listOf(
                    Movement.Left,
                    Movement.Forward,
                    Movement.Left,
                    Movement.Forward,
                    Movement.Left,
                    Movement.Forward,
                    Movement.Left,
                    Movement.Forward,
                    Movement.Forward
                )
            )
        )
        assertEquals(
            listOf(
                Position(Coordinates(1, 2), Orientation.North),
                Position(Coordinates(1, 2), Orientation.West),
                Position(Coordinates(0, 2), Orientation.West),
                Position(Coordinates(0, 2), Orientation.South),
                Position(Coordinates(0, 1), Orientation.South),
                Position(Coordinates(0, 1), Orientation.East),
                Position(Coordinates(1, 1), Orientation.East),
                Position(Coordinates(1, 1), Orientation.North),
                Position(Coordinates(1, 2), Orientation.North),
                Position(Coordinates(1, 3), Orientation.North)
            ),
            result.getOrElse { emptyList() }
        )
    }

    @Test
    fun `robot can not move out the planet limits xAxis`() {
        val getRobotRouteUseCase = GetRobotRouteUseCase()
        val result = getRobotRouteUseCase.invoke(
            PlanetInfo(
                dimensions = Pair(5, 5), robotPosition = Position(
                    Coordinates(1, 2), orientation = Orientation.North
                ), robotMovements = listOf(
                    Movement.Left,
                    Movement.Forward,
                    Movement.Forward
                )
            )
        )
        assertEquals(
            listOf(
                Position(Coordinates(1, 2), Orientation.North),
                Position(Coordinates(1, 2), Orientation.West),
                Position(Coordinates(0, 2), Orientation.West),
                Position(Coordinates(0, 2), Orientation.West)
            ),
            result.getOrElse { emptyList() }
        )
    }

    @Test
    fun `robot can not move out the planet limits yAxis`() {
        val getRobotRouteUseCase = GetRobotRouteUseCase()
        val result = getRobotRouteUseCase.invoke(
            PlanetInfo(
                dimensions = Pair(5, 5), robotPosition = Position(
                    Coordinates(5, 5), orientation = Orientation.North
                ), robotMovements = listOf(
                    Movement.Forward
                )
            )
        )
        assertEquals(
            listOf(
                Position(Coordinates(5, 5), orientation = Orientation.North),
                Position(Coordinates(5, 5), orientation = Orientation.North)
            ),
            result.getOrElse { emptyList() }
        )
    }

    @Test
    fun `verify left turn`() {
        val getRobotRouteUseCase = GetRobotRouteUseCase()
        val result = getRobotRouteUseCase.invoke(
            PlanetInfo(
                dimensions = Pair(5, 5), robotPosition = Position(
                    Coordinates(5, 5), orientation = Orientation.North
                ), robotMovements = listOf(
                    Movement.Left
                )
            )
        )
        assertEquals(
            listOf(
                Position(Coordinates(5, 5), orientation = Orientation.North),
                Position(Coordinates(5, 5), orientation = Orientation.West)
            ),
            result.getOrElse { emptyList() }
        )
    }

    @Test
    fun `verify right turn`() {
        val getRobotRouteUseCase = GetRobotRouteUseCase()
        val result = getRobotRouteUseCase.invoke(
            PlanetInfo(
                dimensions = Pair(5, 5), robotPosition = Position(
                    Coordinates(5, 5), orientation = Orientation.North
                ), robotMovements = listOf(
                    Movement.Right
                )
            )
        )
        assertEquals(
            listOf(
                Position(Coordinates(5, 5), orientation = Orientation.North),
                Position(Coordinates(5, 5), orientation = Orientation.East)
            ),
            result.getOrElse { emptyList() }
        )
    }
}