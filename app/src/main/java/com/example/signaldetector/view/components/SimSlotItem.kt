package com.example.signaldetector.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signaldetector.R
import com.example.signaldetector.view.theme.Purple40

@Composable
fun SimSlotItem(
    iconId: Int,
    signalStrength: String,

    ) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(width = 1.dp, shape = RoundedCornerShape(15.dp), color = Purple40)
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = "Carrier logo",
        )

        Spacer(modifier = Modifier.width(5.dp))

        Column {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Signal strength:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "$signalStrength dBm",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Status:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Good",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Green
                )
            }
        }

    }


}