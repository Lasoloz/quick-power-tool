package com.lasoloz.tools.qpt.listener

import com.tulskiy.keymaster.common.Provider
import org.junit.jupiter.api.Test
import javax.swing.KeyStroke

class IntegrationTest {
    @Test
    fun toolTest() {
        val provider = Provider.getCurrentProvider(false)

        provider.register(KeyStroke.getKeyStroke("control shift alt P")) {
            println("Intercepted!")
        }

        Thread.sleep(10000)

        provider.reset()
        provider.stop()
    }
}
