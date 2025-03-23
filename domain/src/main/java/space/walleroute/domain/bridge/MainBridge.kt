package space.walleroute.domain.bridge

import arrow.core.Either
import space.walleroute.domain.usecase.GetRobotRouteUseCase
import space.walleroute.model.domain.RobotRoute
import space.walleroute.model.failure.Failure
import javax.inject.Inject

interface MainBridge {
    suspend fun getRobotRoute(): Either<Failure, RobotRoute>
}

class MainBridgeImpl @Inject constructor(
    private val getRobotRouteUseCase: GetRobotRouteUseCase
) : MainBridge {
    override suspend fun getRobotRoute(): Either<Failure, RobotRoute> =
        getRobotRouteUseCase.invoke()
}