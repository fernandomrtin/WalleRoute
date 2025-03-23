package space.walleroute.data.datasource

import android.content.Context
import arrow.core.Either
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import space.walleroute.data.R
import space.walleroute.model.dto.RobotRouteDto
import space.walleroute.model.failure.Failure
import java.io.InputStreamReader
import javax.inject.Inject

class MainLocalDataSource @Inject constructor(@ApplicationContext private val context: Context) {

    fun getRobotRouteData(): Either<Failure, RobotRouteDto> {
        return try {
            val jsonString = readJsonFromRaw(R.raw.input)
            val robotRouteDto = Json.decodeFromString<RobotRouteDto>(jsonString)
            Either.Right(robotRouteDto)
        } catch (e: Exception) {
            Either.Left(Failure.JsonParsingError)
        }
    }

    private fun readJsonFromRaw(resourceId: Int): String {
        return try {
            val inputStream = context.resources.openRawResource(resourceId)
            InputStreamReader(inputStream).use { it.readText() }
        } catch (e: Exception) {
            throw RuntimeException("Error al leer el archivo JSON con resourceId: $resourceId", e)
        }
    }
}