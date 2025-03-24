package space.walleroute.ui

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StartButton(
    onClick: () -> Unit
) {
    ElevatedButton(onClick = { onClick() }) {
        Text(stringResource(R.string.start))
    }
}

@Preview
@Composable
fun StartButtonPreview() {
    StartButton(onClick = {})
}