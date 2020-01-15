package com.lasoloz.tools.qpt.actions

import com.lasoloz.tools.qpt.actions.impl.ALWAYS_SUCCESS_MESSAGE
import com.lasoloz.tools.qpt.actions.impl.ALWAYS_UNSUCCESSFUL_MESSAGE
import com.lasoloz.tools.qpt.actions.impl.SuccessfulTestActionImplementation
import com.lasoloz.tools.qpt.actions.impl.UnsuccessfulTestActionImplementation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ActionTest {
    @Test
    fun `given successful Action when Action is ran then Action should return success status`() {
        val result = SuccessfulTestActionImplementation().doAction()

        assertEquals(ActionStatus.Success::class.java, result.javaClass)
        assertEquals(ALWAYS_SUCCESS_MESSAGE, result.message)
    }

    @Test
    fun `given failing Action when Action is ran then Action should return failure status`() {
        val result = UnsuccessfulTestActionImplementation().doAction()

        assertEquals(ActionStatus.Failure::class.java, result.javaClass)
        assertEquals(ALWAYS_UNSUCCESSFUL_MESSAGE, result.message)
    }
}
