package com.olq.piggifyme.screens.details.income

import com.olq.piggifyme.screens.BasePresenter
import com.olq.piggifyme.screens.details.DetailView

/**
 * Created by olq on 17.11.17.
 */
interface IncomeScreenContract {

    interface View : DetailView<Presenter> { }

    interface Presenter : BasePresenter { }
}