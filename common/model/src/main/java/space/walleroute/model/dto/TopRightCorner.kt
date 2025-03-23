package space.walleroute.model.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class TopRightCorner(
    @SerialName("x") var x: Int? = null,
    @SerialName("y") var y: Int? = null
)