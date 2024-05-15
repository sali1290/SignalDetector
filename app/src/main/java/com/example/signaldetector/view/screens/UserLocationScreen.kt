package com.example.signaldetector.view.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.signaldetector.R
import com.example.signaldetector.model.utils.getCurrentCellInfo
import com.example.signaldetector.view.components.CustomButton
import com.example.signaldetector.view.theme.AccentColor
import com.example.signaldetector.view.theme.Typography
import com.example.signaldetector.viewmodel.CellLocationViewModel

@Composable
fun UserLocationScreen() {

    var expanded by remember { mutableStateOf(false) }
    var cellService by remember { mutableStateOf("Cell service") }
    val cellLocationViewModel: CellLocationViewModel = hiltViewModel()
    val cellLocationState by cellLocationViewModel.cellLocation.collectAsState()

    var address by remember { mutableStateOf("") }
    var latitude by remember { mutableDoubleStateOf(0.0) }
    var longitude by remember { mutableDoubleStateOf(0.0) }

    val context = LocalContext.current

    LaunchedEffect(key1 = cellLocationState.result) {
        cellLocationState.result?.let {
            latitude = it.latitude ?: 0.0
            longitude = it.longitude ?: 0.0
            address = it.address ?: ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.screen_padding)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Current Location:", style = Typography.titleLarge)

        Spacer(modifier = Modifier.height(10.dp))

        AnimatedVisibility(visible = address.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .border(width = 1.dp, color = AccentColor, shape = RoundedCornerShape(15.dp))
            ) {
                Text(
                    text = address,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "latitude: $latitude ,longitude: $longitude",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable {
                    expanded = !expanded
                },
            elevation = CardDefaults.cardElevation(10.dp),
            shape = RoundedCornerShape(corner = CornerSize(10.dp))
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = cellService)
                    Spacer(modifier = Modifier.width(10.dp))
                    if (cellLocationState.loading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(25.dp)
                                .height(25.dp)
                        )
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = !expanded }) {
                    listOf("lte", "gsm", "cdma").forEach {
                        DropdownMenuItem(text = { Text(text = it) }, onClick = {
                            cellService = it
                            expanded = false
                            val allCellInfo = getCurrentCellInfo(context)
                            val cellInfo = when (it) {
                                "lte" -> allCellInfo[0]
                                "gsm" -> allCellInfo[1]
                                "cdma" -> allCellInfo[2]
                                else -> allCellInfo[0]
                            }
                            cellLocationViewModel.getCellLocation(cellInfo)
                        })
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        CustomButton(
            text = "Open map",
            backgroundColor = AccentColor,
            onClick = { openMapApp(context, latitude, longitude) })
    }

    LaunchedEffect(key1 = cellLocationState.error) {
        if (!cellLocationState.error.isNullOrEmpty())
            Toast.makeText(context, cellLocationState.error ?: "", Toast.LENGTH_LONG).show()
    }
}

private fun openMapApp(context: Context, latitude: Double, longitude: Double) {
    if (latitude != 0.0 && longitude != 0.0) {
        val url =
            "https://www.google.com/maps/dir/?api=1&destination=$latitude,$longitude"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}