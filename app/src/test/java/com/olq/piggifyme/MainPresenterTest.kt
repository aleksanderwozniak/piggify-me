package com.olq.piggifyme

import com.olq.piggifyme.screens.main.DialogType
import com.olq.piggifyme.screens.main.MainPresenter
import com.olq.piggifyme.screens.main.MainScreenContract
import com.olq.piggifyme.data.Model
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*

/**
 * Created by olq on 11.11.17.
 */

@RunWith(org.mockito.junit.MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock
    lateinit var mockView: MainScreenContract.View

    @Mock
    lateinit var mockModel: Model

    @InjectMocks
    lateinit var realPresenter: MainPresenter



    @Test
    fun init_injectsPresenterIntoView() {
        verify(mockView).presenter = realPresenter
    }

    @Test
    fun start_getsDataFromDB() {
        realPresenter.start()

        verify(mockModel).pullData()
    }

    @Test
    fun start_updatesEachTextView() {
        realPresenter.start()

        verify(mockView).updateIncomeView(0)
        verify(mockView).updateExpenseView(0)
        verify(mockView).updateBalanceView(0)
    }

    @Test
    fun onFABItemClick_displaysItemDialog() {
        val dialogType = DialogType.DIALOG_INCOME

        realPresenter.onFABItemClick(dialogType)

        verify(mockView).showNewItemDialog(dialogType)
    }

    @Test
    fun onNewItemAdded_increasesModelsValue() {
        val dialogType = DialogType.DIALOG_EXPENSE
        val amount = "120"

        realPresenter.onNewItemAdded(dialogType, amount)

        verify(mockModel).expenseValue = 120
    }

    @Test
    fun onNewItemAdded_pushesDataToDB() {
        val dialogType = DialogType.DIALOG_INCOME
        val amount = "10"

        realPresenter.onNewItemAdded(dialogType, amount)

        verify(mockModel).pushData()
    }

    @Test
    fun onNewItemAdded_updatesAppropriateViews() {
        val dialogType1 = DialogType.DIALOG_INCOME
        val dialogType2 = DialogType.DIALOG_EXPENSE
        val amount1 = "7"
        val amount2 = "10"

        realPresenter.onNewItemAdded(dialogType1, amount1)
        realPresenter.onNewItemAdded(dialogType2, amount2)

        verify(mockView, times(1)).updateIncomeView(mockModel.incomeValue)
        verify(mockView, times(1)).updateExpenseView(mockModel.expenseValue)
        verify(mockView, times(2)).updateBalanceView(mockModel.calculateBalance())
    }

    @Test
    fun onNewItemAdded_noUpdateOnNegativeOrZeroValue() {
        val dialogType = DialogType.DIALOG_INCOME
        val amount1 = "-1"
        val amount2 = "0"

        realPresenter.onNewItemAdded(dialogType, amount1)
        realPresenter.onNewItemAdded(dialogType, amount2)

        verify(mockView).presenter = realPresenter
        verifyNoMoreInteractions(mockView)
    }

    @Test
    fun onResetClick_resetsDataInModel() {
        realPresenter.onResetClick()

        verify(mockModel).resetData()
    }

    @Test
    fun onResetClick_updatesViews() {
        realPresenter.onResetClick()

        verify(mockView).updateIncomeView(0)
        verify(mockView).updateExpenseView(0)
        verify(mockView).updateBalanceView(0)
    }
}