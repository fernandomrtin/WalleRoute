package space.walleroute.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanetInfoDto(
    @SerialName("topRightCorner") var topRightCorner: TopRightCorner? = TopRightCorner(),
    @SerialName("roverPosition") var roverPosition: RoverPosition? = RoverPosition(),
    @SerialName("roverDirection") var roverDirection: String? = null,
    @SerialName("movements") var roverMovements: String? = null
)
