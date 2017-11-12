package com.olq.piggifyme.data

import com.olq.piggifyme.data.database.LocalDataSource

/**
 * Created by olq on 11.11.17.
 */
class Model (private val dataSource: LocalDataSource){

    companion object {
        private var instance: Model? = null

        @Synchronized
        fun getInstance(dataSource: LocalDataSource): Model {
            if (instance == null) {
                instance = Model(dataSource)
            }
            return instance!!
        }
    }

    var incomeValue = 0
    var expenseValue = 0

    fun calculateBalance() = incomeValue - expenseValue

    fun pullData() {
        val dataList = dataSource.getData()
        incomeValue = dataList[0].second
        expenseValue = dataList[1].second
    }

    fun pushData() {
        dataSource.saveData(incomeValue, expenseValue)
    }

    fun resetData() {
        incomeValue = 0
        expenseValue = 0
        dataSource.saveData(0, 0)
    }
}