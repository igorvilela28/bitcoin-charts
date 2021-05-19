package com.igorvd.bitcoincharts.core.presentation.extensions

import android.content.Context
import androidx.core.content.ContextCompat

fun Context.getColorCompat(colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}