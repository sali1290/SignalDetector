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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signaldetector.R
import com.example.signaldetector.view.theme.AccentColor

@Composable
fun SimSlotItem(providerName: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(width = 1.dp, shape = RoundedCornerShape(15.dp), color = AccentColor)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = if (providerName == "Irancel") R.drawable.ic_mtn_irancell else R.drawable.ic_ip),
            contentDescription = "Carrier logo",
        )

        Spacer(modifier = Modifier.width(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Provider:",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = providerName,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Green
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SimSlotPreview() {
    SimSlotItem(providerName = "Irancel")
}