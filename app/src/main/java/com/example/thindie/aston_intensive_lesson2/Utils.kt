package com.example.thindie.aston_intensive_lesson2

import android.widget.Button

internal const val ATOMIC_OPERATIONS_TOTAL = "count"
internal const val VALIDATE = "Hello!"
internal const val GEO_ENDPOINT = "geo:0,0?q=%s"

operator fun Button?.invoke(func: () -> Unit) {
    checkNotNull(this){ "Where's my button?" }
    setOnClickListener {
        func.invoke()
    }
}