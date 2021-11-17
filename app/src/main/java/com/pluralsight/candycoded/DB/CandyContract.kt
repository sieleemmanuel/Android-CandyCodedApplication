package com.pluralsight.candycoded.DB

import android.provider.BaseColumns

object CandyContract {
    const val DB_NAME = "candycoded.db"
    const val DB_VERSION = 1
    const val SQL_CREATE_ENTRIES = "CREATE TABLE " + CandyEntry.TABLE_NAME + " (" +
            BaseColumns._ID + " INTEGER PRIMARY KEY," +
            CandyEntry.COLUMN_NAME_NAME + " TEXT," +
            CandyEntry.COLUMN_NAME_PRICE + " TEXT," +
            CandyEntry.COLUMN_NAME_DESC + " TEXT," +
            CandyEntry.COLUMN_NAME_IMAGE + " TEXT)"
    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + CandyEntry.TABLE_NAME

    object CandyEntry : BaseColumns {
        const val TABLE_NAME = "candy"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_PRICE = "price"
        const val COLUMN_NAME_DESC = "description"
        const val COLUMN_NAME_IMAGE = "image"
    }
}