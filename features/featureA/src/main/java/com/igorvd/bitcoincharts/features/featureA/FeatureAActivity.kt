package com.igorvd.bitcoincharts.features.featureA

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FeatureAActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)
    }

    companion object {
        fun newIntent (context: Context): Intent {
            return Intent(context, FeatureAActivity::class.java)
        }
    }
}