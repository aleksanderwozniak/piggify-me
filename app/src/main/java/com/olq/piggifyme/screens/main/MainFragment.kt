package com.olq.piggifyme.screens.main

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.olq.piggifyme.R
import com.olq.piggifyme.injector.Injector
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.toast

class MainFragment : Fragment(), MainScreenContract.View {

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override lateinit var presenter: MainScreenContract.Presenter
    lateinit var alertLayout: View
    lateinit var mSourceEditText: EditText
    lateinit var mValueEditText: EditText



    override fun onResume() {
        super.onResume()
        presenter.start()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter = MainPresenter(this, Injector.provideModel(context))

        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun showNewItemDialog(dialogType: DialogType) {
        alertLayout = layoutInflater.inflate(R.layout.dialog_layout, null)

        mSourceEditText = alertLayout.find(R.id.mSourceEditText)
        mValueEditText = alertLayout.find(R.id.mAmountEditText)

        val title = if (dialogType == (DialogType.DIALOG_INCOME)) "New income" else "New expense"

        val mAlert = alert ("Fill both fields listed below", title = title) {
            customView = alertLayout
            positiveButton("OK") { }
            noButton { }
        }.show()


        mAlert.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val data = Pair(mSourceEditText.text.toString(), mValueEditText.text.toString())

            if (presenter.onNewItemAdded(dialogType, data)) {
                toast("New item added")
                mAlert.dismiss()
            }
        }

    }

    override fun showSourceNameError(errorMsg: String) {
        mSourceEditText.error = errorMsg
    }

    override fun showValueError(errorMsg: String) {
        mValueEditText.error = errorMsg
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
