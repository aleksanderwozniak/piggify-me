package com.olq.piggifyme.data.database

import com.olq.piggifyme.mainscreen.DialogType
import com.olq.piggifyme.utils.parseList
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


/**
 * Created by olq on 11.11.17.
 */
class LocalDataSource(private val piggifyDbHelper: PiggifyDbHelper) {

    fun getData() = piggifyDbHelper.use {
        val income = select(ModelTable.NAME)
                .whereSimple("${ModelTable.TYPE} = ?", "${DialogType.DIALOG_INCOME}")
                .parseList { Triplet(HashMap(it)) }

        val expense = select(ModelTable.NAME)
                .whereSimple("${ModelTable.TYPE} = ?", "${DialogType.DIALOG_EXPENSE}")
                .parseList { Triplet(HashMap(it)) }

        if (income != null && expense != null) {
            listOf(income, expense)
        } else {
            null
        }
    }


    fun saveData(dataPack: Triplet) = piggifyDbHelper.use {
        insert(ModelTable.NAME,
                ModelTable.TYPE to dataPack.type,
                ModelTable.SOURCE to dataPack.source,
                ModelTable.VALUE to dataPack.value)
    }


    fun clearData() {
        piggifyDbHelper.use {
            execSQL("delete from ${ModelTable.NAME}")
        }
    }
}