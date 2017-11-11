package com.olq.piggifyme.mainscreen

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
    }

    interface Presenter : BasePresenter {
        fun onFABItemClick(dialogType: DialogType)
        fun onNewItemAdded(dialogType: DialogType, amount: String)
    }
}