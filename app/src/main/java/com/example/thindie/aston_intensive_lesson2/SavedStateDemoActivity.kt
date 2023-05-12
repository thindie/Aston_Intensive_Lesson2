package com.example.thindie.aston_intensive_lesson2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.atomic.AtomicInteger

@Suppress("SwallowedException")
class SavedStateDemoActivity : AppCompatActivity() {
    private val countsHolder = AtomicInteger(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_saved_state_demo)
        val bundleExerciseButton: Button = findViewById(R.id.button_count_bundle)
        val bundleTextView: TextView = findViewById(R.id.textview_bundle)

        countsHolder.validateState(savedInstanceState)
        bundleTextView.text = countsHolder.get().toString()

        bundleExerciseButton.setOnClickListener {
            bundleTextView.onServeBundleTextView(countsHolder)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ATOMIC_OPERATIONS_TOTAL, countsHolder.get())
    }
}

private fun AtomicInteger.validateState(savedInstanceState: Bundle?) {
    set(
        try {
            checkNotNull(savedInstanceState)
                .getInt(ATOMIC_OPERATIONS_TOTAL)
        } catch (emptyBundle: IllegalStateException) {
            0
        }
    )
}

private fun TextView.onServeBundleTextView(holder: AtomicInteger) {
    text = holder.incrementAndGet().toString()
}