package space.walleroute.presentation

import app.cash.turbine.test
import arrow.core.Either
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import space.walleroute.domain.bridge.MainBridge
import space.walleroute.model.domain.Coordinates
import space.walleroute.model.domain.Movement
import space.walleroute.model.domain.Orientation
import space.walleroute.model.domain.PlanetInfo
import space.walleroute.model.domain.Position
import space.walleroute.model.failure.Failure
import space.walleroute.presentation.viewmodel.MainIntent
import space.walleroute.presentation.viewmodel.MainViewModel

@ExperimentalCoroutinesApi
class MainViewModelUnitTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mainBridge: MainBridge = mockk(relaxed = true)

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
    }

    @Test
    fun `test emit error events`() = runTest {
        coEvery { mainBridge.getPlanetInfo() } returns Either.Left(Failure.NoData)

        viewModel = MainViewModel(mainBridge)

        viewModel.errorEvent.test {
            val result = awaitItem()
            assertEquals(Failure.NoData.msg, result)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `returns correct planet info`() = runTest {
        val planetInfo = PlanetInfo(
            robotPosition = Position(Coordinates(2, 3), Orientation.North),
            robotMovements = emptyList(),
            dimensions = Pair(5, 5)
        )
        coEvery { mainBridge.getPlanetInfo() } returns Either.Right(planetInfo)
        coEvery { mainBridge.getRobotRoute(planetInfo) } returns Either.Left(Failure.NoData)

        viewModel = MainViewModel(mainBridge)

        viewModel.mainState.test {
            val result = awaitItem()
            assertEquals(planetInfo.robotPosition, result.robotPosition)
            assertEquals(planetInfo.dimensions, result.planetDimensions)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test start robot route`() = runTest {
        val planetInfo = PlanetInfo(
            robotPosition = Position(Coordinates(2, 3), Orientation.North),
            robotMovements = listOf(Movement.Forward),
            dimensions = Pair(5, 5)
        )
        val robotRoute = arrayListOf(
            Position(Coordinates(2, 3), Orientation.North),
            Position(Coordinates(2, 4), Orientation.North)
        )

        coEvery { mainBridge.getPlanetInfo() } returns Either.Right(planetInfo)
        coEvery { mainBridge.getRobotRoute(planetInfo) } returns Either.Right(robotRoute)

        viewModel = MainViewModel(mainBridge)

        viewModel.mainState.test {
            viewModel.onIntent(MainIntent.StartRobotRoute)

            assertEquals(planetInfo.robotPosition, awaitItem().robotPosition)

            advanceTimeBy(2000)
            assertEquals(robotRoute[1], awaitItem().robotPosition)

            cancelAndIgnoreRemainingEvents()
        }
    }
}