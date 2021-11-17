package com.pluralsight.candycoded.DB

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import com.pluralsight.candycoded.DB.CandyContract
import android.database.sqlite.SQLiteDatabase
import com.pluralsight.candycoded.DB.CandyContract.CandyEntry
import android.provider.BaseColumns
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.CursorAdapter
import com.pluralsight.candycoded.R
import android.widget.TextView

class CandyCursorAdapter(context: Context?, c: Cursor?) : CursorAdapter(context, c, false) {
    override fun newView(context: Context, cursor: Cursor, parent: ViewGroup): View {
        return LayoutInflater.from(context).inflate(
            R.layout.list_item_candy, parent, false
        )
    }

    override fun bindView(
        view: View, context: Context,
        cursor: Cursor
    ) {
        val textView = view.findViewById<View>(
            R.id.text_view_candy
        ) as TextView
        val candyName = cursor.getString(
            cursor.getColumnIndexOrThrow("name")
        )
        textView.text = candyName
    }
}