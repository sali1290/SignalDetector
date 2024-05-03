package com.example.signaldetector.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.signaldetector.R
import com.example.signaldetector.view.theme.Typography

@Composable
fun PowerInfoDialog(onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 300.dp)
                .background(color = Color.White)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Power range information",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "signal strength >= -79", style = Typography.bodyMedium)
                Text(
                    text = stringResource(id = R.string.perfect), style = Typography.bodyMedium,
                    color = Color(0xFF89FF00)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "-79 > signal strength >= -89", style = Typography.bodyMedium)
                Text(
                    text = stringResource(id = R.string.good), style = Typography.bodyMedium,
                    color = Color(0xFFA7CF79)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "-89 > signal strength >= -99", style = Typography.bodyMedium)
                Text(
                    text = stringResource(id = R.string.average), style = Typography.bodyMedium,
                    color = Color(0xFF57614C)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "-99 > signal strength >= -109", style = Typography.bodyMedium)
                Text(
                    text = stringResource(id = R.string.poor), style = Typography.bodyMedium,
                    color = Color(0xFFFF7272)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "-109 > signal strength >= -120", style = Typography.bodyMedium)
                Text(
                    text = stringResource(id = R.string.very_poor),
                    style = Typography.bodyMedium,
                    color = Color(0xFFFF0000)
                )
            }

        }


    }
}