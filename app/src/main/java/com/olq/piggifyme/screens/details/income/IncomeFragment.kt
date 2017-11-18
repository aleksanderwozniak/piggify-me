package com.olq.piggifyme.screens.details.income

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.olq.piggifyme.R
import com.olq.piggifyme.injector.Injector
import com.olq.piggifyme.screens.details.DetailAdapter
import org.jetbrains.anko.find

class IncomeFragment : Fragment(), IncomeScreenContract.View {

    companion object {
        fun newInstance(): IncomeFragment {
            return IncomeFragment()
        }
    }

    override lateinit var presenter: IncomeScreenContract.Presenter
    private lateinit var incomesList: RecyclerView


    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter = IncomePresenter(this, Injector.provideModel(context))

        val view = inflater!!.inflate(R.layout.fragment_income, container, false)

        incomesList = view.find(R.id.mIncomesRecyclerView)
        incomesList.layoutManager = LinearLayoutManager(context)

        return view
    }


    override fun showDetailList(list: List<Pair<String, Int>>) {
        incomesList.adapter = DetailAdapter(list)
    }
}
