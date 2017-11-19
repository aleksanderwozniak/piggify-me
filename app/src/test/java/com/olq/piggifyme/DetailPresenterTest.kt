package com.olq.piggifyme

import com.olq.piggifyme.utils.MockitoUtils.any
import com.olq.piggifyme.data.Model
import com.olq.piggifyme.screens.details.income.IncomePresenter
import com.olq.piggifyme.screens.details.income.IncomeScreenContract
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify

/**
 * Created by olq on 19.11.17.
 */

// Those tests are written for Income screen,
// but are essentially the same for Expense screen

@RunWith(org.mockito.junit.MockitoJUnitRunner::class)
class DetailPresenterTest {

    @Mock
    lateinit var mockView: IncomeScreenContract.View

    @Mock
    lateinit var mockModel: Model

    @InjectMocks
    lateinit var realPresenter: IncomePresenter



    @Test
    fun start_getsDataFromDB() {
        realPresenter.start()

        verify(mockModel).pullData()
    }

    @Test
    fun start_updatesDetailList() {
        realPresenter.start()

        verify(mockView).showDetailList(any())
    }


    @Test
    fun onResetClick_resetsDB() {
        realPresenter.onResetClick()

        verify(mockModel).resetData()
    }

    @Test
    fun onResetClick_updatesDetailList() {
        realPresenter.onResetClick()

        verify(mockView).showDetailList(any())
    }
}