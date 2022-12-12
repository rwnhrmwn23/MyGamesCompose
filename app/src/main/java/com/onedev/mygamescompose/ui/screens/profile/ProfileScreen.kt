package com.onedev.mygamescompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onedev.mygamescompose.R
import com.onedev.mygamescompose.ui.theme.MyGamesComposeTheme

@Composable
fun ProfileScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painterResource(R.drawable.foto),
                contentDescription = stringResource(R.string.name),
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(100.dp))
            )
            Text(
                text = stringResource(R.string.name),
                style = MaterialTheme.typography.subtitle2,
                textAlign = TextAlign.Center,
            )
            Text(
                text = stringResource(R.string.email),
                style = MaterialTheme.typography.subtitle2,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    MyGamesComposeTheme {
        ProfileScreen()
    }
}
