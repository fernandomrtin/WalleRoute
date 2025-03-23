package space.walleroute.model.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class RoverPosition(
    @SerialName("x") var x: Int? = null,
    @SerialName("y") var y: Int? = null
)