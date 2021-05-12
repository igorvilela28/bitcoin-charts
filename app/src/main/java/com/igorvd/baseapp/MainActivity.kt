package com.igorvd.baseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.igorvd.baseapp.core.data.DataTest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = FeatureAActivity.newIntent(this)
        Log.d("Igor", "testing data: ${DataTest.helloWorld}")
        startActivity(intent)

    }
}