package space.walleroute.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import space.walleroute.model.domain.Position

@Composable
fun PlanetMatrix(rows: Int, columns: Int, robotPosition: Position) {
    LazyColumn {
        items(rows) { row ->
            LazyRow {
                items(columns) { col ->
                    if (robotPosition.coordinates.latitude == row && robotPosition.coordinates.longitude == col) {
                        Cell(robotPosition = robotPosition)
                    } else {
                        Cell()
                    }
                }
            }
        }
    }
}