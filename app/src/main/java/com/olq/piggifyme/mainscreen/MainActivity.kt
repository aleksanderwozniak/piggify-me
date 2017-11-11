package com.olq.piggifyme.mainscreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.olq.piggifyme.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.floating_menu_layout.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    lateinit var presenter: MainScreenContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val fragment = MainFragment.newInstance()

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.contentFrame, fragment, "fragmentMain")
                    .commit()
        }


        presenter = MainPresenter(fragment, Model())

        fab_income.setOnClickListener { presenter.onFABItemClick(DialogType.DIALOG_INCOME) }
        fab_expense.setOnClickListener { presenter.onFABItemClick(DialogType.DIALOG_EXPENSE) }
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
                presenter.onResetClick()
                toast("Data re-set")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
