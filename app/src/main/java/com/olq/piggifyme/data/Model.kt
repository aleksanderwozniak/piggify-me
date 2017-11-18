package com.olq.piggifyme.data

import com.olq.piggifyme.data.database.LocalDataSource
import com.olq.piggifyme.data.database.Triplet

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
    lateinit var listOfIncomes: List<String>
    lateinit var listOfExpenses: List<String>

    fun calculateBalance() = incomeValue - expenseValue


    fun pullData() {
        val dataList = dataSource.getData()

        if (dataList != null) {
            incomeValue = extractCashValue(dataList[0])
            expenseValue = extractCashValue(dataList[1])

            listOfIncomes = extractSources(dataList[0])
            listOfExpenses = extractSources(dataList[1])

        } else {
            incomeValue = 0
            expenseValue = 0
        }
    }

    private fun extractCashValue(list: List<Triplet>): Int {
        var result = 0

        list.forEach {
            triplet ->
            result += triplet.value
        }

        return result
    }

    private fun extractSources(list: List<Triplet>): List<String> {
        val result = mutableListOf<String>()

        list.forEach {
            triplet ->
            result.add(triplet.source)
        }

        return result
    }

    fun pushData(triplet: Triplet){
        dataSource.saveData(triplet)
    }


    fun resetData() {
        dataSource.clearData()
        pullData()
    }
}