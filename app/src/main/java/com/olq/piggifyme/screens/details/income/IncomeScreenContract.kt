package com.olq.piggifyme.screens.details.income

import com.olq.piggifyme.BasePresenter
import com.olq.piggifyme.BaseView

/**
 * Created by olq on 17.11.17.
 */
interface IncomeScreenContract {

    interface View : BaseView<Presenter> {
        fun showDetailList(list: List<Pair<String, Int>>)
    }

    interface Presenter : BasePresenter { }
}