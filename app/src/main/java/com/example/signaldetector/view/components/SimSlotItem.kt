package com.example.signaldetector.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blongho.country_data.World
import com.example.signaldetector.R
import com.example.signaldetector.view.theme.AccentColor
import com.example.signaldetector.view.utility.measureSignalPower

@Composable
fun SimSlotItem(
    slot: Int,
    providerName: String,
    power: Int,
    iconColor: Int,
    country: String,
    showStatusDialog: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, shape = RoundedCornerShape(15.dp), color = AccentColor)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.slot),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(text = (slot + 1).toString())
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
        ) {
            Image(
                painter = painterResource(id = if (providerName == "Irancell") R.drawable.ic_mtn_irancell else R.drawable.ic_mci),
                contentDescription = "Carrier logo",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.provider),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = providerName,
                        color = Color(iconColor)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.status),
                        fontWeight = FontWeight.Bold,
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { showStatusDialog.invoke() }
                    ) {
                        Text(
                            text = measureSignalPower(power).first,
                            color = measureSignalPower(power).second
                        )
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Information"
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.power),
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = power.toString() + stringResource(R.string.dbm))
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.country),
                fontWeight = FontWeight.Bold,
            )
            Image(
                painter = painterResource(id = World.getFlagOf(country)),
                contentDescription = stringResource(R.string.flag),
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SimSlotPreview() {
    SimSlotItem(slot = 0, providerName = "Irancell", power = 0, iconColor = -16746133, "ir", {})
}