package com.olq.piggifyme.screens.details.income

import com.olq.piggifyme.data.Model

/**
 * Created by olq on 17.11.17.
 */
class IncomePresenter(private val view: IncomeScreenContract.View,
                      val model: Model)
    : IncomeScreenContract.Presenter {


    override fun start() {
        model.pullData()
        view.showDetailList(model.listOfIncomes)
    }

    override fun onResetClick() {
        model.resetData()
        start()
    }
}