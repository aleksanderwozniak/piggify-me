package com.olq.piggifyme.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by olq on 11.11.17.
 */
class PiggifyDbHelper(ctx: Context) :
        ManagedSQLiteOpenHelper(ctx, DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME = "income-expense.db"
        val DB_VERSION = 1

        private var instance: PiggifyDbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): PiggifyDbHelper {
            if (instance == null) {
                instance = PiggifyDbHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(ModelTable.NAME, true,
                ModelTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ModelTable.TYPE to TEXT,
                ModelTable.SOURCE to TEXT,
                ModelTable.VALUE to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(ModelTable.NAME, true)
        onCreate(db)
    }
}