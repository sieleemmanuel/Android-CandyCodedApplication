package com.pluralsight.candycoded.DB

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.pluralsight.candycoded.DB.CandyContract
import android.database.sqlite.SQLiteDatabase
import com.pluralsight.candycoded.DB.CandyContract.CandyEntry
import android.provider.BaseColumns
import android.view.ViewGroup
import android.view.LayoutInflater
import com.pluralsight.candycoded.R
import android.widget.TextView

class CandyDbHelper(context: Context?) : SQLiteOpenHelper(
    context,
    CandyContract.DB_NAME,
    null,
    CandyContract.DB_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CandyContract.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(CandyContract.SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}