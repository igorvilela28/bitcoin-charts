package com.igorvd.bitcoincharts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.igorvd.bitcoincharts.features.featureA.FeatureAActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = FeatureAActivity.newIntent(this)
        startActivity(intent)
        finish()
    }
}