package com.olq.piggifyme.screens.details.expense

import com.olq.piggifyme.BasePresenter
import com.olq.piggifyme.screens.details.DetailView

/**
 * Created by olq on 18.11.17.
 */
interface ExpenseScreenContract {

    interface View : DetailView<Presenter> { }

    interface Presenter : BasePresenter { }
}