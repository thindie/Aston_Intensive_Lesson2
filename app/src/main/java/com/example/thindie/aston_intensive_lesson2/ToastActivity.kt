package com.example.thindie.aston_intensive_lesson2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class ToastActivity : AppCompatActivity() {
    private val viewModel: ToastViewModel by lazy {
        ViewModelProvider(this)[ToastViewModel::class.java]
    }
    private val seeMeClicking by lazy {
        { viewModel.onClickCount() }
    }

    private val getClicks by lazy {
        { viewModel.onRequestAtomicOperations() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello_toast)
        val buttonCount: Button = findViewById(R.id.button_count)
        val buttonShowClicksAnotherActivity: Button = findViewById(R.id.button_toast)
        val countText: TextView = findViewById(R.id.show_count)



        viewModel
            .clickedCountState
            .observe(this) { clicksCount ->
                countText.text = clicksCount
            }

        buttonCount.setOnClickListener {
            seeMeClicking()
        }

        buttonShowClicksAnotherActivity.setOnClickListener {
            viewModel.onDoinUsualBossJob(this::startHelloActivity)
        }
    }

    private fun showToast() {
        Toast(this)
            .apply {
                setText(R.string.toast_message)
                duration = Toast.LENGTH_SHORT
                show()
            }
    }

    private fun startHelloActivity() {
        Intent(this@ToastActivity, ClicksReceiverActivity::class.java)
            .apply {
                putExtra(ATOMIC_OPERATIONS_TOTAL, getClicks())
                putExtra(VALIDATE, VALIDATE)
                startActivity(this)
            }
    }

}

