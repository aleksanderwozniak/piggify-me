package com.olq.piggifyme

import com.olq.piggifyme.data.Model
import com.olq.piggifyme.data.database.LocalDataSource
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*

/**
 * Created by olq on 11.11.17.
 */

@RunWith(org.mockito.junit.MockitoJUnitRunner::class)
class ModelTest {

    @Mock
    lateinit var mockDataSource: LocalDataSource

    @InjectMocks
    lateinit var realModel: Model



    @Test
    fun pushData_savesDataInDB() {
        realModel.incomeValue = 25
        realModel.expenseValue = 15
        realModel.pushData()

        verify(mockDataSource).saveData(25, 15)
    }

    @Test
    fun pullData_acquiresValidData(){
        `when`(mockDataSource.getData()).then { listOf(Pair("income", 10), Pair("expense", 5)) }

        realModel.pullData()

        verify(mockDataSource, times(1)).getData()
        assertEquals(10, realModel.incomeValue)
        assertEquals(5, realModel.expenseValue)
    }

    @Test
    fun resetData_clearsCache(){
        realModel.resetData()

        assertEquals(0, realModel.incomeValue)
        assertEquals(0, realModel.expenseValue)
    }

    @Test
    fun resetData_clearsDB(){
        realModel.resetData()

        verify(mockDataSource).saveData(0, 0)
    }


    @Test
    fun database_fullDbCycleTest() {
        `when`(mockDataSource.getData()).then {
            listOf(Pair("income", 25), Pair("expense", 15)) }

        realModel.incomeValue = 25
        realModel.expenseValue = 15
        realModel.pushData()

        assertEquals(25, realModel.incomeValue)
        assertEquals(15, realModel.expenseValue)

        realModel.incomeValue = 90
        realModel.expenseValue = 200

        assertEquals(90, realModel.incomeValue)
        assertEquals(200, realModel.expenseValue)

        realModel.pullData()

        assertEquals(25, realModel.incomeValue)
        assertEquals(15, realModel.expenseValue)

        verify(mockDataSource, times(1)).saveData(25, 15)
        verify(mockDataSource, times(1)).getData()
    }


    @Test
    fun calculateBalance_onPlus() {
        realModel.incomeValue = 10
        realModel.expenseValue = 5

        assertEquals(5, realModel.calculateBalance())
    }

    @Test
    fun calculateBalance_onMinus() {
        realModel.incomeValue = 10
        realModel.expenseValue = 15

        assertEquals(-5, realModel.calculateBalance())
    }

    @Test
    fun calculateBalance_onZero() {
        realModel.incomeValue = 10
        realModel.expenseValue = 10

        assertEquals(0, realModel.calculateBalance())
    }

    @Test
    fun calculateBalance_inputIsZero() {
        realModel.incomeValue = 0
        realModel.expenseValue = 0

        assertEquals(0, realModel.calculateBalance())
    }
}