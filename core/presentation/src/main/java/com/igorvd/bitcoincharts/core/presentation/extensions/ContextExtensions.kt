package com.igorvd.bitcoincharts.core.presentation.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

fun Context.getColorCompat(colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun Context.getDrawableCompat(drawableRes: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawableRes)
}