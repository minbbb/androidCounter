package com.maksedison.counter

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var pref: SharedPreferences
    private val APP_PREFERENCES = "save_count"
    private val APP_PREFERENCES_COUNTER = "counter"
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        pref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        buttonCounter.setOnClickListener {
            counter++
            val editor = pref.edit()
            editor.putInt(APP_PREFERENCES_COUNTER, counter)
            editor.apply()
            buttonCounter.setText(counter.toString())
        }
        buttonDecrease.setOnClickListener {
            counter--
            val editor = pref.edit()
            editor.putInt(APP_PREFERENCES_COUNTER, counter)
            editor.apply()
            buttonCounter.setText(counter.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                val aboutIntent = Intent(this, AboutActivity::class.java)
                startActivity(aboutIntent)
                return true
            }
            R.id.action_set_zero -> {
                counter = 0
                val editor = pref.edit()
                editor.putInt(APP_PREFERENCES_COUNTER, counter)
                editor.apply()
                buttonCounter.setText(counter.toString())
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        if (pref.contains(APP_PREFERENCES_COUNTER)) {
            counter = pref.getInt(APP_PREFERENCES_COUNTER, 0);
            buttonCounter.setText(counter.toString())
        }else{
            buttonCounter.setText("0")
        }
    }
}
