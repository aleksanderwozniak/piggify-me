package com.olq.piggifyme.screens.main

import com.olq.piggifyme.BasePresenter
import com.olq.piggifyme.BaseView

/**
 * Created by olq on 11.11.17.
 */
interface MainScreenContract {

    interface View : BaseView<Presenter> {
        fun showNewItemDialog(dialogType: DialogType)
        fun updateIncomeView(value: Int)
        fun updateExpenseView(value: Int)
        fun updateBalanceView(value: Int)
        fun showSourceNameError(errorMsg: String)
        fun showValueError(errorMsg: String)
    }

    interface Presenter : BasePresenter {
        fun onFABItemClick(dialogType: DialogType)
        fun onNewItemAdded(dialogType: DialogType, data: Pair<String, String>): Boolean
    }
}