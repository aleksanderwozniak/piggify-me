package com.olq.piggifyme.mainscreen

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.olq.piggifyme.R
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.toast

class MainFragment : Fragment(), MainScreenContract.View {

    override lateinit var presenter: MainScreenContract.Presenter


    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }


    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }



    override fun showNewItemDialog(dialogType: DialogType) {
        val alertLayout = layoutInflater.inflate(R.layout.dialog_layout, null) as View
        val mValueEditText = alertLayout.find<EditText>(R.id.editText)

        alert ("Enter amount of cash", dialogType.toString()) {
            customView = alertLayout
            yesButton {
                presenter.onNewItemAdded(dialogType, mValueEditText.text.toString())
                toast("New item added") }
            noButton {  }
        }.show()
    }

    override fun updateIncomeView(value: Int) {
        mIncomeTextView.text = getString(R.string.income_text, value)
    }

    override fun updateExpenseView(value: Int) {
        mExpenseTextView.text = getString(R.string.expense_text, value)
    }

    override fun updateBalanceView(value: Int) {
        mBalanceTextView.text = getString(R.string.balance_text, value)
    }
}
