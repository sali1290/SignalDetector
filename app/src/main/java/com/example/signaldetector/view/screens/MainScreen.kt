package com.example.signaldetector.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signaldetector.R

@Composable
fun MainScreen() {
//    val context = LocalContext.current
//
//    var simStrength1 by remember { mutableIntStateOf(0) }
//    var simStrength2 by remember { mutableIntStateOf(0) }
//
//    var simSubscription1 by remember { mutableStateOf<SubscriptionInfo?>(null) }
//    var simSubscription2 by remember { mutableStateOf<SubscriptionInfo?>(null) }
//
//    LaunchedEffect(key1 = Unit) {
//        try {
//            val simData = SignalPowerDetector.getNetworkStrength(context)
//            simData.forEachIndexed { index, item ->
//                if (index == 0) {
//                    simSubscription1 = item.first
//                    simStrength1 = item.second
//                }
//                if (index == 1) {
//                    simSubscription2 = item.first
//                    simStrength2 = item.second
//                }
//            }
//        } catch (e: Exception) {
//            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
//        }
//    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        items(2) {
            MainItems(title = "sim", icon = R.drawable.ic_mtn_irancell)
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