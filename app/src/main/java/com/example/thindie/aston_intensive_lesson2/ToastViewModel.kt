package com.example.thindie.aston_intensive_lesson2

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.atomic.AtomicInteger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ToastViewModel : ViewModel() {
    private val gigaChadCounter = AtomicInteger(0)

    private val _buttonColorOperator: MutableStateFlow<ColorStateHolder> =
        MutableStateFlow(ColorStateHolder())
    internal val buttonColorOperator: StateFlow<ColorStateHolder> =
        _buttonColorOperator.asStateFlow()

    private val _clickedCountState = MutableLiveData<String>()
    val clickedCountState: LiveData<String>
        get() = _clickedCountState


    fun onDoinUsualBossJob(bossJob: () -> Unit) {
        _clickedCountState
            .postValue(gigaChadCounter.get().toString())
        bossJob()
    }

    internal fun onClickCount(
        holder: ColorStateHolder = ColorStateHolder(
            zeroButtonColor = Color.MAGENTA,
            clickButtonColor = Color.GREEN
        )
    ) {
        _buttonColorOperator.value = holder
        _clickedCountState
            .postValue(gigaChadCounter.incrementAndGet().toString())
    }

    fun onRequestAtomicOperations() = gigaChadCounter.get()

    fun onClickZero() {
        gigaChadCounter.set(-1)
        onClickCount(ColorStateHolder())
    }

    internal data class ColorStateHolder(
        val zeroButtonColor: Int = Color.GRAY,
        val clickButtonColor: Int = Color.BLUE
    )

}