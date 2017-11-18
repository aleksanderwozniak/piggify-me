package com.olq.piggifyme.screens.details.expense

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

class ExpenseFragment : Fragment(), ExpenseScreenContract.View {

    companion object {
        fun newInstance(): ExpenseFragment {
            return ExpenseFragment()
        }
    }

    override lateinit var presenter: ExpenseScreenContract.Presenter
    private lateinit var expensesList: RecyclerView


    override fun onResume() {
        super.onResume()
        presenter.start()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter = ExpensePresenter(this, Injector.provideModel(context))

        val view = inflater!!.inflate(R.layout.fragment_expense, container, false)

        expensesList = view.find(R.id.mExpensesRecyclerView)
        expensesList.layoutManager = LinearLayoutManager(context)

        return view
    }


    override fun showDetailList(list: List<Pair<String, Int>>) {
        expensesList.adapter = DetailAdapter(list)
    }
}