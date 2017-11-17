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


    // returns true if new item was successfully added
    override fun onNewItemAdded(dialogType: DialogType, data: Pair<String, String>): Boolean {
        if (isUserInputValid(data)) {
            val sourceName = data.first
            val addedValue = data.second.toInt()

            updateValueData(dialogType, addedValue)

            model.pushData(Triplet(
                    type = dialogType.toString(),
                    source = sourceName,
                    value = addedValue))

            return true
        }

        return false
    }

    private fun isUserInputValid(data: Pair<String, String>): Boolean {
        if (!isSourceNameValid(data.first)) {
            mainView.showSourceNameError("Source field can not be blank")
            return false
        }

        if (!isValueValid(data.second)) {
            mainView.showValueError("Amount can not be empty or 0")
            return false
        }

        return true
    }

    private fun isSourceNameValid(source: String): Boolean {
        return source.isNotBlank()
    }

    private fun isValueValid(value: String): Boolean {
        return value.isNotBlank() && value.toInt() > 0
    }


    private fun updateValueData(dialogType: DialogType, value: Int) {
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


    override fun onResetClick() {
        model.resetData()
        updateViews()
    }
}