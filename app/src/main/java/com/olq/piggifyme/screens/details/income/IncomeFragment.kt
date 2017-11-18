package com.olq.piggifyme.screens.details.income


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.olq.piggifyme.R
import com.olq.piggifyme.injector.Injector
import kotlinx.android.synthetic.main.fragment_income.*

class IncomeFragment : Fragment(), IncomeScreenContract.View {

    companion object {
        fun newInstance(): IncomeFragment {
            return IncomeFragment()
        }
    }

    override lateinit var presenter: IncomeScreenContract.Presenter


    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter = IncomePresenter(this, Injector.provideModel(context))

        return inflater!!.inflate(R.layout.fragment_income, container, false)
    }



    override fun showDetailList(list: List<String>) {
        mIncomesListView.adapter = ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, list)
    }
}
