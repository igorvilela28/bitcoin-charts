package com.igorvd.bitcoincharts.features.charts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ChartsHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charts_home)
    }

    companion object {
        fun newIntent (context: Context): Intent {
            return Intent(context, ChartsHomeActivity::class.java)
        }
    }
}