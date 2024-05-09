package com.example.signaldetector.view.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.signaldetector.R
import com.example.signaldetector.model.utility.LogKeys
import com.example.signaldetector.model.utility.getCurrentCellInfo
import com.example.signaldetector.view.theme.Typography
import com.example.signaldetector.viewmodel.CellLocationViewModel

@Composable
fun UserLocationScreen() {

    var expanded by remember { mutableStateOf(false) }
    var cellService by remember { mutableStateOf("") }
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

        Text(text = address)

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "$latitude , $longitude")

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { expanded = true },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 40.dp)
        ) {
            Text(text = cellService)
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = !expanded }) {
                listOf("lte", "gsm", "cdma").forEach {
                    DropdownMenuItem(text = { Text(text = it) }, onClick = {
                        cellService = it
                        expanded = false
                        val allCellInfo = getCurrentCellInfo(context)
                        Log.d(LogKeys.REQUEST_TEST, "Cell info: $allCellInfo")
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

    LaunchedEffect(key1 = cellLocationState.error) {
        if (!cellLocationState.error.isNullOrEmpty())
            Toast.makeText(context, cellLocationState.error ?: "", Toast.LENGTH_LONG).show()
    }

}