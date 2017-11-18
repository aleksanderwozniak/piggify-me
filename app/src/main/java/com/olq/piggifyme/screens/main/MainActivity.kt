package com.olq.piggifyme.screens.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.olq.piggifyme.BasePresenter
import com.olq.piggifyme.BaseView
import com.olq.piggifyme.R
import com.olq.piggifyme.screens.details.IncomeFragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.floating_menu_layout.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    companion object {
        private val MAIN_FRAGMENT = "MainFragment"
        private val INCOME_FRAGMENT = "IncomeFragment"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        if (savedInstanceState == null) {
            val fragment = MainFragment.newInstance()

            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.contentFrame, fragment, MAIN_FRAGMENT)
                    .commit()

        }

        bottom_navigation.setOnNavigationItemSelectedListener { item -> onBottomNavClick(item) }
    }

    fun onFabIncomeClick(view: View) {
        (supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT) as MainFragment)
                .presenter.onFABItemClick(DialogType.DIALOG_INCOME)
    }

    fun onFabExpenseClick(view: View) {
        (supportFragmentManager.findFragmentByTag(MAIN_FRAGMENT) as MainFragment)
                .presenter.onFABItemClick(DialogType.DIALOG_EXPENSE)
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
            R.id.action_reset -> {
                // every fragment implements BaseView<BasePresenter>
                val fragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as BaseView<BasePresenter>
                fragment.presenter.onResetClick()
                toast("Data re-set")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onBottomNavClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_details -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.contentFrame, IncomeFragment.newInstance(), INCOME_FRAGMENT)
                        .addToBackStack(null)
                        .commit()
                fab.visibility = View.GONE
            }

            R.id.menu_overview -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.contentFrame, MainFragment.newInstance(), MAIN_FRAGMENT)
                        .addToBackStack(null)
                        .commit()
                fab.visibility = View.VISIBLE
            }
        }

        return true
    }
}
