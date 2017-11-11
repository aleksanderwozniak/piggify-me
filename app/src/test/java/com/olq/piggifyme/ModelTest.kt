package com.olq.piggifyme

import com.olq.piggifyme.mainscreen.Model
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Created by olq on 11.11.17.
 */

class ModelTest {

    private val model = Model()

    @Test
    fun calculateBalance_onPlus() {
        model.incomeValue = 10
        model.expenseValue = 5

        assertEquals(5, model.calculateBalance())
    }

    @Test
    fun calculateBalance_onMinus() {
        model.incomeValue = 10
        model.expenseValue = 15

        assertEquals(-5, model.calculateBalance())
    }

    @Test
    fun calculateBalance_onZero() {
        model.incomeValue = 10
        model.expenseValue = 10

        assertEquals(0, model.calculateBalance())
    }

    @Test
    fun calculateBalance_inputIsZero() {
        model.incomeValue = 0
        model.expenseValue = 0

        assertEquals(0, model.calculateBalance())
    }
}