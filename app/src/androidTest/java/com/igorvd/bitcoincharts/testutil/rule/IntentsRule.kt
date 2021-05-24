package com.igorvd.bitcoincharts.testutil.rule

import androidx.test.espresso.intent.Intents
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class IntentsRule : TestWatcher() {

    override fun starting(description: Description?) {
        Intents.init()
    }

    override fun finished(description: Description?) {
        Intents.release()
    }
}