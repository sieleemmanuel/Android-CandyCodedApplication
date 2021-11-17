package com.pluralsight.candycoded

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pluralsight.candycoded.R
import com.squareup.picasso.Picasso
import android.content.Intent
import com.pluralsight.candycoded.DB.CandyDbHelper
import android.database.sqlite.SQLiteDatabase
import com.pluralsight.candycoded.DB.CandyContract
import com.pluralsight.candycoded.DB.CandyContract.CandyEntry
import android.widget.TextView
import android.view.MenuInflater
import com.pluralsight.candycoded.DetailActivity
import android.preference.PreferenceActivity

class SettingsActivity : PreferenceActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //addPreferencesFromResource();
    }
}