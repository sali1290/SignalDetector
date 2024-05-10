package com.example.signaldetector.viewmodel.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.signaldetector.model.utils.LogKeys
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

data class ResultState<T>(
    val loading: Boolean = false,
    val error: String? = null,
    val result: T
)

fun <T> ViewModel.updateInBackground(
    flow: MutableStateFlow<ResultState<T>>,
    scope: CoroutineScope = viewModelScope,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    useCase: suspend () -> T
) {
    scope.launch(dispatcher) {
        flow.update { it.copy(loading = true, error = null) }
        try {
            val result = useCase.invoke()
            flow.update { ResultState(result = result) }
        } catch (exception: IOException) {
            val message = exception.message!!
            Log.d(LogKeys.VIEWMODEL, "$useCase : $message")
            flow.update { it.copy(loading = false, error = message) }
        }
    }
}