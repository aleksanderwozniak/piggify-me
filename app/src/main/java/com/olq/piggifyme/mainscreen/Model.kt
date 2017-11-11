package com.olq.piggifyme.mainscreen

/**
 * Created by olq on 11.11.17.
 */
class Model {

    var incomeValue = 0
    var expenseValue = 0

    fun calculateBalance() = incomeValue - expenseValue

    fun resetData() {
        incomeValue = 0
        expenseValue = 0
    }
}