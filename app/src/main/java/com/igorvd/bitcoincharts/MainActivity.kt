package com.igorvd.bitcoincharts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.igorvd.bitcoincharts.features.charts.ChartsHomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = ChartsHomeActivity.newIntent(this)
        startActivity(intent)
        finish()
    }
}