package space.walleroute.data.repository

import arrow.core.Either
import space.walleroute.data.datasource.MainLocalDataSource
import space.walleroute.domain.contract.MainRepository
import space.walleroute.model.domain.PlanetInfo
import space.walleroute.model.domain.toPlanetInfo
import space.walleroute.model.failure.Failure
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainLocalDataSource: MainLocalDataSource
) : MainRepository {
    override suspend fun getPlanetInfo(): Either<Failure, PlanetInfo> = mainLocalDataSource.getPlanetInfo().map {
        it.toPlanetInfo()
    }
}