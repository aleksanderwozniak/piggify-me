package com.olq.piggifyme.screens.details.expense

import com.olq.piggifyme.data.Model

/**
 * Created by olq on 18.11.17.
 */
class ExpensePresenter(private val view: ExpenseScreenContract.View,
                       val model: Model)
    : ExpenseScreenContract.Presenter {


    override fun start() {
        model.pullData()
        view.showDetailList(model.listOfExpenseDetails)
    }

    override fun onResetClick() {
        model.resetData()
        start()
    }
}