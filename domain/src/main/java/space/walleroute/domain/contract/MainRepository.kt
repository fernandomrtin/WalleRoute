package space.walleroute.domain.contract

import arrow.core.Either
import space.walleroute.model.domain.RobotRoute
import space.walleroute.model.failure.Failure

interface MainRepository {
    suspend fun getRobotRoute(): Either<Failure, RobotRoute>
}