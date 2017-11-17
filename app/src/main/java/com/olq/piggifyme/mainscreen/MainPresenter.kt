package com.olq.piggifyme.mainscreen

import com.olq.piggifyme.data.Model
import com.olq.piggifyme.data.database.Triplet

/**
 * Created by olq on 11.11.17.
 */
class MainPresenter(private val mainView: MainScreenContract.View,
                    val model: Model)
    : MainScreenContract.Presenter {

    init {
        mainView.presenter = this
    }


    override fun start() {
        model.pullData()
        updateViews()
    }

    private fun updateViews() {
        mainView.updateIncomeView(model.incomeValue)
        mainView.updateExpenseView(model.expenseValue)
        mainView.updateBalanceView(model.calculateBalance())
    }


    override fun onFABItemClick(dialogType: DialogType) {
        mainView.showNewItemDialog(dialogType)
    }


    override fun onNewItemAdded(dialogType: DialogType, data: Pair<String, String>) {
        val sourceName = (data.first)
        val addedValue = validateUserInput(data.second)

        updateValueData(dialogType, addedValue)

        model.pushData(Triplet(
                type = dialogType.toString(),
                source = sourceName,
                value = addedValue))
    }


    private fun validateUserInput(amount: String): Int {
        return if (amount.isEmpty()) 0 else amount.toInt()
    }

    private fun updateValueData(dialogType: DialogType, value: Int) {
        if (value > 0) {
            when (dialogType) {
                DialogType.DIALOG_INCOME -> {
                    model.incomeValue += value
                    mainView.updateIncomeView(model.incomeValue)
                }

                DialogType.DIALOG_EXPENSE -> {
                    model.expenseValue += value
                    mainView.updateExpenseView(model.expenseValue)
                }
            }

            mainView.updateBalanceView(model.calculateBalance())
        }
    }

    override fun onResetClick() {
        model.resetData()
        updateViews()
    }
}