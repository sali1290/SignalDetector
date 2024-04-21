package com.example.signaldetector.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signaldetector.R

@Composable
fun MainScreen() {

    val mainScreenItems =
        listOf(
            AppFeature(title = stringResource(id = R.string.sim_cards), icon = R.drawable.ic_sim),
            AppFeature(title = stringResource(id = R.string.ip), icon = R.drawable.ic_ip)
        )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {

        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ) {
            itemsIndexed(mainScreenItems) { _, item ->
                MainItems(title = item.title, icon = item.icon)
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Composable
fun MainItems(title: String, icon: Int) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(15.dp),
        shape = RoundedCornerShape(5.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Item icon",
                modifier = Modifier
                    .width(75.dp)
                    .height(75.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = title, fontSize = 20.sp)
        }
    }

}

data class AppFeature(
    val title: String,
    val icon: Int
)