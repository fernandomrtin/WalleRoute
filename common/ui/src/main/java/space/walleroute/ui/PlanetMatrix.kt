package space.walleroute.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import space.walleroute.model.domain.Coordinates
import space.walleroute.model.domain.Orientation
import space.walleroute.model.domain.Position

@Composable
fun PlanetMatrix(xAxis: Int, yAxis: Int, robotPosition: Position) {
    LazyColumn(
        reverseLayout = true
    ) {
        items(yAxis + 1) { row ->
            LazyRow {
                items(xAxis + 1) { col ->
                    if (robotPosition.coordinates.yAxis == row && robotPosition.coordinates.xAxis == col) {
                        Cell(robotPosition = robotPosition)
                    } else {
                        Cell()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PlanetMatrixPreview() {
    PlanetMatrix(
        xAxis = 5,
        yAxis = 5,
        robotPosition = Position(Coordinates(1, 2), Orientation.North)
    )
}