package com.igorvd.bitcoincharts

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import java.io.*
import java.lang.Exception

object AssetsLoader {

    /**
     * Load a json stored in the assets folder and returns the content string
     */
    fun loadAsset(assetName: String): String {
        try {
            val assets = InstrumentationRegistry
                .getInstrumentation()
                .context
                .assets

            val inputStream = assets.open(assetName).bufferedReader().use { it.readText() }
            //val string = inputStream.readContent()
            return inputStream
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Igor", e.message.toString())
            return ""
        }
    }

    /**
     * Reads the data for the given input stream
     */
    private fun InputStream.readContent(): String {

        var content = ""
        try {
            val inputStreamReader = InputStreamReader(this)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString = bufferedReader.readLine()

            while (receiveString != null) {
                content += receiveString
                receiveString = bufferedReader.readLine()
            }
            this.close()
        } catch (e: Exception) {
        }

        return content
    }
}