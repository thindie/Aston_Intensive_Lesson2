package com.example.thindie.aston_intensive_lesson2

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HelloConstraintActivity : AppCompatActivity() {
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private val viewModel: ToastViewModel by lazy {
        ViewModelProvider(this)[ToastViewModel::class.java]
    }

    private val seeMeClicking by lazy {
        { viewModel.onClickCount() }
    }

    private val zeroOrder by lazy {
        { viewModel.onClickZero() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello_constraint)

        val buttonCount: Button = findViewById(R.id.button_count)
        val buttonToast: Button = findViewById(R.id.button_toast)
        val buttonZero: Button = findViewById(R.id.button_zero)
        val countText: TextView = findViewById(R.id.show_count)

        viewModel
            .buttonColorOperator
            .onEach { colorHolder ->
                buttonZero.setBackgroundColor(colorHolder.zeroButtonColor)
                buttonCount.setBackgroundColor(colorHolder.clickButtonColor)
            }.launchIn(scope)

        viewModel
            .clickedCountState
            .observe(this) { clicksCount ->
                countText.text = clicksCount
            }

        buttonCount.setOnClickListener {
            seeMeClicking()
        }

        buttonZero.setOnClickListener {
            zeroOrder()
        }

        buttonToast.setOnClickListener {
            viewModel.onDoinUsualBossJob(this::showToast)
        }
        fun hasCameraPermission() = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }


    private fun showToast() {
        Toast.makeText(
            this,
            R.string.toast_message,
            Toast.LENGTH_SHORT
        ).show()
    }
}