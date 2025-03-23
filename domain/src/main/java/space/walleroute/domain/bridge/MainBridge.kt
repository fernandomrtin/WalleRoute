package space.walleroute.domain.bridge

import arrow.core.Either
import space.walleroute.domain.usecase.GetPlanetInfoUseCase
import space.walleroute.domain.usecase.GetRobotRouteUseCase
import space.walleroute.model.domain.PlanetInfo
import space.walleroute.model.domain.Position
import space.walleroute.model.failure.Failure
import javax.inject.Inject

interface MainBridge {
    suspend fun getPlanetInfo(): Either<Failure, PlanetInfo>
    suspend fun getRobotRoute(planetInfo: PlanetInfo): Either<Failure, ArrayList<Position>>
}

class MainBridgeImpl @Inject constructor(
    private val getPlanetInfoUseCase: GetPlanetInfoUseCase,
    private val getRobotRouteUseCase: GetRobotRouteUseCase
) : MainBridge {
    override suspend fun getPlanetInfo(): Either<Failure, PlanetInfo> =
        getPlanetInfoUseCase.invoke()

    override suspend fun getRobotRoute(planetInfo: PlanetInfo): Either<Failure, ArrayList<Position>> =
        getRobotRouteUseCase.invoke(planetInfo)
}