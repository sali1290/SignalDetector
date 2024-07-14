package com.example.signaldetector.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.signaldetector.R
import com.example.signaldetector.view.theme.Typography

@Composable
fun MainScreen(navController: NavController) {

    val mainScreenItems =
        listOf(
            AppFeature(title = stringResource(id = R.string.sim_cards), icon = R.drawable.ic_sim),
            AppFeature(title = stringResource(R.string.location), icon = R.drawable.ic_location),
            AppFeature(title = stringResource(R.string.signal_presence), icon = R.drawable.ic_cell),
            AppFeature(title = stringResource(R.string.wifi), icon = R.drawable.ic_wifi)
        )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)
    ) {

        Text(
            text = stringResource(id = R.string.app_name),
            style = Typography.titleLarge
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ) {
            itemsIndexed(mainScreenItems) { index, item ->
                MainItems(title = item.title, icon = item.icon) {
                    when (index) {
                        0 -> navController.navigate(Screen.SIMInfoScreen.route)
                        1 -> navController.navigate(Screen.UserLocationScreen.route)
                        2 -> navController.navigate(Screen.SignalPresenceScreen.route)
                        3 -> navController.navigate(Screen.WifiListScreen.route)
                    }
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(rememberNavController())
}

@Composable
fun MainItems(title: String, icon: Int, onItemClickListener: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onItemClickListener.invoke() },
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
            Text(
                text = title,
                style = Typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}

data class AppFeature(
    val title: String,
    val icon: Int
)