package space.walleroute.domain.contract

import arrow.core.Either
import space.walleroute.model.domain.PlanetInfo
import space.walleroute.model.failure.Failure

interface MainRepository {
    suspend fun getPlanetInfo(): Either<Failure, PlanetInfo>
}