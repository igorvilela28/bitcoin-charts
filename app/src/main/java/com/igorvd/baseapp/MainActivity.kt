package com.igorvd.baseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.igorvd.baseapp.features.featureA.FeatureAActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = FeatureAActivity.newIntent(this)
        startActivity(intent)
        finish()
    }
}