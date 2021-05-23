package com.igorvd.bitcoincharts.features.charts.testutil

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.Exception

object AssetLoader {

    /**
     * Loads the given json into a String. The json file must exists in the /tests/resources directory
     */
    fun loadJsonFromResources(classLoader: ClassLoader, fileName: String): String {

        val url = classLoader.getResource(fileName)
        val file = File(url.path)
        return file.readContent()
    }

    /**
     * Reads the data for the given file
     */
    private fun File.readContent(): String {

        var content = ""
        try {
            val inputStream = FileInputStream(this)

            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString = bufferedReader.readLine()

            while (receiveString != null) {
                content += receiveString
                receiveString = bufferedReader.readLine()
            }
            inputStream.close()
        } catch (e: Exception) {
        }

        return content
    }
}