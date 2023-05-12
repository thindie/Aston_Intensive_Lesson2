package com.example.thindie.aston_intensive_lesson2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ClicksReceiverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello_clicks)
        val billBoard: TextView = findViewById(R.id.textview_hello_clicks)

        val showUs = requireNotNull(requireNotNull(intent.extras).getString(VALIDATE))
        val clicks = requireNotNull(
            requireNotNull(intent.extras).getInt(
                ATOMIC_OPERATIONS_TOTAL
            )
        )
        billBoard.text = showUs(clicks)
    }


}

private operator fun String.invoke(another: Int): String {
    return this.plus("\n").plus(another)
}