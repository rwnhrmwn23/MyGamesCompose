package com.onedev.mygamescompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onedev.mygamescompose.R
import com.onedev.mygamescompose.ui.theme.MyGamesComposeTheme

@Composable
fun ErrorView(message: String) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(R.drawable.error),
                contentDescription = "error",
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = message,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ErrorViewPreview() {
    MyGamesComposeTheme {
        ErrorView("Not Found")
    }
}
