package space.walleroute.domain.usecase

import arrow.core.Either
import space.walleroute.domain.contract.MainRepository
import space.walleroute.model.domain.PlanetInfo
import space.walleroute.model.failure.Failure
import javax.inject.Inject

class GetPlanetInfoUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend operator fun invoke(): Either<Failure, PlanetInfo> = mainRepository.getPlanetInfo()
}