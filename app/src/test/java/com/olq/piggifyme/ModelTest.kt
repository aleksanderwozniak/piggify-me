package com.olq.piggifyme

import com.olq.piggifyme.data.Model
import com.olq.piggifyme.data.database.LocalDataSource
import com.olq.piggifyme.data.database.Triplet
import com.olq.piggifyme.screens.main.DialogType
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
        val type = DialogType.DIALOG_EXPENSE.toString()
        val source = "Food"
        val value = 150L
        val triplet = Triplet(type, source, value)

        realModel.pushData(triplet)

        verify(mockDataSource).saveData(triplet)
    }


    @Test
    fun pullData_acquiresValidData(){
        val type1 = DialogType.DIALOG_INCOME.toString()
        val source1 = "Job"
        val value1 = 2000L

        val type2 = DialogType.DIALOG_EXPENSE.toString()
        val source2 = "Rent"
        val value2 = 500L

        `when`(mockDataSource.getData()).then {
            listOf(listOf(Triplet(type1, source1, value1)),
                    listOf(Triplet(type2, source2, value2)))
        }

        realModel.pullData()

        verify(mockDataSource, times(1)).getData()
        assertEquals(2000, realModel.incomeValue)
        assertEquals(500, realModel.expenseValue)
    }

    @Test
    fun pullData_addsValuesProperly(){
        val type = DialogType.DIALOG_INCOME.toString()
        val source1 = "Job"
        val source2 = "Bonus"
        val source3 = "Found on ground"
        val value1 = 2000L
        val value2 = 500L
        val value3 = 5L

        `when`(mockDataSource.getData()).then {
            listOf(listOf(Triplet(type, source1, value1),
                            Triplet(type, source2, value2),
                            Triplet(type, source3, value3)),
                    listOf())
        }

        realModel.pullData()

        verify(mockDataSource, times(1)).getData()
        assertEquals(2505L, realModel.incomeValue)
    }


    @Test
    fun resetData_clearsCache(){
        `when`(mockDataSource.getData()).thenReturn(null)

        realModel.resetData()

        assertEquals(0, realModel.incomeValue)
        assertEquals(0, realModel.expenseValue)
    }

    @Test
    fun resetData_clearsDB(){
        `when`(mockDataSource.getData()).thenReturn(null)

        realModel.resetData()

        verify(mockDataSource).clearData()
    }


    @Test
    fun database_fullDbCycleTest() {
        val type1 = DialogType.DIALOG_INCOME.toString()
        val source1 = "Job"
        val value1 = 2000L
        val triplet = Triplet(type1, source1, value1)

        `when`(mockDataSource.getData()).then {
            listOf(listOf(triplet),
                    listOf())
        }

        realModel.incomeValue = value1
        assertEquals(2000L, realModel.incomeValue)
        realModel.pushData(triplet)


        realModel.incomeValue = 90L
        assertEquals(90, realModel.incomeValue)


        realModel.pullData()
        assertEquals(2000L, realModel.incomeValue)


        verify(mockDataSource, times(1)).saveData(triplet)
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