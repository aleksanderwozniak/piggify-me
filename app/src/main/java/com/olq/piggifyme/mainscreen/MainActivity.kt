package com.olq.piggifyme.mainscreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.olq.piggifyme.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.floating_menu_layout.*
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.Appcompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.contentFrame, MainFragment.newInstance(), "fragmentMain")
                    .commit()
        }


        fab_income.setOnClickListener { showNewItemDialog(DialogType.DIALOG_INCOME) }
        fab_expense.setOnClickListener { showNewItemDialog(DialogType.DIALOG_EXPENSE) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_reset -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showNewItemDialog(dialogType: DialogType) {
        val alertLayout = layoutInflater.inflate(R.layout.dialog_layout, null) as View
        val mValueEditText = alertLayout.find<EditText>(R.id.editText)

        alert (Appcompat, "Enter amount of cash", dialogType.toString()) {
            customView = alertLayout
            yesButton {
                // call the presenter
                // validate user input
                toast("Added new $dialogType with value of ${mValueEditText.text}") }
            noButton {  }
        }.show()
    }
}
