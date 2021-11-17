package com.pluralsight.candycoded

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pluralsight.candycoded.R
import com.squareup.picasso.Picasso
import android.content.Intent
import com.pluralsight.candycoded.DB.CandyDbHelper
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.pluralsight.candycoded.DB.CandyContract
import com.pluralsight.candycoded.DB.CandyContract.CandyEntry
import android.widget.TextView
import android.view.MenuInflater
import com.pluralsight.candycoded.DetailActivity
import android.preference.PreferenceActivity
import android.view.View
import android.widget.ImageView

class InfoActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val uri =
            Uri.parse("android.resource://com.codeschool.candycoded/" + R.drawable.store_front)
        val candyStoreImageView = findViewById<View>(R.id.image_view_candy_store) as ImageView
        Picasso.with(this).load(uri).into(candyStoreImageView)
    }

    // ***
    // TODO - Task 2 - Launch the Google Maps Activity
    // ***
    fun createMapIntent(view: View?) {
        val uri = Uri.parse("geo:0,0?q=618 E South St Orlando, FL 32801")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }
    }

    // ***
    // TODO - Task 3 - Launch the Phone Activity
    // ***
    fun createPhoneIntent(view: View?) {
        val uri = Uri.parse("tel:0123456789")
        val phoneIntent = Intent(Intent.ACTION_DIAL)
        phoneIntent.data = uri
        startActivity(phoneIntent)
    }
}