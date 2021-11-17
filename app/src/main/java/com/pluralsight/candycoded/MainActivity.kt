package com.pluralsight.candycoded

import androidx.appcompat.app.AppCompatActivity
import com.pluralsight.candycoded.Candy
import com.pluralsight.candycoded.DB.CandyDbHelper
import android.os.Bundle
import com.pluralsight.candycoded.R
import android.database.sqlite.SQLiteDatabase
import com.pluralsight.candycoded.DB.CandyCursorAdapter
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView
import android.content.Intent
import com.pluralsight.candycoded.DetailActivity
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import android.view.MenuInflater
import com.pluralsight.candycoded.InfoActivity
import android.content.ContentValues
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import com.pluralsight.candycoded.DB.CandyContract
import cz.msebera.android.httpclient.Header

class MainActivity : AppCompatActivity() {
    private lateinit var candies: Array<Candy>
    private val candyDbHelper = CandyDbHelper(this)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = candyDbHelper.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM candy", null)
        val adapter = CandyCursorAdapter(this, cursor)
        val listView = findViewById<View>(R.id.list_view_candy) as ListView
        listView.adapter = adapter
        listView.onItemClickListener = OnItemClickListener { adapterView, view, i, l ->
            val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
            detailIntent.putExtra("position", i)
            startActivity(detailIntent)
        }
        val client = AsyncHttpClient()
        client["https://vast-brushlands-23089.herokuapp.com/main/api", object :
            TextHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                response: String,
                throwable: Throwable
            ) {
                Log.e("AsyncHttpClient", "response = $response")
            }

            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: String) {
                Log.d("AsyncHttpClient", "response = $response")
                val gson = GsonBuilder().create()
                candies = gson.fromJson(response, Array<Candy>::class.java)
                addCandiesToDatabase(candies)
                val db = candyDbHelper.writableDatabase
                val cursor = db.rawQuery("SELECT * FROM candy", null)
                //adapter.changeCursor(cursor);
            }
        }]
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    // ***
    // TODO - Task 1 - Show Store Information Activity
    // ***
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val infoIntent = Intent(this, InfoActivity::class.java)
        startActivity(infoIntent)
        return super.onOptionsItemSelected(item)
    }

    private fun addCandiesToDatabase(candies: Array<Candy>) {
        val db = candyDbHelper.writableDatabase
        for (candy in candies) {
            val values = ContentValues()
            values.put(CandyContract.CandyEntry.COLUMN_NAME_NAME, candy.name)
            values.put(CandyContract.CandyEntry.COLUMN_NAME_PRICE, candy.price)
            values.put(CandyContract.CandyEntry.COLUMN_NAME_DESC, candy.description)
            values.put(CandyContract.CandyEntry.COLUMN_NAME_IMAGE, candy.image)
            db.insert(CandyContract.CandyEntry.TABLE_NAME, null, values)
        }
    }
}