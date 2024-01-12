package com.example.signaldetector.view.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.signaldetector.view.utility.Resource
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun <T> CollectViewModelResponse(
    viewModelProperty: SharedFlow<Resource<T>>,
    lifeCycleOwner: LifecycleOwner,
    onEvent: (Resource<T>) -> Unit
) {
    LaunchedEffect(key1 = lifeCycleOwner.lifecycle) {
        lifeCycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModelProperty.collect {
                onEvent(it)
            }
        }
    }
}