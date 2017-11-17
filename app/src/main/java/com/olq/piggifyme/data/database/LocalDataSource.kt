package com.olq.piggifyme.data.database

import com.olq.piggifyme.utils.parseList
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select


/**
 * Created by olq on 11.11.17.
 */
class LocalDataSource(private val piggifyDbHelper: PiggifyDbHelper) {

    private val parser = rowParser { id: String, value: Int ->
        Pair(id, value)
    }


    fun getData() = piggifyDbHelper.use {
//        val income = select(ModelTable.NAME)
//                .whereSimple("${ModelTable.TYPE} = ?", "income")
//                .parseOpt(parser)
//
//        val expense = select(ModelTable.NAME)
//                .whereSimple("${ModelTable.TYPE} = ?", "expense")
//                .parseOpt(parser)
//
//        if (income != null && expense != null) {
//            listOf(income, expense)
//        } else {
//            listOf(Pair("empty", 0), Pair("empty", 0))
//        }

        val income = select(ModelTable.NAME)
                .whereSimple("${ModelTable.TYPE} = ?", "income")
                .parseList { Triplet(HashMap(it)) }

        val expense = select(ModelTable.NAME)
                .whereSimple("${ModelTable.TYPE} = ?", "expense")
                .parseList { Triplet(HashMap(it)) }

        if (income != null && expense != null) {
            listOf(income, expense)
        } else {
            listOf(Pair("empty", 0), Pair("empty", 0))
        }
    }


    fun saveData(dataPack: Triplet) = piggifyDbHelper.use {
//    fun saveData(incomeValue: Int, expenseValue: Int) = piggifyDbHelper.use {
//        execSQL("delete from ${ModelTable.NAME}")

//        insert(ModelTable.NAME,
//                ModelTable.ID to "income",
//                ModelTable.VALUE to incomeValue)
//
//        insert(ModelTable.NAME,
//                ModelTable.ID to "expense",
//                ModelTable.VALUE to expenseValue)

        insert(ModelTable.NAME,
                ModelTable.TYPE to dataPack.type,
                ModelTable.SOURCE to dataPack.source,
                ModelTable.VALUE to dataPack.value)
    }
}