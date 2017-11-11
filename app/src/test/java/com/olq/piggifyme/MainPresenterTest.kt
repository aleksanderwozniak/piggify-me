package com.olq.piggifyme

import com.olq.piggifyme.mainscreen.DialogType
import com.olq.piggifyme.mainscreen.MainPresenter
import com.olq.piggifyme.mainscreen.MainScreenContract
import com.olq.piggifyme.mainscreen.Model
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy

/**
 * Created by olq on 11.11.17.
 */

@RunWith(org.mockito.junit.MockitoJUnitRunner::class)
class MainPresenterTest {

    private val mockView = mock(MainScreenContract.View::class.java)
    private val spyModel = spy(Model::class.java)
    private val realPresenter = MainPresenter(mockView, spyModel)

    @Test
    fun onResetClick_resetsData() {
        spyModel.incomeValue = 20
        spyModel.expenseValue = 10

        realPresenter.onResetClick()

        assertEquals(0, spyModel.incomeValue)
        assertEquals(0, spyModel.expenseValue)
    }


    @Test
    fun onNewItemAdded_updatesModel() {
        val amountIncome = "120"
        val amountExpense = "140"

        realPresenter.onNewItemAdded(DialogType.DIALOG_INCOME, amountIncome)
        realPresenter.onNewItemAdded(DialogType.DIALOG_EXPENSE, amountExpense)

        assertEquals(120, spyModel.incomeValue)
        assertEquals(140, spyModel.expenseValue)
        assertEquals(-20, spyModel.calculateBalance())
    }

    @Test
    fun onNewItemAdded_validatesInput() {
        val amount = ""   // empty input value
        val expected = 0  // we expect `""` to be changed to `0`

        realPresenter.onNewItemAdded(DialogType.DIALOG_INCOME, amount)

        assertEquals(expected, spyModel.incomeValue)
    }

}