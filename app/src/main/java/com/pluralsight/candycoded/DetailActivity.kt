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
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView

class DetailActivity : AppCompatActivity() {
    var mCandyImageUrl = ""
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val intent = this@DetailActivity.intent
        if (intent != null && intent.hasExtra("position")) {
            val position = intent.getIntExtra("position", 0)
            val dbHelper = CandyDbHelper(this)
            val db = dbHelper.writableDatabase
            val cursor = db.rawQuery("SELECT * FROM candy", null)
            cursor.moveToPosition(position)
            val candyName = cursor.getString(
                cursor.getColumnIndexOrThrow(
                    CandyEntry.COLUMN_NAME_NAME
                )
            )
            val candyPrice = cursor.getString(
                cursor.getColumnIndexOrThrow(
                    CandyEntry.COLUMN_NAME_PRICE
                )
            )
            mCandyImageUrl = cursor.getString(
                cursor.getColumnIndexOrThrow(
                    CandyEntry.COLUMN_NAME_IMAGE
                )
            )
            val candyDesc = cursor.getString(
                cursor.getColumnIndexOrThrow(
                    CandyEntry.COLUMN_NAME_DESC
                )
            )
            val textView = findViewById<View>(R.id.text_view_name) as TextView
            textView.text = candyName
            val textViewPrice = findViewById<View>(R.id.text_view_price) as TextView
            textViewPrice.text = candyPrice
            val textViewDesc = findViewById<View>(R.id.text_view_desc) as TextView
            textViewDesc.text = candyDesc
            val imageView = findViewById<View>(
                R.id.image_view_candy
            ) as ImageView
            Picasso.with(this).load(mCandyImageUrl).into(imageView)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.detail, menu)
        return true
    }

    // ***
    // TODO - Task 4 - Share the Current Candy with an Intent
    // ***
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        createShareIntent()
        return super.onOptionsItemSelected(item)
    }

    private fun createShareIntent() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            SHARE_DESCRIPTION + mCandyImageUrl + HASHTAG_CANDYCODED
        )
        startActivity(shareIntent)
    }

    companion object {
        const val SHARE_DESCRIPTION = "Look at this delicious candy from Candy Coded - "
        const val HASHTAG_CANDYCODED = " #candycoded"
    }
}