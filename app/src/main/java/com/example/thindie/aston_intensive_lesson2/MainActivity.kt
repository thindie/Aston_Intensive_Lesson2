package com.example.thindie.aston_intensive_lesson2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            require(false)
        } catch (e: IllegalArgumentException) {
            Log.e(
                ASTON_INTENSIVE_ACTIVITY_LOG_TAG, e
                    .message
                    .toString()
            )
        }
        val toastExercise: Button = findViewById(R.id.button_hello_toasts)
        val helloConstraint: Button = findViewById(R.id.button_hello_constraint)
        val scrollExercise: Button = findViewById(R.id.button_scroll_view)
        val bundleExercise: Button = findViewById(R.id.button_bundle_exercise)
        val intentExercise: Button = findViewById(R.id.button_intent_exercise)

        bundleExercise {
            start<SavedStateDemoActivity>()
        }

        scrollExercise {
            start<ScrollViewActivity>()
        }

        helloConstraint {
            start<HelloConstraintActivity>()
        }

        toastExercise {
            start<ToastActivity>()
        }

        intentExercise {
            start<IntentActivity>()
        }
    }

    private inline fun <reified T: AppCompatActivity>start() {
        startActivity(Intent(this, T::class.java))
    }
    companion object {
        private val ASTON_INTENSIVE_ACTIVITY_LOG_TAG = MainActivity::class.java.simpleName
    }
}