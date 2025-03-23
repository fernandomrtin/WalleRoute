package space.walleroute.data.repository

import arrow.core.Either
import space.walleroute.data.datasource.MainLocalDataSource
import space.walleroute.domain.contract.MainRepository
import space.walleroute.model.domain.RobotRoute
import space.walleroute.model.domain.toRobotRoute
import space.walleroute.model.failure.Failure
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainLocalDataSource: MainLocalDataSource
) : MainRepository {
    override suspend fun getRobotRoute(): Either<Failure, RobotRoute> = mainLocalDataSource.getRobotRouteData().map {
        it.toRobotRoute()
    }
}