package space.walleroute.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import space.walleroute.model.domain.Coordinates
import space.walleroute.model.domain.Orientation
import space.walleroute.model.domain.Position

@Composable
fun Cell(
    robotPosition: Position? = null
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .padding(4.dp)
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        robotPosition?.let {
            Image(
                painter = painterResource(
                    when (robotPosition.orientation) {
                        Orientation.East -> R.drawable.arrow_east
                        Orientation.North -> R.drawable.arrow_north
                        Orientation.South -> R.drawable.arrow_south
                        Orientation.West -> R.drawable.arrow_west
                    }
                ),
                contentDescription = "Robot position"
            )
        }
    }
}

@Preview
@Composable
fun CellPreview() {
    Cell(robotPosition = Position(Coordinates(0, 0), orientation = Orientation.North))
}