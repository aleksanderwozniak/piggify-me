package com.olq.piggifyme.screens.details.expense

import com.olq.piggifyme.BasePresenter
import com.olq.piggifyme.BaseView

/**
 * Created by olq on 18.11.17.
 */
interface ExpenseScreenContract {

    interface View : BaseView<Presenter> {
        fun showDetailList(list: List<Pair<String, Int>>)
    }

    interface Presenter : BasePresenter { }
}